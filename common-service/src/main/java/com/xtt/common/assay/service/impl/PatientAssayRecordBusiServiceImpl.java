/**   
 * @Title: PatientAssayRecordServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:54 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayRecordBusiServiceImpl implements IPatientAssayRecordBusiService {

    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;

    @Override
    public List<PatientAssayRecordBusiPO> listByCondition(PatientAssayRecordBusiPO query) {
        if (query.getFkTenantId() == null) {
            query.setFkTenantId(UserUtil.getTenantId());
        }
        return patientAssayRecordBusiMapper.listByCondition(query);
    }

    @Override
    public int countByInspectionId(String inspectionId) {

        return patientAssayRecordBusiMapper.countByInspectionId(inspectionId);
    }

    @Override
    public void insertList(List<PatientAssayRecordBusi> listPatientAssayRecordBusi) {
        patientAssayRecordBusiMapper.insertList(listPatientAssayRecordBusi);

    }

    @Override
    public void updateDiaAbFlagByReqId(PatientAssayRecordBusi patientAssayRecordBusi) {
        patientAssayRecordBusiMapper.updateDiaAbFlagByReqId(patientAssayRecordBusi);

    }

}
