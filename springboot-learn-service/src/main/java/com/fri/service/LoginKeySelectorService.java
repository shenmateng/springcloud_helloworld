package com.fri.service;


import com.fri.domain.scanevent.BaseBean;
import com.fri.domain.scanevent.ScanEventLogDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.functions.KeySelector;

/**
 * CEP 编程，需要进行key选取
 */
@Slf4j
public class LoginKeySelectorService extends BaseBean implements KeySelector<ScanEventLogDO, String> {
    @Override
    public String getKey(ScanEventLogDO scanEventLogDO) {
        log.info("设置主键key：【" + scanEventLogDO.getMachineUuid() + "】");
        return scanEventLogDO.getMachineUuid() + "@" + scanEventLogDO.getDay();
    }
}
