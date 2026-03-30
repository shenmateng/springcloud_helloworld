package com.mt.service;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WeakpasswordResult implements Serializable {

    private String taskUuid;

    private List<WeakpasswordItem> items;

    public String getTaskUuid() {
        return taskUuid;
    }

    @JSONField(name = "task_id")
    public void setTaskUuid(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    public List<WeakpasswordItem> getItems() {
        return items;
    }

    @JSONField(name = "items")
    public void setItems(List<WeakpasswordItem> items) {
        this.items = items;
    }
}
