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

    /**
     * 一个抽象工厂里，可以有多个产品族，每个产品族可以产生不同等级的产品，这里的两个接口相当于两个产品等级，如果需要新增种类产品的话，需要对所有的工厂类进行修改
     * 这两个接口的实现类就是产品族，有几个实现类就有几个产品族，不需要修改抽象工厂，只需要增加子类来实现该接口，就能出现很多个产品族，如该代码中，有两个工厂，都可以进行生成动物和植物
     * @return 动物工厂
     */
    public Animal newAnimal();

    public Plant newPlant();
}
