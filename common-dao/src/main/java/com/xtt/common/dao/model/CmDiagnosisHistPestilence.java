package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_hist_pestilence
 */
public class CmDiagnosisHistPestilence {
    /**
     * 主键Id cm_diagnosis_hist_pestilence.id
     */
    private Long id;

    /**
     * 患者Id cm_diagnosis_hist_pestilence.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 诊断日期 cm_diagnosis_hist_pestilence.diagnostic_date
     */
    private Date diagnosticDate;

    /**
     * 诊断名称集合，以“，”号隔开；对应字典item_code值 cm_diagnosis_hist_pestilence.diagnostic_name
     */
    private String diagnosticName;

    /**
     * 其他诊断名称 cm_diagnosis_hist_pestilence.other_diagnostic_name
     */
    private String otherDiagnosticName;

    /**
     * 活动状态，对应字典item_code值 cm_diagnosis_hist_pestilence.activity_state
     */
    private String activityState;

    /**
     * 治疗情况，对应字典item_code值 cm_diagnosis_hist_pestilence.treatment
     */
    private String treatment;

    /**
     * 其他治疗情况 cm_diagnosis_hist_pestilence.other_treatment
     */
    private String otherTreatment;

    /**
     * 租户Id cm_diagnosis_hist_pestilence.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_diagnosis_hist_pestilence.create_time
     */
    private Date createTime;

    /**
     * 创建者Id cm_diagnosis_hist_pestilence.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_diagnosis_hist_pestilence.update_time
     */
    private Date updateTime;

    /**
     * 更新者Id cm_diagnosis_hist_pestilence.update_user_id
     */
    private Long updateUserId;

    /**
     * 备注 cm_diagnosis_hist_pestilence.remark
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
     * 诊断日期
     */
    public Date getDiagnosticDate() {
        return diagnosticDate;
    }

    /**
     * 诊断日期
     */
    public void setDiagnosticDate(Date diagnosticDate) {
        this.diagnosticDate = diagnosticDate;
    }

    /**
     * 诊断名称集合，以“，”号隔开；对应字典item_code值
     */
    public String getDiagnosticName() {
        return diagnosticName;
    }

    /**
     * 诊断名称集合，以“，”号隔开；对应字典item_code值
     */
    public void setDiagnosticName(String diagnosticName) {
        this.diagnosticName = diagnosticName;
    }

    /**
     * 其他诊断名称
     */
    public String getOtherDiagnosticName() {
        return otherDiagnosticName;
    }

    /**
     * 其他诊断名称
     */
    public void setOtherDiagnosticName(String otherDiagnosticName) {
        this.otherDiagnosticName = otherDiagnosticName;
    }

    /**
     * 活动状态，对应字典item_code值
     */
    public String getActivityState() {
        return activityState;
    }

    /**
     * 活动状态，对应字典item_code值
     */
    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }

    /**
     * 治疗情况，对应字典item_code值
     */
    public String getTreatment() {
        return treatment;
    }

    /**
     * 治疗情况，对应字典item_code值
     */
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    /**
     * 其他治疗情况
     */
    public String getOtherTreatment() {
        return otherTreatment;
    }

    /**
     * 其他治疗情况
     */
    public void setOtherTreatment(String otherTreatment) {
        this.otherTreatment = otherTreatment;
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