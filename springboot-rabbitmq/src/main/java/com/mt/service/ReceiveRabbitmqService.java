package com.mt.service;

import com.mt.config.MallRabbitmqConfig;
import com.mt.database.TemplateVevorListAndTaskDO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/12/28 17:56
 * @since ：
 */

@Service
@Slf4j
public class ReceiveRabbitmqService {

    @RabbitListener(queues = MallRabbitmqConfig.COM_MT_QUEUE)
    public void testMq(TemplateVevorListAndTaskDO templateVevorListAndTaskDO, Channel channel, Message message) throws IOException {
        try {
            System.out.println(templateVevorListAndTaskDO.toString());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("生成模板错误信息", e);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

    }
}
