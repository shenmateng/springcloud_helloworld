package com.mt.utils.bean;

/***
 * @program     ：bmp
 * @author      ：chenglining
 * @date        ：Created in 2021/7/20 10:32
 * @description ：copy回调
 * @version     ：B1.3.7
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack <S, T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
