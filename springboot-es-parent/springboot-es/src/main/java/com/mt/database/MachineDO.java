package com.mt.database;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：WangYang
 * @version ：B1.5.1
 * @program ：bmp-prm
 * @date ：Created in 2021/6/17 14:02
 * @description ：实体对象
 */

@Data

public class MachineDO implements Serializable {

    /**
     * 机器id集合
     */
    private List<String> machineList;

    /**
     * 离线机器id集合
     */
    private List<String> offMachineList;

}
