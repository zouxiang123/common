/**   
 * @Title: PatientOutcomePO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月5日 下午1:54:23 
 *
 */
package com.xtt.common.dao.po;

import java.util.Date;

import com.xtt.common.dao.model.PatientOutcome;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientOutcomePO extends PatientOutcome {
    private String recordDateShow;
    private String typeShow;
    private String patientName;
    private String createUserName;
    private String[] excludeTypes;

    private Date startTime;
    private Date endTime;
    private String multiTenant;// 多个租户id
    private String patientOutcomeType; // 转归类型"转归","转出","临时"
    private String deleteSickbedRecord; // 是否删除排床
    private String inType; // 转回类型
    private String[] types; // 转归类型数组

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public String getDeleteSickbedRecord() {
        return deleteSickbedRecord;
    }

    public void setDeleteSickbedRecord(String deleteSickbedRecord) {
        this.deleteSickbedRecord = deleteSickbedRecord;
    }

    public String getRecordDateShow() {
        if (super.getRecordDate() != null) {
            recordDateShow = DateFormatUtil.convertDateToStr(super.getRecordDate());
        }
        return recordDateShow;
    }

    public String getPatientOutcomeType() {
        return patientOutcomeType;
    }

    public void setPatientOutcomeType(String patientOutcomeType) {
        this.patientOutcomeType = patientOutcomeType;
    }

    public void setRecordDateShow(String recordDateShow) {
        if (StringUtil.isNoneBlank(recordDateShow)) {
            super.setRecordDate(DateFormatUtil.convertStrToDate(recordDateShow));
        }
        this.recordDateShow = recordDateShow;
    }

    public String getTypeShow() {
        return typeShow;
    }

    public void setTypeShow(String typeShow) {
        this.typeShow = typeShow;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String[] getExcludeTypes() {
        return excludeTypes;
    }

    public void setExcludeTypes(String[] excludeTypes) {
        this.excludeTypes = excludeTypes;
    }

    public String getMultiTenant() {
        return multiTenant;
    }

    public void setMultiTenant(String multiTenant) {
        this.multiTenant = multiTenant;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

}
