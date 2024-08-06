package com.mt.database;

import lombok.Data;


/**
 * Created by lixinxu on 2022-10-8 14:44
 * Desc:
 */
@Data
public class HostSecuritySettingsMachine {



    // 组织机构名称
    private String orgName;

    // 自身防护
    private Integer selfProtectStatus;

    // 主机分组
    private String machineTags;


    // 一键隔离
    private Integer segregate = 0;

    /**
     * 导入状态：1导入中
     */
    private Integer importStatus = 0;



    // 是否是旧版
    private Boolean old;
}
