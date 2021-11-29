package com.mt.pojo;

import lombok.Data;

/**
 * @author ：mateng
 * @version ：
 * @description ：用户类
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/11/29 13:27
 * @since ：
 */
@Data
public class User {

    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }
}
