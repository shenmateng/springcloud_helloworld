package com.mt.database;

import com.mt.bean.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors
@Data
public class ZcTagUser extends BaseDomain {

    private static final long serialVersionUID = 1L;
    
    private String uuid;
    /**
     * 标签名
     */
    private String tagName;
    /**
     * 用户uuid
     */
    private String userUuid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


}  
