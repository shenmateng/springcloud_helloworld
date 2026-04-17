package com.fri.common;

import com.alibaba.fastjson.JSON;
import com.fri.constant.Constant;
import com.fri.utils.DateUtil;
import com.fri.utils.HikaricpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-3-24 15:26
 * @Description:
 */
@Slf4j
public class MysqlSinkToKernelProtect extends RichSinkFunction<String> {

    private String url;

    private String userName;

    private String passWord;

    private String driverClassName;

    Connection connection = null;

    PreparedStatement insertWebFilterWhiteStmt = null;
    PreparedStatement deleteWebFilterWhiteStmt = null;
    PreparedStatement updateWebFilterWhiteStmt = null;

    PreparedStatement insertIpWhiteStmt = null;
    PreparedStatement deleteIpWhiteStmt = null;
    PreparedStatement updateIpWhiteStmt = null;

    PreparedStatement descTableStmt = null;

    Statement statement = null;

    public MysqlSinkToKernelProtect() {
    }

    public MysqlSinkToKernelProtect(String url, String userName, String passWord, String driverClassName) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
        this.driverClassName = driverClassName;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = HikaricpUtils.getConnection(url, userName, passWord, driverClassName);

        webFilterWhiteInitSql(); //同步到web_filter_white_list表 初始sql
        ipWhiteInitSql();
        //initSql();

        statement = connection.createStatement();
    }

    private void webFilterWhiteInitSql() throws SQLException {
        String insertSql = "insert into ys_kernel_protect.web_filter_white_list(uuid, machine_uuid, dir_path, update_time, center_id) " +
                "values (?, ?, ?, ?, ?)";
        String deleteSql = "delete from ys_kernel_protect.web_filter_white_list where uuid = ? and center_id = ?";
        String updateSql = "update ys_kernel_protect.web_filter_white_list set machine_uuid = ?, dir_path = ?, update_time = ? where uuid = ? and center_id = ?";
        insertWebFilterWhiteStmt = connection.prepareStatement(insertSql);
        deleteWebFilterWhiteStmt = connection.prepareStatement(deleteSql);
        updateWebFilterWhiteStmt = connection.prepareStatement(updateSql);
    }

    private void ipWhiteInitSql() throws SQLException {
        String insertSql = "insert into ys_kernel_protect.ip_white(machine_uuid, addr_begin, addr_end, remark, time_out, update_time, center_id) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        String deleteSql = "delete from ys_kernel_protect.ip_white where machine_uuid = ? and addr_begin = ? and addr_end = ? and center_id = ?";
        String updateSql = "update ys_kernel_protect.ip_white set remark = ?, time_out = ?, update_time = ? where machine_uuid = ? and addr_begin = ? and addr_end = ? and center_id = ?";
        insertIpWhiteStmt = connection.prepareStatement(insertSql);
        deleteIpWhiteStmt = connection.prepareStatement(deleteSql);
        updateIpWhiteStmt = connection.prepareStatement(updateSql);
    }

    private void initSql() throws SQLException {
        String descTableSql = "desc ?";
        descTableStmt = connection.prepareStatement(descTableSql);
    }



    @Override
    public void close() throws Exception {
        super.close();
    }

    @Override
    public void invoke(String value, Context context) {
        //log.info("\n\n\n**********invoke**********\ncontext:\n" + JSON.toJSONString(context) + "\n\n\n");

        try {
            HashMap<String, Object> hs = JSON.parseObject(value, HashMap.class);
            if (!Objects.isNull(hs)) {
                String database = (String) hs.get("database");
                String table = (String) hs.get("table");
                String type = (String) hs.get("type");

                switch(table){
                    case "web_filter_white_list" :
                        //同步ys_kernel_protect库web_filter_white_list表 -> 到集控ys_kernel_protect库web_filter_white_list表
                        syncWebFilterWhiteTable(hs, database, table, type);
                        break;
                    case "ip_white" :
                        //同步ys_kernel_protect库ip_white表 -> 到集控ys_kernel_protect库ip_white表
                        syncIpWhiteTable(hs, database, table, type);
                        break;
                    default : //可选
                        log.info( "未找到" + database + "." + table + "表的同步方法, 使用通用的智能同步方法");
                        syncTable(hs, database, table, type);
                }


            }
        } catch (Exception e) {
            log.info("Synchronize Data To ys_kernel_protect Fail: " + JSON.toJSONString(value));
            log.info("Sync Fail Info", e);
        }
    }

    private void syncWebFilterWhiteTable(HashMap<String, Object> hs, String database, String table, String type) throws SQLException {
        if ("ys_kernel_protect".equals(database) && "web_filter_white_list".equals(table)) {
            HashMap<String, Object> data = JSON.parseObject(hs.get("data").toString(), HashMap.class);
            String uuid = (String) data.get("uuid");
            String machineUuid = (String) data.get("machine_uuid");
            String dirPath = (String) data.get("dir_path");
            Long updateTime = (Long) data.get("update_time");
            if (Constant.INSERT_TYPE.equals(type)) {
                //新增数据
                insertWebFilterWhiteStmt.setString(1, uuid);
                insertWebFilterWhiteStmt.setString(2, machineUuid);
                insertWebFilterWhiteStmt.setString(3, dirPath);
                insertWebFilterWhiteStmt.setTimestamp(4, new Timestamp(updateTime));
                //分中心ID 目前测试环境设为32个1 新增分中心ID目的：与同步数据表中ID组成联合主键
                insertWebFilterWhiteStmt.setString(5, "11111111111111111111111111111111");
                insertWebFilterWhiteStmt.executeUpdate();
            } else if (Constant.DELETE_TYPE.equals(type)) {
                //删除数据
                deleteWebFilterWhiteStmt.setString(1, uuid);
                deleteWebFilterWhiteStmt.setString(2, "11111111111111111111111111111111");
                deleteWebFilterWhiteStmt.executeUpdate();
            } else if (Constant.UPDATE_TYPE.equals(type)) {
                //更新数据
                updateWebFilterWhiteStmt.setString(1, machineUuid);
                updateWebFilterWhiteStmt.setString(2, dirPath);
                updateWebFilterWhiteStmt.setTimestamp(3, new Timestamp(updateTime));
                updateWebFilterWhiteStmt.setString(4, uuid);
                updateWebFilterWhiteStmt.setString(5, "11111111111111111111111111111111");
                updateWebFilterWhiteStmt.executeUpdate();
            }
        }
    }

    private void syncIpWhiteTable(HashMap<String, Object> hs, String database, String table, String type) throws SQLException {
        if ("ys_kernel_protect".equals(database) && "ip_white".equals(table)) {
            HashMap<String, Object> data = JSON.parseObject(hs.get("data").toString(), HashMap.class);
            String machineUuid = (String) data.get("machine_uuid");
            String addrBegin = (String) data.get("addr_begin");
            String addrEnd = (String) data.get("addr_end");
            String remark = (String) data.get("remark");
            Integer timeOut = (Integer) data.get("time_out");
            Long updateTime = (Long) data.get("update_time");
            if (Constant.INSERT_TYPE.equals(type)) {
                //新增数据
                insertWebFilterWhiteStmt.setString(1, machineUuid);
                insertWebFilterWhiteStmt.setString(2, addrBegin);
                insertWebFilterWhiteStmt.setString(3, addrEnd);
                insertWebFilterWhiteStmt.setString(4, remark);
                insertWebFilterWhiteStmt.setInt(5, timeOut);
                insertWebFilterWhiteStmt.setTimestamp(6, new Timestamp(updateTime));
                //分中心ID 目前测试环境设为32个1 新增分中心ID目的：与同步数据表中ID组成联合主键
                insertWebFilterWhiteStmt.setString(7, "11111111111111111111111111111111");
                insertWebFilterWhiteStmt.executeUpdate();
            } else if (Constant.DELETE_TYPE.equals(type)) {
                //删除数据
                deleteWebFilterWhiteStmt.setString(1, machineUuid);
                deleteWebFilterWhiteStmt.setString(2, addrBegin);
                deleteWebFilterWhiteStmt.setString(3, addrEnd);
                deleteWebFilterWhiteStmt.setString(4, "11111111111111111111111111111111");
                deleteWebFilterWhiteStmt.executeUpdate();
            } else if (Constant.UPDATE_TYPE.equals(type)) {
                //更新数据
                updateWebFilterWhiteStmt.setString(1, remark);
                updateWebFilterWhiteStmt.setInt(2, timeOut);
                updateWebFilterWhiteStmt.setTimestamp(3, new Timestamp(updateTime));
                updateWebFilterWhiteStmt.setString(1, machineUuid);
                updateWebFilterWhiteStmt.setString(2, addrBegin);
                updateWebFilterWhiteStmt.setString(3, addrEnd);
                updateWebFilterWhiteStmt.setString(5, "11111111111111111111111111111111");
                updateWebFilterWhiteStmt.executeUpdate();
            }
        }
    }

    private void syncTable(HashMap<String, Object> hs, String database, String table, String type) throws SQLException {
        if(table.contains("protect_task_item"))
        {
            log.info("跳过" + table);
            return;
        }

        String descSql = "desc " + database + "." + table;

        log.info("**********智能同步开始********** " + descSql);

        ResultSet resultSet = statement.executeQuery(descSql);

        List<String> fieldNameList = new ArrayList<>();         // 字段名列表
        List<String> fieldNameKeyList = new ArrayList<>();      // 主键字段
        List<String> fieldNameNotKeyList = new ArrayList<>();   // 非主键字段
        Map<String, String> fieldTypeMap = new HashMap<>();     // 字段对应类型
        Map<String, String> fieldKeyMap = new HashMap<>();      // 字段对应主键标记

        while (resultSet.next())
        {
            String fieldName = resultSet.getString("Field");
            String fieldType = resultSet.getString("Type");
            String fieldKey = resultSet.getString("Key");

            fieldNameList.add(fieldName);

            if("PRI".equals(fieldKey))
            {
                fieldNameKeyList.add(fieldName);
            }
            else
            {
                fieldNameNotKeyList.add(fieldName);
            }

            fieldTypeMap.put(fieldName, fieldType);
            fieldKeyMap.put(fieldName, fieldKey);
        }

        log.info("fieldNameList: " + JSON.toJSONString(fieldNameList));
        log.info("fieldNameKeyList: " + JSON.toJSONString(fieldNameKeyList));
        log.info("fieldNameNotKeyList: " + JSON.toJSONString(fieldNameNotKeyList));
        log.info("fieldTypeMap: " + JSON.toJSONString(fieldTypeMap));
        log.info("fieldKeyMap: " + JSON.toJSONString(fieldKeyMap));


        HashMap<String, Object> data = JSON.parseObject(hs.get("data").toString(), HashMap.class);

        if (Constant.INSERT_TYPE.equals(type)) {
            /**
             * 新增数据
             */
            String fieldsStr = StringUtils.join(fieldNameList.toArray(), ",");

            List<String> valueList = new ArrayList<>();
            fieldNameList.forEach( fieldName -> {
                String fieldType = fieldTypeMap.get(fieldName);

                if(fieldType.contains("char") || fieldType.contains("blob") || fieldType.contains("text"))
                {
                    String value = (String)data.get(fieldName);
                    valueList.add( value == null ? "null" : "'" + value + "'" );
                }
                else if(fieldType.contains("int"))
                {
                    Integer value = (Integer)data.get(fieldName);
                    valueList.add( value == null ? "null" : String.valueOf(value) );
                }
                else if(fieldType.contains("datetime"))
                {
                    Long value = (Long) data.get(fieldName);
                    valueList.add( value == null ? "null" : "'" + DateUtil.formatDateTime(new Date(value)) + "'" );
                }
                else if(fieldType.contains("date"))
                {
                    Long value = (Long) data.get(fieldName);
                    valueList.add( value == null ? "null" : "'" + DateUtil.formatDate(new Date(value)) + "'" );
                }

            });
            String valuesStr = StringUtils.join(valueList.toArray(), ",");

            String insertSql = "insert into " + database + "." + table + "("+ fieldsStr +") values ("+ valuesStr +")";
            int result = statement.executeUpdate(insertSql);

            log.info("新增数据: " + insertSql + "\nresult = " + result);

        } else if (Constant.DELETE_TYPE.equals(type)) {
            /**
             * 删除数据
             */
            List<String> whereList = new ArrayList<>();
            fieldNameKeyList.forEach( fieldName -> {
                String fieldType = fieldTypeMap.get(fieldName);

                if(fieldType.contains("char"))
                {
                    String value = (String)data.get(fieldName);
                    whereList.add( fieldName + " = '" + value + "'" );
                }
                else if(fieldType.contains("int"))
                {
                    Integer value = (Integer)data.get(fieldName);
                    whereList.add( fieldName + " = " + value );
                }

            });
            String whereStr = StringUtils.join(whereList.toArray(), " and ");

            String deleteSql = "delete from " + database + "." + table + " where " + whereStr;
            int result = statement.executeUpdate(deleteSql);

            log.info("删除数据: " + deleteSql + "\nresult = " + result);

        } else if (Constant.UPDATE_TYPE.equals(type)) {
            /**
             * 更新数据
             */
            List<String> updateList = new ArrayList<>();
            fieldNameNotKeyList.forEach( fieldName -> {
                String fieldType = fieldTypeMap.get(fieldName);

                if(fieldType.contains("char") || fieldType.contains("blob") || fieldType.contains("text"))
                {
                    String value = (String)data.get(fieldName);
                    updateList.add( fieldName + " = " + (value == null ? "null" : "'" + value + "'") );
                }
                else if(fieldType.contains("int"))
                {
                    Integer value = (Integer)data.get(fieldName);
                    updateList.add( fieldName + " = " + (value == null ? "null" : value) );
                }
                else if(fieldType.contains("datetime"))
                {
                    Long value = (Long) data.get(fieldName);
                    updateList.add( fieldName + " = " + (value == null ? "null" : "'" + DateUtil.formatDateTime(new Date(value)) + "'") );
                }
                else if(fieldType.contains("date"))
                {
                    Long value = (Long) data.get(fieldName);
                    updateList.add( fieldName + " = " + (value == null ? "null" : "'" + DateUtil.formatDate(new Date(value)) + "'") );
                }

            });
            String updateStr = StringUtils.join(updateList.toArray(), ",");

            List<String> whereList = new ArrayList<>();
            fieldNameKeyList.forEach( fieldName -> {
                String fieldType = fieldTypeMap.get(fieldName);

                if(fieldType.contains("char"))
                {
                    String value = (String)data.get(fieldName);
                    whereList.add( fieldName + " = '" + value + "'" );
                }
                else if(fieldType.contains("int"))
                {
                    Integer value = (Integer)data.get(fieldName);
                    whereList.add( fieldName + " = " + value );
                }

            });
            String whereStr = StringUtils.join(whereList.toArray(), " and ");

            String updateSql = "update " + database + "." + table + " set " + updateStr + " where " + whereStr;
            int result = statement.executeUpdate(updateSql);

            log.info("更新数据: " + updateSql + "\nresult = " + result);
        }

        log.info("**********智能同步结束**********");
    }
}
