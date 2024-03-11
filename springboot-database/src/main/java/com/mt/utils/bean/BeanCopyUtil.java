package com.mt.utils.bean;
 
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
 
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
 
public class BeanCopyUtil extends BeanUtils {
 
    /**
     * 注意复制的对象要有get and set
     * FastJson实现带泛型参数的对象拷贝（深复制）
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> T copyPropertiesTypeReference(S source, TypeReference<T> target){
        String jsonString = JSON.toJSONString(source);
        T t = JSON.parseObject(jsonString, target);
        return  t;
    }
 
    /**
     * Gson实现带泛型参数的对象拷贝（深复制）
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S,T> T copyPropertiesTypeToken(S source, TypeToken<T> target){
        Gson gson = new Gson();
        String jsonString = gson.toJson(source);
        T t = gson.fromJson(jsonString, target.getType());
        return  t;
    }
 
    public static <T> T copyPropertiesTypeToken(String jsonString, TypeToken<T> target){
        Gson gson = new Gson();
        T t = gson.fromJson(jsonString, target.getType());
        return  t;
    }
 
    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: ItemDTO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListPropertiesDeep(sources, target, null);
    }
 
 
    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）(浅拷贝)
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: ItemDTO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
 
    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）(深拷贝)
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: ItemDTO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListPropertiesDeep(List<S> sources, Supplier<T> target, BeanCopyCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            BeanUtil.copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
 
    @FunctionalInterface
    public interface BeanCopyCallBack <S, T> {
 
        /**
         * 定义默认回调方法
         * @param t
         * @param s
         */
        void callBack(S t, T s);
    }
 
}