/**   
 * @Title: DictionaryUtilByMemory.java 通过内存获取字典信息
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月11日 下午8:20:26 
 *
 */
package com.xtt.common.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.xtt.common.common.service.ICmDictService;
import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.po.CmDictPO;
import com.xtt.platform.util.config.SpringUtil;

public class CmDictByMemory implements ICmDictFactory {

	public static ICmDictService cmDictService = SpringUtil.getBean("cmDictServiceImpl", ICmDictService.class);

	private static Map<Integer, Map<String, List<CmDictPO>>> dicMap = new HashMap<Integer, Map<String, List<CmDictPO>>>();

	@Override
	public String getName(String type, String value) {
		if (dicMap.get(UserUtil.getTenantId()) == null) {
			initTenantMap();
		}
		if (dicMap.get(UserUtil.getTenantId()) != null) {
			List<CmDictPO> typeList = dicMap.get(UserUtil.getTenantId()).get(type);
			if (typeList != null) {
				for (CmDictPO d : typeList) {
					if (d.getItemCode().equals(value)) {
						return d.getItemName();
					}
				}
			}

		}
		return null;
	}

	@Override
	public List<CmDictPO> getListByType(String type) {
		if (dicMap.get(UserUtil.getTenantId()) == null) {
			initTenantMap();
		}
		if (dicMap.get(UserUtil.getTenantId()) != null) {
			List<CmDictPO> typeList = dicMap.get(UserUtil.getTenantId()).get(type);
			return typeList;
		}
		return new ArrayList<CmDictPO>();
	}

	private static void initTenantMap() {
		List<CmDictPO> list = cmDictService.selectAll();
		Map<String, List<CmDictPO>> tenantMap = new LinkedHashMap<String, List<CmDictPO>>();

		List<CmDictPO> typeList = null;
		CmDictPO pd;
		for (CmDictPO d : list) {
			if (tenantMap.keySet().contains(d.getpItemCode())) {
				typeList = tenantMap.get(d.getpItemCode());
			} else {
				typeList = new ArrayList<CmDictPO>();
				tenantMap.put(d.getpItemCode(), typeList);
			}
			pd = new CmDictPO();
			pd.setItemCode(d.getItemCode());// 编号
			pd.setItemName(d.getItemName());// 名称
			pd.setItemCode(d.getItemCode());// 值
			typeList.add(pd);
		}
		dicMap.put(UserUtil.getTenantId(), tenantMap);
	}

	@Override
	public String getValue(String type, String name) {
		if (dicMap.get(UserUtil.getTenantId()) == null) {
			initTenantMap();
		}
		if (dicMap.get(UserUtil.getTenantId()) != null) {
			List<CmDictPO> typeList = dicMap.get(UserUtil.getTenantId()).get(type);
			if (typeList != null) {
				for (CmDictPO d : typeList) {
					if (d.getItemName().equals(name)) {
						return d.getItemCode();
					}
				}
			}

		}
		return null;
	}

	@Override
	public void refresh() {
		initTenantMap();
	}
}
