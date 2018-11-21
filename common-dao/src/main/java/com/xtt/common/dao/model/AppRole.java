package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_role
 */
public class AppRole {
    /**
     * app_role.id
     */
    private Long id;

    /**
     * 父id
     * app_role.parent_id
     */
    private Long parentId;

    /**
     * 角色名称
     * app_role.name
     */
    private String name;

    /**
     * 角色描述
     * app_role.description
     */
    private String description;

    /**
     * 租户id
     * app_role.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * app所属系统(P:患者  D:医生   N:护士)
     * app_role.app_sys_owner
     */
    private String appSysOwner;

    /**
     * 固定角色类型
     * app_role.constant_type
     */
    private Integer constantType;

    /**
     * 创建时间
     * app_role.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * app_role.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * app_role.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * app_role.update_user_id
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
     * 父id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 角色描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 角色描述
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * app所属系统(P:患者  D:医生   N:护士)
     */
    public String getAppSysOwner() {
        return appSysOwner;
    }

    /**
     * app所属系统(P:患者  D:医生   N:护士)
     */
    public void setAppSysOwner(String appSysOwner) {
        this.appSysOwner = appSysOwner;
    }

    /**
     * 固定角色类型
     */
    public Integer getConstantType() {
        return constantType;
    }

    /**
     * 固定角色类型
     */
    public void setConstantType(Integer constantType) {
        this.constantType = constantType;
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