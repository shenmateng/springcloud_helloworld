package com.mt.bean.authorize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: chenyuyin
 * @DateTime: 2021-5-10 16:33
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAccredit implements Serializable {

    private static final long serialVersionUID = 2868651562689007346L;
    /**
     * 功能ID
     */
    private String id;

    /**
     * 类型0,1,2
     */
    private Integer type;

    /**
     * 开始时间  针对于日期类型的功能 type=0
     */
    private Long periodOfValidityBegin;

    /**
     * 截至时间   针对于日期类型的功能 type=0
     */
    private Long periodOfValidityEnd;

    /**
     * 服务器数  针对与天数类型 type=1
     */
    private Integer authMachineCount;

    /**
     * 用户权限针对与天数类型 type=2
     */
    private String adminName;
    /**
     * 功能模块是否选择
     */
    private boolean include;
}
