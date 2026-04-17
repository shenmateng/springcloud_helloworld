package com.fri.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-1-27 9:59
 * @Description:
 */
public class ClickHouseUtil {

    private static Connection connection;

    public static Connection getConnection(String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("cc.blynk.clickhouse.ClickHouseDriver");
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("cc.blynk.clickhouse.ClickHouseDriver");
        connection = DriverManager.getConnection("jdbc:clickhouse://192.168.123.252:8123/learn", "default", "");
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
