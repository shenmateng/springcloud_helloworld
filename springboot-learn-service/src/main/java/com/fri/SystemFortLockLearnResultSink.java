package com.fri;

import com.fri.common.AsyncSearchLearningMachineFromRedis;
import com.fri.common.BatchInsertClickHouseUtil;
import com.fri.common.LearnResultSourceFromClickHouse;
import com.fri.config.FlinkNacosConfig;
import com.fri.constant.Constant;
import com.fri.domain.OuterSource;
import com.fri.domain.SystemFortLockLearnResult;
import com.fri.utils.ClearDirtyLearnDataUtil;
import com.fri.utils.DateUtil;
import com.fri.utils.UUIDUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-9 10:37
 * @Description:
 */
public class SystemFortLockLearnResultSink {

//    public static void main(String[] args) throws Exception {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        //以每分钟频率做检查点
//        env.enableCheckpointing(60 * 1000L);
//        //指定设置检查点执行的超时时间为30s
//        env.getCheckpointConfig().setCheckpointTimeout(30 * 1000L);
////        env.setRestartStrategy(RestartStrategies.noRestart());
//        env.setRestartStrategy(RestartStrategies.failureRateRestart( 3, // 一个时间段内的最大失败次数
//                Time.of(5, TimeUnit.MINUTES), // 衡量失败次数的是时间段
//                Time.of(10, TimeUnit.SECONDS) // 间隔
//        ));
//
//        //加载nacos配置信息
//        String url = FlinkNacosConfig.getConfigValue("url");
//        String username = FlinkNacosConfig.getConfigValue("username");
//        String password = FlinkNacosConfig.getConfigValue("password");
//
//        String redisNode = FlinkNacosConfig.getConfigValue("redis.node");
//        boolean single = StringUtils.isEmpty(redisNode);
//        String redisPassWord = FlinkNacosConfig.getConfigValue("redis.password");
//
//        //ClickHouse source
//        DataStreamSource<List<SystemFortLockLearnResult>> source = env.addSource(
//                new LearnResultSourceFromClickHouse(5 * 60 * 1000, url, username, password));
//
//        //异步IO 获取redis中学习的机器, timeout 时间 1s，容量 10（超过10个请求，会反压上游节点） unorderedWait返回结果无顺序(如果是事件时间 实则会根据watermark排序)  orderedWait返回结果有序(fifo)
//        //超过10个请求，会反压上游节点  反压机制来抑制上游数据的摄入
//        SingleOutputStreamOperator<OuterSource> outerSourceStream = AsyncDataStream.unorderedWait(source,
//                new AsyncSearchLearningMachineFromRedis(
//                        single ? FlinkNacosConfig.getConfigValue("redis.host") : null,
//                        single ? Integer.parseInt(FlinkNacosConfig.getConfigValue("redis.port")) : 6379,
//                        redisPassWord, single ? null : redisNode),
//                1000, TimeUnit.MICROSECONDS, 10);
//
//        SingleOutputStreamOperator<List<SystemFortLockLearnResult>> process = outerSourceStream.process(new ProcessFunction<OuterSource, List<SystemFortLockLearnResult>>() {
//            @Override
//            public void processElement(OuterSource outerSource, Context context, Collector<List<SystemFortLockLearnResult>> collector) throws Exception {
//                List<SystemFortLockLearnResult> results = new ArrayList<>();
//                Map<String, List<SystemFortLockLearnResult>> map = outerSource.getSystemFortLockLearn()
//                        .stream()
//                        .filter(learn -> outerSource.getMachineUuids().contains(learn.getMachineUuid()))
//                        .collect(Collectors.groupingBy(SystemFortLockLearnResult::getMachineUuid));
//
//                List<SystemFortLockLearnResult> orgSystemFortLockLearnResults = ClearDirtyLearnDataUtil.listResultByMachines(url, username, password, outerSource.getMachineUuids());
//                Map<String, List<SystemFortLockLearnResult>> orgMap = orgSystemFortLockLearnResults.stream()
//                        .filter(learn -> outerSource.getMachineUuids().contains(learn.getMachineUuid()))
//                        .collect(Collectors.groupingBy(SystemFortLockLearnResult::getMachineUuid));
//
//                map.forEach((machineUuid, res) -> {
//                    res.addAll(orgMap.get(machineUuid));
//                    Map<String, List<SystemFortLockLearnResult>> collect = res.stream().collect(Collectors.groupingBy(SystemFortLockLearnResult::getSubject));
//                    learnAlgorithm(results, collect, outerSource.getGroupByLevel(), outerSource.getGroupBySize());
//                });
//                if (!CollectionUtils.isEmpty(results)) {
//                    ClearDirtyLearnDataUtil.delete(DateUtil.formatDateTime(new Date()), url, username, password, outerSource.getMachineUuids(), single);
//                }
//                ClearDirtyLearnDataUtil.deleteRaw(url, username, password, single);
//                collector.collect(results);
//            }
//        });
//
//        //sink 学习结果
//        BatchInsertClickHouseUtil batchInsertClickHouseUtil = new BatchInsertClickHouseUtil(url, username, password);
//        process.addSink(batchInsertClickHouseUtil);
//        env.execute("Flink Streaming Learn SystemFortLockLearnResult SinkClickHouse");
//    }

    private static void learnAlgorithm(List<SystemFortLockLearnResult> results, Map<String, List<SystemFortLockLearnResult>> map1, int groupByPathLevel, int groupByPathSize) {
        for (Map.Entry<String, List<SystemFortLockLearnResult>> entry : map1.entrySet()) {
            List<SystemFortLockLearnResult> learnResult = entry.getValue();
            Map<String, List<SystemFortLockLearnResult>> collect = learnResult.stream().collect(Collectors.groupingBy(SystemFortLockLearnResult::getRights));
            List<SystemFortLockLearnResult> exeResult = collect.get("x");
            Map<String, SystemFortLockLearnResult> objectSet = new HashMap<>();
            if (!CollectionUtils.isEmpty(exeResult)) {
                exeResult.forEach(result -> {
                    if (objectSet.containsKey(result.getObject())) {
                        objectSet.get(result.getObject()).setQuantity(objectSet.get(result.getObject()).getQuantity() + 1);
                    } else {
                        objectSet.put(result.getObject(), result);
                    }
                });
            }
            Collection<SystemFortLockLearnResult> values = objectSet.values();
            if (!CollectionUtils.isEmpty(values)) {
                for (SystemFortLockLearnResult xResult : values) {
                    xResult.setId(UUIDUtil.get());
                    xResult.setCreateTime(new Date());
                    results.add(xResult);
                }
            }
            List<SystemFortLockLearnResult> rwResults = new ArrayList<>();
            if (!CollectionUtils.isEmpty(collect.get("w"))) {
                rwResults.addAll(collect.get("w"));
            }
            if (!CollectionUtils.isEmpty(collect.get("r"))) {
                rwResults.addAll(collect.get("r"));
            }

            Map<Integer, Set<String>> levelAndParentsMap = new HashMap<>();
            levelAndParentsMap.put(0, Set.of("/"));

            rwResults.forEach(object -> {
                String[] objectArr = object.getObject().substring(1).split("/");
                StringBuilder parentId = new StringBuilder("/");
                for (int i = 0; i < objectArr.length; i++) {
                    int pathLevel = i + 1;
                    if (!levelAndParentsMap.containsKey(pathLevel)) {
                        levelAndParentsMap.put(pathLevel, new HashSet<>());
                    }
                    levelAndParentsMap.get(pathLevel).add(parentId.toString() + objectArr[i]);
                    parentId.append(objectArr[i]).append("/");
                }
            });

            Map<String, SystemFortLockLearnResult> map = new HashMap<>();
            rwResults.forEach(object -> {
                StringBuilder stringBuilder = new StringBuilder();
                String[] objectArr = object.getObject().substring(1).split("/");
                String[] objectArr2 = new String[objectArr.length];
                for (int i = 0; i < objectArr.length; i++) {
                    objectArr2[i] = objectArr[i];
                    int pathLevel = i + 1;
                    Set<String> idAndObjSet = levelAndParentsMap.get(pathLevel);
                    String value = objectArr[i];
                    if (pathLevel < groupByPathLevel) {
                        stringBuilder.append("/").append(value);
                        continue;
                    }
                    if (i == objectArr.length - 1) {
                        if (!value.contains(".")) {
                            stringBuilder.append("/").append(value);
                        } else {
                            String fileNamePre = value.substring(0, value.lastIndexOf("."));
                            String fileNameSuf = value.substring(value.lastIndexOf("."));
                            List<String> list = new ArrayList<>();
                            idAndObjSet.forEach(id -> {
                                if (compareParent(objectArr2, id, pathLevel) && id.endsWith(fileNameSuf)){
                                    String[] split = id.substring(1).split("/");
                                    String substring = split[split.length - 1].substring(0, value.lastIndexOf("."));
                                    list.add(substring);
                                }
                            });
                            if (list.size() >= groupByPathSize || list.contains("*")) { fileNamePre = "*"; }
                            stringBuilder.append("/").append(fileNamePre).append(fileNameSuf);
                        }
                    } else {
                        Set<String> set = new HashSet<>();
                        idAndObjSet.forEach(id -> {
                            if (compareParent(objectArr2, id, pathLevel)) {
                                String[] split = id.split("/");
                                set.add(split[split.length - 1]);
                            }
                        });
                        String res = set.size() >= groupByPathSize || set.contains("*") ? "*" : value;
                        stringBuilder.append("/").append(res);
                        objectArr2[i] = res;
                    }
                }
                object.setObject(stringBuilder.toString());
                if (map.containsKey(stringBuilder.toString())) {
                    String rights = map.get(stringBuilder.toString()).getRights() + object.getRights();
                    Set<String> set = new HashSet<>(Arrays.asList(rights.split("")));
                    map.get(stringBuilder.toString()).setRights(String.join("", set));
                    map.get(stringBuilder.toString()).setQuantity(map.get(stringBuilder.toString()).getQuantity() + object.getQuantity());
                } else {
                    map.put(stringBuilder.toString(), object);
                }
            });

            map.forEach((key, systemFortLockLearnResult) -> {
                if (Objects.equals(systemFortLockLearnResult.getOsType(), Constant.OS_WIN)) {
                    systemFortLockLearnResult.setObject(systemFortLockLearnResult.getObject().substring(1));
                }
                systemFortLockLearnResult.setCreateTime(new Date());
                systemFortLockLearnResult.setId(UUIDUtil.get());
                results.add(systemFortLockLearnResult);
            });
        }
    }

    private static boolean compareParent(String[] objectArr, String id, int pathLevel) {
        int count = 0;
        int maxIndex = pathLevel - 1;
        String[] idArr = id.substring(1).split("/");
        for (int i = 0; i < maxIndex; i++) {
            if ((idArr.length - 1 >= i && objectArr[i].equals(idArr[i])) || objectArr[i].equals("*")) {
                count++;
            }
        }
        return count == maxIndex;
    }
}
