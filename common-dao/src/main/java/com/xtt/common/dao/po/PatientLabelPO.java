/**   
 * @Title: PatientLabelPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2018年3月12日 下午2:05:42 
 *
 */
package com.xtt.common.dao.po;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.model.PatientLabel;

public class PatientLabelPO extends PatientLabel {
    private Long patientId;
    private String patientName;
    private String patientImagePath;
    private String patientSpellInitials;
    private Collection<Long> patientIds;
    private List<PatientLabelPO> labels;
    private Long fkLabelId;
    private Boolean isChecked;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientImagePath() {
        return patientImagePath;
    }

    public void setPatientImagePath(String patientImagePath) {
        this.patientImagePath = patientImagePath;
    }

    public List<PatientLabelPO> getLabels() {
        return labels;
    }

    public void setLabels(List<PatientLabelPO> labels) {
        this.labels = labels;
    }

    public Collection<Long> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(Collection<Long> patientIds) {
        this.patientIds = patientIds;
    }

    public Long getFkLabelId() {
        return fkLabelId;
    }

    public void setFkLabelId(Long fkLabelId) {
        this.fkLabelId = fkLabelId;
    }

    public String getPatientSpellInitials() {
        return patientSpellInitials;
    }

    public void setPatientSpellInitials(String patientSpellInitials) {
        this.patientSpellInitials = patientSpellInitials;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

}
