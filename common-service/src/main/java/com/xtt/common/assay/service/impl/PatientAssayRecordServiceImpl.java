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

@Service
public class PatientAssayRecordServiceImpl implements IPatientAssayRecordService {
    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;

    @Override
    public List<PatientAssayRecordPO> listPatientAssayRecord(PatientAssayRecordPO po) {
        return patientAssayRecordMapper.listPatientAssayRecord(po);
    }

    @Override
    public List<PatientAssayRecordPO> listByCreateTime(Date startCreateTime, Date endCreateTime) {

        return patientAssayRecordMapper.listByCreateTime(startCreateTime, endCreateTime);
    }

    @Override
    public List<PatientAssayRecord> listByItemCode(Date startCreateTime, Date endCreateTime, List<String> itemCode) {

        return patientAssayRecordMapper.listByItemCode(startCreateTime, endCreateTime, itemCode);
    }

    @Override
    public List<PatientAssayRecordPO> listByAfterCount(String afterCount, Date startCreateTime, Date endCreateTime, Integer fkTenantId,
                    String strItemCode) {

        return patientAssayRecordMapper.listByAfterCount(afterCount, startCreateTime, endCreateTime, fkTenantId, strItemCode);
    }

    @Override
    public PatientAssayRecordPO getByBeforeCount(String beforeCount, Date sampleTime, Date startCreateTime, String strItemCode, Long fkPatientId,
                    Integer fkTenantId) {

        return patientAssayRecordMapper.getByBeforeCount(beforeCount, sampleTime, startCreateTime, strItemCode, fkPatientId, fkTenantId);
    }

}
