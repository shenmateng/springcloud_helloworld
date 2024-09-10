package com.mt.database;


import com.mt.database.es.OrgData;
import com.mt.database.es.UserData;
import lombok.Data;

import java.util.List;

@Data
public class Machine  {

    private static final long serialVersionUID = 1L;

    private String uuid;
    /**
     * 服务器名称
     */
    private String machineName;
    /**
     * 外网IP
     */
    private String extranetIp;
    /**
     * 内网ip
     */
    private String intranetIp;
    /**
     * 0-离线状态，1-在线状态
     */
    private Integer onlineStatus;
    /**
     * 0 Win、1 Linux
     */
    private Integer osType;

    private String operatingSystem;

    /**
     * 托管状态 1 已托管、0 未托管
     */
    private Integer trusteeshipStatus;
    /**
     * 机器绑定的用户ID
     */
    private String userUuid;

}
