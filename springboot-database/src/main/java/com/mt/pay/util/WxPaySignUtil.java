package com.mt.pay.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;
import java.util.UUID;

/**
 * 微信支付签名工具类
 */
@Slf4j
public class WxPaySignUtil {

    private static final int TAG_LENGTH_BIT = 128;

    /**
     * 生成随机字符串
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * SHA256withRSA 签名
     *
     * @param message    待签名字符串
     * @param privateKey 商户私钥
     * @return Base64 编码的签名
     */
    public static String sign(String message, PrivateKey privateKey) {
        try {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(privateKey);
            sign.update(message.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch (Exception e) {
            log.error("微信支付签名失败", e);
            throw new RuntimeException("签名失败: " + e.getMessage());
        }
    }

    /**
     * AES-256-GCM 解密微信回调 resource
     *
     * @param apiV3Key       APIv3 密钥（32位字符串）
     * @param associatedData 附加数据
     * @param nonce          随机串（12位）
     * @param ciphertext     Base64 编码的密文
     * @return 解密后的明文
     */
    public static String decryptAesGcm(String apiV3Key, String associatedData,
                                        String nonce, String ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKeySpec key = new SecretKeySpec(apiV3Key.getBytes(StandardCharsets.UTF_8), "AES");
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes(StandardCharsets.UTF_8));

            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("微信支付回调解密失败", e);
            throw new RuntimeException("回调解密失败: " + e.getMessage());
        }
    }
}
