package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * patient_assay_record
 */
public class PatientAssayRecord {
    /**
     * patient_assay_record.id
     */
    private String id;

    /**
     * 患者id
     * patient_assay_record.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * HIS系统中的病患唯一ID
     * patient_assay_record.his_pt_Id
     */
    private String hisPtId;

    /**
     * 组ID
     * patient_assay_record.group_id
     */
    private String groupId;

    /**
     * 组名称
     * patient_assay_record.group_name
     */
    private String groupName;

    /**
     * 项目编码
     * patient_assay_record.item_code
     */
    private String itemCode;

    /**
     * 项目名称
     * patient_assay_record.item_name
     */
    private String itemName;

    /**
     * 化验结果的值
     * patient_assay_record.result
     */
    private String result;

    /**
     * patient_assay_record.result_actual
     */
    private Double resultActual;

    /**
     * patient_assay_record.value_unit
     */
    private String valueUnit;

    /**
     * patient_assay_record.value_min
     */
    private BigDecimal valueMin;

    /**
     * patient_assay_record.value_max
     */
    private BigDecimal valueMax;

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低)
     * patient_assay_record.result_tips
     */
    private String resultTips;

    /**
     * patient_assay_record.reference
     */
    private String reference;

    /**
     * 备注
     * patient_assay_record.remark
     */
    private String remark;

    /**
     * 报告时间
     * patient_assay_record.report_time
     */
    private Date reportTime;

    /**
     * 申请单ID
     * patient_assay_record.req_id
     */
    private String reqId;

    /**
     * 申请时间
     * patient_assay_record.req_time
     */
    private Date reqTime;

    /**
     * patient_assay_record.req_dept
     */
    private String reqDept;

    /**
     * 样本
     * patient_assay_record.sample_class
     */
    private String sampleClass;

    /**
     * 取样时间
     * patient_assay_record.sample_time
     */
    private Date sampleTime;

    /**
     * 送检医生
     * patient_assay_record.send_doctor
     */
    private String sendDoctor;

    /**
     * 检查时间
     * patient_assay_record.check_time
     */
    private Date checkTime;

    /**
     * 检查人
     * patient_assay_record.check_person
     */
    private String checkPerson;

    /**
     * 检查项目唯一ID
     * patient_assay_record.inspection_id
     */
    private String inspectionId;

    /**
     * 透析前后标示（0=非透析前后） 1=透析前 2=透析后
     * patient_assay_record.dia_ab_flag
     */
    private String diaAbFlag;

    /**
     * 组合后的新的项目编码
     * patient_assay_record.new_item_code
     */
    private String newItemCode;

    /**
     * 租户ID
     * patient_assay_record.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * patient_assay_record.create_time
     */
    private Date createTime;

    /**
     * 化验月份
     * patient_assay_record.assay_month
     */
    private String assayMonth;

    /**
     * 化验日期
     * patient_assay_record.assay_date
     */
    private String assayDate;

    /**
     * patient_assay_record.sample_code
     */
    private String sampleCode;

    /**
     * patient_assay_record.test_method
     */
    private String testMethod;

    /**
     * 是否是院外化验项: 0否  1是
     * patient_assay_record.flage
     */
    private Boolean flage;

    /**
     */
    public String getId() {
        return id;
    }

    /**
     */
    public void setId(String id) {
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
     * HIS系统中的病患唯一ID
     */
    public String getHisPtId() {
        return hisPtId;
    }

    /**
     * HIS系统中的病患唯一ID
     */
    public void setHisPtId(String hisPtId) {
        this.hisPtId = hisPtId;
    }

    /**
     * 组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
     * 项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
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
     */
    public Double getResultActual() {
        return resultActual;
    }

    /**
     */
    public void setResultActual(Double resultActual) {
        this.resultActual = resultActual;
    }

    /**
     */
    public String getValueUnit() {
        return valueUnit;
    }

    /**
     */
    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    /**
     */
    public BigDecimal getValueMin() {
        return valueMin;
    }

    /**
     */
    public void setValueMin(BigDecimal valueMin) {
        this.valueMin = valueMin;
    }

    /**
     */
    public BigDecimal getValueMax() {
        return valueMax;
    }

    /**
     */
    public void setValueMax(BigDecimal valueMax) {
        this.valueMax = valueMax;
    }

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低)
     */
    public String getResultTips() {
        return resultTips;
    }

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低)
     */
    public void setResultTips(String resultTips) {
        this.resultTips = resultTips;
    }

    /**
     */
    public String getReference() {
        return reference;
    }

    /**
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 报告时间
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * 报告时间
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * 申请单ID
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * 申请单ID
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * 申请时间
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**
     * 申请时间
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     */
    public String getReqDept() {
        return reqDept;
    }

    /**
     */
    public void setReqDept(String reqDept) {
        this.reqDept = reqDept;
    }

    /**
     * 样本
     */
    public String getSampleClass() {
        return sampleClass;
    }

    /**
     * 样本
     */
    public void setSampleClass(String sampleClass) {
        this.sampleClass = sampleClass;
    }

    /**
     * 取样时间
     */
    public Date getSampleTime() {
        return sampleTime;
    }

    /**
     * 取样时间
     */
    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }

    /**
     * 送检医生
     */
    public String getSendDoctor() {
        return sendDoctor;
    }

    /**
     * 送检医生
     */
    public void setSendDoctor(String sendDoctor) {
        this.sendDoctor = sendDoctor;
    }

    /**
     * 检查时间
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * 检查时间
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * 检查人
     */
    public String getCheckPerson() {
        return checkPerson;
    }

    /**
     * 检查人
     */
    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

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
     * 透析前后标示（0=非透析前后） 1=透析前 2=透析后
     */
    public String getDiaAbFlag() {
        return diaAbFlag;
    }

    /**
     * 透析前后标示（0=非透析前后） 1=透析前 2=透析后
     */
    public void setDiaAbFlag(String diaAbFlag) {
        this.diaAbFlag = diaAbFlag;
    }

    /**
     * 组合后的新的项目编码
     */
    public String getNewItemCode() {
        return newItemCode;
    }

    /**
     * 组合后的新的项目编码
     */
    public void setNewItemCode(String newItemCode) {
        this.newItemCode = newItemCode;
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
     * 化验日期
     */
    public String getAssayDate() {
        return assayDate;
    }

    /**
     * 化验日期
     */
    public void setAssayDate(String assayDate) {
        this.assayDate = assayDate;
    }

    /**
     */
    public String getSampleCode() {
        return sampleCode;
    }

    /**
     */
    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    /**
     */
    public String getTestMethod() {
        return testMethod;
    }

    /**
     */
    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    /**
     * 是否是院外化验项: 0否  1是
     */
    public Boolean getFlage() {
        return flage;
    }

    /**
     * 是否是院外化验项: 0否  1是
     */
    public void setFlage(Boolean flage) {
        this.flage = flage;
    }
}