package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmFormValue;
import com.xtt.common.dao.po.CmFormValuePO;

@Repository
public interface CmFormValueMapper {
	int deleteByPrimaryKey(Long id);

	int insert(CmFormValue record);

	int insertSelective(CmFormValue record);

	CmFormValue selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CmFormValue record);

	int updateByPrimaryKey(CmFormValue record);

	/*use define*/
	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmFormValuePO> selectByCondition(CmFormValue record);

	void deleteByRecordId(@Param("fkRecordId") Long fkRecordId, @Param("sysOwner") String sysOwner);
}