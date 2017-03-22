package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_user
 */
public class SysUser {
    /**
     * sys_user.id
     */
    private Long id;

    /**
     * 帐号
     * sys_user.account
     */
    private String account;

    /**
     * 姓名
     * sys_user.name
     */
    private String name;

    /**
     * 姓名首字母
     * sys_user.initial
     */
    private String initial;

    /**
     * 密码
     * sys_user.password
     */
    private String password;

    /**
     * image_path
     * sys_user.image_path
     */
    private String imagePath;

    /**
     * 性别
     * sys_user.sex
     */
    private String sex;

    /**
     * 出生日期
     * sys_user.birthday
     */
    private Date birthday;

    /**
     * 手机号
     * sys_user.mobile
     */
    private String mobile;

    /**
     * 删除标记
     * sys_user.del_flag
     */
    private Boolean delFlag;

    /**
     * 用户类型（管理员、超级管理员、普通用户）等
     * sys_user.user_type
     */
    private String userType;

    /**
     * 租户ID
     * sys_user.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * sys_user.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_user.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sys_user.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sys_user.update_user_id
     */
    private Long updateUserId;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 姓名首字母
     */
    public String getInitial() {
        return initial;
    }

    /**
     * 姓名首字母
     */
    public void setInitial(String initial) {
        this.initial = initial;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * image_path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * image_path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 删除标记
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 用户类型（管理员、超级管理员、普通用户）等
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 用户类型（管理员、超级管理员、普通用户）等
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 租户ID
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户ID
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}