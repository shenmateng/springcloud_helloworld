package com.mt.database;

import java.io.Serializable;

public class Login implements Serializable {
    private static final long serialVersionUID = -2912993627647821926L;
    // 发起远程联机的计算机名
    private String pcName;
    // 远程登录使用的用户名
    private String loginUser;

    public String getPcName() {
        return this.pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getLoginUser() {
        return this.loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

}
