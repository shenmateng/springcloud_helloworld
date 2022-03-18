package com.mt.design.builder;

import lombok.Data;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：产品角色
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 14:37
 * @since ：1.1.1
 */
@Data
public class Product {

    private String partA;
    private String partB;
    private String partC;

    public void show() {
        //显示产品的特性
        System.out.println(partA);
        System.out.println(partB);
        System.out.println(partC);
    }
}
