package com.mt.service;

import com.mt.database.User;
import com.mt.database.VcStore;
import com.mt.mapper.VoStoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2022/7/29 15:30
 * @Version:
 * @Description: mybatis-plus 测试service类
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

@Service
public class MybatisPlusService {

    @Resource
    private VoStoreMapper voStoreMapper;


    public void mybatisTest(){
        List<User> vcStoreDOS = voStoreMapper.selectList(null);
        VcStore vcStore = new VcStore();
        vcStore.setPlatform("平台");
        User user = new User();
        user.setName("牛马");
        user.setAge(11);
        user.setEmail("niuma.com");
        voStoreMapper.insert(user);
        System.out.println(vcStoreDOS);
    }
}
