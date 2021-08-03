package com.mt.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //开启eureka客户端
public class ProviderMovieStart {

    public static void main(String[] args) {

        SpringApplication.run(ProviderMovieStart.class);
    }

}
