/**   
 * @Title: ICmDictService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午4:07:46 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.po.CmDictPO;

public interface ICmDictService {

	/**
	 * 根据字典类型和字典值获取字典对象
	 * 
	 * @param type
	 *            类型
	 * @param PdDictionaryPO
	 *            字典条件
	 * @return
	 */
	List<CmDictPO> selectAll();

}
