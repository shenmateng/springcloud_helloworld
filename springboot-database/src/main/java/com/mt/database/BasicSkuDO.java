/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：zhangsunjiankun
 * @version ：M1.0
 * @program ：bmp-prm
 * @date ：Created in 2021/04/14 16:58
 * @description ：商品信息表实体类 BasicSkuDO
 */
@Data
public class BasicSkuDO implements Serializable {
    private static final long serialVersionUID = -15469004241082130L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 商品中心Id
     */
    private Integer goodsId;
    /**
     * 系统SKU
     */
    private String sku;
    /**
     * 电压
     */
    private String voltage;
    /**
     * 不带电压SKU
     */
    private String skuWithoutVoltage;
    /**
     * 产品中文名称
     */
    private String chineseName;
    /**
     * 商品spuCode
     */
    private String spu;
    /**
     * 商品spu英文名称
     */
    private String spuEnName;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 小组id
     */
    private Integer groupId;
    /**
     * 小组
     */
    private String group;
    /**
     * 重量(单位：kg)
     */
    private BigDecimal weightKg;
    /**
     * 产品重lbs
     */
    private BigDecimal weightLbs;
    /**
     * 商品长度(单位：cm)
     */
    private BigDecimal lengthCm;
    /**
     * 商品宽度(单位：cm)
     */
    private BigDecimal widthCm;
    /**
     * 商品高度(单位：cm)
     */
    private BigDecimal heightCm;
    /**
     * 产品长inch
     */
    private BigDecimal lengthInch;
    /**
     * 产品宽inch
     */
    private BigDecimal widthInch;
    /**
     * 产品高inch
     */
    private BigDecimal heightInch;
    /**
     * 颜色
     */
    private String color;
    /**
     * 图片地址
     */
    private String images;
    /**
     * 后台类目Id
     */
    private Integer backCategoryId;
    /**
     * sku创建时间
     */
    private Date skuCreateTime;
    /**
     * sku跟新时间
     */
    private Date skuUpdateTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除 0未删除 1已删除
     */
    private Integer delete;

}