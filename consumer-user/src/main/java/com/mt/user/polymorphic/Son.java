package com.mt.user.polymorphic;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 16:19
 * @since ：
 */
public class Son extends Father implements FatherInterface{



    @Override
    void save(){
        System.out.println("我是子类方法");
    }
}
