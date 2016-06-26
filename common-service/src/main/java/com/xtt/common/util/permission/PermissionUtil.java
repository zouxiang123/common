/**   
 * @Title: PermissionUtil.java 
 * @Package com.xtt.txgl.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月13日 上午10:35:19 
 *
 */
package com.xtt.common.util.permission;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xtt.common.dao.model.SysObj;
import com.xtt.common.util.user.UserUtil;

public class PermissionUtil {
	private static IPermissionFactory permissionFactory = new PermissionByMemory();

	/**
	 * 根据url校验是否有权限
	 * 
	 * @Title: checkPermissionByUrl
	 * @param url
	 * @return
	 *
	 */
	public static boolean checkPermissionByUrl(String url) {
		List<SysObj> list = permissionFactory.getNonPermissionList(convertRoleToArray(UserUtil.getLoginUser().getRoleId()));
		for (SysObj s : list) {
			if (StringUtils.isNotBlank(s.getUrl())) {
				for (String str : s.getUrl().split(",")) {
					if (str.equals(url)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 根据key获取权限对象
	 * 
	 * @Title: getPermissionByKey
	 * @param key
	 * @return
	 *
	 */
	public static SysObj getPermissionByKey(String key) {
		List<SysObj> list = permissionFactory.getNonPermissionList(convertRoleToArray(UserUtil.getLoginUser().getRoleId()));
		for (SysObj s : list) {
			if (s.getKey().equals(key)) {
				return s;
			}
		}
		return null;
	}

	private static Long[] convertRoleToArray(String roleId) {
		Long[] roleIds = null;
		if (StringUtils.isNotEmpty(roleId)) {
			String[] tempRoleIds = roleId.split(",");
			roleIds = new Long[tempRoleIds.length];
			for (int i = 0; i < tempRoleIds.length; i++) {
				roleIds[i] = Long.valueOf(tempRoleIds[i]);
			}
		}
		return roleIds;
	}
}
