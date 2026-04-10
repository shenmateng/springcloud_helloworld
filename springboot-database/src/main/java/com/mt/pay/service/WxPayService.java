package com.mt.pay.service;

import com.mt.pay.dto.WxPayOrderReqDTO;
import com.mt.pay.dto.WxPayOrderRespDTO;
import com.mt.pay.dto.WxRefundReqDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信企业号支付 Service 接口
 */
public interface WxPayService {

    /**
     * JSAPI 下单（企业微信内支付）
     *
     * @param req 下单请求参数
     * @return 前端唤起支付所需参数
     */
    WxPayOrderRespDTO createOrder(WxPayOrderReqDTO req);

    /**
     * 查询订单
     *
     * @param outTradeNo 商户订单号
     * @return 微信返回的订单信息
     */
    Map<String, Object> queryOrder(String outTradeNo);

    /**
     * 关闭订单
     *
     * @param outTradeNo 商户订单号
     */
    void closeOrder(String outTradeNo);

    /**
     * 申请退款
     *
     * @param req 退款请求参数
     * @return 微信返回的退款信息
     */
    Map<String, Object> refund(WxRefundReqDTO req);

    /**
     * 查询退款
     *
     * @param outRefundNo 商户退款单号
     * @return 微信返回的退款信息
     */
    Map<String, Object> queryRefund(String outRefundNo);

    /**
     * 处理支付回调通知
     *
     * @param request HTTP 请求（用于读取请求头签名信息）
     * @param body    请求体原始字符串
     * @return 解密后的支付结果 Map
     */
    Map<String, Object> handlePayNotify(HttpServletRequest request, String body);

    /**
     * 处理退款回调通知
     *
     * @param request HTTP 请求
     * @param body    请求体原始字符串
     * @return 解密后的退款结果 Map
     */
    Map<String, Object> handleRefundNotify(HttpServletRequest request, String body);
}
