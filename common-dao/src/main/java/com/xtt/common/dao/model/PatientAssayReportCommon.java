package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_assay_back_common
 */
public class PatientAssayReportCommon {
    /**
     * patient_assay_back_common.id
     */
    private Long id;

    /**
     * 患者id patient_assay_back_common.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 化验月份 patient_assay_back_common.assay_month
     */
    private String assayMonth;

    /**
     * 项目编码 patient_assay_back_common.item_code
     */
    private String itemCode;

    /**
     * 化验结果的值 patient_assay_back_common.result
     */
    private String result;

    /**
     * 租户ID patient_assay_back_common.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 patient_assay_back_common.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_assay_back_common.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_assay_back_common.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_assay_back_common.update_user_id
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
     * 化验月份
     */
    public String getAssayMonth() {
        return assayMonth;
    }

    /**
     * 化验月份
     */
    public void setAssayMonth(String assayMonth) {
        this.assayMonth = assayMonth;
    }

    /**
     * 项目编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 项目编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 化验结果的值
     */
    public String getResult() {
        return result;
    }

    /**
     * 化验结果的值
     */
    public void setResult(String result) {
        this.result = result;
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