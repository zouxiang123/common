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
     * 记录id
     * cm_patient_diagnosis.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 当前步骤
     * cm_patient_diagnosis.step
     */
    private String step;

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
     * 当前步骤
     */
    public String getStep() {
        return step;
    }

    /**
     * 当前步骤
     */
    public void setStep(String step) {
        this.step = step;
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