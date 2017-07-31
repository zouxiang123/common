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

import com.xtt.common.dao.model.PatientAssayRecordBusi;

public class PatientAssayRecordBusiPO extends PatientAssayRecordBusi {
    private String patientName;
    private Collection<Long> patientIds;
    private Collection<String> itemCodes;

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

}
