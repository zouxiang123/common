/**   
 * @Title: PermissionByMemory.java 
 * @Package com.xtt.txgl.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月13日 上午10:38:30 
 *
 */
package com.xtt.common.permission;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.dto.SysObjDto;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

public class PermissionCache implements IPermissionFactory {
	public static final String ALL_SYS_OBJ_KEY = "all_sys_obj_key";

	public static String getKey(Integer tenantId, Long id) {
		return tenantId + "permission" + id;
	}

	/** 加载数据到内存 */
	public static void cacheAll(Map<String, List<SysObjDto>> map) {
		RedisCacheUtil.batchSetObject(map);
	}

	@Override
	public List<SysObjDto> getPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "1", "2" }, true);
	}

	@Override
	public List<SysObjDto> getNonPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "1", "2" }, false);
	}

	@Override
	public List<SysObjDto> getApiPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "api" }, true);
	}

	@Override
	public List<SysObjDto> getApiNonPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "api" }, false);
	}

	/**
	 * 通过roleId和类别获取权限数据
	 * 
	 * @Title: getList
	 * @param roleIds
	 *            角色id
	 * @param types
	 *            菜单类别
	 * @param hasPermission
	 *            是否为有权限的集合
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	private static List<SysObjDto> permissionList(Long[] roleIds, String[] types, boolean hasPermission) {
		HashMap<String, SysObjDto> map = new HashMap<String, SysObjDto>();
		List<SysObjDto> permissionList;
		Integer tenandId = UserUtil.getTenantId();
		for (int i = 0; i < roleIds.length; i++) {
			List<SysObjDto> list = (List<SysObjDto>) RedisCacheUtil.getObject(getKey(tenandId, roleIds[i]));
			if (CollectionUtils.isNotEmpty(list)) {
				for (int t = 0; t < list.size(); t++) {// 使用map去除重复权限
					map.put(list.get(t).getKey(), list.get(t));
				}
				list.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(map.values())) {
			permissionList = new ArrayList<SysObjDto>(map.values().size());
			permissionList.addAll(map.values());
			permissionList.sort(new Comparator<SysObjDto>() {
				@Override
				public int compare(SysObjDto o1, SysObjDto o2) {
					return o1.getCode().compareTo(o2.getCode());
				}
			});
		} else {
			permissionList = new ArrayList<SysObjDto>();
		}
		if (hasPermission) {
			return permissionList;
		}
		// get non permission list
		List<SysObjDto> nonList = new ArrayList<SysObjDto>();
		List<SysObjDto> allObjs = (List<SysObjDto>) RedisCacheUtil.getObject(UserUtil.getTenantId() + ALL_SYS_OBJ_KEY);
		if (CollectionUtils.isNotEmpty(allObjs)) {
			if (CollectionUtils.isNotEmpty(permissionList)) {
				for (int i = 0; i < allObjs.size(); i++) {
					boolean exists = false;
					for (SysObjDto so : permissionList) {
						if (allObjs.get(i).getKey().equals(so.getKey())) {// 检查是否已存在
							exists = true;
							break;
						}
					}
					if (!exists)
						nonList.add(allObjs.get(i));
				}
			} else {// 当角色没有任何权限时
				return allObjs;
			}
		}
		return nonList;
	}
}
