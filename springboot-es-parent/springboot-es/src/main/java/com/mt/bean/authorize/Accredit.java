package com.mt.bean.authorize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accredit implements Serializable {

    private static final long serialVersionUID = 1206640831807151800L;

    /**
     *  功能ID
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
}
