package com.xtt.common.dao.po;

import java.util.List;

/**
 * Created by lgm on 2017/1/22.
 */
public class ReportParameterPO {
    // 年份
    private String year;
    // 月份
    private String month;
    // 患者类型：临时、长期
    private Integer[] patientTemp;
    // 患者类型：0,1 字符串
    private String patientTempValue;

    // 患者信息统计报表部分
    private Integer ageRange;
    private Integer dialysisAgeRange;

    // 转归患者统计
    private String type;

    // 阴转阳统计
    private boolean hasPatient;
    private String patientTitle;
    private String fkDictCode;
    /**
     * 化验月份 patient_assay_record.assay_month
     */
    private String assayMonth;

    // 周促红素用量统计
    private String selectDate;
    private String itemCode;

    //
    private String startDate;
    private String endDate;

    // 阶段评估统计使用
    private String assessId;

    // 并发症信息统计
    private Long cDictionaryId;
    private String patientType;

    // 抗凝剂使用统计
    private Integer[] shiftNumber;
    private String shiftNumberValue;

    private Integer fkTenantId;

    private List<Double> ruleList;// 分组规则

    private String groupRule; // 单个规则
    private Long[] patientLabelId; // 标签编号
    private String patientLabelValue; // 标签值

    private Integer[] medicalType;// 患者医保类型
    private String medicalTypeValue;// 患者医保类型0,1,2
    private Integer patientReportType;// 患者统计类型
    private String patientName; // 患者姓名

    private Boolean isTemp;// 是否临时患者

    // 非均匀年龄段 区间值
    private Integer ageIntervalBeg;
    private Integer ageIntervalEnd;
    // 是否均匀年龄段
    private String ageGapType;
    private String outcomeType;// 是否转归

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer[] getPatientTemp() {
        return patientTemp;
    }

    public void setPatientTemp(Integer[] patientTemp) {
        this.patientTemp = patientTemp;
    }

    public String getPatientTempValue() {
        if (patientTemp != null) {
            if (patientTemp.length != 2) {
                String values = "";
                for (Integer value : patientTemp) {
                    values += "," + value;
                }
                if (values.length() > 0)
                    values = values.substring(1);
                patientTempValue = values;
            }
        }
        return patientTempValue;
    }

    public void setPatientTempValue(String patientTempValue) {
        this.patientTempValue = patientTempValue;
    }

    public Integer getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(Integer ageRange) {
        this.ageRange = ageRange;
    }

    public Integer getDialysisAgeRange() {
        return dialysisAgeRange;
    }

    public void setDialysisAgeRange(Integer dialysisAgeRange) {
        this.dialysisAgeRange = dialysisAgeRange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getHasPatient() {
        return hasPatient;
    }

    public void setHasPatient(boolean hasPatient) {
        this.hasPatient = hasPatient;
    }

    public String getPatientTitle() {
        return patientTitle;
    }

    public void setPatientTitle(String patientTitle) {
        this.patientTitle = patientTitle;
    }

    public String getFkDictCode() {
        return fkDictCode;
    }

    public void setFkDictCode(String fkDictCode) {
        this.fkDictCode = fkDictCode;
    }

    public String getAssayMonth() {
        return assayMonth;
    }

    public void setAssayMonth(String assayMonth) {
        this.assayMonth = assayMonth;
    }

    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssessId() {
        return assessId;
    }

    public void setAssessId(String assessId) {
        this.assessId = assessId;
    }

    public Long getCDictionaryId() {
        return cDictionaryId;
    }

    public void setCDictionaryId(Long cDictionaryId) {
        this.cDictionaryId = cDictionaryId;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public Integer[] getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(Integer[] shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getShiftNumberValue() {
        if (shiftNumber != null) {
            if (shiftNumber.length != 2) {
                String values = "";
                for (Integer value : shiftNumber) {
                    values += "," + value;
                }
                if (values.length() > 0)
                    values = values.substring(1);
                shiftNumberValue = values;
            }
        }
        return shiftNumberValue;
    }

    public void setShiftNumberValue(String shiftNumberValue) {
        this.shiftNumberValue = shiftNumberValue;
    }

    public Integer getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    public List<Double> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Double> ruleList) {
        this.ruleList = ruleList;
    }

    public String getGroupRule() {
        return groupRule;
    }

    public void setGroupRule(String groupRule) {
        this.groupRule = groupRule;
    }

    public Long[] getPatientLabelId() {

        return patientLabelId;
    }

    public void setPatientLabelId(Long[] patientLabelId) {
        this.patientLabelId = patientLabelId;
    }

    public String getPatientLabelValue() {
        return patientLabelValue;
    }

    public void setPatientLabelValue(String patientLabelValue) {
        this.patientLabelValue = patientLabelValue;
    }

    public Integer[] getMedicalType() {
        return medicalType;
    }

    public void setMedicalType(Integer[] medicalType) {
        this.medicalType = medicalType;
    }

    public String getMedicalTypeValue() {
        if (medicalType != null) {
            if (medicalType.length != 2) {
                String values = "";
                for (Integer value : medicalType) {
                    values += "," + value;
                }
                if (values.length() > 0)
                    values = values.substring(1);
                medicalTypeValue = values;
            }
        }
        return medicalTypeValue;
    }

    public void setMedicalTypeValue(String medicalTypeValue) {
        this.medicalTypeValue = medicalTypeValue;
    }

    public Integer getPatientReportType() {
        return patientReportType;
    }

    public void setPatientReportType(Integer patientReportType) {
        this.patientReportType = patientReportType;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Boolean getIsTemp() {
        if (patientTemp != null) {
            if (patientTemp.length == 1) {
                isTemp = patientTemp[0].intValue() == 1;
            }
        }
        return isTemp;
    }

    public void setIsTemp(Boolean isTemp) {
        this.isTemp = isTemp;
    }

    public Integer getAgeIntervalBeg() {
        return ageIntervalBeg;
    }

    public void setAgeIntervalBeg(Integer ageIntervalBeg) {
        this.ageIntervalBeg = ageIntervalBeg;
    }

    public Integer getAgeIntervalEnd() {
        return ageIntervalEnd;
    }

    public void setAgeIntervalEnd(Integer ageIntervalEnd) {
        this.ageIntervalEnd = ageIntervalEnd;
    }

    public String getAgeGapType() {
        return ageGapType;
    }

    public void setAgeGapType(String ageGapType) {
        this.ageGapType = ageGapType;
    }

    public String getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(String outcomeType) {
        this.outcomeType = outcomeType;
    }

}
