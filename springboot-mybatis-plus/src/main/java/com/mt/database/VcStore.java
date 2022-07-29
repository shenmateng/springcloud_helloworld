/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.mt.database;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：makejava
 * @version ：1.23.0
 * @program ：springboot-mybatis-plus
 * @date ：Created in 2022/07/29 15:25
 * @description ：亚马逊沃尔玛VC、DF映射店铺信息表实体类 VcStoreDO  
 */ 
@Data
@TableName("vc_store")
public class VcStore implements Serializable {
    private static final long serialVersionUID = 602087779640748891L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 销售端口
    */
    private String salesPort;
    /**
    * 平台
    */
    private String platform;
    /**
    * 站点
    */
    private String site;
    /**
    * 店铺账号
    */
    private String storeAccount;
    /**
    * 店铺名称
    */
    private String storeName;
    /**
    * 创建时间
    */
    private Date createdTime;
    /**
    * 创建人
    */
    private String createdBy;
    /**
    * 更新时间
    */
    private Date updatedTime;
    /**
    * 更新人
    */
    private String updatedBy;
    /**
    * 是否删除（0：未删除，1：已删除）
    */
    private Integer isDelete;

}
