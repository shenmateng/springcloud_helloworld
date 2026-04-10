package com.mt.pay.swiftpass.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swiftpass 聚合支付配置
 * 对应 Consul / yml 中 swiftpass.* 配置项
 */
@Data
@Component
@ConfigurationProperties(prefix = "swiftpass")
public class SwiftpassConfig {

    /** 商户号（开户邮件中获取） */
    private String mchId;

    /** MD5 签名密钥（开户邮件中获取） */
    private String key;

    /** 支付网关请求地址（联系技术支持确认） */
    private String reqUrl;

    /** 支付成功异步回调地址（改为自己服务器的外网地址） */
    private String notifyUrl;

    /** RSA 签名私钥（Base64，商户本地生成） */
    private String mchPrivateKey;

    /** RSA 验签平台公钥（Base64，登录商户后台查看） */
    private String platPublicKey;
}
