/**   
 * @Title: SysParamUtil.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月18日 上午9:59:51 
 *
 */
package com.xtt.common.util;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.common.service.ISysParamService;
import com.xtt.common.dao.po.SysParamPO;
import com.xtt.common.util.user.UserUtil;
import com.xtt.platform.util.config.SpringUtil;

public class SysParamUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysParamUtil.class);

	private static ISysParamService sysParamService = (ISysParamService) SpringUtil.getBean("sysParamServiceImpl");

	private static HashMap<Integer, HashMap<String, SysParamPO>> paramMap = new HashMap<Integer, HashMap<String, SysParamPO>>();

	/**
	 * 根据参数名称获取参数值
	 * 
	 * @Title: getByName
	 * @param name
	 * @return
	 * 
	 */
	public static String getValueByName(String name) {
		if (paramMap.get(UserUtil.getTenantId()) == null) {
			initMap();
		}
		SysParamPO result = paramMap.get(UserUtil.getTenantId()).get(name);
		if (result == null) {
			LOGGER.warn("param {} is not exists", name);
			return "";
		} else {
			return result.getParamValue();
		}
	}

	/**
	 * 根据参数名称获取参数对象
	 * 
	 * @Title: getByName
	 * @param name
	 * @return
	 * 
	 */
	public static SysParamPO getByName(String name) {
		if (paramMap.get(UserUtil.getTenantId()) == null) {
			initMap();
		}
		return paramMap.get(UserUtil.getTenantId()).get(name);
	}

	/** 加载数据到内存 */
	private static void initMap() {
		List<SysParamPO> list = sysParamService.getByTenantId(UserUtil.getTenantId());
		if (list != null && !list.isEmpty()) {
			HashMap<String, SysParamPO> map = new HashMap<String, SysParamPO>();
			SysParamPO sp;
			for (SysParamPO s : list) {
				sp = new SysParamPO();
				sp.setParamName(s.getParamName());
				sp.setParamValue(s.getParamValue());
				sp.setParamUnit(s.getParamUnit());
				map.put(s.getParamName(), sp);
			}
			paramMap.put(UserUtil.getTenantId(), map);
		} else {
			paramMap.put(UserUtil.getTenantId(), new HashMap<String, SysParamPO>());
			LOGGER.warn("there is no param exists");
		}
	}

	public static void refresh() {
		initMap();
	}

}
