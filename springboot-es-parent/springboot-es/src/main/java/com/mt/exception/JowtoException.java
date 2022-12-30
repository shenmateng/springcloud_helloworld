package com.mt.exception;

import com.alibaba.fastjson.JSON;
import com.mt.bean.BaseDomain;
import com.mt.bean.ResponseForPage;


import java.util.List;
import java.util.Map;

/**
 * @author zhaolm
 * @data 2019/1/10
 */
public class JowtoException extends Exception {
    private JowtoExceptionResponse jowtoExceptionResponse;

    private String code;

    private String msg;

    private Object data;

    public JowtoException(JowtoExceptionResponse jowtoExceptionResponse) {
        super(jowtoExceptionResponse.getMsg());
        this.jowtoExceptionResponse = jowtoExceptionResponse;
        this.code = jowtoExceptionResponse.getCode();
        this.msg = jowtoExceptionResponse.getMsg();
    }

    public JowtoException(Throwable cause, JowtoExceptionResponse jowtoExceptionResponse) {
        super(jowtoExceptionResponse.getMsg(), cause);
        this.jowtoExceptionResponse = jowtoExceptionResponse;
        this.code = jowtoExceptionResponse.getCode();
        this.msg = jowtoExceptionResponse.getMsg();
    }

    public JowtoExceptionResponse getJowtoExceptionResponse() {
        return jowtoExceptionResponse;
    }

    public void setJowtoExceptionResponse(JowtoExceptionResponse jowtoExceptionResponse) {
        this.jowtoExceptionResponse = jowtoExceptionResponse;
        this.code = jowtoExceptionResponse.getCode();
        this.msg = jowtoExceptionResponse.getMsg();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public JowtoException setData(Map<Object, Object> data) {
        this.data = data;
        return this;
    }

    public JowtoException setData(List<Object> data) {
        this.data = data;
        return this;
    }

    public JowtoException setData(JSON data) {
        this.data = data;
        return this;
    }

    public JowtoException setData(BaseDomain data) {
        this.data = data;
        return this;
    }

    public JowtoException setData(ResponseForPage data) {
        this.data = data;
        return this;
    }
}
