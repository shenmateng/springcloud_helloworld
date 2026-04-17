package com.fri.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: chenyuyin
 * @DateTime: 2021/1/2 17:13
 * @Description:
 */
public class DateUtil {

    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDD = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期时间<br>
     * 格式 yyyyMMdd HHmmss
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDateTime(Date date) {
        return new SimpleDateFormat(YYYYMMDD_HHMMSS).format(date);
    }

    /**
     * 格式化日期时间<br>
     * 格式 yyyyMMdd HHmmss
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String transferLongToDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
        return sdf.format(date);
    }

    public static String parseDateToString(Long time) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
        sdf.setTimeZone(timeZone);
        Date date = new Date(time);
        return sdf.format(date);
    }

    /**
     * 格式化日期
     * 格式 yyyyMMdd
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat(YYYYMMDD).format(date);
    }

    //将带有T Z的时间字符串转换成yyyy-MM-dd HH:mm:ss
    public static String convertDate(String strDate) {
        String str = "";
        try {
            strDate = strDate.replace("T", " ").replace("Z", "");
            SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
            Date parse = sdf.parse(strDate);
            return sdf.format(parse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    //将带有T Z的时间字符串转换成yyyy-MM-dd HH:mm:ss +8小时
    public static String convertDate2(String strDate) {
        String str = "";
        try {
            strDate = strDate.replace("T", " ").replace("Z", "");
            SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
            Date parse = sdf.parse(strDate);
            long rightTime = (parse.getTime() + 8 * 60 * 60 * 1000);
            return sdf.format(rightTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    //将带有T时间字符串转换成yyyy-MM-dd HH:mm:ss
    public static String convertTDate(String strDate) {
        String str = "";
        try {
            strDate = strDate.replace("T", " ");
            SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
            Date parse = sdf.parse(strDate);
            return sdf.format(parse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
}
