package com.mt.user.agency.staticAgency;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 10:14
 * @since ：
 */
public class HaveProxyTest {
    public static void main(String[] args) {
        //需要先有目标对象 -- 房东
        TargetClass tc = new TargetClass();

        //需要代理对象 -- 中介
        ProxyClass pc = new ProxyClass();

        //目标对象找代理对象 -- 房东找中介
        pc.setTc(tc);

        //代理完成功能 -- 中介卖房子
        pc.save();
    }

    /*
    A、一般是一个代理对象只能代替一个目标对象
    B、如果一个代理对象要代替多个目标对象时，在代理类中需要设置很多的属性(通用性不好)
     */
}
