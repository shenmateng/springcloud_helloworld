package com.mt.database;



public class MachineAndUserInfoQuery {

    private static final long serialVersionUID = 1L;

    private String uuid;


    private String machineUuid;


    public String getMachineUuid() {
        return machineUuid;
    }

    public void setMachineUuid(String machineUuid) {
        this.machineUuid = machineUuid;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
