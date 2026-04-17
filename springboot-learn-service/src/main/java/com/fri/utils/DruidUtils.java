package com.fri.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author : 陈余银
 * @create : 2022-06-06 10:06:00
 * @description : druid连接池工具类
 */
public class DruidUtils {

    private static final Logger log = LoggerFactory.getLogger(DruidUtils.class);
    private final static DruidUtils DRUID_UTILS = new DruidUtils();
    private static Properties properties = null;
    private static DataSource dataSource = null;
    private static transient Connection conn = null;

    public DruidUtils() {
    }

    /**
     * 1.配置和获取数据库连接配置信息
     * 2.扩展Druid功能,进行配置
     * 3.获取数据库连接,提供对外获取数据库资源的方法
     */
    private void initConfig(String url, String userName, String passWord, String driverName) throws IOException {
        properties = new Properties();
        properties.setProperty(DruidDataSourceFactory.PROP_URL, url);
        properties.setProperty(DruidDataSourceFactory.PROP_USERNAME, userName);
        properties.setProperty(DruidDataSourceFactory.PROP_PASSWORD, passWord);
        properties.setProperty(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, driverName);
        // 加载类路径下，配置文件
        properties.load(Objects.requireNonNull(DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties")));
//        properties.forEach((k, v) -> {
//            log.debug(String.format("key:%s value:%S", k, v));
//        });
//        log.info("初始化配置文件成功.....");
    }

    /**
     * 注册Druid
     */
    private void registerDruid() {
        if (null != dataSource) {
            return;
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提供对外 获取 DruidDatasource 的方法
     *
     * @return
     */
    private DataSource getDruidDataSource(String url, String userName, String passWord, String driverName) {
        if (null != dataSource) {
            return dataSource;
        }
        try {
            DRUID_UTILS.initConfig(url, userName, passWord, driverName);
            DRUID_UTILS.registerDruid();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 提供对外链接
     */
    public Connection getConn(String url, String userName, String passWord, String driverName) {
        try {
            if (conn == null) {
                if (dataSource != null) {
                    conn = dataSource.getConnection();
                } else {
                    conn = getDruidDataSource(url, userName, passWord, driverName).getConnection();
                }
            }
            //设置手动提交
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 提交事务
     */
    public void commit() {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                log.error("提交事务失败,Connection:" + conn);
                e.printStackTrace();
            }
        }
    }

    /**
     * 事务回滚
     */
    public void rollBack() {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                log.error("事务回滚失败,Connection:" + conn);
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("关闭连接失败,Connection:" + conn);
                e.printStackTrace();
            }
        }
    }
}
