package com.mt.database;

import java.util.List;

public class ZcMachineTagName{


    private String tagName;

    private List<ZcMachineTagName1> macUuids;

    public List<ZcMachineTagName1> getMacUuids() {
        return macUuids;
    }

    public void setMacUuids(List<ZcMachineTagName1> macUuids) {
        this.macUuids = macUuids;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
