package com.mt.database;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：mateng
 * @version ：1.3.8
 * @description ：导入VO
 * @program ：bmp
 * @date ：Created in 2021/4/27 19:38
 * @since ：1.3.8
 */

@Data
public class VevorNewListingReqVO implements Serializable {

    private static final long serialVersionUID = -1333852634359698905L;

    /**
     * 站点
     */
    @NotBlank( message= "站点不能为空")
    private String siteCode;

    /**
     * spu_code
     */
    private String spuCode;
    /**
     * spu中文名称
     */
    private String spuName;

    /**
     * 带电SKU
     */
    @NotBlank( message= "带电SKU不能为空")
    private String sku;

    /**
     * 不带电SKU
     */
    @NotBlank( message= "不带电SKU不能为空")
    private String skuWithoutVoltage;

    /**
     * 结果
     */
    private String status;

    /**
     * 原因
     */
    private String reason;
    /**
     * 商品类型
     */
    private String goodsType;

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
     * listing_price_amazon
     */
    private String listingPriceAmazon;

    /**
     * listing_price_shopify
     */
    private BigDecimal listingPriceVevor;

    /**
     * listing_price_shopify
     */
    private String upc;

}
