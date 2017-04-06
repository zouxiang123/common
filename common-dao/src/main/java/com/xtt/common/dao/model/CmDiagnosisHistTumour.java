package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_hist_tumour
 */
public class CmDiagnosisHistTumour {
    /**
     * 主键Id
     * cm_diagnosis_hist_tumour.id
     */
    private Long id;

    /**
     * 患者Id
     * cm_diagnosis_hist_tumour.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 诊断日期
     * cm_diagnosis_hist_tumour.record_date
     */
    private Date recordDate;

    /**
     * 肿瘤类型，文本输入框
     * cm_diagnosis_hist_tumour.record_type
     */
    private String recordType;

    /**
     * 租户Id
     * cm_diagnosis_hist_tumour.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * cm_diagnosis_hist_tumour.create_time
     */
    private Date createTime;

    /**
     * 创建者Id
     * cm_diagnosis_hist_tumour.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * cm_diagnosis_hist_tumour.update_time
     */
    private Date updateTime;

    /**
     * 更新者Id
     * cm_diagnosis_hist_tumour.update_user_id
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
     * 诊断日期
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 诊断日期
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 肿瘤类型，文本输入框
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * 肿瘤类型，文本输入框
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
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