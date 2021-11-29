package com.mt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：WangYang
 * @version ：B1.5.1
 * @program ：bmp-prm
 * @date ：Created in 2021/6/17 14:02
 * @description ：shopify 商品数据
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = ShopifyProductDO.INDEX_NAME, type = ShopifyProductDO.TYPE)
public class ShopifyProductDO implements Serializable {

    public static final String INDEX_NAME = "prm_shopify_product";
    public static final String TYPE = "_doc";

    /**
     * 唯一ID
     */
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;
    /**
     * SKU(带电压)
     */
    @Field(type = FieldType.Keyword)
    private String sku;
    /**
     * 站点
     */
    @Field(type = FieldType.Keyword)
    private String site;
    /**
     * 标题
     */
    @Field(type = FieldType.Keyword)
    private String title;
    /**
     * 产品链接
     */
    @Field(type = FieldType.Keyword)
    private String productLink;
    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    private BigDecimal price;
    /**
     * 主图链接
     */
    @Field(type = FieldType.Keyword)
    private String mainImage;
    /**
     * 主图缩略图链接
     */
    @Field(type = FieldType.Keyword)
    private String mainAbbreviationImage;
    /**
     * 描述
     */
    @Field(type = FieldType.Text)
    private String description;
    /**
     * 库存
     */
    @Field(type = FieldType.Integer)
    private Integer stock;
    /**
     * 颜色
     */
    @Field(type = FieldType.Keyword)
    private String color;
    /**
     * 材料
     */
    @Field(type = FieldType.Keyword)
    private String material;
    /**
     * 规格
     */
    @Field(type = FieldType.Keyword)
    private String specification;
    /**
     * 店铺邮箱
     */
    @Field(type = FieldType.Keyword)
    private String shopMailbox;
    /**
     * 商标
     */
    @Field(type = FieldType.Keyword)
    private String brand;
    /**
     * 一级类目
     */
    @Field(type = FieldType.Keyword)
    private String firstLevel;
    /**
     * 二级类目
     */
    @Field(type = FieldType.Keyword)
    private String secondLevel;
    /**
     * 三级类目
     */
    @Field(type = FieldType.Keyword)
    private String thirdLevel;
    /**
     * 类目字符串 格式：一级>二级>三级
     */
    @Field(type = FieldType.Keyword)
    private String categoryStr;
}
