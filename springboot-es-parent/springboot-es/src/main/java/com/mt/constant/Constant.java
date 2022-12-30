package com.mt.constant;


import com.mt.bean.page.I18nUtil;

/**
 * @author zhaolm
 * @data 2019/1/28
 */
public class Constant {

    public static final String SERVICE_NAME = "http://asset-service";

    public static final String CONTEXT_PATH = "/assetSrv";

    public static final String GK_PATH = "/gk";

    /**
     * 是、有效、正常、启用
     */
    public static final int IS_YES = 1;
    /**
     * 否、无效、异常、禁用
     */
    public static final int IS_NO = 0;

    public static final int SYNC_RATE = 40;

    public static final long DAY_MS = 86400000;

    public static final int SIZE_MAX = 10000;
    /**
     * 虚拟用户ID
     * 备注：安装agent时候，每分钟执行定时任务扫描在线机器发送通知
     */
    public static final String SPECIAL_ID = "00000000000000000000000000000000";
    /**
     * 任务收集当中分组的概念，相当于未分组
     * 备注：创建用户的时候
     */
    public static final String SPECIAL_ALL_ID = "11111111111111111111111111111111";

    public static final String STRING_DATA_EMPTY = I18nUtil.get("NO_DATA");

    /**
     * ES的zc_machine名称
     */
    public static final String ES_MACHINE_INDEX = "zc_machine";

    /**
     * ES的zc_web名称
     */
    public static final String ES_WEB_INDEX = "zc_web";
    public static final String ES_USER_INDEX = "zc_user";

    public static final String ES_APP_INDEX = "zc_software_app";
    public static final String ES_DATABASE_INDEX = "zc_database";
    public static final String ES_FRAME_INDEX = "zc_web_frame";
    public static final String ES_WEB_SERVER_INDEX = "zc_web_server";

    /**
     * ES的zc_machine类型
     */
    public static final String ES_TYPE = "_doc";

    public static final String ES_ID ="id";

    /**
     * 端口状态
     */
    public static final String PORT_STATUS = "portStatus";
    /**
     * 运行用户
     */
    public static final String START_USER = "startUser";
    public static final String START_USER_KEYWORD = "startUser.keyword";
    public static final String START_USERS = "startUsers";
    public static final String COMPANY = "company";

    /**
     * 端口协议
     */
    public static final String ZC_PROTOCAL = "protocal";
    public static final String ZC_PROTOCAL_KEYWORD = "protocal.keyword";
    public static final String ZC_PROTOCALS = "protocol";
    /**
     * 数据库名
     */
    public static final String DATABASE_NAME = "databaseName";
    public static final String DATABASE_NAME_KEYWORD = "databaseName.keyword";
    public static final String DATABASE_NAMES = "databaseNames";
    /**
     * 协议
     */
    public static final String ZC_PORT = "port";
    public static final String PORT_KEYWORD = "port.keyword";
    public static final String ZC_PORTS = "ports";
    /**
     * 发布者
     */
    public static final String ZC_PUBLISHER = "publisher";
    public static final String ZC_PUBLISHER_KEYWORD = "publisher.keyword";
    public static final String ZC_PUBLISHERS = "publishers";
    /**
     * 安装包版本
     */
    public static final String INSTALL_PACKAGE_VERSION = "installPackageVersion";
    public static final String INSTALL_PACKAGE_VERSION_KEYWORD = "installPackageVersion.keyword";

    public static final String INSTALL_PACKAGE_VERSIONS = "installPackageVersions";

    /**
     * 安装包类型
     */
    public static final String INSTALL_PACKAGE_TYPE = "installPackageType";
    public static final String INSTALL_PACKAGE_TYPE_KEYWORD = "installPackageType.keyword";
    public static final String INSTALL_PACKAGE_TYPES = "installPackageTypes";

    public static final String SOURCE = "source";
    /**
     * 安装包名
     */
    public static final String INSTALL_PACKAGE_NAME = "installPackageName";
    /**
     * 安装包名
     */
    public static final String INSTALL_PACKAGE_NAME_KEYWORD = "installPackageName.keyword";
    /**
     * 安装路径
     */
    public static final String INSTALL_PACKAGE_PATH = "installPackagePath";

    public static final String KERNEL_MODULE_PATH = "kernelModulePath";
    /**
     * 机器名
     */
    public static final String MACHINE_NAME = "machineName";
    /**
     * ip地址
     */
    public static final String MACHINE_IPS_IP = "machineIps.ip";

    public static final String MACHINE_IPS_IP_LONG = "machineIps.ipLong";

    /**
     * 主IP
     */
    public static final String MACHINE_PRIMARY_IPS_IP = "machinePrimaryIps.ip";

    /**
     * 源路径
     */
    public static final String INSTALL_PATH = "installPath";


    /**
     * 连接状态
     */
    public static final String CONNECT_STATUS = "connectStatus";

    public static final String CONNECT_STATUS_KEYWORD = "connectStatus.keyword";
    /**
     * 目标端口
     */
    public static final String DST_PORT = "dstPort";
    public static final String DST_PORT_KEYWORD = "dstPort.keyword";
    public static final String DST_PORTS = "dstPorts";


    /**
     * 执行用户
     */
    public static final String EXEC_USER = "execUser";
    public static final String EXEC_USER_KEYWORD = "execUser.keyword";
    public static final String EXEC_USERS = "execUsers";
    public static final String START_TIME_KEYWORD = "startTime.keyword";

    /**
     * 进程版本
     */
    public static final String PROCESS_VERSION = "processVersion";
    public static final String PROCESS_VERSION_KEYWORD = "processVersion.keyword";
    public static final String PROCESS_VERSIONS = "processVersions";

    /**
     * root权限
     */
    public static final String IS_ADMIN = "isAdmin";
    public static final String IS_ADMINS = "isAdmins";

    /**
     * 进程类型
     */
    public static final String PROCESS_TYPE = "processType";
    public static final String PROCESS_TYPE_KEYWORD = "processType.keyword";
    public static final String PROCESS_TYPES = "processTypes";
    public static final String IS_INTERACTIVE = "isInteractive";


    /**
     * 应用版本
     */
    public static final String APP_VERSION = "appVersion";
    public static final String APP_VERSION_KEYWORD = "appVersion.keyword";
    public static final String APP_VERSIONS = "appVersions";

    /**
     * 应用类型
     */
    public static final String APP_TYPE = "appType";
    public static final String APP_TYPE_KEYWORD = "appType.keyword";
    public static final String APP_TYPES = "appTypes";


    /**
     * 启动状态
     */
    public static final String START_STATUS = "startStatus";
    public static final String START_STATUS_KEYWORD = "startStatus.keyword";

    /**
     * 启动类型
     */
    public static final String START_TYPE = "startType";
    public static final String START_TYPE_KEYWORD = "startType.keyword";
    public static final String START_TYPES = "startTypes";

    /**
     * 交互登陆
     */
    public static final String INTERACTIVE_LOGIN = "interactiveLogin";
    public static final String INTERACTIVE_LOGIN_KEYWORD = "interactiveLogin.keyword";
    public static final String INTERACTIVE_LOGINS = "interactiveLogins";


    /**
     * 用户状态
     */
    public static final String USER_STATUS = "userStatus";


    /**
     * 服务类型
     */
    public static final String SERVER_TYPE = "serverType";
    public static final String SERVER_TYPE_KEYWORD = "serverType.keyword";
    public static final String SERVER_TYPES = "serverTypes";


    /**
     * 框架版本
     */
    public static final String WEB_FRAME_VERSION = "webFrameVersion";
    public static final String WEB_FRAME_VERSION_KEYWORD = "webFrameVersion.keyword";
    public static final String WEB_FRAME_VERSIONS = "webFrameVersions";

    /**
     * web框架路径
     */
    public static final String WEB_FRAME_PATH = "webFramePath";


    /**
     * 服务名
     */
    public static final String WEB_SERVER_NAME = "webServerName";
    public static final String WEB_SERVER_NAME_KEYWORD = "webServerName.keyword";
    public static final String WEB_SERVER_NAMES = "webServerNames";

    /**
     * 启动服务
     */
    public static final String START_ITEM = "startItem";
    public static final String SERVER_DESC = "serverDesc";
    public static final String SERVER_NAME = "serverName";
    public static final String START_ITEM_KEYWORD = "startItem.keyword";

    public static final String START_LEVEL = "startLevel";
    public static final String START_LEVEL_KEYWORD = "startLevel.keyword";
    public static final String START_LEVELS = "startLevels";

    public static final String APP_NAME = "appName";
    public static final String APP_NAME_KEYWORD = "appName.keyword";


    /**
     * 服务类型
     */
    public static final String WEB_SERVER_TYPE = "webServerType";
    public static final String WEB_SERVER_TYPE_KEYWORD = "webServerType.keyword";
    public static final String WEB_SERVER_TYPES = "webServerTypes";


    public static final String COUNT_DETAIL = "countDetail";

    /**
     * 条件
     */
    public static final String USER_DATAS = "userDatas";
    public static final String USER_DATAS_USER_UUID = "userDatas.userUuid";
    public static final String USER_DATAS_USER_UUID_KEYWORD = "userDatas.userUuid.keyword";
    public static final String USER_DATAS_ROLE_FLAG = "userDatas.roleFlag";//0 应用、1 系统、2 管控上的(暂时不知道)、-1 默认
    public static final String USER_DATAS_AGENT_INSTALL = "userDatas.agentInstall";//1 有安装权限、0 无安装权限

    /**
     * 数据库类型条件
     */
    public static final String DATABASE_TYPE = "databaseType";
    public static final String DATABASE_TYPE_KEYWORD = "databaseType.keyword";
    public static final String MACHINE_COUNT = "machineCount";
    public static final String MACHINE_UUID_KEYWORD = "machineUuid.keyword";
    public static final String USER_DATAS_MACHINETAGS_KEYWORD = "userDatas.machineTags.keyword";

    /**
     * 内核模块名称
     */
    public static final String KERNEL_MODULE_NAME_KEYWORD = "kernelModuleName.keyword";

    public static final String KERNEL_MODULE_NAME = "kernelModuleName";
    /**
     * 环境变量名
     */
    public static final String ENV_NAME_KEYWORD = "envName.keyword";
    public static final String ENV_NAME = "envName";
    public static final String ENV_VAL = "envVal";
    /**
     * 进程名称
     */
    public static final String PROCESS_NAME = "processName";
    public static final String PROCESS_NAME_KEYWORD = "processName.keyword";
    public static final String PROCESS = "process";
    public static final String RUN_STATUS = "runStatus";

    /**
     * web框架名
     */
    public static final String WEB_FRAME_NAME = "webFrameName";
    public static final String WEB_FRAME_NAME_KEYWORD = "webFrameName.keyword";
    /**
     * 安装时间
     */
    public static final String INSTALL_TIME = "installTime";
    public static final String INSTALL_TIME_KEYWORD = "installTime.keyword";
    public static final String INSTALL_TIMES = "installTimes";

    public static final String KEYWORD = ".keyword"; //.keyword
    public static final String HITS = "hits";
    public static final String TOTAL = "total";
    public static final String VALUE = "value";
    //域名
    public static final String DOMAIN = "domain";
    public static final String DOMAIN_KEYWORD = "domain.keyword";
    /**
     * 绑定地址
     */
    public static final String BIND_IP = "bindIp";
    public static final String MAIN_PATH = "mainPath";
    public static final String PROCESS_PERMISSION = "processPermission";
    public static final String PROCESS_PATH = "processPath";
    public static final String PROCESS_PATH_KEYWORD = "processPath.keyword";
    public static final String SOFTWARE_PACKAGE = "softwarePackage";
    public static final String RUN_USER = "runUser";
    public static final String START_PARAM = "startParam";

    /**
     * 配置文件路径
     */
    public static final String CONFIG_PATH = "configPath";
    public static final String SCRIPT_PATH = "scriptPath";
    /**
     * 日志文件路径
     */
    public static final String LOG_PATH = "logPath";
    /**
     * 数据路径
     */
    public static final String DATA_PATH = "dataPath";

    /**
     * 系统类型
     */
    public static final String OS_TYPE = "osType";

    public static final String ONLINE_TIME = "onlineTime";

    public static final String OFFLINE_TIME = "offlineTime";

    /**
     * 在线状态
     */
    public static final String ONLINE_STATUS = "onlineStatus";

    public static final String USER_NAME = "userName";
    public static final String USER_NAME_KEYWORD = "userName.keyword";

    /**
     * 计划任务名
     */
    public static final String PLAN_TASK_NAME = "planTaskName";
    public static final String PLAN_TASK_NAME_KEYWORD = "planTaskName.keyword";
    public static final String CMD_LINE = "cmdline";

    public static final String PID = "pid";
    // 风险名
    public static final String RISK_NAME = "riskName";

    public static final String RISK_WEBSHELL = "webshell";

    public static final String RISK_VULNERABILITY = "vulnerability";

    public static final String RISK_WEAKPASSWORD = "weak_password";

    public static final String ZC_FUNC_MACHINE_DEF_VAL = "22222222222222222222222222222222";

    public static final String ZC_FUNC_MACHINE_DEF_UUID_VAL = "33333333333333333333333333333333";

    /**
     * 新版agent版本号
     */
    public static final String AGENT_VERSION = "3.1.23.12";

    /**
     * 是否卸载 ,0-未卸载，1-已卸载
     */
    public static final String IF_DELETE = "ifDelete";

}
