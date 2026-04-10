package com.mt.pay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 微信退款请求 DTO
 */
@Data
public class WxRefundReqDTO {

    /** 原商户订单号 */
    @NotBlank(message = "商户订单号不能为空")
    private String outTradeNo;

    /** 商户退款单号（唯一） */
    @NotBlank(message = "退款单号不能为空")
    private String outRefundNo;

    /** 退款原因 */
    private String reason;

    /** 原订单金额，单位：分 */
    @NotNull(message = "原订单金额不能为空")
    private Integer total;

    /** 退款金额，单位：分 */
    @NotNull(message = "退款金额不能为空")
    private Integer refund;
}
