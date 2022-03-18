package com.mt.design.builder.demo;


/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：测试启动类
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 16:48
 * @since ：1.1.1
 */
public class ParlourDecorator {

    public static void main(String[] args) {
        try {
            Decorator d;
            d = (Decorator) ReadXML.getObject();
            ProjectManager m = new ProjectManager(d);
            Parlour p = m.decorate();
            p.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
