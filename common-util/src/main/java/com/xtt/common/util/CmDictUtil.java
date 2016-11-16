/**   
 * @Title: DictionaryUtil.java 用于操作获取字典表的相关信息
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: 陈光浩   
 * @date: 2015年9月9日 下午4:29:48 
 *
 */
package com.xtt.common.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xtt.common.cache.CmDictCache;
import com.xtt.common.cache.ICmDictFactory;
import com.xtt.common.dto.DictDto;
import com.xtt.platform.util.lang.StringUtil;

public class CmDictUtil {

	private static ICmDictFactory factory = new CmDictCache();

	private CmDictUtil() {
	}

	/**
	 * 
	 * @Title: getName 获取字典名称
	 * @param type
	 *            类型
	 * @param value
	 *            值
	 * @return
	 * 
	 */
	public static String getName(String type, String value) {
		if (StringUtils.isNotEmpty(value)) {
			if (value.indexOf(",") != -1) {
				String[] selectedValues = value.split(",");
				String returnStr = "";
				for (int i = 0; i < selectedValues.length; i++) {
					returnStr += factory.getName(type, selectedValues[i]) + ",";
				}
				return returnStr.substring(0, returnStr.length() - 1);
			} else {
				return factory.getName(type, value);
			}
		}

		return value;
	}

	/**
	 * 
	 * @Title: getValue 获取字典对应的值
	 * @param type
	 *            类型
	 * @param name
	 *            名称
	 * 
	 * @return
	 * 
	 */
	public static String getValue(String type, String name) {
		if (StringUtils.isNotEmpty(name)) {
			if (name.indexOf(",") != -1) {
				String[] selectedValues = name.split(",");
				String returnStr = "";
				for (int i = 0; i < selectedValues.length; i++) {
					returnStr += factory.getValue(type, name) + ",";
				}
				return returnStr.substring(0, returnStr.length() - 1);
			} else {
				return factory.getValue(type, name);
			}
		}

		return name;
	}

	/**
	 * 
	 * @Title: getListByType 获取自己集合
	 * @param type
	 *            类型
	 * @return
	 * 
	 */
	public static List<DictDto> getListByType(String type) {

		return factory.getListByType(type);
	}

	/**
	 * 
	 * @Title: getNamesByType 根据类型获取该类型下面的value和name
	 * @param type
	 *            类型
	 * @return Map<value, name>
	 * 
	 */
	public static Map<String, String> getNamesByType(String type) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (DictDto dp : factory.getListByType(type)) {
			map.put(dp.getItemCode(), dp.getItemName());
		}
		return map;
	}

	private static List<DictDto> getListByType(String type, String selectStr, boolean isBool) {
		List<DictDto> list = getListByType(type);
		if (!StringUtil.isEmpty(selectStr)) {
			if (selectStr.indexOf(",") != -1) {
				String[] selectedValues = selectStr.split(",");
				for (DictDto d : list) {
					for (int i = 0; i < selectedValues.length; i++) {
						if (d.getItemCode().equals(selectedValues[i])) {
							d.setIsChecked(true);
						}
					}
				}
			} else {
				for (DictDto d : list) {
					if (d.getItemCode().equals(selectStr)) {
						d.setIsChecked(true);
					}
					if (isBool) {
						if (d.getItemCode().equals("0")) {
							d.setItemCode("false");
						} else if (d.getItemCode().equals("1")) {
							d.setItemCode("true");
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: getListByType
	 * @param type
	 *            字符串类型
	 * @param selectStr
	 *            选中字段的值
	 * @return
	 * 
	 */
	public static List<DictDto> getListByType(String type, String selectStr) {
		return getListByType(type, selectStr, false);
	}

	/**
	 * 
	 * @Title: getListByType
	 * @param type
	 *            boolean类型
	 * @param selected
	 *            是否选中
	 * @return
	 * 
	 */
	public static List<DictDto> getListByType(String type, Boolean selected) {
		String selectStr = "";
		if (selected == null || !selected) {
			selectStr = "0";
		} else {
			selectStr = "1";
		}
		return getListByType(type, selectStr, true);
	}
}
