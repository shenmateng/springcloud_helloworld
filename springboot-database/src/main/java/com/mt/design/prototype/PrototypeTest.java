package com.mt.design.prototype;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：原型模式测试类
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/11 15:59
 * @since ：1.1.1
 */
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = (Realizetype) obj1.clone();
        System.out.println("obj1==obj2?" + (obj1 == obj2));
        System.out.println("obj1==obj2?" + (obj1.getName().equals(obj2.getName())));
    }
}
