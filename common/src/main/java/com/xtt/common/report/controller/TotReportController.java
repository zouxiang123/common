/**   
 * @Title: TotReportController.java 
 * @Package com.xtt.common.report.controller
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年8月22日 下午5:09:08 
 *
 */
package com.xtt.common.report.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.report.service.IPatientOutcomeReportService;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/report/tot/")
public class TotReportController {
    @Autowired
    private IPatientOutcomeReportService patientOutcomeReportService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("cm/report/tot_report");
        Calendar c = Calendar.getInstance();
        model.addObject("currentYear", c.get(Calendar.YEAR));
        return model;
    }

    @RequestMapping("getTotList")
    @ResponseBody
    public HttpResult getTotList(String yearStr) {
        HttpResult rs = HttpResult.getSuccessInstance();
        rs.setRs(patientOutcomeReportService.listTotByYear(yearStr));
        return rs;

    }

}
