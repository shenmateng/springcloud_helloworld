package com.mt.service;

import com.alibaba.fastjson.JSONObject;
import com.mt.database.TemplateVevorListAndTaskDO;
import com.mt.database.VevorNewListingReqVO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;


import com.rabbitmq.client.Channel;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/12/28 17:22
 * @since ：
 */

@Service
public class SendrabbitMqService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;



    public void testRabbitMq(){

        String exchange = "com_mt_exchange";

        //路由key
        String keys = "ma_teng.ss";

        List<VevorNewListingReqVO> listvo = new ArrayList<>();
        VevorNewListingReqVO vevorNewListingReqVO = new VevorNewListingReqVO();
        vevorNewListingReqVO.setSiteCode("AU");
        vevorNewListingReqVO.setSku("aabbcc");
        vevorNewListingReqVO.setCategory1("一级类目");
        vevorNewListingReqVO.setCategory2("二级类目");
        vevorNewListingReqVO.setCategory3("三级类目");
        listvo.add(vevorNewListingReqVO);
        String username = "shenmateng";
        String taskId = "962396475";
        TemplateVevorListAndTaskDO templateVevorListAndTaskDO = new TemplateVevorListAndTaskDO();
        templateVevorListAndTaskDO.setVevorNewListingReq(listvo);
        templateVevorListAndTaskDO.setTaskId(taskId);
        templateVevorListAndTaskDO.setUsername(username);
        rabbitTemplate.convertAndSend(exchange, keys, templateVevorListAndTaskDO);

    }

    public void send(String queueName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "xx@163.com");
        jsonObject.put("timestamp", 0);
        String jsonString = jsonObject.toJSONString();
        System.out.println("jsonString:" + jsonString);
        // 设置消息唯一id 保证每次重试消息id唯一
        Message message = MessageBuilder.withBody(jsonString.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID() + "").build(); //消息id设置在请求头里面 用UUID做全局ID
        amqpTemplate.convertAndSend(queueName, message);
    }



    @RabbitListener(queues = "fanout_email_queue")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        // 获取消息Id
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "UTF-8");
        System.out.println("邮件消费者获取生产者消息msg:" + msg + ",消息id" + messageId);

        JSONObject jsonObject = JSONObject.parseObject(msg);
        Integer timestamp = jsonObject.getInteger("timestamp");

        try {
            int result = 1 / timestamp;
            System.out.println("result" + result);
            // // 手动ack
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            // 手动签收
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            //拒绝消费消息（丢失消息） 给死信队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

        System.out.println("执行结束....");
    }
}
