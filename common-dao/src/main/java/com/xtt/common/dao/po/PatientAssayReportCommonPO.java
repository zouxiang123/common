/**   
 * @Title: PatientAssayBackCommonPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月11日 下午5:24:19 
 *
 */
package com.xtt.common.dao.po;

import java.util.Collection;

import com.xtt.common.dao.model.PatientAssayReportCommon;

public class PatientAssayReportCommonPO extends PatientAssayReportCommon {

    // 患者名称
    private String name;
    // 患者长期临时标识
    private Boolean isTemp;
    // 我的患者
    private Long userId;
    // 患者标签
    private Long[] patientLabelIds;
    private Collection<String> itemCodes;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getPatientLabelIds() {
        return patientLabelIds;
    }

    public void setPatientLabelIds(Long[] patientLabelIds) {
        this.patientLabelIds = patientLabelIds;
    }

    public Boolean getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Boolean isTemp) {
        this.isTemp = isTemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(Collection<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

}
