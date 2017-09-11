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
import com.xtt.common.dao.model.PatientAssayRecord;
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
    public List<PatientAssayRecord> listByItemCode(Date startCreateTime, Date endCreateTime, List<String> itemCode, String groupName) {

        return patientAssayRecordMapper.listByItemCode(startCreateTime, endCreateTime, itemCode, groupName, UserUtil.getTenantId());
    }

    @Override
    public Integer countByPatientId(Date startTime, Date endTime, String itemCode, Long fkPatientId, Double resultActual) {
        return patientAssayRecordMapper.countByPatientId(startTime, endTime, itemCode, fkPatientId, resultActual, UserUtil.getTenantId());
    }

}
