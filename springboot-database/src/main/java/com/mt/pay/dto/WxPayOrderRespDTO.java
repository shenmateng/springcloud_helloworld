package com.mt.pay.dto;

import lombok.Data;

/**
 * 微信支付下单响应 DTO（返回给前端用于唤起支付）
 */
@Data
public class WxPayOrderRespDTO {

    /** 应用ID */
    private String appId;

    /** 时间戳 */
    private String timeStamp;

    /** 随机字符串 */
    private String nonceStr;

    /** 订单详情扩展字符串（prepay_id） */
    private String packageStr;

    /** 签名方式 */
    private String signType;

    /** 支付签名 */
    private String paySign;

    /** 商户订单号（方便前端轮询） */
    private String outTradeNo;
}
