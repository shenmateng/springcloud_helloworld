package com.mt.bean;

import com.alibaba.fastjson.JSON;
import com.mt.bean.page.I18nUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 响应实体
 *
 * @author zhaolm
 * @data 2019/1/7
 */
public class Result implements Serializable {

    private String code = "1";

    private String msg = I18nUtil.get("OK");

    private Object data;

    public Result() {
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public Object getData() {
        return data;
    }

    public Result setData(Map data) {
        this.data = data;
        return this;
    }

    public Result setData(Collection data) {
        this.data = data;
        return this;
    }

    public Result setData(JSON data) {
        this.data = data;
        return this;
    }

    public Result setData(BaseDomain data) {
        this.data = data;
        return this;
    }

    public Result setData(ResponseForPage data) {
        this.data = data;
        return this;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }


}
