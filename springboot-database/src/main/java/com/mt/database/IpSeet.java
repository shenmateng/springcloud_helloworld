package com.mt.database;

import lombok.Data;

import java.io.Serializable;

@Data
public class IpSeet implements Serializable {
    private static final long serialVersionUID = 361482991950440451L;
    
    private Integer id;
    
    private String minip;
    
    private String maxip;
    
    private String position;

}
