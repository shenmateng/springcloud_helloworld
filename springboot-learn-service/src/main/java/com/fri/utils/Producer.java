package com.fri.utils;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-11 8:45
 * @Description:
 */
public class Producer {
    public static void main(String[] args) {
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "192.168.123.252:9092");
        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"admin\" password=\"jowto1234\";");
        //创建生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String value = "{\"eventId\":0,\"newMachineId\":\"1e7fbe1daf4520e5a1341efaee056713\",\"object\":{\"file\":\"/usr/local/gov_defence_agent/runlog/debug/bbb.php\"},\"operation\":\"create_file\",\"standardTimestamp\":1644199791,\"subject\":{\"process\":\"/usr/bin/vim\"}}";
        ProducerRecord record = new ProducerRecord<>("test_cyy", value);
        //发送记录
        producer.send(record);
        producer.close();
    }
}
