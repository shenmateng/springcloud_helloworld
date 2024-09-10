package com.mt.database;

import lombok.Data;

import java.io.Serializable;

/**
 * @desc 聚合后的用户信息
 * @author lihongjun
 */
@Data
public class UserSettingForAgg implements Serializable {

    /**
     * 用户唯一标识
     */
    private String userUuid;
    /*
     *  白名单设置，1-是，0-否
     */
    private Integer ifWhitelist;
    /**
     * 未拦截数
     */
    private Integer unblockedCount = 0;
    /**
     * 已拦截数
     */
    private Integer blockedCount = 0;
    /**
     * 攻击数
     */
    private Integer attackCount = 0;
    /**
     * 最近统计时间
     */
    private Long countTime;
    private String company;
    /**
     * 用户单位
     */
    private String unit;
}
