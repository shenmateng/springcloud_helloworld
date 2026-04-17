package com.fri.common;

import com.alibaba.fastjson.JSON;
import com.fri.constant.Constant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : 陈余银
 * @create : 2022-05-30 08:54:00
 * @description :
 */
public class CommonSyncProcess extends ProcessFunction<Tuple2<Boolean, Row>, String> {

    private String centerId;

    public CommonSyncProcess() {

    }

    public CommonSyncProcess(String centerId) {
        this.centerId = centerId;
    }

    @Override
    public void processElement(Tuple2<Boolean, Row> booleanRowTuple2, Context context, Collector<String> collector) {
        Row row = booleanRowTuple2.f1;
        Set<String> fieldNames = row.getFieldNames(true);
        Map<String, Object> fieldMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(fieldNames)) {
            for (String field : fieldNames) {
                fieldMap.put(field, row.getFieldAs(field));
            }
            String type;
            byte kindType = row.getKind().toByteValue();
            if (Constant.INSERT == kindType || Constant.UPDATE_AFTER == kindType) {
                type = Constant.INSERT_TYPE;
            } else if (Constant.UPDATE_BEFORE == kindType || Constant.DELETE == kindType) {
                type = Constant.DELETE_TYPE;
            } else {
                type = Constant.CREATE_TYPE;
            }
            fieldMap.put(Constant.CENTER_ID, centerId);
            fieldMap.put(Constant.OPERATOR_TYPE, type);
            collector.collect(JSON.toJSONString(fieldMap));
        }
    }
}
