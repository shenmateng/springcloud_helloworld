package com.mt.database;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询防护开关状态，请求参数
 * @author zhouchangsong
 */
@Data
public class MachinesSwitchGetRequest implements Serializable {

    private static final long serialVersionUID = -5453942537905147943L;
    /**
     * ipv4地址
     */
    private String ipv4;
    /**
     * ipv6地址
     */
    private String ipv6;
    /**
     * mac地址
     */
    private String mac;
    /**
     * 机器id
     */
    private String machineUuid;
    /**
     * 机器名称
     */
    private String machineName;
    /**
     * 中心id
     */
    private String centerId;
}
