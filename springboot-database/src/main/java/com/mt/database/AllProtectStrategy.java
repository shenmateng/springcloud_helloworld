package com.mt.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllProtectStrategy extends BaseDomain {
    public static final String STRATEGY_NAME = "name";
    public static final String STRATEGY_REMARK = "remark";
    public static final String STRATEGY_MACHINE = "tag";
    private String id;
    private String userUuid;
    private String name;
    private String remark;
    private Integer source;
    private Integer type;
    private Integer mode;
    private Date createTime;
    private Date updateTime;
    private Date lastExeTime;
    private Integer isAllMachine;
    private Integer successSize;
    private Integer failSize;
    private String failMachineUuids;
    private Integer strategyStatus;
    private Integer osType;
    private String taskUuid;
    private String userName;
    private String templateId;
}
