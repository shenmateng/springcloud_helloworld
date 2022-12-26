package com.mt.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ：WangYang
 * @version ：B1.5.1
 * @program ：bmp-prm
 * @date ：Created in 2021/6/17 14:02
 * @description ：shopify 商品数据
 */

@Data
@EqualsAndHashCode
public class MachineDetailVO {

    /**
     * 机器台数
     */
    private Integer machineCount;
    /**
     * 所欲机器id
     */
    private List<String> machineList;

    /**
     * 离线所欲机器id
     */
    private List<String> offMachineList;


}
