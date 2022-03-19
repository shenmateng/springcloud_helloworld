package com.mt.design.factory.factorymethod;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：工厂类2
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/15 13:47
 * @since ：1.1.1
 */
public class ConcreteFactory2 implements AbstractFactory{

    //具体工厂2：实现了厂品的生成方法

    @Override
    public Product newProduct() {
        System.out.println("具体工厂2生成-->具体产品2...");
        return new ConcreteProduct2();
    }
}
