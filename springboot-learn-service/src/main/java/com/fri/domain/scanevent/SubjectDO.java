package com.fri.domain.scanevent;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectDO implements Serializable {
    private String user;
    private String process;
    private String type;


    private String procUuid;

    private String procHash;

    private String pid;

    private String bVerify;

    private String company;




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


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

}
