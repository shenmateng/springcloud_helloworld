package com.mt.design.factory.factorymethod;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：工厂类1
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/15 13:46
 * @since ：1.1.1
 */
public class ConcreteFactory1 implements AbstractFactory {

    //具体工厂1：实现了厂品的生成方法

    @Override
    public Product newProduct() {
        System.out.println("具体工厂1生成-->具体产品1...");
        return new ConcreteProduct1();
    }
}
