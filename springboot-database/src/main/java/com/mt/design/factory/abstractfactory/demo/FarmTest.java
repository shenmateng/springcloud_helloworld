package com.mt.design.factory.abstractfactory.demo;

import javax.swing.*;
import java.awt.*;

/**
 * @author ：mateng
 * @version ：
 * @description ：测试抽象工厂类
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/16 10:35
 * @since ：
 */
public class FarmTest {

    public static void main(String[] args) {
        try {
            Farm f;
            Animal a;
            Plant p;
            f = (Farm) ReadXML.getObject();
            a = f.newAnimal();
            p = f.newPlant();
            a.show();
            p.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
