/**   
 * @Title: ExceptionRemindController.java 
 * @Package com.xtt.txgl.report.controller
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年6月8日 下午1:47:15 
 *
 */
package com.xtt.common.assay.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.dao.po.ReportExAssayPo;
import com.xtt.common.report.service.IReportExceptionService;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/report/ex")
public class ExceptionRemindController {

    @Autowired
    private IReportExceptionService reportExceptionService;

    /**
     * 跳转异常化验结果列表
     * 
     * @Title: view
     * @return
     *
     */
    @RequestMapping("viewAssay")
    public ModelAndView viewAssay() {
        ModelAndView model = new ModelAndView("cm/report/exception_assay");
        if (HttpServletUtil.isFromPC() && UserUtil.isDoctor()) {
            model.addObject("isFromPC", 1);
        } else {
            model.addObject("isFromPC", 0);
        }
        return model;
    }

    /**
     * 跳转异常化验结果列表
     * 
     * @Title: view
     * @return
     *
     */
    @RequestMapping("searchAssay")
    @ResponseBody
    public HttpResult searchAssay(ReportExAssayPo exAssay) {
        HttpResult result = new HttpResult();
        List<Map> aMaps = reportExceptionService.listExAssayByPatient(exAssay);
        result.setRs(aMaps);
        return result;
    }

    /**
     * 异常提醒_化验结果详情
     * 
     * @Title: exceptionAssayDetail
     * @param reportDateStart
     * @param reportDateEnd
     * @param fkPatientId
     * @param isTemp
     * @return
     *
     */
    @RequestMapping("exceptionAssayDetail")
    @ResponseBody
    public HttpResult exceptionAssayDetail(ReportExAssayPo exAssay) {
        HttpResult result = new HttpResult();
        result.setRs(reportExceptionService.listExAssayByPatientDeital(exAssay));
        return result;
    }
}
