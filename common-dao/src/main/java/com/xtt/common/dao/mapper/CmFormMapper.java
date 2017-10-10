package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmForm;
import com.xtt.common.dao.po.CmFormPO;

@Repository
public interface CmFormMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmForm record);

	int insertSelective(CmForm record);

	CmForm selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmForm record);

	int updateByPrimaryKey(CmForm record);

	/**
	 * 根据条件查询数据
	 */
	List<CmFormPO> selectByCondition(CmForm record);

	/**
	 * 根据版本号和所属系统查询最大的版本号
	 * 
	 * @Title: selectMaxVersion
	 * @param record
	 * @return
	 *
	 */
	Integer selectMaxVersion(CmForm record);

	CmFormPO selectById(Long id);

	/**
	 * 更新isNew 标识
	 * 
	 * @Title: updateIsNew
	 * @param category
	 * @param sysOwner
	 * @param tenantId
	 *
	 */
	void updateIsNew(@Param("category") String category, @Param("sysOwner") String sysOwner, @Param("tenantId") Integer tenantId);
}