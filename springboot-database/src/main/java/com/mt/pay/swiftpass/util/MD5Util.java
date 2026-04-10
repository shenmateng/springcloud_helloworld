package com.mt.pay.swiftpass.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5 签名工具（从老项目迁移）
 */
public class MD5Util {

    /**
     * MD5 签名
     *
     * @param text          待签名字符串
     * @param key           密钥（拼接到末尾）
     * @param inputCharset  编码
     * @return 签名结果（大写）
     */
    public static String sign(String text, String key, String inputCharset) {
        String content = text + key;
        try {
            return DigestUtils.md5Hex(content.getBytes(inputCharset)).toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("MD5签名失败: " + e.getMessage());
        }
    }
}
