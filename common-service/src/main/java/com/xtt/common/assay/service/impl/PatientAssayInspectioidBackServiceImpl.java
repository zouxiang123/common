/**   
 * @Title: PatientAssayBackInspectioidServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月10日 上午11:15:38 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayInspectioidBackService;
import com.xtt.common.dao.mapper.PatientAssayInspectioidBackMapper;
import com.xtt.common.dao.model.PatientAssayInspectioidBack;

@Service
public class PatientAssayInspectioidBackServiceImpl implements IPatientAssayInspectioidBackService {

    @Autowired
    private PatientAssayInspectioidBackMapper patientAssayInspectioidBackMapper;

    @Override
    public void insertList(List<PatientAssayInspectioidBack> list) {
        patientAssayInspectioidBackMapper.insertList(list);
    }

    @Override
    public List<PatientAssayInspectioidBack> listByPatientId(PatientAssayInspectioidBack record) {
        return patientAssayInspectioidBackMapper.listByCondition(record);
    }

}
