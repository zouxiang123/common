package com.xtt.common.dao.model;

import java.util.Date;

import com.xtt.platform.framework.core.model.MyBatisSuperModel;

/**
 * patient_assay_data
 */
public class PatientAssayData extends MyBatisSuperModel{
    /**
     * patient_assay_data.id
     */
    private Long id;

    /**
     * patient_assay_data.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * patient_assay_data.fk_assay_record_id
     */
    private String fkAssayRecordId;

    /**
     * patient_assay_data.item_code
     */
    private String itemCode;

    /**
     * patient_assay_data.item_name
     */
    private String itemName;

    /**
     * patient_assay_data.group_id
     */
    private String groupId;

    /**
     * patient_assay_data.group_name
     */
    private String groupName;

    /**
     * patient_assay_data.assay_val
     */
    private String assayVal;

    /**
     * patient_assay_data.assay_val_num
     */
    private Double assayValNum;

    /**
     * patient_assay_data.assay_val_type
     */
    private Integer assayValType;

    /**
     * patient_assay_data.assay_case
     */
    private String assayCase;

    /**
     * patient_assay_data.assay_time
     */
    private Date assayTime;

    /**
     * patient_assay_data.assay_time_day
     */
    private Integer assayTimeDay;

    /**
     * patient_assay_data.assay_time_month
     */
    private Integer assayTimeMonth;

    /**
     * patient_assay_data.assay_time_year
     */
    private Integer assayTimeYear;

    /**
     * patient_assay_data.assay_option
     */
    private Integer assayOption;

    /**
     * patient_assay_data.send_doctor
     */
    private String sendDoctor;

    /**
     * patient_assay_data.check_person
     */
    private String checkPerson;

    /**
     * patient_assay_data.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * patient_assay_data.create_time
     */
    private Date createTime;

    /**
     * patient_assay_data.create_user_id
     */
    private Long createUserId;

    /**
     * patient_assay_data.update_time
     */
    private Date updateTime;

    /**
     * patient_assay_data.update_user_id
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
    public String getFkAssayRecordId() {
        return fkAssayRecordId;
    }

    /**
     */
    public void setFkAssayRecordId(String fkAssayRecordId) {
        this.fkAssayRecordId = fkAssayRecordId;
    }

    /**
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     */
    public String getItemName() {
        return itemName;
    }

    /**
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     */
    public String getAssayVal() {
        return assayVal;
    }

    /**
     */
    public void setAssayVal(String assayVal) {
        this.assayVal = assayVal;
    }

    /**
     */
    public Double getAssayValNum() {
        return assayValNum;
    }

    /**
     */
    public void setAssayValNum(Double assayValNum) {
        this.assayValNum = assayValNum;
    }

    /**
     */
    public Integer getAssayValType() {
        return assayValType;
    }

    /**
     */
    public void setAssayValType(Integer assayValType) {
        this.assayValType = assayValType;
    }

    /**
     */
    public String getAssayCase() {
        return assayCase;
    }

    /**
     */
    public void setAssayCase(String assayCase) {
        this.assayCase = assayCase;
    }

    /**
     */
    public Date getAssayTime() {
        return assayTime;
    }

    /**
     */
    public void setAssayTime(Date assayTime) {
        this.assayTime = assayTime;
    }

    /**
     */
    public Integer getAssayTimeDay() {
        return assayTimeDay;
    }

    /**
     */
    public void setAssayTimeDay(Integer assayTimeDay) {
        this.assayTimeDay = assayTimeDay;
    }

    /**
     */
    public Integer getAssayTimeMonth() {
        return assayTimeMonth;
    }

    /**
     */
    public void setAssayTimeMonth(Integer assayTimeMonth) {
        this.assayTimeMonth = assayTimeMonth;
    }

    /**
     */
    public Integer getAssayTimeYear() {
        return assayTimeYear;
    }

    /**
     */
    public void setAssayTimeYear(Integer assayTimeYear) {
        this.assayTimeYear = assayTimeYear;
    }

    /**
     */
    public Integer getAssayOption() {
        return assayOption;
    }

    /**
     */
    public void setAssayOption(Integer assayOption) {
        this.assayOption = assayOption;
    }

    /**
     */
    public String getSendDoctor() {
        return sendDoctor;
    }

    /**
     */
    public void setSendDoctor(String sendDoctor) {
        this.sendDoctor = sendDoctor;
    }

    /**
     */
    public String getCheckPerson() {
        return checkPerson;
    }

    /**
     */
    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
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