package com.mt.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author ：WangYang
 * @version ：B1.5.1
 * @program ：bmp-prm
 * @date ：Created in 2021/6/17 14:02
 * @description ：shopify 商品数据
 */

@Data
@EqualsAndHashCode
public class ShopifyProductVO {

    /**
     * 唯一ID
     */
    private Long id;
    /**
     * SKU(带电压)
     */
    private String sku;
    /**
     * 站点
     */
    private String site;
    /**
     * 标题
     */
    private String title;
    /**
     * 产品链接
     */
    private String productLink;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 主图链接
     */
    private String mainImage;
    /**
     * 主图缩略图链接
     */
    private String mainAbbreviationImage;
    /**
     * 描述
     */
    private String description;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 颜色
     */
    private String color;
    /**
     * 材料
     */
    private String material;
    /**
     * 规格
     */
    private String specification;
    /**
     * 店铺邮箱
     */
    private String shopMailbox;
    /**
     * 商标
     */
    private String brand;
    /**
     * 一级类目
     */
    private String firstLevel;
    /**
     * 二级类目
     */
    private String secondLevel;
    /**
     * 三级类目
     */
    private String thirdLevel;
    /**
     * 类目字符串 格式：一级>二级>三级
     */
    private String categoryStr;
}
