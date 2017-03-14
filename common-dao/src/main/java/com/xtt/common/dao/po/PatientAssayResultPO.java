/**   
 * @Title: PatientAssayResultPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月25日 下午2:57:53 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayResult;

public class PatientAssayResultPO extends PatientAssayResult {
    private List<Long> patientIds;// 患者id集合

    // getter and setter
    public List<Long> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<Long> patientIds) {
        this.patientIds = patientIds;
    }

}
