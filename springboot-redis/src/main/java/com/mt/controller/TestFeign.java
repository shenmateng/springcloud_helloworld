package com.mt.controller;

import com.vevor.prm.api.IMappingManageApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/31 16:39
 * @since ：
 */

@Controller
@RequestMapping("/controller-feign")
public class TestFeign {

    @Resource
    private IMappingManageApiService iMappingManageApiService;

    /**
     *
     */
    @PostMapping("/test1")
    public void getEbayData() {
        String etsyData1 = iMappingManageApiService.getEtsyData1();
        System.out.println(etsyData1);
    }
}
