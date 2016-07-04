package com.xtt.common.dao.model;

import java.util.Date;

public class PatientAssayTempRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.fk_patient_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Long fkPatientId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.his_pt_Id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String hisPtId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.assay_date
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String assayDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.assay_month
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String assayMonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.group_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.group_name
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String groupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.item_code
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String itemCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.item_name
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String itemName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.result
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String result;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.result_tips
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String resultTips;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.reference
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String reference;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.remark
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.report_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Date reportTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.req_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String reqId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.req_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Date reqTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.sample_class
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String sampleClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.sample_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Date sampleTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.send_doctor
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String sendDoctor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.check_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Date checkTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.check_person
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String checkPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.inspection_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String inspectionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.fk_tenant_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Integer fkTenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.create_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patient_assay_temp_record.assay_season
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    private String assaySeason;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.id
     *
     * @return the value of patient_assay_temp_record.id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.id
     *
     * @param id the value for patient_assay_temp_record.id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.fk_patient_id
     *
     * @return the value of patient_assay_temp_record.fk_patient_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.fk_patient_id
     *
     * @param fkPatientId the value for patient_assay_temp_record.fk_patient_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.his_pt_Id
     *
     * @return the value of patient_assay_temp_record.his_pt_Id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getHisPtId() {
        return hisPtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.his_pt_Id
     *
     * @param hisPtId the value for patient_assay_temp_record.his_pt_Id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setHisPtId(String hisPtId) {
        this.hisPtId = hisPtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.assay_date
     *
     * @return the value of patient_assay_temp_record.assay_date
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getAssayDate() {
        return assayDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.assay_date
     *
     * @param assayDate the value for patient_assay_temp_record.assay_date
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setAssayDate(String assayDate) {
        this.assayDate = assayDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.assay_month
     *
     * @return the value of patient_assay_temp_record.assay_month
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getAssayMonth() {
        return assayMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.assay_month
     *
     * @param assayMonth the value for patient_assay_temp_record.assay_month
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setAssayMonth(String assayMonth) {
        this.assayMonth = assayMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.group_id
     *
     * @return the value of patient_assay_temp_record.group_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.group_id
     *
     * @param groupId the value for patient_assay_temp_record.group_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.group_name
     *
     * @return the value of patient_assay_temp_record.group_name
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.group_name
     *
     * @param groupName the value for patient_assay_temp_record.group_name
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.item_code
     *
     * @return the value of patient_assay_temp_record.item_code
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.item_code
     *
     * @param itemCode the value for patient_assay_temp_record.item_code
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.item_name
     *
     * @return the value of patient_assay_temp_record.item_name
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.item_name
     *
     * @param itemName the value for patient_assay_temp_record.item_name
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.result
     *
     * @return the value of patient_assay_temp_record.result
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.result
     *
     * @param result the value for patient_assay_temp_record.result
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.result_tips
     *
     * @return the value of patient_assay_temp_record.result_tips
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getResultTips() {
        return resultTips;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.result_tips
     *
     * @param resultTips the value for patient_assay_temp_record.result_tips
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setResultTips(String resultTips) {
        this.resultTips = resultTips;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.reference
     *
     * @return the value of patient_assay_temp_record.reference
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getReference() {
        return reference;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.reference
     *
     * @param reference the value for patient_assay_temp_record.reference
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.remark
     *
     * @return the value of patient_assay_temp_record.remark
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.remark
     *
     * @param remark the value for patient_assay_temp_record.remark
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.report_time
     *
     * @return the value of patient_assay_temp_record.report_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.report_time
     *
     * @param reportTime the value for patient_assay_temp_record.report_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.req_id
     *
     * @return the value of patient_assay_temp_record.req_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.req_id
     *
     * @param reqId the value for patient_assay_temp_record.req_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.req_time
     *
     * @return the value of patient_assay_temp_record.req_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.req_time
     *
     * @param reqTime the value for patient_assay_temp_record.req_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.sample_class
     *
     * @return the value of patient_assay_temp_record.sample_class
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getSampleClass() {
        return sampleClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.sample_class
     *
     * @param sampleClass the value for patient_assay_temp_record.sample_class
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setSampleClass(String sampleClass) {
        this.sampleClass = sampleClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.sample_time
     *
     * @return the value of patient_assay_temp_record.sample_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Date getSampleTime() {
        return sampleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.sample_time
     *
     * @param sampleTime the value for patient_assay_temp_record.sample_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.send_doctor
     *
     * @return the value of patient_assay_temp_record.send_doctor
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getSendDoctor() {
        return sendDoctor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.send_doctor
     *
     * @param sendDoctor the value for patient_assay_temp_record.send_doctor
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setSendDoctor(String sendDoctor) {
        this.sendDoctor = sendDoctor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.check_time
     *
     * @return the value of patient_assay_temp_record.check_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.check_time
     *
     * @param checkTime the value for patient_assay_temp_record.check_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.check_person
     *
     * @return the value of patient_assay_temp_record.check_person
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getCheckPerson() {
        return checkPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.check_person
     *
     * @param checkPerson the value for patient_assay_temp_record.check_person
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.inspection_id
     *
     * @return the value of patient_assay_temp_record.inspection_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getInspectionId() {
        return inspectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.inspection_id
     *
     * @param inspectionId the value for patient_assay_temp_record.inspection_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.fk_tenant_id
     *
     * @return the value of patient_assay_temp_record.fk_tenant_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.fk_tenant_id
     *
     * @param fkTenantId the value for patient_assay_temp_record.fk_tenant_id
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.create_time
     *
     * @return the value of patient_assay_temp_record.create_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.create_time
     *
     * @param createTime the value for patient_assay_temp_record.create_time
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patient_assay_temp_record.assay_season
     *
     * @return the value of patient_assay_temp_record.assay_season
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public String getAssaySeason() {
        return assaySeason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patient_assay_temp_record.assay_season
     *
     * @param assaySeason the value for patient_assay_temp_record.assay_season
     *
     * @mbggenerated Tue May 24 16:30:34 CST 2016
     */
    public void setAssaySeason(String assaySeason) {
        this.assaySeason = assaySeason;
    }
}