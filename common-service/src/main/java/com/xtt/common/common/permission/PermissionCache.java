/**   
 * @Title: PermissionByMemory.java 
 * @Package com.xtt.txgl.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月13日 上午10:38:30 
 *
 */
package com.xtt.common.common.permission;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.model.SysObj;
import com.xtt.common.dao.model.SysRole;
import com.xtt.common.user.service.IRoleService;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.config.SpringUtil;

public class PermissionCache implements PermissionFactory {
	private static IRoleService roleService = SpringUtil.getBean("roleServiceImpl", IRoleService.class);
	private static final String ALL_SYS_OBJ_KEY = "all_sys_obj_key";
	private static boolean hasInit = false;

	private static String getKey(Integer tenantId, Long id) {
		return tenantId + "permission" + id;
	}

	/** 加载数据到内存 */
	private static void init(Integer tenantId) {
		Map<String, List<SysObj>> map = new HashMap<String, List<SysObj>>();
		map.put(tenantId + ALL_SYS_OBJ_KEY, convertSysObjList(roleService.getAllMenuList(null)));
		List<SysRole> list = roleService.getRoleListByTenantId(tenantId);
		for (SysRole sysRole : list) {
			Long[] roleIds = { sysRole.getId() };
			map.put(getKey(tenantId, sysRole.getId()), convertSysObjList(roleService.getMenuListByRoleId(roleIds, null)));
		}
		RedisCacheUtil.batchSetObject(map);
		hasInit = true;
	}

	@Override
	public List<SysObj> getPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "1", "2" }, true);
	}

	@Override
	public List<SysObj> getNonPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "1", "2" }, false);
	}

	@Override
	public List<SysObj> getApiPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "api" }, true);
	}

	@Override
	public List<SysObj> getApiNonPermissionList(Long[] roleIds) {
		return permissionList(roleIds, new String[] { "api" }, false);
	}

	/**
	 * 只缓存有需要的数据
	 * 
	 * @Title: convertSysObjList
	 * @param list
	 * @return
	 *
	 */
	private static List<SysObj> convertSysObjList(List<SysObj> list) {
		List<SysObj> tempList = new ArrayList<SysObj>();
		if (list != null && !list.isEmpty()) {
			SysObj SysObj;
			for (SysObj so : list) {// 缓存有需要的数据
				SysObj = new SysObj();
				SysObj.setName(so.getName());
				SysObj.setKey(so.getKey());
				SysObj.setUrl(so.getUrl());
				SysObj.setType(so.getType());
				SysObj.setCode(so.getCode());
				SysObj.setpCode(so.getpCode());
				tempList.add(SysObj);
			}
			list.clear();
		}
		return tempList;
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
	private static List<SysObj> permissionList(Long[] roleIds, String[] types, boolean hasPermission) {
		if (!hasInit)
			init(UserUtil.getTenantId());
		HashMap<String, SysObj> map = new HashMap<String, SysObj>();
		List<SysObj> permissionList;
		Integer tenandId = UserUtil.getTenantId();
		for (int i = 0; i < roleIds.length; i++) {
			List<SysObj> list = (List<SysObj>) RedisCacheUtil.getObject(getKey(tenandId, roleIds[i]));
			if (CollectionUtils.isNotEmpty(list)) {
				for (int t = 0; t < list.size(); t++) {// 使用map去除重复权限
					map.put(list.get(t).getKey(), list.get(t));
				}
				list.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(map.values())) {
			permissionList = new ArrayList<SysObj>(map.values().size());
			permissionList.addAll(map.values());
			permissionList.sort(new Comparator<SysObj>() {
				@Override
				public int compare(SysObj o1, SysObj o2) {
					return o1.getCode().compareTo(o2.getCode());
				}
			});
		} else {
			permissionList = new ArrayList<SysObj>();
		}
		if (hasPermission) {
			return permissionList;
		}
		// get non permission list
		List<SysObj> nonList = new ArrayList<SysObj>();
		List<SysObj> allObjs = (List<SysObj>) RedisCacheUtil.getObject(UserUtil.getTenantId() + ALL_SYS_OBJ_KEY);
		if (CollectionUtils.isNotEmpty(allObjs)) {
			if (CollectionUtils.isNotEmpty(permissionList)) {
				for (int i = 0; i < allObjs.size(); i++) {
					boolean exists = false;
					for (SysObj so : permissionList) {
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

	@Override
	public void refresh() {
		init(UserUtil.getTenantId());
	}
}
