package com.mt.database;

import java.io.Serializable;

public class Subject implements Serializable {
    private static final long serialVersionUID = 5767246628003805707L;
    // 进程执行体用户名
    private String user;
    // 应用服务名称
    private String webServerName;
    // 应用服务类型
    private String webServerType;
    // 应用服务版本号
    private String webServerVersion;
    // 进程执行体全路径
    private String process;
    // 端口号
    private String netPort;
    // 安装路径
    private String installPath;
    // 页文件的物理路径，此项仅在Type是Web类型是才有意义
    private String webPagePhysicalPath;
    // 类型
    private String type;
    // 文件名
    private String file;
    // 问题文件的哈希值，存储在服务器的唯一标识
    private String fileHash;

    private String procUuid;

    private String procHash;

    private String pid;

    private String bVerify;

    private String company;
    // 参数
    private String argument;


    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProcess() {
        return this.process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getWebPagePhysicalPath() {
        return this.webPagePhysicalPath;
    }

    public void setWebPagePhysicalPath(String webPagePhysicalPath) {
        this.webPagePhysicalPath = webPagePhysicalPath;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProcUuid() {
        return procUuid;
    }

    public void setProcUuid(String procUuid) {
        this.procUuid = procUuid;
    }

    public String getProcHash() {
        return procHash;
    }

    public void setProcHash(String procHash) {
        this.procHash = procHash;
    }

    public String getbVerify() {
        return bVerify;
    }

    public void setbVerify(String bVerify) {
        this.bVerify = bVerify;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getNetPort() {
        return netPort;
    }

    public void setNetPort(String netPort) {
        this.netPort = netPort;
    }

    public String getWebServerName() {
        return webServerName;
    }

    public void setWebServerName(String webServerName) {
        this.webServerName = webServerName;
    }

    public String getWebServerType() {
        return webServerType;
    }

    public void setWebServerType(String webServerType) {
        this.webServerType = webServerType;
    }

    public String getWebServerVersion() {
        return webServerVersion;
    }

    public void setWebServerVersion(String webServerVersion) {
        this.webServerVersion = webServerVersion;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }
}
