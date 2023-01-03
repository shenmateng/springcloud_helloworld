package com.mt.serivce;

import com.alibaba.fastjson.JSON;

import com.mt.database.MachineDO;
import com.mt.database.MachineDetailVO;
import com.mt.database.StrategyDO;
//import com.mt.utils.MachineUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class SystemFortLockService {

//    @Resource
//    private MachineUtils machineUtils;


    public void executeStrategy() {

//        StrategyDO strategyDO = new StrategyDO();
//        strategyDO.setStrategyId("celuoId");
//        strategyDO.setUid("mateng");
//        List<String> onLineMachineList = new ArrayList<>();
//        onLineMachineList.add("一号id");
//        List<String> offLineMachineList = new ArrayList<>();
//        offLineMachineList.add("离线一号id");
//        machineUtils.deleteStrategyDigests(strategyDO.getStrategyId(),strategyDO.getUid());
//        machineUtils.addBinaryDigestToStrategy(strategyDO.getStrategyId(),strategyDO.getUid(),JSON.toJSONString(onLineMachineList),JSON.toJSONString(offLineMachineList),"系统堡垒锁");
    }

    /**
     * 获取增加机器个数
     * @param strategyDO 请求参数
     * @return 响应对象
     */
    public MachineDetailVO selectMachineCount(StrategyDO strategyDO) {

        MachineDetailVO machineDetailVO = new MachineDetailVO();
        //通过es获取存量在线机器id和存量离线机器id
//        MachineDO machineDO = machineUtils.selectStrategyHashCountInfo(strategyDO.getStrategyId(),strategyDO.getUid());
//        machineDetailVO.setMachineCount(machineDO.getMachineList().size());
//        machineDetailVO.setMachineList(machineDO.getMachineList());
//        machineDetailVO.setOffMachineList(machineDO.getOffMachineList());
        return machineDetailVO;
    }


}
