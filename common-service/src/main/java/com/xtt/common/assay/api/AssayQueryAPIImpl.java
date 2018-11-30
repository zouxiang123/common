/**   
 * @Title: AssayQueryAPIImpl.java 
 * @Package com.xtt.common.assay.api
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年11月30日 上午11:42:03 
 *
 */
package com.xtt.common.assay.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xtt.common.api.IAssayQueryAPI;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.util.SysParamUtil;

@Component
public class AssayQueryAPIImpl implements IAssayQueryAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssayQueryAPIImpl.class);

    @Autowired
    private IPatientAssayRecordBusiService patientAssayRecordBusiService;

    public Map<String, Object> getAssayDateRecord(Long fkPatientId, String dateType, String startDateStr, String endDateStr) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        PatientAssayRecordBusiPO record = new PatientAssayRecordBusiPO();
        record.setFkPatientId(fkPatientId);
        record.setDateType(dateType);
        record.setStartDateStr(startDateStr);
        record.setEndDateStr(endDateStr);
        List<PatientAssayRecordBusiPO> items = patientAssayRecordBusiService.listCategory(record);
        List<PatientAssayRecordBusiPO> records = new ArrayList<PatientAssayRecordBusiPO>();
        // 类别切换时默认显示最新一次检查的数据
        if (items != null && !items.isEmpty() && record.getPageNo() == 1) {
            PatientAssayRecordBusiPO temp = items.get(0);
            PatientAssayRecordBusiPO query = new PatientAssayRecordBusiPO();
            query.setReqId(temp.getReqId());
            query.setFkPatientId(temp.getFkPatientId());
            query.setReportTime(temp.getReportTime());
            records = patientAssayRecordBusiService.listByCondition(query);
        }
        map.put("records", records);
        map.put("items", items);

        map.put("record", record);
        // 报告日期，检查日期 开关配置
        String labTimeFlag = SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_ASSAY_LOAD_CONDITION_TIME_SWITCH);
        map.put("labTimeFlag", labTimeFlag);
        LOGGER.info("get assay date record total cost {} ms", System.currentTimeMillis() - start);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    public Map<String, Object> getAssayRecord(Long fkPatientId, String reqId) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        PatientAssayRecordBusiPO record = new PatientAssayRecordBusiPO();
        record.setFkPatientId(fkPatientId);
        record.setReqId(reqId);
        map.put("records", patientAssayRecordBusiService.listByCondition(record));
        LOGGER.info("get assay record total cost {} ms", System.currentTimeMillis() - start);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

}
