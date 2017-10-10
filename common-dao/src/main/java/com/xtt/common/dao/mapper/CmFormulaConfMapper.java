package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmFormulaConf;
import com.xtt.common.dao.po.CmFormulaConfPO;

@Repository
public interface CmFormulaConfMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmFormulaConf record);

	int insertSelective(CmFormulaConf record);

	CmFormulaConf selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmFormulaConf record);

	int updateByPrimaryKey(CmFormulaConf record);

	// use define
	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmFormulaConfPO> selectByCondition(CmFormulaConf record);
}