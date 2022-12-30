package com.mt.bean;

import java.io.Serializable;

/**
 * 请求基类
 *
 * @author zhaolm
 * @data 2019/1/16
 */
public class QueryDomain implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
