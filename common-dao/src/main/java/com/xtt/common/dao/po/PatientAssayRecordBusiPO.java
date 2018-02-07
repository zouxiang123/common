/**   
 * @Title: PatientAssayRecordBusiPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年7月31日 下午3:44:32 
 *
 */
package com.xtt.common.dao.po;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientAssayRecordBusiPO extends PatientAssayRecordBusi {
    private String patientName;
    private String sex;

    private Collection<Long> patientIds;
    private Collection<String> itemCodes;
    private Date startDate;
    private Date endDate;
    private Integer valueType;

    private String strStartDate;
    private String strEndDate;
    private String dateType;
    private String fkDictCode;// 关联项编号
    private Float scalingFactor;// 换算系数
    private String fkDictName;// 关联名称
    private Double scalingResult;// 换算过的结果

    private Collection<String> fkDictCodes;
    // 采集时间字符串
    private String strSampleTime;
    // 报告时间字符串
    private String strReportTime;

    private String[] multResultTips;
    /**
     * 默认：parb.assay_date ASC <br>
     * 1:fk_patient_id,item_code,assay_date DESC <br>
     * 2:fk_patient_id,item_code,assay_date ASC<br>
     * 3:fk_patient_id,fk_dict_code,sample_time ASC
     */
    private Integer queryOrderBy;

    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String startDateStr;
    private String endDateStr;
    private Date startCreateTime;
    private Date endCreateTime;
    // 集团下所有租户id
    private String groupTenant;
    private List<String> ids;// 所有id集合
    private String assayDateStr;

    public String getGroupTenant() {
        return groupTenant;
    }

    public void setGroupTenant(String groupTenant) {
        this.groupTenant = groupTenant;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    public String getStrSampleTime() {
        return strSampleTime;
    }

    public void setStrSampleTime(String strSampleTime) {
        this.strSampleTime = strSampleTime;
    }

    public String getStrReportTime() {
        return strReportTime;
    }

    public void setStrReportTime(String strReportTime) {
        this.strReportTime = strReportTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Collection<Long> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(Collection<Long> patientIds) {
        this.patientIds = patientIds;
    }

    public Collection<String> getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(Collection<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFkDictCode() {
        return fkDictCode;
    }

    public void setFkDictCode(String fkDictCode) {
        this.fkDictCode = fkDictCode;
    }

    public String[] getMultResultTips() {
        return multResultTips;
    }

    public void setMultResultTips(String[] multResultTips) {
        this.multResultTips = multResultTips;
    }

    public Integer getQueryOrderBy() {
        return queryOrderBy;
    }

    /**
     * 默认：parb.assay_date ASC <br>
     * 1:fk_patient_id,item_code,assay_date DESC <br>
     * 2:fk_patient_id,item_code,assay_date ASC <br>
     * 3:fk_patient_id,fk_dict_code,sample_time ASC
     */
    public void setQueryOrderBy(Integer queryOrderBy) {
        this.queryOrderBy = queryOrderBy;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        if (StringUtil.isNotBlank(startDateStr)) {
            startDate = DateFormatUtil.getDateByStr(startDateStr);
        }
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        if (StringUtil.isNotBlank(endDateStr)) {
            endDate = DateFormatUtil.getDateByStr(endDateStr);
        }
        this.endDateStr = endDateStr;
    }

    public Collection<String> getFkDictCodes() {
        return fkDictCodes;
    }

    public void setFkDictCodes(Collection<String> fkDictCodes) {
        this.fkDictCodes = fkDictCodes;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getAssayDateStr() {
        return assayDateStr;
    }

    public void setAssayDateStr(String assayDateStr) {
        this.assayDateStr = assayDateStr;
    }

    public Float getScalingFactor() {
        return scalingFactor;
    }

    public void setScalingFactor(Float scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    public String getFkDictName() {
        return fkDictName;
    }

    public void setFkDictName(String fkDictName) {
        this.fkDictName = fkDictName;
    }

    public Double getScalingResult() {
        return scalingResult;
    }

    public void setScalingResult(Double scalingResult) {
        this.scalingResult = scalingResult;
    }

}
