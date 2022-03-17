package com.mt.design.factory.abstractfactory.demo;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：抽象工厂
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/17 9:53
 * @since ：1.1.1
 */
public interface Farm {

    //抽象工厂：农场类
    public Animal newAnimal();
    public Plant newPlant();
}
