package com.mt.design.prototype;

import lombok.Data;

/**
 * @author ：mateng
 * @version ：
 * @description ：原形模式，具体原型类
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/11 15:53
 * @since ：
 */
@Data
public class Realizetype implements Cloneable {

    private String name;

    Realizetype() {
        System.out.println("具体原型创建成功！");
        name = "11221";
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return (Realizetype) super.clone();
    }
}
