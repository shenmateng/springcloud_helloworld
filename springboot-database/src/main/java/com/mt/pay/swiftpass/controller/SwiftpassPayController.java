package com.mt.pay.swiftpass.controller;

import com.mt.ResponseResult.ResponseResult;
import com.mt.pay.swiftpass.dto.SwiftpassPayReqDTO;
import com.mt.pay.swiftpass.dto.SwiftpassRefundReqDTO;
import com.mt.pay.swiftpass.service.SwiftpassPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Swiftpass 聚合支付 Controller
 * Base URL: /api/swiftpass
 */
@Slf4j
@RestController
@RequestMapping("/api/swiftpass")
public class SwiftpassPayController {

    @Resource
    private SwiftpassPayService swiftpassPayService;

    /**
     * JSAPI 下单
     * 返回 pay_info，前端用于唤起微信支付
     *
     * POST /api/swiftpass/pay
     */
    @PostMapping("/pay")
    public ResponseResult<Map<String, String>> pay(@Valid @RequestBody SwiftpassPayReqDTO req,
                                                    HttpServletRequest request) {
        log.info("下单请求，订单号: {}", req.getOutTradeNo());
        // 自动获取客户端真实IP
        if (req.getSpbillCreateIp() == null || req.getSpbillCreateIp().isEmpty()) {
            req.setSpbillCreateIp(getClientIp(request));
        }
        Map<String, String> result = swiftpassPayService.pay(req);
        return ResponseResult.success(result);
    }



    /** 获取客户端真实IP（兼容代理/nginx转发） */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0].trim();
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 查询订单
     *
     * GET /api/swiftpass/query-order?outTradeNo=xxx
     */
    @GetMapping("/query-order")
    public ResponseResult<Map<String, String>> queryOrder(
            @RequestParam String outTradeNo,
            @RequestParam(defaultValue = "RSA_1_256") String signType) {
        log.info("查询订单: {}", outTradeNo);
        Map<String, String> result = swiftpassPayService.queryOrder(outTradeNo, signType);
        return ResponseResult.success(result);
    }

    /**
     * 申请退款
     *
     * POST /api/swiftpass/refund
     */
    @PostMapping("/refund")
    public ResponseResult<Map<String, String>> refund(@Valid @RequestBody SwiftpassRefundReqDTO req) {
        log.info("退款请求，订单号: {}, 退款单号: {}", req.getOutTradeNo(), req.getOutRefundNo());
        Map<String, String> result = swiftpassPayService.refund(req);
        return ResponseResult.success(result);
    }

    /**
     * 查询退款
     *
     * GET /api/swiftpass/query-refund?outTradeNo=xxx&outRefundNo=xxx
     */
    @GetMapping("/query-refund")
    public ResponseResult<Map<String, String>> queryRefund(
            @RequestParam String outTradeNo,
            @RequestParam String outRefundNo,
            @RequestParam(defaultValue = "RSA_1_256") String signType) {
        log.info("查询退款: {}", outRefundNo);
        Map<String, String> result = swiftpassPayService.queryRefund(outTradeNo, outRefundNo, signType);
        return ResponseResult.success(result);
    }

    /**
     * 支付结果回调（Swiftpass 主动通知）
     * 注意：此接口需外网可访问，不能加鉴权拦截
     *
     * POST /api/swiftpass/notify
     */
    @PostMapping("/notify")
    public String notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String xmlBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("收到支付回调，body: {}", xmlBody);
        try {
            Map<String, String> notifyResult = swiftpassPayService.handleNotify(xmlBody);
            log.info("回调处理成功，订单号: {}", notifyResult.get("out_trade_no"));

            // TODO: 在这里更新业务订单状态
            // String outTradeNo = notifyResult.get("out_trade_no");
            // String transactionId = notifyResult.get("transaction_id");
            // orderService.updateOrderPaid(outTradeNo, transactionId);

            return "success";  // Swiftpass 要求返回 "success" 字符串
        } catch (Exception e) {
            log.error("回调处理失败", e);
            return "error";
        }
    }
}
