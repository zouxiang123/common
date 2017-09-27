package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_hist
 */
public class CmDiagnosisHist {
    /**
     * 主键Id cm_diagnosis_hist.id
     */
    private Long id;

    /**
     * 患者Id cm_diagnosis_hist.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 首次透析日期 cm_diagnosis_hist.first_treatment_date
     */
    private Date firstTreatmentDate;

    /**
     * 首次透析类型 cm_diagnosis_hist.first_treatment_type
     */
    private String firstTreatmentType;

    /**
     * 租户Id cm_diagnosis_hist.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_diagnosis_hist.create_time
     */
    private Date createTime;

    /**
     * 创建者Id cm_diagnosis_hist.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_diagnosis_hist.update_time
     */
    private Date updateTime;

    /**
     * 更新者Id cm_diagnosis_hist.update_user_id
     */
    private Long updateUserId;

    /**
     * 主键Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 患者Id
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 患者Id
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 首次透析日期
     */
    public Date getFirstTreatmentDate() {
        return firstTreatmentDate;
    }

    /**
     * 首次透析日期
     */
    public void setFirstTreatmentDate(Date firstTreatmentDate) {
        this.firstTreatmentDate = firstTreatmentDate;
    }

    /**
     * 首次透析类型
     */
    public String getFirstTreatmentType() {
        return firstTreatmentType;
    }

    /**
     * 首次透析类型
     */
    public void setFirstTreatmentType(String firstTreatmentType) {
        this.firstTreatmentType = firstTreatmentType;
    }

    /**
     * 租户Id
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户Id
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
     * 创建者Id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建者Id
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
     * 更新者Id
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新者Id
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}