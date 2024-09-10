package com.mt.database;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OutreachInfo implements Serializable {


    private String uuid;

    /**
     * 机器id
     */
    private String machineUuid;

    /**
     * 当前服务器ip
     */
    private String innerIp;
    private String ipAndDomain;

    /**
     * 内网IP，整数型值
     */
    private String innerIpAddress;
    private IpAddress targetIpAddress;

    /**
     * 外联ip
     */
    private String outreachIp;
    private String outreachPort;
    /**
     * 外联域名
     */
    private String outreachDomain;
    private List<String> outreachMachine;
    private List<String> ports;
    private String outreachAddress;
    private Integer outreachCount;
    private IpAddress outreachIpAddress;

    private String eventUuid;
    /**
     * 服务器本地时间，时间戳 1970.1.1到现在的秒数
     */
    private Long localTimestamp;
    /**
     * 标准时间
     */
    private Long standardTimestamp;

    /**
     * 入agent时间
     */
    private String agentInputTime;
    /**
     * 入事件时间
     */
    private String eventInputTime;
    /**
     * 入ES时间
     */
    private String saveEsTime;

    private Long countTime;

    /**
     * 拦截状态 0-已拦截，1-未拦截
     */
    private Integer result;
    private String serviceId;
    /**
     * 登录状态
     */
    private Login login;
    /**
     * IOC中网络流量记录的数据，由多个字段拼接成一个Json，包含了访问的流量、
     * 攻击的次数、流量的类型等。记录的Log中不一定所有字段的值都包含，所以部分字段为空时则表示此Log中不包含该字段
     */
    private HttpEntity http;

    /**
     * IOC中应用展示
     */
    private Subject subject;

    /**
     * 执行操作
     */
    private String operation;
    /**
     * 执行操作的扩展信息
     */
    private String operationExtra;
    private String operationDesc;

    /**
     * 外连目的IP/域名
     */
    private List<String> outreachIpAnDomain;
    private List<String> maliciousList;


    private String treePath;


    /**
     * 日志所属分组
     */
    private String groupName;

    /**
     * 日志描述
     */
    private String description;

    /**
     * 风险处理意见，对于该类风险的处理建议
     */
    private String dealSuggestion;

    private Integer categoryUuid;

    private String categoryName;

    /**
     * 日志类型
     */
    private String typeName;


    /**
     * IOC中操作对象
     */
    private ObjectEntity object;

    /**
     * agent上传desc详细信息字段
     */
    private String desc;

    /**
     * 保留userAndSetting  用户侧区分用户权限
     */
    private UserSettingForAgg userAndSettings;

    private String day;

    private int hour;

    private int minute;
    /**
     * 数据标记 zeroDay：零日漏洞 malicious：恶意外联
     */
    private String markName;


    /**
     * 外联情报关联
     */
    private OutreachIntelligence outreachIntelligence;



}
