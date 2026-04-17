package com.fri.datasync;

import com.fri.common.JsonDebeziumDeserializationSchema;
import com.fri.config.FlinkNacosConfig;
import org.apache.flink.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.cdc.connectors.mysql.table.StartupOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Flink CDC 监听 prm_test.zc_machine 表变更，发送到 Kafka
 * 适配 Flink 1.19.3 + Flink CDC 3.x
 *
 * @author mateng
 */
@Slf4j
public class SyncZcMachineToKafka {

    public static void main(String[] args) throws Exception {
        // 1. 从 Nacos 读取配置
        String mysqlHost = FlinkNacosConfig.getConfigValue("cdc.mysql.host");
        int mysqlPort = Integer.parseInt(FlinkNacosConfig.getConfigValue("cdc.mysql.port"));
        String mysqlDatabase = FlinkNacosConfig.getConfigValue("cdc.mysql.database");
        String mysqlTable = FlinkNacosConfig.getConfigValue("cdc.mysql.table");
        String mysqlUsername = FlinkNacosConfig.getConfigValue("cdc.mysql.username");
        String mysqlPassword = FlinkNacosConfig.getConfigValue("cdc.mysql.password");
        String kafkaBrokers = FlinkNacosConfig.getConfigValue("cdc.kafka.brokers");
        String kafkaTopic = FlinkNacosConfig.getConfigValue("cdc.kafka.topic");

        log.info("CDC配置 - MySQL: {}:{}/{}.{}, Kafka: {}/{}",
                mysqlHost, mysqlPort, mysqlDatabase, mysqlTable, kafkaBrokers, kafkaTopic);

        // 2. 创建 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.enableCheckpointing(30 * 1000L);
        env.getCheckpointConfig().setCheckpointTimeout(60 * 1000L);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

        // 3. 创建 MySQL CDC Source
        MySqlSource<String> mysqlSource = MySqlSource.<String>builder()
                .hostname(mysqlHost)
                .port(mysqlPort)
                .databaseList(mysqlDatabase)
                .tableList(mysqlTable)
                .username(mysqlUsername)
                .password(mysqlPassword)
                .startupOptions(StartupOptions.initial())
                .scanNewlyAddedTableEnabled(true)
                .deserializer(new JsonDebeziumDeserializationSchema())
                .build();

        // 4. 读取 CDC 数据流
        DataStreamSource<String> cdcStream = env.fromSource(
                mysqlSource,
                WatermarkStrategy.noWatermarks(),
                "MySQL CDC Source - zc_machine"
        );

        // 调试用
        cdcStream.print("zc_machine变更>>>");

        // 5. 创建 Kafka Sink（Flink 1.19 新 API）
        KafkaSink<String> kafkaSink = KafkaSink.<String>builder()
                .setBootstrapServers(kafkaBrokers)
                .setRecordSerializer(
                        KafkaRecordSerializationSchema.builder()
                                .setTopic(kafkaTopic)
                                .setValueSerializationSchema(new SimpleStringSchema())
                                .build()
                )
                .build();

        // 6. 发送到 Kafka
        cdcStream.sinkTo(kafkaSink).name("Kafka Sink - " + kafkaTopic);

        // 7. 启动
        env.execute("Sync zc_machine CDC to Kafka");
    }
}
