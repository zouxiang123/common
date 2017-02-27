package com.xtt.common.dao.po;

import com.xtt.common.dao.model.MedicalHistory;
import com.xtt.platform.util.time.DateFormatUtil;

public class MedicalHistoryPO extends MedicalHistory {

    private String userName;
    private String mhrMark;
    private String firstDialysisDate_show; // 首次透析日期显示用
    private String firstDialysisDateFrom;
    private String firstDialysisDateTo;
    // 手术日期
    private String ssDateFrom;
    private String ssDateTo;

    // 血透开始时间
    private String xtStartDateFrom;
    private String xtStartDateTo;
    // 血透结束时间
    private String xtEndDateFrom;
    private String xtEndDateTo;

    // 腹透开始时间
    private String ftStartDateFrom;
    private String ftStartDateTo;
    // 腹透结束时间
    private String ftEndDateFrom;
    private String ftEndDateTo;

    // 肾移植开始时间
    private String syzStartDateFrom;
    private String syzStartDateTo;
    // 肾移植结束时间
    private String syzEndDateFrom;
    private String syzEndDateTo;

    // 过敏史录入日期
    private String gmDateFrom;
    private String gmDateTo;

    // 传染病诊断日期
    private String crbDateFrom;
    private String crbDateTo;

    private String optTime_show; // 手术日期

    private String xtStartTime_show; // 血透开始日期

    private String xtEndTime_show; // 血透开始日期

    private String syzStartTime_show; // 肾移植开始日期

    private String syzEndTime_show; // 肾移植结束日期

    private String ftStartTime_show; // 腹透开始时间

    private String ftEndTime_show; // 腹透结束时间

    private String gmEnterTime_show; // 过敏_录入时间

    private String crbDiaTime_show; // 传染病_诊断时间

    public String getMhrMark() {
        return mhrMark;
    }

    public void setMhrMark(String mhrMark) {
        this.mhrMark = mhrMark;
    }

    public String getOptTime_show() {
        optTime_show = DateFormatUtil.convertDateToStr(super.getOptTime(), "yyyy-MM-dd");
        return optTime_show;
    }

    public void setOptTime_show(String optTime_show) {
        this.optTime_show = optTime_show;
        if (optTime_show != null) {
            super.setOptTime(DateFormatUtil.convertStrToDate(optTime_show, "yyyy-MM-dd"));
        }
    }

    public String getXtStartTime_show() {
        xtStartTime_show = DateFormatUtil.convertDateToStr(super.getXtStartTime(), "yyyy-MM-dd");
        return xtStartTime_show;
    }

    public void setXtStartTime_show(String xtStartTime_show) {
        this.xtStartTime_show = xtStartTime_show;
        if (xtStartTime_show != null) {
            super.setXtStartTime(DateFormatUtil.convertStrToDate(xtStartTime_show, "yyyy-MM-dd"));
        }
    }

    public String getXtEndTime_show() {
        xtEndTime_show = DateFormatUtil.convertDateToStr(super.getXtEndTime(), "yyyy-MM-dd");
        return xtEndTime_show;
    }

    public void setXtEndTime_show(String xtEndTime_show) {
        this.xtEndTime_show = xtEndTime_show;
        if (xtEndTime_show != null) {
            super.setXtEndTime(DateFormatUtil.convertStrToDate(xtEndTime_show, "yyyy-MM-dd"));
        }
    }

    public String getSyzStartTime_show() {
        syzStartTime_show = DateFormatUtil.convertDateToStr(super.getSyzStartTime(), "yyyy-MM-dd");
        return syzStartTime_show;
    }

    public void setSyzStartTime_show(String syzStartTime_show) {
        this.syzStartTime_show = syzStartTime_show;
        if (syzStartTime_show != null) {
            super.setSyzStartTime(DateFormatUtil.convertStrToDate(syzStartTime_show, "yyyy-MM-dd"));
        }
    }

    public String getSyzEndTime_show() {
        syzEndTime_show = DateFormatUtil.convertDateToStr(super.getSyzEndTime(), "yyyy-MM-dd");
        return syzEndTime_show;
    }

    public void setSyzEndTime_show(String syzEndTime_show) {
        this.syzEndTime_show = syzEndTime_show;
        if (syzEndTime_show != null) {
            super.setSyzEndTime(DateFormatUtil.convertStrToDate(syzEndTime_show, "yyyy-MM-dd"));
        }
    }

    public String getFtStartTime_show() {
        ftStartTime_show = DateFormatUtil.convertDateToStr(super.getFtStartTime(), "yyyy-MM-dd");
        return ftStartTime_show;
    }

    public void setFtStartTime_show(String ftStartTime_show) {
        this.ftStartTime_show = ftStartTime_show;
        if (ftStartTime_show != null) {
            super.setFtStartTime(DateFormatUtil.convertStrToDate(ftStartTime_show, "yyyy-MM-dd"));
        }
    }

    public String getFtEndTime_show() {
        ftEndTime_show = DateFormatUtil.convertDateToStr(super.getFtEndTime(), "yyyy-MM-dd");
        return ftEndTime_show;
    }

    public void setFtEndTime_show(String ftEndTime_show) {
        this.ftEndTime_show = ftEndTime_show;
        if (ftEndTime_show != null) {
            super.setFtEndTime(DateFormatUtil.convertStrToDate(ftEndTime_show, "yyyy-MM-dd"));
        }
    }

    public String getGmEnterTime_show() {
        gmEnterTime_show = DateFormatUtil.convertDateToStr(super.getGmEnterTime(), "yyyy-MM-dd");
        return gmEnterTime_show;
    }

    public void setGmEnterTime_show(String gmEnterTime_show) {
        this.gmEnterTime_show = gmEnterTime_show;
        if (gmEnterTime_show != null) {
            super.setGmEnterTime(DateFormatUtil.convertStrToDate(gmEnterTime_show, "yyyy-MM-dd"));
        }
    }

    public String getCrbDiaTime_show() {
        crbDiaTime_show = DateFormatUtil.convertDateToStr(super.getCrbDiaTime(), "yyyy-MM-dd");
        return crbDiaTime_show;
    }

    public void setCrbDiaTime_show(String crbDiaTime_show) {
        this.crbDiaTime_show = crbDiaTime_show;
        if (crbDiaTime_show != null) {
            super.setCrbDiaTime(DateFormatUtil.convertStrToDate(crbDiaTime_show, "yyyy-MM-dd"));
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstDialysisDate_show() {
        firstDialysisDate_show = DateFormatUtil.convertDateToStr(super.getFirstDialysisDate(), "yyyy-MM-dd");
        return firstDialysisDate_show;
    }

    public void setFirstDialysisDate_show(String firstDialysisDate_show) {
        this.firstDialysisDate_show = firstDialysisDate_show;
        if (firstDialysisDate_show != null) {
            super.setFirstDialysisDate(DateFormatUtil.convertStrToDate(firstDialysisDate_show, "yyyy-MM-dd"));
        }
    }

    public String getFirstDialysisDateFrom() {
        return firstDialysisDateFrom;
    }

    public void setFirstDialysisDateFrom(String firstDialysisDateFrom) {
        this.firstDialysisDateFrom = firstDialysisDateFrom;
    }

    public String getFirstDialysisDateTo() {
        return firstDialysisDateTo;
    }

    public void setFirstDialysisDateTo(String firstDialysisDateTo) {
        this.firstDialysisDateTo = firstDialysisDateTo;
    }

    public String getSsDateFrom() {
        return ssDateFrom;
    }

    public void setSsDateFrom(String ssDateFrom) {
        this.ssDateFrom = ssDateFrom;
    }

    public String getSsDateTo() {
        return ssDateTo;
    }

    public void setSsDateTo(String ssDateTo) {
        this.ssDateTo = ssDateTo;
    }

    public String getXtStartDateFrom() {
        return xtStartDateFrom;
    }

    public void setXtStartDateFrom(String xtStartDateFrom) {
        this.xtStartDateFrom = xtStartDateFrom;
    }

    public String getXtStartDateTo() {
        return xtStartDateTo;
    }

    public void setXtStartDateTo(String xtStartDateTo) {
        this.xtStartDateTo = xtStartDateTo;
    }

    public String getXtEndDateFrom() {
        return xtEndDateFrom;
    }

    public void setXtEndDateFrom(String xtEndDateFrom) {
        this.xtEndDateFrom = xtEndDateFrom;
    }

    public String getXtEndDateTo() {
        return xtEndDateTo;
    }

    public void setXtEndDateTo(String xtEndDateTo) {
        this.xtEndDateTo = xtEndDateTo;
    }

    public String getFtStartDateFrom() {
        return ftStartDateFrom;
    }

    public void setFtStartDateFrom(String ftStartDateFrom) {
        this.ftStartDateFrom = ftStartDateFrom;
    }

    public String getFtStartDateTo() {
        return ftStartDateTo;
    }

    public void setFtStartDateTo(String ftStartDateTo) {
        this.ftStartDateTo = ftStartDateTo;
    }

    public String getFtEndDateFrom() {
        return ftEndDateFrom;
    }

    public void setFtEndDateFrom(String ftEndDateFrom) {
        this.ftEndDateFrom = ftEndDateFrom;
    }

    public String getFtEndDateTo() {
        return ftEndDateTo;
    }

    public void setFtEndDateTo(String ftEndDateTo) {
        this.ftEndDateTo = ftEndDateTo;
    }

    public String getSyzStartDateFrom() {
        return syzStartDateFrom;
    }

    public void setSyzStartDateFrom(String syzStartDateFrom) {
        this.syzStartDateFrom = syzStartDateFrom;
    }

    public String getSyzStartDateTo() {
        return syzStartDateTo;
    }

    public void setSyzStartDateTo(String syzStartDateTo) {
        this.syzStartDateTo = syzStartDateTo;
    }

    public String getSyzEndDateFrom() {
        return syzEndDateFrom;
    }

    public void setSyzEndDateFrom(String syzEndDateFrom) {
        this.syzEndDateFrom = syzEndDateFrom;
    }

    public String getSyzEndDateTo() {
        return syzEndDateTo;
    }

    public void setSyzEndDateTo(String syzEndDateTo) {
        this.syzEndDateTo = syzEndDateTo;
    }

    public String getGmDateFrom() {
        return gmDateFrom;
    }

    public void setGmDateFrom(String gmDateFrom) {
        this.gmDateFrom = gmDateFrom;
    }

    public String getGmDateTo() {
        return gmDateTo;
    }

    public void setGmDateTo(String gmDateTo) {
        this.gmDateTo = gmDateTo;
    }

    public String getCrbDateFrom() {
        return crbDateFrom;
    }

    public void setCrbDateFrom(String crbDateFrom) {
        this.crbDateFrom = crbDateFrom;
    }

    public String getCrbDateTo() {
        return crbDateTo;
    }

    public void setCrbDateTo(String crbDateTo) {
        this.crbDateTo = crbDateTo;
    }

}
