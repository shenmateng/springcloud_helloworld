package com.mt.serivce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mt.bean.page.I18nUtil;
import com.mt.constant.ResponseCode;
import com.mt.database.*;
import com.mt.database.es.*;
import com.mt.exception.JowtoException;
import com.mt.exception.JowtoRuntimeException;
import com.mt.init.EsIndexInitJob;
import com.mt.utils.*;
import com.mt.bean.ResponseForPage;
import com.mt.config.AssetConfig;
import com.mt.constant.Constant;
import io.searchbox.action.BulkableAction;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.TermsAggregation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    @Autowired
    private JestApiService jestApiService;

    @Autowired
    private EsZcMachineServiceImpl esZcMachineService;

    @Autowired
    private ZcJestApiService zcJestApiService;

    @Autowired
    private EsIndexInitJob esIndexInitJob;

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
            //组合嵌套查询条件
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
        if (ObjectUtils.isNotEmpty(assetQueryForEs.getDriverVersion())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("driverVersion.keyword", assetQueryForEs.getDriverVersion()));
        }else {
            boolQueryBuilder.mustNot(QueryBuilders.existsQuery("driverVersion"));
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


    @Async("assetThreadPool")
    public void upsertZcMachine(UpsertZcMachineP upsertZcMachineP) throws JowtoException {
        log.info("upsertZcMachine:" + JSON.toJSONString(upsertZcMachineP));
        EsZcMachineSave esZcMachineSave = new EsZcMachineSave();
        esZcMachineSave.setId(upsertZcMachineP.getUuid());
        esZcMachineSave = jestApiService.searchDocById(esZcMachineSave);

        long start = System.currentTimeMillis();
        //机器
        ZcMachine zcMachine = new ZcMachine();
        BeanUtils.copyProperties(upsertZcMachineP, zcMachine);
        //更新机器其他
        saveEsMachineOther(zcMachine);
        log.info("添加或更新机器结束:" + upsertZcMachineP.getUuid() + "-CostTime:" + (System.currentTimeMillis() - start));

    }


    @Async("assetThreadPool")
    public void outreachMachineAgg() throws JowtoException {
        List<BulkableAction> eventInfos = new ArrayList<>();

        for(int i = 0;i<20010;i++){
            OutreachInfo outreachInfo = new OutreachInfo();
            outreachInfo.setUuid(UUIDUtils.uuid());
            outreachInfo.setServiceId(UUIDUtils.uuid());
            outreachInfo.setStandardTimestamp(new Date().getTime());
            outreachInfo.setEventInputTime(new Date().toString());
            outreachInfo.setLocalTimestamp(new Date().getTime());
            outreachInfo.setEventInputTime(new Date().toString());
            outreachInfo.setAgentInputTime("1721892508");
            outreachInfo.setCountTime(new Date().getTime());
            outreachInfo.setInnerIp("10.20.0.240");
            outreachInfo.setMachineUuid("e80da55f1cb319f8fb7de43453f8f104");
            outreachInfo.setMarkName("undefined");
            List<String> lists = new ArrayList<>();
            lists.add("1");
            outreachInfo.setOutreachIpAnDomain(lists);
            ObjectEntity objectEntity = new ObjectEntity();
            objectEntity.setDomain("sqm.telemetry.microsoft.com");
            objectEntity.setIp("65.55.252.93:443");
            outreachInfo.setObject(objectEntity);

            outreachInfo.setOutreachAddress("(美国x)");
            outreachInfo.setOutreachCount(i);
            outreachInfo.setOutreachIp("65.55.252.93");

            IpAddress ipAddress = new IpAddress();
            ipAddress.setCity("昆西x");
            ipAddress.setCountry("美国z");
            ipAddress.setCountryOrCity("美国z");
            ipAddress.setRegion("华盛顿州x");
            ipAddress.setType("外国x");
            outreachInfo.setOutreachIpAddress(ipAddress);

            UserSettingForAgg userAndSetting = new UserSettingForAgg();
            userAndSetting.setUserUuid("463051604aef4125824c322264ff3ca3");
            userAndSetting.setCompany("guoyu");
            userAndSetting.setUnit("迁移测试单位2");
            outreachInfo.setUserAndSettings(userAndSetting);

            Index build = new Index.Builder(outreachInfo).index("ys_outreach_machine_agg" + "-" + "2024-09-09").build();
            eventInfos.add(build);
        }

        List<BulkableAction> copyEventInfos = Collections.unmodifiableList(eventInfos);

        List<List<BulkableAction>> eventList = splitList(copyEventInfos, 2000);
        for (List<BulkableAction> list : eventList) {
            Bulk bulk = new Bulk.Builder().addAction(list).build();
            jestClient.executeAsync(bulk, new JestResultHandler<JestResult>() {
                @Override
                public void completed(JestResult jestResult) {
                }

                @Override
                public void failed(Exception e) {

                }
            });
        }

    }


    @Async("assetThreadPool")
    public void outreachMachineAgg1() throws JowtoException {
        List<BulkableAction> eventInfos = new ArrayList<>();

        for(int i = 0;i<20010;i++){
            OutreachInfo outreachInfo = new OutreachInfo();
            outreachInfo.setUuid(UUIDUtils.uuid());
            outreachInfo.setServiceId(UUIDUtils.uuid());
            outreachInfo.setAgentInputTime("1721892508");
            outreachInfo.setCountTime(new Date().getTime());
            outreachInfo.setInnerIp("10.20.0.240");
            outreachInfo.setIpAndDomain("sqm.telemetry.microsoft.com");
            outreachInfo.setStandardTimestamp(new Date().getTime());
            outreachInfo.setEventInputTime(new Date().toString());
            outreachInfo.setLocalTimestamp(new Date().getTime());
            outreachInfo.setEventInputTime(new Date().toString());
            outreachInfo.setMachineUuid("e80da55f1cb319f8fb7de43453f8f104");
            outreachInfo.setMarkName("undefined");
            List<String> lists = new ArrayList<>();
            lists.add("1");
            outreachInfo.setMaliciousList(lists);
            ObjectEntity objectEntity = new ObjectEntity();
            objectEntity.setDomain("sqm.telemetry.microsoft.com");
            objectEntity.setIp("65.55.252.93:443");
            outreachInfo.setObject(objectEntity);

            outreachInfo.setOutreachAddress("(美国x)");
            outreachInfo.setOutreachCount(i);
            outreachInfo.setOutreachIp("65.55.252.93");

            IpAddress ipAddress = new IpAddress();
            ipAddress.setCity("昆西x");
            ipAddress.setCountry("美国z");
            ipAddress.setCountryOrCity("美国z");
            ipAddress.setRegion("华盛顿州x");
            ipAddress.setType("外国x");
            outreachInfo.setOutreachIpAddress(ipAddress);
            List<String> machines = new ArrayList<>();
            List<String> ports = new ArrayList<>();
            machines.add("e80da55f1cb319f8fb7de43453f8f104");
            ports.add("443");
            outreachInfo.setOutreachMachine(machines);
            outreachInfo.setPorts(ports);
            Subject subject = new Subject();
            subject.setbVerify("no");
            subject.setCompany("Microsoft Corporation");
            subject.setPid("616");
            subject.setProcHash("eb833bb4df2cf5a4a7d1789c3add75dc");
            subject.setProcUuid("2ff914f7d234b90ba329f72b9c3fe3ff");
            subject.setProcess("\"\"C:\\windows\\system32\\wsqmcons.exe\"\"");
            subject.setType("2ff914f7d234b90ba329f72b9c3fe3ff");
            subject.setUser("NT AUTHORITY\\\\SYSTEM");
            outreachInfo.setSubject(subject);

            UserSettingForAgg userAndSetting = new UserSettingForAgg();
            userAndSetting.setUserUuid("463051604aef4125824c322264ff3ca3");
            userAndSetting.setCompany("guoyu");
            userAndSetting.setUnit("迁移测试单位2");
            outreachInfo.setUserAndSettings(userAndSetting);

            Index build = new Index.Builder(outreachInfo).index("ys_outreach_ip_domain_agg" + "-" + "2024-09-09").build();
            eventInfos.add(build);
        }

        List<BulkableAction> copyEventInfos = Collections.unmodifiableList(eventInfos);

        List<List<BulkableAction>> eventList = splitList(copyEventInfos, 2000);
        for (List<BulkableAction> list : eventList) {
            Bulk bulk = new Bulk.Builder().addAction(list).build();
            jestClient.executeAsync(bulk, new JestResultHandler<JestResult>() {
                @Override
                public void completed(JestResult jestResult) {
                }

                @Override
                public void failed(Exception e) {

                }
            });
        }

    }


    public void outreachMachineAgg2() throws IOException {
        List<BulkableAction> eventInfos = new ArrayList<>();

        for(int i = 0;i<1;i++){
            String json = "{\n" +
                    "    \"action\": {\n" +
                    "        \"text\": \"10.0.0.28(局域网)（计算机名localhost.localdomain）使用账号 root ssh远程登录服务器 10.20.0.206。来源：系统登录审计\"\n" +
                    "    },\n" +
                    "    \"agencyName\": \"总服务站\",\n" +
                    "    \"date\": \"2024-09-09\",\n" +
                    "    \"description\": \"ʧ��\",\n" +
                    "    \"ignoreStatus\": 1,\n" +
                    "    \"industry\": \"广电\",\n" +
                    "    \"innerIp\": \"10.20.0.206\",\n" +
                    "    \"ip\": \"10.0.0.28\",\n" +
                    "    \"ipv4\": \"10.20.0.206\",\n" +
                    "    \"localTimestamp\": 1725846305000,\n" +
                    "    \"loginName\": \"root\",\n" +
                    "    \"machine\": {\n" +
                    "        \"currentPage\": 1,\n" +
                    "        \"extranetIp\": \"10.20.0.206\",\n" +
                    "        \"intranetIp\": \"10.20.0.206\",\n" +
                    "        \"machineName\": \"localhost.localdomain\",\n" +
                    "        \"maxResults\": 10,\n" +
                    "        \"onlineStatus\": 1,\n" +
                    "        \"operatingSystem\": \"CentOS Linux release 7.9.2009 (Core)\",\n" +
                    "        \"osType\": 1,\n" +
                    "        \"uuid\": \"85b3d237dc28ca49924750924c1c68e3\"\n" +
                    "    },\n" +
                    "    \"machineUuid\": \"02e55654dbdfaef65fe6a1f0ba73c393\",\n" +
                    "    \"mainIp\": \"5.5.5.5\",\n" +
                    "    \"newDataFlag\": 1,\n" +
                    "    \"operateStatus\": 0,\n" +
                    "    \"operation\": \"system_login\",\n" +
                    "    \"operationDesc\": \"系统登录\",\n" +
                    "    \"operationExtra\": \"ʧ��\",\n" +
                    "    \"result\": 2,\n" +
                    "    \"sourceIpAddress\": {\n" +
                    "        \"city\": \"\",\n" +
                    "        \"country\": \"局域网\",\n" +
                    "        \"ip\": \"10.0.0.28\",\n" +
                    "        \"region\": \"\",\n" +
                    "        \"type\": \"局域网\"\n" +
                    "    },\n" +
                    "    \"standardTimestamp\": 1725846304000,\n" +
                    "    \"subject\": {\n" +
                    "        \"process\": \"\",\n" +
                    "        \"type\": \"ssh\",\n" +
                    "        \"user\": \"\"\n" +
                    "    },\n" +
                    "    \"typeName\": \"系统登录\",\n" +
                    "    \"unit\": \"cs002\",\n" +
                    "    \"updateStatus\": 0,\n" +
                    "    \"userUuid\": \"463051604aef4125824c322264ff3ca3\",\n" +
                    "    \"uuid\": \"ff332eca6ee94fd4bc32675f2614bde7\"\n" +
                    "}";

            SuspiciousLoginVO suspiciousLoginVO = JSONObject.parseObject(json, SuspiciousLoginVO.class);
            suspiciousLoginVO.setUuid(UUIDUtils.uuid());
            suspiciousLoginVO.setId(UUIDUtils.uuid());
            Index build = new Index.Builder(suspiciousLoginVO).index("suspicious_login").build();
            eventInfos.add(build);
        }

        List<BulkableAction> copyEventInfos = Collections.unmodifiableList(eventInfos);

        List<List<BulkableAction>> eventList = splitList(copyEventInfos, 2000);
        int i = 1;
        for (List<BulkableAction> list : eventList) {
            Bulk bulk = new Bulk.Builder().addAction(list).build();
            JestResult result = jestClient.execute(bulk);
            System.out.println("执行次数+"+i);
            i++;
        }
        System.out.println("执行完毕");

    }


    public void createIndex() throws IOException {
        this.esIndexInitJob.initIndex(Constant.YS_OUTREACH, System.currentTimeMillis(), false);
        this.esIndexInitJob.initAggIndex(Constant.YS_OUTREACH_MACHINE_AGG, System.currentTimeMillis(), false, true);
        this.esIndexInitJob.initAggIndex(Constant.YS_OUTREACH_MACHINE_PROCESS_AGG, System.currentTimeMillis(), false, true);
        this.esIndexInitJob.initAggIndex(Constant.YS_OUTREACH_IP_DOMAIN_AGG, System.currentTimeMillis(), false, true);
        this.esIndexInitJob.initAggIndex(Constant.YS_OUTREACH_IP_DOMAIN_PROCESS_AGG, System.currentTimeMillis(), false, true);

        System.out.println("执行完毕");

    }

    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    public void saveEsMachineOther(ZcMachine zcMachine) {
        try {
            List<EsZcMachineSave> esZcMachines = getNeedUpsertEsZcMachines(DataUtils.asList(zcMachine));
            log.info("ES更新：" + 1 + ":实际更新:" + esZcMachines.size());
            log.info("实际更新集合:" + JSON.toJSONString(esZcMachines));
            if (!CollectionUtils.isEmpty(esZcMachines)) {
                esZcMachineService.saveBatch(esZcMachines);
                for (EsZcMachineSave esZcMachine : esZcMachines) {
                    //按别名更新各类索引
                    zcJestApiService.updateEsZcCommanBy(EsZcMachineSave.sAlias(), EsZcMachineSave.sType(), esZcMachine.getMachineUuid(), esZcMachine);
                }
            }
        } catch (Exception ex) {
            log.error("", ex);
        }
    }



    public List<EsZcMachineSave> getNeedUpsertEsZcMachines(List<ZcMachine> zcMachines) {
        List<EsZcMachineSave> needUpserts = new ArrayList<>();
        for (ZcMachine zcMachine : zcMachines) {
            String machineUuid = zcMachine.getUuid();
            EsZcMachineSave newEsZcMachine = new EsZcMachineSave();
            if (newEsZcMachine == null) {
                continue;
            }
            EsZcMachineSave t = new EsZcMachineSave();
            t.setId(machineUuid+"100");
            t.setMachineUuid(machineUuid);
            t.setMachineName("dell");

            newEsZcMachine.setId(machineUuid+"100");
            newEsZcMachine.setMachineUuid(machineUuid);
            newEsZcMachine.setMachineName("qq飞车");
            newEsZcMachine.setMachineIp("8.142.22.145");
            newEsZcMachine.setDriverVersion("1234");
            List<UserData> userDataList = new ArrayList<>();
            UserData userData = new UserData();
            UserData userData1 = new UserData();
            userData.setUserUuid(machineUuid);
            userData.setSku("烧烤架");
            userData1.setUserUuid("nested30");
            userData1.setSku("手机");
            List<String> machineTagList = new ArrayList<>();
            List<String> machineTagList1 = new ArrayList<>();
            machineTagList.add("分组13");
            machineTagList.add("分组14");
            machineTagList1.add("分组15");
            machineTagList1.add("分组16");
            userData.setMachineTags(machineTagList);
            userData1.setMachineTags(machineTagList1);
            userDataList.add(userData);
            userDataList.add(userData1);
            newEsZcMachine.setUserDatas(userDataList);
            EsZcMachineSave oldEsZcMachine = null;
            try {
                oldEsZcMachine = this.zcJestApiService.searchDocById(t);
            } catch (Exception ex) {
                log.error("", ex);
            }
            if (oldEsZcMachine == null) {
                needUpserts.add(newEsZcMachine);
            } else {
                EsZcMachineSave newEsZcMachine2 = new EsZcMachineSave();
                EsZcMachineSave oldEsZcMachine2 = new EsZcMachineSave();
                Field[] fields = newEsZcMachine.getClass().getDeclaredFields();
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        if (field.getName().equals("serialVersionUID")
                                || field.getName().equals("updateTime"))
                            continue;
                        Object val1 = field.get(newEsZcMachine);
                        Object val2 = field.get(oldEsZcMachine);
                        if (val1 != null) {
                            field.set(newEsZcMachine2, val1);
                            field.set(oldEsZcMachine2, val2);
                        }
                    } catch (Exception ex) {
                        log.error("", ex);
                    }
                }
                String str1 = JSON.toJSONString(newEsZcMachine2);
                String str2 = JSON.toJSONString(oldEsZcMachine2);
                long v1 = DigitUtils.getCrc32(str1);
                long v2 = DigitUtils.getCrc32(str2);
                if (v1 != v2) {
                    log.info("newEsZcMachine2:" + str1);
                    log.info("oldEsZcMachine2:" + str2);
                    needUpserts.add(newEsZcMachine);
                    break;
                }
            }
        }
        return needUpserts;
    }












}
