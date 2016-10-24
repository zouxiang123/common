/**   
 * @Title: ICmFormNodesService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月22日 下午6:32:29 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.po.CmFormNodes;

public interface ICmFormNodesService {

	/** 根据自定义条件查询数据 */
	List<CmFormNodes> selectByCondition(CmFormNodes record);

	/**
	 * 根据父item_code查询数据
	 *
	 */
	List<CmFormNodes> selectByPItemCode(String itemCode);

	/**
	 * 根据formId查询数据
	 * 
	 * @Title: selectByFormId
	 * @param formId
	 * @return
	 *
	 */
	List<CmFormNodes> selectByFormId(Long formId);

	/**
	 * 根据记录id查询数据
	 * 
	 * @Title: selectByRecordId
	 * @param recordId
	 * @param sysOwner
	 * @return
	 *
	 */
	List<CmFormNodes> selectByRecordId(Long recordId, String sysOwner);
}
