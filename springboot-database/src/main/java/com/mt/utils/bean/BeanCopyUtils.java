package com.mt.utils.bean;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/***
 * @program     ：bmp
 * @author      ：mateng
 * @date        ：Created in 2021/7/20 10:31
 * @description ：数据拷贝工具类
 * @version     ：B1.3.7
 */
public class BeanCopyUtils extends BeanUtils {

    /**
     * 集合数据的拷贝 (浅拷贝)
     *
     * @param sources: 数据源类
     * @param target:  目标类::new(eg: UserVO::new)
     * @return 转换后的集合
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 集合数据的拷贝 (深拷贝)
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: ItemDTO::new)
     * @return
     */
    public static <S, T> List<T> deepCopyListProperties(List<S> sources, Supplier<T> target) {
        return copyListPropertiesDeep(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param sources:  数据源类
     * @param target:   目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return 转换后的集合
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
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
     * @return 转换后的集合
     */
    public static <S, T> List<T> copyListPropertiesDeep(List<S> sources, Supplier<T> target, BeanCopyUtil.BeanCopyCallBack<S, T> callBack) {
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

}