package com.mt.pay.swiftpass.util;

import org.apache.commons.codec.binary.Base64;

import java.util.Map;

/**
 * 签名/验签入口工具（从老项目迁移，改为 Spring 注入配置）
 */
public class SignUtil {

    /**
     * 生成签名
     *
     * @param signType       签名方式：RSA_1_256 或 MD5
     * @param preStr         待签名字符串
     * @param key            MD5密钥
     * @param mchPrivateKey  RSA私钥
     */
    public static String getSign(String signType, String preStr, String key, String mchPrivateKey) {
        if ("RSA_1_256".equals(signType)) {
            try {
                byte[] signBuf = RSAUtil.sign(RSAUtil.SignatureSuite.SHA256,
                        preStr.getBytes("UTF-8"), mchPrivateKey);
                return new String(Base64.encodeBase64(signBuf), "UTF-8");
            } catch (Exception e) {
                throw new RuntimeException("RSA签名失败: " + e.getMessage());
            }
        } else {
            // 默认 MD5
            return MD5Util.sign(preStr, "&key=" + key, "utf-8");
        }
    }

    /**
     * 验证签名
     *
     * @param sign          待验证签名
     * @param signType      签名方式
     * @param resultMap     返回参数 Map
     * @param key           MD5密钥
     * @param platPublicKey RSA平台公钥
     */
    public static boolean verifySign(String sign, String signType,
                                     Map<String, String> resultMap,
                                     String key, String platPublicKey) throws Exception {
        if ("RSA_1_256".equals(signType)) {
            Map<String, String> params = SignUtils.paraFilter(resultMap);
            StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
            SignUtils.buildPayParams(buf, params, false);
            String preStr = buf.toString();
            return RSAUtil.verifySign(
                    RSAUtil.SignatureSuite.SHA256,
                    preStr.getBytes("UTF-8"),
                    Base64.decodeBase64(sign.getBytes("UTF-8")),
                    platPublicKey
            );
        } else if ("MD5".equals(signType)) {
            return SignUtils.checkParam(resultMap, key);
        }
        return false;
    }
}
