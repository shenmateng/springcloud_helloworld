package com.mt.mapper.onnew;/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

import com.mt.database.TemplateShopifyLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ：makejava
 * @version ：M1.0
 * @program ：rabbitmq
 * @date ：Created in 2021/04/23 17:29
 * @description ：shopify自动导入数据log表 TemplateShopifyLogMapper
 */
public interface TemplateShopifyLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TemplateShopifyLogDO queryById(Integer id);

    /**
     * 通过ID集合查询多条数据
     *
     * @param ids 主键集合
     * @return 实例对象集合
     */
    List<TemplateShopifyLogDO> queryByIds(@Param("ids") List<Integer> ids);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param templateShopifyLogDO 实例对象
     * @return 对象列表
     */
    List<TemplateShopifyLogDO> queryAll(TemplateShopifyLogDO templateShopifyLogDO);

    /**
     * 新增可选数据
     *
     * @param templateShopifyLogDO 实例对象
     * @return 影响行数
     */
    int insertSelective(TemplateShopifyLogDO templateShopifyLogDO);

    /**
     * 批量新增数据
     *
     * @param templateShopifyLogDOList 实例对象集合
     * @return 影响行数
     */
    int insertBatch(@Param("dataObjectList") List<TemplateShopifyLogDO> templateShopifyLogDOList);

    /**
     * 修改数据
     *
     * @param templateShopifyLogDO 实例对象
     * @return 影响行数
     */
    int update(TemplateShopifyLogDO templateShopifyLogDO);

    /**
     * 批量修改数据
     *
     * @param templateShopifyLogDOList 实例对象集合
     * @return 影响行数
     */
    int updateBatch(@Param("dataObjectList") List<TemplateShopifyLogDO> templateShopifyLogDOList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键集合删除数据
     *
     * @param ids 主键集合
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") List<Integer> ids);


    /**
     * 通过主键逻辑删除数据,0:未删除,1:已删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int logicDeleteById(Integer id);

    /**
     * 通过主键集合逻辑删除数据,0:未删除,1:已删除
     *
     * @param ids 主键集合
     * @return 影响行数
     */
    int logicDeleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param templateShopifyLogDO 实例对象
     * @return 对象列表
     */
    List<TemplateShopifyLogDO> queryAlls(TemplateShopifyLogDO templateShopifyLogDO);
}
