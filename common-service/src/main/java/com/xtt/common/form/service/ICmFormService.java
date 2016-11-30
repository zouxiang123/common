/**   
 * @Title: ICmFormService.java 
 * @Package com.xtt.common.form.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月29日 下午4:26:05 
 *
 */
package com.xtt.common.form.service;

import java.util.List;

import com.xtt.common.dao.po.CmFormPO;

public interface ICmFormService {
	/**
	 * 根据类别查询数据
	 * 
	 * @Title: selectByCategory
	 * @param category
	 * @param sysOwner
	 * @param isEnable
	 * @return
	 *
	 */
	List<CmFormPO> selectByCategory(String category, String sysOwner, boolean isEnable);

	/**
	 * 根据条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmFormPO> selectByCondition(CmFormPO record);

	/**
	 * 根据类别查询最大的版本号
	 * 
	 * @Title: selectMaxVersion
	 * @param category
	 * @param sysOwner
	 * @return
	 *
	 */
	Integer selectMaxVersion(String category, String sysOwner);

	/**
	 * 根据id查询数据
	 * 
	 * @Title: selectById
	 * @param formId
	 * @return
	 *
	 */
	CmFormPO selectById(Long formId);

	/**
	 * 将isNew标识变更为false
	 * 
	 * @Title: updateIsNew
	 * @param category
	 * @param sysOwner
	 *
	 */
	void updateIsNew(String category, String sysOwner);

	void insert(CmFormPO record);

	/**
	 * 根据id查询数据
	 * 
	 * @Title: delById
	 * @param id
	 * @return
	 *
	 */
	String delById(Long id);
}
