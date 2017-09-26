/**   
 * @Title: PatientOrdersPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月25日 下午7:31:15 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientOrders;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientOrdersPO extends PatientOrders {
    private String dateStr;// 日期

    private Boolean isDownload;// 是否需要下载

    public static final String PT_TYPE_ZHUYUAN = "01";// 住院
    public static final String PT_TYPE_MENZHEN = "02";// 门诊

    public String startDate;// 开始日期
    public String endDate;// 结束日期

    public String ptName;// 病患姓名

    private String chargesDateShow; // 费用日期

    public String getChargesDateShow() {
        if (super.getChargesDate() != null) {
            chargesDateShow = DateFormatUtil.convertDateToStr(super.getChargesDate());
        } else if (super.getEnterTime() != null) {
            chargesDateShow = DateFormatUtil.convertDateToStr(super.getEnterTime());
        }
        return chargesDateShow;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setChargesDateShow(String chargesDateShow) {
        this.chargesDateShow = chargesDateShow;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Boolean isDownload) {
        this.isDownload = isDownload;
    }

}
