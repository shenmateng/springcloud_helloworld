package com.mt.database;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ZcTagUserIp extends BaseDomain {


    private static final long serialVersionUID = 1L;

    private String uuid;
    /**
     * 标签名
     */
    private String tagName;

    /**
     * 标签的UUid
     */
    private String tagUuid;
    /**
     * 用户uuid
     */
    private String userUuid;

    /**
     * ip段的开始位置
     */
    private String ipRange;

    /**
     * ip段的开始位置的索引
     */
    private Long ipStartIndex;

    /**
     *  ip段的结束位置索引
     */
    private Long ipEndIndex;

    /**
     * 创建时间
     */
    private Date createTime;

}
