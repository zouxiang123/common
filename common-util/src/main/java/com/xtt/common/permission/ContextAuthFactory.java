package com.xtt.common.permission;

import java.util.Map;

public interface ContextAuthFactory {

	public void addAuth(String key, Map<String, Object> auth);

	public void delAuth(String key);

	public Map<String, Object> getAuth();

	public void setAccount2Token(String account, String token);

	public String getTokenByAccount(String account);

	/**
	 * 刷新存活时间
	 * 
	 * @Title: refreshLiveTime
	 * @param key
	 * @param liveTime
	 *
	 */
	public void refreshLiveTime(String key, Long liveTime);
}