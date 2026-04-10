package com.mt.pay.swiftpass.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Swiftpass 下单请求 DTO
 *
 * 注意：测试商户号(7551000001)不能传 sub_appid 和 sub_openid，
 *       切换正式商户号后必须传，值分别为公众号 appId 和用户 openId。
 */
@Data
public class SwiftpassPayReqDTO {

    /** 商品描述 */
    @NotBlank(message = "商品描述不能为空")
    private String body;

    /** 商户系统内部订单号（唯一，最长32位） */
    @NotBlank(message = "商户订单号不能为空")
    private String outTradeNo;

    /** 订单金额，单位：分，最小1分，不能有小数 */
    @NotNull(message = "订单金额不能为空")
    private Integer totalFee;

    /**
     * 用户 openId（可选）
     * 测试商户号：联系 Swiftpass 获取测试 openId，或不传看是否支持
     * 正式商户号：改用下方 sub_openid
     */
    private String openId;

    /**
     * 【正式商户号专用】公众号 appId
     * 正式环境取消注释，值为微信公众号的 appId
     * 测试商户号(7551000001)不传此参数
     */
    // private String subAppId;

    /**
     * 【正式商户号专用】用户在公众号下的 openId
     * 正式环境取消注释，值通过微信网页授权获取
     * 参考：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
     * 测试商户号(7551000001)不传此参数
     */
    // private String subOpenId;

    /** 签名方式：MD5（默认）或 RSA_1_256 */
    private String signType = "RSA_1_256";

    /** 终端IP（客户端真实IP，自动从请求头获取，无需手动传） */
    private String spbillCreateIp;
}
