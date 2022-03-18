package com.mt.design.builder;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：指挥者，调用建造者中的方法完成复杂对象的创建。
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 15:01
 * @since ：1.1.1
 */
public class Director {

    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }
    //产品构建与组装方法
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
