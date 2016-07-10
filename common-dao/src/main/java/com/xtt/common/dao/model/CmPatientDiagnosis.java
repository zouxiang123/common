package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_patient_diagnosis
 */
public class CmPatientDiagnosis {
    /**
     * 物理主键
     * cm_patient_diagnosis.id
     */
    private Long id;

    /**
     * 诊断配置编号
     * cm_patient_diagnosis.fk_dianosis_conf_id
     */
    private Long fkDianosisConfId;

    /**
     * 记录id
     * cm_patient_diagnosis.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 最后操作步骤
     * cm_patient_diagnosis.last_step
     */
    private Integer lastStep;

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     * cm_patient_diagnosis.sys_owner
     */
    private String sysOwner;

    /**
     * 租户id
     * cm_patient_diagnosis.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * cm_patient_diagnosis.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * cm_patient_diagnosis.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * cm_patient_diagnosis.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * cm_patient_diagnosis.update_user_id
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
     * 诊断配置编号
     */
    public Long getFkDianosisConfId() {
        return fkDianosisConfId;
    }

    /**
     * 诊断配置编号
     */
    public void setFkDianosisConfId(Long fkDianosisConfId) {
        this.fkDianosisConfId = fkDianosisConfId;
    }

    /**
     * 记录id
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 记录id
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 最后操作步骤
     */
    public Integer getLastStep() {
        return lastStep;
    }

    /**
     * 最后操作步骤
     */
    public void setLastStep(Integer lastStep) {
        this.lastStep = lastStep;
    }

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
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