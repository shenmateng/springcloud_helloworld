package com.mt.service;

import com.github.pagehelper.PageInfo;
import com.mt.database.TemplateShopifyLogDO;
import com.mt.database.TemplateShopifyLogVO;

import java.util.List;

/**
 * @author mateng
 * @description Shopify日志Service接口
 * @date 2024
 */
public interface TemplateShopifyLogService {

    /**
     * 根据ID查询单条数据
     *
     * @param id 主键ID
     * @return 实例对象
     */
    TemplateShopifyLogVO queryById(Integer id);

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @return 分页结果
     */
    PageInfo<TemplateShopifyLogVO> queryPage(TemplateShopifyLogDO condition, Integer pageNum, Integer pageSize);

    /**
     * 条件查询（不分页）
     *
     * @param condition 查询条件
     * @return 对象列表
     */
    List<TemplateShopifyLogVO> queryList(TemplateShopifyLogDO condition);

    /**
     * 新增数据
     *
     * @param templateShopifyLogDO 实例对象
     * @return 主键ID
     */
    Integer insert(TemplateShopifyLogDO templateShopifyLogDO);

    /**
     * 批量新增数据
     *
     * @param list 实例对象列表
     * @return 影响行数
     */
    Integer insertBatch(List<TemplateShopifyLogDO> list);

    /**
     * 修改数据
     *
     * @param templateShopifyLogDO 实例对象
     * @return 影响行数
     */
    Integer update(TemplateShopifyLogDO templateShopifyLogDO);

    /**
     * 批量修改数据
     *
     * @param list 实例对象列表
     * @return 影响行数
     */
    Integer updateBatch(List<TemplateShopifyLogDO> list);

    /**
     * 物理删除（根据ID）
     *
     * @param id 主键ID
     * @return 影响行数
     */
    Integer deleteById(Integer id);

    /**
     * 批量物理删除
     *
     * @param ids 主键ID列表
     * @return 影响行数
     */
    Integer deleteByIds(List<Integer> ids);

    /**
     * 逻辑删除（根据ID）
     *
     * @param id 主键ID
     * @return 影响行数
     */
    Integer logicDeleteById(Integer id);

    /**
     * 批量逻辑删除
     *
     * @param ids 主键ID列表
     * @return 影响行数
     */
    Integer logicDeleteByIds(List<Integer> ids);

    /**
     * 根据任务ID查询
     *
     * @param taskId 任务ID
     * @return 对象列表
     */
    List<TemplateShopifyLogVO> queryByTaskId(String taskId);

    /**
     * 根据状态查询
     *
     * @param status 状态(0:处理中 1:完成 2:错误)
     * @return 对象列表
     */
    List<TemplateShopifyLogVO> queryByStatus(Integer status);

    /**
     * 根据平台和站点查询
     *
     * @param platform 平台
     * @param site     站点
     * @return 对象列表
     */
    List<TemplateShopifyLogVO> queryByPlatformAndSite(String platform, String site);
}
