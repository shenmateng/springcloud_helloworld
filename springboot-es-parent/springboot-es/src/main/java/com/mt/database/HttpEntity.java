package com.mt.database;

import java.io.Serializable;

public class HttpEntity implements Serializable {
    private static final long serialVersionUID = -1654935934318948706L;
    //host地址
    private String host;
    private String url;
    //查询参数
    private String queryString;
    //请求方法
    private String method;
    private String userAgent;
    //请求引用来源
    private String referer;
    //cookie信息
    private String cookie;
    private String x_forwarded;
    //规则描述，Agent防护的规则描述
    private String ruleDesc;
    //代理ip
    private String proxy_ip;
    //匹配项，Agent防护匹配的规则
    private String regexMatch;
    //攻击次数，Agent防护攻击的次数
    private String reserve1;
    //日志类型 :2-畸形文件，3-文件解析漏洞，4-防盗链，5-防多线程下载，6-防应用程序漏洞，7-防溢出，8-防SQL注入，9-防XSS攻击，10-防文件下载，11-防非法请求，12-敏感词过滤，13-后台防护，15-网络通信，19-网页浏览实时防护，20-HTTP请求防护
    private String type;
    //域名
    private String domain;
    private String port;

    public HttpEntity() {
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getCookie() {
        return this.cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getX_forwarded() {
        return this.x_forwarded;
    }

    public void setX_forwarded(String x_forwarded) {
        this.x_forwarded = x_forwarded;
    }

    public String getRuleDesc() {
        return this.ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public String getProxy_ip() {
        return this.proxy_ip;
    }

    public void setProxy_ip(String proxy_ip) {
        this.proxy_ip = proxy_ip;
    }

    public String getRegexMatch() {
        return this.regexMatch;
    }

    public void setRegexMatch(String regexMatch) {
        this.regexMatch = regexMatch;
    }

    public String getReserve1() {
        return this.reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
