package com.mt.database.es;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserData implements Serializable {

    /**
     * 用户标识
     */
    private String userUuid;

    /**
     * 机器标签
     */
    private List<String> machineTags;

    /**
     * 0-应用负责人，1-系统负责人
     */
    private Integer roleFlag;
    /**
     * G01 Agent安装权限: 1有该权限，0无该权限
     */
    private Integer agentInstall;
    /**
     * G01 Agent安装权限: 1有该权限，0无该权限
     */
    private String sku;
}
