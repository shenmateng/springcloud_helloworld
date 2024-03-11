package com.mt.utils;



import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * bean拷贝工具类
 * @author 周长松
 */
public class BeanUtils {
    /**
     * 若bean中有Date类型的属性，且可能为null时，可能会抛出一个异常：org.apache.commons.beanutils.ConversionException: No value specified for 'Date'，
     *ConvertUtils.register(new DateConverter(null), java.util.Date.class);//添加这一行代码，重新注册一个转换器
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T transfer(Object text, Class<T> clazz) {
        if (text == null) {
            return null;
        }
        T t = null;
        //若为null的话，会自作多情的给默认值
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        ConvertUtils.register(new DateConverter(null), Date.class);
        ConvertUtils.register(new DateConverter(null), Date.class);
        try {
            t = clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.copyProperties(t,text);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }




}
