package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmPatientDiagnosisItem;

@Repository
public interface CmPatientDiagnosisItemMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmPatientDiagnosisItem record);

	int insertSelective(CmPatientDiagnosisItem record);

	CmPatientDiagnosisItem selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmPatientDiagnosisItem record);

	int updateByPrimaryKey(CmPatientDiagnosisItem record);
}