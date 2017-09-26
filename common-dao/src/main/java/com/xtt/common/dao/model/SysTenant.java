package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_tenant
 */
public class SysTenant {
    /**
     * sys_tenant.id
     */
    private Integer id;

    /**
     * 透析中心名称
     * sys_tenant.name
     */
    private String name;

    /**
     * 是否可用
     * sys_tenant.is_enable
     */
    private Boolean isEnable;

    /**
     * 有效开始日期
     * sys_tenant.start_date
     */
    private Date startDate;

    /**
     * 有效终止日期
     * sys_tenant.end_date
     */
    private Date endDate;

    /**
     * sys_tenant.is_default
     */
    private Boolean isDefault;

    /**
     * 集团虚拟租户标识（1：集团虚拟租户，0：普通租户）
     * sys_tenant.group_flag
     */
    private Boolean groupFlag;

    /**
     * sys_tenant.license
     */
    private String license;

    /**
     * 创建时间
     * sys_tenant.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_tenant.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sys_tenant.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sys_tenant.update_user_id
     */
    private Long updateUserId;

    /**
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 透析中心名称
     */
    public String getName() {
        return name;
    }

    /**
     * 透析中心名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 是否可用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否可用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 有效开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 有效开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 有效终止日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 有效终止日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 集团虚拟租户标识（1：集团虚拟租户，0：普通租户）
     */
    public Boolean getGroupFlag() {
        return groupFlag;
    }

    /**
     * 集团虚拟租户标识（1：集团虚拟租户，0：普通租户）
     */
    public void setGroupFlag(Boolean groupFlag) {
        this.groupFlag = groupFlag;
    }

    /**
     */
    public String getLicense() {
        return license;
    }

    /**
     */
    public void setLicense(String license) {
        this.license = license;
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