package com.mt.database;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectMachineUuidsByTagUuidTagP implements Serializable {

    private String userUuid;

    private String tagUuid;

    private Integer onlineStatus;
}
