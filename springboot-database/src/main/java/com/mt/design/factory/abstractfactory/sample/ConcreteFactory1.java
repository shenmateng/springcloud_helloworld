package com.mt.design.factory.abstractfactory.sample;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：具体工厂
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/16 10:34
 * @since ：1.1.1
 */
public class ConcreteFactory1 implements AbstractFactory {

    @Override
    public Product1 newProduct1() {
        System.out.println("具体工厂 1 生成-->具体产品 11...");
        return new ConcreteProduct11();
    }
    @Override
    public Product2 newProduct2() {
        System.out.println("具体工厂 1 生成-->具体产品 21...");
        return new ConcreteProduct21();
    }
}
