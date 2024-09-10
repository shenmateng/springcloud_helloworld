package com.mt.database;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

public class ObjectEntity implements Serializable {

    private static final long serialVersionUID = -3896880112874113845L;


    private String ip;

    private String domain;




    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }



    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
