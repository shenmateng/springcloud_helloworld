package com.mt.design.factory.factorymethod;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：抽象工厂，//抽象工厂：提供了厂品的生成方法
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/14 17:22
 * @since ：1.1.1
 */
public interface AbstractFactory {

     Product newProduct();

}
