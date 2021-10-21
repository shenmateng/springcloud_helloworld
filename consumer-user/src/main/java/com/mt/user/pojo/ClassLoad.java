package com.mt.user.pojo;

import org.junit.Test;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/13 13:37
 * @since ：
 */
public class ClassLoad {

    public static final String s = "马腾";
    public static String ss = "神马腾";

    @Test
    public void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //是显示哪一个类的Class对象，当这个对象new出来后，这个Class类对象就是该对象的运行类型。
        Class<?> aClass = Class.forName("com.mt.user.pojo.User");
        Class<?> movieClass = User.class;
        User user = new User();
        Class<?> aClass3 = user.getClass();
        System.out.println("第一种"+aClass);
        System.out.println("第二种"+movieClass);
        System.out.println("第三种"+aClass3);

        //这个类对象的运行类型是java.lang.Class类型的
        Class<? extends Class> aClass1 = aClass.getClass();
        System.out.println(aClass1);
        Object o = aClass.newInstance();
        System.out.println(o);
        Class<?> aClass2 = o.getClass();

    }

    public static void main(String[] args) {

    }
}