package com.xtt.common.common.permission;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xtt.common.common.constants.CommonConstants;
import com.xtt.common.common.util.HttpServletUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.config.PropertiesUtil;
import com.xtt.platform.util.lang.StringUtil;

public class ContextAuthCache implements ContextAuthFactory {
	private static ContextAuthCache instance;
	private static final int REDIS_DB = 8;
	private static final long LIVE_TIME = 2 * 60 * 60 * 1000;
	private static final String TOKEN_STRATEGY = (String) PropertiesUtil.getContextProperty("tokenStrategy");

	public ContextAuthCache() {

	}

	/** 是否使用cookie token策略 */
	private boolean isCookieTokenStrategy() {
		return CommonConstants.COOKIE_TOKEN.equals(TOKEN_STRATEGY) ? true : false;
	}

	/** 根据策略获取key */
	private String getKey(HttpServletRequest request) {
		if (isCookieTokenStrategy()) {// if use cookie token strategy
			return (String) request.getAttribute(CommonConstants.COOKIE_TOKEN);
		} else {// get form api token strategy
			return (String) request.getParameter(CommonConstants.API_TOKEN);
		}
	}

	public static ContextAuthCache getInstance() {
		if (instance == null) {
			instance = new ContextAuthCache();
		}
		return instance;
	}

	@Override
	public void addAuth(String key, Map<String, Object> auth) {
		if (auth == null)
			return;
		if (key == null) {
			HttpServletRequest request = HttpServletUtil.getRequest();
			if (request != null) {
				key = getKey(request);
			}
		}
		if (StringUtil.isNotBlank(key)) {
			if (isCookieTokenStrategy()) {
				RedisCacheUtil.setObject(key, auth, REDIS_DB, LIVE_TIME);
			} else {// api token need not set live time
				RedisCacheUtil.setObjectWithDB(key, auth, REDIS_DB);
			}
		}
	}

	@Override
	public void delAuth(String key) {
		if (StringUtil.isBlank(key)) {
			HttpServletRequest request = HttpServletUtil.getRequest();
			if (request != null) {
				key = getKey(request);
			}
		}
		if (StringUtil.isNotBlank(key))
			RedisCacheUtil.deleteWithDB(key, REDIS_DB);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAuth() {
		HttpServletRequest request = HttpServletUtil.getRequest();
		if (request != null) {
			String key = getKey(request);
			return StringUtil.isBlank(key) ? null : (Map<String, Object>) RedisCacheUtil.getObject(key, REDIS_DB);
		}
		return null;
	}

	public void setAccount2Token(String account, String token) {
		RedisCacheUtil.setObject(account, token);
	}

	public String getTokenByAccount(String account) {
		return RedisCacheUtil.getString(account);
	}

	@Override
	public void refreshLiveTime(String key, Long liveTime) {
		if (!isCookieTokenStrategy()) {// just from cookie_token request need refresh
			return;
		}
		if (StringUtil.isBlank(key) && HttpServletUtil.getRequest() != null) {
			key = getKey(HttpServletUtil.getRequest());
		}
		if (StringUtil.isNotBlank(key))
			RedisCacheUtil.setLiveTime(key, liveTime == null ? LIVE_TIME : liveTime);
	}
}