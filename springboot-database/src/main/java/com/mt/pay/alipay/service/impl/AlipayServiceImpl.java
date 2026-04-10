package com.mt.pay.alipay.service.impl;

import com.mt.pay.alipay.dto.AlipayOrderReqDTO;
import com.mt.pay.alipay.dto.AlipayRefundReqDTO;
import com.mt.pay.alipay.service.AlipayService;
import com.mt.pay.swiftpass.config.SwiftpassConfig;
import com.mt.pay.swiftpass.util.SignUtil;
import com.mt.pay.swiftpass.util.SignUtils;
import com.mt.pay.swiftpass.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * 支付宝服务窗支付 Service 实现
 * 复用 Swiftpass 网关配置和工具类，service 字段改为支付宝对应值
 */
@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {

    private static final String VERSION = "2.0";
    private static final String CHARSET = "UTF-8";

    // 复用微信支付的 Swiftpass 配置（同一个商户号，同一个网关）
    @Resource
    private SwiftpassConfig swiftpassConfig;

    @Override
    public Map<String, String> pay(AlipayOrderReqDTO req) {
        log.info("支付宝下单，订单号: {}", req.getOutTradeNo());
        TreeMap<String, String> map = new TreeMap<>();
        map.put("service", "pay.alipay.jspay");   // 支付宝服务窗
        map.put("version", VERSION);
        map.put("charset", CHARSET);
        map.put("sign_type", req.getSignType());
        map.put("mch_id", swiftpassConfig.getMchId());
        map.put("notify_url", swiftpassConfig.getNotifyUrl());
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        map.put("body", req.getBody());
        map.put("out_trade_no", req.getOutTradeNo());
        map.put("total_fee", String.valueOf(req.getTotalFee()));
        map.put("mch_create_ip", req.getMchCreateIp());

        if (req.getAttach() != null && !req.getAttach().isEmpty()) {
            map.put("attach", req.getAttach());
        }
        // buyer_logon_id 和 buyer_id 二选一
        if (req.getBuyerLogonId() != null && !req.getBuyerLogonId().isEmpty()) {
            map.put("buyer_logon_id", req.getBuyerLogonId());
        } else if (req.getBuyerId() != null && !req.getBuyerId().isEmpty()) {
            map.put("buyer_id", req.getBuyerId());
        }

        return doRequest(map, req.getSignType());
    }

    @Override
    public Map<String, String> queryOrder(String outTradeNo, String signType) {
        log.info("支付宝查询订单: {}", outTradeNo);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("service", "unified.trade.query");
        map.put("version", VERSION);
        map.put("charset", CHARSET);
        map.put("sign_type", signType);
        map.put("mch_id", swiftpassConfig.getMchId());
        map.put("out_trade_no", outTradeNo);
        map.put("nonce_str", String.valueOf(new Date().getTime()));

        return doRequest(map, signType);
    }

    @Override
    public Map<String, String> closeOrder(String outTradeNo, String signType) {
        log.info("支付宝关闭订单: {}", outTradeNo);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("service", "unified.trade.close");
        map.put("version", VERSION);
        map.put("charset", CHARSET);
        map.put("sign_type", signType);
        map.put("mch_id", swiftpassConfig.getMchId());
        map.put("out_trade_no", outTradeNo);
        map.put("nonce_str", String.valueOf(new Date().getTime()));

        return doRequest(map, signType);
    }

    @Override
    public Map<String, String> refund(AlipayRefundReqDTO req) {
        log.info("支付宝退款，订单号: {}, 退款单号: {}", req.getOutTradeNo(), req.getOutRefundNo());
        TreeMap<String, String> map = new TreeMap<>();
        map.put("service", "unified.trade.refund");
        map.put("version", VERSION);
        map.put("charset", CHARSET);
        map.put("sign_type", req.getSignType());
        map.put("mch_id", swiftpassConfig.getMchId());
        map.put("op_user_id", swiftpassConfig.getMchId());
        map.put("out_trade_no", req.getOutTradeNo());
        map.put("out_refund_no", req.getOutRefundNo());
        map.put("total_fee", String.valueOf(req.getTotalFee()));
        map.put("refund_fee", String.valueOf(req.getRefundFee()));
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        if (req.getRefundDesc() != null) {
            map.put("refund_desc", req.getRefundDesc());
        }

        return doRequest(map, req.getSignType());
    }

    @Override
    public Map<String, String> queryRefund(String outTradeNo, String outRefundNo, String signType) {
        log.info("支付宝查询退款: {}", outRefundNo);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("service", "unified.trade.refundquery");
        map.put("version", VERSION);
        map.put("charset", CHARSET);
        map.put("sign_type", signType);
        map.put("mch_id", swiftpassConfig.getMchId());
        map.put("out_trade_no", outTradeNo);
        map.put("out_refund_no", outRefundNo);
        map.put("nonce_str", String.valueOf(new Date().getTime()));

        return doRequest(map, signType);
    }

    @Override
    public Map<String, String> handleNotify(String xmlBody) {
        log.info("收到支付宝回调: {}", xmlBody);
        try {
            Map<String, String> notifyMap = XmlUtil.toMap(xmlBody.getBytes("utf-8"), "utf-8");
            String signType = notifyMap.get("sign_type");
            String sign = notifyMap.get("sign");

            if (sign != null && !SignUtil.verifySign(sign, signType, notifyMap,
                    swiftpassConfig.getKey(), swiftpassConfig.getPlatPublicKey())) {
                throw new RuntimeException("回调验签失败");
            }

            String status = notifyMap.get("status");
            String resultCode = notifyMap.get("result_code");
            if (!"0".equals(status) || !"0".equals(resultCode)) {
                throw new RuntimeException("回调业务状态异常: status=" + status + ", result_code=" + resultCode);
            }

            log.info("支付宝回调验签通过，订单号: {}", notifyMap.get("out_trade_no"));
            return notifyMap;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("回调处理失败: " + e.getMessage());
        }
    }

    private Map<String, String> doRequest(TreeMap<String, String> map, String signType) {
        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = SignUtil.getSign(signType, preStr,
                swiftpassConfig.getKey(), swiftpassConfig.getMchPrivateKey());
        map.put("sign", sign);

        String reqXml = XmlUtil.sortedMapToXml(map);
        log.info("请求XML: {}", reqXml);

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(swiftpassConfig.getReqUrl());
            httpPost.setEntity(new StringEntity(reqXml, "utf-8"));
            httpPost.setHeader("Content-Type", "text/xml;utf-8");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);

            byte[] bytes = EntityUtils.toByteArray(response.getEntity());
            Map<String, String> resultMap = XmlUtil.toMap(bytes, "utf-8");
            log.info("响应结果: {}", resultMap);

            if (map.containsKey("out_trade_no")) {
                resultMap.put("out_trade_no", map.get("out_trade_no"));
            }

            String reSign = resultMap.get("sign");
            String resSignType = resultMap.get("sign_type");
            if (reSign != null && resSignType != null) {
                try {
                    if (!SignUtil.verifySign(reSign, resSignType, resultMap,
                            swiftpassConfig.getKey(), swiftpassConfig.getPlatPublicKey())) {
                        log.warn("响应验签失败，请检查 platPublicKey 配置");
                    }
                } catch (Exception e) {
                    log.warn("响应验签异常: {}", e.getMessage());
                }
            }

            return resultMap;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("支付宝请求失败: " + e.getMessage());
        } finally {
            try {
                if (response != null) response.close();
                if (client != null) client.close();
            } catch (Exception ignored) {}
        }
    }
}
