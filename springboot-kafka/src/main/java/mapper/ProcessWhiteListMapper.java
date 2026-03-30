
import feign.Param;

import java.util.List;

 /**
 * @author ：makejava
 * @version ：
 * @program ：
 * @date ：Created in 2025/10/11 12:36
 * @description ：进程白名单表 ProcessWhiteListMapper
 */ 
public interface ProcessWhiteListMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProcessWhiteListDO queryById(Long id);
    
    /**
     * 通过ID集合查询多条数据
     *
     * @param ids 主键集合
     * @return 实例对象集合
     */
    List<ProcessWhiteListDO> queryByIds(@Param("ids") List<Long> ids);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param processWhiteList 实例对象
     * @return 对象列表
     */
    List<ProcessWhiteListDO> queryAll(ProcessWhiteListDO processWhiteList);

    /**
     * 新增可选数据
     * @param processWhiteList 实例对象
     * @return 影响行数
     */
    int insertSelective(ProcessWhiteListDO processWhiteList);
    
    /**
     * 批量新增数据
     * @apiNote 数据库字段必须有默认值时，才可以使用
     * @apiNote 不适用于自定义主键赋值，例如：雪花算法产生的主键，UUID...
     * @param processWhiteListList 实例对象集合
     * @return 影响行数
     */    
    int insertAllColumnBatch(@Param("list") List<ProcessWhiteListDO> processWhiteListList);
    
    /**
     * 批量新增数据
     *
     * @param processWhiteListList 实例对象集合
     * @return 影响行数
     */
    int insertBatch(@Param("list") List<ProcessWhiteListDO> processWhiteListList);

    /**
     * 修改数据
     *
     * @param processWhiteList 实例对象
     * @return 影响行数
     */
    int update(ProcessWhiteListDO processWhiteList);
    
    /**
     * 批量修改数据
     *
     * @param processWhiteListList 实例对象集合
     * @return 影响行数
     */
    int updateBatch(@Param("list") List<ProcessWhiteListDO> processWhiteListList);
   


    
    
        
}


