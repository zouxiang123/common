package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmFormBaseItems;
import com.xtt.common.dao.po.CmFormBaseItemsPO;

@Repository
public interface CmFormBaseItemsMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmFormBaseItems record);

	int insertSelective(CmFormBaseItems record);

	CmFormBaseItems selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmFormBaseItems record);

	int updateByPrimaryKey(CmFormBaseItems record);

	/*自定义*/
	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmFormBaseItemsPO> selectByCondition(CmFormBaseItems record);
}