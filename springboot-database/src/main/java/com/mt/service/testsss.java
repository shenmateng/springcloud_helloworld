package com.mt.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.content.MachineSwitchEnum;
import com.mt.database.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.list.AbstractLinkedList;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2024/4/9 11:37
 * @Version:
 * @Description:
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
public class testsss {

    public static void main(String[] args) throws JsonProcessingException {
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> map2 = new HashMap<>();
        Map<String,Map<String,Integer>> maps = new HashMap<>();
        map.put("mateng",0);
        map.put("mateng1",2);
        maps.put("机器1",map);
        map.get(2);
        Result result =new Result();
        result.setData(maps);

                Map<String,Integer> mapss = new LinkedHashMap<>();
        Map<String, Integer> stringIntegerMap = MachineSwitchEnum.allCode();
        stringIntegerMap.put("G01_FHKG_17",4);
        System.out.println(stringIntegerMap);

        List<MachineGetResultCheckVO> resultCheckVOS = new ArrayList<>();
        MachineGetResultCheckVO machineGetResultCheckVO = new MachineGetResultCheckVO();
        MachineGetResultCheckVO machineGetResultCheckVO1 = new MachineGetResultCheckVO();

        List<AttackProtectItem> attackProtectList = new ArrayList<>();
        List<AttackProtectItem> attackProtectList1 = new ArrayList<>();
        AttackProtectItem attackProtectItem = new AttackProtectItem();
        AttackProtectItem attackProtectItem1 = new AttackProtectItem();
        attackProtectItem.setName("test1");
        attackProtectItem.setTplId("201");
        attackProtectItem.setStatus(1);
        attackProtectItem.setRemark("测试1");
        attackProtectItem1.setName("test2");
        attackProtectItem1.setTplId("202");
        attackProtectItem1.setStatus(0);
        attackProtectItem1.setRemark("测试2");
        attackProtectList.add(attackProtectItem);
        attackProtectList1.add(attackProtectItem1);
        machineGetResultCheckVO.setCode(1);
        machineGetResultCheckVO.setResultMsg("成功");
        machineGetResultCheckVO.setStatus(true);
        machineGetResultCheckVO.setMac("00-E0-4C-36-18-D1");
        machineGetResultCheckVO.setIpv4("192.168.123.188");
        machineGetResultCheckVO.setIpv6("xxxxxxx");
        machineGetResultCheckVO.setAttackProtectList(attackProtectList);

        machineGetResultCheckVO1.setCode(0);
        machineGetResultCheckVO1.setResultMsg("成功");
        machineGetResultCheckVO1.setStatus(true);
        machineGetResultCheckVO1.setMac("00-E0-4C-36-18-D1");
        machineGetResultCheckVO1.setIpv4("192.168.123.188");
        machineGetResultCheckVO1.setIpv6("xxxxxxx11111");
        machineGetResultCheckVO1.setAttackProtectList(attackProtectList1);

        resultCheckVOS.add(machineGetResultCheckVO);
        resultCheckVOS.add(machineGetResultCheckVO1);

        System.out.println(JSON.toJSONString(resultCheckVOS));

    }



    /**
     *
     * version1  目标版本
     * version2  源版本
     * 第一个参数大返回正数，第二个参数大返回负数，一样返回0；
     */
    public static int compareVersion(String version1, String version2) {
        try {
            version1 = version1.replace("win_", "").replace("linux_", "").replace("arm_", "");
            version2 = version2.replace("win_", "").replace("linux_", "").replace("arm_", "");

            if (version1.equals(version2)) {
                return 0;
            }
            String[] version1Array = version1.split("\\.");
            String[] version2Array = version2.split("\\.");
            int index = 0;
            //获取最小长度值
            int minLen = Math.min(version1Array.length, version2Array.length);
            int diff = 0;
            //循环判断每位的大小
            while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
                index++;
            }
            if (diff == 0) {
                //如果位数不一致，比较多余位数
                for (int i = index; i < version1Array.length; i++) {
                    if (Integer.parseInt(version1Array[i]) > 0) {
                        return 1;
                    }
                }

                for (int i = index; i < version2Array.length; i++) {
                    if (Integer.parseInt(version2Array[i]) > 0) {
                        return -1;
                    }
                }
                return 0;
            } else {
                return diff > 0 ? 1 : -1;
            }
        }catch (Exception e) {
            return 99;
        }
    }


}
