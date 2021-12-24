package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：mateng
 * @version ：1.3.8
 * @description ： shopify上新清单VO
 * @program ： bmp
 * @date ：Created in 2021/4/15 16:20
 * @since ：1.3.8
 */

@Data
public class ShopifyNewListingVO implements Serializable {

    private static final long serialVersionUID = -2141260042337437467L;
    /**
     * 站点
     */
    private String siteCode;

    /**
     * 带电SKU
     */
    private String sku;

    /**
     * 不带电SKU
     */
    private String skuWithoutVoltage;

    /**
     * 一级分类
     */
    private String category1;

    /**
     * 二级分类
     */
    private String category2;

    /**
     * 三级分类
     */
    private String category3;

    /**
     * tags
     */
    private String tags;

    /**
     * image_main_path
     */
    private String imageMainPath;

    /**
     * image_path
     */
    private String imagePath;

    /**
     * listing_price_ebay
     */
    private BigDecimal listingPriceEbay;

    /**
     * listing_price_shopify
     */
    private BigDecimal listingPriceShopify;



}
