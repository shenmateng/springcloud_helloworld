/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：makejava
 * @version ：M1.0
 * @program ：rabbitmq
 * @date ：Created in 2021/04/23 17:29
 * @description ：shopify自动导入数据log表实体类 TemplateShopifyLogDO
 */
@Data
public class TemplateShopifyLogDO implements Serializable {
    private static final long serialVersionUID = 954745001090549866L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 平台
     */
    private String platfrom;
    /**
     * 站点
     */
    private String site;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 状态(0:处理中  1:完成  2:错误)
     */
    private Integer status;
    /**
     * 生成模板和范本成功的条数
     */
    private Integer successData;
    /**
     * 生成模板和范本失败的条数
     */
    private Integer failData;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 是否删除 0未删除 1已删除
     */
    private Integer delete;

    /**
     * 开始时间
     */
    private String startTimes;



}
