package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EventWhitelistAutoAdd implements Serializable {
    private static final long serialVersionUID = -78967376396400250L;
    /**
    * 主键id
    */
    private Integer id;
    /**
    * event_whitelist主键
    */
    private String eventWhitelistUuid;
    /**
    * 用户uuid
    */
    private String userUuid;
    /**
    * 分组标签,用,隔开
    */
    private String machineTag;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 创建时间
    */
    private Date createTime;

}
