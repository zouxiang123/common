/**   
 * @Title: UserUtilByHttpSession.java 实现用户登录信息相关接口
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: 陈光浩   
 * @date: 2015年9月16日 上午9:27:16 
 *
 */
package com.xtt.common.common.permission;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.xtt.common.common.constants.CommonConstants;
import com.xtt.common.common.util.ContextAuthUtil;

public class UserUtilByMemory implements UserUtilFactory {

	private static final ThreadLocal<LoginUser> threadLocalLoginUser = new ThreadLocal<LoginUser>();

	@Override
	public void setLoginUser(String token, LoginUser loginUser) {
		HashMap<String, Object> auth = (HashMap<String, Object>) ContextAuthUtil.getAuth();
		// 同一帐号后登陆踢除上次登陆状态
		if (auth == null) {
			auth = new HashMap<String, Object>();
			auth.put(CommonConstants.LOGIN_USER, loginUser);
			ContextAuthUtil.addAuth(token, auth);
		} else {
			auth.put(CommonConstants.LOGIN_USER, loginUser);
		}
	}

	@Override
	public void setLoginUser(LoginUser loginUser) {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		auth.put(CommonConstants.LOGIN_USER, loginUser);
	}

	@Override
	public LoginUser getLoginUser() {
		LoginUser loginUser = null;
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		if (auth != null) {
			loginUser = (LoginUser) auth.get(CommonConstants.LOGIN_USER);
		}
		if (auth == null || loginUser == null) {
			return threadLocalLoginUser.get();
		}
		return loginUser;
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
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		auth.put(CommonConstants.USER_PERMISSION, permission);
	}

	@Override
	public void setNonPermissionList(JsonArray array) {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		auth.put(CommonConstants.USER_NON_PERMISSION, array);
	}

	@Override
	public void setApiPermissionList(JsonArray array) {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		auth.put(CommonConstants.API_PERMISSION, array);
	}

	@Override
	public JsonArray getNonPermissionList() {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		return (JsonArray) auth.get(CommonConstants.USER_NON_PERMISSION);
	}

	@Override
	public JsonArray getApiPermissionList() {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		return (JsonArray) auth.get(CommonConstants.API_PERMISSION);
	}

	@Override
	public void setThreadLoginUser(LoginUser user) {
		threadLocalLoginUser.set(user);
	}
}
