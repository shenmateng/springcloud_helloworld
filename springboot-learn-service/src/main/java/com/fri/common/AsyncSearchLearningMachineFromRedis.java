package com.fri.common;

import com.fri.domain.OuterSource;
import com.fri.domain.SystemFortLockLearnResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-24 13:15
 * @Description: 异步获取redis中处于学习中的主机与聚合层级
 */
public class AsyncSearchLearningMachineFromRedis extends RichAsyncFunction<List<SystemFortLockLearnResult>, OuterSource> {

    private transient JedisPool pool;

    private JedisCluster jedisCluster;

    private String host;

    private int port;

    private String password;

    private String redisNode;

    public AsyncSearchLearningMachineFromRedis() {

    }

    public AsyncSearchLearningMachineFromRedis(String host, int port, String password, String redisNode) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.redisNode = redisNode;
    }

    /**
     * open 方法中初始化链接
     *
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        if (StringUtils.isEmpty(redisNode)) {
            pool = new JedisPool(new JedisPoolConfig(), host, port, 6000, password);
        } else {
            Set<HostAndPort> hostAndPorts = splitString(redisNode);
            jedisCluster = new JedisCluster(hostAndPorts, 5000, 3000, 10, password, new GenericObjectPoolConfig());
        }
    }

    @Override
    public void asyncInvoke(List<SystemFortLockLearnResult> systemFortLockLearnResults, ResultFuture<OuterSource> resultFuture) {
        OuterSource outerSource = new OuterSource();
        outerSource.setSystemFortLockLearn(systemFortLockLearnResults);
        outerSource.setGroupByLevel(2);
        outerSource.setGroupBySize(5);
        Jedis jedis = null;
        try {
            if (StringUtils.isEmpty(redisNode)) {
                jedis = pool.getResource();
                getRedisConf(systemFortLockLearnResults, outerSource, jedis.smembers("sys_fort_lock_learning_machines"), jedis.get("group_by_level"), jedis.get("group_by_size"));
            } else {
                getRedisConf(systemFortLockLearnResults, outerSource, jedisCluster.smembers("sys_fort_lock_learning_machines"), jedisCluster.get("group_by_level"), jedisCluster.get("group_by_size"));
            }
        } catch (Exception e) {
            System.out.println("e" + e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisCluster != null) {
                jedisCluster.close();
            }
            resultFuture.complete(Collections.singletonList(outerSource));
        }
    }

    private void getRedisConf(List<SystemFortLockLearnResult> systemFortLockLearnResults, OuterSource outerSource, Set<String> sysFortLockMachines, String groupByLevel, String groupBySize) {
        if (!CollectionUtils.isEmpty(systemFortLockLearnResults)) {
            outerSource.setMachineUuids(new ArrayList<>(sysFortLockMachines));
        }
        if (!StringUtils.isEmpty(groupByLevel)) {
            outerSource.setGroupByLevel(Integer.parseInt(groupByLevel));
        }
        if (!StringUtils.isEmpty(groupBySize)) {
            outerSource.setGroupBySize(Integer.parseInt(groupBySize));
        }
    }

    /**
     * close function
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        super.close();
    }

    public Set<HostAndPort> splitString(String str) {
        String[] list = str.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String s : list) {
            int index = s.indexOf(":");
            nodes.add(new HostAndPort(s.substring(0, index), Integer.parseInt(s.substring(index + 1))));
        }
        return nodes;
    }
}
