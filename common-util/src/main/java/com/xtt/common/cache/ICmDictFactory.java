/**   
 * @Title: ICmDictFactory.java 
 * @Package com.xtt.common.common.cache
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午3:59:40 
 *
 */
package com.xtt.common.cache;

import java.util.List;

import com.xtt.common.dto.DictDto;

public interface ICmDictFactory {
	/**
	 * 
	 * @Title: getName 获取字典名称
	 * @param type
	 *            字典类型
	 * @param value
	 *            字典值
	 * @return 字典名称
	 * 
	 */
	public String getName(String type, String value);

	/**
	 * 
	 * @Title: getListByType 获取字典集合
	 * @param type
	 *            字典类型
	 * @return 字典集合
	 * 
	 */
	public List<DictDto> getListByType(String type);

	/**
	 * 
	 * @Title: getValue 获取字典名称
	 * @param type
	 *            字典类型
	 * @param name
	 *            字典名称
	 * @return 字典对应的值
	 * 
	 */
	public String getValue(String type, String name);

}
