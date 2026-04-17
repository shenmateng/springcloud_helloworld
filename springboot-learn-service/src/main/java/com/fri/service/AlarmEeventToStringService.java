package com.fri.service;

import com.alibaba.fastjson.JSON;
import com.fri.domain.scanevent.BaseBean;
import com.fri.domain.scanevent.ScanEventLogDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.List;

/**
 *  逻辑统计场景告警存入kafka
 *  @author wangxiaoming-ghq 2022-06-01
 */
@Slf4j
public   class AlarmEeventToStringService extends BaseBean implements MapFunction<List<ScanEventLogDO>, String> {

    @Override
    public String map(List<ScanEventLogDO> scanEventLogDOs) throws Exception {
        log.info("推送kafka消息体：【" + scanEventLogDOs + "】");
        return JSON.toJSONString(scanEventLogDOs);
    }
}