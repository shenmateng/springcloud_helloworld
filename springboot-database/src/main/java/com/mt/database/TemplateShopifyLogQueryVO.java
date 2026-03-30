package com.mt.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mateng
 * @description Shopify日志查询VO
 * @date 2024
 */
@Data
public class TemplateShopifyLogQueryVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

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
     * 开始时间-起始
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTimeBegin;

    /**
     * 开始时间-结束
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTimeEnd;

    /**
     * 结束时间-起始
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTimeBegin;

    /**
     * 结束时间-结束
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTimeEnd;

    /**
     * 状态(0:处理中 1:完成 2:错误)
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;
}
