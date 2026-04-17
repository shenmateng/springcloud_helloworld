package com.fri.constant;

    public class Constant {

        public static final String CONTEXT_PATH = "/learnSrv";

        /**
         * 是、有效、正常、启用、成功、开启
         */
        public static final int IS_YES = 1;

        /**
         * 否、无效、异常、禁用、失败、关闭
         */
        public static final int IS_NO = 0;

        /**
         * 操作系统类型
         * win：0
         * linux：1
         * all：2
         */
        public static final int OS_WIN = 0;
        public static final int OS_LINUX = 1;
        public static final int OS_ALL = 2;

        /**
         * 操作类型：create update delete insert
         */
        public static final String CREATE_TYPE = "create";
        public static final String DELETE_TYPE = "delete";
        public static final String UPDATE_TYPE = "update";
        public static final String INSERT_TYPE = "insert";

        /**
         * operator类型
         */
        public static final String OPERATOR_TYPE = "syncOpType";

        /**
         * 中心id
         */
        public static final String CENTER_ID = "center_id";

        /**
         * byte
         * 0（+I）INSERT
         * 1（-U）UPDATE_BEFORE
         * 2（+U）UPDATE_AFTER
         * 3（-D）DELETE
         */
        public static final int INSERT = 0;
        public static final int UPDATE_BEFORE = 1;
        public static final int UPDATE_AFTER = 2;
        public static final int DELETE = 3;

        /**
         * operator主键
         */
        public static final String OPERATOR_PRI_KEY = "syncOpPriKey";

        /**
         * db相关信息
         */
        public static final String DB_NAME = "databaseName";
        public static final String DB_TABLE = "databaseTable";
}
