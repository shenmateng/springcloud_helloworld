package com.mt.service;

import com.alibaba.fastjson.JSON;
import com.mt.database.host.CommonFileCondition;
import com.mt.database.host.CommonFileTrustConfig;
import com.mt.database.host.SetSwitchVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2025/9/22 16:03
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
public class testhost {

    public static void main(String[] args) {


        List<CommonFileTrustConfig> commonFileTrustConfigs = new ArrayList<>();
        CommonFileTrustConfig commonFileTrustConfig = new CommonFileTrustConfig();

        List<CommonFileCondition> condition = new ArrayList<>();
        //进程组装
        CommonFileCondition commonFileCondition = new CommonFileCondition();
        commonFileCondition.setField("ProcessPath");
        commonFileCondition.setMatchType("wildcard");
        commonFileCondition.setValue("www.mt.com");
        condition.add(commonFileCondition);

        //文件组装
        CommonFileCondition commonFileConditionFile = new CommonFileCondition();
        commonFileConditionFile.setField("FilePath");
        commonFileConditionFile.setMatchType("wildcard");
        commonFileConditionFile.setValue("wenjian.txt");
        condition.add(commonFileConditionFile);

        commonFileTrustConfig.setCondition(condition);
        commonFileTrustConfigs.add(commonFileTrustConfig);

        SetSwitchVo sysVo = new SetSwitchVo();
        sysVo.getData().put("whitelistRule", commonFileTrustConfigs);

        System.out.println(JSON.toJSONString(sysVo));
        System.out.println("完成");


    }
}
