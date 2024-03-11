package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProtectConfBackupsMachine implements Serializable {
    private static final long serialVersionUID = 294119611055973206L;
    /**
    * 主键id
    */
    private String id;
    /**
    * 备份主表id
    */
    private String proId;
    /**
    * 机器id
    */
    private String machineUuid;
    /**
    * 备份内容
    */
    private String content;
    /**
    * 原因
    */
    private String reason;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;

}
