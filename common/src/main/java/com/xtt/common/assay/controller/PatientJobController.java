/**   
 * @Title: PatientJobController.java 
 * @Package com.xtt.txgl.door
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年5月23日 下午4:28:20 
 *
 */
package com.xtt.common.assay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.report.service.IPatientAssayNewstService;
import com.xtt.common.report.service.IReportExceptionService;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;

/**
 * 手动执行患者相关调用类
 * 
 * @ClassName: PatientJobController
 * @date: 2017年5月23日 下午4:30:08
 * @version: V1.0
 *
 */
@Controller
@RequestMapping("door")
public class PatientJobController {
    @Autowired
    private IPatientAssayNewstService patientAssayNewstService;
    @Autowired
    private IReportExceptionService reportExceptionService;

    /**
     * 手动更新逾期项化验数据内容
     * 
     * @Title: patientAssayNewst
     * @return
     */
    @RequestMapping("patientAssayNewst")
    @ResponseBody
    public String patientAssayNewst() {
        patientAssayNewstService.insertAuto(UserUtil.getTenantId());
        return "success";
    }

    /**
     * 用于手动调用异常提醒_检验结果
     * 
     * @Title: assay
     * 
     * @param tenantId
     *            租户ID
     */
    @RequestMapping("assay")
    @ResponseBody
    public HttpResult assay(@RequestParam("tenantId") Integer tenantId) {
        HttpResult result = new HttpResult();
        reportExceptionService.executeAssayResultByTenantId(tenantId);
        return result;
    }

}
