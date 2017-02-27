/**   
 * @Title: ClinicalDiagnosisResultPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年10月16日 下午5:21:26 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.ClinicalDiagnosisResult;

public class ClinicalDiagnosisResultPO extends ClinicalDiagnosisResult {
    /** 用于显示 */
    private String typeName;// 类型名称

    private int count;// 数量

    /** 用于查询条件 */
    private String startTime;// 开始时间
    private String endTime;// 结束时间

    private Integer fkTenantId;// 租户id

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

}
