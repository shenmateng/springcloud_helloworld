package com.mt.init;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.aliases.AddAliasMapping;
import io.searchbox.indices.aliases.ModifyAliases;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class EsIndexInitJob {
    @Autowired
    private JestClient jestClient;

    public static final String MAPPING = "{\"properties\":{\"action\":{\"properties\":{\"html\":{\"type\":\"keyword\",\"index\":false,\"doc_values\":false}}},\"webServerId\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}, \"mainIp\": {\"type\": \"text\",\"fields\": { \"keyword\": {\"type\": \"keyword\", \"ignore_above\": 256} }},\"alarmMsg\":{\"type\":\"keyword\",\"index\":false,\"doc_values\":false},\"categoryName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"categoryUuid\":{\"type\":\"byte\"},\"day\":{\"type\":\"date\",\"index\":false},\"description\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"eventId\":{\"type\":\"integer\"},\"eventUuid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"groupName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"hour\":{\"type\":\"byte\",\"index\":false},\"ip\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ipAddress\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"stack\":{\"type\":\"keyword\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"suspicious\":{\"type\":\"keyword\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"levelDesc\":{\"type\":\"keyword\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"localTimestamp\":{\"type\":\"long\",\"index\":false,\"doc_values\":false},\"logType\":{\"type\":\"byte\",\"index\":false,\"doc_values\":false},\"machine\":{\"properties\":{\"currentPage\":{\"type\":\"byte\",\"index\":false,\"doc_values\":false},\"maxResults\":{\"type\":\"byte\",\"index\":false,\"doc_values\":false}}},\"machineUuid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"minute\":{\"type\":\"short\",\"index\":false},\"object\":{\"properties\":{\"cmdline\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"info\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"add\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"rmi\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ldap\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"dns\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"host\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ip\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"pid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"proc\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"url\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"operation\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"operationDesc\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"phase\":{\"type\":\"byte\"},\"phaseDesc\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"result\":{\"type\":\"byte\"},\"score\":{\"type\":\"byte\",\"index\":false,\"doc_values\":false},\"serviceId\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"source\":{\"type\":\"short\"},\"sourceDesc\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"sourceIpAddress\":{\"properties\":{\"city\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"country\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ip\":{\"type\":\"keyword\",\"index\":false,\"doc_values\":false}}},\"region\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"type\":{\"type\":\"keyword\",\"index\":false,\"doc_values\":false},\"standardTimestamp\":{\"type\":\"long\"},\"subject\":{\"properties\":{\"pid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"webServerName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"webServerType\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"webServerVersion\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"netPort\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"installPath\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"procHash\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"procUuid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"process\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"type\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"user\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"treePath\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"typeName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ucrc\":{\"type\":\"long\",\"index\":false,\"doc_values\":false},\"outreachIntelligenceList\":{\"type\":\"nested\"},\"userAndSettings\":{\"type\":\"nested\"}}}";

    public static final String AGG_MAPPING = "{\"properties\":{\"standardTimestamp\":{\"type\":\"long\"},\"outreachMachine\":{\"type\":\"nested\"},\"outreachIntelligenceList\":{\"type\":\"nested\"},\"userAndSettings\":{\"type\":\"nested\"},\"orgCounts\":{\"type\":\"nested\"}}}";

    public static final String OUTREACH_MAPPING = "{\"properties\": {\"machineUuid\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"innerIp\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"agentInputTime\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"countTime\": {\"type\": \"long\"},\"object\": {\"properties\": {\"cmdline\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"ip\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"pid\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"proc\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"url\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}}}},\"subject\": {\"properties\": {\"pid\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"procHash\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"procUuid\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"process\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"type\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"user\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}}}},\"outreachAddress\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"outreachIpAddress\": {\"properties\": {\"ip\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"country\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"area\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"region\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"city\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"countryOrCity\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"county\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"type\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"company\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}}}},\"malicious\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"ipAndDomain\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"outreachMachine\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"maliciousList\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"ports\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\",\"ignore_above\": 256}}},\"outreachIp\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"outreachCount\": {\"type\": \"integer\"},\"result\": {\"type\": \"integer\"},\"saveEsTime\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"standardTimestamp\": {\"type\": \"date\"},\"userAndSettings\": {\"properties\": {\"userUuid\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"area\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"company\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"countTime\": {\"type\": \"long\"},\"industry\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"province\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}},\"city\": {\"type\": \"text\",\"fields\": {\"keyword\": {\"type\": \"keyword\"}}}}}}}";

    public static final String RASP_AGG_MAPPING = "{\"properties\":{\"raspAggId\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"machineUuid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"eventUuid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"webServerId\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ip\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ipLong\":{\"type\":\"long\"},\"ipAddress\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"sourceIpAddress\":{\"properties\":{\"city\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"country\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ip\":{\"type\":\"keyword\",\"index\":false,\"doc_values\":false},\"region\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"type\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"innerIp\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"innerIpLong\":{\"type\":\"long\"},\"innerIpAddress\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"targetIpAddress\":{\"properties\":{\"city\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"country\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ip\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"region\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"type\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"agentInputTime\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"eventInputTime\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"saveEsTime\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"groupName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"categoryName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"typeName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"description\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"operation\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"operationExtra\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"operationDesc\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"levelDesc\":{\"type\":\"keyword\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"result\":{\"type\":\"byte\"},\"status\":{\"type\":\"long\"},\"machine\":{\"properties\":{\"extranetIp\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"intranetIp\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"machineName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"onlineStatus\":{\"type\":\"long\"},\"operatingSystem\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"osType\":{\"type\":\"long\"},\"uuid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"userAndSettings\":{\"type\":\"nested\",\"properties\":{\"area\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"attackCount\":{\"type\":\"long\"},\"blockedCount\":{\"type\":\"long\"},\"city\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"company\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"countTime\":{\"type\":\"long\",\"index\":false,\"doc_values\":false},\"industry\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"province\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"unblockedCount\":{\"type\":\"long\"},\"userUuid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"subject\":{\"properties\":{\"webServerName\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"webServerType\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"webServerVersion\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"netPort\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"process\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"installPath\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"type\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"pid\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"webPagePhysicalPath\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"object\":{\"properties\":{\"command\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"info\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"add\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"file\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"rmi\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"ldap\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"dns\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"host\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}},\"standardTimestamp\":{\"type\":\"long\"},\"eventGrade\":{\"type\":\"long\"},\"eventType\":{\"type\":\"long\"},\"attackCount\":{\"type\":\"long\"},\"interceptCount\":{\"type\":\"long\"},\"countTime\":{\"type\":\"long\"},\"userUuids\":{\"type\":\"text\"},\"fortEventType\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}},\"judgeStatus\":{\"type\":\"text\",\"fields\":{\"keyword\":{\"type\":\"keyword\",\"ignore_above\":256}}}}}";


    public void execute() {
        // 预初始化未来一周索引
        for (int i = 1; i <= 7; i++) {
            long time = System.currentTimeMillis() + (24 * 60 * 60 * 1000) * i;
            try {
                this.initIndex(Constant.ORIGINAL_LOG_INDEX, time, true);
                this.initAggIndex(Constant.EVENT_INDEX, time, true, false);
                this.initAggIndex(Constant.YS_AGGREGATION, time, true, false);
                this.initAggIndex(Constant.YS_FORT, time, true, false);
                this.initIndex(Constant.YS_OUTREACH, time, true);
                this.initAggIndex(Constant.YS_OUTREACH_IP_DOMAIN_AGG, time, true, true);
                this.initAggIndex(Constant.YS_OUTREACH_MACHINE_AGG, time, true, true);
                this.initAggIndex(Constant.YS_OUTREACH_IP_DOMAIN_PROCESS_AGG, time, true, true);
                this.initAggIndex(Constant.YS_OUTREACH_MACHINE_PROCESS_AGG, time, true, true);
                // this.initRaspAggIndex(Constant.YS_RASP_AGGREGATION, time, true);
                log.info("预初始化第 {} 天索引完成", i);
            } catch (Exception e) {
                log.error("预初始化第 {} 天索引失败", i);
            }
        }

    }

    public void initIndex(String index, Long timeMilis, boolean flag) {

        Map<String, Object> settings = new HashMap<>(16);
        settings.put("number_of_replicas", 1);
        settings.put("number_of_shards", 3);
        settings.put("index.merge.scheduler.max_thread_count", 1);
        settings.put("index.refresh_interval", "60s");
        settings.put("index.translog.durability", "async");
        settings.put("index.translog.flush_threshold_size", "1024mb");
        settings.put("index.translog.sync_interval", "120s");
        settings.put("index.max_result_window", 1_000000000);
        LocalDate localDate = Instant.ofEpochMilli(timeMilis).atZone(ZoneId.systemDefault()).toLocalDate();
        String esIndex = index + "-" + localDate;

        try {
            if (LocalDateTime.now().getHour() < 1 && flag) {
                this.jestClient.execute(new DeleteIndex.Builder(esIndex).build());
            }
            JestResult execute = this.jestClient.execute(new CreateIndex.Builder(esIndex).settings(settings).mappings(EsIndexInitJob.MAPPING).build());
            this.jestClient.execute(new ModifyAliases.Builder(new AddAliasMapping.Builder(esIndex, index).build()).build());
            log.info("创建ES索引 {} 响应code：{}", esIndex, execute.getResponseCode());
        } catch (IOException e) {
            log.error("初始化索引异常", e);
        }

//        try {
//            LocalDate l = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate hlocalDate = l.plusDays(-saveDays);
//            DeleteIndex deleteIndex = new DeleteIndex.Builder(index + "-" + hlocalDate).build();
//            JestResult execute = jestClient.execute(deleteIndex);
//            log.info("删除ES索引 {} 响应code：{}", index + "-" + hlocalDate, execute.getResponseCode());
//        } catch (IOException e) {
//            log.error("删除index异常", e);
//        }
    }

    /**
     * 根据需求创建新索引（事件聚合，堡垒锁事件，堡垒锁集合事件）
     *
     * @param index
     * @param timeMilis
     * @param flag
     * @param isOutreach 外联标识
     * @author lihongjun
     */
    public void initAggIndex(String index, Long timeMilis, boolean flag, boolean isOutreach) {
        Map<String, Object> settings = new HashMap<>(16);
        settings.put("number_of_replicas", 1);
        settings.put("number_of_shards", 3);
        settings.put("index.merge.scheduler.max_thread_count", 1);
        settings.put("index.refresh_interval", "60s");
        settings.put("index.translog.durability", "async");
        settings.put("index.translog.flush_threshold_size", "1024mb");
        settings.put("index.translog.sync_interval", "120s");
        settings.put("index.max_result_window", 1_000000000);
        LocalDate localDate = Instant.ofEpochMilli(timeMilis).atZone(ZoneId.systemDefault()).toLocalDate();
        String esIndex = index + "-" + localDate;
        try {
            if (LocalDateTime.now().getHour() < 1 && flag) {
                this.jestClient.execute(new DeleteIndex.Builder(esIndex).build());
            }
            JestResult execute;
            if (isOutreach) {
                execute = this.jestClient.execute(new CreateIndex.Builder(esIndex).settings(settings).mappings(EsIndexInitJob.OUTREACH_MAPPING).build());
            } else {
                execute = this.jestClient.execute(new CreateIndex.Builder(esIndex).settings(settings).mappings(EsIndexInitJob.AGG_MAPPING).build());
            }
            this.jestClient.execute(new ModifyAliases.Builder(new AddAliasMapping.Builder(esIndex, index).build()).build());
            log.info("创建ES索引 {} 响应code：{} 响应错误：{}", esIndex, execute.getResponseCode(),execute.getErrorMessage());
            execute.getErrorMessage();
        } catch (IOException e) {
            log.error("初始化索引异常", e);
        }

//        try {
//            LocalDate l = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate hlocalDate = l.plusDays(-saveDays);
//            DeleteIndex deleteIndex = new DeleteIndex.Builder(index + "-" + hlocalDate).build();
//            JestResult execute = jestClient.execute(deleteIndex);
//            log.info("删除ES索引 {} 响应code：{}", index + "-" + hlocalDate, execute.getResponseCode());
//        } catch (IOException e) {
//            log.error("删除index异常", e);
//        }
    }


}
