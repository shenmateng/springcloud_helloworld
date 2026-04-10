package com.mt.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mt.pay.config.WxPayConfig;
import com.mt.pay.dto.WxPayOrderReqDTO;
import com.mt.pay.dto.WxPayOrderRespDTO;
import com.mt.pay.dto.WxRefundReqDTO;
import com.mt.pay.service.WxPayService;
import com.mt.pay.util.WxPaySignUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信企业号支付 Service 实现
 * 基于微信支付 APIv3（真实模式，需要商户证书）
 */
@Slf4j
@Setter
public class WxPayServiceImpl implements WxPayService {

    private WxPayConfig wxPayConfig;

    private CloseableHttpClient wxPayHttpClient;

    private PrivateKey merchantPrivateKey;

    // ==================== 下单 ====================

    @Override
    public WxPayOrderRespDTO createOrder(WxPayOrderReqDTO req) {
        log.info("微信支付下单，订单号: {}, 金额: {} 分", req.getOutTradeNo(), req.getTotal());

        // 构建请求体
        JSONObject body = new JSONObject();
        body.put("appid", wxPayConfig.getAppId());
        body.put("mchid", wxPayConfig.getMchId());
        body.put("description", req.getDescription());
        body.put("out_trade_no", req.getOutTradeNo());
        body.put("notify_url", wxPayConfig.getNotifyUrl());
        if (req.getAttach() != null) {
            body.put("attach", req.getAttach());
        }

        JSONObject amount = new JSONObject();
        amount.put("total", req.getTotal());
        amount.put("currency", "CNY");
        body.put("amount", amount);

        JSONObject payer = new JSONObject();
        payer.put("openid", req.getOpenId());
        body.put("payer", payer);

        // 发起请求
        String responseBody = doPost(wxPayConfig.getJsapiUrl(), body.toJSONString());
        JSONObject result = JSON.parseObject(responseBody);

        String prepayId = result.getString("prepay_id");
        if (prepayId == null) {
            log.error("微信支付下单失败，响应: {}", responseBody);
            throw new RuntimeException("微信支付下单失败: " + result.getString("message"));
        }

        log.info("微信支付下单成功，prepay_id: {}", prepayId);
        return buildJsapiParams(prepayId, req.getOutTradeNo());
    }

    /**
     * 构建 JSAPI 唤起支付所需参数并签名
     */
    private WxPayOrderRespDTO buildJsapiParams(String prepayId, String outTradeNo) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = WxPaySignUtil.generateNonceStr();
        String packageStr = "prepay_id=" + prepayId;

        // 签名串：appId\n时间戳\n随机串\npackage\n
        String signMessage = wxPayConfig.getAppId() + "\n"
                + timeStamp + "\n"
                + nonceStr + "\n"
                + packageStr + "\n";

        String paySign = WxPaySignUtil.sign(signMessage, merchantPrivateKey);

        WxPayOrderRespDTO resp = new WxPayOrderRespDTO();
        resp.setAppId(wxPayConfig.getAppId());
        resp.setTimeStamp(timeStamp);
        resp.setNonceStr(nonceStr);
        resp.setPackageStr(packageStr);
        resp.setSignType("RSA");
        resp.setPaySign(paySign);
        resp.setOutTradeNo(outTradeNo);
        return resp;
    }

    // ==================== 查询订单 ====================

    @Override
    public Map<String, Object> queryOrder(String outTradeNo) {
        log.info("查询微信支付订单，订单号: {}", outTradeNo);
        String url = wxPayConfig.getQueryOrderUrl() + outTradeNo + "?mchid=" + wxPayConfig.getMchId();
        String responseBody = doGet(url);
        log.info("查询订单响应: {}", responseBody);
        return JSON.parseObject(responseBody, Map.class);
    }

    // ==================== 关闭订单 ====================

    @Override
    public void closeOrder(String outTradeNo) {
        log.info("关闭微信支付订单，订单号: {}", outTradeNo);
        String url = String.format(wxPayConfig.getCloseOrderUrl(), outTradeNo);

        JSONObject body = new JSONObject();
        body.put("mchid", wxPayConfig.getMchId());

        String responseBody = doPost(url, body.toJSONString());
        log.info("关闭订单响应: {}", responseBody);
    }

    // ==================== 退款 ====================

    @Override
    public Map<String, Object> refund(WxRefundReqDTO req) {
        log.info("申请微信退款，订单号: {}, 退款单号: {}", req.getOutTradeNo(), req.getOutRefundNo());

        JSONObject body = new JSONObject();
        body.put("out_trade_no", req.getOutTradeNo());
        body.put("out_refund_no", req.getOutRefundNo());
        if (req.getReason() != null) {
            body.put("reason", req.getReason());
        }

        JSONObject amount = new JSONObject();
        amount.put("refund", req.getRefund());
        amount.put("total", req.getTotal());
        amount.put("currency", "CNY");
        body.put("amount", amount);

        String responseBody = doPost(wxPayConfig.getRefundUrl(), body.toJSONString());
        log.info("申请退款响应: {}", responseBody);

        Map<String, Object> result = JSON.parseObject(responseBody, Map.class);
        if (result.get("status") == null) {
            throw new RuntimeException("申请退款失败: " + result.get("message"));
        }
        return result;
    }

    @Override
    public Map<String, Object> queryRefund(String outRefundNo) {
        log.info("查询微信退款，退款单号: {}", outRefundNo);
        String url = wxPayConfig.getQueryRefundUrl() + outRefundNo;
        String responseBody = doGet(url);
        log.info("查询退款响应: {}", responseBody);
        return JSON.parseObject(responseBody, Map.class);
    }

    // ==================== 回调处理 ====================

    @Override
    public Map<String, Object> handlePayNotify(HttpServletRequest request, String body) {
        log.info("收到微信支付回调");
        return decryptNotify(request, body);
    }

    @Override
    public Map<String, Object> handleRefundNotify(HttpServletRequest request, String body) {
        log.info("收到微信退款回调");
        return decryptNotify(request, body);
    }

    /**
     * 解密微信回调通知
     * 验签由 WechatPayHttpClientBuilder 的 Validator 自动处理（拦截器层），
     * 这里只做 resource 解密
     */
    private Map<String, Object> decryptNotify(HttpServletRequest request, String body) {
        JSONObject notifyBody = JSON.parseObject(body);
        JSONObject resource = notifyBody.getJSONObject("resource");

        String algorithm = resource.getString("algorithm");
        String ciphertext = resource.getString("ciphertext");
        String associatedData = resource.getString("associated_data");
        String nonce = resource.getString("nonce");

        // AES-256-GCM 解密
        String plainText = WxPaySignUtil.decryptAesGcm(
                wxPayConfig.getApiV3Key(),
                associatedData,
                nonce,
                ciphertext
        );

        log.info("回调解密结果: {}", plainText);
        return JSON.parseObject(plainText, Map.class);
    }

    // ==================== HTTP 工具方法 ====================

    private String doPost(String url, String jsonBody) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));

        try (CloseableHttpResponse response = wxPayHttpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("POST {} 状态码: {}", url, statusCode);
            return responseBody;
        } catch (Exception e) {
            log.error("微信支付 POST 请求失败，url: {}", url, e);
            throw new RuntimeException("微信支付请求失败: " + e.getMessage());
        }
    }

    private String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");

        try (CloseableHttpResponse response = wxPayHttpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            log.info("GET {} 状态码: {}", url, statusCode);
            return responseBody;
        } catch (Exception e) {
            log.error("微信支付 GET 请求失败，url: {}", url, e);
            throw new RuntimeException("微信支付请求失败: " + e.getMessage());
        }
    }
}
