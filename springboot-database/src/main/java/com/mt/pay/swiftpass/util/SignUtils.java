package com.mt.pay.swiftpass.util;

import java.util.*;

/**
 * 签名参数处理工具（从老项目迁移）
 */
public class SignUtils {

    public static boolean checkParam(Map<String, String> params, String key) {
        if (!params.containsKey("sign")) return false;
        String sign = params.get("sign");
        Map<String, String> filtered = paraFilter(params);
        StringBuilder buf = new StringBuilder((filtered.size() + 1) * 10);
        buildPayParams(buf, filtered, false);
        String signReceive = MD5Util.sign(buf.toString(), "&key=" + key, "utf-8");
        return sign.equalsIgnoreCase(signReceive);
    }

    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<>(sArray.size());
        for (String k : sArray.keySet()) {
            String v = sArray.get(k);
            if (v == null || v.isEmpty() || "sign".equalsIgnoreCase(k)) continue;
            result.put(k, v);
        }
        return result;
    }

    public static void buildPayParams(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
        List<String> keys = new ArrayList<>(payParams.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            sb.append(key).append("=");
            sb.append(encoding ? urlEncode(payParams.get(key)) : payParams.get(key));
            sb.append("&");
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
    }

    private static String urlEncode(String str) {
        try {
            return java.net.URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }
}
