package com.fri.utils;

import com.alibaba.fastjson.JSON;
import com.fri.domain.scanevent.ScanEventLogDO;
import com.fri.domain.scanevent.ScanIpDO;
import com.fri.domain.scanevent.SubjectDO;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

/**
 * @Author: mt
 * @DateTime: 2022-2-11 8:45
 * @Description:
 */
public class ScanProducer {
    public static void main(String[] args) {
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "192.168.123.37:9092");
        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"admin\" password=\"jowto1234\";");
        //创建生产者实例
            for(int i = 0;i<1000;i++){
                KafkaProducer<String, String> producer = new KafkaProducer<>(props);
                ScanEventLogDO scanEventLogDO = new ScanEventLogDO();
                Date date = new Date();
                scanEventLogDO.setAgentInputTime(date.getTime());
                scanEventLogDO.setDay("2023-05-19 17:30:01");
                scanEventLogDO.setEventId(0);
                scanEventLogDO.setEventType(0);
                scanEventLogDO.setEventUuid("00000000");
                scanEventLogDO.setHours(9);
                scanEventLogDO.setIfFort(1);
                scanEventLogDO.setIfWhite(0);
                scanEventLogDO.setIgnore(0);
                scanEventLogDO.setLevelDesc("低危啊啊啊啊啊啊啊");
                scanEventLogDO.setLocalTimestamp(date.getTime());
                scanEventLogDO.setMachineUuid("a23b3c09567062b388da3cd175a9b4fa");
                scanEventLogDO.setMinute(560);
                ScanIpDO scanIpDO = new ScanIpDO();
                scanIpDO.setDomain("update.googleapis.com");
                scanIpDO.setIp("203.208.50.143:"+ i+10);
                scanEventLogDO.setObject(scanIpDO);
                scanEventLogDO.setOperation("connect");
                scanEventLogDO.setResult(1);
                scanEventLogDO.setScore(0);
                scanEventLogDO.setServiceId("");
                scanEventLogDO.setSource(1);
                scanEventLogDO.setSourceDesc("内置规则");
                scanEventLogDO.setStandardTimestamp(date.getTime());
                scanEventLogDO.setStatus("0");
                SubjectDO subjectDO = new SubjectDO();
                subjectDO.setbVerify("yes");
                subjectDO.setCompany("Google LLC");
                subjectDO.setPid("13304");
                subjectDO.setProcHash("94c4c8130e96785dd19b70a0fc1088f6");
                subjectDO.setProcUuid("89a68a45906802441cdf8d71b6fb4833");
                subjectDO.setProcess("C:\\users\\administrator\\appdata\\local\\google\\update\\googleupdate.exe");
                subjectDO.setType("kernel");
                subjectDO.setUser("Administrator");
                scanEventLogDO.setSubject(subjectDO);
                scanEventLogDO.setTreePath("wininit.exe(NT AUTHORITY\\SYSTEM)(380)|services.exe(NT AUTHORITY\\SYSTEM)(464)|svchost.exe(Administrator)(532)|GoogleUpdate.exe(Administrator)(13304)");
                scanEventLogDO.setUcrc(4112332482L);
                scanEventLogDO.setScanNumber(10);
                scanEventLogDO.setScanTime(200000L);
                scanEventLogDO.setPushTime(date.getTime());
                System.out.println("推送时间"+date.getTime());
                ProducerRecord record = new ProducerRecord<>("sys_active_port_scan", JSON.toJSONString(scanEventLogDO));
                //发送记录
                producer.send(record);
                producer.close();
            }



    }
}
