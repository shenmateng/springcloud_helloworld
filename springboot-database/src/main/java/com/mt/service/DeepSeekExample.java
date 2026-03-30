package com.mt.service;

import okhttp3.*;

import java.io.IOException;

public class DeepSeekExample {
    public static void main(String[] args) throws IOException {
        // API URL 和请求的密钥
        String apiUrl = "https://api.deepseek.ai/your-endpoint";
        String apiKey = "your-api-key";

        // 创建 OkHttpClient 实例
        OkHttpClient client = new OkHttpClient();

        // 创建请求
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", "Bearer " + apiKey)  // 添加授权头部
                .build();

        // 发起请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // 打印返回的响应
                System.out.println(response.body().string());
            } else {
                System.out.println("请求失败，状态码：" + response.code());
            }
        }
    }
}
