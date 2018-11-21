/**   
 * @Title: AppPatientAssayRecordBusiPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月14日 下午5:31:28 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.AppPatientAssayRecordBusi;

public class AppPatientAssayRecordBusiPO extends AppPatientAssayRecordBusi {
    private String assayDateStr;// 化验日期

    public String getAssayDateStr() {
        return assayDateStr;
    }

    public void setAssayDateStr(String assayDateStr) {
        this.assayDateStr = assayDateStr;
    }
}
