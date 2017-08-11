package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CureSymptomAndCondition;
import com.xtt.common.dao.po.CureSymptomAndConditionPO;

@Repository
public interface CureSymptomAndConditionMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cure_symptom_and_condition
	 * 
	 * @mbggenerated Thu Sep 10 13:03:28 CST 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cure_symptom_and_condition
	 * 
	 * @mbggenerated Thu Sep 10 13:03:28 CST 2015
	 */
	int insert(CureSymptomAndCondition record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cure_symptom_and_condition
	 * 
	 * @mbggenerated Thu Sep 10 13:03:28 CST 2015
	 */
	int insertSelective(CureSymptomAndCondition record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cure_symptom_and_condition
	 * 
	 * @mbggenerated Thu Sep 10 13:03:28 CST 2015
	 */
	CureSymptomAndCondition selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cure_symptom_and_condition
	 * 
	 * @mbggenerated Thu Sep 10 13:03:28 CST 2015
	 */
	int updateByPrimaryKeySelective(CureSymptomAndCondition record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cure_symptom_and_condition
	 * 
	 * @mbggenerated Thu Sep 10 13:03:28 CST 2015
	 */
	int updateByPrimaryKey(CureSymptomAndCondition record);

	/**
	 * 根据透析活动ID取得信息
	 * 
	 * @Title: selectByDCId
	 * @param pdId
	 *            患者诊断主表ID
	 * @return CureSymptomAndCondition
	 * 
	 */
	CureSymptomAndConditionPO selectByPDId(Long pdId);

	/**
	 * 根据患者ID查询历史记录
	 * 
	 * @Title: selectByPatientId
	 * @param patientId
	 *            患者ID
	 * @return List<CureSymptomAndConditionPO>
	 * 
	 */
	List<CureSymptomAndConditionPO> selectByPatientId(Long patientId);
}