package com.fri.common;

import com.alibaba.fastjson.JSONObject;
import com.fri.constant.Constant;
import com.fri.domain.EventLog;
import com.fri.domain.SystemFortLockRaw;
import com.fri.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-9 10:29
 * @Description:
 */
@Slf4j
public class SystemFortLockMapFunction implements MapFunction<String, List<SystemFortLockRaw>> {

    @Override
    public List<SystemFortLockRaw> map(String value) {
        List<SystemFortLockRaw> systemFortLockRaws = new ArrayList<>();
        if (StringUtils.isEmpty(value)) {
            return systemFortLockRaws;
        }
        List<EventLog> eventLogs;
        try {
            eventLogs = JSONObject.parseArray(value, EventLog.class);
        } catch (Exception e) {
            System.out.println("parse error : " + value);
            return systemFortLockRaws;
        }

        for (EventLog eventLog : eventLogs) {
            SystemFortLockRaw systemFortLockRaw = new SystemFortLockRaw();
            systemFortLockRaw.setId(UUIDUtil.get());
            if (eventLog == null || eventLog.getEventId() == null || StringUtils.isEmpty(eventLog.getMachineUuid()) || eventLog.getSubject() == null || StringUtils.isEmpty(eventLog.getSubject().getProcess())
                    || eventLog.getObject() == null || eventLog.getStandardTimestamp() == null) {
                continue;
            }
            systemFortLockRaw.setMachineUuid(eventLog.getMachineUuid());
            systemFortLockRaw.setSubject(eventLog.getSubject().getProcess().replaceAll("\\\\", "/").replaceAll("//", "/"));
            boolean isOpFile = Objects.equals(eventLog.getEventId(), 0);
            if (isOpFile) {
                systemFortLockRaw.setObject(eventLog.getObject().getFile().replaceAll("\\\\", "/").replaceAll("//", "/"));
            } else if(!StringUtils.isEmpty(eventLog.getObject().getUrl())) {
                systemFortLockRaw.setObject(eventLog.getObject().getUrl().replaceAll("\\\\", "/").replaceAll("//", "/"));
            } else if (!StringUtils.isEmpty(eventLog.getObject().getProc())) {
                systemFortLockRaw.setObject(eventLog.getObject().getProc().trim().replaceAll("\\\\", "/").replaceAll("//", "/"));
            }
            systemFortLockRaw.setRights(isOpFile ? (Objects.equals(eventLog.getOperationExtra(), "read") ? "r" : "w") : "x");
            systemFortLockRaw.setCreateTime(new Date(eventLog.getStandardTimestamp()));

            String pattern = "^[A-z]:/(.+?)*$";
            boolean isWinFile = isOpFile && systemFortLockRaw.getObject().matches(pattern);
            systemFortLockRaw.setOsType(isWinFile ? Constant.OS_WIN : Constant.OS_LINUX);
            if (isWinFile) { systemFortLockRaw.setObject("/" + systemFortLockRaw.getObject()); }
            systemFortLockRaws.add(systemFortLockRaw);
        }
        return systemFortLockRaws;
    }
}
