package com.fri.datasync;

import com.fri.common.JsonDebeziumDeserializationSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.cdc.connectors.mysql.table.StartupOptions;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Flink CDC 监听 prm_test.zc_machine 表变更，发送到 Kafka
 * 先用硬编码配置跑通，后续再接 Nacos
 *
 * @author mateng
 */
@Slf4j
public class SyncZcMachineToKafka {

    public static void main(String[] args) throws Exception {
        // ========== 配置（先硬编码，跑通后再改成 Nacos） ==========
        String mysqlHost = "58.87.74.91";
        int mysqlPort = 3306;
        String mysqlDatabase = "prm_test";
        String mysqlTable = "prm_test.zc_machine";
        String mysqlUsername = "root";
        String mysqlPassword = "962396";  // 改成真实密码
        String kafkaBrokers = "localhost:9092";
        String kafkaTopic = "zc_machine_cdc";

        log.info("CDC配置 - MySQL: {}:{}/{}, Kafka: {}/{}",
                mysqlHost, mysqlPort, mysqlTable, kafkaBrokers, kafkaTopic);

        // 1. 创建 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.enableCheckpointing(30 * 1000L);
        env.getCheckpointConfig().setCheckpointTimeout(60 * 1000L);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

        // 2. 创建 MySQL CDC Source
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

        // 3. 读取 CDC 数据流
        DataStreamSource<String> cdcStream = env.fromSource(
                mysqlSource,
                WatermarkStrategy.noWatermarks(),
                "MySQL CDC Source - zc_machine"
        );

        cdcStream.print("zc_machine变更>>>");

        // 4. Kafka Sink
        KafkaSink<String> kafkaSink = KafkaSink.<String>builder()
                .setBootstrapServers(kafkaBrokers)
                .setRecordSerializer(
                        KafkaRecordSerializationSchema.builder()
                                .setTopic(kafkaTopic)
                                .setValueSerializationSchema(new SimpleStringSchema())
                                .build()
                )
                .build();

        cdcStream.sinkTo(kafkaSink).name("Kafka Sink - " + kafkaTopic);

        // 5. 启动
        env.execute("Sync zc_machine CDC to Kafka");
    }
}
