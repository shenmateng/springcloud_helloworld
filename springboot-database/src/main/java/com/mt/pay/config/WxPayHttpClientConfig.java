package com.mt.pay.config;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;

/**
 * 微信支付 HttpClient 配置（仅真实模式下加载）
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "wx.pay.mock", havingValue = "false", matchIfMissing = true)
public class WxPayHttpClientConfig {

    @Resource
    private WxPayConfig wxPayConfig;

    /**
     * 加载商户私钥
     */
    @Bean
    public PrivateKey merchantPrivateKey() throws IOException {
        String path = wxPayConfig.getPrivateKeyPath();
        if (path == null || path.isEmpty()) {
            throw new RuntimeException("wx.pay.private-key-path 未配置");
        }
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is == null) {
            throw new RuntimeException("商户私钥文件不存在，请将 apiclient_key.pem 放到 resources/" + path
                    + "，或在配置中设置 wx.pay.mock=true 使用 Mock 模式");
        }
        try (InputStream keyStream = is) {
            return PemUtil.loadPrivateKey(keyStream);
        }
    }

    /**
     * 自动更新平台证书验证器
     */
    @Bean
    public AutoUpdateCertificatesVerifier verifier(PrivateKey merchantPrivateKey) {
        return new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(
                        wxPayConfig.getMchId(),
                        new PrivateKeySigner(wxPayConfig.getCertSerialNo(), merchantPrivateKey)
                ),
                wxPayConfig.getApiV3Key().getBytes()
        );
    }

    /**
     * 注入带签名/验签能力的 HttpClient
     */
    @Bean
    public CloseableHttpClient wxPayHttpClient(PrivateKey merchantPrivateKey,
                                               AutoUpdateCertificatesVerifier verifier) {
        return WechatPayHttpClientBuilder.create()
                .withMerchant(wxPayConfig.getMchId(), wxPayConfig.getCertSerialNo(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
    }
}
