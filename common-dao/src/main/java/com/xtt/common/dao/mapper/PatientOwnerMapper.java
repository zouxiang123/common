package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientOwner;

@Repository
public interface PatientOwnerMapper {
	int deleteByPrimaryKey(Long id);

	int insert(PatientOwner record);

	int insertSelective(PatientOwner record);

	PatientOwner selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(PatientOwner record);

	int updateByPrimaryKey(PatientOwner record);

	/*use define*/
	/**
	 * 根据患者id批量更新数据为disable状态
	 * 
	 * @Title: updateDisableByPatientId
	 * @param fkPatientId
	 *
	 */
	void updateDisableByPatientId(Long fkPatientId);

	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<PatientOwner> selectByCondition(PatientOwner record);

}