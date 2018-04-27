/**   
 * @Title: PatientAssayRecordServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:54 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IAssayFilterRuleService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class PatientAssayRecordServiceImpl implements IPatientAssayRecordService {
    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;
    @Autowired
    private IAssayFilterRuleService assayFilterRuleService;

    @Override
    public List<PatientAssayRecordPO> listPatientAssayRecord(PatientAssayRecordPO po) {
        return patientAssayRecordMapper.listPatientAssayRecord(po);
    }

    @Override
    public List<PatientAssayRecordPO> listByCreateTime(Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        return patientAssayRecordMapper.listByCreateTime(startCreateTime, endCreateTime, UserUtil.getTenantId(), fkPatientId);
    }

    @Override
    public List<PatientAssayRecordPO> listAssayRecordByCondition(PatientAssayRecordPO po) {
        return patientAssayRecordMapper.listAssayRecordByCondition(po);
    }

    @Override
    public List<Long> listPatientIds(String assayDate, Integer tenantId) {
        return patientAssayRecordMapper.listPatientIds(assayDate, tenantId);
    }

    @Override
    public Date getMinSampleTimeByCreateTime(PatientAssayRecordPO assayRecord) {
        if (assayRecord.getFkTenantId() == null) {
            assayRecord.setFkTenantId(UserUtil.getTenantId());
        }
        PatientAssayRecordPO assayRecordByCreateTime = patientAssayRecordMapper.getMinSampleTimeByCreateTime(assayRecord);
        Date minSampleTime = null;
        if (assayRecordByCreateTime != null) {
            AssayFilterRule assayFilterRule = assayFilterRuleService.getByTenantId(assayRecord.getFkTenantId());
            // 根据清洗规则化验时间取值
            if (StringUtil.equals(assayFilterRule.getAssayDateType(), AssayConsts.SAMPLE_TIME)) {
                minSampleTime = assayRecordByCreateTime.getSampleTime();
            }
            if (StringUtil.equals(assayFilterRule.getAssayDateType(), AssayConsts.REPORT_TIME)) {
                minSampleTime = assayRecordByCreateTime.getReportTime();
            }
        }
        return minSampleTime;
    }

    @Override
    public List<PatientAssayRecordPO> listDict(Integer tenantId, Date startCreateTime, Date endCreateTime) {
        return patientAssayRecordMapper.listDict(tenantId, startCreateTime, endCreateTime);
    }

}
