package com.fri.common;

import com.fri.domain.SystemFortLockLearnResult;
import com.fri.utils.AESUtil;
import com.fri.utils.ClickHouseUtil;
import com.fri.utils.HikaricpUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-9 8:37
 * @Description:
 */
public class LearnResultSourceFromClickHouse extends RichSourceFunction<List<SystemFortLockLearnResult>> {

    private String url;

    private String userName;

    private String passWord;

    private long timeGap;

    private PreparedStatement ps = null;

    private Connection connection = null;

    public LearnResultSourceFromClickHouse() {

    }

    public LearnResultSourceFromClickHouse(long timeGap, String url, String userName, String passWord) {
        this.timeGap = timeGap;
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * open()方法中建立拦截，这样不用每次invoke的时候都需要建立连接和释放连接
     * org.apache.flink.api.common.functions.AbstractRichFunction#open
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        String sql = "SELECT id, machine_uuid as machineUuid, os_type as osType, subject, object, rights, " +
                "create_time as createTime FROM learn.system_fort_lock_raw order by create_time desc limit 4000";
        connection = HikaricpUtils.getConnection(url, AESUtil.decrypt(userName, AESUtil.AES_KEY),
                AESUtil.decrypt(passWord, AESUtil.AES_NEW_KEY), null);
        //获取执行语句
        ps = connection.prepareStatement(sql);
        ps.setFetchSize(2000);
        ps.setFetchDirection(ResultSet.FETCH_REVERSE);
    }

    /**
     * DataStream 调用一次 run()方法执行查询并处理结果集
     * org.apache.flink.streaming.api.functions.source.SourceFunction#run
     */
    @Override
    public void run(SourceContext<List<SystemFortLockLearnResult>> sourceContext) throws Exception {
        while (true) {
            List<SystemFortLockLearnResult> results = new ArrayList<>(4000);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SystemFortLockLearnResult lockLearnResult = new SystemFortLockLearnResult();
                lockLearnResult.setId(rs.getString("id"));
                lockLearnResult.setMachineUuid(rs.getString("machineUuid"));
                lockLearnResult.setSubject(rs.getString("subject"));
                lockLearnResult.setObject(rs.getString("object"));
                lockLearnResult.setRights(rs.getString("rights"));
                lockLearnResult.setCreateTime(rs.getTimestamp("createTime"));
                lockLearnResult.setOsType(rs.getInt("osType"));
                lockLearnResult.setQuantity(1);
                results.add(lockLearnResult);
            }
            sourceContext.collect(results);
            Thread.sleep(timeGap);
        }
    }

    /**
     * 取消一个job时
     * org.apache.flink.streaming.api.functions.source.SourceFunction#cancel()
     */
    @Override
    public void cancel() {

    }

    /**
     * 关闭数据库连接
     * org.apache.flink.api.common.functions.AbstractRichFunction#close()
     */
    @Override
    public void close() throws Exception {
        super.close();
//        if (connection != null) {
//            connection.close();
//        }
//        if (ps != null) {
//            ps.close();
//        }
    }
}
