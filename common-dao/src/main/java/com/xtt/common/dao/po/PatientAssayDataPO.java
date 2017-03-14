/**   
 * @Title: PatientAssayDataPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年12月5日 下午4:13:37 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayData;

public class PatientAssayDataPO extends PatientAssayData {
    // 起始日期/单日期查询
    private String startTime;
    // 结束日期
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
