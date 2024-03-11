package com.mt.database;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

public class UserInfo extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 主键
     */
    private String uuid;
    /**
     * 角色表(system_role)主键
     */
    private String systemRoleUuid;
    /**
     * 账号
     */
    @NotEmpty(groups = {GetUserByUsername.class})
    private String username;
    /**
     * 密码（不可逆加密）
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态，1-启用；0-禁用
     */
    private Integer status;
    /**
     * 是否重置密码，1-是；0-否
     */
    private Integer ifResetPassword;

    /**
     * 是否开始双重认证，1-是；0-否
     */
    private Integer ifDoubleAuthen;
    /**
     * 创建人主键
     */
    private String createUuid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;
    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色类型（0 应用管理员，1 系统管理员）
     */
    private Integer roleFlag;
    /**
     * 账号类型 0 系统类型 1业务用户类型
     */
    private Integer userType;

    /**
     * 是否锁住：1 是 0 否
     */
    private Integer ifLock;

    private Long machineCount;

    /**
     * 组织结构唯一标识
     */
    private String orgUuid;
    /**
     * 组织结构名称
     */
    private String orgName;

    /**
     * agent绑定的uuid
     */
    private String bindUuid;

    /**
     * 是否通过协议，1-是；0-否
     */
    private Integer isPass;

    private String systemUuid;

    private List<String> usernameList;

    private String machineUuid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMachineUuid() {
        return machineUuid;
    }

    public void setMachineUuid(String machineUuid) {
        this.machineUuid = machineUuid;
    }

    public List<String> getUsernameList() {
        return usernameList;
    }

    public void setUsernameList(List<String> usernameList) {
        this.usernameList = usernameList;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Long getMachineCount() {
        return machineCount;
    }

    public void setMachineCount(Long machineCount) {
        this.machineCount = machineCount;
    }

    public Integer getIfLock() {
        return ifLock;
    }

    public void setIfLock(Integer ifLock) {
        this.ifLock = ifLock;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSystemRoleUuid() {
        return systemRoleUuid;
    }

    public void setSystemRoleUuid(String systemRoleUuid) {
        this.systemRoleUuid = systemRoleUuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIfResetPassword() {
        return ifResetPassword;
    }

    public void setIfResetPassword(Integer ifResetPassword) {
        this.ifResetPassword = ifResetPassword;
    }

    public String getCreateUuid() {
        return createUuid;
    }

    public void setCreateUuid(String createUuid) {
        this.createUuid = createUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getRoleName() {

        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBindUuid() {
        return bindUuid;
    }

    public void setBindUuid(String bindUuid) {
        this.bindUuid = bindUuid;
    }

    public String getOrgUuid() {
        return orgUuid;
    }

    public void setOrgUuid(String orgUuid) {
        this.orgUuid = orgUuid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }

    public String getSystemUuid() {
        return systemUuid;
    }

    public void setSystemUuid(String systemUuid) {
        this.systemUuid = systemUuid;
    }


    public interface GetUserByUuid {
    }

    public interface GetUserByUsername {
    }

    public static UserInfo extendUserInfo(UserInfo userInfo){
        if(userInfo != null) {
            if (StringUtils.isNotBlank(userInfo.getRoleName()) && "应用管理员".equals(userInfo.getRoleName())) {
                userInfo.setRoleFlag(0);
            }
            else if (StringUtils.isNotBlank(userInfo.getRoleName()) && "系统管理员".equals(userInfo.getRoleName())) {
                userInfo.setRoleFlag(1);
            }
            else if ("0".equals(userInfo.getSystemUuid())) {
                userInfo.setRoleFlag(2);
            }else{
                userInfo.setRoleFlag(-1);
            }
        }
        return userInfo;
    }

    public static List<UserInfo> extendUserInfos(List<UserInfo> userInfos){
        if(!CollectionUtils.isEmpty(userInfos)){
            userInfos.forEach(userInfo -> {
                extendUserInfo(userInfo);
            });
        }
        return userInfos;
    }

    public Integer getIfDoubleAuthen() {
        return ifDoubleAuthen;
    }

    public void setIfDoubleAuthen(Integer ifDoubleAuthen) {
        this.ifDoubleAuthen = ifDoubleAuthen;
    }
}
