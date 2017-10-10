package com.xtt.common.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.util.ContextAuthUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.PermissionUtil;
import com.xtt.common.util.SSOClientUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.CommonUtil;
import com.xtt.platform.util.lang.StringUtil;

public class LoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    /* 过滤地址 */
    /* 过滤地址 */
    private Set<String> excludePathSet = null;
    private Set<String> excludeFileSet = null;
    private boolean needRedirect = false;
    private String homePath = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String loadExcludePaths = filterConfig.getInitParameter("excludePath");
        if (StringUtils.isNotBlank(loadExcludePaths)) {
            excludePathSet = arrayConvertSet(loadExcludePaths);
        }

        String loadexcludeFiles = filterConfig.getInitParameter("excludeFile");
        if (StringUtil.isNotBlank(loadexcludeFiles)) {
            excludeFileSet = arrayConvertSet(loadexcludeFiles);
        }
        String needRedirectStr = filterConfig.getInitParameter("needRedirect");
        if (StringUtil.isNotBlank(needRedirectStr)) {
            needRedirect = "true".equals(needRedirectStr);
        }
        homePath = filterConfig.getInitParameter("homePath");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 添加过滤请求
        long startTime = System.currentTimeMillis();
        // clear local thread login user
        UserUtil.setThreadLoginUser(null);
        /** 校验是否特殊地址 排除[excludePath、excludeFile] */
        if (SSOClientUtil.isVerifyRequestFile(excludeFileSet, request) || SSOClientUtil.isVerifyRequestURL(excludePathSet, request)) {
            chain.doFilter(request, response);
            return;
        }

        String cookieValue = HttpServletUtil.getCookieValueByName(CommonConstants.COOKIE_TOKEN);
        cookieValue = StringUtil.isBlank(cookieValue) ? request.getParameter(CommonConstants.COOKIE_TOKEN) : cookieValue;
        if (StringUtil.isNotBlank(cookieValue)) {// cookie 中的token不为空时，将其放到请求中去
            req.setAttribute(CommonConstants.COOKIE_TOKEN, cookieValue);
        }
        // 移动端Token校验
        Map<String, Object> authMap = ContextAuthUtil.getAuth();
        if (request.getParameter(CommonConstants.API_TOKEN) != null && authMap == null) {
            Map<String, Object> map = new HashMap<>();
            map.put(CommonConstants.API_STATUS, CommonConstants.FAILURE);
            map.put(CommonConstants.API_ERROR_MESSAGE, "invalidate token");
            response.getWriter().print(CommonUtil.object2Json(map));
            return;
        }

        String url = CommonConstants.BASE_URL.concat(request.getContextPath());
        if (needRedirect) {
            // 校验是否为登入登出操作
            String goToUrl = null;
            if (SSOClientUtil.verifyURLContainPath(request.getServletPath(), "login")) {
                goToUrl = "login";
            } else if (SSOClientUtil.verifyURLContainPath(request.getServletPath(), "logout")) {
                goToUrl = "logout";
            }
            if (StringUtil.isNotBlank(goToUrl)) {
                if (StringUtil.isNotBlank(homePath)) {
                    url = url.concat("/").concat(homePath).concat(".shtml");
                }
                url = URLEncoder.encode(url, "UTF-8");
                response.sendRedirect(CommonConstants.COMMON_SERVER_ADDR.concat(goToUrl).concat(".shtml?redirectUrl=").concat(url)
                                .concat("&sysOwner=").concat(HttpServletUtil.getSysName()));
                return;
            }
        }
        url = url.concat(request.getServletPath());
        if (!isLogin(request, authMap)) {
            if (HttpServletUtil.isAjaxRequest(request)) {// if is ajax request return;
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            if (needRedirect) {
                String paramStr = "";
                Map<String, String[]> paramMap = req.getParameterMap();
                if (paramMap != null && !paramMap.isEmpty()) {
                    int index = 0;
                    for (Entry<String, String[]> entry : paramMap.entrySet()) {
                        if (entry.getValue() != null && entry.getValue().length > 0) {
                            for (int i = 0; i < entry.getValue().length; i++) {
                                paramStr += index == 0 ? "?" : "&";
                                paramStr += entry.getKey() + "=" + entry.getValue()[i];
                                index++;
                            }
                        }
                    }
                }
                url = URLEncoder.encode(url + paramStr, "UTF-8");
                response.sendRedirect(CommonConstants.COMMON_SERVER_ADDR.concat("login.shtml?redirectUrl=" + url).concat("&sysOwner=")
                                .concat(HttpServletUtil.getSysName()));
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
            return;
        }
        // set login user to thread local
        UserUtil.setThreadLoginUser((LoginUser) authMap.get(CommonConstants.LOGIN_USER));
        // 是登陆状态，刷新用户登录的生命周期
        ContextAuthUtil.refreshLiveTime();
        if (!PermissionUtil.hasPermission(request.getServletPath(), authMap)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);

        String ajax = (HttpServletUtil.isAjaxRequest(request) ? "是" : "否");
        long runTime = System.currentTimeMillis() - startTime;
        if (runTime > 300) {
            LOGGER.warn("********[异步请求:" + ajax + "][ " + url + " ]运行时长:[" + runTime / 1000 + "][" + runTime + " 毫秒]********");
        }

    }

    @Override
    public void destroy() {
        if (excludePathSet != null) {
            excludePathSet.clear();
        }
        if (excludeFileSet != null) {
            excludeFileSet.clear();
        }
        needRedirect = false;
        homePath = null;
    }

    /**
     * 数组转集合
     * 
     * @param arrays
     */
    private Set<String> arrayConvertSet(String arrays) {
        String[] analyArrays = arrays.split(",");
        Set<String> set = new HashSet<>(analyArrays.length);
        for (String path : analyArrays) {
            set.add(path);
        }
        return set;
    }

    private boolean isLogin(HttpServletRequest request, Map<String, Object> authMap) {
        if (authMap == null || authMap.get(CommonConstants.LOGIN_USER) == null) {
            return false;
        }
        return true;
    }
}
