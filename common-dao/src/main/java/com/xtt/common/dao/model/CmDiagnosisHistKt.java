package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_hist_kt
 */
public class CmDiagnosisHistKt {
    /**
     * 主键Id cm_diagnosis_hist_kt.id
     */
    private Long id;

    /**
     * 患者Id cm_diagnosis_hist_kt.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 开始时间 cm_diagnosis_hist_kt.start_date
     */
    private Date startDate;

    /**
     * 结束时间 cm_diagnosis_hist_kt.end_date
     */
    private Date endDate;

    /**
     * 结束原因，对应字典表item_code值 cm_diagnosis_hist_kt.end_reason
     */
    private String endReason;

    /**
     * 其他结束原因 cm_diagnosis_hist_kt.other_end_reason
     */
    private String otherEndReason;

    /**
     * 租户Id cm_diagnosis_hist_kt.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_diagnosis_hist_kt.create_time
     */
    private Date createTime;

    /**
     * 创建者Id cm_diagnosis_hist_kt.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_diagnosis_hist_kt.update_time
     */
    private Date updateTime;

    /**
     * 更新者Id cm_diagnosis_hist_kt.update_user_id
     */
    private Long updateUserId;

    /**
     * 备注 cm_diagnosis_hist_kt.remark
     */
    private String remark;

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
     * 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 结束原因，对应字典表item_code值
     */
    public String getEndReason() {
        return endReason;
    }

    /**
     * 结束原因，对应字典表item_code值
     */
    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }

    /**
     * 其他结束原因
     */
    public String getOtherEndReason() {
        return otherEndReason;
    }

    /**
     * 其他结束原因
     */
    public void setOtherEndReason(String otherEndReason) {
        this.otherEndReason = otherEndReason;
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

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}