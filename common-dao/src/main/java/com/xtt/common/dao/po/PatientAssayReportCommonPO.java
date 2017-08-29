/**   
 * @Title: PatientAssayBackCommonPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月11日 下午5:24:19 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayReportCommon;

public class PatientAssayReportCommonPO extends PatientAssayReportCommon {

    // 患者名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
