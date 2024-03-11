package com.mt.mapper.onnew;

import com.mt.database.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ：makejava
* @version ：
* @program ：
* @date ：Created in 2023/08/23 15:29
* @description ： SystemWhiteListGroupMachineMapper
*/
public interface SystemWhiteListGroupMachineMapper {

   /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
   SystemWhiteListGroupMachineDO queryById(Integer id);

   /**
    * 通过ID集合查询多条数据
    *
    * @param ids 主键集合
    * @return 实例对象集合
    */
   List<SystemWhiteListGroupMachineDO> queryByIds(@Param("ids") List<Integer> ids);

   /**
    * 通过实体作为筛选条件查询
    *
    * @param systemWhiteListGroupMachine 实例对象
    * @return 对象列表
    */
   List<SystemWhiteListGroupMachineDO> queryAll(SystemWhiteListGroupMachineDO systemWhiteListGroupMachine);

   /**
    * 新增可选数据
    * @param systemWhiteListGroupMachine 实例对象
    * @return 影响行数
    */
   int insertSelective(SystemWhiteListGroupMachineDO systemWhiteListGroupMachine);

   /**
    * 批量新增数据
    * @apiNote 数据库字段必须有默认值时，才可以使用
    * @apiNote 不适用于自定义主键赋值，例如：雪花算法产生的主键，UUID...
    * @param systemWhiteListGroupMachineList 实例对象集合
    * @return 影响行数
    */
   int insertAllColumnBatch(@Param("list") List<SystemWhiteListGroupMachineDO> systemWhiteListGroupMachineList);

   /**
    * 批量新增数据
    *
    * @param systemWhiteListGroupMachineList 实例对象集合
    * @return 影响行数
    */
   int insertBatch(@Param("list") List<SystemWhiteListGroupMachineDO> systemWhiteListGroupMachineList);

   /**
    * 修改数据
    *
    * @param systemWhiteListGroupMachine 实例对象
    * @return 影响行数
    */
   int update(SystemWhiteListGroupMachineDO systemWhiteListGroupMachine);

   /**
    * 批量修改数据
    *
    * @param systemWhiteListGroupMachineList 实例对象集合
    * @return 影响行数
    */
   int updateBatch(@Param("list") List<SystemWhiteListGroupMachineDO> systemWhiteListGroupMachineList);

   /**
    * 通过主键删除数据，推荐使用逻辑删除
    *
    * @param id 主键
    * @return 影响行数
    */

   int deleteById(Integer id);

   /**
    * 通过主键集合删除数据，推荐使用逻辑删除
    *
    * @param ids 主键集合
    * @return 影响行数
    */

   int deleteByIds(@Param("ids") List<Integer> ids);


   List<SystemWhiteListGroupMachineDO> listByAllIds(@Param("whiteListId") List<String> whiteListId);

   public void insertBatch1(@Param("ZcTagFixUserIps") List<ZcTagFixUserIp> ZcTagFixUserIps);

   public void insertBatch2(@Param("ZcTagUserIps") List<ZcTagUserIp> ZcTagUserIps);

   public Long deleteByMachineUuidAndUserUuidList(@Param("deleteZcMachineBasic") List<DeleteZcMachineBasicVO> deleteZcMachineBasic);

   List<UserInfo> getUsersByUserUuidLists(@Param("userUuidLists") List<String> userUuid);

   public List<ZcMachineTagDO> selectMachineTagsList(@Param(value = "userInfos") List<MachineAndUserInfoQuery> userInfos);

    /**
     * 通过ID查询单条数据
     *
     * @param eventWhiteId 主键
     * @return 实例对象
     */
    EventWhitelistAutoAdd queryByEventWhiteId(String eventWhiteId);
}


