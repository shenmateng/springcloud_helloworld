package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanlongrui
 * @Description  外联情报
 * @date 2022/05/25
 */
@Data
public class OutreachIntelligence implements Serializable {

    /**
     * 域名解析或IP解析
     */
    private String type;
    private String outreachIp;
    private String outreachDomain;
    /**
     * ip归属地
     */
    private String location;

    /**
     * 运营商
     */
    private String isp;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     *ip开放端口
     */
    private List<String> openPorts;
    /**
     *网站Title
     */
    private List<String> titles;



    /**
     *组件信息
     */
    private List<String> rules;


    /**
     * ip攻击情报类型
     */
    private List<String> threatTypes;

    /**
     * 外联情报标签类型
     */
    private String malicious;
    /**
     * 外联情报威胁等级
     */
    private String riskLevel;
    /**
     * 外联情报过期时间
     */
    private String etime;
    /**
     * 来源厂商
     */
    private String company;
    /**
     * 外联情报端口
     */
    private String ports;
}