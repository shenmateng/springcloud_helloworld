package com.mt.controller;

import com.mt.ResponseResult.ResponseResult;
import com.mt.database.ZcMachine;
import com.mt.mapper.onnew.ZcMachineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * zc_machine 测试 Controller
 * 用于测试 Flink CDC 监听表变更
 */
@Slf4j
@RestController
@RequestMapping("/api/zc-machine")
public class ZcMachineTestController {

    @Resource
    private ZcMachineMapper zcMachineMapper;

    /**
     * 插入一条测试数据（用于触发 Flink CDC）
     *
     * POST /api/zc-machine/insert
     */
    @PostMapping("/insert")
    public ResponseResult<ZcMachine> insert(@RequestBody(required = false) ZcMachine req) {
        // 如果没传参数，自动生成一条测试数据
        if (req == null) {
            req = new ZcMachine();
        }
        if (req.getUuid() == null || req.getUuid().isEmpty()) {
            req.setUuid(UUID.randomUUID().toString().replace("-", ""));
        }
        if (req.getMachineName() == null) {
            req.setMachineName("测试服务器-" + System.currentTimeMillis());
        }
        if (req.getOnlineStatus() == null) {
            req.setOnlineStatus(1);
        }
        if (req.getOsType() == null) {
            req.setOsType(1);
        }
        if (req.getIfDelete() == null) {
            req.setIfDelete(0);
        }
        if (req.getInstallTime() == null) {
            req.setInstallTime(new Date());
        }
        if (req.getUpdateTime() == null) {
            req.setUpdateTime(new Date());
        }

        log.info("插入zc_machine测试数据，uuid: {}", req.getUuid());
        zcMachineMapper.insert(req);
        return ResponseResult.success(req);
    }

    /**
     * 快速插入（不需要传任何参数）
     *
     * GET /api/zc-machine/quick-insert
     */
    @GetMapping("/quick-insert")
    public ResponseResult<ZcMachine> quickInsert() {
        return insert(null);
    }

    /**
     * 根据 uuid 查询
     *
     * GET /api/zc-machine/{uuid}
     */
    @GetMapping("/{uuid}")
    public ResponseResult<ZcMachine> queryByUuid(@PathVariable String uuid) {
        ZcMachine result = zcMachineMapper.queryByUuid(uuid);
        return ResponseResult.success(result);
    }
}
