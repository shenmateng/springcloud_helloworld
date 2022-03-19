package com.mt.design.factory.abstractfactory.demo;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：生成工厂的方法
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/16 10:36
 * @since ：1.1.1
 */
public class ReadXML {
    public static Object getObject() {
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("D:\\wodedaima\\springcloud_helloworld\\springboot-database\\src\\main\\resources\\com\\mt\\FactoryMethod\\config.xml"));
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = "com.mt.design.factory.abstractfactory.demo." + classNode.getNodeValue();
            System.out.println("新类名：" + cName);
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
