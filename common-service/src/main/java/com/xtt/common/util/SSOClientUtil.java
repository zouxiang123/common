package com.xtt.common.util;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * 单点登录操作
 * 
 * @author wolf-yansl
 * 
 */
public class SSOClientUtil {

	/** cookie 保存token值 */
	public static final String TOKEN_CODE_KEY = "user_key";

	/** 客户端应用ID */
	public static final String CLIENT_ID_KEY = "client_id_key";
	/* 密文 */
	public static final String CLIENT_SERCRET_KEY = "client_secret_key";
	/** 用户账号key */
	public static final String ACCOUNT_NO_KEY = "user";

	/**
	 * cookie 域范围
	 */
	public static final String COOKIE_DOMAIN = "www.xtt.com";

	/**
	 * 获取sessionID
	 * 
	 * @param request
	 * @return
	 */
	public static String getSessionId(HttpServletRequest request) {
		return request.getSession().getId();
	}

	public static void setSessionAttrbute(HttpServletRequest request, String key, Object obj) {
		request.getSession().setAttribute(key, obj);
	}

	public static Object getSessionAttrbute(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

	public static void removeSessionAttrbute(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	/**
	 * 获取登录凭证
	 * 
	 * @param request
	 * @return
	 */
	public static String getTokenCode(HttpServletRequest request) {
		return getCookieValue(request, TOKEN_CODE_KEY);
	}

	/**
	 * 获取登录账号
	 * 
	 * @param request
	 * @return
	 */
	public static String getAccountNo(HttpServletRequest request) {
		return getCookieValue(request, ACCOUNT_NO_KEY);
	}

	/**
	 * 获取应用ID
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientId(HttpServletRequest request) {
		return getCookieValue(request, CLIENT_ID_KEY);
	}

	/**
	 * 获取密文
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientSecret(HttpServletRequest request) {
		return getCookieValue(request, CLIENT_SERCRET_KEY);
	}

	/**
	 * 获取cookie内容
	 * 
	 * @param request
	 * @param paramCookieName
	 *            cookie键
	 * @return
	 */
	private static String getCookieValue(HttpServletRequest request, String paramCookieName) {
		String tokenCode = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if (StringUtils.isNotBlank(cookieName) && cookieName.equals(paramCookieName)) {
					tokenCode = cookie.getValue();
				}
			}
		}
		return tokenCode;
	}

	/**
	 * 获取客户端应用地址 http://localhost:8081/lt-frontend
	 * 
	 * @return
	 */
	public static String getClientURI(HttpServletRequest request) {
		StringBuffer uri = new StringBuffer();
		uri.append(request.getScheme());
		uri.append("://");
		uri.append(request.getServerName());
		uri.append(":");
		uri.append(request.getServerPort());
		uri.append(request.getContextPath());
		return uri.toString();
	}

	/**
	 * 验证请求路径是否通过
	 * 
	 * @return true 成功 false 失败
	 */
	public static boolean isVerifyRequestURI(List<String> excludePaths, HttpServletRequest request) {
		boolean requestVerify = false;
		if (excludePaths != null && excludePaths.size() > 0) {
			StringBuffer requestURI = request.getRequestURL();
			for (String excludePath : excludePaths) {
				if (requestURI.indexOf(excludePath) >= 0) {
					requestVerify = true;
					break;
				}
			}
		}
		return requestVerify;
	}

	/***
	 * 设置客户端 cookei内容
	 * 
	 * @param response
	 * @param cookieKey
	 *            cookie键
	 * @param cookieValue
	 *            cooki值
	 * @param invalidTime
	 *            cookie生命周期
	 */
	public static void setCookie(HttpServletResponse response, String cookieKey, String cookieValue, int invalidTime) {

		Cookie tokenCookie = new Cookie(cookieKey, cookieValue);
		tokenCookie.setMaxAge(invalidTime * 60);
		tokenCookie.setPath("/");
		tokenCookie.setDomain(COOKIE_DOMAIN);
		response.addCookie(tokenCookie);

	}

	/**
	 * 清楚当前用户所有信息
	 */
	public static void clearUser(HttpServletResponse response, HttpServletRequest request) {
		/* request.getSession().invalidate(); */
		SSOClientUtil.removeSessionAttrbute(request, SSOClientUtil.ACCOUNT_NO_KEY);
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				cookie.setDomain(COOKIE_DOMAIN);
				String cookieName = cookie.getName();
				if (StringUtils.isNotBlank(cookieName) && cookieName.equals("JSESSIONID")) {
					continue;
				}
				response.addCookie(cookie);
			}
		}
	}
}
