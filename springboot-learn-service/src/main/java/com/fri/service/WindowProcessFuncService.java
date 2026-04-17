package com.fri.service;


import com.alibaba.fastjson.JSON;
import com.fri.domain.scanevent.ScanEventLogDO;
import com.fri.domain.scanevent.ScanIpVO;
import com.fri.utils.CopyClassUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 滑动窗口内复杂事件解析逻辑实现
 *
 * @author wangxiaoming-ghq 2022-06-01
 */
@Slf4j
public class WindowProcessFuncService extends ProcessWindowFunction<ScanEventLogDO, List<ScanEventLogDO>, String, TimeWindow> implements Serializable {
    @Override
    public void process(String s, Context context, Iterable<ScanEventLogDO> iterable, Collector<List<ScanEventLogDO>> collector) {

        //新需求：
        // 需要收集的时候同一台主机的ip，超过100个后生成主动端口扫描，目前是没有判断是否在同一个主机下（根据machineUuid判断）
        // 需要判断同一个主机下，相同的100个ip，但是收集到不同的100个端口，生成主动扫描日志

        // 收集同一台主机，扫描到不同的100个ip,不管端口是否相同集合
        Map<String, Set<ScanIpVO>> scanIpSets = new HashMap<>();

        ScanEventLogDO pushScanEventLogDO;
        int scanNumber = 1;
        ScanEventLogDO iterator = iterable.iterator().next();
        if (ObjectUtils.isNotEmpty(iterator) && ObjectUtils.isNotEmpty(iterator.getScanNumber())) {
            scanNumber = iterator.getScanNumber();
        }
        log.info("规定时间内扫描次数【" + scanNumber + "】");
        for (ScanEventLogDO scanEventLogDO : iterable) {
            log.info("收集到的登录事件【" + scanEventLogDO + "】");
            //开始检测当前窗口内的事件，并将失败的事件收集到ips
            if (StringUtils.isNotEmpty(scanEventLogDO.getMachineUuid()) &&
                    ObjectUtils.isNotEmpty(scanEventLogDO.getObject()) && StringUtils.isNotEmpty(scanEventLogDO.getObject().getIp())) {
                String ipHost = scanEventLogDO.getObject().getIp();
                String ip = ipHost.substring(0, ipHost.indexOf(":"));
                String host = ipHost.substring(ipHost.indexOf(":")+1);
                //先根据机器id从map中获取，来判断是否是同一个发起扫描的主机
                log.info("事件中的主机uuid【" + scanEventLogDO.getMachineUuid() + "】");
                log.info("事件中的ip加端口【" + ipHost + "】");
                Set<ScanIpVO> ipVos = scanIpSets.get(scanEventLogDO.getMachineUuid());
                //如果从map中获取到为空的话，说明不是同一个发起扫描的主机
                if(ObjectUtils.isEmpty(ipVos)){  //将该主机的信息添加到map中，key为主机uuid
                    log.info("不是同一个machine机器：新添加map集合key:{}", scanEventLogDO.getMachineUuid());
                    Set<ScanIpVO> scanIpVos = new HashSet<>();
                    ScanIpVO scanIpVo = new ScanIpVO();
                    scanIpVo.setScanIp(ip);
                    scanIpVo.setScanTime(scanEventLogDO.getScanTime());
                    scanIpVo.setIp(ipHost);
                    scanIpVos.add(scanIpVo);
                    scanIpSets.put(scanEventLogDO.getMachineUuid(),scanIpVos);
                }else {  //如果获取到则对value的集合添加
                    log.info("是同一个machine机器：继续在该key的基础上添加value值");
                    ScanIpVO scanIpVO = new ScanIpVO();
                    scanIpVO.setScanIp(ip);
                    scanIpVO.setScanTime(scanEventLogDO.getScanTime());
                    scanIpVO.setIp(ipHost);
                    ipVos.add(scanIpVO);
                }

                Set<ScanIpVO> hostVo = scanIpSets.get(scanEventLogDO.getMachineUuid() + "+" + ip);
                if(ObjectUtils.isEmpty(hostVo)){
                    log.info("不是同一个machine机器，也不是同一个ip:{}", scanEventLogDO.getMachineUuid() + "+" + scanEventLogDO.getObject().getIp());
                    Set<ScanIpVO> scanIpVos = new HashSet<>();
                    ScanIpVO scanIpVo = new ScanIpVO();
                    scanIpVo.setScanIp(host);
                    scanIpVo.setScanTime(scanEventLogDO.getScanTime());
                    scanIpVo.setIp(ipHost);
                    scanIpVos.add(scanIpVo);
                    scanIpSets.put(scanEventLogDO.getMachineUuid() + "+" + ip,scanIpVos);
                }else {
                    log.info("是同一个machine机器，是同一个ip");
                    ScanIpVO scanIpVO = new ScanIpVO();
                    scanIpVO.setScanIp(host);
                    scanIpVO.setScanTime(scanEventLogDO.getScanTime());
                    scanIpVO.setIp(ipHost);
                    hostVo.add(scanIpVO);
                }
            }

            //如果检测到ip集合已超过指定数，则清空ips集合，进行数据推送，等待下一次检测
            for (Map.Entry<String, Set<ScanIpVO>> entry : scanIpSets.entrySet()) {
                String key = entry.getKey();
                Set<ScanIpVO> v = entry.getValue();
                if (v.size() >= scanNumber) {
                    Set<String> collect = v.stream().map(ScanIpVO::getIp).collect(Collectors.toSet());
                    String other = String.join(",", collect);
                    List<ScanEventLogDO> scanEventLogDOS = new ArrayList<>();
                    pushScanEventLogDO = CopyClassUtils.copy(scanEventLogDO, new ScanEventLogDO());
                    pushScanEventLogDO.setEventId(16000001);
                    pushScanEventLogDO.setEventUuid("00f42401");
                    pushScanEventLogDO.getObject().setOther(other);
                    pushScanEventLogDO.setScanIpVO(v);
                    scanEventLogDOS.add(pushScanEventLogDO);
                    log.info("检测到ip集合已超过指定数,进行主动端口扫描事件数据推送:{}", JSON.toJSONString(pushScanEventLogDO));
                    //将当前登录成功的事件进行收集上报
                    collector.collect(scanEventLogDOS);
                    v.clear();
                }
                log.info("当前machine机器收集--主机名称------不同的ip加端口size数：{}----------{}",key,v.size());
            }

            log.info("收集的--------每个主动扫描机器,对应的被扫主机的-----------map集合:{}", JSON.toJSONString(scanIpSets));
        }
    }

}