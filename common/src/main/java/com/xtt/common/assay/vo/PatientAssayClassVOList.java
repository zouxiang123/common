package com.xtt.common.assay.vo;

public class PatientAssayClassVOList {

    private PatientAssayClassVO[] PatientAssayClassVO;
    private String assayClass;

    public PatientAssayClassVO[] getPatientAssayClassVO() {
        return PatientAssayClassVO;
    }

    public void setPatientAssayClassVO(PatientAssayClassVO[] patientAssayClassVO) {
        PatientAssayClassVO = patientAssayClassVO;
    }

    public String getAssayClass() {
        return assayClass;
    }

    public void setAssayClass(String assayClass) {
        this.assayClass = assayClass;
    }

}
