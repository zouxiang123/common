/**   
 * @Title: DictHospitalLabPO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月20日 下午7:35:04 
 *
 */
package com.xtt.common.dao.po;

import java.util.Collection;
import java.util.Date;

import com.xtt.common.dao.model.DictHospitalLab;

public class DictHospitalLabPO extends DictHospitalLab {
    public static final String DATE_TYPE_MONTH = "m";
    public static final String DATE_TYPE_SEASON = "s";

    public static final Integer VALUE_TYPE_NUMBER = 1;

    private Collection<String> dictCodes;

    private String fkItemName;
    private String assayClass;// 化验类
    private Boolean selectFlag;// 是否选中
    private Boolean categoryFlag;// 是否是化验单
    private String result;// 化验值
    private Date assayDate;// 录入日期
    private String assayDateStr;
    private String resultTips;// 化验结果
    private String patientAssayId;// 患者化验记录单
    private Long fkPatientId;// 患者id

    public String getFkItemName() {
        return fkItemName;
    }

    public void setFkItemName(String fkItemName) {
        this.fkItemName = fkItemName;
    }

    public Collection<String> getDictCodes() {
        return dictCodes;
    }

    public void setDictCodes(Collection<String> dictCodes) {
        this.dictCodes = dictCodes;
    }

    public String getAssayClass() {
        return assayClass;
    }

    public void setAssayClass(String assayClass) {
        this.assayClass = assayClass;
    }

    public Boolean getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(Boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

    public Boolean getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(Boolean categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getAssayDate() {
        return assayDate;
    }

    public void setAssayDate(Date assayDate) {
        this.assayDate = assayDate;
    }

    public String getAssayDateStr() {
        return assayDateStr;
    }

    public void setAssayDateStr(String assayDateStr) {
        this.assayDateStr = assayDateStr;
    }

    public String getResultTips() {
        return resultTips;
    }

    public void setResultTips(String resultTips) {
        this.resultTips = resultTips;
    }

    public String getPatientAssayId() {
        return patientAssayId;
    }

    public void setPatientAssayId(String patientAssayId) {
        this.patientAssayId = patientAssayId;
    }

    public Long getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

}
