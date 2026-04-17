/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.fri.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @program ：image-manage
 * @Author : mateng
 * @Description : 实体合并
 * @Date : 2023/05/17
 * @VERSION : B1.3.0
 **/
@Slf4j
public class CopyClassUtils {

    /**
     * 实体数据合并： 将数据源与目标实体中，命名相同的属性值，合并至目标实体
     * 注：父类属性值不会合并
     * @param sourceBean 数据源实体
     * @param targetBean 目标实体
     * @return targetBean 数据的目标实体
     */
    public static <T> T copy(Object sourceBean, T targetBean) {
        if (sourceBean == null || targetBean == null) {
            return null;
        }
        Field[] sourceFields = sourceBean.getClass().getDeclaredFields();
        Field[] targetFields = targetBean.getClass().getDeclaredFields();
        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (judgeAssign(sourceField, targetField, sourceBean, targetBean)) {
                    break;
                }
            }
        }
        return targetBean;
    }

    /**
     * 判断并合并属性值
     * @param sourceField 数据源属性
     * @param targetField 目标源属性
     * @param sourceBean 数据源
     * @param targetBean 目标源
     * @return Boolean：是否合并
     */
    private static boolean judgeAssign(Field sourceField, Field targetField, Object sourceBean, Object targetBean) {
        try {
            if (sourceField.getName().equalsIgnoreCase(targetField.getName()) && sourceField.getType().getTypeName().equals(targetField.getType().getTypeName())) {
                sourceField.setAccessible(true);
                Object obj = sourceField.get(sourceBean);
                //集合类型非空判断
                if (obj instanceof Collection) {
                    Collection<?> newValue = (Collection<?>) obj;
                    if (newValue.size() <= 0) {
                        return true;
                    }
                }
                //数据类型非空判断
                if (obj != null) {
                    targetField.setAccessible(true);
                    targetField.set(targetBean, obj);
                }
                return true;
            }
        } catch (IllegalAccessException e) {
            log.error("", e);
        }
        return false;
    }
}
