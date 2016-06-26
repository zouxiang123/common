/**   
 * @Title: UserUtilByHttpSession.java 实现用户登录信息相关接口
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: 陈光浩   
 * @date: 2015年9月16日 上午9:27:16 
 *
 */
package com.xtt.common.util.user;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.constants.CommonConstants;

public class UserUtilByHttpSession implements IUserUtilFactory {

	private static final ThreadLocal<Integer> tenantThreadLocal = new ThreadLocal<Integer>();

	@Override
	public void setLoginUser(LoginUser loginUser) {
		HttpSession session = HttpServletUtil.getSession();
		session.setAttribute(CommonConstants.LOGIN_USER, loginUser);

	}

	@Override
	public LoginUser getLoginUser() {
		HttpSession session = HttpServletUtil.getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute(CommonConstants.LOGIN_USER);
		if (loginUser == null) {
			loginUser = new LoginUser();
			loginUser.setId(CommonConstants.SYSTEM_USER_ID);
			loginUser.setTenantId(tenantThreadLocal.get());
			return loginUser;
		}
		return (LoginUser) session.getAttribute(CommonConstants.LOGIN_USER);
	}

	@Override
	public Long getLoginUserId() {
		LoginUser loginUser = getLoginUser();
		return loginUser.getId();
	}

	@Override
	public String getLoginUserName() {
		LoginUser loginUser = getLoginUser();
		return loginUser.getName();
	}

	@Override
	public void setPermission(JsonArray permission) {
		HttpSession session = HttpServletUtil.getSession();
		session.setAttribute(CommonConstants.USER_PERMISSION, permission);
	}

	@Override
	public void setNonPermissionList(JsonArray array) {
		HttpSession session = HttpServletUtil.getSession();
		session.setAttribute(CommonConstants.USER_NON_PERMISSION, array);
	}

	@Override
	public void setApiPermissionList(JsonArray array) {
		HttpSession session = HttpServletUtil.getSession();
		session.setAttribute(CommonConstants.API_PERMISSION, array);
	}

	@Override
	public JsonArray getNonPermissionList() {
		HttpSession session = HttpServletUtil.getSession();
		return (JsonArray) session.getAttribute(CommonConstants.USER_NON_PERMISSION);
	}

	@Override
	public JsonArray getApiPermissionList() {
		HttpSession session = HttpServletUtil.getSession();
		return (JsonArray) session.getAttribute(CommonConstants.API_PERMISSION);
	}

	@Override
	public void setThreadTenant(Integer id) {
		tenantThreadLocal.set(id);
	}
}
