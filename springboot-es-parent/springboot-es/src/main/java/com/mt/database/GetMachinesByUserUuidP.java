package com.mt.database;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class GetMachinesByUserUuidP {

    @NotBlank
    private String userUuid;

    private String tagUuid;

    private String driverVersion;

}
