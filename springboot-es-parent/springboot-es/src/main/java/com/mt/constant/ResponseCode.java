package com.mt.constant;


import com.mt.bean.page.I18nUtil;
import com.mt.exception.JowtoExceptionResponse;

public enum ResponseCode implements JowtoExceptionResponse {

    //机器
    MACHINE("00101", I18nUtil.get("MACHINE")),
    MACHINE_NOT_EXISTS("0010101",I18nUtil.get("MACHINE_NOT_EXISTS")),
    MACHINE_UUID_NOT_NULL("0010102",I18nUtil.get("MACHINE_UUID_NOT_NULL")),
    MACHINE_APP_PERSON_NOT_EXISTS("0010104",I18nUtil.get("MACHINE_APP_PERSON_NOT_EXISTS")),
    MACHINE_USER_GRANT_MACHINE_COUNT("0010105",I18nUtil.get("MACHINE_USER_GRANT_MACHINE_COUNT")),
    MACHINE_ALL_GRANT_MACHINE_COUNT("0010106",I18nUtil.get("MACHINE_ALL_GRANT_MACHINE_COUNT")),
    OFFLINE_HOSTS_CAN_NOT_BE_RECYCLED("0010107",I18nUtil.get("OFFLINE_HOSTS_CAN_NOT_BE_RECYCLED")),

    //标签
    TAG("00102",I18nUtil.get("TAG")),
    TAG_NAME_REPEAT("0010201",I18nUtil.get("TAG_NAME_REPEAT")),
    TAG_DOING_USE("0010202",I18nUtil.get("TAG_DOING_USE")),
    TAG_NOT_DELETE_AUTH("0010203",I18nUtil.get("TAG_NOT_DELETE_AUTH")),
    TAG_NOT_EXIST("0010204",I18nUtil.get("TAG_NOT_EXIST")),
    TAG_NAME_NOT_NULL("0010205",I18nUtil.get("TAG_NAME_NOT_NULL")),
    TAG_UUID_NOT_NULL("0010206",I18nUtil.get("TAG_UUID_NOT_NULL")),
    TAG_FUNC_UUID_NOT_NULL("0010207",I18nUtil.get("TAG_FUNC_UUID_NOT_NULL")),
    TAG_IS_USED_NOT_DELETE("0010208",I18nUtil.get("TAG_IS_USED_NOT_DELETE")),
    TAG_FUNC_VAL_NOT_NULL("0010209",I18nUtil.get("TAG_FUNC_VAL_NOT_NULL")),
    TAG_NAME_IS_NOT_ALL("0010210",I18nUtil.get("TAG_NAME_IS_NOT_ALL")),
    TAG_NAME_IS_NOT_UN_GROUP("0010210",I18nUtil.get("TAG_NAME_IS_NOT_UN_GROUP")),

    //TASK
    TASK("00103",I18nUtil.get("TASK")),
    TASK_ZC_TYPE_IS_EMPTY("0010301",I18nUtil.get("TASK_ZC_TYPE_IS_EMPTY")),
    TASK_MACHINE_IS_EMPTY("0010302",I18nUtil.get("TASK_MACHINE_IS_EMPTY")),
    TASK_IS_NOT_EXISTS("0010304",I18nUtil.get("TASK_IS_NOT_EXISTS")),
    TASK_IS_NOT_RUN("0010305",I18nUtil.get("TASK_IS_NOT_RUN")),
    TASK_NAME_NOT_NULL("0010306",I18nUtil.get("TASK_NAME_NOT_NULL")),
    TASK_NAME_REPEAT("0010307",I18nUtil.get("TASK_NAME_REPEAT")),
    TASK_UUID_NOT_NULL("0010308",I18nUtil.get("TASK_UUID_NOT_NULL")),
    TASK_ZC_SYS_TASK_LIST("0010309",I18nUtil.get("TASK_ZC_SYS_TASK_LIST")),
    TASK_ZC_SYS_TASK_NOT_EXISTS("0010309",I18nUtil.get("TASK_ZC_SYS_TASK_NOT_EXISTS")),
    TASK_ZC_TASK_STOP_FAIL("0010310",I18nUtil.get("TASK_ZC_TASK_STOP_FAIL")),
    TASK_ZC_TASK_NOT_STOP_NOT_RUN("0010311",I18nUtil.get("TASK_ZC_TASK_NOT_STOP_NOT_RUN")),
    TASK_ZC_SYS_TASK_CREATE_TIMER_FAIL("0010312",I18nUtil.get("TASK_ZC_SYS_TASK_CREATE_TIMER_FAIL")),
    TASK_ZC_TASK_CREATE_TIMER_FAIL("0010313",I18nUtil.get("TASK_ZC_TASK_CREATE_TIMER_FAIL")),
    TASK_IS_NOT_RUN_IS_FORBIDDEN("0010313",I18nUtil.get("TASK_IS_NOT_RUN")),
    TASK_IS_SYSTEM_BUT_NOT_DELETE("0010314",I18nUtil.get("TASK_IS_SYSTEM_BUT_NOT_DELETE")),
    TASK_IN_NO_SCAN_TIME("0010315",I18nUtil.get("TASK_IN_NO_SCAN_TIME")),
    TASK_ABNORMAL_CALL_TO_SSK_TASK("0010316",I18nUtil.get("TASK_ABNORMAL_CALL_TO_SSK_TASK")),
    TASK_TYOTSOTMIE("0010317",I18nUtil.get("TASK_TYOTSOTMIE")),
    TASK_MACHINE_OFFLINE("0010318",I18nUtil.get("TASK_MACHINE_OFFLINE")),
    TASK_SSK_TASK_UUID_IS_NOT_NULL("0010319",I18nUtil.get("TASK_SSK_TASK_UUID_IS_NOT_NULL")),
    TASK_FAIL("0010320",I18nUtil.get("TASK_FAIL")),
    TASK_ENABLE_MUST_HAVE_1("0010321",I18nUtil.get("TASK_ENABLE_MUST_HAVE_1")),

    //ZC
    ZC("00104",I18nUtil.get("ZC")),
    ZC_USER_UUID_NOT_NULL("0010400",I18nUtil.get("ZC_USER_UUID_NOT_NULL")),
    ZC_QUERY_SYNTAX_ERROR("0010401",I18nUtil.get("ZC_QUERY_SYNTAX_ERROR")),
    ZC_SEARCH_QUICK_NAME_REPEAT("0010402",I18nUtil.get("ZC_SEARCH_QUICK_NAME_REPEAT")),
    ZC_SEARCH_QUICK_NOT_EXIST("0010403",I18nUtil.get("ZC_SEARCH_QUICK_NOT_EXIST")),
    ZC_SEARCH_QUICK_UUID_NOT_NUL("0010404",I18nUtil.get("ZC_SEARCH_QUICK_UUID_NOT_NUL")),
    ZC_SEARCH_QUICK_NAME_NOT_NUL("0010405",I18nUtil.get("ZC_SEARCH_QUICK_NAME_NOT_NUL")),
    ZC_SEARCH_QUICK_QUERY_NOT_NUL("0010406",I18nUtil.get("ZC_SEARCH_QUICK_QUERY_NOT_NUL")),
    ZC_QUERY_KEY_WORD_MAX_LEN_ERROR("0010407",I18nUtil.get("ZC_QUERY_KEY_WORD_MAX_LEN_ERROR")),
    ZC_APP_PERSON_NOT_NULL("0010408",I18nUtil.get("ZC_APP_PERSON_NOT_NULL")),
    ZC_STORE_TYPE_NOT_NULL("0010409",I18nUtil.get("ZC_STORE_TYPE_NOT_NULL")),
    ZC_APP_PERSON_IS_NOT_SYS_PERSON("0010410",I18nUtil.get("ZC_APP_PERSON_IS_NOT_SYS_PERSON")),
    ZC_MUST_IS_SYS_PERSON("0010411",I18nUtil.get("ZC_MUST_IS_SYS_PERSON")),


    ZC_ZC_TYPE_NOT_NUL("0010421",I18nUtil.get("ZC_ZC_TYPE_NOT_NUL")),


    ZC_EXPORT_REQUERST_URL_ERROR("0010461",I18nUtil.get("ZC_EXPORT_REQUERST_URL_ERROR")),
    ZC_EXPORT_REQUERST_DATA_ERROR("0010462",I18nUtil.get("ZC_EXPORT_REQUERST_DATA_ERROR")),
    ZC_EXPORT_RESULT_ERROR("0010463",I18nUtil.get("ZC_EXPORT_RESULT_ERROR")),
    ZC_EXPORT_SELECT_FIELDS_NOT_NULL("0010464",I18nUtil.get("ZC_EXPORT_SELECT_FIELDS_NOT_NULL")),
    ZC_IMPORT_RESULT_ERROR("0010471",I18nUtil.get("ZC_IMPORT_RESULT_ERROR")),
    ZC_IMPORT_RESULT_NOT_NULL("0010472",I18nUtil.get("ZC_IMPORT_RESULT_NOT_NULL")),
    ZC_IMPORT_FILE_ERROR("0010473",I18nUtil.get("ZC_IMPORT_FILE_ERROR")),


    ZC_CFG_AUTO_COLLECT_NOT_NULL("0010481",I18nUtil.get("ZC_CFG_AUTO_COLLECT_NOT_NULL")),



    //查询机器列表失败
    ZC_MACHINE_QUERY_FAIL("0010102",I18nUtil.get("ZC_MACHINE_QUERY_FAIL")),
    //查询离线机器列表失败
   OFF_MACHINE_QUERY_FAIL("0010103",I18nUtil.get("OFF_MACHINE_QUERY_FAIL")),
    //运维信息机器列表失败
   OPERATIONALINFO_MACHINE_QUERY_FAIL("0010104",I18nUtil.get("OPERATIONALINFO_MACHINE_QUERY_FAIL")),
    //查询硬盘配置机器列表失败
   HARDWARE_MACHINE_QUERY_FAIL("0010105",I18nUtil.get("HARDWARE_MACHINE_QUERY_FAIL")),




    ZC_DATABASE_MACHINE_DETAIL_QUERY_FAIL("0010106",I18nUtil.get("ZC_DATABASE_MACHINE_DETAIL_QUERY_FAIL")),
    ZC_DATABASE_MACHINE_BASIC_DETAIL_QUERY_FAIL("0010107",I18nUtil.get("ZC_DATABASE_MACHINE_BASIC_DETAIL_QUERY_FAIL")),
    ZC_PORT_QUERY_FAIL("0010108",I18nUtil.get("ZC_PORT_QUERY_FAIL")),
    ZC_PORT_BASIC_QUERY_FAIL("0010109",I18nUtil.get("ZC_PORT_BASIC_QUERY_FAIL")),
    ZC_PORT_MACHINE_QUERY_FAIL("0010110",I18nUtil.get("ZC_PORT_MACHINE_QUERY_FAIL")),



    ZC_PORT_MACHINE_BASIC_QUERY_FAIL("0010111",I18nUtil.get("ZC_PORT_MACHINE_BASIC_QUERY_FAIL")),
    ZC_ENV_QUERY_FAIL("0010112",I18nUtil.get("ZC_ENV_QUERY_FAIL")),
    ZC_ENV_BASIC_QUERY_FAIL("0010113",I18nUtil.get("ZC_ENV_BASIC_QUERY_FAIL")),
    ZC_ENV_MACHINE_QUERY_FAIL("0010114",I18nUtil.get("ZC_ENV_MACHINE_QUERY_FAIL")),
    ZC_ENV_MACHINE_DETAIL_QUERY_FAIL("0010115",I18nUtil.get("ZC_ENV_MACHINE_DETAIL_QUERY_FAIL")),
    ZC_WEB_QUERY_FAIL("0010116",I18nUtil.get("ZC_WEB_QUERY_FAIL")),

    // 187
    ZC_WEB_BASIC_QUERY_FAIL("0010117",I18nUtil.get("ZC_WEB_BASIC_QUERY_FAIL")),
    ZC_WEB_MACHINE_QUERY_FAIL("0010118",I18nUtil.get("ZC_WEB_MACHINE_QUERY_FAIL")),


    ZC_WEB_MACHINE_BASIC_QUERY_FAIL("0010119",I18nUtil.get("ZC_WEB_MACHINE_BASIC_QUERY_FAIL")),
    ZC_WEB_FRAME_MACHINE_DETAIL_QUERY_FAIL("0010186",I18nUtil.get("ZC_WEB_FRAME_MACHINE_DETAIL_QUERY_FAIL")),
    ZC_WEB_FRAME_RISK_DETAIL_QUERY_FAIL("0010187",I18nUtil.get("ZC_WEB_FRAME_RISK_DETAIL_QUERY_FAIL")),
    ZC_WEB_SERVER_DETAIL_QUERY_FAIL("0010188",I18nUtil.get("ZC_WEB_SERVER_DETAIL_QUERY_FAIL")),
    ZC_WEB_SERVER_MACHINE_QUERY_FAIL("0010189",I18nUtil.get("ZC_WEB_SERVER_MACHINE_QUERY_FAIL")),
    ZC_WEB_SERVER_RISK_QUERY_FAIL("0010190",I18nUtil.get("ZC_WEB_SERVER_RISK_QUERY_FAIL")),
    ZC_WEB_SERVER_RISK_DETAIL_QUERY_FAIL("0010191",I18nUtil.get("ZC_WEB_SERVER_RISK_DETAIL_QUERY_FAIL")),
    ZC_WEB_SERVER_QUERY_FAIL("0010192",I18nUtil.get("ZC_WEB_SERVER_QUERY_FAIL")),
    ZC_WEB_SERVER_MACHINE_DETAIL_QUERY_FAIL("0010193",I18nUtil.get("ZC_WEB_SERVER_MACHINE_DETAIL_QUERY_FAIL")),
    ZC_WEB_SERVER_PORT_DETAIL_QUERY_FAIL("0010194",I18nUtil.get("ZC_WEB_SERVER_PORT_DETAIL_QUERY_FAIL")),

    ZC_PORT_MACHINE_DETAIL_QUERY_FAIL("0010195",I18nUtil.get("ZC_PORT_MACHINE_DETAIL_QUERY_FAIL")),


    ZC_PORT_COUNT_QUERY_FAIL("0010196",I18nUtil.get("ZC_PORT_COUNT_QUERY_FAIL")),
    ZC_DATABASE_RISK_QUERY_FAIL("0010197",I18nUtil.get("ZC_DATABASE_RISK_QUERY_FAIL")),



    ZC_COLUMN_QUERY_FAIL("0010198",I18nUtil.get("ZC_COLUMN_QUERY_FAIL")),










    //185



    ZC_WEB_RISK_QUERY_FAIL("0010120",I18nUtil.get("ZC_WEB_RISK_QUERY_FAIL")),
    ZC_WEB_RISK_BASIC_QUERY_FAIL("0010121",I18nUtil.get("ZC_WEB_RISK_BASIC_QUERY_FAIL")),
    ZC_WEB_FRAME_QUERY_FAIL("0010122",I18nUtil.get("ZC_WEB_FRAME_QUERY_FAIL")),
     ZC_WEB_FRAME_BASIC_FRAME_QUERY_FAIL("0010123",I18nUtil.get("ZC_WEB_FRAME_BASIC_FRAME_QUERY_FAIL")),
    ZC_WEB_FRAME_MACHINE_FRAME_QUERY_FAIL("0010124",I18nUtil.get("ZC_WEB_FRAME_MACHINE_FRAME_QUERY_FAIL")),
    ZC_WEB_FRAME_MACHINE_BASIC_FRAME_QUERY_FAIL("0010125",I18nUtil.get("ZC_WEB_FRAME_MACHINE_BASIC_FRAME_QUERY_FAIL")),
    ZC_WEB_FRAME_RISK_FRAME_QUERY_FAIL("0010126",I18nUtil.get("ZC_WEB_FRAME_RISK_FRAME_QUERY_FAIL")),
    ZC_WEB_FRAME_RISK_DEATIL_FRAME_QUERY_FAIL("0010127",I18nUtil.get("ZC_WEB_FRAME_RISK_DEATIL_FRAME_QUERY_FAIL")),

    ZC_KERNEL_MODULE_FRAME_QUERY_FAIL("0010128",I18nUtil.get("ZC_KERNEL_MODULE_FRAME_QUERY_FAIL")),

    ZC_KERNEL_MODULE_BASIC_FRAME_QUERY_FAIL("0010129",I18nUtil.get("ZC_KERNEL_MODULE_BASIC_FRAME_QUERY_FAIL")),
    ZC_KERNEL_MODULE_MACHINE_FRAME_QUERY_FAIL("0010130",I18nUtil.get("ZC_KERNEL_MODULE_MACHINE_FRAME_QUERY_FAIL")),
    ZC_KERNEL_MODULE_MACHINE_DETAIL_FRAME_QUERY_FAIL("0010131",I18nUtil.get("ZC_KERNEL_MODULE_MACHINE_DETAIL_FRAME_QUERY_FAIL")),





    ZC_PLAN_TASK_FRAME_QUERY_FAIL("0010132",I18nUtil.get("ZC_PLAN_TASK_FRAME_QUERY_FAIL")),
    ZC_PLAN_TASK_BASIC_FRAME_QUERY_FAIL("0010133",I18nUtil.get("ZC_PLAN_TASK_BASIC_FRAME_QUERY_FAIL")),
    ZC_PLAN_TASK_MACHINE_FRAME_QUERY_FAIL("0010134",I18nUtil.get("ZC_PLAN_TASK_MACHINE_FRAME_QUERY_FAIL")),


    ZC_PLAN_TASK_MACHINE_DETAIL_FRAME_QUERY_FAIL("0010135",I18nUtil.get("ZC_PLAN_TASK_MACHINE_DETAIL_FRAME_QUERY_FAIL")),





    ZC_USER_FRAME_QUERY_FAIL("0010136",I18nUtil.get("ZC_USER_FRAME_QUERY_FAIL")),
    ZC_USER_BASIC_FRAME_QUERY_FAIL("0010137",I18nUtil.get("ZC_USER_BASIC_FRAME_QUERY_FAIL")),

    ZC_SOFTWARE_APP_DETAIL_FRAME_QUERY_FAIL("0010179",I18nUtil.get("ZC_SOFTWARE_APP_DETAIL_FRAME_QUERY_FAIL")),
    ZC_SOFTWARE_APP_PROCESS_COUNT_FRAME_QUERY_FAIL("0010180",I18nUtil.get("ZC_SOFTWARE_APP_PROCESS_COUNT_FRAME_QUERY_FAIL")),

    ZC_START_SERVER_MACHINE_DETAIL_FRAME_QUERY_FAIL("0010181",I18nUtil.get("ZC_START_SERVER_MACHINE_DETAIL_FRAME_QUERY_FAIL")),


    ZC_START_SERVER_MACHINE_FRAME_QUERY_FAIL("0010182",I18nUtil.get("ZC_START_SERVER_MACHINE_FRAME_QUERY_FAIL")),
    ZC_START_SERVER_FRAME_QUERY_FAIL("0010183",I18nUtil.get("ZC_START_SERVER_FRAME_QUERY_FAIL")),
    ZC_START_SERVER_DETAIL_FRAME_QUERY_FAIL("0010184",I18nUtil.get("ZC_START_SERVER_DETAIL_FRAME_QUERY_FAIL")),
    ZC_KERNEL_MODULE_DETAIL_FRAME_QUERY_FAIL("0010185",I18nUtil.get("ZC_KERNEL_MODULE_DETAIL_FRAME_QUERY_FAIL")),









    ZC_SOFTWARE_APP_FRAME_QUERY_FAIL("0010181",I18nUtil.get("ZC_SOFTWARE_APP_FRAME_QUERY_FAIL")),
    //178
    ZC_USER_MACHINE_FRAME_QUERY_FAIL("0010138",I18nUtil.get("ZC_USER_MACHINE_FRAME_QUERY_FAIL")),





    ZC_USER_MACHINE_DETAIL_FRAME_QUERY_FAIL("0010139",I18nUtil.get("ZC_USER_MACHINE_DETAIL_FRAME_QUERY_FAIL")),
    ZC_USER_DETAIL_FRAME_QUERY_FAIL("0010140",I18nUtil.get("ZC_USER_DETAIL_FRAME_QUERY_FAIL")),
    ZC_USER_RISK_FRAME_QUERY_FAIL("0010141",I18nUtil.get("ZC_USER_RISK_FRAME_QUERY_FAIL")),
    ZC_USER_RISK_DETAIL_FRAME_QUERY_FAIL("0010142",I18nUtil.get("ZC_USER_RISK_DETAIL_FRAME_QUERY_FAIL")),
    ZC_MACHINE_COUNT_QUERY_FAIL("0010143",I18nUtil.get("ZC_MACHINE_COUNT_QUERY_FAIL")),

    // 10160
    ZC_SINGLE_INSTALL_PACKAGE_QUERY_FAIL("0010160",I18nUtil.get("ZC_SINGLE_INSTALL_PACKAGE_QUERY_FAIL")),
    ZC_SINGLE_KERNEL_MODULE_QUERY_FAIL("0010161",I18nUtil.get("ZC_SINGLE_KERNEL_MODULE_QUERY_FAIL")),
    ZC_SINGLE_WEB_SERVER_QUERY_FAIL("0010162",I18nUtil.get("ZC_SINGLE_WEB_SERVER_QUERY_FAIL")),
    ZC_SINGLE_WEB_FRAME_QUERY_FAIL("0010163",I18nUtil.get("ZC_SINGLE_WEB_FRAME_QUERY_FAIL")),
    ZC_SINGLE_PLAN_TASK_QUERY_FAIL("0010164",I18nUtil.get("ZC_SINGLE_PLAN_TASK_QUERY_FAIL")),
    ZC_SINGLE_DATABASE_QUERY_FAIL("0010165",I18nUtil.get("ZC_SINGLE_DATABASE_QUERY_FAIL")),
    ZC_SINGLE_DATABASE_NAME_QUERY_FAIL("0010166",I18nUtil.get("ZC_SINGLE_DATABASE_NAME_QUERY_FAIL")),
    ZC_SINGLE_PROCESS_QUERY_FAIL("0010167",I18nUtil.get("ZC_SINGLE_PROCESS_QUERY_FAIL")),
    ZC_SINGLE_HARDWARE_QUERY_FAIL("0010168",I18nUtil.get("ZC_SINGLE_HARDWARE_QUERY_FAIL")),
    ZC_SINGLE_MACHINE_QUERY_FAIL("0010169",I18nUtil.get("ZC_SINGLE_MACHINE_QUERY_FAIL")),
    ZC_SINGLE_USER_QUERY_FAIL("0010170",I18nUtil.get("ZC_SINGLE_USER_QUERY_FAIL")),
    ZC_SINGLE_USER_NAME_QUERY_FAIL("0010171",I18nUtil.get("ZC_SINGLE_USER_NAME_QUERY_FAIL")),
    ZC_SINGLE_PORT_QUERY_FAIL("0010172",I18nUtil.get("ZC_SINGLE_PORT_QUERY_FAIL")),
    ZC_SINGLE_WEB_QUERY_FAIL("0010173",I18nUtil.get("ZC_SINGLE_WEB_QUERY_FAIL")),
    ZC_SINGLE_WEB_DOMAIN_QUERY_FAIL("0010173",I18nUtil.get("ZC_SINGLE_WEB_DOMAIN_QUERY_FAIL")),
    ZC_SINGLE_ENV_QUERY_FAIL("0010174",I18nUtil.get("ZC_SINGLE_ENV_QUERY_FAIL")),




    ZC_PROCESS_COUNT_QUERY_FAIL("0010144",I18nUtil.get("ZC_PROCESS_COUNT_QUERY_FAIL")),
    ZC_DATABASE_COUNT_QUERY_FAIL("0010145",I18nUtil.get("ZC_DATABASE_COUNT_QUERY_FAIL")),
    ZC_KERNEL_MODULE_COUNT_QUERY_FAIL("0010146",I18nUtil.get("ZC_KERNEL_MODULE_COUNT_QUERY_FAIL")),

    ZC_WEB_COUNT_QUERY_FAIL("0010147",I18nUtil.get("ZC_WEB_COUNT_QUERY_FAIL")),
    ZC_WEB_FRAME_COUNT_QUERY_FAIL("0010148",I18nUtil.get("ZC_WEB_FRAME_COUNT_QUERY_FAIL")),
    ZC_PLAN_TASK_COUNT_QUERY_FAIL("0010149",I18nUtil.get("ZC_PLAN_TASK_COUNT_QUERY_FAIL")),
    ZC_ENV_COUNT_QUERY_FAIL("0010150",I18nUtil.get("ZC_ENV_COUNT_QUERY_FAIL")),

//    ZC_DATABASE_COUNT_QUERY_FAIL("0020145",I18nUtil.get("ZC_DATABASE_COUNT_QUERY_FAIL")),
//    ZC_KERNEL_MODULE_COUNT_QUERY_FAIL("0020146",I18nUtil.get("ZC_KERNEL_MODULE_COUNT_QUERY_FAIL")),
    ZC_NET_WORK_QUERY_FAIL("0010151",I18nUtil.get("ZC_NET_WORK_QUERY_FAIL")),
    ZC_NET_WORK_DETAIL_QUERY_FAIL("0010159",I18nUtil.get("ZC_NET_WORK_DETAIL_QUERY_FAIL")),
    ZC_PROCESS_QUERY_FAIL("0010152",I18nUtil.get("ZC_PROCESS_QUERY_FAIL")),
    ZC_PROCESS_MACHINE_QUERY_FAIL("0010153",I18nUtil.get("ZC_PROCESS_MACHINE_QUERY_FAIL")),
    ZC_PROCESS_MACHINE_DETAIL_QUERY_FAIL("0010154",I18nUtil.get("ZC_PROCESS_MACHINE_DETAIL_QUERY_FAIL")),
    ZC_DATABASE_QUERY_FAIL("0010155",I18nUtil.get("ZC_DATABASE_QUERY_FAIL")),
    ZC_DATABASE_DETAIL_QUERY_FAIL("0010156",I18nUtil.get("ZC_DATABASE_DETAIL_QUERY_FAIL")),
    ZC_DATABASE_RISK_DETAIL_QUERY_FAIL("0010157",I18nUtil.get("ZC_DATABASE_RISK_DETAIL_QUERY_FAIL")),
    ZC_PROCESS_DETAIL_QUERY_FAIL("0010158",I18nUtil.get("ZC_PROCESS_DETAIL_QUERY_FAIL")),

    ZC_WEB_SERVER_COUNT_QUERY_FAIL("0010159",I18nUtil.get("ZC_WEB_SERVER_COUNT_QUERY_FAIL")),

    ZC_WINDOWS_INSTALL_PACKAGE_FRAME_QUERY_FAIL("0010175",I18nUtil.get("ZC_WINDOWS_INSTALL_PACKAGE_FRAME_QUERY_FAIL")),
    ZC_WINDOWS_INSTALL_PACKAGE_DETAIL_FRAME_QUERY_FAIL("0010176",I18nUtil.get("ZC_WINDOWS_INSTALL_PACKAGE_DETAIL_FRAME_QUERY_FAIL")),
    ZC_WINDOWS_INSTALL_PACKAGE_MACHINE_QUERY_FAIL("0010177",I18nUtil.get("ZC_WINDOWS_INSTALL_PACKAGE_MACHINE_QUERY_FAIL")),
    ZC_WINDOWS_INSTALL_PACKAGE_MACHINE_DETAIL_QUERY_FAIL("0010178",I18nUtil.get("ZC_WINDOWS_INSTALL_PACKAGE_MACHINE_DETAIL_QUERY_FAIL")),


    AUTHORIZE("00108",I18nUtil.get("AUTHORIZE")),
    AUTHORIZE_FILE_ILLEGAL("0010801", I18nUtil.get("AUTHORIZE_FILE_ILLEGAL")),
    AUTHORIZE_FILE_UPLOAD_EXCEPTION("0010802",I18nUtil.get("AUTHORIZE_FILE_UPLOAD_EXCEPTION")),
    AUTHORIZE_COUNT_EXCEPTION("0010803", I18nUtil.get("AUTHORIZE_COUNT_EXCEPTION")),
    AUTHORIZE_TIME_ILLEGAL("0010804", I18nUtil.get("AUTHORIZE_TIME_ILLEGAL")),
    AUTHORIZE_FILE_NOT_NULL("0010805", I18nUtil.get("AUTHORIZE_FILE_NOT_NULL")),
    AUTHORIZE_FUNCTION_MODULE_NOT_NULL("0010806", I18nUtil.get("AUTHORIZE_FUNCTION_MODULE_NOT_NULL")),
    AUTHORIZE_FILE_ILLEGAL_PLEASE_AGAIN_EXPORT("0010807", I18nUtil.get("AUTHORIZE_FILE_ILLEGAL_PLEASE_AGAIN_EXPORT")),

    PARAM_TIME_MINUTE_TIMEPREFIX_ERROR("0010806", I18nUtil.get("PARAM_TIME_MINUTE_TIMEPREFIX_ERROR")),
    PARAM_TIME_HOUR_TIMEPREFIX_ERROR("0010807", I18nUtil.get("PARAM_TIME_HOUR_TIMEPREFIX_ERROR")),
    ES_QUERY_DATA_ERROR("0010808", I18nUtil.get("ES_QUERY_DATA_ERROR")),
    ES_QUERY_REFLEX_ERROR("0010809", I18nUtil.get("ES_QUERY_REFLEX_ERROR")),

    ZC_MACHINE_NULL("0010890",I18nUtil.get("ZC_MACHINE_NULL")),
    ZC_NET_WORK_ID_IS_NULL("0010891",I18nUtil.get("ZC_NET_WORK_ID_IS_NULL")),
    ZC_MACHINE_IMPORT_APPLY_NAME_ERROR("0010892",I18nUtil.get("ZC_MACHINE_IMPORT_APPLY_NAME_ERROR")),
    ZC_MACHINE_IMPORT_ERROR("0010893",I18nUtil.get("ZC_MACHINE_IMPORT_ERROR")),
    ZC_MACHINE_IP_IS_NULL("0010894",I18nUtil.get("ZC_MACHINE_IP_IS_NULL")),
    ZC_MACHINE_IP_ERROR("0010895",I18nUtil.get("ZC_MACHINE_IP_ERROR")),
    UPDATE_ERROR("0010896",I18nUtil.get("UPDATE_ERROR")),
    ZC_MACHINE_IMPORT_0("0010897",I18nUtil.get("ZC_MACHINE_IMPORT_0")),
    ZC_MACHINE_IMPORT_TITLE_ERROR("0010898",I18nUtil.get("ZC_MACHINE_IMPORT_TITLE_ERROR")),

    USER_NO_HAVE_AGENT_INSTALL("0010899", I18nUtil.get("USER_NO_HAVE_AGENT_INSTALL")),
    MACHINE_BOUND_USER("0010900", I18nUtil.get("MACHINE_BOUND_USER"))



    ;
    private final String code;
    private final String msg;


    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }}
