package com.mt.user.polymorphic;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 16:20
 * @since ：
 */
public class Test {

    public static void main(String[] args) {

        cat(new Son());
        cat(new Son1());
        cat(new Father());

    }

    static void cat(Father father){
        father.save();
    }
}
