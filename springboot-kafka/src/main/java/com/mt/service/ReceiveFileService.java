package com.mt.service;


import com.alibaba.fastjson.JSON;
import com.mt.database.AspectKafkaDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2023/3/18 16:19
 * @Version: 2.1.5
 * @Description: kafka接受端
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
@Service
public class ReceiveFileService {


    @KafkaListeners({
            @KafkaListener(topics = {"shenmateng"},groupId = "tianyibukewei"),

    })
    public void ConsumerMessage(ConsumerRecord<?, ?> record , Acknowledgment ack) {
        sendMessage(record,ack);
    }

    public void sendMessage (ConsumerRecord<?, ?> record , Acknowledgment ack) {
        try {
            String value = record.value() == null ? null : record.value().toString();
            if (value == null || value.isEmpty()) {
                log.warn("收到空消息，topic: {}, partition: {}, offset: {}",
                        record.topic(), record.partition(), record.offset());
                return;
            }

            // 将消息 JSON 转换为 AspectKafkaDomain 对象
            AspectKafkaDomain domain = JSON.parseObject(value, AspectKafkaDomain.class);
            log.info("第一个消费者收到消息，topic: {}, partition: {}, offset: {}, domain: {}",
                    record.topic(), record.partition(), record.offset(), domain);

            // TODO: 在这里处理 domain 业务逻辑

        } catch (Exception e) {
            log.error("消息处理异常，topic: {}, offset: {}, value: {}",
                    record.topic(), record.offset(), record.value(), e);
        } finally {
            ack.acknowledge();
        }
    }
}
