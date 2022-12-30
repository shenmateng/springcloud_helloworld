package com.mt.serivce;

import com.mt.constant.ResponseCode;
import com.mt.database.es.EsZcMachineIp;
import com.mt.database.es.EsZcMachinePrimaryIp;
import com.mt.database.es.UserData;
import com.mt.exception.JowtoRuntimeException;
import com.mt.utils.*;
import com.mt.bean.ResponseForPage;
import com.mt.config.AssetConfig;
import com.mt.constant.Constant;
import com.mt.database.AssetQueryForEs;
import com.mt.database.es.EsZcMachine;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.TermsAggregation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EsZcQueryBaseService {

    @Autowired
    private JestClient jestClient;

    /**
     * 资产列表
     *
     * @param assetQueryForEs
     * @return
     */
    public <T> ResponseForPage<T> listAssets(AssetQueryForEs assetQueryForEs, Class<T> tClass, String index, Integer queryType) throws Exception {
        if (assetQueryForEs.getCurrentPage() * assetQueryForEs.getMaxResults() > AssetConfig.INDEX_MAX_RESULT_WINDOW) {
            throw new Exception("系统异常");
        }
        //queryType= 0 需要统计的查询列表  1不需要统计的查询列表
        // 创建查询bulid
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //获取1W以上条数的真实总数
        searchSourceBuilder.trackTotalHits(true);

        //根据用户id和分组嵌套查询
        if (!StringUtils.isEmpty(assetQueryForEs.getUserUuid())) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
            List<String> userUuids = DataUtils.splitByComma(assetQueryForEs.getUserUuid());
            if(userUuids != null && userUuids.size() > 1) {
                queryBuilder.filter(QueryBuilders.termsQuery(Constant.USER_DATAS_USER_UUID_KEYWORD, userUuids));

            }else{
                queryBuilder.filter(QueryBuilders.termQuery(Constant.USER_DATAS_USER_UUID_KEYWORD, assetQueryForEs.getUserUuid()));
            }
            if (!StringUtils.isEmpty(assetQueryForEs.getMachineTags())) {
                List<String> machineTags = DataUtils.splitByComma(assetQueryForEs.getMachineTags());
                if(!DataUtils.listContain(machineTags,"全部")) {
                    queryBuilder.filter(QueryBuilders.termsQuery(Constant.USER_DATAS_MACHINETAGS_KEYWORD, Arrays.asList(assetQueryForEs.getMachineTags().split(","))));
                }
            }
            QueryBuilder nestedQuery = QueryBuilders.nestedQuery(Constant.USER_DATAS, queryBuilder
                    , ScoreMode.None);
            boolQueryBuilder.filter(nestedQuery);
        }else{
            if(assetQueryForEs.getOrgQueryFlag() == 1) {
                //组织架构查询
                List<String> orgUuids = DataUtils.splitByComma(assetQueryForEs.getOrgUuid());
                boolQueryBuilder.filter(QueryBuilders.termsQuery("orgDatas.orgUuid.keyword", orgUuids));
            }
        }

        if(assetQueryForEs.getSelectFlag() == 0) {
            if(!StringUtils.isEmpty(assetQueryForEs.getSelectMachineUuid())) {
                boolQueryBuilder.filter(QueryBuilders.termsQuery("machineUuid.keyword", DataUtils.splitByComma(assetQueryForEs.getSelectMachineUuid())));
            }
            if(!StringUtils.isEmpty(assetQueryForEs.getSelectId())) {
                boolQueryBuilder.filter(QueryBuilders.termsQuery("_id", DataUtils.splitByComma(assetQueryForEs.getSelectId())));
            }
        }else if(assetQueryForEs.getSelectFlag() == 1){
            assetQueryForEs.setMaxResults(AssetConfig.INDEX_MAX_RESULT_WINDOW);
        }

        //模糊查询
        if (!StringUtils.isEmpty(assetQueryForEs.getKeyWord()) && !CollectionUtils.isEmpty(assetQueryForEs.getKeys())) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            Long[] range = IpAddressUtils2.getRange(assetQueryForEs.getKeyWord());
            if(!ArrayUtils.isEmpty(range)) {
                //if(assetQueryForEs.getKeys().contains(Constant.MACHINE_IPS_IP)) {
                    boolQuery.should(QueryBuilders.rangeQuery(Constant.MACHINE_IPS_IP_LONG)
                            .gte(range[0])
                            .lte(range[1]));
                //}
                if(assetQueryForEs.getKeys().contains(Constant.MACHINE_PRIMARY_IPS_IP)) {
                    boolQuery.should(QueryBuilders.rangeQuery(Constant.MACHINE_PRIMARY_IPS_IP)
                            .gte(range[0])
                            .lte(range[1]));
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("for(int i=0;i<doc['machineIps.ip.keyword'].length;i++){if(doc['machineIps.version'][i] == 0 && !doc['machineIps.ip.keyword'][i].contains(':') && doc['machineIps.ip.keyword'][i].contains(params.keyWord)){return true;}} return false;");
            String code = sb.toString();
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("keyWord",assetQueryForEs.getKeyWord());
            Script script = new Script(Script.DEFAULT_SCRIPT_TYPE,
                    Script.DEFAULT_SCRIPT_LANG, code, params);
            boolQuery.should(QueryBuilders.scriptQuery(script));
            if (index.equals(new EsZcMachine().index())) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append("for(int i=0;i<doc['machinePrimaryIps.ip.keyword'].length;i++){if(doc['machinePrimaryIps.version'][i] == 0 && !doc['machinePrimaryIps.ip.keyword'][i].contains(':') && doc['machinePrimaryIps.ip.keyword'][i].contains(params.keyWord)){return true;}} return false;");
                String code1 = sb1.toString();
                Script script1 = new Script(Script.DEFAULT_SCRIPT_TYPE,
                        Script.DEFAULT_SCRIPT_LANG, code1, params);
                boolQuery.should(QueryBuilders.scriptQuery(script1));
            }
            QueryStringQueryBuilder queryStringQuery1 = QueryBuilders.queryStringQuery(QueryParserUtils.escape(assetQueryForEs.getKeyWord()));
            QueryStringQueryBuilder queryStringQuery2 = QueryBuilders.queryStringQuery("*" + QueryParserUtils.escape(assetQueryForEs.getKeyWord()) + "*");
            QueryStringQueryBuilder queryStringQuery3 = QueryBuilders.queryStringQuery("*" + QueryParserUtils.escape(assetQueryForEs.getKeyWord()) + "*");
            List<String> keys = assetQueryForEs.getKeys();
            for (String key : keys) {
                queryStringQuery1.field(key);
                queryStringQuery2.field(key);
                queryStringQuery3.field(key + ".keyword");
            }
            queryStringQuery1.defaultOperator(Operator.AND);
            queryStringQuery2.defaultOperator(Operator.AND);
            queryStringQuery3.defaultOperator(Operator.AND);
            boolQuery.should(queryStringQuery1);
            boolQuery.should(queryStringQuery2);
            boolQuery.should(queryStringQuery3);
            boolQueryBuilder.filter(boolQuery);
        }

        //过滤条件查询
        if (!CollectionUtils.isEmpty(assetQueryForEs.getFilters())) {
            assetQueryForEs.getFilters().forEach((key, value) -> {
                if (value instanceof List) {
                    List valueList = (List) value;
                    boolQueryBuilder.filter(QueryBuilders.termsQuery(key, valueList));
                } else {
                    boolQueryBuilder.filter(QueryBuilders.termQuery(key, value));
                }
            });
        }

        //范围过滤查询或特殊查询构建QueryBuilder
        if (!CollectionUtils.isEmpty(assetQueryForEs.getFilterQueryBuilders())) {
            assetQueryForEs.getFilterQueryBuilders().forEach(boolQueryBuilder::filter);
        }

        //统计机器数
        if (!StringUtils.isEmpty(assetQueryForEs.getCountField())) {
            TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms(Constant.MACHINE_COUNT).field(assetQueryForEs.getCountField()).order(BucketOrder.count(false)).size(AssetConfig.INDEX_MAX_RESULT_WINDOW);
            //是否需要除统计外其他额外字段
            if (assetQueryForEs.getIfCountOtherField()) {
                int size = assetQueryForEs.getCurrentPage() * assetQueryForEs.getMaxResults();
                aggregationBuilder.subAggregation(AggregationBuilders.topHits(Constant.COUNT_DETAIL).size(1));
            }
            searchSourceBuilder.aggregation(aggregationBuilder);
        }

        // 设置分页
        int form = (assetQueryForEs.getCurrentPage() - 1) * assetQueryForEs.getMaxResults();
        searchSourceBuilder.from(form);
        searchSourceBuilder.size(assetQueryForEs.getMaxResults());

        // 根据传的字段来设置排序sort
        if (assetQueryForEs.getSortField() != null) {
            searchSourceBuilder.sort(assetQueryForEs.getSortField(), assetQueryForEs.getSortOrder());
        }

        String query = searchSourceBuilder.query(boolQueryBuilder).toString();
        Search search = new Search.Builder(query).addIndex(index).addType(Constant.ES_TYPE).build();
        SearchResult searchResult = null;
        try {
            searchResult = jestClient.execute(search);
            if(!searchResult.isSucceeded()){
                log.error(searchResult.getErrorMessage());
                return new ResponseForPage<>();
            }
        } catch (IOException e) {
            log.error("es列表执行异常:", e);
        }

        //解析es查询的结果
        if (queryType == 0) {
            return queryCountListResult(tClass, searchResult, assetQueryForEs);
        } else {
            return queryDetailsListResult(tClass, searchResult, assetQueryForEs);
        }
    }


    private <T> ResponseForPage<T> queryCountListResult(Class<T> tClass, SearchResult searchResult, AssetQueryForEs assetQueryForEs) throws Exception {
        ResponseForPage<T> responeForPage = new ResponseForPage<>();
        //获取统计数据
        List<TermsAggregation.Entry> buckets = searchResult.getAggregations().getAggregation(Constant.MACHINE_COUNT, TermsAggregation.class).getBuckets();
        if (CollectionUtils.isEmpty(buckets)) {
            return new ResponseForPage<>();
        }
        List<T> list = new ArrayList<>();
        List<String> fields = assetQueryForEs.getFields();
        for (TermsAggregation.Entry bucket : buckets) {
            if (CollectionUtils.isEmpty(fields)) {
                return new ResponseForPage<>();
            }
            T t = null;
            try {
                //利用反射获取对象
                t = tClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new Exception("系统异常");
            }
            if (assetQueryForEs.getIfCountOtherField()) {
                List<SearchResult.Hit<T, Void>> hits = bucket.getTopHitsAggregation(Constant.COUNT_DETAIL).getHits(tClass);
                for (SearchResult.Hit<T, Void> hit : hits) {
                    t = hit.source;
                    if (t == null) {
                        continue;
                    }
                    if (ReflectUtils.ifFieldExist(Constant.ES_ID, t.getClass())) {
                        ReflectUtils.setValueByField(Constant.ES_ID, t, hit.id);
                    }
                    dealEsMachineIps(t);
                    filterEsUserDatas(t, assetQueryForEs.getUserUuid());
                    filterOrgDatas(t,assetQueryForEs.getOrgUuid());
                }
            }
            for (String field : fields) {
                Field declaredField = null;
                try {
                    declaredField = t.getClass().getDeclaredField(field);
                } catch (NoSuchFieldException e) {
                    throw new JowtoRuntimeException(ResponseCode.ES_QUERY_REFLEX_ERROR);
                }
                if (Objects.equals(declaredField.getType().getName(), "java.lang.String")) {
                    ReflectUtils.setValueByField(field, t, bucket.getKey());
                } else {
                    ReflectUtils.setValueByField(field, t, bucket.getCount());
                }
            }
            list.add(t);
        }
        //内存中分页
        int start = (assetQueryForEs.getCurrentPage() - 1) * assetQueryForEs.getMaxResults();
        int end = start + assetQueryForEs.getMaxResults();
        List<T> listPage = list.subList(Math.min(start,list.size()), Math.min(end, list.size()));
        responeForPage.setTotal((long) list.size());
        responeForPage.setList(listPage);
        return responeForPage;
    }


    private <T> ResponseForPage<T> queryDetailsListResult(Class<T> tClass, SearchResult searchResult, AssetQueryForEs assetQueryForEs) {
        ResponseForPage<T> responeForPage = new ResponseForPage<>();
        List<SearchResult.Hit<T, Void>> hits = searchResult.getHits(tClass);
        if (CollectionUtils.isEmpty(hits)) {
            return new ResponseForPage<>();
        }
        List<T> list = new ArrayList<>();
        for (SearchResult.Hit<T, Void> hit : hits) {
            T t = hit.source;
            if (t == null) {
                continue;
            }
            if (ReflectUtils.ifFieldExist(Constant.ES_ID, t.getClass())) {
                ReflectUtils.setValueByField(Constant.ES_ID, t, hit.id);
            }
            dealEsMachineIps(t);
            dealEsMachinePrimaryIps(t);
            filterEsUserDatas(t, assetQueryForEs.getUserUuid());
            filterOrgDatas(t,assetQueryForEs.getOrgUuid());
            list.add(t);
        }
        responeForPage.setTotal(searchResult.getJsonObject().getAsJsonObject(Constant.HITS).getAsJsonObject(Constant.TOTAL).get(Constant.VALUE).getAsLong());
        responeForPage.setList(list);
        return responeForPage;
    }

    private <T> void dealEsMachineIps(T t) {
        //通过属性获取属性值
        if (ReflectUtils.ifFieldExist("machineIps", t.getClass()) && ReflectUtils.ifFieldExist("ipv4", t.getClass()) && ReflectUtils.ifFieldExist("ipv6", t.getClass())) {
            List<EsZcMachineIp> machineIps = ReflectUtils.getValueByField("machineIps", t);
            if (!CollectionUtils.isEmpty(machineIps)) {
                ReflectUtils.setValueByField("ipv4", t, String.join(",", EsZcMachineIp.toIpv4s(machineIps)));
                ReflectUtils.setValueByField("ipv6", t, String.join(",", EsZcMachineIp.toIpv6s(machineIps)));
            }
        }
    }

    private <T> void dealEsMachinePrimaryIps(T t) {
        //通过属性获取属性值
        if (ReflectUtils.ifFieldExist("machinePrimaryIps", t.getClass()) && ReflectUtils.ifFieldExist("primaryIpv4", t.getClass()) && ReflectUtils.ifFieldExist("primaryIpv6", t.getClass())) {
            List<EsZcMachinePrimaryIp> machineIps = ReflectUtils.getValueByField("machinePrimaryIps", t);
            if (!CollectionUtils.isEmpty(machineIps)) {
                ReflectUtils.setValueByField("primaryIpv4", t, String.join(",", EsZcMachinePrimaryIp.toIpv4s(machineIps)));
                ReflectUtils.setValueByField("primaryIpv6", t, String.join(",", EsZcMachinePrimaryIp.toIpv6s(machineIps)));
            }
        }
    }

    private <T> void filterEsUserDatas(T t, String userUuid) {
        if (!StringUtils.isEmpty(userUuid) && ReflectUtils.ifFieldExist("userDatas", t.getClass())) {
            List<String> userUuids = DataUtils.splitByComma(userUuid);
            List<UserData> userDatas = ReflectUtils.getValueByField("userDatas", t);
            if (!CollectionUtils.isEmpty(userDatas)) {
                userDatas = userDatas.stream().filter(userData -> {
                    return userUuids.contains(userData.getUserUuid());
                }).collect(Collectors.toList());
                ReflectUtils.setValueByField("userDatas", t, userDatas);
                if (ReflectUtils.ifFieldExist("machineTag", t.getClass())) {
                    ReflectUtils.setValueByField("machineTag", t, String.join(",", userDatas.get(0).getMachineTags()));
                }
            }
        }
    }

    private static <T> T filterOrgDatas(T t,String orgUuid){
/*        if(t != null){
            try {
                if(ReflectUtils.ifFieldExist("orgDatas", t.getClass())) {
                    List<OrgData> orgDatas = (List<OrgData>) t.getClass().getMethod("getOrgDatas").invoke(t);
                    List<OrgData> results = filterChildOrgDatas(orgDatas, orgUuid);
                    t.getClass().getMethod("setOrgDatas", List.class).invoke(t, results);
                }
            }catch (Exception ex){
                log.error("",ex);
            }
        }*/
        return t;
    }
}
