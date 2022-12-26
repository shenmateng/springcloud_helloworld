package com.mt.controller;

import com.mt.database.MachineDetailVO;
import com.mt.database.StrategyDO;
import com.mt.serivce.SystemFortLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2022/12/26 14:06
 * @Version: 2.1.6
 * @Description: 系统堡垒锁controller
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
@RestController
@RequestMapping("/springboot" + "/systemFortLock")
public class SystemFortLockController {

    @Resource
    private SystemFortLockService systemFortLockService;


    /**
     * 执行策略
     */
    @RequestMapping(value = "/executeStrategy")
    public void executeStrategy(){
        systemFortLockService.executeStrategy();
    }

    /**
     * 获取增量机器个数
     * @param strategyDO 请求参数
     * @return 响应对象
     */
    @RequestMapping(value = "/selectMachineCount")
    public MachineDetailVO selectMachineCount(@RequestBody @Valid StrategyDO strategyDO){
        return systemFortLockService.selectMachineCount(strategyDO);
    }


}
