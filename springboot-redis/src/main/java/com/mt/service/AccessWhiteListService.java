package com.mt.service;


import com.mt.database.AccessWhiteIpsVO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;

public interface AccessWhiteListService {


    /**
     * 防护白名单模板列表
     * @return 模板列表
     */
    AccessWhiteIpsVO list() throws ParseException;

    /**
     * 防护白名单开关控制
     * @param switchStatus 请求参数
     */
    void updateStatus(Integer switchStatus);

    /**
     * 添加编辑白名单
     * @param accessWhiteIpsVO 请求参数
     */
    void addAccessWhitelist(AccessWhiteIpsVO accessWhiteIpsVO) throws Exception;

    public static void main(String[] args) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost.getHostAddress());
        } catch (UnknownHostException e) {}

    }
}
