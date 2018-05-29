package com.xtt.common.util;

import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.xtt.platform.util.lang.StringUtil;

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
    public static boolean isVerifyRequestURL(Set<String> excludePathSet, HttpServletRequest request) {
        if (CollectionUtils.isNotEmpty(excludePathSet)) {
            String requestURI = request.getServletPath();
            if (StringUtil.isNotBlank(requestURI)) {
                if (StringUtil.isEmpty(requestURI) || requestURI.indexOf(".shtml") == -1) {// 地址无效时不做校验
                    return true;
                }
                requestURI = requestURI.substring(0, requestURI.indexOf(".shtml"));
                if (excludePathSet.contains(requestURI)) { // 优先匹配全路径地址的过滤
                    return true;
                }
                String[] uris = requestURI.split("/");
                for (int i = 0; i < uris.length; i++) {
                    if (StringUtil.isNotBlank(uris[i]) && excludePathSet.contains(uris[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 校验文件后缀请求是否通过
     * 
     * @Title: isVerifyRequestFile
     * @param excludeFileSet
     * @param request
     * @return true 成功 false 失败
     *
     */
    public static boolean isVerifyRequestFile(Set<String> excludeFileSet, HttpServletRequest request) {
        if (CollectionUtils.isNotEmpty(excludeFileSet)) {
            String requestURI = request.getServletPath();
            int index = requestURI.lastIndexOf(".");
            if (index > -1) {
                return excludeFileSet.contains(requestURI.substring(index));
            }
        }
        return false;
    }

    /**
     * 校验url是否包含路径
     * 
     * @return true 成功 false 失败
     */
    public static boolean verifyURLContainPath(String url, String path) {
        if (StringUtil.isNotBlank(url)) {
            if (StringUtil.isEmpty(url) || url.indexOf(".shtml") == -1) {// 地址无效时不做校验
                return true;
            }
            url = url.substring(1, url.indexOf(".shtml"));
            String[] uris = url.split("/");
            for (int i = 0; i < uris.length; i++) {
                if (StringUtil.isNotBlank(uris[i])) {
                    if (uris[i].equals(path))
                        return true;
                }
            }
        }
        return false;
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
