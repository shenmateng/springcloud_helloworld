package com.mt.service;

import com.mt.database.ShopifyNewListingVO;
import com.vevor.bmp.common.constant.RedisConstant;
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
        String auKey1 = "com:mt:redis:hashput";
        String auKey3 = "com:mt:redis:put";
        List<ShopifyNewListingVO> auData = new ArrayList<>();
        String sss = "测试hashkey";
        for (int i = 0; i < 10; i++) {
            ShopifyNewListingVO shopifyNewListingVO = new ShopifyNewListingVO();
            shopifyNewListingVO.setSiteCode("AU" + i);
            shopifyNewListingVO.setSku("mateng" + i);
            shopifyNewListingVO.setCategory1("一级类目" + i);
            shopifyNewListingVO.setCategory2("二级类目" + i);
            shopifyNewListingVO.setCategory3("三级类目" + i);
            auData.add(shopifyNewListingVO);
        }

        redisTemplate.opsForList().leftPushAll(auKey3, auData);
        redisTemplate.opsForHash().put(auKey1, "AU1", sss);
        redisTemplate.opsForHash().put(auKey1, "AU2", auKey);
        redisTemplate.opsForHash().put(auKey1, "AU3", auData.get(0));
        redisTemplate.opsForHash().put(auKey1, "AU4", auData);
    }


}
