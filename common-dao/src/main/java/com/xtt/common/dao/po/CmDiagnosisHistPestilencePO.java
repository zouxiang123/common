/**   
 * @Title: CmDiagnosisHistPestilencePO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2016
 * @author: jackie   
 * @date: 2016年12月1日 下午7:09:53 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.CmDiagnosisHistPestilence;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmDiagnosisHistPestilencePO extends CmDiagnosisHistPestilence {
    private String operatorName;
    private String diagnosticDateShow;
    private String diagnosticDateForm;
    private String diagnosticNameShow;
    private List<String> diagnosticNames;
    private String activityStateShow;
    private String treatmentShow;
    private String createTimeShow;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getDiagnosticDateShow() {
        if (super.getDiagnosticDate() != null)
            diagnosticDateShow = DateFormatUtil.convertDateToStr(super.getDiagnosticDate(), DateFormatUtil.FORMAT_DATE2);
        return diagnosticDateShow;
    }

    public void setDiagnosticDateShow(String diagnosticDateShow) {
        this.diagnosticDateShow = diagnosticDateShow;
    }

    public String getDiagnosticDateForm() {
        if (super.getDiagnosticDate() != null)
            diagnosticDateForm = DateFormatUtil.convertDateToStr(super.getDiagnosticDate(), DateFormatUtil.FORMAT_DATE1);
        return diagnosticDateForm;
    }

    public void setDiagnosticDateForm(String diagnosticDateForm) {
        if (StringUtils.isNotBlank(diagnosticDateForm))
            super.setDiagnosticDate(DateFormatUtil.convertStrToDate(diagnosticDateForm));
        this.diagnosticDateForm = diagnosticDateForm;
    }

    public String getDiagnosticNameShow() {
        return diagnosticNameShow;
    }

    public void setDiagnosticNameShow(String diagnosticNameShow) {
        this.diagnosticNameShow = diagnosticNameShow;
    }

    public List<String> getDiagnosticNames() {
        return diagnosticNames;
    }

    public void setDiagnosticNames(List<String> diagnosticNames) {
        this.diagnosticNames = diagnosticNames;
    }

    public String getActivityStateShow() {
        return activityStateShow;
    }

    public void setActivityStateShow(String activityStateShow) {
        this.activityStateShow = activityStateShow;
    }

    public String getTreatmentShow() {
        return treatmentShow;
    }

    public void setTreatmentShow(String treatmentShow) {
        this.treatmentShow = treatmentShow;
    }

    public String getCreateTimeShow() {
        if (super.getCreateTime() != null)
            createTimeShow = DateFormatUtil.convertDateToStr(super.getCreateTime(), DateFormatUtil.FORMAT_DATE2);
        return createTimeShow;
    }

    public void setCreateTimeShow(String createTimeShow) {
        this.createTimeShow = createTimeShow;
    }
}
