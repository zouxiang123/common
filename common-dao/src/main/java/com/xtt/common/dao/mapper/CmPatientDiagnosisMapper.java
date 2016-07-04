package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmPatientDiagnosis;

@Repository
public interface CmPatientDiagnosisMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmPatientDiagnosis record);

	int insertSelective(CmPatientDiagnosis record);

	CmPatientDiagnosis selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmPatientDiagnosis record);

	int updateByPrimaryKey(CmPatientDiagnosis record);
}