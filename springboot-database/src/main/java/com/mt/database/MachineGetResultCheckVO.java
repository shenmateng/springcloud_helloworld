package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询防护开关状态，响应参数
 * @author zhouchangsong
 */
@Data
public class MachineGetResultCheckVO implements Serializable{
    private static final long serialVersionUID = 6125876933096605793L;
    /**
     * 状态；-2 未执行  -1 执行中 1 执行成功  0 执行失败  2 服务器离线  3 存在多台机器无法操作
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String resultMsg;
    /**
     * 机器名称
     */
    private String hostName;
    /**
     * ipv4地址
     */
    private String ipv4;
    /**
     * ipv6地址
     */
    private String ipv6;
    /**
     * mac地址
     */
    private String mac;
    /**
     * 开关列表详细
     */
    private List<AttackProtectItem> attackProtectList;
    /**
     * 状态
     */
    private Boolean status;

}
