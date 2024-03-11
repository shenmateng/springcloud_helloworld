package com.mt.database;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class AccessWhiteIpsVO {

    /**
     * ip/ip段
     */
    @NotEmpty
    @Size(max=255)
    private String ip;

    /**
     * 类型：0，不限制登陆时间段；1，指定登陆时间段
     */
    @Min(value = 0)
    @Max(value = 1)
    @NotNull
    private Integer type;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;



}
