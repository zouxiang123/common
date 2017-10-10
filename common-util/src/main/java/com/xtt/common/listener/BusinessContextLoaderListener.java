/**   
 * @Title: ContextLoaderListener.java 
 * @Package com.xtt.common.common.listener
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月7日 上午11:38:21 
 *
 */
package com.xtt.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xtt.common.constants.CommonConstants;

public class BusinessContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        ServletContext application = servletContext.getServletContext();
        // set log4j config path
        application.setInitParameter("log4jConfigLocation", "file:" + CommonConstants.BASE_PATH + "/config/log4j.properties");
        application.setInitParameter("BASE_PATH", CommonConstants.BASE_PATH);
        // System.setProperty("BASE_PATH", CommonConstants.BASE_PATH);
        application.setAttribute("COMMON_SERVER_ADDR", CommonConstants.COMMON_SERVER_ADDR);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
    }

}
