package com.mt.design.builder.demo;


/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：指挥者：项目经理
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/18 16:48
 * @since ：1.1.1
 */
class ProjectManager {
    private Decorator builder;
    public ProjectManager(Decorator builder) {
        this.builder = builder;
    }
    //产品构建与组装方法
    public Parlour decorate() {
        builder.buildWall();
        builder.buildTV();
        builder.buildSofa();
        return builder.getResult();
    }
}