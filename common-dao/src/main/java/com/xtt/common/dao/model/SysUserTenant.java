package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_user_tenant
 */
public class SysUserTenant {
    /**
     * sys_user_tenant.id
     */
    private Long id;

    /**
     * 用户id
     * sys_user_tenant.fk_user_id
     */
    private Long fkUserId;

    /**
     * 租户id
     * sys_user_tenant.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 角色类别（医生，护士，管理员等）
     * sys_user_tenant.role_type
     */
    private String roleType;

    /**
     * 职位
     * sys_user_tenant.position
     */
    private String position;

    /**
     * 座机号
     * sys_user_tenant.telephone
     */
    private String telephone;

    /**
     * 皮肤
     * sys_user_tenant.skin
     */
    private String skin;

    /**
     * 所属系统
     * sys_user_tenant.sys_owner
     */
    private String sysOwner;

    /**
     * 创建时间
     * sys_user_tenant.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_user_tenant.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sys_user_tenant.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sys_user_tenant.update_user_id
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
     * 用户id
     */
    public Long getFkUserId() {
        return fkUserId;
    }

    /**
     * 用户id
     */
    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
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
     * 角色类别（医生，护士，管理员等）
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * 角色类别（医生，护士，管理员等）
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 职位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 座机号
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 座机号
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 皮肤
     */
    public String getSkin() {
        return skin;
    }

    /**
     * 皮肤
     */
    public void setSkin(String skin) {
        this.skin = skin;
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