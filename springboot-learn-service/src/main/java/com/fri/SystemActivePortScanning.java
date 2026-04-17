package com.fri;

import com.fri.config.FlinkNacosConfig;
import com.fri.domain.scanevent.ScanEventLogDO;
import com.fri.service.*;
import com.fri.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * @Author: mateng
 * @program: learn-service
 * @Date: 2023/5/12 15:40
 * @Version: 2.1.5.2
 * @Description: 管理中心针对主动发起扫描主机进行告警
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
@Slf4j
public class SystemActivePortScanning {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //以每分钟频率做检查点
        env.enableCheckpointing(60 * 1000L);
        //指定设置检查点执行的超时时间为30s
        env.getCheckpointConfig().setCheckpointTimeout(30 * 1000L);
        env.setRestartStrategy(RestartStrategies.noRestart());

        //source
        Properties props = new Properties();
        //加载nacos配置文件
        String topic = FlinkNacosConfig.getConfigValue("scantopic");

        setKafkaProperty(props);
        //定义Flink Kafka Consumer
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), props);
        //添加source数据流
        DataStreamSource<String> kafkaMailMsg = env.addSource(consumer);

        //② 筛选登录消息，创建初始登录事件流
        SingleOutputStreamOperator<ScanEventLogDO> loginMapDs = kafkaMailMsg.map(new ScanEventLogToBeanService()).name("Map算子加工流");

        //③ 设置水位线  延迟时间1秒   空闲时间3分
        WatermarkStrategy<ScanEventLogDO> watermarkStrategy = WatermarkStrategy.<ScanEventLogDO>forBoundedOutOfOrderness(Duration.ofSeconds(1))
                .withTimestampAssigner((eventMsg, timestamp) -> TimeUtils.longToTimestamp(eventMsg.getPushTime()));
        SingleOutputStreamOperator<ScanEventLogDO> loginWmDs = loginMapDs.assignTimestampsAndWatermarks(watermarkStrategy.withIdleness(Duration.ofSeconds(30))).name("增加水位线");

        //④ 设置主键
        KeyedStream<ScanEventLogDO, String> loginKeyedDs = loginWmDs.keyBy(new LoginKeySelectorService());
        //⑥ 转化为滑动窗口  窗口大小300秒   滑动步长300秒
        WindowedStream<ScanEventLogDO, String, TimeWindow> loginWindowDs = loginKeyedDs.window(DynSlidingEventTimeWindowsService.of(Time.seconds(300L),Time.seconds(300L)));

        //⑦ 在窗口内进行逻辑统计
        SingleOutputStreamOperator<List<ScanEventLogDO>> loginWindowsDealDs  = loginWindowDs.process(new WindowProcessFuncService()).name("窗口处理逻辑");

        //⑧ 将结果转化为通用DataStream<String>格式
        SingleOutputStreamOperator<String> resultDs  = loginWindowsDealDs.map(new AlarmEeventToStringService()).name("窗口结果转化为标准格式");

        //将结果输出到kafka
        resultDs.addSink(new FlinkKafkaProducer<String>("ssk_eventlog",new SimpleStringSchema(),props));

        env.execute("Flink Streaming ETL Original SystemActivePortScanning");

    }


    private static void setKafkaProperty(Properties props) {
        //设置连接kafka集群的参数
        props.setProperty("bootstrap.servers", FlinkNacosConfig.getConfigValue("bootstrap.servers"));
        props.setProperty("group.id", FlinkNacosConfig.getConfigValue("group.id"));
        //设置每次消费新产生的该分区下的数据
        props.setProperty("auto.offset.reset", FlinkNacosConfig.getConfigValue("auto.offset.reset"));
        props.setProperty("key.deserializer", FlinkNacosConfig.getConfigValue("key.deserializer"));
        props.setProperty("value.deserializer", FlinkNacosConfig.getConfigValue("value.deserializer"));
        props.setProperty("security.protocol", FlinkNacosConfig.getConfigValue("security.protocol"));
        props.setProperty("sasl.mechanism", FlinkNacosConfig.getConfigValue("sasl.mechanism"));
        props.setProperty("sasl.jaas.config", FlinkNacosConfig.getConfigValue("sasl.jaas.config"));
    }
}
