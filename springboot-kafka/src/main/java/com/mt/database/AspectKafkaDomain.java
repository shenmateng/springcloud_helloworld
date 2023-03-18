package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mateng
 * @version 1.0.0
 * @description AspectKafkaDomain
 * @since 2022/2/22 17:17
 */
@Data
public class AspectKafkaDomain implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * kafka主题
     * userTopical          用户信息
     * operationalTopical   运维信息
     * workOrderTopical     工单情况
     * systemTopical        系统信息
     *
     * @return
     */
    private String topical;

    private String modelUuid;
    /**
     * 通道标识
     */
    private String token;
    /**
     * 模块类型
     * User-用户信息
     * Operational-运维信息
     * Work-工单情况
     * System-系统信息
     *
     * @return
     */
    private String module;
    /**
     * 功能操作
     * User-用户信息        1xxx
     * Operational-运维信息 2xxx
     * Work-工单情况        3xxx
     * System-系统信息      4xxx
     *
     * @return
     */
    private Integer operate;
    /**
     * 操作数据
     */
    private Object data;
    /**
     * 关键词备用
     */
    private String keyword;
    /**
     * 中心标识
     */
    private String systemSign;
    /**
     * 是否全部发送  0-否   1-是
     */
    private Integer ifAll = 0;

    private String authUuid;

    private List<String> userUuids;

    public AspectKafkaDomain() {
    }

    public AspectKafkaDomain(String topical, String token, String module, Integer operate, Object data) {
        this.topical = topical;
        this.token = token;
        this.module = module;
        this.operate = operate;
        this.data = data;
    }
}
