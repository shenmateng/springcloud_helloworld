package com.mt.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信企业号支付配置
 * 对应 bootstrap.yml 中 wx.pay.* 配置项
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayConfig {

    /** 企业微信 corpId */
    private String corpId;

    /** 微信支付商户号 */
    private String mchId;

    /** APIv3 密钥（32位） */
    private String apiV3Key;

    /** 商户API证书序列号 */
    private String certSerialNo;

    /** 商户私钥文件路径（classpath 相对路径） */
    private String privateKeyPath;

    /** 应用 appId（企业微信子应用 agentId 对应的 appId，或公众号 appId） */
    private String appId;

    /** 支付成功回调地址 */
    private String notifyUrl;

    /** 微信支付 JSAPI 下单地址 */
    private String jsapiUrl = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";

    /** 微信支付查询订单地址 */
    private String queryOrderUrl = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/";

    /** 微信支付关闭订单地址 */
    private String closeOrderUrl = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/%s/close";

    /** 申请退款地址 */
    private String refundUrl = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";

    /** 查询退款地址 */
    private String queryRefundUrl = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/";
}
