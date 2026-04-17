package com.fri.utils;

import com.fri.domain.SystemFortLockRaw;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-8 10:57
 * @Description:
 */
public class InsertClickHouseUtil extends RichSinkFunction<List<SystemFortLockRaw>> {

    private String url;

    private String userName;

    private String passWord;

    Connection connection = null;

    PreparedStatement preparedStatement = null;

    public InsertClickHouseUtil() {

    }

    public InsertClickHouseUtil(String url, String userName, String passWord) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        String sql = "INSERT INTO learn.system_fort_lock_raw (id, machine_uuid, os_type ,subject, object, rights, create_time) " +
                "VALUES (?, ?, ?, ?, ?, ?,?)";
        connection = HikaricpUtils.getConnection(url, AESUtil.decrypt(userName, AESUtil.AES_KEY),
                AESUtil.decrypt(passWord, AESUtil.AES_NEW_KEY), null);
        preparedStatement = connection.prepareStatement(sql);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

    @Override
    public void invoke(List<SystemFortLockRaw> results, Context context) throws Exception {
        for (SystemFortLockRaw lockLearnResult : results) {
            preparedStatement.setString(1, lockLearnResult.getId());
            preparedStatement.setString(2, lockLearnResult.getMachineUuid());
            preparedStatement.setInt(3, lockLearnResult.getOsType());
            preparedStatement.setString(4, lockLearnResult.getSubject());
            preparedStatement.setString(5, lockLearnResult.getObject());
            preparedStatement.setString(6, lockLearnResult.getRights());
            preparedStatement.setTimestamp(7, new Timestamp(lockLearnResult.getCreateTime().getTime()));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
    }
}
