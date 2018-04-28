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
import com.xtt.platform.util.lang.StringUtil;

public class BusinessContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        ServletContext application = servletContext.getServletContext();
        String configFileName = application.getInitParameter("configFileName");
        if (StringUtil.isBlank(configFileName)) {
            throw new RuntimeException("******************** can not find config file **************");
        }
        // set log4j config path
        application.setInitParameter("log4jConfigLocation", "file:" + CommonConstants.BASE_PATH + "/config/log4j.properties");
        String configPath = CommonConstants.BASE_PATH.concat("/config/").concat(configFileName);
        application.setInitParameter("CONFIG_PATH", configPath);
        System.out.println(configPath);
        // 初始化请求地址相关参数
        CommonConstants.initUrlParam(configPath);
        // 添加通用服务地址
        application.setInitParameter("COMMON_SERVER_ADDR", CommonConstants.COMMON_SERVER_ADDR);
        System.out.println(CommonConstants.COMMON_SERVER_ADDR);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // TODO Auto-generated method stub
    }

}
