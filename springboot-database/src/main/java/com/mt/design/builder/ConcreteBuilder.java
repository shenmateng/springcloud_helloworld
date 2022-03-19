package com.mt.design.builder;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：具体建造者：实现了抽象建造者接口
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 14:39
 * @since ：1.1.1
 */
public class ConcreteBuilder extends Builder{
    @Override
    public void buildPartA() {
        product.setPartA("建造 PartA");
    }
    @Override
    public void buildPartB() {
        product.setPartB("建造 PartB");
    }
    @Override
    public void buildPartC() {
        product.setPartC("建造 PartC");
    }
}
