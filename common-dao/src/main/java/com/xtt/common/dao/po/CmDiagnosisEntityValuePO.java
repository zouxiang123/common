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

    private Integer count; // 数量

    private String year; // 年份

    private Integer countAll;// 所有原发病的数量

    private String itemName; // 原发病对应的名称

    private String itemNameValue; // 诊断名称扩展

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getCountAll() {
        return countAll;
    }

    public void setCountAll(Integer countAll) {
        this.countAll = countAll;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNameValue() {
        return itemNameValue;
    }

    public void setItemNameValue(String itemNameValue) {
        this.itemNameValue = itemNameValue;
    }
}
