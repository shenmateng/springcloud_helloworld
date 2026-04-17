package com.fri.utils;

import com.fri.domain.SystemFortLockLearnResult;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-10 8:36
 * @Description:
 */
public class ClearDirtyLearnDataUtil {

    public static void delete(String date, String url, String userName, String passWord, List<String> machines, boolean single) {
        Connection conn;
        PreparedStatement preparedStatement;
        try {
            conn = HikaricpUtils.getConnection(url, AESUtil.decrypt(userName, AESUtil.AES_KEY),
                    AESUtil.decrypt(passWord, AESUtil.AES_NEW_KEY), null);
            if (CollectionUtils.isEmpty(machines)) {
                String sql = single ? "ALTER TABLE learn.system_fort_lock_learn_result DELETE where create_time < ?" :
                        "ALTER TABLE learn.system_fort_lock_learn_result_local ON CLUSTER shard3_repl1 DELETE where create_time < ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, date);
            } else {
                String sql = single ? "ALTER TABLE learn.system_fort_lock_learn_result DELETE where create_time < ? and machine_uuid in (" :
                        "ALTER TABLE learn.system_fort_lock_learn_result_local ON CLUSTER shard3_repl1 DELETE where create_time < ? and machine_uuid in (";
                StringBuilder queryBuilder = new StringBuilder(sql);
                for (int i = 0; i < machines.size(); i++) {
                    queryBuilder.append(" ?");
                    if (i != machines.size() - 1) {
                        queryBuilder.append(",");
                    }
                }
                queryBuilder.append(")");
                preparedStatement = conn.prepareStatement(queryBuilder.toString());
                preparedStatement.setString(1, date);
                for (int i = 0; i < machines.size(); i++) {
                    preparedStatement.setString(i + 2, machines.get(i));
                }
            }
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<SystemFortLockLearnResult> listResultByMachines(String url, String userName, String passWord, List<String> machines) {
        List<SystemFortLockLearnResult> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(machines)) { return results; }
        Connection conn;
        PreparedStatement preparedStatement;
        try {
            conn = HikaricpUtils.getConnection(url, AESUtil.decrypt(userName, AESUtil.AES_KEY),
                    AESUtil.decrypt(passWord, AESUtil.AES_NEW_KEY), null);

            String sql = "select * learn.system_fort_lock_learn_result where machine_uuid in (";
            StringBuilder queryBuilder = new StringBuilder(sql);
            for (int i = 0; i < machines.size(); i++) {
                queryBuilder.append(" ?");
                if (i != machines.size() - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(")");
            preparedStatement = conn.prepareStatement(queryBuilder.toString());
            for (int i = 0; i < machines.size(); i++) {
                preparedStatement.setString(i + 1, machines.get(i));
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SystemFortLockLearnResult lockLearnResult = new SystemFortLockLearnResult();
                lockLearnResult.setId(rs.getString("id"));
                lockLearnResult.setMachineUuid(rs.getString("machineUuid"));
                lockLearnResult.setSubject(rs.getString("subject"));
                lockLearnResult.setObject(rs.getString("object"));
                lockLearnResult.setRights(rs.getString("rights"));
                lockLearnResult.setCreateTime(rs.getTimestamp("createTime"));
                lockLearnResult.setOsType(rs.getInt("osType"));
                lockLearnResult.setQuantity(rs.getInt("quantity"));
                results.add(lockLearnResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void deleteRaw(String url, String userName, String passWord, boolean single) {
        Connection conn;
        PreparedStatement preparedStatement;
        try {
            conn = HikaricpUtils.getConnection(url, AESUtil.decrypt(userName, AESUtil.AES_KEY),
                    AESUtil.decrypt(passWord, AESUtil.AES_NEW_KEY), null);
            String sql = single ? "ALTER TABLE learn.system_fort_lock_raw DELETE where create_time < (SELECT create_time FROM learn.system_fort_lock_raw order by create_time limit 10000,1)" :
                    "ALTER TABLE learn.system_fort_lock_raw_local ON CLUSTER shard3_repl1 DELETE where create_time < (SELECT create_time FROM learn.system_fort_lock_raw order by create_time limit 10000,1)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
