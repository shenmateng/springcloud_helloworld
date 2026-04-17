package com.mt.mapper.onnew;

import com.mt.database.ZcMachine;
import org.apache.ibatis.annotations.Param;

/**
 * zc_machine 表 Mapper（测试用）
 */
public interface ZcMachineMapper {

    int insert(ZcMachine zcMachine);

    ZcMachine queryByUuid(@Param("uuid") String uuid);
}
