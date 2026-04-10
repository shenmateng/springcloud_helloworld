package com.mt.pay.swiftpass.service;

import com.mt.pay.swiftpass.dto.SwiftpassPayReqDTO;
import com.mt.pay.swiftpass.dto.SwiftpassRefundReqDTO;

import java.util.Map;

/**
 * Swiftpass 聚合支付 Service 接口
 */
public interface SwiftpassPayService {

    /** JSAPI 下单，返回 pay_info（前端唤起支付用） */
    Map<String, String> pay(SwiftpassPayReqDTO req);

    /** 查询订单 */
    Map<String, String> queryOrder(String outTradeNo, String signType);

    /** 申请退款 */
    Map<String, String> refund(SwiftpassRefundReqDTO req);

    /** 查询退款 */
    Map<String, String> queryRefund(String outTradeNo, String outRefundNo, String signType);

    /** 处理支付回调，验签后返回通知参数 */
    Map<String, String> handleNotify(String xmlBody);
}
