package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDict;
import com.xtt.common.dao.po.CmDictPO;

@Repository
public interface CmDictMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmDict record);

	int insertSelective(CmDict record);

	CmDict selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmDict record);

	int updateByPrimaryKey(CmDict record);

	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param cmDictPO
	 * @return
	 *
	 */
	List<CmDictPO> selectByCondition(CmDictPO record);
}