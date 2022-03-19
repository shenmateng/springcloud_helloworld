package com.mt.design.builder;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：抽象建造者：包含创建产品各个子部件的抽象方法。
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 14:38
 * @since ：1.1.1
 */
abstract class Builder {

    //创建产品对象
    protected Product product = new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();
    //返回产品对象
    public Product getResult() {
        return product;
    }
}
