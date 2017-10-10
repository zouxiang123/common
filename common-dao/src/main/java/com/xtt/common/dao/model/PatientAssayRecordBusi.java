package com.xtt.common.dao.model;

import java.util.Date;

import com.xtt.platform.framework.core.model.MyBatisSuperModel;

/**
 * patient_assay_record_busi
 */
public class PatientAssayRecordBusi extends MyBatisSuperModel {
    /**
     * patient_assay_record_busi.id
     */
    private Long id;

    /**
     * 患者id patient_assay_record_busi.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 组ID patient_assay_record_busi.group_id
     */
    private String groupId;

    /**
     * 组名称 patient_assay_record_busi.group_name
     */
    private String groupName;

    /**
     * 项目编码 patient_assay_record_busi.item_code
     */
    private String itemCode;

    /**
     * 项目名称 patient_assay_record_busi.item_name
     */
    private String itemName;

    /**
     * 化验结果的值 patient_assay_record_busi.result
     */
    private String result;

    /**
     * 真实结果的值 patient_assay_record_busi.result_actual
     */
    private Double resultActual;

    /**
     * 单位 patient_assay_record_busi.value_unit
     */
    private String valueUnit;

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低) patient_assay_record_busi.result_tips
     */
    private String resultTips;

    /**
     * 参考值 patient_assay_record_busi.reference
     */
    private String reference;

    /**
     * 申请单ID patient_assay_record_busi.req_id
     */
    private String reqId;

    /**
     * 样本 patient_assay_record_busi.sample_class
     */
    private String sampleClass;

    /**
     * 取样时间 patient_assay_record_busi.sample_time
     */
    private Date sampleTime;

    /**
     * 报告时间 patient_assay_record_busi.report_time
     */
    private Date reportTime;

    /**
     * 检查项目唯一ID patient_assay_record_busi.inspection_id
     */
    private String inspectionId;

    /**
     * 透析前后标示（0=非透析前后） 1=透析前 2=透析后 patient_assay_record_busi.dia_ab_flag
     */
    private String diaAbFlag;

    /**
     * 化验月份 patient_assay_record_busi.assay_month
     */
    private String assayMonth;

    /**
     * 化验日期 patient_assay_record_busi.assay_date
     */
    private Date assayDate;

    /**
     * 是否是院外化验项: 0否 1是 patient_assay_record_busi.flage
     */
    private Boolean flage;

    /**
     * 租户ID patient_assay_record_busi.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 patient_assay_record_busi.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_assay_record_busi.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_assay_record_busi.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_assay_record_busi.update_user_id
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
     * 真实结果的值
     */
    public Double getResultActual() {
        return resultActual;
    }

    /**
     * 真实结果的值
     */
    public void setResultActual(Double resultActual) {
        this.resultActual = resultActual;
    }

    /**
     * 单位
     */
    public String getValueUnit() {
        return valueUnit;
    }

    /**
     * 单位
     */
    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
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
     * 参考值
     */
    public String getReference() {
        return reference;
    }

    /**
     * 参考值
     */
    public void setReference(String reference) {
        this.reference = reference;
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
    public Date getAssayDate() {
        return assayDate;
    }

    /**
     * 化验日期
     */
    public void setAssayDate(Date assayDate) {
        this.assayDate = assayDate;
    }

    /**
     * 是否是院外化验项: 0否 1是
     */
    public Boolean getFlage() {
        return flage;
    }

    /**
     * 是否是院外化验项: 0否 1是
     */
    public void setFlage(Boolean flage) {
        this.flage = flage;
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