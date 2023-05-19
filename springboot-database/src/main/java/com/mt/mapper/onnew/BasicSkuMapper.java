/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.mt.mapper.onnew;

import com.mt.database.BasicSkuDO;
import com.mt.database.ZcMachine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：zhangsunjiankun
 * @version ：M1.0
 * @program ：bmp-prm
 * @date ：Created in 2021/04/14 16:58
 * @description ：商品信息表 BasicSkuMapper
 */
public interface BasicSkuMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BasicSkuDO queryById(Integer id);

    /**
     * 通过ID集合查询多条数据
     *
     * @param ids 主键集合
     * @return 实例对象集合
     */
    List<BasicSkuDO> queryByIds(@Param("ids") List<Integer> ids);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param basicSkuDO 实例对象
     * @return 对象列表
     */
    List<BasicSkuDO> queryAll(BasicSkuDO basicSkuDO);

    /**
     * 新增可选数据
     *
     * @param basicSkuDO 实例对象
     * @return 影响行数
     */
    int insertSelective(BasicSkuDO basicSkuDO);

    /**
     * 批量新增数据
     *
     * @param basicSkuDOList 实例对象集合
     * @return 影响行数
     */
    int insertBatch(@Param("dataObjectList") List<BasicSkuDO> basicSkuDOList);

    /**
     * 修改数据
     *
     * @param basicSkuDO 实例对象
     * @return 影响行数
     */
    int update(BasicSkuDO basicSkuDO);

    /**
     * 批量修改数据
     *
     * @param basicSkuDOList 实例对象集合
     * @return 影响行数
     */
    int updateBatch(@Param("dataObjectList") List<BasicSkuDO> basicSkuDOList);

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

    List<BasicSkuDO> querySku();

    List<ZcMachine> findOffMachineByAll();

     BasicSkuDO BasicSkuMapper();
}