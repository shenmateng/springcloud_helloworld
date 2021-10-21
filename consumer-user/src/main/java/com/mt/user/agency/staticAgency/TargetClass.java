package com.mt.user.agency.staticAgency;

/**
 * @author ：mateng
 * @version ：
 * @description ：静态代理类
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 9:49
 * @since ：
 */
public class TargetClass implements TargetClassInterface {
    @Override
    public void save() {
        System.out.println("target......save.........");
    }
}
