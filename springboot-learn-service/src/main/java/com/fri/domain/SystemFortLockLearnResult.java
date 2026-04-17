package com.fri.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by lixinxu on 2022-1-20 10:27
 * Desc: 系统堡垒锁学习结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemFortLockLearnResult {
    private String id;
    // 机器id
    private String machineUuid;
    // 进程
    private String subject;
    // 路径
    private String object;
    // 权限
    private String rights;
    // 创建时间
    private Date createTime;
    // 聚合数量
    private Integer quantity;

    private Integer osType;
}
