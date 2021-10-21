package com.mt.user.agency.dynamicAgency1;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ：mateng
 * @version ：
 * @description ：动态代理工厂
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 10:43
 * @since ：
 */
//工厂类，作用类似jdk工厂，创建代理类，创建代理对象
public class ProxyFactoryClass implements MethodInterceptor {
    //目标对象
    private Object obj;

    //设置关系
    public void setObj(Object obj) {
        this.obj = obj;
    }

    //类似jdk工厂中invoke方法，实现合并功能，把原来的功能和扩展的功能合并
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object o1 = method.invoke(obj);
        System.out.println("new....function");
        return o1;
    }

    //获取代理对象的方法，类似jdk工厂中getProxy()
    public Object getProxy(){
        Enhancer enhancer = new Enhancer();

        //又是在内存中帮助我们组装一个类(代理类)
        //父类 -- 目标对象所属的类是父类
        enhancer.setSuperclass(obj.getClass());
        //设置当前对象
        enhancer.setCallback(this);

        //送出代理对象
        return enhancer.create();
    }

    public static void main(String[] args) {
        //目标对象
        TargetClass tc = new TargetClass();

        //工厂对象
        ProxyFactoryClass pc = new ProxyFactoryClass();

        //设置关系
        pc.setObj(tc);

        //代理对象 -- 多态  父类引用指向子类对象
        TargetClass tci = (TargetClass) pc.getProxy();

        //完成功能 代理对象tci代替目标对象tc完成功能
        tci.save();
    }
}
