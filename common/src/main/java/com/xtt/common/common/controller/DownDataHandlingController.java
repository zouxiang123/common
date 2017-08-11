/**   
 * @Title: DownDataProcessController.java 
 * @Package com.xtt.common.common.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月3日 下午1:39:39 
 *
 */
package com.xtt.common.common.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.constants.IApiConst;
import com.xtt.common.dto.ApiCommQueryDto;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.report.controller.AssayReportController;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpClientUtil;

@Controller
@RequestMapping("/downDataHandling/")
public class DownDataHandlingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownDataHandlingController.class);

    @Autowired
    private IPatientService patientService;
    @Autowired
    private ICommonCacheService commonCacheService;
    @Autowired
    private AssayReportController assayReportController;

    /**
     * 数据下载完调用接口
     * 
     * @Title: handling
     * @param type（检验:lis、患者:patient、影像报告:pacs、医嘱:orders；）
     * @param tenantId
     * @return
     *
     */
    @RequestMapping("handling")
    @ResponseBody
    public Map<String, String> handling(ApiCommQueryDto po) {
        String parmType = po.getParmType();
        Integer tenantId = po.getTenantId();
        LOGGER.info("get request to handling {}", po.toString());
        Map<String, String> map = new HashMap<String, String>();
        new Thread(() -> {
            try {
                UserUtil.setThreadTenant(tenantId);
                if (Objects.equals(IApiConst.UPDATE_PT_TYPE, parmType)) {
                    patientService.updatePatientType(tenantId);
                    commonCacheService.cachePatient(tenantId);
                    // 调用随访自动处理数据
                    Map<String, String> param = new HashMap<>();
                    param.put("tenantId", tenantId + "");
                    param.put("parmType", IApiConst.UPDATE_PT_TYPE);
                    param.put("dateStr", po.getDateStr());
                    param.put("fkPatientId", po.getFkPatientId() + "");
                    HttpClientUtil.post(CommonConstants.FU_URL + "/fuDownDataHandling/handling.shtml", param);
                } else if (Objects.equals(IApiConst.REPORT_LIS, parmType)) {
                    assayReportController.refreshMonthReport(tenantId, po.getDateStr());
                }
            } catch (Exception e) {
                LOGGER.error("handling data failed,case by：", e);
            }
        }).start();
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }
}
