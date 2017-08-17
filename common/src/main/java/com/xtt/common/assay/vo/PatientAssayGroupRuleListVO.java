package com.xtt.common.assay.vo;

import java.util.List;

import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayGroupRulePO;

public class PatientAssayGroupRuleListVO {
    private String itemCode;
    private List<PatientAssayGroupRulePO> patientAssayGroupRuleList;

    private DictHospitalLabPO dictHospitalLabPO;//

    public DictHospitalLabPO getDictHospitalLabPO() {
        return dictHospitalLabPO;
    }

    public void setDictHospitalLabPO(DictHospitalLabPO dictHospitalLabPO) {
        this.dictHospitalLabPO = dictHospitalLabPO;
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
