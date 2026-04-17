package com.fri.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-21 14:38
 * @Description: Hikaricp数据库连接池工具类
 */
public class HikaricpUtils {

    private static HikariDataSource dataSource;

    private static Connection connection;

    /**
     * 通过数据源获取连接
     *
     * @return
     * @throws IOException
     */
    public static Connection getConnection(String url, String username, String password, String driverClassName) {

        try {
            // 因为dataSource是全局变量、默认初始化值为null
            if (dataSource == null) {
                // 通过字节输入流 读取 配置文件  hikaricp.properties
                InputStream is = HikaricpUtils.class.getClassLoader().getResourceAsStream("hikaricp.properties");
                // 因为HikariConfig类不可以加载io，但是可以加载Properties。因此：将输入流is封装到props
                Properties props = new Properties();
                props.load(is);
                // 再将封装好的props 传入到HikariConfig 类中，得到 config对象
                HikariConfig config = new HikariConfig(props);
                if (!StringUtils.isEmpty(driverClassName)) {
                    config.setDriverClassName(driverClassName);
                }
                config.setJdbcUrl(url);
                config.setUsername(username);
                config.setPassword(password);
                // 将config对象传入给HikariDataSource ，返回dataSource
                dataSource = new HikariDataSource(config);
                connection = dataSource.getConnection();
            }
            // 返回connection
            return connection;
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
