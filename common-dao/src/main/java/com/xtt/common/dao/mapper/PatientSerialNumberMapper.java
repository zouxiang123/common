package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientSerialNumber;
import com.xtt.common.dao.po.PatientSerialNumberPO;

@Repository
public interface PatientSerialNumberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PatientSerialNumber record);

    int insertSelective(PatientSerialNumber record);

    PatientSerialNumber selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PatientSerialNumber record);

    int updateByPrimaryKey(PatientSerialNumber record);

    List<PatientSerialNumberPO> selectByCondition(PatientSerialNumberPO patientSerialNumberPO);

    void updateBySerialNum(PatientSerialNumberPO patientSerialNumberPO);
}