package com.mt.design.factory.factorymethod;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

/**
 * @author ：mateng
 * @version ：1.1.1
 * @description ：工厂方法模式测试
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/14 17:02
 * @since ：1.1.1
 */
public class ReadXML1 {
    //该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getObject() {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("D:\\wodedaima\\springcloud_helloworld\\springboot-database\\src\\main\\resources\\com\\mt\\FactoryMethod\\config1.xml"));
            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = "com.mt.design.factory.factorymethod." + classNode.getNodeValue();
            System.out.println("新类名："+cName);
            //通过类名生成实例对象并将其返回
            //这里的路径需要全包名
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
