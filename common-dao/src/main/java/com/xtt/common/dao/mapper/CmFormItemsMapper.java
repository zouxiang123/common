package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmFormItems;
import com.xtt.common.dao.po.CmFormItemsPO;

@Repository
public interface CmFormItemsMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmFormItems record);

	int insertSelective(CmFormItems record);

	CmFormItems selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmFormItems record);

	int updateByPrimaryKey(CmFormItems record);

	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmFormItemsPO> selectByCondition(CmFormItems record);

	/**
	 * 查询某种类列最大的版本号
	 * 
	 * @Title: selectMaxVersion
	 * @param category
	 * @param tenantId
	 * @return
	 *
	 */
	Integer selectMaxVersion(@Param("category") String category, @Param("tenantId") Integer tenantId);

	/**
	 * 根据formid删除数据
	 * 
	 * @Title: deleteByFormId
	 * @param id
	 *
	 */
	void deleteByFormId(Long formId);
}