package com.mt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author zhaolm
 * @data 2019/1/21
 */
public class Md5 {
    public static String encodePassword(String rawPass) {
        String ret = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(rawPass.getBytes());
            ret = new String(Base64.getEncoder().encodeToString(md.digest()));
            ret = ret.substring(0, ret.length() - 8);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * 生成ES的ID
     * @param rawPass
     * @return
     */
    public static String encode(String rawPass) {
        String ret = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(rawPass.getBytes());
            ret = new String(Base64.getUrlEncoder().encodeToString(md.digest()));
            ret = ret.substring(0, ret.length() - 8);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static boolean isPasswordValid(String encPass, String rawPass) {
        if (encPass == null) {
            return false;
        }
        String pass2 = encodePassword(rawPass);
        return encPass.equals(pass2);
    }

    /**
     * 获取文件的md5值
     * @param file
     * @return
     */
    public static String getMD5Code(File file) {
        String value = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            value = bigInt.toString(16);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(Md5.encodePassword("FRI_1408"));
    }


}
