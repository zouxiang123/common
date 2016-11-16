/**   
 * @Title: UserUtilContext.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月16日 下午4:47:49 
 *
 */
package com.xtt.common.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dto.SysObjDto;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.util.ContextAuthUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.PermissionUtil;
import com.xtt.platform.util.io.JsonUtil;

public class UserUtilContext {
	private UserUtilContext() {
	}

	private static final ThreadLocal<LoginUser> threadLocalLoginUser = new ThreadLocal<LoginUser>();

	public static void setLoginUser(String token, LoginUser loginUser) {
		// 同一帐号后登陆踢除上次登陆状态
		String hisToken = ContextAuthUtil.getTokenByAccount(loginUser.getAccount());
		if (hisToken != null) {
			ContextAuthUtil.delAuth(hisToken);
		}
		ContextAuthUtil.setAccount2Token(loginUser.getAccount(), token);
		HashMap<String, Object> auth = new HashMap<String, Object>();
		auth.put(CommonConstants.LOGIN_USER, loginUser);
		ContextAuthUtil.addAuth(token, auth);
	}

	/**
	 * 
	 * @Title: setLoginUser 登录时存放当前登陆者对象
	 * @param loginUser
	 * 
	 */
	public static void setLoginUser(LoginUser loginUser) {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		if (auth != null) {
			auth.put(CommonConstants.LOGIN_USER, loginUser);
			ContextAuthUtil.refreshAuth(auth);
		} else {
			HttpSession session = HttpServletUtil.getSession();
			if (session != null) {
				auth = new HashMap<String, Object>();
				auth.put(CommonConstants.LOGIN_USER, loginUser);
				ContextAuthUtil.addAuth(session.getId(), auth);
			}
		}
	}

	/**
	 * 获取当前登录者对象
	 * 
	 * @Title: getLoginUser
	 * @return
	 * 
	 */
	public static LoginUser getLoginUser() {
		// first:get form threadLocal
		LoginUser loginUser = threadLocalLoginUser.get();
		if (loginUser == null) {
			// first:get form redis
			Map<String, Object> auth = ContextAuthUtil.getAuth();
			if (auth != null) {
				loginUser = (LoginUser) auth.get(CommonConstants.LOGIN_USER);
			}
		}
		return loginUser;
	}

	/**
	 * 获取当前登陆者id
	 * 
	 * @Title: getLoginUserId
	 * 
	 */
	public static Long getLoginUserId() {
		LoginUser loginUser = getLoginUser();
		return loginUser.getId();
	}

	/**
	 * 获取当前登录者名称
	 * 
	 * @Title: getLoginUserName
	 * 
	 */
	public static String getLoginUserName() {
		LoginUser loginUser = getLoginUser();
		return loginUser.getName();
	}

	public static void setPermission(Long[] roleIds) {
		if (roleIds == null) {
			ContextAuthUtil.putAuth(CommonConstants.USER_PERMISSION, null);
		} else {
			ContextAuthUtil.putAuth(CommonConstants.USER_PERMISSION, covertCmSysObjList(PermissionUtil.getPermissionList(roleIds)));
		}
	}

	public static void setNonPermissionList(Long[] roleIds) {
		if (roleIds == null) {
			ContextAuthUtil.putAuth(CommonConstants.USER_NON_PERMISSION, null);
		} else {
			ContextAuthUtil.putAuth(CommonConstants.USER_NON_PERMISSION, covertCmSysObjList(PermissionUtil.getNonPermissionList(roleIds)));
		}
	}

	public static void setApiPermissionList(Long[] roleIds) {
		if (roleIds == null) {
			ContextAuthUtil.putAuth(CommonConstants.API_PERMISSION, null);
		} else {
			ContextAuthUtil.putAuth(CommonConstants.API_PERMISSION, covertCmSysObjList(PermissionUtil.getApiPermissionList(roleIds)));
		}
	}

	public static String getNonPermissionList() {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		return (String) auth.get(CommonConstants.USER_NON_PERMISSION);
	}

	public static String getApiPermissionList() {
		Map<String, Object> auth = ContextAuthUtil.getAuth();
		return (String) auth.get(CommonConstants.API_PERMISSION);
	}

	private static String covertCmSysObjList(List<SysObjDto> list) {
		if (list != null && !list.isEmpty()) {
			return JsonUtil.AllJsonUtil().toJson(list);
		}
		return null;
	}

	public static void setThreadTenant(Integer id) {
		LoginUser user = new LoginUser();
		user.setId(CommonConstants.SYSTEM_USER_ID);
		user.setTenantId(id);
		threadLocalLoginUser.set(user);
	}

	public static void setThreadLoginUser(LoginUser user) {
		threadLocalLoginUser.set(user);
	}

}
