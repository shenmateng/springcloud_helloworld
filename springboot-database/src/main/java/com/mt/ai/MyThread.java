package com.mt.ai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2025/2/24 10:48
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

@Slf4j
@Service
@Data
public class MyThread extends Thread{


    private WebSocket webSocket;


    public static String fromMart="";

    public static final String appid = "55e";

    public static final String domain = "4.0Ultra";

    public static List<RoleContent> historyList=new ArrayList<>(); // 对话历史存储集合

    // 环境治理的重要性  环保  人口老龄化  我爱我的祖国
    public static  String NewQuestion = "";

    // 个性化参数
    private Boolean wsCloseFlag;

    public static Boolean wsClose=false;

    public void run() {
        try {
            JSONObject requestJson=new JSONObject();

            JSONObject header=new JSONObject();  // header参数
            header.put("app_id",appid);
            header.put("uid", UUID.randomUUID().toString().substring(0, 10));

            JSONObject parameter=new JSONObject(); // parameter参数
            JSONObject chat=new JSONObject();
            chat.put("domain",domain);
            chat.put("temperature",0.8);
            chat.put("max_tokens",8192);
            parameter.put("chat",chat);

            JSONObject payload=new JSONObject(); // payload参数
            JSONObject message=new JSONObject();
            JSONArray text=new JSONArray();

            // 历史问题获取
            if(historyList.size()>0){
                for(RoleContent tempRoleContent:historyList){
                    text.add(JSON.toJSON(tempRoleContent));
                }
            }
            //               调优角色设置
            RoleContent roleContentSys = new RoleContent();
            roleContentSys.role = "system";
            roleContentSys.content = "你是一个老师,负责根据用户提供的在线pdf,word文档进行阅读分析出题(选择题、判断题、多选题、简单题)，生成用户需要的json字符串格式，无需其他回复直接生成json格式字符串返回，格式为：" + fromMart+",键和值必须用双引号括起来：所有字符串（包括键名）都必须使用双引号 \"，不能使用单引号 '";
            text.add(JSON.toJSON(roleContentSys));

            // 最新问题
            RoleContent roleContent=new RoleContent();
            roleContent.role="user";
            roleContent.content=NewQuestion;
            text.add(JSON.toJSON(roleContent));
            historyList.add(roleContent);

            message.put("text",text);
            payload.put("message",message);


            requestJson.put("header",header);
            requestJson.put("parameter",parameter);
            requestJson.put("payload",payload);
            System.err.println(requestJson); // 可以打印看每次的传参明细
            webSocket.send(requestJson.toString());
            // 等待服务端返回完毕后关闭
            while (true) {
                // System.err.println(wsCloseFlag + "---");
                Thread.sleep(200);
                if (wsCloseFlag) {
                    break;
                }
            }
            webSocket.close(1000, "");
            wsClose=true;
            log.error("-------------大模型调用结束--------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
