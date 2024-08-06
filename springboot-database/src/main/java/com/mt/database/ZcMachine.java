package com.mt.database;

import lombok.Data;

import java.util.Date;

@Data
public class ZcMachine extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private String uuid;
    /**
     * 服务器名称
     */
    private String machineName;
    /**
     * mac地址
     */
    private String mac;
    /**
     * 外网IP
     */
    private String extranetIp;
    /**
     * 内网ip
     */
    private String intranetIp;
    /**
     * 0-离线状态，1-在线状态
     */
    private Integer onlineStatus;
    /**
     * 服务器类型，1-linux 0-windows
     */
    private Integer osType;
    /**
     * 操作系统
     */
    private String operatingSystem;
    /**
     * agent版本
     */
    private String softwareVersion;
    /**
     * 安装时间
     */
    private Date installTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 在线时间
     */
    private Date onlineTime;
    /**
     * 离线时间
     */
    private Date offlineTime;
    /**
     * 是否卸载 ,0-未卸载，1-已卸载
     */
    private Integer ifDelete;
    /**
     * 绑定uuid
     */
    private String bindUuid;
    /**
     * 隔离状态（0 未隔离，1 已隔离）
     */
    private Integer segregateStatus;
    /**
     * 机器类型
     */
    private String machineType;
    /**
     * 驱动版本
     */
    private String driverVersion;

    private String netIp;
    /**
     * 资产编号
     */
    private Integer serialNumber;
    /**
     * 所在机房
     */
    private String computerRoom;
    /**
     * 备注
     */
    private String remark;
    /**
     * 服务器别名
     */
    private String machineAlias;
    /**
     * 是否是第一次安装： 1：安装接口，2：定时上报接口
     */
    private Integer installEntrance;
    /**
     * 托管状态 1 已托管、0 未托管
     */
    private Integer trusteeshipStatus;
    /**
     * 授权状态；0-未授权、1-已授权
     */
    private Integer impowerStatus;
    /**
     * 资产标签
     */
    private String machineTags;
    /**
     * 禁ping状态；0-关闭、1-开启
     */
    private Integer forbidPing;
    /**
     * agent卸载密码
     */
    private String agentUninstallPwd;
    /**
     * 升级状态；1 未升级、2 升级中
     */
    private Integer agentUpgradeStatus;
    /**
     * 机器绑定的用户ID
     */
    private String userUuid;
}
