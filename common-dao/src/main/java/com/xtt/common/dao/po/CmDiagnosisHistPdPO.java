/**   
 * @Title: CmDiagnosisHistPdPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2016
 * @author: jackie   
 * @date: 2016年12月1日 下午7:09:53 
 *
 */
package com.xtt.common.dao.po;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.CmDiagnosisHistPd;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmDiagnosisHistPdPO extends CmDiagnosisHistPd {
    private String operatorName;
    private String startDateShow;
    private String startDateForm;
    private String startReasonShow;
    private String endDateShow;
    private String endDateForm;
    private String endReasonShow;
    private String createTimeShow;

    private String hospitalName; // 就诊医院

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getStartDateShow() {
        if (super.getStartDate() != null)
            startDateShow = DateFormatUtil.convertDateToStr(super.getStartDate(), DateFormatUtil.FORMAT_DATE2);
        return startDateShow;
    }

    public void setStartDateShow(String startDateShow) {
        this.startDateShow = startDateShow;
    }

    public String getStartDateForm() {
        if (super.getStartDate() != null)
            startDateForm = DateFormatUtil.convertDateToStr(super.getStartDate(), DateFormatUtil.FORMAT_DATE1);
        return startDateForm;
    }

    public void setStartDateForm(String startDateForm) {
        if (StringUtils.isNotBlank(startDateForm))
            super.setStartDate(DateFormatUtil.convertStrToDate(startDateForm));
        this.startDateForm = startDateForm;
    }

    public String getStartReasonShow() {
        return startReasonShow;
    }

    public void setStartReasonShow(String startReasonShow) {
        this.startReasonShow = startReasonShow;
    }

    public String getEndDateShow() {
        if (super.getEndDate() != null)
            endDateShow = DateFormatUtil.convertDateToStr(super.getEndDate(), DateFormatUtil.FORMAT_DATE2);
        return endDateShow;
    }

    public void setEndDateShow(String endDateShow) {
        this.endDateShow = endDateShow;
    }

    public String getEndDateForm() {
        if (super.getEndDate() != null)
            endDateForm = DateFormatUtil.convertDateToStr(super.getEndDate(), DateFormatUtil.FORMAT_DATE1);
        return endDateForm;
    }

    public void setEndDateForm(String endDateForm) {
        if (StringUtils.isNotBlank(endDateForm))
            super.setEndDate(DateFormatUtil.convertStrToDate(endDateForm));
        this.endDateForm = endDateForm;
    }

    public String getEndReasonShow() {
        return endReasonShow;
    }

    public void setEndReasonShow(String endReasonShow) {
        this.endReasonShow = endReasonShow;
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
