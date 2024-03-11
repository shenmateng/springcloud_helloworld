package com.mt.service;


import com.alibaba.fastjson.JSON;
import com.mt.database.AccessWhiteIpsVO;
import com.mt.utils.BeanUtils;
import com.mt.utils.IpUtils;
import com.mt.utils.RedisConstant;
import com.mt.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2023/6/26 11:25
 * @Version: 2.1.7
 * @Description: 访问白名单service
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
 *
 */
@Slf4j
@Service
public class AccessWhiteListServiceImpl implements AccessWhiteListService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 防护白名单模板列表
     * @return 模板列表
     */
    @Override
    public AccessWhiteIpsVO list() throws ParseException {
        String ips = (String)redisTemplate.opsForHash().get(RedisConstant.REDIS_KEY_WHITE_LIST, "ips");
        log.info("查询redis成功-------{}",ips);

        if(!StringUtils.isEmpty(ips)){
            //redisObj有值转成bean
            AccessWhiteIpsVO accessWhiteIpsVO = JSON.parseObject(ips, AccessWhiteIpsVO.class);
            log.info("redis转换数据对象---------{}",accessWhiteIpsVO);
            Date startTime = accessWhiteIpsVO.getStartTime();
            Date endTime = accessWhiteIpsVO.getEndTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String start = dateFormat.format(startTime);
            String end = dateFormat.format(endTime);
            Date date = new Date();
            String nowTime = dateFormat.format(date);

            Date parse = dateFormat.parse(start);
            Date parse1 = dateFormat.parse(end);
            Date parse2 = dateFormat.parse(nowTime);
            Integer status = (Integer)redisUtils.hget(RedisConstant.REDIS_KEY_WHITE_LIST, "status");

            boolean effectiveDate = isEffectiveDate(parse2, parse, parse1);
            String sss = "你在说什么啊，的说法是生成环境士大夫";
            String ssss = "生成环境";
            boolean contains = sss.contains(sss);
            return accessWhiteIpsVO;
        }

        return new AccessWhiteIpsVO();

    }

    /**
     * 防护白名单开关控制
     * @param switchStatus 请求参数
     */
    @Override
    public void updateStatus(Integer switchStatus) {
        redisUtils.hdel(RedisConstant.REDIS_KEY_WHITE_LIST,"status");
        redisUtils.hset(RedisConstant.REDIS_KEY_WHITE_LIST,"status",switchStatus);
        log.info("修改开关控制------修改redis成功-------{}",switchStatus);
    }

    /**
     * 添加编辑白名单
     * @param accessWhiteIpsVO 请求参数
     */
    @Override
    public void addAccessWhitelist(AccessWhiteIpsVO accessWhiteIpsVO) throws Exception {

        for (String ip : accessWhiteIpsVO.getIp().split(",")){
            if(!IpUtils.isIpv4(ip) && !IpUtils.isIpRange(ip) && !IpUtils.isIpCidr(ip)) {
                throw new Exception("ip类型异常");
            }
        }
        try {
            redisTemplate.opsForHash().delete(RedisConstant.REDIS_KEY_WHITE_LIST,"ips");
            redisTemplate.opsForHash().put(RedisConstant.REDIS_KEY_WHITE_LIST,"ips",JSON.toJSONString(accessWhiteIpsVO));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("添加编辑白名单------添加--修改redis成功-------{}",JSON.toJSONString(accessWhiteIpsVO));


    }


    /**
     *
     * @param nowTime   当前时间
     * @param startTime	开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }



}
