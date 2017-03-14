/**   
 * @Title: PatientOrdersPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月25日 下午7:31:15 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientOrdersSource;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientOrdersSourcePO extends PatientOrdersSource {
    private String startDate;// 开始日期
    private String endDate;// 结束日期

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

    public void setChargesDateShow(String chargesDateShow) {
        this.chargesDateShow = chargesDateShow;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
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

}
