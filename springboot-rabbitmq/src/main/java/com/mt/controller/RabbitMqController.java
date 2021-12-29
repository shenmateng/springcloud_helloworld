package com.mt.controller;

import com.mt.service.ReceiveRabbitmqService;
import com.mt.service.SendrabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：测试rabbitmqController
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/12/28 16:19
 * @since ：1.1.1
 */
@Slf4j
@RestController
@RequestMapping("/controller-rabbitmq")
public class RabbitMqController {

    @Resource
    private SendrabbitMqService sendrabbitMqService;


    /**
     *
     */
    @PostMapping("/test1")
    public void testRabbitmq() {
        sendrabbitMqService.testRabbitMq();
    }



}
