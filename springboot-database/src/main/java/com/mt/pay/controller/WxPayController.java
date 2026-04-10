package com.mt.pay.controller;

import com.mt.ResponseResult.ResponseResult;
import com.mt.pay.dto.WxPayOrderReqDTO;
import com.mt.pay.dto.WxPayOrderRespDTO;
import com.mt.pay.dto.WxRefundReqDTO;
import com.mt.pay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信企业号支付 Controller
 * Base URL: /api/wx-pay
 */
@Slf4j
@RestController
@RequestMapping("/api/wx-pay")
public class WxPayController {

    @Resource(name = "wxPayService")
    private WxPayService wxPayService;

    /**
     * JSAPI 下单
     * 企业微信内 H5 页面调用，返回唤起支付所需参数
     *
     * POST /api/wx-pay/create-order
     */
    @PostMapping("/create-order")
    public ResponseResult<WxPayOrderRespDTO> createOrder(@Valid @RequestBody WxPayOrderReqDTO req) {
        log.info("收到下单请求，订单号: {}", req.getOutTradeNo());
        WxPayOrderRespDTO resp = wxPayService.createOrder(req);
        return ResponseResult.success(resp);
    }

    /**
     * 查询订单
     *
     * GET /api/wx-pay/query-order/{outTradeNo}
     */
    @GetMapping("/query-order/{outTradeNo}")
    public ResponseResult<Map<String, Object>> queryOrder(@PathVariable String outTradeNo) {
        log.info("查询订单，订单号: {}", outTradeNo);
        Map<String, Object> result = wxPayService.queryOrder(outTradeNo);
        return ResponseResult.success(result);
    }

    /**
     * 关闭订单
     *
     * POST /api/wx-pay/close-order/{outTradeNo}
     */
    @PostMapping("/close-order/{outTradeNo}")
    public ResponseResult<Void> closeOrder(@PathVariable String outTradeNo) {
        log.info("关闭订单，订单号: {}", outTradeNo);
        wxPayService.closeOrder(outTradeNo);
        return ResponseResult.success();
    }

    /**
     * 申请退款
     *
     * POST /api/wx-pay/refund
     */
    @PostMapping("/refund")
    public ResponseResult<Map<String, Object>> refund(@Valid @RequestBody WxRefundReqDTO req) {
        log.info("申请退款，订单号: {}, 退款单号: {}", req.getOutTradeNo(), req.getOutRefundNo());
        Map<String, Object> result = wxPayService.refund(req);
        return ResponseResult.success(result);
    }

    /**
     * 查询退款
     *
     * GET /api/wx-pay/query-refund/{outRefundNo}
     */
    @GetMapping("/query-refund/{outRefundNo}")
    public ResponseResult<Map<String, Object>> queryRefund(@PathVariable String outRefundNo) {
        log.info("查询退款，退款单号: {}", outRefundNo);
        Map<String, Object> result = wxPayService.queryRefund(outRefundNo);
        return ResponseResult.success(result);
    }

    /**
     * 支付结果回调（微信主动通知）
     * 注意：此接口需要外网可访问，且不能加鉴权拦截
     *
     * POST /api/wx-pay/notify/pay
     */
    @PostMapping("/notify/pay")
    public Map<String, String> payNotify(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("收到支付回调，body: {}", body);

        try {
            Map<String, Object> payResult = wxPayService.handlePayNotify(request, body);
            log.info("支付回调处理成功，结果: {}", payResult);

            // TODO: 根据 payResult 更新业务订单状态
            // String outTradeNo = (String) payResult.get("out_trade_no");
            // String tradeState = (String) payResult.get("trade_state");
            // orderService.updateOrderStatus(outTradeNo, tradeState);

            return successNotifyResponse();
        } catch (Exception e) {
            log.error("支付回调处理失败", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return failNotifyResponse(e.getMessage());
        }
    }

    /**
     * 退款结果回调（微信主动通知）
     *
     * POST /api/wx-pay/notify/refund
     */
    @PostMapping("/notify/refund")
    public Map<String, String> refundNotify(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("收到退款回调，body: {}", body);

        try {
            Map<String, Object> refundResult = wxPayService.handleRefundNotify(request, body);
            log.info("退款回调处理成功，结果: {}", refundResult);

            // TODO: 根据 refundResult 更新业务退款状态
            // String outRefundNo = (String) refundResult.get("out_refund_no");
            // String refundStatus = (String) refundResult.get("refund_status");
            // refundService.updateRefundStatus(outRefundNo, refundStatus);

            return successNotifyResponse();
        } catch (Exception e) {
            log.error("退款回调处理失败", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return failNotifyResponse(e.getMessage());
        }
    }

    /** 回调成功响应（微信要求格式） */
    private Map<String, String> successNotifyResponse() {
        Map<String, String> resp = new HashMap<>();
        resp.put("code", "SUCCESS");
        resp.put("message", "成功");
        return resp;
    }

    /** 回调失败响应 */
    private Map<String, String> failNotifyResponse(String message) {
        Map<String, String> resp = new HashMap<>();
        resp.put("code", "FAIL");
        resp.put("message", message);
        return resp;
    }
}
