package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_patient_role
 */
public class AppPatientRole {
    /**
     * 主键id
     * app_patient_role.id
     */
    private Long id;

    /**
     * 患者id
     * app_patient_role.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 角色id
     * app_patient_role.fk_role_id
     */
    private Long fkRoleId;

    /**
     * 创建时间
     * app_patient_role.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * app_patient_role.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * app_patient_role.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * app_patient_role.update_user_id
     */
    private Long updateUserId;

    /**
     * 租户id
     * app_patient_role.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 患者id
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 患者id
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 角色id
     */
    public Long getFkRoleId() {
        return fkRoleId;
    }

    /**
     * 角色id
     */
    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
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