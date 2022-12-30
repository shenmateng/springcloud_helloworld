/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.mt.config.database;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：MaTeng
 * @version ：B1.3.0
 * @program ：bmp-prm
 * @date ：Created in 2020/12/4 13:39
 * @description ：druid configuration
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidPropsConfig {

    /**
     * 合并多个DruidDataSource
     */
    private Boolean useGlobalDataSourceStat;

    /**
     * 驱动名称
     */
    private String driverClassName;

    /**
     * 是否开启指定每个连接上PSCache的大小
     */
    private Boolean poolPreparedStatements;

    /**
     * 指定每个连接上PSCache的大小
     */
    private Integer maxPoolPreparedStatementPerConnectionSize;

    /**
     * 验证连接是否有效时间周期
     */
    private Integer timeBetweenEvictionRunsMillis;

    /**
     * 最小空闲时间
     */
    private Integer minEvictableIdleTimeMillis;

    /**
     * 验证数据库连接的查询语句
     */
    private String validationQuery;

    /**
     * 验证这条连接是否可用
     */
    private Boolean testWhileIdle;

    /**
     * 如果为true（默认false），当应用向连接池申请连接时，连接池会判断这条连接是否是可用
     */
    private Boolean testOnBorrow;

    /**
     * 如果为true（默认false），当应用使用完连接，连接池回收连接的时候会判断该连接是否还可用
     */
    private Boolean testOnReturn;
}
