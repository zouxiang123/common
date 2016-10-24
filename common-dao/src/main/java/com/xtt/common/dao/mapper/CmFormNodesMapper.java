/**   
 * @Title: CmFormNodesMapper.java 
 * @Package com.xtt.common.dao.mapper
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月22日 下午5:34:22 
 *
 */
package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.po.CmFormNodes;

@Repository
public interface CmFormNodesMapper {
	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	public List<CmFormNodes> selectByCondition(CmFormNodes record);

	/**
	 * 根据value表的记录id查询数据
	 * 
	 * @Title: selectByRecordId
	 * @param record
	 * @return
	 *
	 */
	public List<CmFormNodes> selectByRecordId(CmFormNodes record);

}
