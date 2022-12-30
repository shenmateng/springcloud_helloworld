package com.mt.database;

import java.io.Serializable;

/**
 * @author zhaolm
 * @data 2019/1/24
 */
public class BaseQuery implements Serializable {

    private String token;

    private String userUuid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
