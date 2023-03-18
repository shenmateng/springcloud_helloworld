package com.mt.controller;


import com.mt.database.AspectKafkaDomain;
import com.mt.service.SendFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author mateng
 * @version 1.0.0
 * @description SendMessageController
 * @since 2022/2/16 16:06
 */
@Slf4j
@RestController
@RequestMapping("springboot" +"/sendMessage")
public class SendMessageController {

    @Autowired
    private SendFileService sendFileService;

    /**
     * 推送数据到kafka
     * @param aspectKafkaDomain
     * @return
     */
    @RequestMapping("/sendKafka")
    @ResponseBody
    public void sendKafka (@RequestBody @Valid AspectKafkaDomain aspectKafkaDomain) {

        log.info("分中心推送kafka开始");
        sendFileService.sendKafkaMessage(aspectKafkaDomain);
        log.info("分中心推送kafka结束");
    }

}
