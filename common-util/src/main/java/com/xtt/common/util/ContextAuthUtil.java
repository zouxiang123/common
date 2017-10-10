/**   
 * @Title: Snippet.java 
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月19日 上午8:46:37 
 *
 */
package com.xtt.common.util;

import java.util.Map;

import com.xtt.common.permission.ContextAuthCache;
import com.xtt.common.permission.ContextAuthFactory;

public class ContextAuthUtil {
    private static ContextAuthFactory factory = new ContextAuthCache();

    public static void putAuth(String key, Object value) {
        Map<String, Object> auth = factory.getAuth(null);
        auth.put(key, value);
        refreshAuth(auth);
    }

    public static void refreshLiveTime() {
        factory.refreshLiveTime(null, null);
    }

    public static void addAuth(String key, Map<String, Object> auth) {
        factory.addAuth(key, auth);
    }

    public static void delAuth() {
        factory.delAuth(null);
    }

    public static void delAuth(String sessionId) {
        factory.delAuth(sessionId);
    }

    public static Map<String, Object> getAuth() {
        return factory.getAuth(null);
    }

    public static void setAccount2Token(String account, String token) {
        factory.setAccount2Token(account, token);
    }

    public static String getTokenByAccount(String account) {
        return factory.getTokenByAccount(account);
    }

    public static void refreshAuth(Map<String, Object> auth) {
        factory.addAuth(null, auth);
    }

    public static Map<String, Object> getAuth(String token) {
        return factory.getAuth(token);
    }

}
