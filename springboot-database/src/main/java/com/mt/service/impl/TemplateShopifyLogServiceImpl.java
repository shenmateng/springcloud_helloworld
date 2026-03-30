package com.mt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mt.database.TemplateShopifyLogDO;
import com.mt.database.TemplateShopifyLogVO;
import com.mt.mapper.onnew.TemplateShopifyLogMapper;
import com.mt.service.TemplateShopifyLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mateng
 * @description Shopify日志Service实现类
 * @date 2024
 */
@Slf4j
@Service
public class TemplateShopifyLogServiceImpl implements TemplateShopifyLogService {

    @Resource
    private TemplateShopifyLogMapper templateShopifyLogMapper;

    @Override
    public TemplateShopifyLogVO queryById(Integer id) {
        log.info("查询Shopify日志，ID: {}", id);
        TemplateShopifyLogDO dataObject = templateShopifyLogMapper.queryById(id);
        return convertToVO(dataObject);
    }

    @Override
    public PageInfo<TemplateShopifyLogVO> queryPage(TemplateShopifyLogDO condition, Integer pageNum, Integer pageSize) {
        log.info("分页查询Shopify日志，条件: {}, 页码: {}, 每页大小: {}", condition, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<TemplateShopifyLogDO> list = templateShopifyLogMapper.queryAll(condition);
        PageInfo<TemplateShopifyLogDO> pageInfo = new PageInfo<>(list);
        
        // 转换为VO
        List<TemplateShopifyLogVO> voList = list.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        
        PageInfo<TemplateShopifyLogVO> result = new PageInfo<>(voList);
        result.setTotal(pageInfo.getTotal());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setPages(pageInfo.getPages());
        
        return result;
    }

    @Override
    public List<TemplateShopifyLogVO> queryList(TemplateShopifyLogDO condition) {
        log.info("查询Shopify日志列表，条件: {}", condition);
        List<TemplateShopifyLogDO> list = templateShopifyLogMapper.queryAll(condition);
        return list.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(TemplateShopifyLogDO templateShopifyLogDO) {
        log.info("新增Shopify日志: {}", templateShopifyLogDO);
        int rows = templateShopifyLogMapper.insertSelective(templateShopifyLogDO);
        if (rows > 0) {
            log.info("新增成功，ID: {}", templateShopifyLogDO.getId());
            return templateShopifyLogDO.getId();
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertBatch(List<TemplateShopifyLogDO> list) {
        log.info("批量新增Shopify日志，数量: {}", list.size());
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int rows = templateShopifyLogMapper.insertBatch(list);
        log.info("批量新增成功，影响行数: {}", rows);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(TemplateShopifyLogDO templateShopifyLogDO) {
        log.info("修改Shopify日志: {}", templateShopifyLogDO);
        int rows = templateShopifyLogMapper.update(templateShopifyLogDO);
        log.info("修改成功，影响行数: {}", rows);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateBatch(List<TemplateShopifyLogDO> list) {
        log.info("批量修改Shopify日志，数量: {}", list.size());
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int rows = templateShopifyLogMapper.updateBatch(list);
        log.info("批量修改成功，影响行数: {}", rows);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteById(Integer id) {
        log.info("物理删除Shopify日志，ID: {}", id);
        int rows = templateShopifyLogMapper.deleteById(id);
        log.info("物理删除成功，影响行数: {}", rows);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByIds(List<Integer> ids) {
        log.info("批量物理删除Shopify日志，IDs: {}", ids);
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        int rows = templateShopifyLogMapper.deleteByIds(ids);
        log.info("批量物理删除成功，影响行数: {}", rows);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer logicDeleteById(Integer id) {
        log.info("逻辑删除Shopify日志，ID: {}", id);
        int rows = templateShopifyLogMapper.logicDeleteById(id);
        log.info("逻辑删除成功，影响行数: {}", rows);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer logicDeleteByIds(List<Integer> ids) {
        log.info("批量逻辑删除Shopify日志，IDs: {}", ids);
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        int rows = templateShopifyLogMapper.logicDeleteByIds(ids);
        log.info("批量逻辑删除成功，影响行数: {}", rows);
        return rows;
    }

    @Override
    public List<TemplateShopifyLogVO> queryByTaskId(String taskId) {
        log.info("根据任务ID查询Shopify日志，taskId: {}", taskId);
        TemplateShopifyLogDO condition = new TemplateShopifyLogDO();
        condition.setTaskId(taskId);
        List<TemplateShopifyLogDO> list = templateShopifyLogMapper.queryAll(condition);
        return list.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TemplateShopifyLogVO> queryByStatus(Integer status) {
        log.info("根据状态查询Shopify日志，status: {}", status);
        TemplateShopifyLogDO condition = new TemplateShopifyLogDO();
        condition.setStatus(status);
        List<TemplateShopifyLogDO> list = templateShopifyLogMapper.queryAll(condition);
        return list.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TemplateShopifyLogVO> queryByPlatformAndSite(String platform, String site) {
        log.info("根据平台和站点查询Shopify日志，platform: {}, site: {}", platform, site);
        TemplateShopifyLogDO condition = new TemplateShopifyLogDO();
        condition.setPlatfrom(platform);
        condition.setSite(site);
        List<TemplateShopifyLogDO> list = templateShopifyLogMapper.queryAll(condition);
        return list.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    /**
     * DO转VO
     *
     * @param dataObject DO对象
     * @return VO对象
     */
    private TemplateShopifyLogVO convertToVO(TemplateShopifyLogDO dataObject) {
        if (dataObject == null) {
            return null;
        }
        TemplateShopifyLogVO vo = new TemplateShopifyLogVO();
        BeanUtils.copyProperties(dataObject, vo);
        return vo;
    }

    /**
     * VO转DO
     *
     * @param vo VO对象
     * @return DO对象
     */
    private TemplateShopifyLogDO convertToDO(TemplateShopifyLogVO vo) {
        if (vo == null) {
            return null;
        }
        TemplateShopifyLogDO dataObject = new TemplateShopifyLogDO();
        BeanUtils.copyProperties(vo, dataObject);
        return dataObject;
    }
}
