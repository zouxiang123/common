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

    private Date startDate;
    private Date endDate;
    private String multiTenant;// 多个租户id

    public String getRecordDateShow() {
        if (super.getRecordDate() != null) {
            recordDateShow = DateFormatUtil.convertDateToStr(super.getRecordDate());
        }
        return recordDateShow;
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

}
