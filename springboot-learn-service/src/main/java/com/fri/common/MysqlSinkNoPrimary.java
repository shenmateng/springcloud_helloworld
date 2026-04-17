package com.fri.common;

import com.alibaba.fastjson.JSON;
import com.fri.constant.Constant;
import com.fri.utils.DateUtil;
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
import java.util.*;

/**
 * @author : 陈余银
 * @create : 2022-06-07 10:02:00
 * @description :
 */
public class MysqlSinkNoPrimary extends TwoPhaseCommitSinkFunction<String, DruidUtils, Void> {

    private String url;

    private String userName;

    private String passWord;

    private String driverClassName;

    private String syncDbName;

    private String syncTableName;

    private static final Logger log = LoggerFactory.getLogger(MysqlSinkPlus.class);

    public MysqlSinkNoPrimary() {
        super(new KryoSerializer<>(DruidUtils.class, new ExecutionConfig()), VoidSerializer.INSTANCE);
    }

    public MysqlSinkNoPrimary(String url, String userName, String passWord, String driverClassName, String syncDbName, String syncTableName) {
        super(new KryoSerializer<>(DruidUtils.class, new ExecutionConfig()), VoidSerializer.INSTANCE);
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
        this.driverClassName = driverClassName;
        this.syncDbName = syncDbName;
        this.syncTableName = syncTableName;
    }

    @Override
    protected void invoke(DruidUtils druidUtils, String mapString, Context context) {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            HashMap<String, Object> data = JSON.parseObject(mapString, HashMap.class);

            String descSql = "desc " + syncDbName + "." + syncTableName;

            Connection conn = druidUtils.getConn(url, userName, passWord, driverClassName);
            statement = conn.createStatement();
            resultSet = statement.executeQuery(descSql);

            List<String> fieldNameList = new ArrayList<>();
            Map<String, String> fieldTypeMap = new HashMap<>();
            while (resultSet.next()) {
                String fieldName = resultSet.getString("Field");
                String fieldType = resultSet.getString("Type");
                fieldTypeMap.put(fieldName, fieldType);
                fieldNameList.add(fieldName);
            }

            if (Constant.INSERT_TYPE.equals(data.get(Constant.OPERATOR_TYPE))) {
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
                        insertDatas.append("'").append(DateUtil.convertTDate(value.toString())).append("',");
                    } else {
                        insertDatas.append("'").append(value).append("',");
                    }
                    insertFieldNames.append("`").append(fields).append("`").append(",");
                }
                sql = "insert into " + syncDbName + "." + syncTableName + "(" + insertFieldNames.substring(0, (insertFieldNames.length() - 1)) + ") " +
                        "values(" + insertDatas.substring(0, insertDatas.length() - 1) + ");";
            } else if (Constant.DELETE_TYPE.equals(data.get(Constant.OPERATOR_TYPE))) {
                StringBuilder delete = new StringBuilder();
                List<Integer> nullPositions = new ArrayList<>();
                for (int i = 0; i < fieldNameList.size(); i++) {
                    Object value = data.get(fieldNameList.get(i));
                    String fieldType = fieldTypeMap.get(fieldNameList.get(i));
                    if (Objects.isNull(value) || ("".equals(value.toString()))) {
                        nullPositions.add(i);
                    } else if (fieldType.contains("datetime") || fieldType.contains("date") || fieldType.contains("timestamp")) {
                        nullPositions.add(i);
                    }
                }
                for (int i = 0; i < fieldNameList.size(); i++) {
                    if (!nullPositions.contains(i)) {
                        Object value = data.get(fieldNameList.get(i));
                        String fieldType = fieldTypeMap.get(fieldNameList.get(i));
                        if (MysqlUtil.isNumType(fieldType) || "null".equals(value.toString())) {
                            delete.append("`").append(fieldNameList.get(i)).append("`").append("=")
                                    .append(" ").append(value);
                        } else {
                            value = formatObject(value);
                            delete.append("`").append(fieldNameList.get(i)).append("`").append("=")
                                    .append(" '").append(value).append("'");
                        }
                        delete.append(" and ");
                    }
                }
                sql = "delete from " + syncDbName + "." + syncTableName + " where " + delete.substring(0, delete.length() - 5) + " limit 1" + ";";
            }
//            String strSql = sql.replaceAll("\\\\", "\\\\\\\\");
            if (!StringUtils.isEmpty(sql)) {
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            log.info("Sync value{}", sql);
            log.info("Sync Fail...", e);
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

    private Object formatObject(Object value) {
        String val = value.toString();
        if (val.contains(":")) {
            value = val.replaceAll("\\\\", "\\\\\\\\");
        } else {
            value = val.replaceAll("\'", "\\\\'");
        }
        return value;
    }

    @Override
    protected DruidUtils beginTransaction() throws Exception {
        log.info("start beginTransaction.......");
        return new DruidUtils();
    }

    @Override
    protected void preCommit(DruidUtils druidUtils) throws Exception {
        log.info("start preCommit...");
    }

    @Override
    protected void commit(DruidUtils druidUtils) {
        log.info("start commit...");
        druidUtils.commit();
    }

    @Override
    protected void abort(DruidUtils druidUtils) {
        log.info("start abort rollback...");
        druidUtils.rollBack();
    }
}
