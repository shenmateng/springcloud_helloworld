package com.mt.design.builder.demo;




/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：抽象建造者：装修工人
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 16:48
 * @since ：1.1.1
 */
abstract class Decorator {
    //创建产品对象
    protected Parlour product = new Parlour();
    public abstract void buildWall();
    public abstract void buildTV();
    public abstract void buildSofa();
    //返回产品对象
    public Parlour getResult() {
        return product;
    }
}