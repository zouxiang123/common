/**   
 * @Title: DownDataProcessController.java 
 * @Package com.xtt.common.common.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月3日 下午1:39:39 
 *
 */
package com.xtt.common.common.controller;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.api.CommQueryApi;
import com.xtt.common.assay.controller.PatientAssayRecordController;
import com.xtt.common.assay.service.IPatientAssayConfService;
import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.constants.IApiConst;
import com.xtt.common.dao.model.PatientAssayConf;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.report.controller.AssayReportController;
import com.xtt.common.report.service.IPatientAssayNewstService;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.time.DateFormatUtil;

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
    @Autowired
    private PatientAssayRecordController patientAssayRecordController;
    @Autowired
    private IPatientAssayConfService patientAssayConfService;
    @Autowired
    private IPatientAssayNewstService patientAssayNewstService;

    /**
     * 数据下载完调用接口
     * 
     * @Title: handling
     * @param type（检验:lis、患者:patient、影像报告:pacs、医嘱:orders；）
     * @param tenantId
     * @return
     *
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("handling")
    @ResponseBody
    public HttpResult handling(CommQueryApi po) {
        String parmType = po.getParmType();
        Integer tenantId = po.getTenantId();
        LOGGER.info("get request to handling {}", po.toString());
        HttpResult result = HttpResult.getSuccessInstance();
        try {
            UserUtil.setThreadTenant(tenantId, po.getSysOwner());
            if (Objects.equals(IApiConst.UPDATE_PT_TYPE, parmType)) {
                patientService.updatePatientType(tenantId);
                commonCacheService.cachePatient(tenantId);
            } else if (Objects.equals(IApiConst.REPORT_LIS, parmType)) {
                String subType = po.getSubType();
                boolean isDelete = Objects.equals(subType, IApiConst.LIS_SUB_TYPE_RM_ONE) || Objects.equals(subType, IApiConst.LIS_SUB_TYPE_RM_ALL);
                long start = System.currentTimeMillis();
                String dateStr = po.getDateStr();
                // 插入化验数据
                LOGGER.info("===================== begin to hand create_time {} assay record =====================", dateStr);
                result = patientAssayRecordController.insertAuto(dateStr, tenantId, po.getFkPatientId(), isDelete, po.getSysOwner());
                if (result.getRs() != null) {
                    Set<String> handDateSet = (Set<String>) result.getRs();
                    if (!isDelete) {
                        Set<String> assayMonthSet = new TreeSet<>();
                        PatientAssayConf patientAssayConf = patientAssayConfService.selectByTenantId(tenantId);
                        handDateSet.forEach(date -> {
                            assayMonthSet.add(patientAssayConfService.selectMonthAndYearByDate(DateFormatUtil.convertStrToDate(date), tenantId,
                                            patientAssayConf));
                        });
                        // 化验项统计根据配置月份生成
                        assayMonthSet.forEach(month -> {
                            LOGGER.info("===================== handle month {} assay report =====================", month);
                            assayReportController.insertMonthData(month, tenantId, null);
                        });
                    } else {
                        LOGGER.info("===================== hand all assay report data =====================");
                        assayReportController.insertAutoHistory(tenantId, null, po.getSysOwner());
                    }
                    LOGGER.info("===================== hand all assay newst report data =====================");
                    // 每日自动更新患者最新-次化验数据
                    patientAssayNewstService.insertAuto(tenantId);
                }
                LOGGER.info("===================== end of hand create_time {} assay record , total cost {} ms============", dateStr,
                                System.currentTimeMillis() - start);
            }
        } catch (Exception e) {
            LOGGER.error("handling data failed,case by：", e);
        }
        return result;
    }
}
