/**   
 * @Title: PatientOutcomeReportPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年8月23日 上午8:58:32 
 *
 */
package com.xtt.common.dao.po;

import java.util.Date;

public class PatientOutcomeReportPO extends PatientOutcomeReport {

    private Date catheterDate;

    private Date outComeDate;

    public Date getCatheterDate() {
        return catheterDate;
    }

    public void setCatheterDate(Date catheterDate) {
        this.catheterDate = catheterDate;
    }

    public Date getOutComeDate() {
        return outComeDate;
    }

    public void setOutComeDate(Date outComeDate) {
        this.outComeDate = outComeDate;
    }
}
