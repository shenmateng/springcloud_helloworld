package com.mt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Author wangsen
 * @Date 2019/10/28
 **/
@Configuration
@Order(1)
public class AssetConfig {

    public static int TASK_THREAD_CORE_SIZE;

    public static int TASK_THREAD_MAX_SIZE;

    public static int TASK_THREAD_QUEUE_SIZE;

    public static int INDEX_NUMBER_OF_SHARDS;

    public static int INDEX_NUMBER_OF_REPLICAS;

    public static int INDEX_MAX_RESULT_WINDOW;

    public static String INDEX_REFRESH_INTERVAL;

    public static int INDEX_BULK_SIZE;

    public static int INDEX_BULK_UPDATE_SIZE;

    public static String INDEX_TRANSLOG_DURABILITY;

    public static String INDEX_TRANSLOG_SYNC_INTERVAL;

    public static int ES_ZC_SEARCH_LOG_MAX_KEEP_DAY = 7;

    @Bean
    public int initCallConfig(
                    @Value("${task.threadCoreSize}")int taskThreadCoreSize,
                    @Value("${task.threadMaxSize}")int taskThreadMaxSize,
                    @Value("${task.threadQueueSize}")int taskThreadQueueSize,
                    @Value("${index.number_of_shards}")int indexNumberOfShards,
                    @Value("${index.number_of_replicas}")int indexNumberOfReplicas,
                    @Value("${index.max_result_window}")int indexMaxResultWindow,
                    @Value("${index.bulk_size}")int indexBulkSize,
                    @Value("${index.bulk_update_size}")int indexBulkUpdateSize,
                    @Value("${index.refresh_interval}")String indexRefreshInterval,
                    @Value("${index.translog.durability}")String indexTranslogDurability,
                    @Value("${index.translog.sync_interval}")String indexTranslogSyncInterval) {

        TASK_THREAD_CORE_SIZE = taskThreadCoreSize;
        TASK_THREAD_MAX_SIZE = taskThreadMaxSize;
        TASK_THREAD_QUEUE_SIZE = taskThreadQueueSize;
        INDEX_NUMBER_OF_SHARDS = indexNumberOfShards;
        INDEX_NUMBER_OF_REPLICAS = indexNumberOfReplicas;
        INDEX_MAX_RESULT_WINDOW =  indexMaxResultWindow;
        INDEX_BULK_SIZE = indexBulkSize;
        INDEX_BULK_UPDATE_SIZE = indexBulkUpdateSize;
        INDEX_REFRESH_INTERVAL = indexRefreshInterval;
        INDEX_TRANSLOG_DURABILITY = indexTranslogDurability;
        INDEX_TRANSLOG_SYNC_INTERVAL = indexTranslogSyncInterval;
        return 0;
    }
}
