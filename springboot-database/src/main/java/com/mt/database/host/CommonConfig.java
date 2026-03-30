package com.mt.database.host;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zg
 * @date 2024/3/19
 */
@Data
public class CommonConfig implements Serializable {

    private static final long serialVersionUID = 42L;

    private Long id;

    @JSONField(serialize = false)
    private Long modelId;

    @JSONField(name = "path")
    private String content;

    private String filePath;

    /**
     * 类型 新增或修改
     */
    @JSONField(serialize = false)
    private Type type;

    public enum Type {
        ADD, DELETE
    }
}
