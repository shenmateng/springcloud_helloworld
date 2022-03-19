package com.mt.design.factory.abstractfactory.sample;


/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：产品一
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/15 13:50
 * @since ：1.1.1
 */
public class ConcreteProduct11 implements Product1 {

    //具体产品1：实现抽象产品中的抽象方法

    @Override
    public void show() {
        System.out.println("具体产品1显示...");
    }
}
