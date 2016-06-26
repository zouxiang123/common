/**   
 * @Title: UserUtilContext.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月16日 下午4:47:49 
 *
 */
package com.xtt.common.util.user;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xtt.common.dao.model.SysObj;
import com.xtt.common.util.constants.CommonConstants;
import com.xtt.common.util.constants.CommonConstants.MethodEnum;
import com.xtt.common.util.permission.IPermissionFactory;
import com.xtt.common.util.permission.PermissionByDataBase;

public class UserUtilContext {
	private UserUtilContext() {
	}

	private static IUserUtilFactory userUtilFactory;
	private static IPermissionFactory permissionFactory;

	static {
		if (MethodEnum.HTTPSESSION.name().equals(CommonConstants.DEFUALT_SESSION_METHOD)) {
			userUtilFactory = new UserUtilByHttpSession();
			permissionFactory = new PermissionByDataBase();
		} else if (MethodEnum.CACHE.name().equals(CommonConstants.DEFUALT_SESSION_METHOD)) {

		} else {
			throw new RuntimeException("must select user strategy");
		}
	}

	/**
	 * 
	 * @Title: setLoginUser 登录时存放当前登陆者对象
	 * @param loginUser
	 * 
	 */
	public static void setLoginUser(LoginUser loginUser) {

		userUtilFactory.setLoginUser(loginUser);
	}

	/**
	 * 获取当前登录者对象
	 * 
	 * @Title: getLoginUser
	 * @return
	 * 
	 */
	public static LoginUser getLoginUser() {

		return userUtilFactory.getLoginUser();
	}

	/**
	 * 获取当前登陆者id
	 * 
	 * @Title: getLoginUserId
	 * 
	 */
	public static Long getLoginUserId() {

		return userUtilFactory.getLoginUserId();
	}

	/**
	 * 获取当前登录者名称
	 * 
	 * @Title: getLoginUserName
	 * 
	 */
	public static String getLoginUserName() {

		return userUtilFactory.getLoginUserName();
	}

	public static void setPermission(Long[] roleIds) {
		if (roleIds == null) {
			userUtilFactory.setPermission(null);
		} else {
			userUtilFactory.setPermission(covertListToJsonArray(permissionFactory.getPermissionList(roleIds)));
		}
	}

	public static void setNonPermissionList(Long[] roleIds) {
		if (roleIds == null) {
			userUtilFactory.setNonPermissionList(null);
		} else {
			userUtilFactory.setNonPermissionList(covertListToJsonArray(permissionFactory.getNonPermissionList(roleIds)));
		}
	}

	public static void setApiPermissionList(Long[] roleIds) {
		if (roleIds == null) {
			userUtilFactory.setApiPermissionList(null);
		} else {
			userUtilFactory.setApiPermissionList(covertListToJsonArray(permissionFactory.getNonPermissionList(roleIds)));
		}
	}

	public static JsonArray getNonPermissionList() {
		return userUtilFactory.getNonPermissionList();
	}

	public static JsonArray getApiPermissionList() {
		return userUtilFactory.getApiPermissionList();
	}

	private static JsonArray covertListToJsonArray(java.util.List<SysObj> list) {
		JsonArray array = new JsonArray();
		if (list != null && !list.isEmpty()) {
			for (SysObj sys : list) {
				JsonObject o = new JsonObject();
				o.addProperty("name", sys.getName());
				o.addProperty("key", sys.getKey());
				o.addProperty("url", sys.getUrl());
				o.addProperty("code", sys.getCode());
				o.addProperty("pCode", sys.getpCode());
				array.add(o);
			}
		}
		return array;
	}

	public static void setThreadTenant(Integer id) {
		userUtilFactory.setThreadTenant(id);
	}

}
