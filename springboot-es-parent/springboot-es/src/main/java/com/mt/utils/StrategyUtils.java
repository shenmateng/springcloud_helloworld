package com.mt.utils;



import com.alibaba.fastjson.JSON;


import java.util.*;


/**
 * 策略工具类
 *
 * @author zhang
 */
public class StrategyUtils {


    /**
     * 转换json
     *
     * @param map
     * @return
     */
    public static String toJson(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>(16);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String[] arr = entry.getKey().split("#");
            int n = arr.length;
            Map<String, Object> tempMap = result;
            for (int j = 0; j < n; j++) {
                if (j == (n - 1)) {
                    if (!tempMap.containsKey(arr[j])) {
                        tempMap.put(arr[j], entry.getValue());
                    }
                } else {
                    if (!tempMap.containsKey(arr[j])) {
                        Map<String, Object> newMap = new HashMap<>(16);
                        tempMap.put(arr[j], newMap);
                        tempMap = newMap;
                    } else {
                        tempMap = (Map<String, Object>) tempMap.get(arr[j]);
                    }
                }
            }
        }
        return JSON.toJSONString(result);
    }



}
