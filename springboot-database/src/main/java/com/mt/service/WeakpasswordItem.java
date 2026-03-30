package com.mt.service;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public class WeakpasswordItem implements Serializable {
    private String appType;

    private String appVer;

    private String username;

    private String cipherText;

    private String plainText;

    private Boolean isWeakPwd;

    private String machineUuid;

    public String getAppType() {
        return appType;
    }

    @JSONField(name = "app_type")
    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppVer() {
        return appVer;
    }

    @JSONField(name = "app_ver")
    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getUsername() {
        return username;
    }

    @JSONField(name = "username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getCipherText() {
        return cipherText;
    }

    @JSONField(name = "cipher_text")
    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getPlainText() {
        return plainText;
    }

    @JSONField(name = "plain_text")
    public void setPlainText(String plainText) {
        if(!StringUtils.isEmpty(plainText)) {
            this.plainText = plainText.trim();
        }
    }

    public Boolean getWeakPwd() {
        return isWeakPwd;
    }

    @JSONField(name = "is_weak_pwd")
    public void setWeakPwd(Boolean weakPwd) {
        isWeakPwd = weakPwd;
    }

    public String getMachineUuid() {
        return machineUuid;
    }

    @JSONField(name = "new_machine_id")
    public void setMachineUuid(String machineUuid) {
        this.machineUuid = machineUuid;
    }
}
