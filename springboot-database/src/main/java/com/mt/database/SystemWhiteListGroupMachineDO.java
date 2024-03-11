package com.mt.database;

import lombok.Data;

import java.io.Serializable;


@Data
public class SystemWhiteListGroupMachineDO implements Serializable {
    private static final long serialVersionUID = 252671481976842166L;
    
    private Integer id;
    
    private String whiteListId;
    
    private String machineUuid;
    
    private String groupName;

}
