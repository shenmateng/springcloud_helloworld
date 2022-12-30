package com.mt.exception;

/**
 * @author 段杨宇
 * @create 2019-05-21 17:08
 **/
public class ErrorDomain {
    private String code;
    private String msg;
    private Object data;
    private Exception exception;

    public ErrorDomain(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
