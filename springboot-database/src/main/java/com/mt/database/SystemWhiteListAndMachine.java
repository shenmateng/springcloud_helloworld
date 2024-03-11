package com.mt.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemWhiteListAndMachine {

    private Integer id;
    private String whiteListId;
    private String machineUuid;
}
