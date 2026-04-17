package com.fri.common;

import com.fri.config.FlinkNacosConfig;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.runtime.state.storage.FileSystemCheckpointStorage;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.concurrent.TimeUnit;

/**
 * @author : 陈余银
 * @create : 2022-05-30 16:12:00
 * @description :
 */
public class FlinkSyncEnvSet {
    public static void flinkEnvSet(StreamExecutionEnvironment env) {
        /*
         *读取的是binlog中的数据，如果任务挂掉，尽量能实现断点续传功能。如果从最新的读取（丢数据）。如果从最开始读（重复数据）。
         *理想状态：读取binlog中的数据读一行，保存一次读取到的（读取到的行）位置信息。而flink中读取行位置信息保存在Checkpoint中。
         *使用Checkpoint可以把flink中读取（按行）的位置信息保存在Checkpoint中
         */
        env.enableCheckpointing(30 * 1000L);
        //指定设置检查点执行的超时时间为60s
        env.getCheckpointConfig().setCheckpointTimeout(60 * 1000L);
        //设置CheckPoint模式
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.setRestartStrategy(RestartStrategies.failureRateRestart( 3, // 一个时间段内的最大失败次数
                Time.of(5, TimeUnit.MINUTES), // 衡量失败次数的是时间段
                Time.of(10, TimeUnit.SECONDS) // 间隔
        ));
        /*
         *任务挂掉的时候是否清理checkpoint。使任务正常退出时不删除CK内容，有助于任务恢复。默认的是取消的时候清空checkpoint中的数据
         * RETAIN_ON_CANCELLATION，表示取消任务的时候，保存最后一次的checkpoint。便于任务的重启和恢复
         * DELETE_ON_CANCELLATION，表示取消任务的时候，会删除Checkpoint数据，只有job执行失败的时候才会保存checkpoint
         */
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setStateBackend(new HashMapStateBackend());
        env.getCheckpointConfig().setCheckpointStorage(new FileSystemCheckpointStorage(FlinkNacosConfig.getConfigValue("hdfs.url")));
        //设置访问HDFS的用户名
        System.setProperty("HADOOP_USER_NAME", FlinkNacosConfig.getConfigValue("hdfs.username"));
    }
}
