package com.mt.database;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 *
 * @author Volcano
 * @version 1.0
 * @date 2021/12/14
 */
public class UserInfo extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 主键
     */
    private String uuid;
    /**
     * 统一身份认证用户id
     */
    private String unifiedUuid;
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
     * 微信OpenId
     */
    private String wechatId;
    /**
     * 状态，1-启用；0-禁用
     */
    private Integer status;

    /**
     * 最后禁用的时间
     */
    private Date lastStatusErrorTime;

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
     * 创建时间1
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
     * 角色Code
     */
    private String roleCode;
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

    /**
     * 详细地址
     */
    private String address;

    /**
     * 部门
     */
    private String department;

    /**
     * 职务
     */
    private String dutuies;

    /**
     * 注册码
     */
    private String registerCode;

    /**
     * 是否绑定公众号
     */
    private Boolean isBindWx;

    /**
     * 市code
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区code
     */
    private String areaCode;

    /**
     * 区名称
     */
    private String areaName;

    /**
     * 省code
     */
    private String provinceCode;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 备注
     */
    private String remark;
    /**
     * 单位
     */
    private String unit;

    /**
     * 用户单位
     */
    private String userUnit;

    /**
     * 有效时间
     */
    private Date validTime;

    /**
     * 所属系统标识
     */
    private String systemSign;
    /**
     * 服务站名称
     */
    private String agencyName;
    /**
     * 账号设置权重
     */
    private String account;
    /**
     * 专家协助权重
     */
    private String assist;
    /**
     * 时间分析权重
     */
    private String event;
    /**
     * 安全月报权重
     */
    private String monthly;
    /**
     * 安装与配置权重
     */
    private String allocation;
    /**
     * 模板配置权重
     */
    private String template;

    private String industry;


    private String accessCode;
    private String ministry;

    private String creator;

    private Date beginTime;
    private Integer authNumber;
    private Integer useAuthNumber;
    private Integer authType;

    /**
     * 剩余天数
     */
    private Integer differentDays;

    /**
     * 逾期天数
     */
    private Integer overdueDays;
    /**
     * 系统模板使用数量
     */
    private Integer systModelUseNum = 0;
    /**
     * 堡垒锁模板使用数量
     */
    private Integer sysFortModelUseNum = 0;
    /**
     * 应用模板使用数量
     */
    private Integer appModelUseNum = 0;
    /**
     * 专项模板使用数量
     */
    private Integer zxModelUseNum = 0;
    /**
     * 总安装数
     */
    private Long allInstallCount = 0L;
    /**
     * 今日安装数
     */
    private Long todayInstallCount = 0L;

    private Integer installNumber;

    private Date lastInstallTime;


    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }

    public Date getLastStatusErrorTime() {
        return lastStatusErrorTime;
    }

    public void setLastStatusErrorTime(Date lastStatusErrorTime) {
        this.lastStatusErrorTime = lastStatusErrorTime;
    }

    public Date getLastInstallTime() {
        return lastInstallTime;
    }

    public void setLastInstallTime(Date lastInstallTime) {
        this.lastInstallTime = lastInstallTime;
    }

    public Integer getInstallNumber() {
        return installNumber;
    }

    public void setInstallNumber(Integer installNumber) {
        this.installNumber = installNumber;
    }

    public Integer getSystModelUseNum() {
        return systModelUseNum;
    }

    public void setSystModelUseNum(Integer systModelUseNum) {
        this.systModelUseNum = systModelUseNum;
    }

    public Integer getSysFortModelUseNum() {
        return sysFortModelUseNum;
    }

    public void setSysFortModelUseNum(Integer sysFortModelUseNum) {
        this.sysFortModelUseNum = sysFortModelUseNum;
    }

    public Integer getAppModelUseNum() {
        return appModelUseNum;
    }

    public void setAppModelUseNum(Integer appModelUseNum) {
        this.appModelUseNum = appModelUseNum;
    }

    public Integer getZxModelUseNum() {
        return zxModelUseNum;
    }

    public void setZxModelUseNum(Integer zxModelUseNum) {
        this.zxModelUseNum = zxModelUseNum;
    }

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    public Integer getUseAuthNumber() {
        return useAuthNumber;
    }

    public void setUseAuthNumber(Integer useAuthNumber) {
        this.useAuthNumber = useAuthNumber;
    }

    public Integer getDifferentDays() {
        return differentDays;
    }

    public void setDifferentDays(Integer differentDays) {
        this.differentDays = differentDays;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getAuthNumber() {
        return authNumber;
    }

    public void setAuthNumber(Integer authNumber) {
        this.authNumber = authNumber;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getEvent() {
        return event;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getAllocation() {
        return allocation;
    }

    public void setAllocation(String allocation) {
        this.allocation = allocation;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getAllInstallCount() {
        return allInstallCount;
    }

    public void setAllInstallCount(Long allInstallCount) {
        this.allInstallCount = allInstallCount;
    }

    public Long getTodayInstallCount() {
        return todayInstallCount;
    }

    public void setTodayInstallCount(Long todayInstallCount) {
        this.todayInstallCount = todayInstallCount;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Boolean getBindWx() {
        return isBindWx;
    }

    public void setBindWx(Boolean bindWx) {
        isBindWx = bindWx;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDutuies() {
        return dutuies;
    }

    public void setDutuies(String dutuies) {
        this.dutuies = dutuies;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
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

    public static UserInfo extendUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            if (StringUtils.isNotBlank(userInfo.getRoleName()) && "应用管理员".equals(userInfo.getRoleName())) {
                userInfo.setRoleFlag(0);
            } else if (StringUtils.isNotBlank(userInfo.getRoleName()) && "系统管理员".equals(userInfo.getRoleName())) {
                userInfo.setRoleFlag(1);
            } else if ("0".equals(userInfo.getSystemUuid())) {
                userInfo.setRoleFlag(2);
            } else {
                userInfo.setRoleFlag(-1);
            }
        }
        return userInfo;
    }

    public static List<UserInfo> extendUserInfos(List<UserInfo> userInfos) {
        if (!CollectionUtils.isEmpty(userInfos)) {
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

    public String getSystemSign() {
        return systemSign;
    }

    public void setSystemSign(String systemSign) {
        this.systemSign = systemSign;
    }

    public String getUnifiedUuid() {
        return unifiedUuid;
    }

    public void setUnifiedUuid(String unifiedUuid) {
        this.unifiedUuid = unifiedUuid;
    }
}
