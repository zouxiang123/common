/**   
 * @Title: PermissionFactory.java 
 * @Package com.xtt.txgl.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月18日 上午10:51:16 
 *
 */
package com.xtt.common.util.permission;

import java.util.List;

import com.xtt.common.dao.model.SysObj;
import com.xtt.common.user.service.IRoleService;
import com.xtt.platform.util.config.SpringUtil;

public class PermissionByDataBase implements IPermissionFactory {

	private static IRoleService roleService = (IRoleService) SpringUtil.getBean("roleServiceImpl");

	@Override
	public List<SysObj> getPermissionList(Long[] roleIds) {
		String[] types = { "1", "2" };
		return roleService.getMenuListByRoleId(roleIds, types);
	}

	@Override
	public List<SysObj> getNonPermissionList(Long[] roleIds) {
		String[] types = { "1", "2" };
		return roleService.getNotChecked(roleIds, types);
	}

	@Override
	public List<SysObj> getApiPermissionList(Long[] roleIds) {
		String[] types = { "api" };
		return roleService.getNotChecked(roleIds, types);
	}

	@Override
	public List<SysObj> getApiNonPermissionList(Long[] roleIds) {
		String[] types = { "api" };
		return roleService.getNotChecked(roleIds, types);
	}

	@Override
	public void refresh() {
		// 不需要做任何事情
	}

}
