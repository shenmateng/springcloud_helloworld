package com.mt.database.host;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zg
 * @date 2024/3/19
 */
@Data
public class CommonFileCondition implements Serializable {

    private static final long serialVersionUID = 42L;

    private String field;

    private String matchType;

    private String value;


}
