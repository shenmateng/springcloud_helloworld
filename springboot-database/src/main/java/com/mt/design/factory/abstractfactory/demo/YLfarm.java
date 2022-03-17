package com.mt.design.factory.abstractfactory.demo;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：具体工厂：榆林农场类
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/17 9:55
 * @since ：1.1.1
 */
public class YLfarm implements Farm{
    @Override
    public Animal newAnimal() {
        System.out.println("新牛出生！");
        return new Cattle();
    }
    @Override
    public Plant newPlant() {
        System.out.println("蔬菜长成！");
        return new Vegetables();
    }
}
