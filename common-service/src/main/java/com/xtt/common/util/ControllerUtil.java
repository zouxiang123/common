/**   
 * @Title: ControllerUtil.java 
 * @Package com.xtt.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 下午5:24:28 
 *
 */
package com.xtt.common.util;

import java.util.HashMap;
import java.util.Map;

import com.xtt.common.util.constants.CommonConstants;

public class ControllerUtil {
	/**
	 * 获取成功的map
	 * 
	 * @Title: getSuccessMap
	 * @return
	 *
	 */
	public static Map<String, Object> getSuccessMap() {
		return getSuccessMap(null);
	}

	/**
	 * 获取成功的map
	 * 
	 * @Title: getSuccessMap
	 * @param paramMap其它参数
	 * @return
	 *
	 */
	public static Map<String, Object> getSuccessMap(Map<String, Object> paramMap) {
		return getResultMap(CommonConstants.SUCCESS, paramMap);
	}

	/**
	 * 获取警告的map
	 * 
	 * @Title: getWarningMap
	 * @return
	 *
	 */
	public static Map<String, Object> getWarningMap() {
		return getWarningMap(null);
	}

	/**
	 * 获取警告map
	 * 
	 * @Title: getWarningMap
	 * @param paramMap其它参数
	 * @return
	 *
	 */
	public static Map<String, Object> getWarningMap(Map<String, Object> paramMap) {
		return getResultMap(CommonConstants.WARNING, paramMap);
	}

	/**
	 * 获取失败的map
	 * 
	 * @Title: getFailureMap
	 * @return
	 *
	 */
	public static Map<String, Object> getFailureMap() {
		return getFailureMap(null);
	}

	/**
	 * 获取执行失败map
	 * 
	 * @Title: getFailureMap
	 * @param paramMap参数
	 * @return
	 *
	 */
	public static Map<String, Object> getFailureMap(Map<String, Object> paramMap) {
		return getResultMap(CommonConstants.FAILURE, paramMap);
	}

	private static Map<String, Object> getResultMap(String status, Map<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CommonConstants.STATUS, status);
		if (paramMap != null && !paramMap.isEmpty()) {
			map.putAll(paramMap);
		}
		return map;
	}
}
