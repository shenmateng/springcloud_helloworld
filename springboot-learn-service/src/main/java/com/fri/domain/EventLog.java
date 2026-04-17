package com.fri.domain;

import lombok.Data;

/**
 * @Author: chenyuyin
 * @DateTime: 2022-2-9 10:19
 * @Description:
 */
@Data
public class EventLog {
    private Integer eventId;
    private String machineUuid;
    private Subject subject;
    private Object object;
    private String operation;
    private String operationExtra;
    private Long standardTimestamp;
}
