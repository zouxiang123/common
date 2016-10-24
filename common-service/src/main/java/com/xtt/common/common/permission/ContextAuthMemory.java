package com.xtt.common.common.permission;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xtt.common.common.constants.CommonConstants;
import com.xtt.platform.util.lang.StringUtil;

public class ContextAuthMemory implements ContextAuthFactory {
	private static ContextAuthMemory instance;
	private ConcurrentHashMap<String, Map<String, Object>> authMap;
	private ConcurrentHashMap<String, String> userTokenMap;

	public ContextAuthMemory() {
		authMap = new ConcurrentHashMap<String, Map<String, Object>>();
		userTokenMap = new ConcurrentHashMap<String, String>();
	}

	public static ContextAuthMemory getInstance() {
		if (instance == null) {
			instance = new ContextAuthMemory();
		}
		return instance;
	}

	@Override
	public void addAuth(String key, Map<String, Object> auth) {
		if (auth == null || StringUtil.isBlank(key))
			return;
		authMap.put(key, auth);
	}

	@Override
	public void delAuth(String key) {
		if (key != null) {
			authMap.remove(key);
		}
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (servletRequestAttributes != null) {
			Map<String, Object> map = authMap.get(servletRequestAttributes.getRequest().getSession().getId());
			if (map != null) {
				map.clear();
			}
		}
	}

	@Override
	public Map<String, Object> getAuth() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			if (request != null) {// token level is highest
				String token = (String) request.getParameter(CommonConstants.API_TOKEN);
				if (token != null) {// get from token
					return authMap.get(token);
				} else {// get from session
					return authMap.get(request.getSession().getId());
				}
			}
		}
		return null;
	}

	public void setAccount2Token(String account, String token) {
		userTokenMap.put(account, token);
	}

	public String getTokenByAccount(String account) {
		return userTokenMap.get(account);
	}

	@Override
	public void refreshLiveTime(String key, Long liveTime) {
		// TODO Auto-generated method stub

	}

}