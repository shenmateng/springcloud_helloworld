package com.mt.database.es;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EsZcMachineIp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ip
     */
    private String ip;
    /**
     * 内网外网：0-内网，1-外网
     */
    private Integer type;
    /**
     * Ip版本：0-Ipv4，1-Ipv6
     */
    private Integer version;
    /**
     * 来源：0-系统上传，1-用户自定义， 2-资产收集
     */
    private Integer source;
    /**
     * 属性：0-非主ip,1-主ip
     */
    private Integer attr;

    private Long ipLong;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getAttr() {
        return attr;
    }

    public void setAttr(Integer attr) {
        this.attr = attr;
    }

    public static List<EsZcMachineIp> toMachineIpv4s(List<EsZcMachineIp> zcMachineIps){
        List<EsZcMachineIp> machineIpv4s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getVersion() == 0){
                    machineIpv4s.add(zcMachineIp);
                }
            });
        }
        return machineIpv4s;
    }

    public static List<EsZcMachineIp> toMachineIpv6s(List<EsZcMachineIp> zcMachineIps){
        List<EsZcMachineIp> machineIpv6s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getVersion() == 1){
                    machineIpv6s.add(zcMachineIp);
                }
            });
        }
        return machineIpv6s;
    }

    public static List<String> toIpv4s(List<EsZcMachineIp> zcMachineIps){
        List<String> ipv4s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getVersion() == 0){
                    ipv4s.add(zcMachineIp.getIp());
                }
            });
        }
        return ipv4s;
    }

    public static List<String> toInnerIpv4s(List<EsZcMachineIp> zcMachineIps){
        List<String> innerIpv4s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getType() == 0){
                    if(zcMachineIp.getVersion() == 0){
                        innerIpv4s.add(zcMachineIp.getIp());
                    }
                }
            });
        }
        return innerIpv4s;
    }

    public static List<String> toOuterIpv4s(List<EsZcMachineIp> zcMachineIps){
        List<String> outerIpv4s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getType() == 0){
                }else{
                    if(zcMachineIp.getVersion() == 0){
                        outerIpv4s.add(zcMachineIp.getIp());
                    }
                }
            });
        }
        return outerIpv4s;
    }

    public static List<String> toIpv6s(List<EsZcMachineIp> zcMachineIps){
        List<String> ipv6s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getVersion() == 1){
                    ipv6s.add(zcMachineIp.getIp());
                }
            });
        }
        return ipv6s;
    }

    public static List<String> toInnerIpv6s(List<EsZcMachineIp> zcMachineIps){
        List<String> innerIpv6s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getType() == 0){
                    if(zcMachineIp.getVersion() == 0){
                    }else{
                        innerIpv6s.add(zcMachineIp.getIp());
                    }
                }
            });
        }
        return innerIpv6s;
    }

    public static List<String> toOuterIpv6s(List<EsZcMachineIp> zcMachineIps){
        List<String> outerIpv6s = new ArrayList<>();
        if(!CollectionUtils.isEmpty(zcMachineIps)){
            zcMachineIps.forEach(zcMachineIp -> {
                if(zcMachineIp.getType() == 0){
                }else{
                    if(zcMachineIp.getVersion() == 0){
                    }else{
                        outerIpv6s.add(zcMachineIp.getIp());
                    }
                }
            });
        }
        return outerIpv6s;
    }

    public Long getIpLong() {
        return ipLong;
    }

    public void setIpLong(Long ipLong) {
        this.ipLong = ipLong;
    }
}
