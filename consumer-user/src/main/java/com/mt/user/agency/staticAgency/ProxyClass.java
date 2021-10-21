package com.mt.user.agency.staticAgency;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2021/10/21 9:50
 * @since ：
 */

//代理类，是代理对象所属的类，此类的对象就是代理对象
//当前的代理设计，在不追加新的属性的情况下，此代理对象是无法代替其他目标对象的
//当前的代理对象只能代替一个目标对象
public class ProxyClass implements TargetClassInterface {
    //属性
    //目标类型的属性
    private TargetClass tc;

    public void setTc(TargetClass tc) {
        this.tc = tc;
    }

    @Override
    public void save() {
        //新增功能
        //真正完成功能的是目标对象
        tc.save();
        //新增功能
        System.out.println("new.......function");
    }
}
