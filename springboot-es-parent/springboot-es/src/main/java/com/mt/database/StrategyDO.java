package com.mt.database;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：WangYang
 * @version ：B1.5.1
 * @program ：bmp-prm
 * @date ：Created in 2021/6/17 14:02
 * @description ：shopify 商品数据
 */

@Data

public class StrategyDO implements Serializable {

    /**
     * 策略id
     */
    private String strategyId;
    /**
     * 用户ID
     */
    private String uid;
    /**
     * 机器id集合
     */
    private String machineList;

    /**
     * 离线机器id集合
     */
    private String offMachineList;
    /**
     * 防护类型
     */
    private String protectType;

}
