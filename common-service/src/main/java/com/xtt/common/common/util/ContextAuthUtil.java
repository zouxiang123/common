/**   
 * @Title: Snippet.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月19日 上午8:46:37 
 *
 */
package com.xtt.common.common.util;

import java.util.Map;

import com.xtt.common.common.constants.CommonConstants.MethodEnum;
import com.xtt.common.common.permission.ContextAuthCache;
import com.xtt.common.common.permission.ContextAuthFactory;
import com.xtt.common.common.permission.ContextAuthMemory;
import com.xtt.platform.util.config.PropertiesUtil;

public class ContextAuthUtil {
	private static ContextAuthFactory factory;
	static {
		String defaultSessionStrategy = (String) PropertiesUtil.getContextProperty("defaultSessionStrategy");
		if (MethodEnum.HTTPSESSION.name().equals(defaultSessionStrategy)) {
			factory = new ContextAuthMemory();
		} else if (MethodEnum.CACHE.name().equals(defaultSessionStrategy)) {
			factory = new ContextAuthCache();
		} else if (MethodEnum.MEMORY.name().equals(defaultSessionStrategy)) {
			factory = new ContextAuthMemory();
		} else {
			throw new RuntimeException("must select user strategy");
		}
	}

	public static void putAuth(String key, Object value) {
		Map<String, Object> auth = factory.getAuth();
		auth.put(key, value);
		refreshAuth(auth);
	}

	public static void refreshLiveTime() {
		factory.refreshLiveTime(null, null);
	}

	public static void addAuth(String key, Map<String, Object> auth) {
		factory.addAuth(key, auth);
	}

	public static void delAuth() {
		factory.delAuth(null);
	}

	public static void delAuth(String sessionId) {
		factory.delAuth(sessionId);
	}

	public static Map<String, Object> getAuth() {
		return factory.getAuth();
	}

	public static void setAccount2Token(String account, String token) {
		factory.setAccount2Token(account, token);
	}

	public static String getTokenByAccount(String account) {
		return factory.getTokenByAccount(account);
	}

	public static void refreshAuth(Map<String, Object> auth) {
		factory.addAuth(null, auth);
	}

}
