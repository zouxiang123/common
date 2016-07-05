package com.xtt.common.dao.model;

/**
 * cm_patient_diagnosis_item
 */
public class CmPatientDiagnosisItem {
    /**
     * 物理主键
     * cm_patient_diagnosis_item.id
     */
    private Long id;

    /**
     * 诊断主表
     * cm_patient_diagnosis_item.fk_patient_diagnosis_id
     */
    private Long fkPatientDiagnosisId;

    /**
     * 记录id
     * cm_patient_diagnosis_item.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 诊断字典编号
     * cm_patient_diagnosis_item.fk_diagnosis_code
     */
    private String fkDiagnosisCode;

    /**
     * 检测项的值
     * cm_patient_diagnosis_item.item_value
     */
    private String itemValue;

    /**
     * 租户id
     * cm_patient_diagnosis_item.fk_tenant_id
     */
    private Integer fkTenantId;

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
     * 诊断主表
     */
    public Long getFkPatientDiagnosisId() {
        return fkPatientDiagnosisId;
    }

    /**
     * 诊断主表
     */
    public void setFkPatientDiagnosisId(Long fkPatientDiagnosisId) {
        this.fkPatientDiagnosisId = fkPatientDiagnosisId;
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
     * 诊断字典编号
     */
    public String getFkDiagnosisCode() {
        return fkDiagnosisCode;
    }

    /**
     * 诊断字典编号
     */
    public void setFkDiagnosisCode(String fkDiagnosisCode) {
        this.fkDiagnosisCode = fkDiagnosisCode;
    }

    /**
     * 检测项的值
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 检测项的值
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
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
}