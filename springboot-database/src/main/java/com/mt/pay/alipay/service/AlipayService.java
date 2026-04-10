package com.mt.pay.alipay.service;

import com.mt.pay.alipay.dto.AlipayOrderReqDTO;
import com.mt.pay.alipay.dto.AlipayRefundReqDTO;

import java.util.Map;

/**
 * 支付宝服务窗支付 Service 接口
 * 通过 Swiftpass 聚合支付网关对接
 */
public interface AlipayService {

    /** 下单，返回 pay_info（含 tradeNO，前端用于唤起支付宝收银台） */
    Map<String, String> pay(AlipayOrderReqDTO req);

    /** 查询订单 */
    Map<String, String> queryOrder(String outTradeNo, String signType);

    /** 关闭订单 */
    Map<String, String> closeOrder(String outTradeNo, String signType);

    /** 申请退款 */
    Map<String, String> refund(AlipayRefundReqDTO req);

    /** 查询退款 */
    Map<String, String> queryRefund(String outTradeNo, String outRefundNo, String signType);

    /** 处理支付回调通知，验签后返回通知参数 */
    Map<String, String> handleNotify(String xmlBody);
}
