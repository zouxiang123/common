package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientSerialNumber;

public class PatientSerialNumberPO extends PatientSerialNumber {

    private Integer isOrderBy;// 是否排序(1:排序，0：不排序)

    public Integer getIsOrderBy() {
        return isOrderBy;
    }

    public void setIsOrderBy(Integer isOrderBy) {
        this.isOrderBy = isOrderBy;
    }

}
