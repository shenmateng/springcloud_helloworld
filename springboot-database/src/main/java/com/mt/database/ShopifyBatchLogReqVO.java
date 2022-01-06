package com.mt.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ：mateng
 * @version ：1.3.8
 * @description ：批量刊登log记录VO
 * @program ：bmp
 * @date ：Created in 2021/4/19 11:30
 * @since ：1.3.8
 */
@Data
public class ShopifyBatchLogReqVO {
    /**
     * 站点
     */
    private String siteCode;

    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    /**
     * 平台
     */
    @NotBlank( message= "平台不能为空")
    private String platform;

    /**
     * 每页多少
     */
    @Min(value = 1, message = "当前页不能小于1")
    @NotNull(message = "当前页不为空")
    private Integer pageSize;

    /**
     * 当前页面
     */
    @Min(value = 1, message = "页面大小不能小于1")
    @NotNull(message = "页面大小不能为空")
    private Integer currentPage;

}
