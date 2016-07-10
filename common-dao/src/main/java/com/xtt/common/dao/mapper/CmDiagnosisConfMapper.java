package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisConf;
import com.xtt.common.dao.po.CmDiagnosisConfPO;

@Repository
public interface CmDiagnosisConfMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmDiagnosisConf record);

	int insertSelective(CmDiagnosisConf record);

	CmDiagnosisConf selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmDiagnosisConf record);

	int updateByPrimaryKey(CmDiagnosisConf record);

	List<CmDiagnosisConfPO> selectByCondition(CmDiagnosisConfPO record);
}