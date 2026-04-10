package com.mt.pay.service.impl;

import com.mt.pay.dto.WxPayOrderReqDTO;
import com.mt.pay.dto.WxPayOrderRespDTO;
import com.mt.pay.dto.WxRefundReqDTO;
import com.mt.pay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 微信支付 Mock 实现 —— 本地开发/测试用，不调用真实微信接口
 * 使用方式：在 bootstrap.yml 中设置 wx.pay.mock=true，
 *           并在 WxPayConfig 中通过 @ConditionalOnProperty 切换实现
 */
@Slf4j
public class WxPayMockServiceImpl implements WxPayService {

    @Override
    public WxPayOrderRespDTO createOrder(WxPayOrderReqDTO req) {
        log.info("[MOCK] 下单，订单号: {}, 金额: {} 分", req.getOutTradeNo(), req.getTotal());

        WxPayOrderRespDTO resp = new WxPayOrderRespDTO();
        resp.setAppId("wx_mock_appid");
        resp.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
        resp.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
        resp.setPackageStr("prepay_id=mock_prepay_" + req.getOutTradeNo());
        resp.setSignType("RSA");
        resp.setPaySign("mock_pay_sign_" + System.currentTimeMillis());
        resp.setOutTradeNo(req.getOutTradeNo());
        return resp;
    }

    @Override
    public Map<String, Object> queryOrder(String outTradeNo) {
        log.info("[MOCK] 查询订单: {}", outTradeNo);
        Map<String, Object> result = new HashMap<>();
        result.put("out_trade_no", outTradeNo);
        result.put("trade_state", "SUCCESS");
        result.put("trade_state_desc", "支付成功");
        result.put("transaction_id", "mock_wx_" + System.currentTimeMillis());
        result.put("success_time", "2024-01-01T10:00:00+08:00");
        return result;
    }

    @Override
    public void closeOrder(String outTradeNo) {
        log.info("[MOCK] 关闭订单: {}", outTradeNo);
    }

    @Override
    public Map<String, Object> refund(WxRefundReqDTO req) {
        log.info("[MOCK] 申请退款，订单号: {}, 退款单号: {}", req.getOutTradeNo(), req.getOutRefundNo());
        Map<String, Object> result = new HashMap<>();
        result.put("out_trade_no", req.getOutTradeNo());
        result.put("out_refund_no", req.getOutRefundNo());
        result.put("refund_id", "mock_refund_" + System.currentTimeMillis());
        result.put("status", "PROCESSING");
        result.put("amount", req.getRefund());
        return result;
    }

    @Override
    public Map<String, Object> queryRefund(String outRefundNo) {
        log.info("[MOCK] 查询退款: {}", outRefundNo);
        Map<String, Object> result = new HashMap<>();
        result.put("out_refund_no", outRefundNo);
        result.put("refund_id", "mock_refund_id_001");
        result.put("status", "SUCCESS");
        result.put("success_time", "2024-01-01T11:00:00+08:00");
        return result;
    }

    @Override
    public Map<String, Object> handlePayNotify(HttpServletRequest request, String body) {
        log.info("[MOCK] 处理支付回调，body: {}", body);
        Map<String, Object> result = new HashMap<>();
        result.put("out_trade_no", "mock_order_001");
        result.put("trade_state", "SUCCESS");
        result.put("transaction_id", "mock_wx_trans_001");
        return result;
    }

    @Override
    public Map<String, Object> handleRefundNotify(HttpServletRequest request, String body) {
        log.info("[MOCK] 处理退款回调，body: {}", body);
        Map<String, Object> result = new HashMap<>();
        result.put("out_refund_no", "mock_refund_001");
        result.put("refund_status", "SUCCESS");
        return result;
    }
}
