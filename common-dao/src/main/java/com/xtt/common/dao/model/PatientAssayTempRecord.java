package com.xtt.common.dao.model;

/**
 * patient_assay_temp_record
 */
public class PatientAssayTempRecord {
    /**
     * patient_assay_temp_record.id
     */
    private Long id;

    /**
     * 患者id patient_assay_temp_record.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 项目编码 patient_assay_temp_record.item_code
     */
    private String itemCode;

    /**
     * 项目名称 patient_assay_temp_record.item_name
     */
    private String itemName;

    /**
     * 化验结果的值 patient_assay_temp_record.result
     */
    private String result;

    /**
     * 检查结果提示(1:正常;2:无法识别的异常;3:偏高;4:偏低) patient_assay_temp_record.result_tips
     */
    private String resultTips;

    /**
     * 租户ID patient_assay_temp_record.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 化验日期 patient_assay_temp_record.assay_date
     */
    private String assayDate;

    /**
     * patient_assay_temp_record.sample_code
     */
    private String sampleCode;

    /**
     * patient_assay_temp_record.test_method
     */
    private String testMethod;

    /**
     * 批次号 patient_assay_temp_record.batch_no
     */
    private String batchNo;

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
     * 批次号
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}