/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.mt.ResponseResult;

import org.springframework.http.HttpStatus;

/**
 * @author ：Murphy ZhangSun
 * @version ：S1.1.2
 * @description ：ResponseVO 建造者
 * @program ：vevor-scp
 * @date ：Created in 2020/7/10 9:22
 */
public class ResponseVOBuilder<T> {
    private int code = HttpStatus.OK.value();
    private String message = HttpStatus.OK.getReasonPhrase();
    private T data;

    /**
     * 无参构造函数
     */
    public ResponseVOBuilder() {
        super();
    }

    /**
     * 有参构造函数
     *
     * @param code 响应码
     * @param message 响应消息
     */
    public ResponseVOBuilder(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResponseVOBuilder<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ResponseVOBuilder<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseVOBuilder<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 返回带状态码、描述、数据的方法
     *
     * @return 默认响应体
     */
    public ResponseResult<T> build() {
        return new ResponseResult<>(code, message, data);
    }
}