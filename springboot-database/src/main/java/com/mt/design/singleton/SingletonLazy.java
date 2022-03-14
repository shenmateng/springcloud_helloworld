package com.mt.design.singleton;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：测试懒汉式单例模式
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/11 11:48
 * @since ：1.1.1
 */
public class SingletonLazy {
    public static void main(String[] args) {
        President zt1 = President.getInstance();
        zt1.getName();    //输出总统的名字
        President zt2 = President.getInstance();
        zt2.getName();    //输出总统的名字
        if (zt1 == zt2) {
            System.out.println("他们是同一人！");
        } else {
            System.out.println("他们不是同一人！");
        }
    }
}

class President {
    //保证instance在所有线程中同步
    private static volatile President instance = null;

    //private避免类在外部被实例化
    private President() {
        System.out.println("产生一个总统！");
    }

    //在getInstance方法上加同步
    public static synchronized President getInstance() {
        if (instance == null) {
            instance = new President();
        } else {
            System.out.println("已经有一个总统，不能产生新总统！");
        }
        return instance;
    }

    public void getName() {
        System.out.println("我是美国总统：特朗普。");
    }
}
