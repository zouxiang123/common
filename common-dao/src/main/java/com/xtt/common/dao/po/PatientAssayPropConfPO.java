/**   
 * @Title: PatientAssayPropConfPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年7月12日 下午3:31:08 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayPropConf;

/**
 * 化验宣教提醒配置po
 * 
 * @ClassName: PatientAssayPropConfPO
 * @date: 2018年7月12日 下午3:31:16
 * @version: V1.0
 *
 */
public class PatientAssayPropConfPO extends PatientAssayPropConf {
    private String unusualCode;
    private String unusualName;

    public String getUnusualCode() {
        return unusualCode;
    }

    public void setUnusualCode(String unusualCode) {
        this.unusualCode = unusualCode;
    }

    public String getUnusualName() {
        return unusualName;
    }

    public void setUnusualName(String unusualName) {
        this.unusualName = unusualName;
    }
}
