package com.mt.design.factory.factorymethod;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：产品一
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/15 13:50
 * @since ：1.1.1
 */
public class ConcreteProduct1 implements Product {

    //具体产品1：实现抽象产品中的抽象方法

    @Override
    public void show() {
        System.out.println("具体产品1显示...");
    }
}
