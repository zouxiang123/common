package com.xtt.common.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.xtt.common.util.constants.CommonConstants;

public class AppSessionContext {
	private static AppSessionContext instance;
	private HashMap<String, Object> sessionMap;

	private AppSessionContext() {
		sessionMap = new HashMap<String, Object>();
	}

	public static AppSessionContext getInstance() {
		if (instance == null) {
			instance = new AppSessionContext();
		}
		return instance;
	}

	public synchronized void addSession(HttpSession session) {
		if (session != null) {
			String token = (String) HttpServletUtil.getRequest().getParameter(CommonConstants.API_TOKEN);
			if (token == null) {
				sessionMap.put(session.getId(), session);
			} else {
				sessionMap.put(token, session);
			}
		}
	}

	public synchronized void delSession(HttpSession session) {
		if (session != null) {
			String token = (String) HttpServletUtil.getRequest().getParameter(CommonConstants.API_TOKEN);
			if (token == null) {
				sessionMap.remove(session.getId());
			} else {
				sessionMap.remove(token);
			}
		}
	}

	public synchronized HttpSession getSession(String token) {
		if (token == null)
			return null;
		return (HttpSession) sessionMap.get(token);
	}

}