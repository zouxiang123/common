/**   
 * @Title: CmDiagnosisHistSurgeryPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2016
 * @author: jackie   
 * @date: 2016年12月1日 下午7:09:53 
 *
 */
package com.xtt.common.dao.po;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.CmDiagnosisHistSurgery;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmDiagnosisHistSurgeryPO extends CmDiagnosisHistSurgery {
    private String operatorName;
    private String surgeryDateShow;
    private String surgeryDateForm;
    private String createTimeShow;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getSurgeryDateShow() {
        if (super.getSurgeryDate() != null)
            surgeryDateShow = DateFormatUtil.convertDateToStr(super.getSurgeryDate(), DateFormatUtil.FORMAT_DATE2);
        return surgeryDateShow;
    }

    public void setSurgeryDateShow(String surgeryDateShow) {
        this.surgeryDateShow = surgeryDateShow;
    }

    public String getSurgeryDateForm() {
        if (super.getSurgeryDate() != null)
            surgeryDateForm = DateFormatUtil.convertDateToStr(super.getSurgeryDate(), DateFormatUtil.FORMAT_DATE1);
        return surgeryDateForm;
    }

    public void setSurgeryDateForm(String surgeryDateForm) {
        if (StringUtils.isNotBlank(surgeryDateForm))
            super.setSurgeryDate(DateFormatUtil.convertStrToDate(surgeryDateForm));
        this.surgeryDateForm = surgeryDateForm;
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
