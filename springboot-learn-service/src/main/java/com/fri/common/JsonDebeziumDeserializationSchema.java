package com.fri.common;

import com.alibaba.fastjson.JSON;
import com.fri.constant.Constant;
import com.fri.utils.DateUtil;
import com.fri.utils.MysqlUtil;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-3-24 15:08
 * @Description:
 */
@Slf4j
public class JsonDebeziumDeserializationSchema implements DebeziumDeserializationSchema<String> {

    private String centerId;

    public JsonDebeziumDeserializationSchema() {

    }

    public JsonDebeziumDeserializationSchema(String centerId) {
        this.centerId = centerId;
    }

    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<String> collector) {
        //从sourceRecord中获取binlog日志的database,table,type,after,befor等数据
        String topic = sourceRecord.topic();
        String[] split = topic.split("[.]");
        String database = split[1];
        String table = split[2];

        //获取数据本身
        Struct struct = (Struct) sourceRecord.value();
        Struct after = struct.getStruct("after");
        Struct before = struct.getStruct("before");

        //获取每张表的唯一主键，用于删除操作
        Struct pk = (Struct) sourceRecord.key();
        List<Field> pkFieldList = pk.schema().fields();
        Field keyField = pkFieldList.get(0);
        String key = keyField.name();
        /*
         	 1，同时存在 beforeStruct 跟 afterStruct数据的话，就代表是update的数据
             2,只存在 beforeStruct 就是delete数据
             3，只存在 afterStruct数据 就是insert数据
        */
        HashMap<String, Object> result = new HashMap<>();
        result.put(Constant.CENTER_ID, centerId);
        result.put(Constant.DB_NAME, database);
        result.put(Constant.DB_TABLE, table);
        if (before != null && after != null) {
            result.put(Constant.OPERATOR_TYPE, Constant.UPDATE_TYPE);
            result.put(Constant.OPERATOR_PRI_KEY, key);
            spliceData(after, result, Constant.UPDATE_TYPE);
        } else if (after != null) {
            result.put(Constant.OPERATOR_TYPE, Constant.INSERT_TYPE);
            spliceData(after, result, Constant.INSERT_TYPE);
        } else if (before != null) {
            result.put(Constant.OPERATOR_TYPE, Constant.DELETE_TYPE);
            result.put(Constant.OPERATOR_PRI_KEY, key);
            Schema beforeSchema = before.schema();
            for (Field field : beforeSchema.fields()) {
                Object data = before.get(field);
                String fieldName = field.name();
                for (Field field1 : pkFieldList) {
                    if (Objects.equals(field1.name(), fieldName)) {
                        result.put(fieldName, data.toString());
                    }
                }
            }
        } else {
            result.put(Constant.OPERATOR_TYPE, Constant.CREATE_TYPE);
        }
        collector.collect(JSON.toJSONString(result));
    }

    private void spliceData(Struct after, HashMap<String, Object> result, String type) {
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
                    result.put(fieldName, MysqlUtil.convertToTimestamp(data));
                } else if (zoneTimeType && !"null".equals(data.toString())) {
                    if (Constant.UPDATE_TYPE.equals(type)) {
                        result.put(fieldName, DateUtil.convertDate2(data.toString()));
                    } else {
                        result.put(fieldName, DateUtil.convertDate(data.toString()));
                    }
                } else {
                    result.put(fieldName, data);
                }
            } else {
                result.put(fieldName, data);
            }
        }
    }

    @Override
    public TypeInformation<String> getProducedType() {
        return BasicTypeInfo.STRING_TYPE_INFO;
    }
}
