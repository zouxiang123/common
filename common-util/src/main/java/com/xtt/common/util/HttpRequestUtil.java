/**   
 * @Title: HttpRequestUtil.java 
 * @Package com.xtt.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年10月31日 下午5:51:51 
 *
 */
package com.xtt.common.util;

import java.util.Map;

import org.apache.http.client.CookieStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.constants.CommonConstants;
import com.xtt.platform.util.http.HttpClientResultUtil;
import com.xtt.platform.util.http.HttpClientUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.io.JsonUtil;
import com.xtt.platform.util.lang.StringUtil;

public class HttpRequestUtil extends HttpClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

    /**
     * 获取postJson请求结果
     * 
     * @Title: getPostJsonResult
     * @param url
     * @param param
     * @param cookieUrl
     * @return
     *
     */
    public static HttpResult getPostJsonResult(String url, Object param, String cookieUrl) {
        HttpResult result = null;
        JsonUtil jsonUtil = JsonUtil.AllJsonUtil();
        CookieStore cookie = null;
        if (StringUtil.isNoneBlank(cookieUrl)) {
            cookie = HttpServletUtil.getHttpClientCookieStore(CommonConstants.COMMON_SERVER_ADDR);
        }
        HttpClientResultUtil httpResult = HttpClientUtil.postJson(url, jsonUtil.toJson(param), cookie);
        if (StringUtil.isEmpty(httpResult.getExceptionMessage())) {
            if (httpResult.getStatus() == 200) {
                result = jsonUtil.fromJson(httpResult.getContext(), HttpResult.class);
            } else {
                LOGGER.error("getPostJson result from url 【{}】 failed,result status is {}", url, httpResult.getStatus());
            }
        } else {
            LOGGER.error("getPostJson result from url 【{}】 failed, case by {}", url, httpResult.getExceptionMessage());
        }
        return result;
    }

    /**
     * 获取post请求结果
     * 
     * @Title: getPostResult
     * @param url
     * @param param
     * @param cookieUrl
     * @return
     *
     */
    public static HttpResult getPostResult(String url, Map<String, String> param, String cookieUrl) {
        HttpResult result = null;
        CookieStore cookie = null;
        if (StringUtil.isNoneBlank(cookieUrl)) {
            cookie = HttpServletUtil.getHttpClientCookieStore(CommonConstants.COMMON_SERVER_ADDR);
        }
        HttpClientResultUtil httpResult = HttpClientUtil.post(url, param, cookie);
        if (StringUtil.isEmpty(httpResult.getExceptionMessage())) {
            if (httpResult.getStatus() == 200) {
                result = JsonUtil.AllJsonUtil().fromJson(httpResult.getContext(), HttpResult.class);
            } else {
                LOGGER.error("getPost result from url 【{}】 failed,result status is {}", url, httpResult.getStatus());
            }
        } else {
            LOGGER.error("getPost result from url 【{}】 failed, case by {}", url, httpResult.getExceptionMessage());
        }
        return result;
    }
}
