package com.mt.mapper.onnew;/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */


import com.mt.database.SystemWhiteListAndMachine;
import com.mt.database.TemplateShopifyLogDO;
import com.mt.database.ZcMachineTagName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询这个用户管理了哪些机器(用户创建了这些分组，分组选了这些机器；并且还要是这个用户管理了这些机器)
     */
    List<ZcMachineTagName> selectMachineUuidsByTagName(@Param("userUuid") String userUuid, @Param("tagName") String tagName, @Param("onlineStatus") Integer onlineStatus);

    List<SystemWhiteListAndMachine> listByAllIds(@Param("whiteListId") List<String> whiteListId);



}
