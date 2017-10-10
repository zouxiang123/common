package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_outcome
 */
public class PatientOutcome {
    /**
     * patient_outcome.id
     */
    private Long id;

    /**
     * 患者id
     * patient_outcome.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 类型:(1:腹透,2:移植,3:死亡)	
     * patient_outcome.type
     */
    private String type;

    /**
     * 类型原因
     * patient_outcome.type_reason
     */
    private String typeReason;

    /**
     * 转归日期
     * patient_outcome.record_date
     */
    private Date recordDate;

    /**
     * 原因
     * patient_outcome.reason
     */
    private String reason;

    /**
     * 所属系统
     * patient_outcome.sys_owner
     */
    private String sysOwner;

    /**
     * 转出到的系统
     * patient_outcome.to_sys_owner
     */
    private String toSysOwner;

    /**
     * 租户id
     * patient_outcome.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 转出医院
     * patient_outcome.to_tenant_id
     */
    private Integer toTenantId;

    /**
     * 转出医院名称
     * patient_outcome.to_tenant_name
     */
    private String toTenantName;

    /**
     * patient_outcome.create_time
     */
    private Date createTime;

    /**
     * patient_outcome.create_user_id
     */
    private Long createUserId;

    /**
     * patient_outcome.update_time
     */
    private Date updateTime;

    /**
     * patient_outcome.update_user_id
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
     * 类型:(1:腹透,2:移植,3:死亡)	
     */
    public String getType() {
        return type;
    }

    /**
     * 类型:(1:腹透,2:移植,3:死亡)	
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 类型原因
     */
    public String getTypeReason() {
        return typeReason;
    }

    /**
     * 类型原因
     */
    public void setTypeReason(String typeReason) {
        this.typeReason = typeReason;
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
     * 原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 原因
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
     * 转出到的系统
     */
    public String getToSysOwner() {
        return toSysOwner;
    }

    /**
     * 转出到的系统
     */
    public void setToSysOwner(String toSysOwner) {
        this.toSysOwner = toSysOwner;
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
     * 转出医院
     */
    public Integer getToTenantId() {
        return toTenantId;
    }

    /**
     * 转出医院
     */
    public void setToTenantId(Integer toTenantId) {
        this.toTenantId = toTenantId;
    }

    /**
     * 转出医院名称
     */
    public String getToTenantName() {
        return toTenantName;
    }

    /**
     * 转出医院名称
     */
    public void setToTenantName(String toTenantName) {
        this.toTenantName = toTenantName;
    }

    /**
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}