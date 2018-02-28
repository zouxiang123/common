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

import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayRecordServiceImpl implements IPatientAssayRecordService {
    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;

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
        return patientAssayRecordMapper.getMinSampleTimeByCreateTime(assayRecord);
    }

    @Override
    public List<PatientAssayRecordPO> listDict(Integer tenantId, Date startCreateTime, Date endCreateTime) {
        return patientAssayRecordMapper.listDict(tenantId, startCreateTime, endCreateTime);
    }

}
