/**   
 * @Title: SysParamUtil.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月18日 上午9:59:51 
 *
 */
package com.xtt.common.common.util;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.common.service.ISysParamService;
import com.xtt.common.dao.po.SysParamPO;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.config.SpringUtil;

public class SysParamUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysParamUtil.class);

	private static ISysParamService sysParamService = SpringUtil.getBean("sysParamServiceImpl", ISysParamService.class);
	private static boolean hasInit = false;

	private static String getKey(Integer tenantId, String key) {
		return tenantId + "sysParam" + key;
	}

	/** 加载数据到内存 */
	public static void init(Integer tenantId) {
		List<SysParamPO> list = sysParamService.getByTenantId(tenantId);
		if (CollectionUtils.isNotEmpty(list)) {
			HashMap<String, SysParamPO> map = new HashMap<String, SysParamPO>();
			SysParamPO obj;
			String key;
			for (SysParamPO s : list) {
				obj = new SysParamPO();
				key = getKey(tenantId, s.getParamName());
				obj.setParamName(s.getParamName());
				obj.setParamValue(s.getParamValue());
				obj.setParamUnit(s.getParamUnit());
				map.put(key, obj);
			}
			// clear list
			list.clear();
			RedisCacheUtil.batchSetObject(map);
		}
		hasInit = true;
	}

	/**
	 * 根据参数名称获取参数值
	 * 
	 * @Title: getByName
	 * @param name
	 * @return
	 * 
	 */
	public static String getValueByName(String name) {
		SysParamPO result = getByName(UserUtil.getTenantId(), name);
		if (result != null) {
			return result.getParamValue();
		}
		return "";
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
		return getByName(UserUtil.getTenantId(), name);
	}

	/**
	 * 根据租户和参数名称获取参数对象
	 * 
	 * @Title: getByName
	 * @param tenantId
	 * @param name
	 * @return
	 * 
	 */
	public static SysParamPO getByName(Integer tenantId, String name) {
		if (!hasInit)
			init(tenantId);
		SysParamPO obj = (SysParamPO) RedisCacheUtil.getObject(getKey(tenantId, name));
		if (obj == null) {
			LOGGER.warn("param {} is not exists", name);
			obj = new SysParamPO();
		}
		return obj;
	}

	public static String getValueByName(Integer tenantId, String name) {
		return getByName(tenantId, name).getParamValue();
	}

	public static void refresh(Integer tenantId) {
		init(tenantId);
	}

}
