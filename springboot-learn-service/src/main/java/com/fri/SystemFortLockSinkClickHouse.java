package com.fri;

import com.fri.common.SystemFortLockMapFunction;
import com.fri.config.FlinkNacosConfig;
import com.fri.domain.SystemFortLockRaw;
import com.fri.utils.InsertClickHouseUtil;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SystemFortLockSinkClickHouse {

//    public static void main(String[] args) throws Exception {
//        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        //以每分钟频率做检查点
//        env.enableCheckpointing(60 * 1000L);
//        //指定设置检查点执行的超时时间为30s
//        env.getCheckpointConfig().setCheckpointTimeout(30 * 1000L);
////        env.setRestartStrategy(RestartStrategies.noRestart());
//        env.setRestartStrategy(RestartStrategies.failureRateRestart( 3, // 一个时间段内的最大失败次数
//                Time.of(5, TimeUnit.MINUTES), // 衡量失败次数的是时间段
//                Time.of(10, TimeUnit.SECONDS) // 间隔
//        ));
//
//        //source
//        Properties props = new Properties();
//        //加载nacos配置文件
//        String topic = FlinkNacosConfig.getConfigValue("topic");
//        String url = FlinkNacosConfig.getConfigValue("url");
//        String username = FlinkNacosConfig.getConfigValue("username");
//        String password = FlinkNacosConfig.getConfigValue("password");
//        setKafkaProperty(props);
//        //定义Flink Kafka Consumer
//        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), props);
//        //添加source数据流
//        DataStreamSource<String> source = env.addSource(consumer);
//        DataStream<List<SystemFortLockRaw>> dataStream = source.map(new SystemFortLockMapFunction());
//        //sink
//        InsertClickHouseUtil clickHouseUtil = new InsertClickHouseUtil(url, username, password);
//        dataStream.addSink(clickHouseUtil);
//        env.execute("Flink Streaming ETL Original SystemFortLock SinkClickHouse");
//    }

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
