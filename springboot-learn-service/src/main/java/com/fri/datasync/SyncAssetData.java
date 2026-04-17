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
 * @Author: chenyuyin
 * @DateTime: 2022-5-30 16:17
 * @Description:
 */
public class SyncAssetData {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FlinkSyncEnvSet.flinkEnvSet(env);

        String username = AESUtil.decrypt(FlinkNacosConfig.getConfigValue("mysql.username"), AESUtil.AES_KEY);
        String password = AESUtil.decrypt(FlinkNacosConfig.getConfigValue("mysql.password"), AESUtil.AES_NEW_KEY);
        String driverClassName = FlinkNacosConfig.getConfigValue("mysql.driverClassName");

        MySqlSource<String> sourceFunction = MySqlSource.<String>builder()
                .hostname(FlinkNacosConfig.getConfigValue("mysql.host"))
                .port(Integer.parseInt(FlinkNacosConfig.getConfigValue("mysql.port")))
                .databaseList(FlinkNacosConfig.getConfigValue("mysql.asset.database"))
                .tableList(FlinkNacosConfig.getConfigValue("mysql.asset.table"))
                .username(username)
                .password(password)
                /*
                 * initial初始化快照,即全量导入后增量导入(检测更新数据写入)
                 * latest:只进行增量导入(不读取历史变化)
                 * timestamp:指定时间戳进行数据导入(大于等于指定时间戳读取数据)
                 */
                .startupOptions(StartupOptions.initial())
                .scanNewlyAddedTableEnabled(true)
                //读的数据是binlog文件，反序列化器，解析数据
                .deserializer(new JsonDebeziumDeserializationSchema(FlinkNacosConfig.getConfigValue("org.centerId")))
                .build();

        DataStreamSource<String> commonSource = env
                .fromSource(sourceFunction, WatermarkStrategy.noWatermarks(), "sync ys_asset source");

        commonSource
                .addSink(new MysqlSinkPlus(FlinkNacosConfig.getConfigValue("mysql.asset.url"), username, password, driverClassName))
                .setParallelism(1);

        env.execute("Sync Asset DB Data");
    }
}
