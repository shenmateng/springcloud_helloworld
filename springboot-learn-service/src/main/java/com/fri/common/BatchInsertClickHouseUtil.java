package com.fri.common;

import com.fri.domain.SystemFortLockLearnResult;
import com.fri.utils.AESUtil;
import com.fri.utils.HikaricpUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-8 8:48
 * @Description:
 */
public class BatchInsertClickHouseUtil extends RichSinkFunction<List<SystemFortLockLearnResult>> {

    private String url;

    private String userName;

    private String passWord;

    Connection connection = null;

    PreparedStatement preparedStatement = null;

    public BatchInsertClickHouseUtil() {

    }

    public BatchInsertClickHouseUtil(String url, String userName, String passWord) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = HikaricpUtils.getConnection(url, AESUtil.decrypt(userName, AESUtil.AES_KEY),
                AESUtil.decrypt(passWord, AESUtil.AES_NEW_KEY), null);
        //插入
        String batchInsertSql = "INSERT INTO learn.system_fort_lock_learn_result(id, machine_uuid, subject, object, rights, create_time, quantity)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(batchInsertSql);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

    @Override
    public void invoke(List<SystemFortLockLearnResult> results, Context context) throws Exception {
        for (SystemFortLockLearnResult lockLearnResult : results) {
            preparedStatement.setString(1, lockLearnResult.getId());
            preparedStatement.setString(2, lockLearnResult.getMachineUuid());
            preparedStatement.setString(3, lockLearnResult.getSubject());
            preparedStatement.setString(4, lockLearnResult.getObject());
            preparedStatement.setString(5, lockLearnResult.getRights());
            preparedStatement.setTimestamp(6, new Timestamp(lockLearnResult.getCreateTime().getTime()));
            preparedStatement.setInt(7, lockLearnResult.getQuantity());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
    }
}
