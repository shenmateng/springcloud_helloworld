package com.mt.design.prototype;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：原型模式测试demo
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/14 11:28
 * @since ：1.1.1
 */
public class ProtoTypeCitation {

    public static void main(String[] args) throws CloneNotSupportedException {
        citation obj1 = new citation("张三", "同学：在2016学年第一学期中表现优秀，被评为三好学生。", "榆林学院");
        obj1.display();
        citation obj2 = (citation) obj1.clone();
        obj2.setName("李四");
        obj2.display();
    }
}
//奖状类
class citation implements Cloneable {
    private String name;
    private String info;
    private String college;
    citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("奖状创建成功！");
    }
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return (this.name);
    }
    void display() {
        System.out.println(name + info + college);
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("奖状拷贝成功！");
        return (citation) super.clone();
    }
}
