/**   
 * @Title: ReportPatientOutcomePO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年3月28日 下午6:58:45 
 *
 */
package com.xtt.common.dao.po;

import java.util.Date;

import com.xtt.platform.util.time.DateFormatUtil;

public class PatientOutcomeReport {
    private Long id;
    /**
     * 患者id patient_outcome.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 类型:(1:腹透,2:移植,3:死亡) patient_outcome.type
     */
    private String type;

    /**
     * 转归日期 patient_outcome.record_date
     */
    private Date recordDate;

    /**
     * 原因 patient_outcome.reason
     */
    private String reason;

    /**
     * 所属系统 patient_outcome.sys_owner
     */
    private String sysOwner;

    /**
     * patient_outcome.fk_tenant_id
     */
    private Integer fkTenantId;

    private String recordDateShow;
    private String typeShow;
    private String patientName;
    private String[] excludeTypes;

    private Date startDate;
    private Date endDate;
    // 统计参数：患者类型（长期、临时）
    private Boolean isTemp;
    private String serialNum;
    private String[] types; // 转归类型数组

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    public Integer getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    public String getRecordDateShow() {
        if (getRecordDate() != null) {
            recordDateShow = DateFormatUtil.convertDateToStr(getRecordDate());
        }
        return recordDateShow;
    }

    public void setRecordDateShow(String recordDateShow) {
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

    public String[] getExcludeTypes() {
        return excludeTypes;
    }

    public void setExcludeTypes(String[] excludeTypes) {
        this.excludeTypes = excludeTypes;
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

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public Boolean getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Boolean isTemp) {
        this.isTemp = isTemp;
    }

}
