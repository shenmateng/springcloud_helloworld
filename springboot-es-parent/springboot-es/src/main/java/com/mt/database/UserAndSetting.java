package com.mt.database;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaolm
 * @data 2019/4/11
 */
@Data
public class UserAndSetting implements Serializable {

    private String userUuid;
    /*
     *  白名单设置，1-是，0-否
     */
    private Integer ifWhitelist;
    /**
     * 0-未查看，1-已查看
     */
    private Integer status = 0;

    private String area;
    private String province;
    private String city;
    private String company;
    private String unit;
    private String industry;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Integer getIfWhitelist() {
        return ifWhitelist;
    }

    public void setIfWhitelist(Integer ifWhitelist) {
        this.ifWhitelist = ifWhitelist;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
