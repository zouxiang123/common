package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_kpi_hist
 */
public class PatientKpiHist {
    /**
     * 系统ID patient_kpi_hist.id
     */
    private Long id;

    /**
     * 患者id patient_kpi_hist.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 记录时间 patient_kpi_hist.record_date
     */
    private Date recordDate;

    /**
     * 类别 patient_kpi_hist.category
     */
    private String category;

    /**
     * 内容 patient_kpi_hist.content
     */
    private String content;

    /**
     * 所属系统 patient_kpi_hist.sys_owner
     */
    private String sysOwner;

    /**
     * 租户ID patient_kpi_hist.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 patient_kpi_hist.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_kpi_hist.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_kpi_hist.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_kpi_hist.update_user_id
     */
    private Long updateUserId;

    /**
     * 系统ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 系统ID
     */
    public void setId(Long id) {
        this.id = id;
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
     * 记录时间
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 记录时间
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 所属系统
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
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