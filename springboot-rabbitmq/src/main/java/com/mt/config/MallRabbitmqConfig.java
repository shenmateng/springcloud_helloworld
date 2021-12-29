/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.mt.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：mateng
 * @version ：B1.3.0
 * @program ：bmp-prm
 * @date : Created in 2020/12/8 13:56
 * @description ：
 */
@Configuration
public class MallRabbitmqConfig {

    /**
     * 交换机名称
     */
    public static final String COM_MT_EXCHANGE = "com_mt_exchange";


    /**
     * 创建队列
     */
    public static final String COM_MT_QUEUE = "com_mt_queue";


    /**
     *    声明创建交换机
     */
    @Bean(COM_MT_EXCHANGE)
    public TopicExchange vevorTemplateExchange(){
        return new TopicExchange(COM_MT_EXCHANGE, true, true);
    }

    /**
     * 创建队列
     * @return 创建队列
     */
    @Bean(COM_MT_QUEUE)
    public Queue vevorTemplateQueue(){
        return new Queue(COM_MT_QUEUE);
    }

    /**
     * 创建vevor模板队列与交换机的绑定
     */
    @Bean
    public Binding createVevorTemplateExchange(@Qualifier(COM_MT_QUEUE) Queue queue, @Qualifier(COM_MT_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("ma_teng.ss").noargs();
    }

}
