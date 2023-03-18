//package com.mt.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mt.database.MachineDO;
//import com.mt.database.StrategyDO;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.Requests;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Slf4j
//@Component
//public class MachineUtils {
//
//
//    @Resource
//    private RestHighLevelClient restHighLevelClient;
//
//    /**
//     * 根据es唯一id获取文档，获取所有机器id
//     * @param id 策略id
//     * @param uid 用户id
//     * @return 该策略下的所有机器id
//     */
//    public MachineDO selectStrategyHashCountInfo(String id, String uid) {
//        GetRequest request = new GetRequest("strategy_machine_index", id + "_" + uid);
//
//        MachineDO machineDO = new MachineDO();
//        try {
//            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
//            String sourceAsString = response.getSourceAsString();
//            //JSON字符串转换成Java对象
//            StrategyDO strategyDO = JSONObject.parseObject(sourceAsString, StrategyDO.class);
//            String machineList = strategyDO.getMachineList();
//            String offMachineList = strategyDO.getOffMachineList();
//            List<String> machineLists = JSONObject.parseObject(machineList, ArrayList.class);
//            List<String> offMachineLists = JSONObject.parseObject(offMachineList, ArrayList.class);
//            machineDO.setMachineList(machineLists);
//            machineDO.setOffMachineList(offMachineLists);
//            return machineDO;
//        } catch (IOException e) {
//            log.error("统计策略hash类型失败,es服务异常", e);
//        }
//        return machineDO;
//    }
//
//    /**
//     * 保存机器id集合到es中
//     * @param id 主键策略id
//     * @param uid 用户id
//     * @param machineList 机器id集合
//     * @param offMachineList 离线机器id集合
//     */
//    public void addBinaryDigestToStrategy(String id, String uid, String machineList,String offMachineList,String protectType) {
//
//        BulkRequest bulkRequest = Requests.bulkRequest();
//        StrategyDO strategyDO = new StrategyDO();
//        strategyDO.setStrategyId(id);
//        strategyDO.setUid(uid);
//        strategyDO.setMachineList(machineList);
//        strategyDO.setOffMachineList(offMachineList);
//        strategyDO.setOffMachineList(offMachineList);
//        bulkRequest.add(Requests.indexRequest("strategy_machine_index").id(strategyDO.getStrategyId()+"_"+strategyDO.getUid()).source(JSON.toJSONBytes(strategyDO), XContentType.JSON));
//        try {
//            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            log.error("保存es失败日志", e);
//        }
//    }
//
//    /**
//     * 删除es中该策略下的所有机器id
//     *
//     * @param id  策略id
//     * @param uid 用户id
//     */
//    public void deleteStrategyDigests(String id, String uid) {
//        GetRequest isThere = new GetRequest("strategy_machine_index", id + "_" + uid);
//        isThere.fetchSourceContext(new FetchSourceContext(false));
//        isThere.storedFields("_none_");
//        DeleteRequest request = new DeleteRequest("strategy_machine_index", id + "_" + uid);
//        request.timeout("1s");
//        try {
//            boolean exists = restHighLevelClient.exists(isThere, RequestOptions.DEFAULT);
//            if (exists) {
//                restHighLevelClient.delete(request, RequestOptions.DEFAULT);
//            }
//        } catch (Exception e) {
//            log.error("删除es失败日志", e);
//        }
//    }
//}
