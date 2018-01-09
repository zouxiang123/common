package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_assay_inspectioid_back
 */
public class PatientAssayInspectioidBack {
    /**
     * 检查项目唯一ID
     * patient_assay_inspectioid_back.inspection_id
     */
    private String inspectionId;

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
     * 创建时间
     * patient_assay_inspectioid_back.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * patient_assay_inspectioid_back.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * patient_assay_inspectioid_back.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * patient_assay_inspectioid_back.update_user_id
     */
    private Long updateUserId;

    /**
     * 检查项目唯一ID
     */
    public String getInspectionId() {
        return inspectionId;
    }

    /**
     * 检查项目唯一ID
     */
    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
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