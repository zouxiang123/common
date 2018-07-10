/**   
 * @Title: DialysisExceptionStopPo.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2018年7月6日 下午3:24:53 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.DialysisExceptionStop;

public class DialysisExceptionStopPo extends DialysisExceptionStop {
    private String startTimeShow;
    private String endTimeShow;
    private String stopReasonShow;
    private String patientName;

    private String hospitalName;
    private String docName;
    private String docImagePath;
    private String docId;

    public String getStartTimeShow() {
        return startTimeShow;
    }

    public void setStartTimeShow(String startTimeShow) {
        this.startTimeShow = startTimeShow;
    }

    public String getEndTimeShow() {
        return endTimeShow;
    }

    public void setEndTimeShow(String endTimeShow) {
        this.endTimeShow = endTimeShow;
    }

    public String getStopReasonShow() {
        return stopReasonShow;
    }

    public void setStopReasonShow(String stopReasonShow) {
        this.stopReasonShow = stopReasonShow;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocImagePath() {
        return docImagePath;
    }

    public void setDocImagePath(String docImagePath) {
        this.docImagePath = docImagePath;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

}
