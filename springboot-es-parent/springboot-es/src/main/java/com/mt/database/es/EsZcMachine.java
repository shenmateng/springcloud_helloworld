package com.mt.database.es;

import com.mt.constant.Constant;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.List;

public class EsZcMachine extends EsZcBase {
    private String id;
    private String machineUuid;
    private String machineName;
    private String machineIp;
    private String ipv4;
    private String ipv6;
    private String machineTag;
    private String operationSystem;
    private String softwareVersion;
    private Integer osType;
    private Integer machineStatus;
    private Integer onlineStatus;
    private List<EsZcMachineIp> machineIps;
    private List<EsZcMachinePrimaryIp> machinePrimaryIps;
    private String customIp;
    private String remark;
    private String machineType;
    private String host;
    private String driverVersion;
    private Long riskCount;
    private String riskName;
    private String riskType;
    private Integer riskDealFlag;
    private String cpu;
    private String cpuVersion;
    private Long cpuCount;
    private String memory;
    private String memoryVersion;
    private Long memorySize;
    private Double memoryUsage;
    private Long partitionCount;
    private Long networkCardCount;
    private Long processCount;
    private Long userCount;
    private Long softwareAppCount;
    private Long webCount;
    private Long webServerCount;
    private Long webFrameCount;
    private Long webAppCount;
    private Long databaseCount;
    private Long portCount;
    private Long networkConnectionCount;
    private Long startServerCount;
    private Long installPackageCount;
    private Long planTaskCount;
    private Long envCount;
    private Long kernelModuleCount;
    private Long onlineTime;
    private Long offlineTime;
    private Long offlineDayCount;
    private String kernelVersion;
    private Integer assetsLevel;
    private String lastLoginUser;
    private String lastLoginTime;
    private String lastLoginIp;
    private Long installTime;
    private Long installationTime;
    private String directPerson;
    private String directPersonEmail;
    private String directPersonPhone;
    private String sysPerson;
    private String appPerson;
    private String machineLocation;
    private String fixedAssetsNumber;
    private String note;
    private String department;
    private String manufacturer;
    private String deviceType;
    private String product;
    private String serial;
    private String mac;
    private List<EsZcNetworkCard> zcNetworkCards;
    private List<EsZcDisk> zcDisks;
    private String disk;
    private Long diskSize;
    private Double diskUsage;
    private Long userGroupCount;
    private String arch;
    private List<UserData> userDatas;
    private List<OrgData> orgDatas;
    private Long createTime;
    private Long updateTime;
    private Long syncTime;
    private Integer newFlag;
    private Integer ifDelete;
    private String jspAgent;
    private String zcType = "zc_machine";

    public String getJspAgent() {
        return jspAgent;
    }

    public void setJspAgent(String jspAgent) {
        this.jspAgent = jspAgent;
    }

    public Long getInstallationTime() {
        return installationTime;
    }

    public void setInstallationTime(Long installationTime) {
        this.installationTime = installationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachineUuid() {
        return machineUuid;
    }

    public void setMachineUuid(String machineUuid) {
        this.machineUuid = machineUuid;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getMachineTag() {
        return machineTag;
    }

    public void setMachineTag(String machineTag) {
        this.machineTag = machineTag;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public Integer getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(Integer machineStatus) {
        this.machineStatus = machineStatus;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public List<EsZcMachineIp> getMachineIps() {
        return machineIps;
    }

    public void setMachineIps(List<EsZcMachineIp> machineIps) {
        this.machineIps = machineIps;
    }

    public List<EsZcMachinePrimaryIp> getMachinePrimaryIps() {
        return machinePrimaryIps;
    }

    public void setMachinePrimaryIps(List<EsZcMachinePrimaryIp> machinePrimaryIps) {
        this.machinePrimaryIps = machinePrimaryIps;
    }

    public String getCustomIp() {
        return customIp;
    }

    public void setCustomIp(String customIp) {
        this.customIp = customIp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    public Long getRiskCount() {
        return riskCount;
    }

    public void setRiskCount(Long riskCount) {
        this.riskCount = riskCount;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public Integer getRiskDealFlag() {
        return riskDealFlag;
    }

    public void setRiskDealFlag(Integer riskDealFlag) {
        this.riskDealFlag = riskDealFlag;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getCpuVersion() {
        return cpuVersion;
    }

    public void setCpuVersion(String cpuVersion) {
        this.cpuVersion = cpuVersion;
    }

    public Long getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Long cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getMemoryVersion() {
        return memoryVersion;
    }

    public void setMemoryVersion(String memoryVersion) {
        this.memoryVersion = memoryVersion;
    }

    public Double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public Long getPartitionCount() {
        return partitionCount;
    }

    public void setPartitionCount(Long partitionCount) {
        this.partitionCount = partitionCount;
    }

    public Long getNetworkCardCount() {
        return networkCardCount;
    }

    public void setNetworkCardCount(Long networkCardCount) {
        this.networkCardCount = networkCardCount;
    }

    public Long getProcessCount() {
        return processCount;
    }

    public void setProcessCount(Long processCount) {
        this.processCount = processCount;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Long getSoftwareAppCount() {
        return softwareAppCount;
    }

    public void setSoftwareAppCount(Long softwareAppCount) {
        this.softwareAppCount = softwareAppCount;
    }

    public Long getWebCount() {
        return webCount;
    }

    public void setWebCount(Long webCount) {
        this.webCount = webCount;
    }

    public Long getWebServerCount() {
        return webServerCount;
    }

    public void setWebServerCount(Long webServerCount) {
        this.webServerCount = webServerCount;
    }

    public Long getWebFrameCount() {
        return webFrameCount;
    }

    public void setWebFrameCount(Long webFrameCount) {
        this.webFrameCount = webFrameCount;
    }

    public Long getWebAppCount() {
        return webAppCount;
    }

    public void setWebAppCount(Long webAppCount) {
        this.webAppCount = webAppCount;
    }

    public Long getDatabaseCount() {
        return databaseCount;
    }

    public void setDatabaseCount(Long databaseCount) {
        this.databaseCount = databaseCount;
    }

    public Long getPortCount() {
        return portCount;
    }

    public void setPortCount(Long portCount) {
        this.portCount = portCount;
    }

    public Long getNetworkConnectionCount() {
        return networkConnectionCount;
    }

    public void setNetworkConnectionCount(Long networkConnectionCount) {
        this.networkConnectionCount = networkConnectionCount;
    }

    public Long getStartServerCount() {
        return startServerCount;
    }

    public void setStartServerCount(Long startServerCount) {
        this.startServerCount = startServerCount;
    }

    public Long getInstallPackageCount() {
        return installPackageCount;
    }

    public void setInstallPackageCount(Long installPackageCount) {
        this.installPackageCount = installPackageCount;
    }

    public Long getPlanTaskCount() {
        return planTaskCount;
    }

    public void setPlanTaskCount(Long planTaskCount) {
        this.planTaskCount = planTaskCount;
    }

    public Long getEnvCount() {
        return envCount;
    }

    public void setEnvCount(Long envCount) {
        this.envCount = envCount;
    }

    public Long getKernelModuleCount() {
        return kernelModuleCount;
    }

    public void setKernelModuleCount(Long kernelModuleCount) {
        this.kernelModuleCount = kernelModuleCount;
    }

    public Long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Long getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Long offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Long getOfflineDayCount() {
        return offlineDayCount;
    }

    public void setOfflineDayCount(Long offlineDayCount) {
        this.offlineDayCount = offlineDayCount;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        this.kernelVersion = kernelVersion;
    }

    public Integer getAssetsLevel() {
        return assetsLevel;
    }

    public void setAssetsLevel(Integer assetsLevel) {
        this.assetsLevel = assetsLevel;
    }

    public String getLastLoginUser() {
        return lastLoginUser;
    }

    public void setLastLoginUser(String lastLoginUser) {
        this.lastLoginUser = lastLoginUser;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Long installTime) {
        this.installTime = installTime;
    }

    public String getDirectPerson() {
        return directPerson;
    }

    public void setDirectPerson(String directPerson) {
        this.directPerson = directPerson;
    }

    public String getDirectPersonEmail() {
        return directPersonEmail;
    }

    public void setDirectPersonEmail(String directPersonEmail) {
        this.directPersonEmail = directPersonEmail;
    }

    public String getDirectPersonPhone() {
        return directPersonPhone;
    }

    public void setDirectPersonPhone(String directPersonPhone) {
        this.directPersonPhone = directPersonPhone;
    }

    public String getSysPerson() {
        return sysPerson;
    }

    public void setSysPerson(String sysPerson) {
        this.sysPerson = sysPerson;
    }

    public String getAppPerson() {
        return appPerson;
    }

    public void setAppPerson(String appPerson) {
        this.appPerson = appPerson;
    }

    public String getMachineLocation() {
        return machineLocation;
    }

    public void setMachineLocation(String machineLocation) {
        this.machineLocation = machineLocation;
    }

    public String getFixedAssetsNumber() {
        return fixedAssetsNumber;
    }

    public void setFixedAssetsNumber(String fixedAssetsNumber) {
        this.fixedAssetsNumber = fixedAssetsNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public List<EsZcNetworkCard> getZcNetworkCards() {
        return zcNetworkCards;
    }

    public void setZcNetworkCards(List<EsZcNetworkCard> zcNetworkCards) {
        this.zcNetworkCards = zcNetworkCards;
    }

    public List<EsZcDisk> getZcDisks() {
        return zcDisks;
    }

    public void setZcDisks(List<EsZcDisk> zcDisks) {
        this.zcDisks = zcDisks;
    }

    public Long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Long memorySize) {
        this.memorySize = memorySize;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public Long getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(Long diskSize) {
        this.diskSize = diskSize;
    }

    public Double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(Double diskUsage) {
        this.diskUsage = diskUsage;
    }

    public Long getUserGroupCount() {
        return userGroupCount;
    }

    public void setUserGroupCount(Long userGroupCount) {
        this.userGroupCount = userGroupCount;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public List<UserData> getUserDatas() {
        return userDatas;
    }

    public void setUserDatas(List<UserData> userDatas) {
        this.userDatas = userDatas;
    }

    public List<OrgData> getOrgDatas() {
        return orgDatas;
    }

    public void setOrgDatas(List<OrgData> orgDatas) {
        this.orgDatas = orgDatas;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Long syncTime) {
        this.syncTime = syncTime;
    }

    public Integer getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(Integer newFlag) {
        this.newFlag = newFlag;
    }


    public Integer getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Integer ifDelete) {
        this.ifDelete = ifDelete;
    }


    public String getZcType() {
        return zcType;
    }

    public void setZcType(String zcType) {
        this.zcType = zcType;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String index() {
        return "zc_machine";
    }

    @Override
    public String type() {
        return sType();
    }

    @Override
    public String alias() {
        return sAlias();
    }

    @Override
    public String mapping() {
        try {
            XContentBuilder mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                        .startObject("userDatas").field("type","nested").endObject()
                        .startObject("zcNetworkCards").field("type","nested").endObject()
                        .startObject("zcDisks").field("type","nested").endObject()
                        .startObject("installationTime").field("type","long").endObject()
                    .endObject()
                    .endObject();
            return Strings.toString(mapping);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> queryFields() {
        return List.of(Constant.MACHINE_NAME, Constant.MACHINE_IPS_IP);
    }

    @Override
    public List<String> queryKeys() {
        return null;
    }
}
