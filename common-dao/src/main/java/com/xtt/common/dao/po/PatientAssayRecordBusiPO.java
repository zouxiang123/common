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

import com.xtt.common.dao.model.PatientAssayRecordBusi;

public class PatientAssayRecordBusiPO extends PatientAssayRecordBusi {
    private String patientName;
    private Collection<Long> patientIds;
    private Collection<String> itemCodes;
    private Date startDate;
    private Date endDate;
    private String fkDictCode;// 关联项编号

    private String[] multResultTips;
    /**
     * 默认：parb.assay_date ASC <br>
     * 1:fk_patient_id,item_code,assay_date DESC <br>
     * 2:fk_patient_id,item_code,assay_date ASC
     */
    private Integer queryOrderBy;

    private BigDecimal minValue;
    private BigDecimal maxValue;

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
     * 2:fk_patient_id,item_code,assay_date ASC
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

}
