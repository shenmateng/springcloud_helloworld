package com.mt.repository;

import com.mt.pojo.ShopifyProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：WangYang
 * @version ：B1.5.4
 * @program ：bmp-prm
 * @date ：Created in 2021/6/17 14:15
 * @description ：Shopify 存储库
 */

@Repository
public interface ShopifyRepository extends ElasticsearchRepository<ShopifyProductDO,String> {



}
