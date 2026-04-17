package com.fri.datasync;

import com.fri.common.JsonDebeziumDeserializationSchema;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.Properties;

/**
 * Flink CDC 监听 prm_test.zc_machine 表变更，发送到 Kafka
 *
 * 使用方式：
 * 1. 本地调试：直接运行 main 方法（需要 IDEA 配置 flink provided scope 为 compile）
 * 2. 集群部署：mvn package 后提交到 Flink 集群
 *    flink run -c com.fri.datasync.SyncZcMachineToKafka springboot-learn-service-1.0.0.jar
 *
 * @author mateng
 */
@Slf4j
public class SyncZcMachineToKafka {

    // ========== MySQL 配置（prm_test 库） ==========
    private static final String MYSQL_HOST = "58.87.74.91";
    private static final int MYSQL_PORT = 3306;
    private static final String MYSQL_DATABASE = "prm_test";
    private static final String MYSQL_TABLE = "prm_test.zc_machine";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "你的数据库密码";

    // ========== Kafka 配置 ==========
    private static final String KAFKA_BROKERS = "58.87.74.91:9092";
    private static final String KAFKA_TOPIC = "zc_machine_cdc";

    public static void main(String[] args) throws Exception {
        // 1. 创建 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // Checkpoint 配置（断点续传）
        env.enableCheckpointing(30 * 1000L);
        env.getCheckpointConfig().setCheckpointTimeout(60 * 1000L);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

        // 2. 创建 MySQL CDC Source
        MySqlSource<String> mysqlSource = MySqlSource.<String>builder()
                .hostname(MYSQL_HOST)
                .port(MYSQL_PORT)
                .databaseList(MYSQL_DATABASE)
                .tableList(MYSQL_TABLE)
                .username(MYSQL_USERNAME)
                .password(MYSQL_PASSWORD)
                // initial: 先全量快照再增量；latest-offset: 只读增量
                .startupOptions(StartupOptions.initial())
                // 监听新增的表
                .scanNewlyAddedTableEnabled(true)
                // 反序列化 binlog 为 JSON
                .deserializer(new JsonDebeziumDeserializationSchema())
                .build();

        // 3. 读取 CDC 数据流
        DataStreamSource<String> cdcStream = env.fromSource(
                mysqlSource,
                WatermarkStrategy.noWatermarks(),
                "MySQL CDC Source - zc_machine"
        );

        // 4. 打印到控制台（调试用，生产可去掉）
        cdcStream.print("zc_machine变更>>>");

        // 5. 创建 Kafka Sink
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("bootstrap.servers", KAFKA_BROKERS);
        // 如果 Kafka 开启了 SASL 认证，取消下面注释
        // kafkaProps.setProperty("security.protocol", "SASL_PLAINTEXT");
        // kafkaProps.setProperty("sasl.mechanism", "PLAIN");
        // kafkaProps.setProperty("sasl.jaas.config",
        //     "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"admin\" password=\"xxx\";");

        FlinkKafkaProducer<String> kafkaSink = new FlinkKafkaProducer<>(
                KAFKA_TOPIC,
                new SimpleStringSchema(),
                kafkaProps
        );

        // 6. 将 CDC 数据发送到 Kafka
        cdcStream.addSink(kafkaSink).name("Kafka Sink - " + KAFKA_TOPIC);

        // 7. 启动任务
        env.execute("Sync zc_machine CDC to Kafka");
    }
}
