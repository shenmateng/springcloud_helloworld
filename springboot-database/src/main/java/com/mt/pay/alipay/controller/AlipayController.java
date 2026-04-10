package com.mt.pay.alipay.controller;

import com.mt.ResponseResult.ResponseResult;
import com.mt.pay.alipay.dto.AlipayOrderReqDTO;
import com.mt.pay.alipay.dto.AlipayRefundReqDTO;
import com.mt.pay.alipay.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 支付宝服务窗支付 Controller
 * Base URL: /api/alipay
 */
@Slf4j
@RestController
@RequestMapping("/api/alipay")
public class AlipayController {

    @Resource
    private AlipayService alipayService;

    /**
     * 下单
     * 返回 pay_info，其中 tradeNO 用于前端唤起支付宝收银台
     *
     * POST /api/alipay/pay
     */
    @PostMapping("/pay")
    public ResponseResult<Map<String, String>> pay(@Valid @RequestBody AlipayOrderReqDTO req,
                                                    HttpServletRequest request) {
        log.info("支付宝下单，订单号: {}", req.getOutTradeNo());
        if (req.getMchCreateIp() == null || req.getMchCreateIp().isEmpty()) {
            req.setMchCreateIp(getClientIp(request));
        }
        Map<String, String> result = alipayService.pay(req);
        return ResponseResult.success(result);
    }

    /**
     * 查询订单
     *
     * GET /api/alipay/query-order?outTradeNo=xxx
     */
    @GetMapping("/query-order")
    public ResponseResult<Map<String, String>> queryOrder(
            @RequestParam String outTradeNo,
            @RequestParam(defaultValue = "RSA_1_256") String signType) {
        log.info("支付宝查询订单: {}", outTradeNo);
        return ResponseResult.success(alipayService.queryOrder(outTradeNo, signType));
    }

    /**
     * 关闭订单
     *
     * POST /api/alipay/close-order/{outTradeNo}
     */
    @PostMapping("/close-order/{outTradeNo}")
    public ResponseResult<Map<String, String>> closeOrder(
            @PathVariable String outTradeNo,
            @RequestParam(defaultValue = "RSA_1_256") String signType) {
        log.info("支付宝关闭订单: {}", outTradeNo);
        return ResponseResult.success(alipayService.closeOrder(outTradeNo, signType));
    }

    /**
     * 申请退款
     *
     * POST /api/alipay/refund
     */
    @PostMapping("/refund")
    public ResponseResult<Map<String, String>> refund(@Valid @RequestBody AlipayRefundReqDTO req) {
        log.info("支付宝退款，订单号: {}", req.getOutTradeNo());
        return ResponseResult.success(alipayService.refund(req));
    }

    /**
     * 查询退款
     *
     * GET /api/alipay/query-refund?outTradeNo=xxx&outRefundNo=xxx
     */
    @GetMapping("/query-refund")
    public ResponseResult<Map<String, String>> queryRefund(
            @RequestParam String outTradeNo,
            @RequestParam String outRefundNo,
            @RequestParam(defaultValue = "RSA_1_256") String signType) {
        log.info("支付宝查询退款: {}", outRefundNo);
        return ResponseResult.success(alipayService.queryRefund(outTradeNo, outRefundNo, signType));
    }

    /**
     * 支付结果回调（Swiftpass 主动通知）
     * 注意：此接口需外网可访问，不能加鉴权拦截
     *
     * POST /api/alipay/notify
     */
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) throws IOException {
        String xmlBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("收到支付宝回调，body: {}", xmlBody);
        try {
            Map<String, String> notifyResult = alipayService.handleNotify(xmlBody);
            log.info("支付宝回调处理成功，订单号: {}", notifyResult.get("out_trade_no"));

            // TODO: 更新业务订单状态
            // String outTradeNo = notifyResult.get("out_trade_no");
            // orderService.updateOrderPaid(outTradeNo);

            return "success";
        } catch (Exception e) {
            log.error("支付宝回调处理失败", e);
            return "fail";
        }
    }

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
}
