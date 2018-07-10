package com.xtt.common.dao.model;

import java.util.Date;

/**
 * dialysis_exception_stop
 */
public class DialysisExceptionStop {
    /**
     * dialysis_exception_stop.id
     */
    private Long id;

    /**
     * 透析活动表id dialysis_exception_stop.fk_dialysis_campaign_id
     */
    private Long fkDialysisCampaignId;

    /**
     * 患者ID dialysis_exception_stop.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 透析日期 dialysis_exception_stop.dialysis_date
     */
    private Date dialysisDate;

    /**
     * 预计透析时间(分钟) dialysis_exception_stop.dialysis_time
     */
    private Integer dialysisTime;

    /**
     * 实际透析时间(分钟) dialysis_exception_stop.actual_dialysis_time
     */
    private Integer actualDialysisTime;

    /**
     * 提前下机时长(分钟) dialysis_exception_stop.advance_time
     */
    private Integer advanceTime;

    /**
     * 透析开始时间 dialysis_exception_stop.start_time
     */
    private Date startTime;

    /**
     * 透析结束时间 dialysis_exception_stop.end_time
     */
    private Date endTime;

    /**
     * dialysis_exception_stop.stop_reason_type
     */
    private String stopReasonType;

    /**
     * dialysis_exception_stop.stop_reason_desc
     */
    private String stopReasonDesc;

    /**
     * 删除标记 dialysis_exception_stop.del_flag
     */
    private Boolean delFlag;

    /**
     * 操作人 dialysis_exception_stop.operator_id
     */
    private Long operatorId;

    /**
     * 租户ID dialysis_exception_stop.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 dialysis_exception_stop.create_time
     */
    private Date createTime;

    /**
     * 创建人 dialysis_exception_stop.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 dialysis_exception_stop.update_time
     */
    private Date updateTime;

    /**
     * 更新人 dialysis_exception_stop.update_user_id
     */
    private Long updateUserId;

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
     * 透析活动表id
     */
    public Long getFkDialysisCampaignId() {
        return fkDialysisCampaignId;
    }

    /**
     * 透析活动表id
     */
    public void setFkDialysisCampaignId(Long fkDialysisCampaignId) {
        this.fkDialysisCampaignId = fkDialysisCampaignId;
    }

    /**
     * 患者ID
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 患者ID
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 透析日期
     */
    public Date getDialysisDate() {
        return dialysisDate;
    }

    /**
     * 透析日期
     */
    public void setDialysisDate(Date dialysisDate) {
        this.dialysisDate = dialysisDate;
    }

    /**
     * 预计透析时间(分钟)
     */
    public Integer getDialysisTime() {
        return dialysisTime;
    }

    /**
     * 预计透析时间(分钟)
     */
    public void setDialysisTime(Integer dialysisTime) {
        this.dialysisTime = dialysisTime;
    }

    /**
     * 实际透析时间(分钟)
     */
    public Integer getActualDialysisTime() {
        return actualDialysisTime;
    }

    /**
     * 实际透析时间(分钟)
     */
    public void setActualDialysisTime(Integer actualDialysisTime) {
        this.actualDialysisTime = actualDialysisTime;
    }

    /**
     * 提前下机时长(分钟)
     */
    public Integer getAdvanceTime() {
        return advanceTime;
    }

    /**
     * 提前下机时长(分钟)
     */
    public void setAdvanceTime(Integer advanceTime) {
        this.advanceTime = advanceTime;
    }

    /**
     * 透析开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 透析开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 透析结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 透析结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     */
    public String getStopReasonType() {
        return stopReasonType;
    }

    /**
     */
    public void setStopReasonType(String stopReasonType) {
        this.stopReasonType = stopReasonType;
    }

    /**
     */
    public String getStopReasonDesc() {
        return stopReasonDesc;
    }

    /**
     */
    public void setStopReasonDesc(String stopReasonDesc) {
        this.stopReasonDesc = stopReasonDesc;
    }

    /**
     * 删除标记
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 操作人
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 操作人
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 租户ID
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户ID
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