/**   
 * @Title: CmSysParam.java 
 * @Package com.xtt.common.dto
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月10日 下午6:30:34 
 *
 */
package com.xtt.common.dto;

import java.util.List;

public class SysParamDto {
    private String paramName;
    private String paramDesc;
    private String paramValue;
    private String dicType;
    private String paramUnit;
    private Integer fkTenantId;
    private String sysOwner;
    private List<Object> dicUnitList;// 单位对应的字典表数据

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getParamUnit() {
        return paramUnit;
    }

    public void setParamUnit(String paramUnit) {
        this.paramUnit = paramUnit;
    }

    public Integer getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    public List<Object> getDicUnitList() {
        return dicUnitList;
    }

    public void setDicUnitList(List<Object> dicUnitList) {
        this.dicUnitList = dicUnitList;
    }

}
