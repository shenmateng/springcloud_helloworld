package com.mt.service;

import com.mt.database.ShopifyNewListingVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：redis测试类
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/12/22 14:41
 * @since ：1.1.1
 */

@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void testRedis() {
        String auKey = "com:mt:redis:test:";
        List<ShopifyNewListingVO> auData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ShopifyNewListingVO shopifyNewListingVO = new ShopifyNewListingVO();
            shopifyNewListingVO.setSiteCode("AU" + i);
            shopifyNewListingVO.setSku("mateng" + i);
            shopifyNewListingVO.setCategory1("一级类目" + i);
            shopifyNewListingVO.setCategory2("二级类目" + i);
            shopifyNewListingVO.setCategory3("三级类目" + i);
            auData.add(shopifyNewListingVO);
        }

        redisTemplate.opsForList().leftPushAll(auKey, auData);

    }


}
