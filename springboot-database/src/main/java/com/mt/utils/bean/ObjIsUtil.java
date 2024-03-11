package com.mt.utils.bean;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Title：判断某个对象属性值都不为空，字符串属性不为空和不为空字符串
 * Description：利用类的反射获取，前提是javabean中的属性都是包装类
 * @author WZQ
 * @version 1.0.0
 * @date 2020/4/19
 */
public class ObjIsUtil {

    /**
     * 根据属性名获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断对象中的属性值是否都不为空，字符串属性的需要判断空字符串
     */
    public static Boolean isAllNotNull(Object o) {
        //获取对象的属性数组，反射
        Field[] fields = o.getClass().getDeclaredFields();
        String name = "";
        for (int i = 0; i < fields.length; i++) {
            //属性名
            name = fields[i].getName();

            //pojos和dto含有serialVersionUID，得去掉该字段的判断
            if (name.equals("serialVersionUID"))
                continue;

            //当前属性名的值
            Object fieldValueByName = getFieldValueByName(name, o);

            //值为null
            if (fieldValueByName == null)
                return false;

            //如果是String，判断空字符串""
            if (fields[i].getType().equals(String.class)){
                String s = (String) fieldValueByName;
                if (StringUtils.isEmpty(s)){
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 判断对象中的属性值至少有一个不为空
     */
    public static Boolean oneNotNull(Object o) {
        //获取对象的属性数组，反射
        Field[] fields = o.getClass().getDeclaredFields();
        String name = "";
        for (int i = 0; i < fields.length; i++) {
            //属性名
            name = fields[i].getName();

            //当前属性名的值
            Object fieldValueByName = getFieldValueByName(name, o);

            //如果是String，判断空字符串""
            if (fields[i].getType().equals(String.class)){
                String s = (String) fieldValueByName;
                if (!StringUtils.isEmpty(s)){
                    return true;
                }
            }else{
                //值为null
                if (fieldValueByName != null)
                    return true;
            }

        }
        return false;
    }

}