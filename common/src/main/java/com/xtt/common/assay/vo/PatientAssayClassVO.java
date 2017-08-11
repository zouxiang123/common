package com.xtt.common.assay.vo;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayClass;

public class PatientAssayClassVO {

    List<PatientAssayClass> detailList;
    String assayClass;

    public List<PatientAssayClass> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<PatientAssayClass> detailList) {
        this.detailList = detailList;
    }

    public String getAssayClass() {
        return assayClass;
    }

    public void setAssayClass(String assayClass) {
        this.assayClass = assayClass;
    }
}
