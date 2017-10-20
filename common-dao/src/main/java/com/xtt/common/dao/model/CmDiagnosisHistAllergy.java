package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_hist_allergy
 */
public class CmDiagnosisHistAllergy {
    /**
     * 主键Id cm_diagnosis_hist_allergy.id
     */
    private Long id;

    /**
     * 患者Id cm_diagnosis_hist_allergy.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 录入时间 cm_diagnosis_hist_allergy.input_date
     */
    private Date inputDate;

    /**
     * 过敏源，对应字典item_code值 cm_diagnosis_hist_allergy.allergens
     */
    private String allergens;

    /**
     * 其他过敏源 cm_diagnosis_hist_allergy.other_allergens
     */
    private String otherAllergens;

    /**
     * 名称 cm_diagnosis_hist_allergy.name
     */
    private String name;

    /**
     * 租户Id cm_diagnosis_hist_allergy.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_diagnosis_hist_allergy.create_time
     */
    private Date createTime;

    /**
     * 创建者Id cm_diagnosis_hist_allergy.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_diagnosis_hist_allergy.update_time
     */
    private Date updateTime;

    /**
     * 更新者Id cm_diagnosis_hist_allergy.update_user_id
     */
    private Long updateUserId;

    /**
     * 备注 cm_diagnosis_hist_allergy.remark
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
     * 录入时间
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * 录入时间
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * 过敏源，对应字典item_code值
     */
    public String getAllergens() {
        return allergens;
    }

    /**
     * 过敏源，对应字典item_code值
     */
    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    /**
     * 其他过敏源
     */
    public String getOtherAllergens() {
        return otherAllergens;
    }

    /**
     * 其他过敏源
     */
    public void setOtherAllergens(String otherAllergens) {
        this.otherAllergens = otherAllergens;
    }

    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name;
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