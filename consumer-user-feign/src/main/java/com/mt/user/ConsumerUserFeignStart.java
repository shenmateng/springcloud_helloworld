package com.mt.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients  //开启Feign功能
@EnableCircuitBreaker  //开启断路保护功能
@EnableHystrixDashboard
public class ConsumerUserFeignStart {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserFeignStart.class);
    }




}
