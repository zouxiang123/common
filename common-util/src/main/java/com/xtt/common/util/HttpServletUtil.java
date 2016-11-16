/**   
 * @Title: HttpServletUtil.java 封装获取HttpServlet相关的方法，如获取request、session等
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月16日 上午9:43:49 
 *
 */
package com.xtt.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xtt.common.constants.CommonConstants;
import com.xtt.platform.util.lang.StringUtil;

public class HttpServletUtil {

	private HttpServletUtil() {

	}

	/**
	 * 
	 * @Title: getRequest 获取HttpServletRequest对象
	 * @return
	 * 
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (servletRequestAttributes == null) {
			return null;
		}
		HttpServletRequest request = servletRequestAttributes.getRequest();
		return request;
	}

	public static boolean isFromPC() {
		String userAgent = getRequest().getHeader("user-agent");
		if (userAgent.indexOf("Windows NT") > -1 || userAgent.indexOf("Macintosh") > -1) {
			return true;
		}
		return false;
	}

	/**
	 * get session object
	 * 
	 * @Title: getSession
	 * @return
	 *
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		if (request != null) {
			return request.getSession();
		}
		return null;
	}

	/**
	 * 获取当前网络ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * 根据名字获取cookie值
	 * 
	 * @param request
	 *             
	 * @param name
	 *            cookie名字  
	 * @return  
	 */
	public static String getCookieValueByName(String name) {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return null;
		}
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * 根据名字获取cookie  
	 * 
	 * @param request
	 *             
	 * @param name
	 *            cookie名字  
	 * @return  
	 */
	public static Cookie getCookieByName(String name) {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return null;
		}
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 *             
	 * @return  
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	public static String getToken() {
		return getRequest().getParameter(CommonConstants.API_TOKEN);
	}

	public static String getProjectName() {
		String projectName = null;
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		projectName = (String) servletContext.getAttribute(CommonConstants.PROJECT_NAME);
		if (StringUtil.isBlank(projectName)) {// 如果从上下文中获取不到项目名称，则从请求中获取
			HttpServletRequest request = getRequest();
			if (request != null) {
				projectName = (String) request.getAttribute(CommonConstants.PROJECT_NAME);
			}
		}
		return projectName;
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
}
