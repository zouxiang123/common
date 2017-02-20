/**   
 * @Title: AssayController.java 
 * @Package com.xtt.txgl.patient.controller
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.assay.service.IPatientAssayResultService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.BusinessReportUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/patient/assay/")
public class PatientAssayController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientAssayController.class);

    @Autowired
    private IPatientAssayRecordService patientAssayRecordService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IPatientAssayResultService patientAssayResultService;

    @RequestMapping("record")
    public ModelAndView record(Long patientId) {
        ModelAndView model = new ModelAndView("assay/patient_assay_record");
        PatientAssayRecordPO record = new PatientAssayRecordPO();
        record.setFkPatientId(patientId);
        model.addObject("assayResult", patientAssayResultService.getByPatientId(patientId));
        model.addObject("patientId", patientId);
        model.addObject("tenantId", UserUtil.getTenantId());
        model.addObject("patient", patientService.selectById(patientId));
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
    public Map<String, Object> getAssayDateRecord(PatientAssayRecordPO record) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayRecordPO> items = patientAssayRecordService.getAssayDateRecord(record);
        List<PatientAssayRecordPO> records = new ArrayList<PatientAssayRecordPO>();
        List<PatientAssayRecordPO> categoryList = new ArrayList<PatientAssayRecordPO>();
        // 类别切换时默认显示最新一次检查的数据
        if (items != null && !items.isEmpty()) {
            PatientAssayRecordPO temp = items.get(0);
            PatientAssayRecordPO query = new PatientAssayRecordPO();
            query.setAssayDate(temp.getAssayDate());
            query.setFkPatientId(temp.getFkPatientId());
            categoryList = patientAssayRecordService.getCategoryList(query);
            records = patientAssayRecordService.getByCondition(query);
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
    public Map<String, Object> getAssayRecord(PatientAssayRecordPO record, Boolean needCategory) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        if (needCategory != null && needCategory)
            map.put("categoryList", patientAssayRecordService.getCategoryList(record));
        map.put("records", patientAssayRecordService.getByCondition(record));
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
        return patientAssayRecordService.getReportData(patientId, BusinessReportUtil.getStartOrEndDate(startDateStr, true),
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

    /**
     * 
     * 根据条件查询患者某项目最新的数据
     * 
     * @Title: getLatestByCondition
     * @param record
     * @return
     *
     */
    @RequestMapping("selectItemLatestDataByCondition")
    @ResponseBody
    public Map<String, Object> selectItemLatestDataByCondition(@RequestBody PatientAssayRecordPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PatientAssayRecordPO> list = patientAssayRecordService.selectItemLatestDataByCondition(record);
        map.put("items", list);
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }
}
