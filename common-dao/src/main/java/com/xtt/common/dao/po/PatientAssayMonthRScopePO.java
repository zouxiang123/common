package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayMonthRScope;

public class PatientAssayMonthRScopePO extends PatientAssayMonthRScope {

    private String showStartTime;
    private String showEndTime;

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
    }

    public String getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }

}