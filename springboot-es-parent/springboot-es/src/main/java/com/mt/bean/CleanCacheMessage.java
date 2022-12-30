package com.mt.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class CleanCacheMessage implements Serializable {

    private String method;

    private Object parameter;

    private String userUuid;
}
