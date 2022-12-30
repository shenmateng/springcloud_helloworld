package com.mt.exception;

import com.alibaba.fastjson.JSON;
import com.mt.bean.BaseDomain;
import com.mt.bean.ResponseForPage;

import java.util.Collection;
import java.util.Map;

/**
 * @author zhaolm
 * @data 2019/10/12
 */
public class JowtoRuntimeException extends RuntimeException {
    private JowtoExceptionResponse jowtoExceptionResponse;

    private String code;

    private String msg;

    private Object data;

    public JowtoRuntimeException(JowtoExceptionResponse jowtoExceptionResponse) {
        super(jowtoExceptionResponse.getMsg());
        this.code = jowtoExceptionResponse.getCode();
        this.msg = jowtoExceptionResponse.getMsg();
    }

    public JowtoRuntimeException(Throwable cause, JowtoExceptionResponse jowtoExceptionResponse) {
        super(jowtoExceptionResponse.getMsg(), cause);
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

    public void setData(Map<Object, Object> data) {
        this.data = data;
    }

    public void setData(Collection data) {
        this.data = data;
    }

    public void setData(JSON data) {
        this.data = data;
    }

    public void setData(BaseDomain data) {
        this.data = data;
    }

    public void setData(ResponseForPage data) {
        this.data = data;
    }
}
