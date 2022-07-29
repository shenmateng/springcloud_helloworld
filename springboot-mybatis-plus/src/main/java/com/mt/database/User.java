/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */
package com.mt.database;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author ：makejava
 * @version ：1.23.0
 * @program ：springboot-mybatis-plus
 * @date ：Created in 2022/07/29 16:14
 * @description ：实体类 UserDO  
 */ 
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 379340805673413951L;
    /**
    * 主键id
    */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别
    */
    private Integer age;
    /**
    * 邮箱
    */
    private String email;

}
