/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.mt.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author ：MaTeng
 * @version ：B1.3.0
 * @program ：bmp-prm
 * @date ：Created in 2020/12/4 13:41
 * @description ：druid configuration
 */
@Slf4j
@Configuration
@MapperScan(basePackages = MyBatisDataSourceConfig.PACKAGES, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisDataSourceConfig {
    protected static final String PACKAGES = "com.mt.mapper";

    protected static final String MAPPER_XML_LOCATION = "classpath*:com/mt/mapper/*Mapper.xml";

    /**
     * MyBatis 数据源事务管理器
     *
     * @param dataSource DruidDataSource 数据源
     * @return 数据源事务管理器
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * MyBatis sqlSessionFactory
     *
     * @param dataSource DruidDataSource 数据源
     * @return sqlSessionFactory
     * @throws Exception e
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        if(log.isInfoEnabled()){
            log.info("初始化数据库:{}",((DruidDataSource) dataSource).getRawJdbcUrl());
        }
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 开启驼峰
        Objects.requireNonNull(sqlSessionFactoryBean.getObject())
                .getConfiguration()
                .setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean
                // Mapper XML 扫描路径
                .setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }
}
