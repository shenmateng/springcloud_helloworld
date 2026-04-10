package com.mt.pay.alipay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 支付宝服务窗下单请求 DTO
 * 通过 Swiftpass 网关对接支付宝，service=pay.alipay.jspay
 */
@Data
public class AlipayOrderReqDTO {

    /** 商品描述 */
    @NotBlank(message = "商品描述不能为空")
    private String body;

    /** 商户系统内部订单号（唯一，最长32位） */
    @NotBlank(message = "商户订单号不能为空")
    private String outTradeNo;

    /** 订单金额，单位：分 */
    @NotNull(message = "订单金额不能为空")
    private Integer totalFee;

    /**
     * 买家支付宝登录账号（邮箱格式）
     * buyer_logon_id 和 buyer_id 必须填其中一个
     */
    private String buyerLogonId;

    /**
     * 买家支付宝用户ID
     * 一般通过支付宝网页授权获取
     * buyer_logon_id 和 buyer_id 必须填其中一个
     */
    private String buyerId;

    /** 附加信息（可选，原样回调） */
    private String attach;

    /** 签名方式：RSA_1_256（默认）或 MD5 */
    private String signType = "RSA_1_256";

    /** 终端IP（自动从请求头获取，无需手动传） */
    private String mchCreateIp;
}
