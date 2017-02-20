/**   
 * @Title: ContextLoaderListener.java 
 * @Package com.xtt.txgl.common.listener
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月7日 上午11:38:21 
 *
 */
package com.xtt.common.common.init;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.platform.util.DBUtil;
import com.xtt.platform.util.config.SpringUtil;

public class DataInit extends HttpServlet {
    private static final long serialVersionUID = -8524264697965072535L;
    private ICommonCacheService commonCacheService = SpringUtil.getBean("commonCacheServiceImpl", ICommonCacheService.class);

    public void init() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        // 设置全局版本号
        String version = "1.0.0";
        try {
            version = (String) DBUtil.getSingle("SELECT param_value FROM sys_param where param_name = 'sysVersion'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        servletContext.setAttribute("version", version);
        commonCacheService.cacheAll();
    }

}
