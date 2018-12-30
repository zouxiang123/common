/**   
 * @Title: ReportExWeightPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年6月6日 下午1:57:32 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.ReportExAssay;

public class ReportExAssayPo extends ReportExAssay {

    /**
     * 患者名称
     */
    private String patientName;
    /**
     * 是否临时患者[0,1全部患者，0长期患者，1临时患者]
     */
    private String isTemp;
    /**
     * 开始时间
     */
    private String reportDateStart;
    /**
     * 结束时间
     */
    private String reportDateEnd;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(String isTemp) {
        this.isTemp = isTemp;
    }

    public String getReportDateStart() {
        return reportDateStart;
    }

    public void setReportDateStart(String reportDateStart) {
        this.reportDateStart = reportDateStart;
    }

    public String getReportDateEnd() {
        return reportDateEnd;
    }

    public void setReportDateEnd(String reportDateEnd) {
        this.reportDateEnd = reportDateEnd;
    }
}
