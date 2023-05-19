package com.mt.database;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class EventWhitelist{

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @NotEmpty(groups = {UpdateEventWhitelist.class})
    @Size(max = 32, groups = {UpdateEventWhitelist.class})
    private String uuid;
    /**
     * 用户uuid
     */
    private String userUuid;
    /**
     * 事件uuid
     */
    @NotEmpty(groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
    @Size(max = 100, groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
    private String eventUuid;
    /**
     * 加白参数
     */
    @NotBlank(groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
    private String param;
    /**
     * 加白参数描述
     */
    @NotBlank(groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
    private String paramDesc;
    /**
     * 服务器数
     */
    private Integer machineCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updatTime;

    private String keyWord;
    /**
     * @Author liuYun
     * @Description //事件名称 2020/3/28
     * @Date 15:37 2020/3/28
     * @Param
     * @return
     **/
    @NotBlank(groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
    @Size(max = 100, groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
    private String typeName;


    /**
     * 事件日志类型
     */
//    @NotEmpty(groups = {EventWhitelist.AddEventWhitelist.class, EventWhitelist.UpdateEventWhitelist.class})
//    @Size(max = 100, groups = {EventWhitelist.AddEventWhitelist.class, EventWhitelist.UpdateEventWhitelist.class})
    private String operation;
    /**
     * 事件日志类型描述
     */
//    @NotEmpty(groups = {EventWhitelist.AddEventWhitelist.class, EventWhitelist.UpdateEventWhitelist.class})
//    @Size(max = 100, groups = {EventWhitelist.AddEventWhitelist.class, EventWhitelist.UpdateEventWhitelist.class})
    private String operationDesc;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

//    @NotNull(groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
//    @Size(min = 1, groups = {AddEventWhitelist.class, UpdateEventWhitelist.class})
    private Set<String> machineUuids;

    private Integer isAll;

    public Set<String> getMachineUuids() {
        return machineUuids;
    }

    public void setMachineUuids(Set<String> machineUuids) {
        this.machineUuids = machineUuids;
    }

    public String getUuid() {
        return uuid;  
    }  
  
    public void setUuid(String uuid) {  
        this.uuid = uuid;  
    }  

    public String getUserUuid() {
        return userUuid;  
    }  
  
    public void setUserUuid(String userUuid) {  
        this.userUuid = userUuid;  
    }  

    public String getEventUuid() {
        return eventUuid;  
    }  
  
    public void setEventUuid(String eventUuid) {  
        this.eventUuid = eventUuid;  
    }  

    public String getParam() {
        return param;  
    }  
  
    public void setParam(String param) {  
        this.param = param;  
    }  

    public String getParamDesc() {
        return paramDesc;  
    }  
  
    public void setParamDesc(String paramDesc) {  
        this.paramDesc = paramDesc;  
    }  

    public Integer getMachineCount() {
        return machineCount;  
    }  
  
    public void setMachineCount(Integer machineCount) {  
        this.machineCount = machineCount;  
    }  

    public Date getCreateTime() {
        return createTime;  
    }  
  
    public void setCreateTime(Date createTime) {  
        this.createTime = createTime;  
    }  

    public Date getUpdatTime() {
        return updatTime;  
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public void setUpdatTime(Date updatTime) {
        this.updatTime = updatTime;  
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getIsAll() {
        return isAll;
    }

    public void setIsAll(Integer isAll) {
        this.isAll = isAll;
    }

    public interface AddEventWhitelist {
    }

    public interface UpdateEventWhitelist {
    }

    public interface ListEventWhitelists {
    }

}
