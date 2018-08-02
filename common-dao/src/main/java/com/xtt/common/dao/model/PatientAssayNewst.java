package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_assay_newest
 */
public class PatientAssayNewst {
    /**
     * 系统流水ID patient_assay_newest.id
     */
    private Long id;

    /**
     * 患者ID patient_assay_newest.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 化验项编号 patient_assay_newest.item_code
     */
    private String itemCode;

    /**
     * 化验项名称 patient_assay_newest.item_name
     */
    private String itemName;

    /**
     * 患者化验值 patient_assay_newest.assay_val
     */
    private String assayVal;

    /**
     * 化验项范围 patient_assay_newest.assay_range
     */
    private String assayRange;

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低) patient_assay_newest.assay_case
     */
    private String assayCase;

    /**
     * 化验时间 patient_assay_newest.assay_time
     */
    private Date assayTime;

    /**
     * 租户id patient_assay_newest.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 patient_assay_newest.create_time
     */
    private Date createTime;

    /**
     * 创建人，默认登录用户 patient_assay_newest.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间 patient_assay_newest.update_time
     */
    private Date updateTime;

    /**
     * 修改人，默认登录用户 patient_assay_newest.update_user_id
     */
    private Long updateUserId;

    /**
     * 系统流水ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 系统流水ID
     */
    public void setId(Long id) {
        this.id = id;
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
     * 化验项编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 化验项编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 化验项名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 化验项名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 患者化验值
     */
    public String getAssayVal() {
        return assayVal;
    }

    /**
     * 患者化验值
     */
    public void setAssayVal(String assayVal) {
        this.assayVal = assayVal;
    }

    /**
     * 化验项范围
     */
    public String getAssayRange() {
        return assayRange;
    }

    /**
     * 化验项范围
     */
    public void setAssayRange(String assayRange) {
        this.assayRange = assayRange;
    }

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低)
     */
    public String getAssayCase() {
        return assayCase;
    }

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低)
     */
    public void setAssayCase(String assayCase) {
        this.assayCase = assayCase;
    }

    /**
     * 化验时间
     */
    public Date getAssayTime() {
        return assayTime;
    }

    /**
     * 化验时间
     */
    public void setAssayTime(Date assayTime) {
        this.assayTime = assayTime;
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
     * 创建人，默认登录用户
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人，默认登录用户
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 修改人，默认登录用户
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 修改人，默认登录用户
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}