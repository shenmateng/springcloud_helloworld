package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：mateng
 * @version ：1.3.8
 * @description ：生成模板和范本DO
 * @program ：bmp
 * @date ：Created in 2021/4/25 8:43
 * @since ：1.3.8
 */
@Data
public class TemplateVevorListAndTaskDO implements Serializable {
    private static final long serialVersionUID = -6907045303514231976L;
    /**
     * 导入SKU集合
     */
    private List<VevorNewListingReqVO> vevorNewListingReq;
    /**
     * 任务Id
     */
    private String taskId;
    /**
     * 用户名
     */
    private String username;
}
