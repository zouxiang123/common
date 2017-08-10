package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_assay_back_inspectioid
 */
public class PatientAssayBackInspectioid {
    /**
     * patient_assay_back_inspectioid.req_id
     */
    private String reqId;

    /**
     * patient_assay_back_inspectioid.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * patient_assay_back_inspectioid.sample_time
     */
    private Date sampleTime;

    /**
     * patient_assay_back_inspectioid.dia_ab_flag
     */
    private String diaAbFlag;

    /**
     * patient_assay_back_inspectioid.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * patient_assay_back_inspectioid.create_time
     */
    private Date createTime;

    /**
     * patient_assay_back_inspectioid.create_user_id
     */
    private Long createUserId;

    /**
     * patient_assay_back_inspectioid.update_time
     */
    private Date updateTime;

    /**
     * patient_assay_back_inspectioid.update_user_id
     */
    private Long updateUserId;

    /**
     */
    public String getReqId() {
        return reqId;
    }

    /**
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     */
    public Date getSampleTime() {
        return sampleTime;
    }

    /**
     */
    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }

    /**
     */
    public String getDiaAbFlag() {
        return diaAbFlag;
    }

    /**
     */
    public void setDiaAbFlag(String diaAbFlag) {
        this.diaAbFlag = diaAbFlag;
    }

    /**
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    /**
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}