package com.xtt.common.home.controller;

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

@Controller
@RequestMapping
public class HomeController {
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
    @RequestMapping("about")
    public ModelAndView about() throws Exception {
        ModelAndView model = new ModelAndView("home/about_us");
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
