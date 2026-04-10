package com.mt.pay;

import com.alibaba.fastjson.JSON;
import com.mt.pay.controller.WxPayController;
import com.mt.pay.dto.WxPayOrderReqDTO;
import com.mt.pay.dto.WxRefundReqDTO;
import com.mt.pay.service.WxPayService;
import com.mt.pay.service.impl.WxPayMockServiceImpl;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 微信支付 Controller 单元测试
 * 不依赖 Spring 容器，不需要数据库/Consul/微信证书
 * 直接运行：右键 -> Run
 */
class WxPayControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // 直接 new，不走 Spring 容器
        WxPayService mockService = new WxPayMockServiceImpl();
        WxPayController controller = new WxPayController();
        // 通过反射注入 service
        try {
            var field = WxPayController.class.getDeclaredField("wxPayService");
            field.setAccessible(true);
            field.set(controller, mockService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCreateOrder() throws Exception {
        WxPayOrderReqDTO req = new WxPayOrderReqDTO();
        req.setDescription("测试商品");
        req.setOutTradeNo("TEST_ORDER_" + System.currentTimeMillis());
        req.setTotal(100);  // 1元
        req.setOpenId("mock_openid_001");

        mockMvc.perform(post("/api/wx-pay/create-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(req)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.outTradeNo").exists())
                .andExpect(jsonPath("$.data.packageStr").exists())
                .andExpect(jsonPath("$.data.paySign").exists());
    }

    @Test
    void testCreateOrder_missingField() throws Exception {
        // 缺少必填字段，应返回 400
        WxPayOrderReqDTO req = new WxPayOrderReqDTO();
        req.setDescription("测试商品");
        // 故意不设置 outTradeNo 和 total

        mockMvc.perform(post("/api/wx-pay/create-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(req)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testQueryOrder() throws Exception {
        mockMvc.perform(get("/api/wx-pay/query-order/TEST_ORDER_001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.trade_state").value("SUCCESS"));
    }

    @Test
    void testCloseOrder() throws Exception {
        mockMvc.perform(post("/api/wx-pay/close-order/TEST_ORDER_001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    void testRefund() throws Exception {
        WxRefundReqDTO req = new WxRefundReqDTO();
        req.setOutTradeNo("TEST_ORDER_001");
        req.setOutRefundNo("REFUND_" + System.currentTimeMillis());
        req.setReason("用户申请退款");
        req.setTotal(100);
        req.setRefund(100);

        mockMvc.perform(post("/api/wx-pay/refund")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(req)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.status").value("PROCESSING"));
    }

    @Test
    void testQueryRefund() throws Exception {
        mockMvc.perform(get("/api/wx-pay/query-refund/REFUND_001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.status").value("SUCCESS"));
    }

    @Test
    void testPayNotify() throws Exception {
        // 模拟微信回调的请求体
        String notifyBody = "{"
                + "\"id\":\"mock-notify-id\","
                + "\"event_type\":\"TRANSACTION.SUCCESS\","
                + "\"resource\":{"
                + "  \"algorithm\":\"AEAD_AES_256_GCM\","
                + "  \"ciphertext\":\"mock_cipher\","
                + "  \"associated_data\":\"transaction\","
                + "  \"nonce\":\"mock_nonce_12\""
                + "}"
                + "}";

        // Mock 实现不做真实解密，直接返回模拟数据
        mockMvc.perform(post("/api/wx-pay/notify/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(notifyBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("SUCCESS"));
    }
}
