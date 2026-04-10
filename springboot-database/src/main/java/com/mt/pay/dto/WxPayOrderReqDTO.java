package com.mt.pay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 微信支付下单请求 DTO
 */
@Data
public class WxPayOrderReqDTO {

    /** 商品描述 */
    @NotBlank(message = "商品描述不能为空")
    private String description;

    /** 商户系统内部订单号（唯一） */
    @NotBlank(message = "商户订单号不能为空")
    private String outTradeNo;

    /** 订单金额，单位：分 */
    @NotNull(message = "订单金额不能为空")
    private Integer total;

    /** 付款用户的 openId（企业微信成员 openId） */
    @NotBlank(message = "用户openId不能为空")
    private String openId;

    /** 附加数据（可选，原样回调） */
    private String attach;
}
