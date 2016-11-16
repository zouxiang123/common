/**   
 * @Title: IFollowUpConfSerivce.java 
 * @Package com.xtt.pd.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 下午4:55:59 
 *
 */
package com.xtt.common.form.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.CmFormItemsPO;

public interface ICmFormItemsSerivce {
	/**
	 * 获取所有的随访单配置项
	 * 
	 * @Title: selectAll
	 * @return
	 *
	 */
	List<CmFormItemsPO> selectAll();

	/**
	 * 保存随访单配置
	 * 
	 * @Title: saveFollowUpConf
	 * @param records
	 * @param formId
	 * @return
	 *
	 */
	Map<String, Object> saveConf(CmFormItemsPO[] records, String formName, Long formId);

	/**
	 * 根据ItemCode查询数据
	 * 
	 * @Title: selectByItemCode
	 * @param itemCode
	 * @param sysOwner
	 * @return
	 *
	 */
	List<CmFormItemsPO> selectByItemCode(String itemCode, String sysOwner);

	/**
	 * 批量删除节点
	 * 
	 * @Title: delFollowConf
	 * @param records
	 * @return
	 *
	 */
	Map<String, Object> deleteConf(CmFormItemsPO[] records);

	/**
	 * 根据自定义条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmFormItemsPO> selectByCondition(CmFormItemsPO record);

	/**
	 * 根据formId 删除数据
	 * 
	 * @Title: deleteByFormId
	 * @param id
	 *
	 */
	void deleteByFormId(Long id);
}
