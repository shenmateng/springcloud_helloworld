package com.mt.controller;

import com.github.pagehelper.PageInfo;
import com.mt.ResponseResult.ResponseResult;
import com.mt.database.TemplateShopifyLogDO;
import com.mt.database.TemplateShopifyLogVO;
import com.mt.service.TemplateShopifyLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author mateng
 * @description Shopify日志管理Controller
 * @date 2024
 */
@Slf4j
@RestController
@RequestMapping("/api/shopify-log")
public class TemplateShopifyLogController {

    @Resource
    private TemplateShopifyLogService templateShopifyLogService;

    /**
     * 根据ID查询单条数据
     *
     * @param id 主键ID
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseResult<TemplateShopifyLogVO> queryById(@PathVariable("id") Integer id) {
        log.info("查询Shopify日志，ID: {}", id);
        TemplateShopifyLogVO result = templateShopifyLogService.queryById(id);
        return ResponseResult.success(result);
    }

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @return 分页结果
     */
    @PostMapping("/page")
    public ResponseResult<List<TemplateShopifyLogVO>> queryPage(
            @RequestBody TemplateShopifyLogDO condition,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询Shopify日志，条件: {}, 页码: {}, 每页大小: {}", condition, pageNum, pageSize);
        PageInfo<TemplateShopifyLogVO> pageInfo = templateShopifyLogService.queryPage(condition, pageNum, pageSize);
        return ResponseResult.successPage(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    /**
     * 条件查询（不分页）
     *
     * @param condition 查询条件
     * @return 数据列表
     */
    @PostMapping("/list")
    public ResponseResult<List<TemplateShopifyLogVO>> queryList(@RequestBody TemplateShopifyLogDO condition) {
        log.info("查询Shopify日志列表，条件: {}", condition);
        List<TemplateShopifyLogVO> list = templateShopifyLogService.queryList(condition);
        return ResponseResult.success(list);
    }

    /**
     * 新增数据
     *
     * @param templateShopifyLogDO 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseResult<Integer> insert(@Valid @RequestBody TemplateShopifyLogDO templateShopifyLogDO) {
        log.info("新增Shopify日志: {}", templateShopifyLogDO);
        Integer id = templateShopifyLogService.insert(templateShopifyLogDO);
        return ResponseResult.success(id);
    }

    /**
     * 批量新增数据
     *
     * @param list 实体对象列表
     * @return 新增结果
     */
    @PostMapping("/batch")
    public ResponseResult<Integer> insertBatch(@Valid @RequestBody List<TemplateShopifyLogDO> list) {
        log.info("批量新增Shopify日志，数量: {}", list.size());
        Integer count = templateShopifyLogService.insertBatch(list);
        return ResponseResult.success(count);
    }

    /**
     * 修改数据
     *
     * @param templateShopifyLogDO 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseResult<Integer> update(@Valid @RequestBody TemplateShopifyLogDO templateShopifyLogDO) {
        log.info("修改Shopify日志: {}", templateShopifyLogDO);
        Integer count = templateShopifyLogService.update(templateShopifyLogDO);
        return ResponseResult.success(count);
    }

    /**
     * 批量修改数据
     *
     * @param list 实体对象列表
     * @return 修改结果
     */
    @PutMapping("/batch")
    public ResponseResult<Integer> updateBatch(@Valid @RequestBody List<TemplateShopifyLogDO> list) {
        log.info("批量修改Shopify日志，数量: {}", list.size());
        Integer count = templateShopifyLogService.updateBatch(list);
        return ResponseResult.success(count);
    }

    /**
     * 物理删除（根据ID）
     *
     * @param id 主键ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseResult<Integer> deleteById(@PathVariable("id") Integer id) {
        log.info("物理删除Shopify日志，ID: {}", id);
        Integer count = templateShopifyLogService.deleteById(id);
        return ResponseResult.success(count);
    }

    /**
     * 批量物理删除
     *
     * @param ids 主键ID列表
     * @return 删除结果
     */
    @DeleteMapping("/batch")
    public ResponseResult<Integer> deleteByIds(@RequestBody List<Integer> ids) {
        log.info("批量物理删除Shopify日志，IDs: {}", ids);
        Integer count = templateShopifyLogService.deleteByIds(ids);
        return ResponseResult.success(count);
    }

    /**
     * 逻辑删除（根据ID）
     *
     * @param id 主键ID
     * @return 删除结果
     */
    @PutMapping("/logic-delete/{id}")
    public ResponseResult<Integer> logicDeleteById(@PathVariable("id") Integer id) {
        log.info("逻辑删除Shopify日志，ID: {}", id);
        Integer count = templateShopifyLogService.logicDeleteById(id);
        return ResponseResult.success(count);
    }

    /**
     * 批量逻辑删除
     *
     * @param ids 主键ID列表
     * @return 删除结果
     */
    @PutMapping("/logic-delete/batch")
    public ResponseResult<Integer> logicDeleteByIds(@RequestBody List<Integer> ids) {
        log.info("批量逻辑删除Shopify日志，IDs: {}", ids);
        Integer count = templateShopifyLogService.logicDeleteByIds(ids);
        return ResponseResult.success(count);
    }

    /**
     * 根据任务ID查询
     *
     * @param taskId 任务ID
     * @return 数据列表
     */
    @GetMapping("/task/{taskId}")
    public ResponseResult<List<TemplateShopifyLogVO>> queryByTaskId(@PathVariable("taskId") String taskId) {
        log.info("根据任务ID查询Shopify日志，taskId: {}", taskId);
        List<TemplateShopifyLogVO> list = templateShopifyLogService.queryByTaskId(taskId);
        return ResponseResult.success(list);
    }

    /**
     * 根据状态查询
     *
     * @param status 状态(0:处理中 1:完成 2:错误)
     * @return 数据列表
     */
    @GetMapping("/status/{status}")
    public ResponseResult<List<TemplateShopifyLogVO>> queryByStatus(@PathVariable("status") Integer status) {
        log.info("根据状态查询Shopify日志，status: {}", status);
        List<TemplateShopifyLogVO> list = templateShopifyLogService.queryByStatus(status);
        return ResponseResult.success(list);
    }

    /**
     * 根据平台和站点查询
     *
     * @param platform 平台
     * @param site     站点
     * @return 数据列表
     */
    @GetMapping("/platform-site")
    public ResponseResult<List<TemplateShopifyLogVO>> queryByPlatformAndSite(
            @RequestParam String platform,
            @RequestParam String site) {
        log.info("根据平台和站点查询Shopify日志，platform: {}, site: {}", platform, site);
        List<TemplateShopifyLogVO> list = templateShopifyLogService.queryByPlatformAndSite(platform, site);
        return ResponseResult.success(list);
    }
}
