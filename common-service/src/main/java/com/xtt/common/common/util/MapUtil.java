/**   
 * @Title: MapUtil.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月17日 下午4:34:45 
 *
 */
package com.xtt.common.common.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MapUtil {
	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
}

class MapKeyComparator implements Comparator<String> {

	@Override
	public int compare(String str1, String str2) {

		return str1.compareTo(str2);
	}
}