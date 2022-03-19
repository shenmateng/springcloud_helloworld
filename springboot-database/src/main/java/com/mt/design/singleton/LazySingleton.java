package com.mt.design.singleton;


/**
 * @author ：mateng
 * @version ：
 * @description ：懒汉式单例，该模式的特点是类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例。
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/8 16:04
 * @since ：
 */
public class LazySingleton {

    /*
      如果编写的是多线程程序，则不要删除代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
      如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
     */

    /**
     * 保证 instance 在所有线程中同步
     */
    private static volatile LazySingleton instance = null;


    /**
     * private 避免类在外部被实例化
     */
    private LazySingleton() {
    }

    //getInstance 方法前加同步
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
