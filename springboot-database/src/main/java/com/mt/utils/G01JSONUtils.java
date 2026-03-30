package com.mt.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author mzj
 * @date 2021/7/8 14:37
 */
public class G01JSONUtils {

    public static JSONObject putObj(String k,Object v){
        JSONObject obj = new JSONObject();
        obj.put(k, v);
        return obj;
    }

    public static String putStr(String k,Object v){
        JSONObject obj = new JSONObject();
        obj.put(k, v);
        return obj.toJSONString();
    }

    public static String toJSONString(Object o){
        return JSONObject.toJSONString(o);
    }
}
