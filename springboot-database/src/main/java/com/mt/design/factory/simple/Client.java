package com.mt.design.factory.simple;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：简单工厂模式
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/14 13:59
 * @since ：1.1.1
 */
public class Client {

    public static void main(String[] args) {
        Product product = SimpleFactory.makeProduct(0);
        product.show();

    }
    //抽象产品
    public interface Product {
        void show();
    }
    //具体产品：ProductA
    static class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品1显示...");
        }
    }
    //具体产品：ProductB
    static class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品2显示...");
        }
    }
    final class Const {
        static final int PRODUCT_A = 0;
        static final int PRODUCT_B = 1;
        static final int PRODUCT_C = 2;
    }

    //工厂，根据传入参数不同，创建不同对象出来
    static class SimpleFactory {
        public static Product makeProduct(int kind) {
            switch (kind) {
                case Const.PRODUCT_A:
                    return new ConcreteProduct1();
                case Const.PRODUCT_B:
                    return new ConcreteProduct2();
            }
            return null;
        }
    }
}
