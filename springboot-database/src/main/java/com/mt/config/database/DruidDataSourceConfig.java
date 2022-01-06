/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.mt.config.database;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：MaTeng
 * @version ：B1.3.0
 * @program ：bmp-prm
 * @date ：Created in 2020/12/4 13:41
 * @description ：druid configuration
 */
@Slf4j
@SpringBootConfiguration
public class DruidDataSourceConfig {

    private final WallFilter wallFilter;

    private final StatFilter statFilter;

    private final Slf4jLogFilter slf4jLogFilter;

    private final DruidPropsConfig druidPropsConfig;

    public DruidDataSourceConfig(WallFilter wallFilter, StatFilter statFilter, Slf4jLogFilter slf4jLogFilter, DruidPropsConfig druidPropsConfig) {
        this.wallFilter = wallFilter;
        this.statFilter = statFilter;
        this.slf4jLogFilter = slf4jLogFilter;
        this.druidPropsConfig = druidPropsConfig;
    }

    /**
     * 初始化DruidDataSource对象
     * Spring Boot 2.X 版本不再支持配置继承，多数据源的话每个数据源的所有配置都需要单独配置，否则配置不会生效
     * 为了配置方便，这里直接读取bootstrap.yml配置文件中的内容，并手动加载到DruidDataSource对象中
     *
     * @return DruidDataSource对象，并且已经按照配置文件加载好了各项配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.prm")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        // filter配置
        List<Filter> filterList = new ArrayList<>(3);
        filterList.add(statFilter);
        filterList.add(wallFilter);
        filterList.add(slf4jLogFilter);
        druidDataSource.setProxyFilters(filterList);
        druidDataSource.setUseGlobalDataSourceStat(druidPropsConfig.getUseGlobalDataSourceStat());
        druidDataSource.setDriverClassName(druidPropsConfig.getDriverClassName());
        druidDataSource.setPoolPreparedStatements(druidPropsConfig.getPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidPropsConfig.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidPropsConfig.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidPropsConfig.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidPropsConfig.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidPropsConfig.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidPropsConfig.getTestOnBorrow());
        druidDataSource.setTestOnReturn(druidPropsConfig.getTestOnReturn());
        return druidDataSource;
    }

    /**
     * 配置Druid监控
     * 后台管理Servlet
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //这是配置的druid监控的登录密码
        Map<String, String> initParams = new HashMap<>(0);
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        initParams.put("resetEnable", "false");
        //默认就是允许所有访问
        initParams.put("allow", "");
        //黑名单的IP
        initParams.put("deny", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置web监控的filter
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
