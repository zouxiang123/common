/**   
 * @Title: PatientManagePO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月25日 上午9:17:23 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.CmPatient;

/**
 * 用于患者管理页面
 * 
 * @ClassName: PatientManagePO
 * @date: 2015年12月25日 上午9:17:28
 * @version: V1.0
 * 
 */
public class PatientManagePO extends CmPatient {
    private String sexShow;
    private String ageFrom;
    private String ageTo;
    private String T;
    private String AVF;
    private String CVC;
    private String AVG;

    public String getSexShow() {
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

    public String getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(String ageFrom) {
        this.ageFrom = ageFrom;
    }

    public String getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public String getAVF() {
        return AVF;
    }

    public void setAVF(String aVF) {
        AVF = aVF;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String cVC) {
        CVC = cVC;
    }

    public String getAVG() {
        return AVG;
    }

    public void setAVG(String aVG) {
        AVG = aVG;
    }

}
