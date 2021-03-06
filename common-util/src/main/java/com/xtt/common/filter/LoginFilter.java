package com.xtt.common.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

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
    private final List<String> excludePathList = Collections.synchronizedList(new ArrayList<String>());
    private final List<String> excludeFileList = Collections.synchronizedList(new ArrayList<String>());
    private boolean needRedirect = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String loadExcludePaths = filterConfig.getInitParameter("excludePath");
        if (StringUtils.isNotBlank(loadExcludePaths)) {
            arrayConvertList(loadExcludePaths, excludePathList);
        }

        String loadexcludeFiles = filterConfig.getInitParameter("excludeFile");
        if (StringUtil.isNotBlank(loadexcludeFiles)) {
            arrayConvertList(loadexcludeFiles, excludeFileList);
        }
        String needRedirectStr = filterConfig.getInitParameter("needRedirect");
        if (StringUtil.isNotBlank(needRedirectStr)) {
            needRedirect = "true".equals(needRedirectStr);
        }
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
        if (SSOClientUtil.isVerifyRequestFile(excludeFileList, request) || SSOClientUtil.isVerifyRequestURL(excludePathList, request)) {
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
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(CommonConstants.API_STATUS, CommonConstants.FAILURE);
            map.put(CommonConstants.API_ERROR_MESSAGE, "invalidate token");
            response.getWriter().print(CommonUtil.object2Json(map));
            return;
        }
        String contextPath = request.getContextPath();
        String serverUrl = CommonConstants.BASE_URL.concat(contextPath.startsWith("/") ? contextPath.substring(1) : contextPath);
        String url = serverUrl.concat(request.getServletPath());
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
                url = URLEncoder.encode(url.concat(paramStr), "UTF-8");
                response.sendRedirect(serverUrl.concat("/login.shtml?redirectUrl=").concat(url));
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

        long runTime = System.currentTimeMillis() - startTime;
        if (runTime > 300) {
            LOGGER.warn("********[{} request][ {} ]运行时长:[{} s][{} ms]********", HttpServletUtil.isAjaxRequest(request) ? "ajax" : "", url,
                            runTime / 1000, runTime);
        }
    }

    @Override
    public void destroy() {
        excludePathList.clear();
        excludeFileList.clear();
        needRedirect = false;
    }

    /**
     * 数组转集合
     * 
     * @param arrays
     * @param addlist
     */
    private void arrayConvertList(String arrays, List<String> addlist) {
        String[] analyArrays = arrays.split(",");
        for (String path : analyArrays) {
            addlist.add(path);
        }
    }

    private boolean isLogin(HttpServletRequest request, Map<String, Object> authMap) {
        if (authMap == null || authMap.get(CommonConstants.LOGIN_USER) == null) {
            return false;
        }
        LoginUser user = (LoginUser) authMap.get(CommonConstants.LOGIN_USER);
        String sysName = HttpServletUtil.getSysOwner();
        // 判断是否是登录本系统
        if (sysName != null) {
            return Objects.equals(sysName, user.getSysOwner());
        }
        return true;
    }
}
