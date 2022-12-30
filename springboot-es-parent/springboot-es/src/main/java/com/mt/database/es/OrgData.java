package com.mt.database.es;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrgData implements Serializable {

    private String orgUuid;

    private String orgName;

    private String parentOrgUuid;
    /**
     * 机构数量
     */
    private Integer orgLevel;

    private String districtCode;

    private String districtName;
}
