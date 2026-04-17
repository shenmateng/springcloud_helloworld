package com.fri.utils;

import io.debezium.time.MicroTimestamp;
import io.debezium.time.NanoTimestamp;
import io.debezium.time.Timestamp;
import io.debezium.time.ZonedTimestamp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.table.data.TimestampData;
import org.apache.kafka.connect.data.Schema;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-4-27 9:51
 * @Description:
 */
@Slf4j
public class MysqlUtil {

    /**
     * 传入一个mysql的数据类型，判断该数据类型是否是数字类型的
     *
     * @param : schema
     * @return :
     */
    public static Boolean isNumType(String schema) {
        String[] mysqlNumTypeArr = new String[]{
                "TINYINT",
                "MEDIUMINT",
                "INT",
                "INTEGER",
                "BIGINT",
                "FLOAT",
                "DOUBLE",
                "DECIMAL",
                "LONG"
        };
        boolean flag = false;
        for (String mysqlNumType : mysqlNumTypeArr) {
            if (schema.toUpperCase().contains(mysqlNumType) && !schema.toUpperCase().contains("LONGTEXT")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static Boolean isTimeStampType(Schema schema) {
        boolean flag = false;
        String name = schema.name();
        if (!StringUtils.isEmpty(name)) {
            flag = Timestamp.SCHEMA_NAME.equals(name) || MicroTimestamp.SCHEMA_NAME.equals(name)
                    || NanoTimestamp.SCHEMA_NAME.equals(name);
        }
        return flag;
    }

    public static Boolean isZonedTimeStampType(Schema schema) {
        boolean flag = false;
        String name = schema.name();
        if (!StringUtils.isEmpty(name)) {
            flag = ZonedTimestamp.SCHEMA_NAME.equals(name);
        }
        return flag;
    }

    public static String convertToTimestamp(Object dbzObj) {
        String formatTime = TimestampData.fromEpochMillis(Long.parseLong(dbzObj.toString()))
                .toLocalDateTime().atOffset(ZoneOffset.ofHours(8)).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDateTime date = LocalDateTime.parse(formatTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
