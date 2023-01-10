package com.mt.utils;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

/**
 * 数字工具类
 */
public abstract class DigitUtils {

    public static String parseNumberStr(String src){
        String regex="\\d+(?:\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static Double parsePercent(String src) {
        Double val = null;
        try {
            String numberStr = DigitUtils.parseNumberStr(src);
            val = Double.parseDouble(numberStr);
            String endStr = src.substring(numberStr.length());
            if ("%".equals(endStr)) {
                BigDecimal b = BigDecimal.valueOf(val /100.0);
                val = b.setScale(4, RoundingMode.HALF_UP).doubleValue();
            } else if ("‰".equals(endStr)) {
                BigDecimal b =BigDecimal.valueOf(val /1000.0);
                val = b.setScale(5, RoundingMode.HALF_UP).doubleValue();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return val;
    }


    /**
     * 取整有余数进一
     * @return
     */
    public static long roundingWithRemainderIntoOne(long a,long b){
        long c = a%b == 0 ? (a/b) : (a/b)+1;
        return c;
    }

    public static double scale(double d,int scale){
        BigDecimal bd = new BigDecimal(d).setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static<T> long getCrc32(T t){
        CRC32 crc32 = new CRC32();
        crc32.update(JSON.toJSONString(t).getBytes());
        return crc32.getValue();
    }

    public static<T> long getCrc32(String str){
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        return crc32.getValue();
    }

    public static void main(String[] args){
        Double val = DigitUtils.parsePercent("3.56%");
        System.out.println(val);
        System.out.println(DigitUtils.scale(5.986,2));
    }
}
