package com.mt.user.agency.dynamicAgency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 10:23
 * @since ：
 */
//通过此类获取代理对象，类生成代理对象类，不仅仅生成代理对象，还生成代理对象所属的类
public class ProxyFactoryClass implements InvocationHandler {

    //设置目标类属性---通用
    private Object obj;

    //属性设置的set方法
    public void setObj(Object obj) {
        this.obj = obj;
    }

    //类似静态代理中的整合的方法，即真正实现功能以及增加其他功能的合并位置
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*
         * Class clazz = Class.forName("com.offcn.jdk.TargetJDKClass");
         * TargetJDKClass tc = clazz.newInsance();
         * Method method = clazz.getMethod("save");
         * method.invoke(tc);
         * */
        //类似tc.save()方法调用的实现，反射的方式实现方法调用method
        Object o = method.invoke(obj);//反射调用方法时可能有返回值可能没有返回值
        String name = method.getName();
        System.out.println(name);
        //此功能给save添加
        System.out.println("new.......function");
        return o;
    }

    //获取代理对象
    public Object getProxy(){
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        //目标对象
        TargetJDKClass tc = new TargetJDKClass();
        //目标自己完成
        //tc.save();

        //工厂对象
        ProxyFactoryClass pc = new ProxyFactoryClass();

        //设置关系
        pc.setObj(tc);

        //获取代理对象 -- 接口多态
        TargetJDKClassInterface1 tci = (TargetJDKClassInterface1) pc.getProxy();
        Object proxy = pc.getProxy();
        //代替完成功能
        tci.savess();
    }
}
