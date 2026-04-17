package com.fri.common;

import com.fri.constant.Constant;
import com.fri.utils.DateUtil;
import com.fri.utils.MysqlUtil;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import io.debezium.data.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.List;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-4-27 09:46
 * @Description:
 */
@Slf4j
public class JsonDebeziumDeserializationSchemaToSql implements DebeziumDeserializationSchema<String> {

    private String centerId;

    public JsonDebeziumDeserializationSchemaToSql() {

    }

    public JsonDebeziumDeserializationSchemaToSql(String centerId) {
        this.centerId = centerId;
    }

    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<String> collector) {
        //从sourceRecord中获取binlog日志的database,table,type,after,befor等数据
        String topic = sourceRecord.topic();
        String[] split = topic.split("[.]");
        String database = split[1];
        String table = split[2];

        //获取操作类型
        Envelope.Operation operation = Envelope.operationFor(sourceRecord);
        //获取数据本身
        Struct struct = (Struct) sourceRecord.value();
        Struct after = struct.getStruct("after");
        Struct before = struct.getStruct("before");

        //用于拼接所有insert的字段名
        StringBuilder fieldNames = new StringBuilder("`").append("center_id").append("`").append(",");
        //用于拼接所有insert的数据
        StringBuilder insertDatas = new StringBuilder("'").append(centerId).append("'").append(",");
        //用于拼接所有更新的数据
        StringBuilder updateDatas = new StringBuilder();
        //获取每张表的唯一主键，用于删除操作
        Struct pk = (Struct) sourceRecord.key();
        List<Field> pkFieldList = pk.schema().fields();
        Field keyField = pkFieldList.get(0);
        String key = keyField.name();
        //用于获取每张表唯一主键下所对应的数据
        String keyData = "";
        /*
         	 1，同时存在 beforeStruct 跟 afterStruct数据的话，就代表是update的数据
             2,只存在 beforeStruct 就是delete数据
             3，只存在 afterStruct数据 就是insert数据
        */
        String type;
        if (before != null && after != null) {
            type = Constant.UPDATE_TYPE;
            //拼接update更新后的sql
            spliceAfterData(after, updateDatas);
            //拼接update更新前的sql
            keyData = spliceBeforeData(before, key, keyData);
        } else if (after != null) {
            type = Constant.INSERT_TYPE;
            //拼接insert的sql
            spliceInsertData(after, fieldNames, insertDatas);
        } else if (before != null) {
            type = Constant.DELETE_TYPE;
            //拼接delete的sql
            keyData = spliceBeforeData(before, key, keyData);
        } else {
            type = "read";
        }

        if (Constant.CREATE_TYPE.equals(operation.toString().toLowerCase())) {
            type = Constant.INSERT_TYPE;
        }
        //按数据更新类型拼接最终的sql
        String sql;
        switch (type) {
            case Constant.INSERT_TYPE:
                sql = "insert into " + database + "." + table + "(" + fieldNames.substring(0, (fieldNames.length() - 1)) + ") " +
                        "values(" + insertDatas.substring(0, insertDatas.length() - 1) + ");";
                break;
            case Constant.DELETE_TYPE:
                sql = "delete from " + database + "." + table + " where " + key + "=" + keyData + " and center_id = '" + centerId + "';";
                break;
            case Constant.UPDATE_TYPE:
                sql = "update " + database + "." + table + " set " + updateDatas.substring(0, updateDatas.length() - 1) +
                        " where " + key + "=" + keyData + " and center_id = '" + centerId + "';";
                break;
            default:
                sql = "";
                break;
        }
        collector.collect(sql);
    }

    private void spliceInsertData(Struct after, StringBuilder fieldNames, StringBuilder insertDatas) {
        Schema schema = after.schema();
        for (Field field : schema.fields()) {
            Object data = after.get(field);
            String fieldName = field.name();
            String fieldSchema = field.schema().toString();
            Schema singleSchema = field.schema();
            if (data != null && !("".equals(data.toString()))) {
                data = data.toString();
            } else {
                data = "null";
            }
            boolean zoneTimeType = MysqlUtil.isZonedTimeStampType(singleSchema);
            if (MysqlUtil.isNumType(fieldSchema) || "null".equals(data.toString()) || zoneTimeType) {
                if (MysqlUtil.isTimeStampType(singleSchema) && !"null".equals(data.toString())) {
                    insertDatas.append("'").append(MysqlUtil.convertToTimestamp(data)).append("',");
                } else if (zoneTimeType && !"null".equals(data.toString())) {
                    insertDatas.append("'").append(DateUtil.convertDate(data.toString())).append("',");
                } else {
                    insertDatas.append(data).append(",");
                }
            } else {
                insertDatas.append("'").append(data).append("',");
            }
            fieldNames.append("`").append(fieldName).append("`").append(",");
        }
    }

    private String spliceBeforeData(Struct before, String key, String keyData) {
        Schema beforeSchema = before.schema();
        for (Field field : beforeSchema.fields()) {
            Object data = before.get(field);
            String fieldName = field.name();
            String fieldSchema = field.schema().toString();
            if (MysqlUtil.isNumType(fieldSchema)) {
                if (key.equals(fieldName)) {
                    keyData = data.toString();
                }
            } else {
                if (key.equals(fieldName)) {
                    keyData = "'" + data.toString() + "'";
                }
            }
        }
        return keyData;
    }

    private void spliceAfterData(Struct after, StringBuilder updateDatas) {
        Schema schema = after.schema();
        for (Field field : schema.fields()) {
            Object data = after.get(field);
            String fieldName = field.name();
            String fieldSchema = field.schema().toString();
            Schema singleSchema = field.schema();
            if (data != null && !("".equals(data.toString()))) {
                data = data.toString();
            } else {
                data = "null";
            }
            boolean zoneTimeType = MysqlUtil.isZonedTimeStampType(singleSchema);
            if (MysqlUtil.isNumType(fieldSchema) || "null".equals(data.toString()) || zoneTimeType) {
                if (MysqlUtil.isTimeStampType(singleSchema) && !"null".equals(data.toString())) {
                    updateDatas.append("`").append(fieldName).append("`").append("=").append("'").append(MysqlUtil.convertToTimestamp(data)).append("',");
                } else if (zoneTimeType && !"null".equals(data.toString())) {
                    updateDatas.append("`").append(fieldName).append("`").append("=").append("'").append(DateUtil.convertDate(data.toString())).append("',");
                } else {
                    updateDatas.append("`").append(fieldName).append("`").append("=").append(data).append(",");
                }
            } else {
                updateDatas.append("`").append(fieldName).append("`").append("=").append("'").append(data).append("',");
            }
        }
    }

    @Override
    public TypeInformation<String> getProducedType() {
        return BasicTypeInfo.STRING_TYPE_INFO;
    }
}
