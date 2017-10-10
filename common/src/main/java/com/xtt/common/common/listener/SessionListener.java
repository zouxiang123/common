package com.xtt.common.common.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xtt.common.util.ContextAuthUtil;

public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ContextAuthUtil.addAuth(httpSessionEvent.getSession().getId(), new HashMap<String, Object>());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ContextAuthUtil.delAuth(httpSessionEvent.getSession().getId());
    }
}