package com.mt.database;

import lombok.Data;

import java.util.Date;

@Data
public class ZcMachineUser extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private String uuid;
    /**
     * 机器uuid
     */
    private String bindUuid;
    /**
     * 机器uuid
     */
    private String machineUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
    /**
     * 单位
     */
    private String unit;
    /**
     * 行业
     */
    private String industry;
    /**
     * 所属服务站
     */
    private String systemSign;
    /**
     * 部门
     */
    private String department;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
