package com.mt.user.polymorphic;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 17:14
 * @since ：
 */
public class Son1 extends Father {

    @Override
    void save(){
        System.out.println("我是子类1");
    }
}
