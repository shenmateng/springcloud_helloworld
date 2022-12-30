package com.mt.database;

import lombok.Data;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * @author liuxi
 * @data 2020/8/24
 */
@Data
public class AssetQueryForEs extends PageQuery {

    /**
     * 关键字字段
     */
    private List<String> keys;
    /**
     * 过滤条件
     */
    private Map<String, Object> filters;
    /**
     * 需要范围查询的信息
     */
    private List<QueryBuilder> filterQueryBuilders;
    /**
     * 统计需要返回的字段
     */
    private List<String> fields;

    /**
     * 需要统计的字段
     */
    private String countField;

    /**
     * 是否需要除统计外的其他字段
     */
    private Boolean ifCountOtherField = false;

    /**
     * 分组机器
     */
    private String machineTags;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 升降序
     */
    private SortOrder sortOrder = SortOrder.DESC;

    private String orgUuid;

    /**
     * 0-不使用，1-使用
     */
    private Integer orgQueryFlag = 1;

    /**
     * 删除状态；这个可能用不到，机器一旦被删除，userData信息就会被清空，后台登录用户在查询的时候都是携带用户ID的，所以这个字段只会在不传用户ID的情况下用到
     * 0 未删除、1 已删除
     */
    private Integer ifDelete;
}
