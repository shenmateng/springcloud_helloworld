package com.mt.pay.config;

import com.mt.pay.service.WxPayService;
import com.mt.pay.service.impl.WxPayMockServiceImpl;
import com.mt.pay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 根据 wx.pay.mock=true/false 切换 Mock 或真实实现
 *
 * 本地开发/没有证书时：在 Consul 或本地 yml 中设置 wx.pay.mock=true
 * 生产环境：设置 wx.pay.mock=false，并提供真实证书
 */
@Slf4j
@Configuration
public class WxPayServiceConfig {

    /**
     * Mock 模式（wx.pay.mock=true）
     * 不需要证书，不调用真实微信接口
     */
    @Bean("wxPayService")
    @ConditionalOnProperty(name = "wx.pay.mock", havingValue = "true")
    public WxPayService wxPayMockService() {
        log.warn("========================================");
        log.warn("  微信支付运行在 [MOCK] 模式，仅用于开发测试  ");
        log.warn("========================================");
        return new WxPayMockServiceImpl();
    }

    /**
     * 真实模式（wx.pay.mock=false 或未配置）
     * 需要真实商户证书
     */
    @Bean("wxPayService")
    @ConditionalOnProperty(name = "wx.pay.mock", havingValue = "false", matchIfMissing = true)
    public WxPayService wxPayRealService(WxPayConfig wxPayConfig,
                                         org.apache.http.impl.client.CloseableHttpClient wxPayHttpClient,
                                         java.security.PrivateKey merchantPrivateKey) {
        log.info("微信支付运行在 [真实] 模式");
        WxPayServiceImpl service = new WxPayServiceImpl();
        service.setWxPayConfig(wxPayConfig);
        service.setWxPayHttpClient(wxPayHttpClient);
        service.setMerchantPrivateKey(merchantPrivateKey);
        return service;
    }
}
