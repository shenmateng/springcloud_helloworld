package com.mt.mapper.onnew;/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */


import com.mt.database.TemplateShopifyLogDO;

/**
 * @author ：makejava
 * @version ：M1.0
 * @program ：rabbitmq
 * @date ：Created in 2021/04/23 17:29
 * @description ：shopify自动导入数据log表 TemplateShopifyLogExtMapper
 */
public interface TemplateShopifyLogExtMapper extends TemplateShopifyLogMapper {
    /**
     * 修改数据
     *
     * @param templateShopifyLogDO 实例对象
     * @return 影响行数
     */
    int updateByTaskId(TemplateShopifyLogDO templateShopifyLogDO);


}
