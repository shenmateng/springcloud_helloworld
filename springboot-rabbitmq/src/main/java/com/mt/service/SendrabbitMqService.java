package com.mt.service;

import com.mt.database.TemplateVevorListAndTaskDO;
import com.mt.database.VevorNewListingReqVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
}
