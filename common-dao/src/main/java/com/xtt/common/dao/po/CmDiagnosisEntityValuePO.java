/**   
 * @Title: CmDiagnosisEntityValuePO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2016
 * @author: jackie   
 * @date: 2016年12月1日 下午7:09:53 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.CmDiagnosisEntityValue;

public class CmDiagnosisEntityValuePO extends CmDiagnosisEntityValue {

    /**
     * 选项对应的entity数据
     */
    private CmDiagnosisEntityPO entity;

    private String diagnosisData;// 诊断信息

    public CmDiagnosisEntityPO getEntity() {
        return entity;
    }

    public void setEntity(CmDiagnosisEntityPO entity) {
        this.entity = entity;
    }

    public String getDiagnosisData() {
        return diagnosisData;
    }

    public void setDiagnosisData(String diagnosisData) {
        this.diagnosisData = diagnosisData;
    }

}
