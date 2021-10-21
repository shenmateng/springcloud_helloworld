package com.mt.user.agency.dynamicAgency;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 10:22
 * @since ：
 */
//当前类的对象是目标对象
public class TargetJDKClass implements TargetJDKClassInterface,TargetJDKClassInterface1 {

    @Override
    public void save() {
        System.out.println("target......save.......");
    }

    @Override
    public void savess() {
        System.out.println("target......save.......");
    }
}
