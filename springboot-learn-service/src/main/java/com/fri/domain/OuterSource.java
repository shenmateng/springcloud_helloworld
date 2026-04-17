package com.fri.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-24 14:36
 * @Description:
 */
@Data
public class OuterSource {
    private List<String> machineUuids;
    private int groupByLevel;
    private int groupBySize;
    private List<SystemFortLockLearnResult> systemFortLockLearn;
}
