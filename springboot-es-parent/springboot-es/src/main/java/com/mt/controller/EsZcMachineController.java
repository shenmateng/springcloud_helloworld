package com.mt.controller;


import com.mt.bean.Result;
import com.mt.cnnotation.JowtoResponseBody;
import com.mt.database.GetMachinesByUserUuidP;
import com.mt.database.es.EsZcMachine;
import com.mt.serivce.EsZcMachineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assetSrv" + "/machineController")
public class EsZcMachineController {


    @Resource
    private EsZcMachineService machineService;

    /**
     * 给定userUuids和tagUuids获取机器list
     * @param getMachinesByUserUuidP 请求入参
     * @return 实体对象
     */
    @JowtoResponseBody
    @PostMapping("/getMachinesByUserUuid")
    public Result getMachinesByUserUuid(@RequestBody @Valid GetMachinesByUserUuidP getMachinesByUserUuidP) throws Exception {
        List<EsZcMachine> machines = machineService.getMachinesByUserUuid(getMachinesByUserUuidP);
        return new Result().setData(machines);
    }

}
