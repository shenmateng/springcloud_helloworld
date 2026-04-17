package com.fri.datasync;

import com.fri.common.FlinkSyncEnvSet;
import com.fri.common.JsonDebeziumDeserializationSchema;
import com.fri.common.MysqlSinkPlus;
import com.fri.config.FlinkNacosConfig;
import com.fri.utils.AESUtil;
import org.apache.flink.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.cdc.connectors.mysql.table.StartupOptions;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author : 陈余银
 * @create : 2022-05-30 16:56:00
 * @description :
 */
public class SyncProtectData {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FlinkSyncEnvSet.flinkEnvSet(env);

        String username = AESUtil.decrypt(FlinkNacosConfig.getConfigValue("mysql.username"), AESUtil.AES_KEY);
        String password = AESUtil.decrypt(FlinkNacosConfig.getConfigValue("mysql.password"), AESUtil.AES_NEW_KEY);
        String driverClassName = FlinkNacosConfig.getConfigValue("mysql.driverClassName");

        MySqlSource<String> sourceFunction = MySqlSource.<String>builder()
                .hostname(FlinkNacosConfig.getConfigValue("mysql.host"))
                .port(Integer.parseInt(FlinkNacosConfig.getConfigValue("mysql.port")))
                .databaseList(FlinkNacosConfig.getConfigValue("mysql.protect.database"))
                .tableList(FlinkNacosConfig.getConfigValue("mysql.protect.table"))
                .username(username)
                .password(password)
                .startupOptions(StartupOptions.initial())
                .scanNewlyAddedTableEnabled(true)
                .deserializer(new JsonDebeziumDeserializationSchema(FlinkNacosConfig.getConfigValue("org.centerId")))
                .build();

        DataStreamSource<String> commonSource = env
                .fromSource(sourceFunction, WatermarkStrategy.noWatermarks(), "sync ys_protect source");

        commonSource
                .addSink(new MysqlSinkPlus(FlinkNacosConfig.getConfigValue("mysql.protect.url"), username, password, driverClassName))
                .setParallelism(1);

        env.execute("Sync Protect Data");
    }
}
