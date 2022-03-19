package com.mt.design.factory.abstractfactory.sample;


/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：产品2
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/15 13:51
 * @since ：1.1.1
 */
public class ConcreteProduct21 implements Product2 {

    //具体产品2：实现抽象产品中的抽象方法

    @Override
    public void show() {
        System.out.println("具体产品2显示...");
    }
}
