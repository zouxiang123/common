/**   
 * @Title: PatientAssayConfPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月14日 下午4:04:16 
 *
 */
package com.xtt.common.dao.po;

import java.util.Date;

public class PatientAssayConfPO {
    private Date startDate;
    private Date endDate;
    private String startDateShow;
    private String endDateShow;

    public String getStartDateShow() {
        return startDateShow;
    }

    public void setStartDateShow(String startDateShow) {
        this.startDateShow = startDateShow;
    }

    public String getEndDateShow() {
        return endDateShow;
    }

    public void setEndDateShow(String endDateShow) {
        this.endDateShow = endDateShow;
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

}
