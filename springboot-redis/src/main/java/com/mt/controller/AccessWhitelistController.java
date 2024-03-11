package com.mt.controller;


import com.mt.database.AccessWhiteIpsVO;
import com.mt.service.AccessWhiteListService;
import com.mt.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2023/6/26 11:07
 * @Version: 2.1.7
 * @Description: 管理中心访问白名单Controller
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

@Controller
@Slf4j
@RequestMapping("/agentSettingSrv" + "/accessWhitelistController")
public class AccessWhitelistController {

    @Autowired
    private AccessWhiteListService accessWhiteListService;

    /**
     * 防护白名单模板列表
     * @return 模板列表
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public Result addBlackWhiteIpsTemplate() throws ParseException {
        AccessWhiteIpsVO accessWhiteIpsVO = accessWhiteListService.list();
        return new Result().setData(accessWhiteIpsVO);
    }

    /**
     * 防护白名单开关控制
     *
     * @param switchStatus 请求参数
     * @return 返回结果
     */
    @PostMapping(value = "/updateStatus")
    @ResponseBody
    public Result switchStatus(Integer switchStatus)  {
        accessWhiteListService.updateStatus(switchStatus);
        return new Result();
    }


    /**
     * 添加编辑白名单
     *
     * @param accessWhiteIpsVO 请求参数
     * @return 返回结果
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public Result addAccessWhitelist(@RequestBody @Valid AccessWhiteIpsVO accessWhiteIpsVO) throws Exception {
        accessWhiteListService.addAccessWhitelist(accessWhiteIpsVO);
        return new Result();
    }



}
