/**   
 * @Title: AssayController.java 
 * @Package com.xtt.common.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:34:38 
 *
 */
package com.xtt.common.assay.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayResultService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.patient.service.ICmPatientService;
import com.xtt.common.util.BusinessReportUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/patient/assay/")
public class PatientAssayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientAssayController.class);

    @Autowired
    private IPatientAssayRecordBusiService patientAssayRecordBusiService;
    @Autowired
    private ICmPatientService cmPatientService;
    @Autowired
    private IPatientAssayResultService patientAssayResultService;

    @RequestMapping("record")
    public ModelAndView record(Long patientId) {
        ModelAndView model = new ModelAndView("assay/patient_assay_record");
        PatientAssayRecordBusiPO record = new PatientAssayRecordBusiPO();
        record.setFkPatientId(patientId);
        model.addObject("assayResult", patientAssayResultService.getByPatientId(patientId));
        model.addObject("patientId", patientId);
        model.addObject("tenantId", UserUtil.getTenantId());
        model.addObject("patient", cmPatientService.selectById(patientId));
        return model;
    }

    /**
     * 所获不同的检查时间
     * 
     * @Title: getAssayDateRecord
     * @param record
     * @return
     *
     */
    @RequestMapping("getAssayDateRecord")
    @ResponseBody
    public Map<String, Object> getAssayDateRecord(PatientAssayRecordBusiPO record) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayRecordBusiPO> items = patientAssayRecordBusiService.listByCondition(record);
        List<PatientAssayRecordBusiPO> records = new ArrayList<PatientAssayRecordBusiPO>();
        List<PatientAssayRecordBusiPO> categoryList = new ArrayList<PatientAssayRecordBusiPO>();
        // 类别切换时默认显示最新一次检查的数据
        if (items != null && !items.isEmpty()) {
            PatientAssayRecordBusiPO temp = items.get(0);
            PatientAssayRecordBusiPO query = new PatientAssayRecordBusiPO();
            query.setAssayDate(temp.getAssayDate());
            query.setFkPatientId(temp.getFkPatientId());
            categoryList = patientAssayRecordBusiService.listCategory(query);
            records = patientAssayRecordBusiService.listByCondition(query);
        }
        map.put("categoryList", categoryList);
        map.put("records", records);
        map.put("items", items);
        LOGGER.info("get assay date record total cost {} ms", System.currentTimeMillis() - start);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 所获不同的检查时间
     * 
     * @Title: getAssayDateRecord
     * @param record
     * @return
     *
     */
    @RequestMapping("getAssayRecord")
    @ResponseBody
    public Map<String, Object> getAssayRecord(PatientAssayRecordBusiPO record, Boolean needCategory) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        if (needCategory != null && needCategory)
            map.put("categoryList", patientAssayRecordBusiService.listCategory(record));
        map.put("records", patientAssayRecordBusiService.listByCondition(record));
        LOGGER.info("get assay record total cost {} ms", System.currentTimeMillis() - start);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 获取某项化验单报表数据
     * 
     * @Title: getAssayReport
     * @return
     * @throws UnsupportedEncodingException
     *
     */
    @RequestMapping("getAssayReport")
    @ResponseBody
    public List<Map<String, Object>> getAssayReport(String startDateStr, String endDateStr, Long patientId, String itemCode)
                    throws UnsupportedEncodingException {
        return patientAssayRecordBusiService.listReportData(patientId, BusinessReportUtil.getStartOrEndDate(startDateStr, true),
                        BusinessReportUtil.getStartOrEndDate(endDateStr, false), itemCode);
    }

    /**
     * 保存化验项汇总数据
     * 
     * @Title: saveAssayResult
     * @param record
     * @return
     *
     */
    @RequestMapping("saveAssayResult")
    @ResponseBody
    public Map<String, Object> saveAssayResult(PatientAssayResultPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        patientAssayResultService.saveAssayResult(record);
        map.put("id", record.getId());
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }
}
