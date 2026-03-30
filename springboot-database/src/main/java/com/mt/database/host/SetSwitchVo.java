package com.mt.database.host;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zg
 * @date 2024/3/12
 */
@Data
public class SetSwitchVo implements Serializable {
    private static final long serialVersionUID = 42L;

    @JSONField(name = "task_type")
    private int taskType = 8001;

    private Map<String,Object> data = new HashMap<>();
}
