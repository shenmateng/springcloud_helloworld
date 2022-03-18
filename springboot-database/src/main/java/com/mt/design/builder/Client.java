package com.mt.design.builder;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：客户类
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 15:22
 * @since ：1.1.1
 */
public class Client {

    /**
     *目的：我们需要创建出来Product这个对象，理解建造者模型的思想，我们这个对象里有许多属性，要如何给这些属性赋值
     * 第二步：使用多态创建出来具体建造者：实现了抽象建造者接口，在具体建造者里进行属性的赋值，这里只是在方法里面进行了赋值，并没有去调用，所以我们还需要一个指挥者。
     * 第三步：创建出来指挥者的对象，指挥者传入建造者对象，调用建造者的方法进行属性的赋值，并且返回目标对象。
     */


    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }
}
