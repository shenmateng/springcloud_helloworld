package com.mt.database.es;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  abstract class EsZcBase implements Serializable {

    public abstract String id();

    public abstract String index();

    public abstract String type();

    public abstract String alias();

    public abstract String mapping();

    public static String sAlias(){
        return "zc";
    }

    public static String sType(){
        return "_doc";
    }

    public abstract List<String> queryFields();

    public abstract List<String> queryKeys();

    public static List<String> listCommonFileds(){
        List<String> commonFileds = new ArrayList<>();
        commonFileds.add("machineName");
        commonFileds.add("onlineStatus");
        commonFileds.add("operationSystem");
        commonFileds.add("ipv4");
        commonFileds.add("ipv6");
        commonFileds.add("machineTag");
        commonFileds.add("remark");
        commonFileds.add("host");
        return commonFileds;
    }

    public static List<String> listFilterFileds(){
        List<String> filterFileds = new ArrayList<>();
        return filterFileds;
    }

    public static List<String> listFilterShowFileds(){
        List<String> filterShowFileds = new ArrayList<>();
        filterShowFileds.add("id");
        filterShowFileds.add("zcType");
        filterShowFileds.add("ifDelete");
        filterShowFileds.add("newFlag");
        filterShowFileds.add("machineUuid");
        filterShowFileds.add("machineIp");
        filterShowFileds.add("osType");
        filterShowFileds.add("machineStatus");
        filterShowFileds.add("userDatas");
        filterShowFileds.add("machineIps");
        filterShowFileds.add("riskCount");
        filterShowFileds.add("riskName");
        filterShowFileds.add("riskType");
        filterShowFileds.add("riskDealFlag");
        filterShowFileds.add("zcNetworkCards");
        filterShowFileds.add("zcDisks");
        filterShowFileds.add("customIp");
        filterShowFileds.add("zcProcessModules");
        filterShowFileds.add("offlineDayCount");
        return filterShowFileds;
    }

    public static List<String> listFilterExportFileds(){
        List<String> filterExportFileds = new ArrayList<>();
        filterExportFileds.add("id");
        filterExportFileds.add("zcType");
        filterExportFileds.add("ifDelete");
        filterExportFileds.add("newFlag");
        filterExportFileds.add("machineIp");
        filterExportFileds.add("osType");
        filterExportFileds.add("machineStatus");
        filterExportFileds.add("userDatas");
        filterExportFileds.add("machineIps");
        filterExportFileds.add("riskCount");
        filterExportFileds.add("riskName");
        filterExportFileds.add("riskType");
        filterExportFileds.add("riskDealFlag");
        filterExportFileds.add("zcNetworkCards");
        filterExportFileds.add("zcDisks");
        filterExportFileds.add("customIp");
        filterExportFileds.add("zcProcessModules");
        filterExportFileds.add("offlineDayCount");
        return filterExportFileds;
    }

}
