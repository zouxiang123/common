package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisConf;

@Repository
public interface CmDiagnosisConfMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmDiagnosisConf record);

	int insertSelective(CmDiagnosisConf record);

	CmDiagnosisConf selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmDiagnosisConf record);

	int updateByPrimaryKey(CmDiagnosisConf record);
}