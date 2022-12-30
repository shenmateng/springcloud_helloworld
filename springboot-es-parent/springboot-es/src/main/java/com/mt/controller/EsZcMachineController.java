package com.mt.controller;


import com.mt.bean.Result;
import com.mt.cnnotation.JowtoResponseBody;
import com.mt.database.GetMachinesByUserUuidP;
import com.mt.database.es.EsZcMachine;
import com.mt.serivce.EsZcMachineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/assetSrv" + "/machineController")
public class EsZcMachineController {


    @Resource
    private EsZcMachineService machineService;

    /**
     * 给定userUuids和tagUuids获取机器list
     * @param getMachinesByUserUuidP 请求入参
     * @return
     */
    @JowtoResponseBody
    @RequestMapping("/getMachinesByUserUuid")
    public Result getMachinesByUserUuid(@RequestBody @Valid GetMachinesByUserUuidP getMachinesByUserUuidP) throws Exception {
        List<EsZcMachine> machines = machineService.getMachinesByUserUuid(getMachinesByUserUuidP);
        return new Result().setData(machines);
    }

}
