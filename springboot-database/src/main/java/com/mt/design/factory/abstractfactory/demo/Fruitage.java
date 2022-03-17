package com.mt.design.factory.abstractfactory.demo;

import javax.swing.*;
import java.awt.*;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：具体产品：水果类
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/17 9:52
 * @since ：1.1.1
 */
public class Fruitage implements Plant {
    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");
    public Fruitage() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("植物：水果"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("src/P_Fruitage.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户点击窗口关闭
    }
    @Override
    public void show() {
        jf.setVisible(true);
    }
}
