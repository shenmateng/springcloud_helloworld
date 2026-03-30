//package com.mt.ai;
//
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.WebSocket;
//
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@Component
//@Slf4j
//public class XunFeiAiUtils {
//
//
//    //生成简答题
//    public String generateShortAnswer(String fileUrl,String number) throws Exception {
////        BigModelNew.isHandlJson=false;
//        BigModelNew.fromMart="[ {\"quContent\": \"这是简答题目\", \"analysis\": \"这是简答题参考答案\"}],生成一行json字符串去除多余的空格";
//        BigModelNew.wsClose=false;
//        BigModelNew.historyList.clear();
//        BigModelNew.NewQuestion = "根据这个文档"+fileUrl+"，帮我出"+number+"道简答题，无需其他回复,直接给我json格式的字符串就行,生成一行json字符串去除多余的空格";
//        // 构建鉴权url
//        String authUrl = BigModelNew.getAuthUrl(BigModelNew.hostUrl, BigModelNew.apiKey, BigModelNew.apiSecret);
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
//        Request request = new Request.Builder().url(url).build();
//        BigModelNew.totalAnswer="";
//        WebSocket webSocket = client.newWebSocket(request, new BigModelNew());
//        String AiResult = "";
//        while (true) {
//            TimeUnit.SECONDS.sleep(1);
//            if (BigModelNew.wsClose){
//                AiResult=BigModelNew.totalAnswer;
//                break;
//            }
//        }
//        return AiResult;
//    }
//
//
//    //    其他题
//    public String generateOther(String fileUrl,String number,String type) throws Exception {
////        BigModelNew.isHandlJson=false;
//        BigModelNew.fromMart="[{{\"quContent\":\"这是题目\",\"analysis\":\"这是题目解析\",\"allOption\":[{\"option\":\"答案1\",\"optionAnalysis\":\"这是正确答案解析\",\"isTrue\":\"1\"},{\"option\":\"答案2\",\"optionAnalysis\":\"这是正确答案解析\",\"isTrue\":\"0\"},{\"option\":\"答案2\",\"optionAnalysis\":\"这是正确答案解析\",\"isTrue\":\"0\"},{\"option\":\"答案4\",\"optionAnalysis\":\"这是正确答案解析\",\"isTrue\":\"0\"}]}}],生成一行json字符串去除多余的空格";
//        BigModelNew.wsClose=false;
//        BigModelNew.historyList.clear();
//        BigModelNew.NewQuestion = "根据这个文档"+fileUrl+"，帮我出"+number+"道"+type+"，无需其他回复,直接给我json格式的字符串就行,生成一行json字符串去除多余的空格";
//        log.info("构建用户语言:  {}",BigModelNew.NewQuestion);
//        // 构建鉴权url
//        String authUrl = BigModelNew.getAuthUrl(BigModelNew.hostUrl, BigModelNew.apiKey, BigModelNew.apiSecret);
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
//        Request request = new Request.Builder().url(url).build();
//        BigModelNew.totalAnswer="";
//        WebSocket webSocket = client.newWebSocket(request, new BigModelNew());
//        String AiResult = "";
//        while (true) {
//            TimeUnit.SECONDS.sleep(1);
//            if (BigModelNew.wsClose){
//                AiResult=BigModelNew.totalAnswer;
//                break;
//            }
//        }
//        System.out.println("---------------------------结果---------------------");
//        System.out.println(AiResult);
//        return AiResult;
//    }
//    /**
//     * 处理json格式
//     */
//    public String generateJson(String fileUrl,String number,String type) throws Exception {
////        BigModelNew.isHandlJson=true;
//        BigModelNew.wsClose=false;
//        BigModelNew.historyList.clear();
//        BigModelNew.NewQuestion = "根据这个文档"+fileUrl+"，帮我出"+number+"道"+type+"，无需其他回复,直接给我json格式的字符串就行,生成一行json字符串去除多余的空格";
//        log.info("构建用户语言:  {}",BigModelNew.NewQuestion);
//        // 构建鉴权url
//        String authUrl = BigModelNew.getAuthUrl(BigModelNew.hostUrl, BigModelNew.apiKey, BigModelNew.apiSecret);
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
//        Request request = new Request.Builder().url(url).build();
//        BigModelNew.totalAnswer="";
//        WebSocket webSocket = client.newWebSocket(request, new BigModelNew());
//        String AiResult = "";
//        while (true) {
//            TimeUnit.SECONDS.sleep(1);
//            if (BigModelNew.wsClose){
//                AiResult=BigModelNew.totalAnswer;
//                break;
//            }
//        }
//        System.out.println("---------------------------结果---------------------");
//        System.out.println(AiResult);
//        return AiResult;
//    }
//}