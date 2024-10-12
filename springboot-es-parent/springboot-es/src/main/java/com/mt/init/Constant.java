package com.mt.init;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaolm
 * @data 2019/1/28
 */
public class Constant {

    public static final String FOREIGN_COUNTRY = "外国";
    public static final String LOCAL_AREA_NETWORK = "局域网";
    public static final String CHINA = "中国";
    public static final String UNKNOW = "unknown";

    private Constant() {
    }

    /**
     * ES查询最大条数限制值
     */
    public static long ES_MAX_RESULT = 10000;

    public static final String CONTEXT_PATH = "/eventSrv";

    public static final String GK_PATH = "/gk";

    /**
     * 否、无效、异常、禁用
     */
    public static final int IS_NO = 0;

    /**
     * 是、有效、正常、启用
     */
    public static final int IS_YES = 1;

    public static final String APPLICATION_ROLE = "应用管理员";

    public static final String SYSTEM_ROLE = "系统管理员";

    public static final String DEFAULT = "0X000000000000000000000000000000";

    public static Map<String, String> TPLMAP = new HashMap<>();

    public static final List<String> CITY_MAP = Arrays.asList("北京", "天津", "上海", "重庆");

    public static final List<String> NO_ADD_KEYWORD = Arrays.asList("source", "categoryUuid", "Integer", "result", "ucrc");

    public static final List<String> KERNEL = Arrays.asList("create_proc", "create_bin", "proc_exit", "proc_operate", "listen_close", "listen", "driver_unload");
    // 事件
    public static final String EVENT_INDEX = "ys_event";
    // 聚合事件
    public static final String YS_EVENT_AGGREGATION = "ys_event_aggregation";
    // 原始攻击日志
    public static final String ORIGINAL_LOG_INDEX = "ys_original_log";
    // 堡垒锁日志
    public static final String YS_FORT = "ys_fort";
    // 堡垒锁聚合事件
    public static final String YS_FORT_AGGREGATION = "ys_fort_aggregation";

    // 堡垒锁聚合+事件聚合
    public static final String YS_AGGREGATION = "ys_aggregation";
    // rasp日志聚合
    public static final String YS_RASP_AGGREGATION = "ys_rasp_aggregation";
    public static final String EVENT_TYPE = "event";
    public static final String FORT_TYPE = "fort";
    public static final String ZERODAY_TYPE = "zeroDay";// 零日漏洞
    public static final String MALICIOUS_TYPE = "malicious";// 恶意外联
    public static final String KNOW_WEBSHELL_TYPE = "knowWebshell";// 已知webshell
    public static final String RASP_TYPE = "rasp";
    public static final String OUTREACH_TYPE = "连接";
    // 外联索引
    public static final String YS_OUTREACH = "ys_outreach";
    public static final String YS_OUTREACH_MACHINE_AGG = "ys_outreach_machine_agg";
    public static final String YS_OUTREACH_MACHINE_PROCESS_AGG = "ys_outreach_machine_process_agg";
    public static final String YS_OUTREACH_IP_DOMAIN_AGG = "ys_outreach_ip_domain_agg";
    public static final String YS_OUTREACH_IP_DOMAIN_PROCESS_AGG = "ys_outreach_ip_domain_process_agg";
    public static final String IP_BLACK_LIST = "ip_domain_black_list";

    public static final List<String> OUTREACH_AGG_INDEXS = Arrays.asList(YS_OUTREACH_MACHINE_AGG, YS_OUTREACH_MACHINE_PROCESS_AGG, YS_OUTREACH_IP_DOMAIN_AGG, YS_OUTREACH_IP_DOMAIN_PROCESS_AGG);


    public static volatile Map<String, List<String>> MACHINE_USERUUIDS = new ConcurrentHashMap<>();





    public static Map<String, List<String>> LOG_WHITELISTS = new ConcurrentHashMap<>();

    public static Map<String, List<String>> EVENT_WHITELISTS = new ConcurrentHashMap<>();







    public static final String SUSPICIOUS_LOGIN_TOPIC = "suspicious_login";

    /**
     * redis key
     **/
    public static final String NOTICE_PUSH_CONFIG = "NOTICE_PUSH_CONFIG";
    public static final String NOTICE_PUSH_COUNT = "NOTICE_PUSH_COUNT";
    // 主IP
    public static final String MACHINE_PRIMARY_IP = "MACHINE_PRIMARY_IP";

    /**
     * kafka topic
     **/
    public static final String NOTICE_PUSH_MESSAGE = "notice_push_message";
    public static final String SYNC_LOG_DATA_RETRY = "sync-log-data-retry";
    public static final String SYNC_AGG_DATA_RETRY = "sync-agg-data-retry";

    // 缓存维护 恶意外联匹配规则-南京实验室
    public static Map<String, String> dicIntelNanjingMap = new ConcurrentHashMap<>();
    // 缓存维护 恶意外联匹配规则汉化-南京实验室
    public static Map<String, String> dicIntelNanjingTranslateMap = new ConcurrentHashMap<>();

    // 离线同步topic
    public static final String OFFLINE_SOURCE_TOPIC = "offlinesync_sourcefile";

    // 离线文件同步路径
    public static final String OFFLINE_SOURCE_PATH = "/data/api/nettyFile/offlineSync/sourceFile/";

    // 保存es失败写入磁盘
    public static final String EVENT_SAVE_FAIL_PATH = "/data/api/eventFailFile/eventFile/";
}
