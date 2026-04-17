package com.fri.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by lixinxu on 2022-1-20 10:27
 * Desc: 系统堡垒锁学习原始数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemFortLockRaw {
    private String id;
    // 机器id
    private String machineUuid;
    // 操作系统类型
    private Integer osType;
    // 进程
    private String subject;
    // 路径
    private String object;
    // 权限
    private String rights;
    // 创建时间
    private Date createTime;
}
