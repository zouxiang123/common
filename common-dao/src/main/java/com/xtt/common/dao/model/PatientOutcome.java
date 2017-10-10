package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_outcome
 */
public class PatientOutcome {
    /**
     * 物理主键 patient_outcome.id
     */
    private Long id;

    /**
     * 患者id patient_outcome.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 转归类型 patient_outcome.type
     */
    private String type;

    /**
     * 转归日期 patient_outcome.record_date
     */
    private Date recordDate;

    /**
     * 转归原因 patient_outcome.reason
     */
    private String reason;

    /**
     * 所属系统 patient_outcome.sys_owner
     */
    private String sysOwner;

    /**
     * 租户id patient_outcome.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 patient_outcome.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_outcome.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_outcome.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_outcome.update_user_id
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
     * 转归类型
     */
    public String getType() {
        return type;
    }

    /**
     * 转归类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 转归日期
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 转归日期
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 转归原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 转归原因
     */
    public void setReason(String reason) {
        this.reason = reason;
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