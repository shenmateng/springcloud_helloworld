package com.fri.utils;

import com.fri.domain.scanevent.BaseBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * 2022年6月17日15:35:10
 * @author mateng
 * 时间处理全局工具类
 */
@Slf4j
public class TimeUtils extends BaseBean {

    /**
     * 将UTC时间转换为北京时间的timestamp
     * @param UTCDatetimeStr:标准UTC时间
     * @return timestamp:标准北京时间
     */
    public static long switchUTCToBeijingTimestamp(String UTCDatetimeStr) {
        String UTCDatetime = UTCDatetimeStr.replace("Z", " UTC");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date time = null;
        try {
            time = format.parse(UTCDatetime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert time != null;

        return time.getTime();

    }

    public static long stringToTimestamp(String dateString) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(dateString);
            log.info("日志推送时间：【" + date.getTime()*1001L + "】");
            return date.getTime()*1001L;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long longToTimestamp(Long dateLong) {
        try {
            log.info("日志推送时间：【" + dateLong + "】");
            return dateLong ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     *
     * @param UTCDatetimeStr:标准UTC日期时间 yyyy-MM-dd HH:mm:ss
     * @return beijingDatetime:标准北京日期时间 YYYY-MM-DD HH:MM:SS
     */
    public static String switchUTCToBeijingDateTime(String UTCDatetimeStr) {
        String UTCDatetime = UTCDatetimeStr.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time;
        String beijingDatetime = null;
        try {
            time = format.parse(UTCDatetime);
            beijingDatetime = defaultFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beijingDatetime;
    }



    @Test
    public void timeTest(){
        String UTC = "2023-05-16T09:35:30.465Z";
        String UTCTime = "2022-04-20T09:35:33.465Z";
        String UTCTime1 = "2022-04-20T09:58:13.647Z";
        String dateString = "2023-05-16 17:02:40";
        //System.out.println(switchUTCToBeijingTime(switchUTCToBeijingDateTime(UTCTime)));
        System.out.println(switchUTCToBeijingTimestamp(UTCTime)/1000L);
        System.out.println(switchUTCToBeijingTimestamp(UTC)/1000L);
        System.out.println(switchUTCToBeijingTimestamp(UTCTime1));

        System.out.println(switchUTCToBeijingTimestamp(UTCTime)/1000L - switchUTCToBeijingTimestamp(UTC)/1000L);
        System.out.println(switchUTCToBeijingDateTime(UTCTime));
        System.out.println(switchUTCToBeijingDateTime(UTC));
        long l = stringToTimestamp(dateString);
        System.out.println(l);
        Timestamp.valueOf(String.valueOf(switchUTCToBeijingDateTime(UTCTime)));

        System.out.println( Timestamp.valueOf(String.valueOf(switchUTCToBeijingDateTime(UTCTime))).getTime()/1000L);
    }

    /**
     * @param dateStr:时间字符串
     * @param formatStr：时间
     * @return long:时间戳
     */
    public static Long string2Millis(String dateStr, String formatStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
            return simpleDateFormat.parse(dateStr).getTime();
        } catch (Exception e) {
            return 0L;
        }
    }




    @Test
    public void testlong(){

        Long timestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime time2 =LocalDateTime.ofEpochSecond(1655177452000L/1000L,0,ZoneOffset.ofHours(8));

        System.out.println(LocalDateTime.now());
        System.out.println(timestamp);
        System.out.println(time2);

    }

    public static String switchLongTsToStringDateTime(long ts){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
        String timeText=format.format(ts);

        return timeText;
    }

    public static long switchToEpochMil(LocalDateTime dt) {
        ZoneOffset zoneOffset8 = ZoneOffset.of("+8");
        return dt.toInstant(zoneOffset8).toEpochMilli();
    }

}
