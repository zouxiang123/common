package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_icd
 */
public class CmDiagnosisIcd {
    /**
     * cm_diagnosis_icd.id
     */
    private Long id;

    /**
     * 患者Id cm_diagnosis_icd.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 租户Id cm_diagnosis_icd.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_diagnosis_icd.create_time
     */
    private Date createTime;

    /**
     * 创建人 cm_diagnosis_icd.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_diagnosis_icd.update_time
     */
    private Date updateTime;

    /**
     * 更新人 cm_diagnosis_icd.update_user_id
     */
    private Long updateUserId;

    /**
     * cm_diagnosis_icd.fk_dict_icd_id
     */
    private String fkDictIcdId;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
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

    /**
     */
    public String getFkDictIcdId() {
        return fkDictIcdId;
    }

    /**
     */
    public void setFkDictIcdId(String fkDictIcdId) {
        this.fkDictIcdId = fkDictIcdId;
    }
}