package com.mt.design.builder.demo;



/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：具体建造者：具体装修工人2
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 16:48
 * @since ：1.1.1
 */
class ConcreteDecorator2 extends Decorator {
    @Override
    public void buildWall() {
        product.setWall("w2");
    }
    @Override
    public void buildTV() {
        product.setTV("TV2");
    }
    @Override
    public void buildSofa() {
        product.setSofa("sf2");
    }
}
