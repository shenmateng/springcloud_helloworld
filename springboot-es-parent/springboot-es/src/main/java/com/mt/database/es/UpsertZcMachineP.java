package com.mt.database.es;

import java.io.Serializable;
import java.util.Date;


public class UpsertZcMachineP implements Serializable {

    private String uuid;
    /**
     * 服务器名称
     */
    private String machineName;
    /**
     * mac
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
     * 安装时间(只记录第一次安装的时间点)
     */
    private Date installTime;
    /**
     * 安装时间(记录第一次安装和每次卸载重装的时间点)
     */
    private Date installationTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否卸载 ,0-未卸载，1-已卸载
     */
    private Integer ifDelete;
    /**
     * 绑定uuid
     */
    private String bindUuid;

    private String machineType;

    private String driverVersion;

    private String netIp;
    /**
     * 这个是用来标记他是通过安装进来的，还是通过/token接口进来的
     * 1 /server_register_machine 2 /token
     */
    private Integer installEntrance;

    public Integer getInstallEntrance() {
        return installEntrance;
    }

    public void setInstallEntrance(Integer installEntrance) {
        this.installEntrance = installEntrance;
    }

    public Date getInstallationTime() {
        return installationTime;
    }

    public void setInstallationTime(Date installationTime) {
        this.installationTime = installationTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getExtranetIp() {
        return extranetIp;
    }

    public void setExtranetIp(String extranetIp) {
        this.extranetIp = extranetIp;
    }

    public String getIntranetIp() {
        return intranetIp;
    }

    public void setIntranetIp(String intranetIp) {
        this.intranetIp = intranetIp;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }

    public String getBindUuid() {
        return bindUuid;
    }

    public void setBindUuid(String bindUuid) {
        this.bindUuid = bindUuid;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    public String getNetIp() {
        return netIp;
    }

    public void setNetIp(String netIp) {
        this.netIp = netIp;
    }
}
