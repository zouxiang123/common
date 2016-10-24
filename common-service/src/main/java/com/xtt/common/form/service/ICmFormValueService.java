/**   
 * @Title: ICmFormValueService.java 
 * @Package com.xtt.common.form
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月29日 上午9:00:11 
 *
 */
package com.xtt.common.form.service;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.po.CmFormValuePO;

public interface ICmFormValueService {
	/**
	 * 根据item_code查询数据
	 * 
	 * @Title: selectByItemCode
	 * @param itemCode
	 * @param formId
	 * @return
	 *
	 */
	List<CmFormValuePO> selectByItemCode(String itemCode, Long formId);

	/**
	 * 根据表单主表查询数据
	 * 
	 * @Title: selectByFormId
	 * @return
	 *
	 */
	List<CmFormValuePO> selectByFormId(Long formId);

	/**
	 * 根据自定义条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmFormValuePO> selectByCondition(CmFormValuePO record);

	/**
	 * 批量插入数据
	 * 
	 * @Title: batchInsert
	 * @param list
	 *
	 */
	void batchInsert(Collection<CmFormValuePO> records);

	/**
	 * 根据记录id和所属系统输出数据
	 * 
	 * @Title: deleteByRecordId
	 * @param recordId
	 * @param sysOwner
	 *
	 */
	void deleteByRecordId(Long recordId, String sysOwner);
}
