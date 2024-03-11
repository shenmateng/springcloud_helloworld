package com.mt.utils.bean;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class BeanUtils {
        /**
         * @param orig  源对象
         * @param dest  目标对象
         */
        public static void copyProperties(final Object orig,final Object dest){
            try{
                org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }

        /**
         * @Description：拷贝list元素对象，将origs中的元素信息，拷贝覆盖至dests中
         * @param origs 源list对象
         * @param dests 目标list对象
         * @param origsElementTpe 源list元素类型对象
         * @param destElementTpe 目标list元素类型对象
         * @param <T1> 源list元素类型
         * @param <T2> 目标list元素类型
         */
        public static <T1,T2> void copyProperties(final List<T1> origs, final List<T2> dests, Class<T1> origsElementTpe, Class<T2> destElementTpe){
            if(origs==null||dests==null){
                return ;
            }
            if(dests.size()!=0){
                //防止目标对象被覆盖，要求必须长度为零
                throw new RuntimeException("目标对象存在值");
            }
            try{
                for (T1 orig: origs) {
                    T2 t = destElementTpe.newInstance();
                    dests.add(t);
                    copyProperties(orig,t);
                }
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
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
    }
