package com.fri.common;

import com.alibaba.fastjson.JSON;
import com.fri.constant.Constant;
import com.fri.utils.DruidUtils;
import com.fri.utils.MysqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.base.VoidSerializer;
import org.apache.flink.api.java.typeutils.runtime.kryo.KryoSerializer;
import org.apache.flink.streaming.api.functions.sink.TwoPhaseCommitSinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 陈余银
 * @create : 2022-06-06 14:53:00
 * @description :
 */
public class MysqlSinkPlus extends TwoPhaseCommitSinkFunction<String, DruidUtils, Void> {

    private String url;

    private String userName;

    private String passWord;

    private String driverClassName;

    private static final Logger log = LoggerFactory.getLogger(MysqlSinkPlus.class);

    public MysqlSinkPlus() {
        super(new KryoSerializer<>(DruidUtils.class, new ExecutionConfig()), VoidSerializer.INSTANCE);
    }

    public MysqlSinkPlus(String url, String userName, String passWord, String driverClassName) {
        super(new KryoSerializer<>(DruidUtils.class, new ExecutionConfig()), VoidSerializer.INSTANCE);
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
        this.driverClassName = driverClassName;
    }

    /**
     * 执行数据库入库操作  task初始化的时候调用
     *
     * @param druidUtils
     * @param mapString
     * @param context
     * @throws Exception
     */
    @Override
    protected void invoke(DruidUtils druidUtils, String mapString, Context context) {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            HashMap<String, Object> data = JSON.parseObject(mapString, HashMap.class);

            String syncDbName = (String) data.get(Constant.DB_NAME);
            String syncTableName = (String) data.get(Constant.DB_TABLE);

            if ("ys_user".equals(syncDbName) && "system_menu".equals(syncTableName)) {
                syncTableName = "system_menu_gk";
            }
            if ("ys_user".equals(syncDbName) && "system_user_info".equals(syncTableName)) {
                syncTableName = "system_user_info_gk";
            }
            if ("ys_user".equals(syncDbName) && "system_role".equals(syncTableName)) {
                syncTableName = "system_role_gk";
            }
            if ("ys_user".equals(syncDbName) && "system_role_menu".equals(syncTableName)) {
                syncTableName = "system_role_menu_gk";
            }

            String descSql = "desc " + syncDbName + "." + syncTableName;
            Connection conn = druidUtils.getConn(url, userName, passWord, driverClassName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(descSql);

            List<String> fieldNameList = new ArrayList<>();
            Map<String, String> fieldTypeMap = new HashMap<>();
            List<String> priKeyList = new ArrayList<>();
            while (resultSet.next()) {
                String fieldName = resultSet.getString("Field");
                String fieldType = resultSet.getString("Type");
                String fieldKey = resultSet.getString("Key");
                if ("PRI".equals(fieldKey)) {
                    priKeyList.add(fieldName);
                }
                fieldTypeMap.put(fieldName, fieldType);
                fieldNameList.add(fieldName);
            }
            String opType = (String) data.get(Constant.OPERATOR_TYPE);
            sql = spliteSql(sql, data, syncDbName, syncTableName, fieldNameList, fieldTypeMap, opType, priKeyList);
//            String strSql = sql.replaceAll("\\\\", "\\\\\\\\");
            if (!StringUtils.isEmpty(sql)) {
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            log.info("Synchronize Fail SQL: {}", sql);
            log.info("Sync Fail original str: {}", mapString);
            log.info("Sync Fail Info", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String spliteSql(String sql, HashMap<String, Object> data, String syncDbName, String syncTableName, List<String> fieldNameList, Map<String, String> fieldTypeMap, String opType, List<String> priKeyList) {
        if (Constant.INSERT_TYPE.equals(opType)) {
            StringBuilder insertFieldNames = new StringBuilder();
            StringBuilder insertDatas = new StringBuilder();
            for (String fields : fieldNameList) {
                Object value = data.get(fields);
                String fieldType = fieldTypeMap.get(fields);
                if (value != null && !("".equals(value.toString()))) {
                    value = formatObject(value);
                } else {
                    value = "null";
                }
                if (MysqlUtil.isNumType(fieldType) || "null".equals(value.toString())) {
                    insertDatas.append(value).append(",");
                } else if (fieldType.contains("datetime") || fieldType.contains("date") || fieldType.contains("timestamp")) {
                    insertDatas.append("'").append(value.toString()).append("',");
                } else {
                    insertDatas.append("'").append(value).append("',");
                }
                insertFieldNames.append("`").append(fields).append("`").append(",");
            }
            sql = "insert into " + syncDbName + "." + syncTableName + "(" + insertFieldNames.substring(0, (insertFieldNames.length() - 1)) + ") " +
                    "values(" + insertDatas.substring(0, insertDatas.length() - 1) + ");";
        } else if (Constant.UPDATE_TYPE.equals(opType)) {
            StringBuilder updateDatas = new StringBuilder();
            for (String fields : fieldNameList) {
                Object value = data.get(fields);
                String fieldType = fieldTypeMap.get(fields);
                if (value != null && !("".equals(value.toString()))) {
                    value = formatObject(value);
                } else {
                    value = "null";
                }
                if (MysqlUtil.isNumType(fieldType) || "null".equals(value.toString())) {
                    updateDatas.append("`").append(fields).append("`").append("=").append(value).append(",");
                } else {
                    updateDatas.append("`").append(fields).append("`").append("=").append("'").append(value.toString()).append("',");
                }
            }
            String centerId = (String) data.get(Constant.CENTER_ID);
            StringBuilder priDatas = getStringBuilder(data, fieldTypeMap, priKeyList);
            sql = "update " + syncDbName + "." + syncTableName + " set " + updateDatas.substring(0, updateDatas.length() - 1) +
                    " where `center_id` = '" + centerId + "'" + priDatas.substring(0, priDatas.length() - 1) + ";";
        } else if (Constant.DELETE_TYPE.equals(opType)) {
            String centerId = (String) data.get(Constant.CENTER_ID);
            StringBuilder priDatas = getStringBuilder(data, fieldTypeMap, priKeyList);
            sql = "delete from " + syncDbName + "." + syncTableName + " where `center_id` = '" + centerId + "'"
                    + priDatas.substring(0, priDatas.length() - 1) + ";";
        }
        return sql;
    }

    private StringBuilder getStringBuilder(HashMap<String, Object> data, Map<String, String> fieldTypeMap, List<String> priKeyList) {
        StringBuilder priDatas = new StringBuilder();
        for (String pri : priKeyList) {
            if (Constant.CENTER_ID.equals(pri)) {
                continue;
            }
            String fieldType = fieldTypeMap.get(pri);
            if (MysqlUtil.isNumType(fieldType)) {
                priDatas.append(" and ").append("`").append(pri).append("` = ").append(data.get(pri)).append(" ");
            } else {
                priDatas.append(" and ").append("`").append(pri).append("` = '").append(data.get(pri)).append("' ");
            }
        }
        return priDatas;
    }

    private Object formatObject(Object value) {
        String val = value.toString();
        if (val.contains(":") || val.equals("\\")) {
            value = val.replaceAll("\\\\", "\\\\\\\\");
        } else {
            value = val.replaceAll("\'", "\\\\'");
        }
        return value;
    }

    @Override
    protected DruidUtils beginTransaction() {
        //log.info("start beginTransaction.......");
        return new DruidUtils();
    }

    @Override
    protected void preCommit(DruidUtils druidUtils) {
        //log.info("start preCommit...");
    }

    @Override
    protected void commit(DruidUtils druidUtils) {
        //log.info("start commit...");
        druidUtils.commit();
    }

    @Override
    protected void abort(DruidUtils druidUtils) {
        //log.info("start abort rollback...");
        druidUtils.rollBack();
    }
}
