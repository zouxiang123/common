/**   
 * @Title: CmDiagnosisHistAllergyPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2016
 * @author: jackie   
 * @date: 2016年12月1日 下午7:09:53 
 *
 */
package com.xtt.common.dao.po;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.CmDiagnosisHistAllergy;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmDiagnosisHistAllergyPO extends CmDiagnosisHistAllergy {
    private String operatorName;
    private String inputDateShow;
    private String inputDateForm;
    private String allergensShow;
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

    public String getInputDateShow() {
        if (super.getInputDate() != null)
            inputDateShow = DateFormatUtil.convertDateToStr(super.getInputDate(), DateFormatUtil.FORMAT_DATE2);
        return inputDateShow;
    }

    public void setInputDateShow(String inputDateShow) {
        this.inputDateShow = inputDateShow;
    }

    public String getInputDateForm() {
        if (super.getInputDate() != null)
            inputDateForm = DateFormatUtil.convertDateToStr(super.getInputDate(), DateFormatUtil.FORMAT_DATE1);
        return inputDateForm;
    }

    public void setInputDateForm(String inputDateForm) {
        if (StringUtils.isNotBlank(inputDateForm))
            super.setInputDate(DateFormatUtil.convertStrToDate(inputDateForm));
        this.inputDateForm = inputDateForm;
    }

    public String getAllergensShow() {
        return allergensShow;
    }

    public void setAllergensShow(String allergensShow) {
        this.allergensShow = allergensShow;
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
