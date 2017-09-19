package com.xtt.common.assay.vo;

import java.util.List;

import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayGroupRulePO;

public class PatientAssayGroupRuleListVO {
    private String itemCode;

    private List<PatientAssayGroupRulePO> patientAssayGroupRuleList;

    private AssayHospDictPO assayHospDict;//

    public AssayHospDictPO getAssayHospDict() {
        return assayHospDict;
    }

    public void setAssayHospDict(AssayHospDictPO assayHospDict) {
        this.assayHospDict = assayHospDict;
    }

    public List<PatientAssayGroupRulePO> getPatientAssayGroupRuleList() {
        return patientAssayGroupRuleList;
    }

    public void setPatientAssayGroupRuleList(List<PatientAssayGroupRulePO> patientAssayGroupRuleList) {
        this.patientAssayGroupRuleList = patientAssayGroupRuleList;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

}
