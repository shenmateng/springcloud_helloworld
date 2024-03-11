package com.mt.mapper.onnew;

import com.mt.database.IpSeet;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ：makejava
* @version ：
* @program ：
* @date ：Created in 2024/03/01 11:15
* @description ： IpSeetMapper
*/
public interface IpSeetMapper {

   /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
   IpSeet queryById(Integer id);

   /**
    * 通过ID集合查询多条数据
    *
    * @param ids 主键集合
    * @return 实例对象集合
    */
   List<IpSeet> queryByIds(@Param("ids") List<Integer> ids);

   /**
    * 通过实体作为筛选条件查询
    *
    * @param ipSeet 实例对象
    * @return 对象列表
    */
   List<IpSeet> queryAll(IpSeet ipSeet);

   /**
    * 新增可选数据
    * @param ipSeet 实例对象
    * @return 影响行数
    */
   int insertSelective(IpSeet ipSeet);

   /**
    * 批量新增数据
    * @apiNote 数据库字段必须有默认值时，才可以使用
    * @apiNote 不适用于自定义主键赋值，例如：雪花算法产生的主键，UUID...
    * @param ipSeetList 实例对象集合
    * @return 影响行数
    */
   int insertAllColumnBatch(@Param("list") List<IpSeet> ipSeetList);

   /**
    * 批量新增数据
    *
    * @param ipSeetList 实例对象集合
    * @return 影响行数
    */
   int insertBatch(@Param("list") List<IpSeet> ipSeetList);

   /**
    * 修改数据
    *
    * @param ipSeet 实例对象
    * @return 影响行数
    */
   int update(IpSeet ipSeet);

   /**
    * 批量修改数据
    *
    * @param ipSeetList 实例对象集合
    * @return 影响行数
    */
   int updateBatch(@Param("list") List<IpSeet> ipSeetList);


   /**
    * 通过实体作为筛选条件查询
    *
    * @param ip 实例对象
    * @return 对象列表
    */
   List<IpSeet> queryip(Long ip);

   /**
    * 通过实体作为筛选条件查询
    *
    * @return 对象列表
    */
   List<IpSeet> queryAllIn();

}


