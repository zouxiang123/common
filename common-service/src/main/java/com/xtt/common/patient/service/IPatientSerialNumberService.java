package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.po.PatientSerialNumberPO;

public interface IPatientSerialNumberService {

    public List<PatientSerialNumberPO> selectByCondition(PatientSerialNumberPO patientSerialNumberPO);

    /**
     * 根据SerialNum进行编辑
     */
    public void updateBySerialNum(PatientSerialNumberPO patientSerialNumberPO);

    public void insert();

}
