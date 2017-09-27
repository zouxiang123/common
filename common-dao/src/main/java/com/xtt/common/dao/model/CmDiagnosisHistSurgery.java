package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_hist_surgery
 */
public class CmDiagnosisHistSurgery {
    /**
     * 主键Id cm_diagnosis_hist_surgery.id
     */
    private Long id;

    /**
     * 患者Id cm_diagnosis_hist_surgery.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 手术日期 cm_diagnosis_hist_surgery.surgery_date
     */
    private Date surgeryDate;

    /**
     * 名称 cm_diagnosis_hist_surgery.surgery_name
     */
    private String surgeryName;

    /**
     * 租户Id cm_diagnosis_hist_surgery.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_diagnosis_hist_surgery.create_time
     */
    private Date createTime;

    /**
     * 创建者Id cm_diagnosis_hist_surgery.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_diagnosis_hist_surgery.update_time
     */
    private Date updateTime;

    /**
     * 更新者Id cm_diagnosis_hist_surgery.update_user_id
     */
    private Long updateUserId;

    /**
     * 备注 cm_diagnosis_hist_surgery.remark
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
     * 手术日期
     */
    public Date getSurgeryDate() {
        return surgeryDate;
    }

    /**
     * 手术日期
     */
    public void setSurgeryDate(Date surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    /**
     * 名称
     */
    public String getSurgeryName() {
        return surgeryName;
    }

    /**
     * 名称
     */
    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
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