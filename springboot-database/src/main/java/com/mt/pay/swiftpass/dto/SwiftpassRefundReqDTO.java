package com.mt.pay.swiftpass.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Swiftpass 退款请求 DTO
 */
@Data
public class SwiftpassRefundReqDTO {

    /** 原商户订单号 */
    @NotBlank(message = "商户订单号不能为空")
    private String outTradeNo;

    /** 商户退款单号（唯一） */
    @NotBlank(message = "退款单号不能为空")
    private String outRefundNo;

    /** 原订单金额，单位：分 */
    @NotNull(message = "原订单金额不能为空")
    private Integer totalFee;

    /** 退款金额，单位：分 */
    @NotNull(message = "退款金额不能为空")
    private Integer refundFee;

    /** 退款原因（可选） */
    private String refundDesc;

    /** 签名方式 */
    private String signType = "MD5";
}
