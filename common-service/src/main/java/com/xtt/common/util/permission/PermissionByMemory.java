/**   
 * @Title: PermissionByMemory.java 
 * @Package com.xtt.txgl.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月13日 上午10:38:30 
 *
 */
package com.xtt.common.util.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xtt.common.dao.model.SysObj;
import com.xtt.common.dao.model.SysRole;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.util.user.UserUtil;
import com.xtt.platform.util.config.SpringUtil;

public class PermissionByMemory implements IPermissionFactory {
	private static IRoleService roleService = (IRoleService) SpringUtil.getBean("roleServiceImpl");
	private static HashMap<Long, List<SysObj>> sysObjMap = null;// 每个角色对应的菜单
	private static List<SysObj> allSysObj = null;// 全部菜单

	@Override
	public List<SysObj> getPermissionList(Long[] roleIds) {
		if (sysObjMap == null) {
			initData();
		}
		String[] types = { "1", "2" };
		return permissionList(roleIds, types, true);
	}

	@Override
	public List<SysObj> getNonPermissionList(Long[] roleIds) {
		if (sysObjMap == null) {
			initData();
		}
		String[] types = { "1", "2" };
		return permissionList(roleIds, types, false);
	}

	@Override
	public List<SysObj> getApiPermissionList(Long[] roleIds) {
		if (sysObjMap == null) {
			initData();
		}
		String[] types = { "api" };
		return permissionList(roleIds, types, true);
	}

	@Override
	public List<SysObj> getApiNonPermissionList(Long[] roleIds) {
		if (sysObjMap == null) {
			initData();
		}
		String[] types = { "api" };
		return permissionList(roleIds, types, false);
	}

	/** 加载数据到内存 */
	private static void initData() {
		allSysObj = convertSysObjList(roleService.getAllMenuList(null));
		List<SysRole> list = roleService.getRoleListByTenantId(UserUtil.getTenantId());
		sysObjMap = new HashMap<Long, List<SysObj>>();
		for (SysRole sysRole : list) {
			Long[] roleIds = { sysRole.getId() };
			sysObjMap.put(sysRole.getId(), convertSysObjList(roleService.getMenuListByRoleId(roleIds, null)));
		}
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
	private static List<SysObj> permissionList(Long[] roleIds, String[] types, boolean hasPermission) {
		List<SysObj> list = new ArrayList<SysObj>();
		for (int i = 0; i < roleIds.length; i++) {
			for (SysObj sysObj : sysObjMap.get(roleIds[i])) {
				boolean exists = false;
				for (SysObj s : list) {
					if (s.getKey().equals(sysObj.getKey())) {// 检查是否已存在
						exists = true;
						break;
					}
				}
				if (!exists) {
					if (types != null) {
						for (String type : types) {
							if (type.equals(sysObj.getType())) {// 判断类型
								list.add(sysObj);
								break;
							}
						}
					} else {
						list.add(sysObj);
					}
				}
			}
		}
		if (hasPermission)
			return list;
		else {
			List<SysObj> nonList = new ArrayList<SysObj>();
			for (SysObj s : allSysObj) {
				boolean exists = false;
				for (SysObj so : list) {
					if (s.getKey().equals(so.getKey())) {// 检查是否已存在
						exists = true;
						break;
					}
				}
				if (!exists)
					nonList.add(s);
			}
			return nonList;
		}
	}

	@Override
	public void refresh() {
		initData();
	}
}
