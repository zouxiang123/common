package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmPatientDiagnosisValue;
import com.xtt.common.dao.po.CmPatientDiagnosisValuePO;

@Repository
public interface CmPatientDiagnosisValueMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmPatientDiagnosisValue record);

	int insertSelective(CmPatientDiagnosisValue record);

	CmPatientDiagnosisValue selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmPatientDiagnosisValue record);

	int updateByPrimaryKey(CmPatientDiagnosisValue record);

	/**
	 * 根据条件动态查询
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmPatientDiagnosisValuePO> selectByCondition(CmPatientDiagnosisValuePO record);
}