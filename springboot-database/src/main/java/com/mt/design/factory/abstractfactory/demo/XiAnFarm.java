package com.mt.design.factory.abstractfactory.demo;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：西安农场
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/17 9:57
 * @since ：1.1.1
 */
public class XiAnFarm implements Farm {

    //具体工厂：西安农场类
    @Override
    public Animal newAnimal() {
        System.out.println("新马出生！");
        return new Horse();
    }
    @Override
    public Plant newPlant() {
        System.out.println("水果长成！");
        return new Fruitage();
    }
}
