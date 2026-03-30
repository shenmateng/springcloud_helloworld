package com.mt.service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.mt.utils.G01JSONUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2025/4/10 16:05
 * @Version:
 * @Description:
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
public class test0021 {


    public static void main(String[] args) {




        //设置map默认开关状态
        Map<String, String> mapList = new LinkedHashMap<>();
        //应用网络访问过滤开关，大开关
        mapList.put("15", "0");
        //进程外联控制开关
        mapList.put("0", "0");
        //进程外联监控开关
        mapList.put("1", "0");

        //ip层流量控制开关，大开关
        mapList.put("9", "0");
        //ASP模型流量过滤开关
        mapList.put("10", "0");
        //DNS解析监控开关
        mapList.put("6", "0");

        //文件防护开关，大开关
        mapList.put("13", "0");
        //文件读防护开关
        mapList.put("14", "0");

        //进程创建监控开关，大开关
        mapList.put("3", "0");
        //进程创建日志
        mapList.put("23", "0");

        //进程结束监控开关，大开关
        mapList.put("4", "0");

        //进程防护开关
        mapList.put("8", "0");

        //应用白名单控制开关
        mapList.put("16", "0");

        //动态链接库/驱动执行控制
        mapList.put("17", "0");

        //文件哈希黑名单
        mapList.put("18", "0");

        Map<String,String> map1 = new HashMap<>();
        map1.put("0","1");
        String jsonString = G01JSONUtils.toJSONString(map1);
        Gson gson = new Gson();
        Map<String, String> maps = gson.fromJson(jsonString, Map.class);


        String ss = "25b1:050a:ffff:0000:0000:0000:0000:0000:11126";
        // 找到最后一个冒号的位置
        int lastColonIndex = ss.lastIndexOf(":");
        // 提取冒号后面的部分
        String result = ss.substring(lastColonIndex + 1);

        System.out.println(result);

        mapList.putAll(maps);
        mapList.putAll(maps);System.out.println(mapList);

        Date date = new Date();
        Date date1 = DateUtils.addDays(date, -1);
        System.out.println(date1);
        String startDateLastStr = parseDateToStr("yyyy-MM-dd", date1);
        System.out.println(startDateLastStr);


    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }
}
