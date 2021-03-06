/**   
 * @Title: PatientAssayTempRecordPO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月24日 下午6:45:02 
 *
 */
package com.xtt.common.dao.po;

import java.util.Collection;
import java.util.Date;

import com.xtt.common.dao.model.PatientAssayTempRecord;

public class PatientAssayTempRecordPO extends PatientAssayTempRecord {
    private String dateType;// 日期类型，按月还是按季度
    private Date startDate;// 开始时间
    private Date endDate;// 结束时间
    private Collection<String> itemCodes;// 指定itemCodes

    public String getDateType() {
        return dateType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Collection<String> getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(Collection<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

}
