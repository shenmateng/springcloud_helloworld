package com.mt.controller;

import com.mt.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：redis 测试Controller
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/12/22 15:32
 * @since ：1.1.1
 */
@Slf4j
@RestController
@RequestMapping("/controller-redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    /**
     *
     */
    @PostMapping("/test1")
    public void getEbayData() {
        redisService.testRedis();
    }


}
