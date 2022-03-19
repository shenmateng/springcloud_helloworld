package com.mt.design.factory.factorymethod;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：工厂方法模式测试
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/14 16:43
 * @since ：1.1.1
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        try {
            Product a;
            AbstractFactory af;
            af = (AbstractFactory) ReadXML1.getObject();
            a = af.newProduct();
            a.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


