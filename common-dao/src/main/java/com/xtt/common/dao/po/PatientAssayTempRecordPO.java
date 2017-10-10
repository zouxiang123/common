/**   
 * @Title: PatientAssayTempRecordPO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月24日 下午6:45:02 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayTempRecord;

public class PatientAssayTempRecordPO extends PatientAssayTempRecord {
    private String dateType;// 日期类型，按月还是按季度
    private String startDateStr;// 开始时间
    private String endDateStr;// 结束时间

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

}
