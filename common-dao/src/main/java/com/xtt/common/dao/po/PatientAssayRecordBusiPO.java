/**   
 * @Title: PatientAssayRecordBusiPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年7月31日 下午3:44:32 
 *
 */
package com.xtt.common.dao.po;

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

}
