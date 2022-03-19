package com.mt.design.factory.abstractfactory.sample;


/**
 *
 */







/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：抽象工厂，提供了产品的生成方法
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/16 10:33
 * @since ：1.1.1
 */
public interface AbstractFactory {

    public Product1 newProduct1();
    public Product2 newProduct2();
}
