package com.mt.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class SystemConfig {

    @Bean(name = "assetThreadPool")
    public ThreadPoolTaskExecutor assetThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100000);
        executor.setKeepAliveSeconds(10);
        executor.setThreadNamePrefix("asset-thread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Value("${spring.elasticsearch.jest.uris}")
    private String uris;
    @Value("${spring.elasticsearch.jest.username}")
    private String username;
    @Value("${spring.elasticsearch.jest.password}")
    private String password;
    @Value("${spring.elasticsearch.jest.connection-timeout}")
    private Integer connTimeout;
    @Value("${spring.elasticsearch.jest.read-timeout}")
    private Integer readTimeout;

    @Bean
    public JestClient getJestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(Arrays.asList(uris.split(",")))
                .defaultCredentials(username, password)
                .connTimeout(connTimeout)
                .readTimeout(readTimeout)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
