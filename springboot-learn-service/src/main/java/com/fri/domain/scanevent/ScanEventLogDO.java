/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.fri.domain.scanevent;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author ：mateng
 * @version ：2.1.5.2
 * @program ：learn-service
 * @date ：Created in 2023/05/17 16:58
 * @description ：事件接收日志对象
 */
@Data
public class ScanEventLogDO implements Serializable {
    /**
     * agent上报时间
     */
    private Long agentInputTime;

    /**
     * 天 ："2023-05-17"
     */
    private String day;

    /**
     * 事件id
     */
    private Integer eventId;

    /**
     * 事件类型
     */
    private Integer eventType;

    /**
     * 事件Uuid
     */
    private String eventUuid;

    /**
     * 学习小时
     */
    private Integer hours;

    /**
     * 是否是堡垒所日志0 是，1 不是
     */
    private Integer ifFort=1;

    /**
     * 堡垒锁报警日志-加标状态（0未加白，1已加白）
     */
    private Integer ifWhite=0;

    /**
     * 是否匹配到了忽略规则（0未匹配，1匹配到了）主要用户安全管理员用户
     */
    private Integer ignore=0;

    /**
     * 事件等级（低危、中危、高危）
     */
    private String levelDesc;

    /**
     * 本地时间
     */
    private Long localTimestamp;

    /**
     * 机器id
     */
    private String machineUuid;

    /**
     * 分钟
     */
    private int minute;

    /**
     * ip和域名实体
     */
    private ScanIpDO object;


    /**
     * 是否运算
     */
    private String operation;

    /**
     * 结果
     */
    private Integer result;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 服务id
     */
    private String serviceId;

    /**
     * 来源：0-系统上传，1-用户自定义， 2-资产收集
     */
    private Integer source;

    /**
     * 来源描述
     */
    private String sourceDesc;

    /**
     * 上报时间戳
     */
    private Long standardTimestamp;

    /**
     * 状态
     */
    private String status;

    /**
     * sku创建时间
     */
    private SubjectDO subject;

    /**
     * 数路径
     */
    private String treePath;

    /**
     * ucrc
     */
    private Long ucrc;

    /**
     * 扫描时间
     */
    private Long scanTime;

    /**
     * 规定时间内扫描次数
     */
    private Integer scanNumber;

    /**
     * 推送时间
     */
    private Long pushTime;

    /**
     * 扫描ip和扫描时间
     */
    private Set<ScanIpVO> scanIpVO;



}