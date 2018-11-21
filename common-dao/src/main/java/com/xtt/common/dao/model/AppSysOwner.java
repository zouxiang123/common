package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_sys_owner
 */
public class AppSysOwner {
    /**
     * app_sys_owner.id
     */
    private Long id;

    /**
     * 所属系统
     * app_sys_owner.sys_owner
     */
    private String sysOwner;

    /**
     * 所属系统名称
     * app_sys_owner.sys_owner_name
     */
    private String sysOwnerName;

    /**
     * 创建时间
     * app_sys_owner.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * app_sys_owner.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * app_sys_owner.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * app_sys_owner.update_user_id
     */
    private Long updateUserId;

    /**
     * 租户id
     * app_sys_owner.fk_tenant_id
     */
    private Integer fkTenantId;

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
     * 所属系统
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    /**
     * 所属系统名称
     */
    public String getSysOwnerName() {
        return sysOwnerName;
    }

    /**
     * 所属系统名称
     */
    public void setSysOwnerName(String sysOwnerName) {
        this.sysOwnerName = sysOwnerName;
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

    /**
     * 租户id
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户id
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }
}