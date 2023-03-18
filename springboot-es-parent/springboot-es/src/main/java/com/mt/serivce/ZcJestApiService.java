package com.mt.serivce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mt.database.es.EsZcMachine;
import com.mt.database.es.EsZcMachineSave;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Update;
import io.searchbox.core.UpdateByQuery;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j
@Component
public class ZcJestApiService extends JestApiService{

    @Autowired
    private JestClient jestClient;


    public <T> boolean upsertEsZcMachine(EsZcMachineSave t){
        try {
            log.info("es更新数据,更新前：{}",JSONObject.toJSONString(t));
            JSONObject jsonObject = (JSONObject) JSON.toJSON(t);
            log.info("es更新数据,JSON：{}",jsonObject.toJSONString());
            Map<String, Object> params = jsonObject.getInnerMap();
            StringBuilder sb = new StringBuilder();
            params.forEach((key, value) -> {
                if("assetsLevel".equals(key)) {
                    sb.append("if(!ctx._source.containsKey('assetsLevel')){ctx._source.assetsLevel=0;}");
                }else{
                    sb.append("if(params." + key + " != null){ctx._source." + key + "=params." + key + ";}");
                }
            });
            JSONObject scriptJsonObj = new JSONObject();
            scriptJsonObj.put("inline", sb.toString());
            scriptJsonObj.put("params", params);
            log.info("es更新数据,语句：{}",scriptJsonObj.toJSONString());
            return this.upsertDoc(t,scriptJsonObj.toJSONString());
        }catch (Exception ex){
            log.error("",ex);
        }
        return false;
    }

    public <T> boolean upsertDoc(T t,String scriptStr){
        try {
            String index = (String) t.getClass().getMethod("index").invoke(t);
            String type = (String) t.getClass().getMethod("type").invoke(t);
            String id = (String) t.getClass().getMethod("id").invoke(t);
            t.getClass().getMethod("setId",String.class).invoke(t,new String[]{null});
            JSONObject sriptJsonObj = JSON.parseObject(scriptStr);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("script",sriptJsonObj);
            jsonObj.put("upsert",t);
            Update update = new Update.Builder(jsonObj.toJSONString())
                    .index(index)
                    .type(type)
                    .id(id)
                    .setParameter("retry_on_conflict",3)
                    .refresh(true)
                    .build();
            JestResult jr = jestClient.execute(update);
            boolean isSucceeded = jr.isSucceeded();
            if(!isSucceeded){
                log.error(jr.getJsonString());
            }else{
            }
            return jr.isSucceeded();
        } catch (Exception ex) {
            log.error("",ex);
        }
        return false;
    }

    public <T> boolean updateEsZcCommanBy(String index,String type, String machineUuid,EsZcMachineSave esZcMachine) {
        List<T> list = new ArrayList<>();
        try {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            queryBuilder.filter(QueryBuilders.termQuery("machineUuid.keyword", machineUuid));
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("machineName",esZcMachine.getMachineName());
            params.put("machineIp",esZcMachine.getMachineIp());
            params.put("machineTag",esZcMachine.getMachineTag());
            params.put("operationSystem",esZcMachine.getOperationSystem());
            params.put("softwareVersion",esZcMachine.getSoftwareVersion());
            params.put("osType",esZcMachine.getOsType());
            params.put("machineIps",esZcMachine.getMachineIps());
            params.put("machinePrimaryIps",esZcMachine.getMachinePrimaryIps());
            params.put("onlineStatus",esZcMachine.getOnlineStatus());
            params.put("customIp",esZcMachine.getCustomIp());
            //params.put("remark",esZcMachine.getRemark());
            params.put("host",esZcMachine.getHost());
            params.put("userDatas",esZcMachine.getUserDatas());
            params.put("orgDatas",esZcMachine.getOrgDatas());
            params.put("sysPerson",esZcMachine.getSysPerson());
            params.put("appPerson",esZcMachine.getAppPerson());
            params.put("ifDelete",esZcMachine.getIfDelete());
            params.put("jspAgent",esZcMachine.getJspAgent());
            StringBuilder sb = new StringBuilder();
            sb.append("if(params.machineName != null){ctx._source.machineName=params.machineName;}");
            sb.append("if(params.machineIp != null){ctx._source.machineIp=params.machineIp;}");
            sb.append("if(params.machineTag != null){ctx._source.machineTag=params.machineTag;}");
            sb.append("if(params.operationSystem != null){ctx._source.operationSystem=params.operationSystem;}");
            sb.append("if(params.softwareVersion != null){ctx._source.softwareVersion=params.softwareVersion;}");
            sb.append("if(params.osType != null){ctx._source.osType=params.osType;}");
            sb.append("if(params.machineStatus != null){ctx._source.machineStatus=params.machineStatus;}");
            sb.append("if(params.onlineStatus != null){ctx._source.onlineStatus=params.onlineStatus;}");
            sb.append("if(params.machineIps != null){ctx._source.machineIps=params.machineIps;}");
            sb.append("if(params.machinePrimaryIps != null){ctx._source.machinePrimaryIps=params.machinePrimaryIps;}");
            sb.append("if(params.customIp != null){ctx._source.customIp=params.customIp;}");
            //sb.append("if(params.remark != null){ctx._source.remark=params.remark;}");
            sb.append("if(params.host != null){ctx._source.host=params.host;}");
            sb.append("if(params.userDatas != null){ctx._source.userDatas=params.userDatas;}");
            sb.append("if(params.orgDatas != null){ctx._source.orgDatas=params.orgDatas;}");
            sb.append("if(params.sysPerson != null){ctx._source.sysPerson=params.sysPerson;}");
            sb.append("if(params.appPerson != null){ctx._source.appPerson=params.appPerson;}");
            sb.append("if(params.ifDelete != null){ctx._source.ifDelete=params.ifDelete;}");
            sb.append("if(params.jspAgent != null){ctx._source.jspAgent=params.jspAgent;}");

            JSONObject scriptJsonObj = new JSONObject();
            scriptJsonObj.put("inline",sb.toString());
            scriptJsonObj.put("params",params);
            return this.updateDocByQuery(index,type,queryBuilder,scriptJsonObj.toJSONString(),true);
        } catch (Exception ex) {
            log.error("",ex);
        }
        return false;
    }

    public <T> boolean updateDocByQuery(String index, String type, QueryBuilder queryBuilder, String scriptStr, boolean proceed) {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(queryBuilder);
            JSONObject queryJsonObj = JSON.parseObject(searchSourceBuilder.toString());
            JSONObject sriptJsonObj = JSON.parseObject(scriptStr);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("script",sriptJsonObj);
            jsonObj.put("query",queryJsonObj.get("query"));
            UpdateByQuery.Builder builder = new UpdateByQuery.Builder(jsonObj.toJSONString())
                    .addIndex(index)
                    .addType(type)
                    .refresh(true);
            if(proceed){
                builder.setParameter("conflicts","proceed");
            }
            UpdateByQuery updateByQuery = builder.build();
            JestResult jr = jestClient.execute(updateByQuery);
            boolean isSucceeded = jr.isSucceeded();
            if(!isSucceeded){
                log.error(jr.getJsonString());
            }else{
            }
            return jr.isSucceeded();
        } catch (Exception ex) {
            log.error("",ex);
        }
        return false;
    }

}