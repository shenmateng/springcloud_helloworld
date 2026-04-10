package com.mt.pay.swiftpass.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mt.ResponseResult.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 微信公众号网页授权 Controller
 *
 * 流程：
 * 1. 前端访问 /api/wx-oauth/authorize，后端重定向到微信授权页
 * 2. 用户同意后微信回调 /api/wx-oauth/callback?code=xxx
 * 3. 后端用 code 换取 openId 返回给前端
 */
@Slf4j
@RestController
@RequestMapping("/api/wx-oauth")
public class WxOAuthController {

    // 公众号 appId（在 Consul 配置）
    @Value("${wx.oauth.app-id:}")
    private String appId;

    // 公众号 appSecret（在 Consul 配置）
    @Value("${wx.oauth.app-secret:}")
    private String appSecret;

    // 授权成功后前端跳转地址（带上 openId 参数）
    @Value("${wx.oauth.redirect-front-url:}")
    private String redirectFrontUrl;

    /**
     * 第一步：跳转微信授权页
     * 前端直接访问这个地址即可
     *
     * GET /api/wx-oauth/authorize
     */
    @GetMapping("/authorize")
    public void authorize(HttpServletResponse response) throws IOException {
        // 授权回调地址（必须在公众号后台配置白名单）
        String callbackUrl = URLEncoder.encode(
                "https://3df8-222-90-117-184.ngrok-free.app/api/wx-oauth/callback",
                "UTF-8"
        );

        // snsapi_base：静默授权，只获取 openId，用户无感知
        // snsapi_userinfo：需用户手动同意，可获取昵称头像等
        String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"
                + "?appid=" + appId
                + "&redirect_uri=" + callbackUrl
                + "&response_type=code"
                + "&scope=snsapi_base"
                + "&state=pay"
                + "#wechat_redirect";

        log.info("跳转微信授权: {}", authUrl);
        response.sendRedirect(authUrl);
    }

    /**
     * 第二步：微信授权回调，用 code 换取 openId
     * 微信会带着 code 回调这个地址
     *
     * GET /api/wx-oauth/callback?code=xxx&state=pay
     */
    @GetMapping("/callback")
    public void callback(@RequestParam String code,
                         @RequestParam(required = false) String state,
                         HttpServletResponse response) throws IOException {
        log.info("收到微信授权回调，code: {}", code);

        // 用 code 换取 access_token 和 openId
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
                + "?appid=" + appId
                + "&secret=" + appSecret
                + "&code=" + code
                + "&grant_type=authorization_code";

        try {
            String result = sendGet(url);
            log.info("换取token响应: {}", result);

            JSONObject json = JSON.parseObject(result);
            String openId = json.getString("openid");

            if (openId == null) {
                log.error("获取openId失败: {}", result);
                response.getWriter().write("获取openId失败: " + result);
                return;
            }

            log.info("获取openId成功: {}", openId);

            // 把 openId 带给前端（跳转到前端支付页面）
            String frontUrl = redirectFrontUrl + "?openId=" + openId + "&state=" + state;
            response.sendRedirect(frontUrl);

        } catch (Exception e) {
            log.error("获取openId异常", e);
            response.getWriter().write("系统异常: " + e.getMessage());
        }
    }

    /**
     * 直接返回 openId（调试用，生产环境建议删除）
     *
     * GET /api/wx-oauth/get-openid?code=xxx
     */
    @GetMapping("/get-openid")
    public ResponseResult<String> getOpenId(@RequestParam String code) {
        log.info("获取openId，code: {}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
                + "?appid=" + appId
                + "&secret=" + appSecret
                + "&code=" + code
                + "&grant_type=authorization_code";

        try {
            String result = sendGet(url);
            JSONObject json = JSON.parseObject(result);
            String openId = json.getString("openid");

            if (openId == null) {
                return ResponseResult.error(400, "获取openId失败: " + result);
            }
            return ResponseResult.success(openId);
        } catch (Exception e) {
            return ResponseResult.error(500, "系统异常: " + e.getMessage());
        }
    }

    /**
     * 简单 GET 请求
     */
    private String sendGet(String url) throws Exception {
        java.net.URL obj = new java.net.URL(url);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        java.io.BufferedReader in = new java.io.BufferedReader(
                new java.io.InputStreamReader(con.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }
}
