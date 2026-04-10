package com.mt.pay.swiftpass.util;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 签名/验签工具（从老项目迁移）
 */
public class RSAUtil {

    public enum SignatureSuite {
        SHA1("SHA1WithRSA"),
        SHA256("SHA256WithRSA");

        private final String suite;

        SignatureSuite(String suite) {
            this.suite = suite;
        }

        public String val() {
            return suite;
        }
    }

    private static KeyFactory getKeyFactory() {
        try {
            return KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("初始化RSA KeyFactory失败");
        }
    }

    public static byte[] sign(SignatureSuite suite, byte[] msgBuf, String privateKeyStr) {
        try {
            Signature signature = Signature.getInstance(suite.val());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
            PrivateKey privateKey = getKeyFactory().generatePrivate(keySpec);
            signature.initSign(privateKey);
            signature.update(msgBuf);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("RSA签名失败: " + e.getMessage());
        }
    }

    public static boolean verifySign(SignatureSuite suite, byte[] msgBuf, byte[] sign, String publicKeyStr) {
        try {
            Signature signature = Signature.getInstance(suite.val());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            PublicKey publicKey = getKeyFactory().generatePublic(keySpec);
            signature.initVerify(publicKey);
            signature.update(msgBuf);
            return signature.verify(sign);
        } catch (Exception e) {
            throw new RuntimeException("RSA验签失败: " + e.getMessage());
        }
    }
}
