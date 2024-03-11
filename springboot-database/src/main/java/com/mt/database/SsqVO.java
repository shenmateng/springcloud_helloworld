package com.mt.database;

import lombok.Data;

import java.io.Serializable;

@Data
public class SsqVO implements Serializable {
    private static final long serialVersionUID = 361482991950440451L;
    
    private String p;
    
    private String c;
    
    private String d;

}
