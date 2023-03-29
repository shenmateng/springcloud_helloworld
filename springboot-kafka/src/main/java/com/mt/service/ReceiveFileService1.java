package com.mt.service;


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
public class ReceiveFileService1 {


    @KafkaListeners({
            @KafkaListener(topics = {"shenmateng"},groupId = "smt"),
            @KafkaListener(topics = {"tianyibukewei"}),
            @KafkaListener(topics = {"dahuaxiyou"})
    })
    public void ConsumerMessage(ConsumerRecord<?, ?> record , Acknowledgment ack) {
        sendMessage(record,ack);
    }

    public void sendMessage (ConsumerRecord<?, ?> record , Acknowledgment ack) {
        log.info("进入客户端kafka消费者监听1");
        // 消费的哪个topic、partition的消息,打印出消息内容
        AspectKafkaDomain transfer = new AspectKafkaDomain();
        transfer.setTopical(record.topic());
        transfer.setData(record.value());
        ack.acknowledge();
    }
}
