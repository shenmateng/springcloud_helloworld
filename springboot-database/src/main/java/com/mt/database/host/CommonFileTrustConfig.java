package com.mt.database.host;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zg
 * @date 2024/3/19
 */
@Data
public class CommonFileTrustConfig implements Serializable {

    private static final long serialVersionUID = 42L;

    private String scope = "global";

    private Long id;

    private List<CommonFileCondition> condition;

    /**
     * 类型 新增或修改
     */
    @JSONField(serialize = false)
    private Type type;

    public enum Type {
        ADD, DELETE
    }
}
