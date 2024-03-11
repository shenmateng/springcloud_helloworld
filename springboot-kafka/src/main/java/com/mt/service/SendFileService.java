package com.mt.service;


import com.alibaba.fastjson.JSON;
import com.mt.database.AspectKafkaDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @author mateng
 * @version 1.0.0
 * @description SendFileServiceImpl
 * @since 2022/3/3 14:58
 */
@Slf4j
@Service
public class SendFileService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendKafkaMessage(AspectKafkaDomain aspectKafkaDomain) {
        log.info("放入kafka开始");
        for (int i = 0; i < 1; i++) {
            aspectKafkaDomain.setOperate(i);
            ListenableFuture<SendResult<String, String>> send = this.kafkaTemplate.send(aspectKafkaDomain.getTopical(), JSON.toJSONString(aspectKafkaDomain));
            System.out.println(send);
        }
        log.info("放入kafka结束");
    }

}
