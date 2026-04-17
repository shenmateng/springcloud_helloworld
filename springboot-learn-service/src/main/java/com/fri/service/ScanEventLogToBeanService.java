package com.fri.service;


import com.alibaba.fastjson.JSONObject;
import com.fri.domain.scanevent.BaseBean;
import com.fri.domain.scanevent.ScanEventLogDO;
import com.fri.utils.CopyClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;


/**
 * @author ：mateng
 * @version ：2.1.5.2
 * @program ：learn-service
 * @date ：Created in 2023/05/17 16:58
 * @description ：kafka消息持久化
 */
@Slf4j
public class ScanEventLogToBeanService extends BaseBean implements MapFunction<String, ScanEventLogDO> {
    @Override
    public ScanEventLogDO map(String logJson) throws Exception {
        log.info("没有转换为json之前的字符串数据：【" + logJson + "】");
        ScanEventLogDO scanEventLogDO = new ScanEventLogDO();
        if (StringUtils.isEmpty(logJson)) {
            return scanEventLogDO;
        }
        ScanEventLogDO scanEventObj;
        try {
            scanEventObj = JSONObject.parseObject(logJson, ScanEventLogDO.class);
        } catch (Exception e) {
            System.out.println("parse error : " + logJson);
            return scanEventLogDO;
        }
        log.info("转换为json数据：【" + scanEventObj + "】");
        scanEventLogDO = CopyClassUtils.copy(scanEventObj, new ScanEventLogDO());

        log.info("最后封装对象：【" + scanEventLogDO + "】");
        return scanEventLogDO;
    }
}