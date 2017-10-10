/**   
 * @Title: SystemMonitorController.java 
 * @Package com.xtt.common.monitor.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月23日 下午5:15:42 
 *
 */
package com.xtt.common.monitor.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.Feedback;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;

@RequestMapping("/monitor")
@Controller
public class SystemMonitorController {
    @Autowired
    ICommonService commonService;

    /**
     * 关于
     * 
     * @Title: about
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("view")
    public ModelAndView view() throws Exception {
        ModelAndView model = new ModelAndView("system/system_monitor");
        return model;
    }

    /**
     * 意见反馈
     * 
     * @Title: feedback
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("feedback")
    public ModelAndView feedback() throws Exception {
        ModelAndView model = new ModelAndView("home/feedback");
        return model;
    }

    /**
     * 意见反馈
     * 
     * @Title: feedback
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveFeedback")
    @ResponseBody
    public String saveFeedback(HttpServletRequest request, Feedback feedback) throws Exception {
        if (feedback.getContent() != null && feedback.getContent().length() > 512) {
            feedback.setContent(feedback.getContent().substring(0, 512));
        }
        feedback.setUserAgent(request.getHeader("user-agent"));
        feedback.setIp(HttpServletUtil.getIpAddr(request));
        feedback.setCreateUserId(UserUtil.getLoginUserId());
        feedback.setCreateTime(new Date());
        feedback.setFkTenantId(UserUtil.getTenantId());
        commonService.saveFeedback(feedback);
        return CommonConstants.SUCCESS;
    }

}
