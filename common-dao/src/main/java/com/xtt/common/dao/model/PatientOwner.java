package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_owner
 */
public class PatientOwner {
    /**
     * 物理主键 patient_owner.id
     */
    private Long id;

    /**
     * 患者id patient_owner.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 所属系统 patient_owner.sys_owner
     */
    private String sysOwner;

    /**
     * 租户id patient_owner.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 是否可用 patient_owner.is_enable
     */
    private Boolean isEnable;

    /**
     * 创建时间 patient_owner.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_owner.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_owner.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_owner.update_user_id
     */
    private Long updateUserId;

    /**
     * 物理主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 物理主键
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