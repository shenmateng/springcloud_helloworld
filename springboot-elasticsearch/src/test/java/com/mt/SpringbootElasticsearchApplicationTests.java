package com.mt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mt.pojo.ShopifyProductDO;
import com.mt.pojo.ShopifyProductVO;
import com.mt.pojo.User;
import com.mt.repository.ShopifyRepository;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.util.ObjectUtils;


import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootElasticsearchApplicationTests {

    @Autowired
    public RestHighLevelClient restHighLevelClient;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private ShopifyRepository repository;
    @Test
    void contextLoads() {
    }

    // 测试索引的创建， Request PUT liuyou_index
    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("mateng_index");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());// 查看是否创建成功
        System.out.println(createIndexResponse);// 查看返回对象
        restHighLevelClient.close();
    }

    // 测试获取索引，并判断其是否存在
    @Test
    public void testIndexIsExists() throws IOException {
        GetIndexRequest request = new GetIndexRequest("mateng_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);// 索引是否存在
        restHighLevelClient.close();
    }

    // 测试索引删除
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("mateng_index");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());// 是否删除成功
        restHighLevelClient.close();
    }

    // 测试添加文档(先创建一个User实体类，添加fastjson依赖)
    @Test
    public void testAddDocument() throws IOException {
        // 创建一个User对象
        User mateng = new User("mateng", 18);
        // 创建请求
        IndexRequest request = new IndexRequest("mateng_index");
        // 制定规则 PUT /mateng_index/_doc/1
        request.id("1");// 设置文档ID
        request.timeout(TimeValue.timeValueMillis(1000));// request.timeout("1s")
        // 将我们的数据放入请求中
        request.source(JSON.toJSONString(mateng), XContentType.JSON);

        // 客户端发送请求，获取响应的结果
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.status());// 获取建立索引的状态信息 CREATED
        System.out.println(response);// 查看返回内容 IndexResponse[index=liuyou_index,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
    }


    // 获取文档，判断是否存在 get /liuyou_index/_doc/1
    @Test
    public void testDocumentIsExists() throws IOException {
        GetRequest request = new GetRequest("mateng_index", "1");
        // 不获取返回的 _source的上下文了
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    // 测试获得文档信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("mateng_index","1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        String sourceAsString = response.getSourceAsString();
        //JSON字符串转换成Java对象
        User student1 = JSONObject.parseObject(sourceAsString, User.class);
        System.out.println(student1);// 打印文档内容
        System.out.println(request);// 返回的全部内容和命令是一样的
        restHighLevelClient.close();
    }

    // 测试更新文档内容
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("mateng_index", "1");
        User user = new User("shenmateng",23321);
        request.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.status()); // OK
        restHighLevelClient.close();
    }

    // 测试删除文档
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("mateng_index", "1");
        request.timeout("1s");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.status());// OK
    }


    // 特殊的，真的项目一般会 批量插入数据
    @Test
    public void testBulk() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("mateng-1",1));
        users.add(new User("mateng-2",2));
        users.add(new User("mateng-3",3));
        users.add(new User("mateng-4",4));
        users.add(new User("mateng-5",5));
        users.add(new User("mateng-6",6));
        // 批量请求处理
        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(
                    // 这里是数据信息
                    new IndexRequest("bulk")
                            .id(""+(i + 1)) // 没有设置id 会自定生成一个随机id
                            .source(JSON.toJSONString(users.get(i)),XContentType.JSON)
            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());// ok
    }


    // 查询
    // SearchRequest 搜索请求
    // SearchSourceBuilder 条件构造
    // HighlightBuilder 高亮
    // TermQueryBuilder 精确查询
    // MatchAllQueryBuilder
    // xxxQueryBuilder ...
    @Test
    public void testSearch() throws IOException {
        // 1.创建查询请求对象
        SearchRequest searchRequest = new SearchRequest();
        // 2.构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // (1)查询条件 使用QueryBuilders工具类创建
        // 精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "mateng");
        //        // 匹配查询
        //        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        // (2)其他<可有可无>：（可以参考 SearchSourceBuilder 的字段部分）
        // 设置高亮
        searchSourceBuilder.highlighter(new HighlightBuilder());
        //        // 分页
        //        searchSourceBuilder.from();
        //        searchSourceBuilder.size();
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // (3)条件投入
        searchSourceBuilder.query(termQueryBuilder);
        // 3.添加条件到请求
        searchRequest.source(searchSourceBuilder);
        // 4.客户端查询请求
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 5.查看返回结果
        SearchHits hits = search.getHits();
        System.out.println(JSON.toJSONString(hits));
        System.out.println("=======================");
        for (SearchHit documentFields : hits.getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }

    /**
     * 批量保存到ES中
     */
    @Test
    public void saveShopifyProducts() {
        List<ShopifyProductDO> shopifyProductDos = new ArrayList<>();
        for(int i = 0;i<=10;i++){
            ShopifyProductDO shopifyProductDO = new ShopifyProductDO();
            shopifyProductDO.setId((long)i);
            shopifyProductDO.setSku("神马腾"+i);
            shopifyProductDO.setSite("AU"+i);
            shopifyProductDO.setTitle("金克斯的含义就是金克斯"+i);
            shopifyProductDO.setProductLink("www.baidu.com"+"+"+i);
            BigDecimal bigDecimal = new BigDecimal(9623);
            shopifyProductDO.setPrice(bigDecimal);
            shopifyProductDO.setMainImage("主图连接"+i);
            shopifyProductDO.setMainAbbreviationImage("主图缩略图链接"+i);
            shopifyProductDO.setDescription("时间不在于你拥有多少"+i);
            shopifyProductDO.setStock(i);
            shopifyProductDO.setColor("五颜六色"+i);
            shopifyProductDO.setMaterial("海克斯水晶"+i);
            shopifyProductDO.setSpecification("苹果12，256G"+i);
            shopifyProductDO.setShopMailbox("店铺邮箱"+i);
            shopifyProductDO.setBrand("商标"+i);
            shopifyProductDO.setFirstLevel("一级类目"+i);
            shopifyProductDO.setSecondLevel("二级类目"+i);
            shopifyProductDO.setThirdLevel("三级类目"+i);
            shopifyProductDO.setCategoryStr("类目字符串 格式：一级>二级>三级"+"+"+i);
            shopifyProductDos.add(shopifyProductDO);
        }
        repository.saveAll(shopifyProductDos);

    }

    /**
     * 按条件删除数据,这里删除是可以的，但是查询因为版本问题会报错，这个api只支持7.0以下的api，这里我用的是7.6.2的
     */
    @Test
    public void queryAndDeleteTest() {
        List<ShopifyProductVO> shopifyProductVoss = new ArrayList<>();

        ShopifyProductVO shopifyProductVos1 = new ShopifyProductVO();
        ShopifyProductVO shopifyProductVos2 = new ShopifyProductVO();
        ShopifyProductVO shopifyProductVos3 = new ShopifyProductVO();
        long i = 3;
//        shopifyProductVos.setId(i);
        shopifyProductVos1.setSite("AU3");
        shopifyProductVos2.setSite("AU4");
        shopifyProductVos3.setSite("AU5");
        shopifyProductVos1.setFirstLevel("一级类目3");
        shopifyProductVos2.setFirstLevel("一级类目4");
        shopifyProductVos3.setFirstLevel("一级类目5");

        //删除
//        deleteShopifyProducts(shopifyProductVos);
        //查询
        shopifyProductVoss.add(shopifyProductVos1);
        shopifyProductVoss.add(shopifyProductVos2);
        shopifyProductVoss.add(shopifyProductVos3);
        //Elasticsearch的滚动查询
//        List<ShopifyProductDO> byIdShopifyProduct = findByIdShopifyProduct(shopifyProductVos);
//        System.out.println(byIdShopifyProduct);
        //Elasticsearch的批量滚动查询
        List<ShopifyProductDO> byIdShopifyProducts = findByIdShopifyProducts(shopifyProductVoss);
        System.out.println(byIdShopifyProducts);
    }

    /**
     * 按条件{@link ShopifyProductVO}查询数据,Elasticsearch的滚动查询
     *
     * @param shopifyProductVo {@link ShopifyProductVO} 删除条件查询
     * @return Boolean
     */
    public List<ShopifyProductDO> findByIdShopifyProduct(ShopifyProductVO shopifyProductVo) {

        if (ObjectUtils.isEmpty(shopifyProductVo)) {
            return new ArrayList<>();
        }
        //这是之前2.2.x版本的查询方式，已经被淘汰了。
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withIndices(ShopifyProductDO.INDEX_NAME)
//                .withTypes(ShopifyProductDO.TYPE)
//                .withQuery(matchCondition(shopifyProductVo))
//                .withPageable(PageRequest.of(0, 500))
//                .build();
//        // 搜索条件构造器构建：NativeSearchQuery
//        ScrolledPage<ShopifyProductVO> scroll = elasticsearchRestTemplate.startScroll(30000, searchQuery, ShopifyProductVO.class);
        // 构建搜索条件：搜索条件构造器
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        // 分页：默认第1页的50条数据
        searchQueryBuilder.withPageable(PageRequest.of(0, 2));
        searchQueryBuilder.withQuery(Objects.requireNonNull(matchCondition(shopifyProductVo)));
        // 搜索条件构造器构建：NativeSearchQuery
        NativeSearchQuery searchQuery1 = searchQueryBuilder.build();
        IndexCoordinates indexCoordinatesFor = elasticsearchRestTemplate.getIndexCoordinatesFor(ShopifyProductDO.class);
        SearchScrollHits<ShopifyProductDO> searchHits = elasticsearchRestTemplate.searchScrollStart(30000, searchQuery1, ShopifyProductDO.class, indexCoordinatesFor);
        List<org.springframework.data.elasticsearch.core.SearchHit<ShopifyProductDO>> searchHits1 = searchHits.getSearchHits();
        List<ShopifyProductDO> shopifyProductDos = new ArrayList<>();
        searchHits.forEach(o -> {
            ShopifyProductDO content = o.getContent();
            shopifyProductDos.add(content);
        });
        while (searchHits.getSearchHits().size() > 0) {
            searchHits = elasticsearchRestTemplate.searchScrollContinue(searchHits.getScrollId(), 30000, ShopifyProductDO.class, indexCoordinatesFor);
            searchHits.getSearchHits().forEach(o -> {
                ShopifyProductDO content = o.getContent();
                shopifyProductDos.add(content);
            });
        }
        System.out.println(shopifyProductDos);
//        shopifyProductDos.addAll(scroll.getContent());
//        while (scroll.hasContent()) {
//            scroll = elasticsearchRestTemplate.continueScroll(scroll.getScrollId(), 30000, ShopifyProductVO.class);
//            shopifyProductDos.addAll(scroll.getContent());
//        }
//        elasticsearchRestTemplate.clearScroll(scroll.getScrollId());
        //及时释放es服务器资源
        elasticsearchRestTemplate.searchScrollClear(Collections.singletonList(searchHits.getScrollId()));

        return shopifyProductDos;
    }

    /**
     * 按条件{@link ShopifyProductVO}查询数据,Elasticsearch的滚动查询,批量的
     *
     * @param shopifyProductVos {@link ShopifyProductVO} 查询条件
     * @return ShopifyProductVO
     */
    public List<ShopifyProductDO> findByIdShopifyProducts(List<ShopifyProductVO> shopifyProductVos) {

        final int timeOut = 30000;

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (ShopifyProductVO shopifyProductVo : shopifyProductVos) {
            QueryBuilder query = QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("site", shopifyProductVo.getSite()))
                    .must(QueryBuilders.matchQuery("firstLevel", shopifyProductVo.getFirstLevel()))
                /*    .must(QueryBuilders.matchQuery("secondLevel", shopifyProductVo.getSecondLevel()))
                    .must(QueryBuilders.matchQuery("thirdLevel", shopifyProductVo.getThirdLevel()))*/;

            boolQueryBuilder.should(query);
        }

        // 构建搜索条件：搜索条件构造器
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        // 分页：默认第1页的50条数据
        searchQueryBuilder.withPageable(PageRequest.of(0, 2));
        searchQueryBuilder.withQuery(Objects.requireNonNull(boolQueryBuilder));
        // 搜索条件构造器构建：NativeSearchQuery
        NativeSearchQuery searchQuery1 = searchQueryBuilder.build();
        IndexCoordinates indexCoordinatesFor = elasticsearchRestTemplate.getIndexCoordinatesFor(ShopifyProductDO.class);
        SearchScrollHits<ShopifyProductDO> searchHits = elasticsearchRestTemplate.searchScrollStart(timeOut, searchQuery1, ShopifyProductDO.class, indexCoordinatesFor);
        List<org.springframework.data.elasticsearch.core.SearchHit<ShopifyProductDO>> searchHits1 = searchHits.getSearchHits();
        List<ShopifyProductDO> shopifyProductDos = new ArrayList<>();
        searchHits.forEach(o -> {
            ShopifyProductDO content = o.getContent();
            shopifyProductDos.add(content);
        });
        while (searchHits.getSearchHits().size() > 0) {
            searchHits = elasticsearchRestTemplate.searchScrollContinue(searchHits.getScrollId(), timeOut, ShopifyProductDO.class, indexCoordinatesFor);
            searchHits.getSearchHits().forEach(o -> {
                ShopifyProductDO content = o.getContent();
                shopifyProductDos.add(content);
            });
        }
        System.out.println(shopifyProductDos);
        //及时释放es服务器资源
        elasticsearchRestTemplate.searchScrollClear(Collections.singletonList(searchHits.getScrollId()));
        return shopifyProductDos;
    }



    /**
     * 按条件{@link ShopifyProductVO}删除数据
     *
     * @param shopifyProductVos {@link ShopifyProductVO} 删除条件
     */
    public void deleteShopifyProducts(ShopifyProductVO shopifyProductVos) {

        if (ObjectUtils.isEmpty(shopifyProductVos)) {return;}

        IndexCoordinates indexCoordinatesFor = elasticsearchRestTemplate.getIndexCoordinatesFor(ShopifyProductDO.class);
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        searchQueryBuilder.withQuery(Objects.requireNonNull(matchCondition(shopifyProductVos)));
        NativeSearchQuery searchQuery1 = searchQueryBuilder.build();
        //删除
        elasticsearchRestTemplate.delete(searchQuery1,ShopifyProductDO.class,indexCoordinatesFor);
    }

    /**
     * 转换查询条件 (id(ProductId)/title(标题)/sku(Sku带电压)/site(站点)/shopMailbox(店铺邮箱)/firstLevel(一级类目)/secondLevel(二级类目)/thirdLevel(三级类目))
     *
     * @param shopifyProductVos Product
     * @return BoolQueryBuilder
     */
    private BoolQueryBuilder matchCondition(ShopifyProductVO shopifyProductVos) {
        if (ObjectUtils.isEmpty(shopifyProductVos)) {return null;}

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (shopifyProductVos.getId() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("id", shopifyProductVos.getId()));
        }
        if (StringUtils.isNotBlank(shopifyProductVos.getTitle())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("title", shopifyProductVos.getTitle()));
        }
        if (StringUtils.isNotBlank(shopifyProductVos.getSku())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("sku", shopifyProductVos.getSku()));
        }
        if (StringUtils.isNotBlank(shopifyProductVos.getSite())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("site", shopifyProductVos.getSite()));
        }
        if (StringUtils.isNotBlank(shopifyProductVos.getShopMailbox())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("shopMailbox", shopifyProductVos.getShopMailbox()));
        }
        if (StringUtils.isNotBlank(shopifyProductVos.getFirstLevel())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("firstLevel", shopifyProductVos.getFirstLevel()));
        }
        if (StringUtils.isNotBlank(shopifyProductVos.getSecondLevel())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("secondLevel", shopifyProductVos.getSecondLevel()));
        }
        if (StringUtils.isNotBlank(shopifyProductVos.getThirdLevel())) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("thirdLevel", shopifyProductVos.getThirdLevel()));
        }

        return boolQueryBuilder;
    }

}
