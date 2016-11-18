package com.xtt.common.dao.model;

import java.util.Date;

/**
 * medical_history_remark
 */
public class MedicalHistoryRemark {
    /**
     * medical_history_remark.id
     */
    private Long id;

    /**
     * medical_history_remark.mhr_start_time
     */
    private Date mhrStartTime;

    /**
     * medical_history_remark.mhr_end_time
     */
    private Date mhrEndTime;

    /**
     * medical_history_remark.mhr_startReason_orName
     */
    private String mhrStartreasonOrname;

    /**
     * medical_history_remark.mhr_startOrtherReason
     */
    private String mhrStartortherreason;

    /**
     * medical_history_remark.mhr_endReason
     */
    private String mhrEndreason;

    /**
     * medical_history_remark.mhr_endOtherReason
     */
    private String mhrEndotherreason;

    /**
     * medical_history_remark.mhr_remark
     */
    private String mhrRemark;

    /**
     * medical_history_remark.mhr_mark
     */
    private String mhrMark;

    /**
     * medical_history_remark.create_time
     */
    private Date createTime;

    /**
     * medical_history_remark.create_user_id
     */
    private Long createUserId;

    /**
     * medical_history_remark.update_time
     */
    private Date updateTime;

    /**
     * medical_history_remark.update_user_id
     */
    private Long updateUserId;

    /**
     * medical_history_remark.medical_historyId
     */
    private Long medicalHistoryid;

    /**
     * medical_history_remark.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * medical_history_remark.activityStatus
     */
    private String activitystatus;

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
     */
    public Date getMhrStartTime() {
        return mhrStartTime;
    }

    /**
     */
    public void setMhrStartTime(Date mhrStartTime) {
        this.mhrStartTime = mhrStartTime;
    }

    /**
     */
    public Date getMhrEndTime() {
        return mhrEndTime;
    }

    /**
     */
    public void setMhrEndTime(Date mhrEndTime) {
        this.mhrEndTime = mhrEndTime;
    }

    /**
     */
    public String getMhrStartreasonOrname() {
        return mhrStartreasonOrname;
    }

    /**
     */
    public void setMhrStartreasonOrname(String mhrStartreasonOrname) {
        this.mhrStartreasonOrname = mhrStartreasonOrname;
    }

    /**
     */
    public String getMhrStartortherreason() {
        return mhrStartortherreason;
    }

    /**
     */
    public void setMhrStartortherreason(String mhrStartortherreason) {
        this.mhrStartortherreason = mhrStartortherreason;
    }

    /**
     */
    public String getMhrEndreason() {
        return mhrEndreason;
    }

    /**
     */
    public void setMhrEndreason(String mhrEndreason) {
        this.mhrEndreason = mhrEndreason;
    }

    /**
     */
    public String getMhrEndotherreason() {
        return mhrEndotherreason;
    }

    /**
     */
    public void setMhrEndotherreason(String mhrEndotherreason) {
        this.mhrEndotherreason = mhrEndotherreason;
    }

    /**
     */
    public String getMhrRemark() {
        return mhrRemark;
    }

    /**
     */
    public void setMhrRemark(String mhrRemark) {
        this.mhrRemark = mhrRemark;
    }

    /**
     */
    public String getMhrMark() {
        return mhrMark;
    }

    /**
     */
    public void setMhrMark(String mhrMark) {
        this.mhrMark = mhrMark;
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

    /**
     */
    public Long getMedicalHistoryid() {
        return medicalHistoryid;
    }

    /**
     */
    public void setMedicalHistoryid(Long medicalHistoryid) {
        this.medicalHistoryid = medicalHistoryid;
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
    public String getActivitystatus() {
        return activitystatus;
    }

    /**
     */
    public void setActivitystatus(String activitystatus) {
        this.activitystatus = activitystatus;
    }
}