/**   
 * @Title: CmDiagnosisHistPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2016
 * @author: jackie   
 * @date: 2016年12月1日 下午7:09:53 
 *
 */
package com.xtt.common.dao.po;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.CmDiagnosisHist;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmDiagnosisHistPO extends CmDiagnosisHist {
    private String operatorName;
    private String firstTreatmentDateShow;
    private String firstTreatmentDateForm;
    private String firstTreatmentTypeShow;
    private String createTimeShow;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getFirstTreatmentDateShow() {
        if (super.getFirstTreatmentDate() != null)
            firstTreatmentDateShow = DateFormatUtil.convertDateToStr(super.getFirstTreatmentDate(), DateFormatUtil.FORMAT_DATE2);
        return firstTreatmentDateShow;
    }

    public void setFirstTreatmentDateShow(String firstTreatmentDateShow) {
        this.firstTreatmentDateShow = firstTreatmentDateShow;
    }

    public String getFirstTreatmentDateForm() {
        if (super.getFirstTreatmentDate() != null)
            firstTreatmentDateForm = DateFormatUtil.convertDateToStr(super.getFirstTreatmentDate(), DateFormatUtil.FORMAT_DATE1);
        return firstTreatmentDateForm;
    }

    public void setFirstTreatmentDateForm(String firstTreatmentDateForm) {
        if (StringUtils.isNotBlank(firstTreatmentDateForm))
            super.setFirstTreatmentDate(DateFormatUtil.convertStrToDate(firstTreatmentDateForm));
        this.firstTreatmentDateForm = firstTreatmentDateForm;
    }

    public String getFirstTreatmentTypeShow() {
        return firstTreatmentTypeShow;
    }

    public void setFirstTreatmentTypeShow(String firstTreatmentTypeShow) {
        this.firstTreatmentTypeShow = firstTreatmentTypeShow;
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
