package com.mt.database;

import lombok.Data;


@Data
public class SuspiciousLoginVO {

    private String id;
    /**
     * 可以原因
     */
    private String description;
    /**
     * 登录类型
     */
    private String typeName;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 登录状态 成功：3，失败：2
     */
    private Integer result;
    /**
     * 登录时间
     */
    private Long standardTimestamp;
    /**
     * 机器ip
     */
    private String ipv4;
    /**
     * 所属服务站
     */
    private String agencyName;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 单位
     */
    private String unit;

    /**
     * 数据可疑状态：0-忽略、1-可疑
     */
    private Integer ignoreStatus = 0;
    /**
     * 数据更新状态：0-旧数据、1-新数据
     */
    private Integer updateStatus = 0;
    /**
     * 用户id
     */
    private String userUuid;
    /**
     * 统计日期
     */
    private String date;

    /**
     * 唯一id，用日志id做标识
     */
    private String  uuid;
    /**
     * 机器id
     */
    private String machineUuid;
    /**
     * 新旧数据标识 1新数据 0 旧数据
     */
    private Integer newDataFlag;
    /**
     * 忽略的数据状态 0：没点过忽略的状态 1：忽略后变正常的数据
     */
    private Integer operateStatus = 0;
    /**
     * 忽略时间
     */
    private Long ignoreTime;
    private String operationExtra;
    private String operationDesc;
    private String operation;
    private Long localTimestamp;
    private String mainIp;
    private Machine machine;
    private IpAddress sourceIpAddress;
    private Action action;
    private Subject subject;
    /**
     * 机器ip,被攻击的ip
     */
    private String innerIp;
    private String ip;
}
