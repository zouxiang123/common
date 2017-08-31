package com.xtt.common.dao.model;

/**
 * patient_assay_inspectioid_back
 */
public class PatientAssayInspectioidBack {
    /**
     * 检查项目唯一ID
     * patient_assay_inspectioid_back.inspectioid
     */
    private String inspectioid;

    /**
     * 患者id
     * patient_assay_inspectioid_back.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 透前透后唯一标识
     * patient_assay_inspectioid_back.dia_ab_flag
     */
    private String diaAbFlag;

    /**
     * 租户id
     * patient_assay_inspectioid_back.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 检查项目唯一ID
     */
    public String getInspectioid() {
        return inspectioid;
    }

    /**
     * 检查项目唯一ID
     */
    public void setInspectioid(String inspectioid) {
        this.inspectioid = inspectioid;
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
     * 透前透后唯一标识
     */
    public String getDiaAbFlag() {
        return diaAbFlag;
    }

    /**
     * 透前透后唯一标识
     */
    public void setDiaAbFlag(String diaAbFlag) {
        this.diaAbFlag = diaAbFlag;
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