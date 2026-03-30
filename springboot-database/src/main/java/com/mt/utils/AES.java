package com.mt.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by zhaolm .
 */
public class AES {

    public static final String AES_KEY = "695fb4b0b6c643d1866c6c405bdffacf";

    public static final String AUTH_KEY = "guoyutecYI3245NHJssdf";


    public static String encrypt(String strIn, String strKey) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(strIn.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String strIn, String strKey) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = Base64.getDecoder().decode(strIn);

        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString;
    }

    public static String decryptAuth(String strIn){
        try{
            SecretKeySpec skeySpec = getKey(AUTH_KEY);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(strIn);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        // 创建一个空的16位字节数组（默认值为0）
        byte[] arrB = new byte[16];

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

        return skeySpec;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("y/J+ke/GR8DJSg3G", AES.AES_KEY));
        System.out.println("gy_agent_setting     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_agent_setting?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_asset     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_asset?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_auth     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_auth?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_common     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_common?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_event_analysis     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_event_analysis?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_monitor     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_monitor?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_operational     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_operational?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_protect     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_protect?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_scan     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_scan?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
        System.out.println("gy_user     " + AES.encrypt("jdbc:mysql://192.168.10.20:10000/gy_user?characterEncoding=UTF-8&allowMultiQueries=true&useTimezone=true&serverTimezone=GMT%2B8", AES.AES_KEY));
    }
}
