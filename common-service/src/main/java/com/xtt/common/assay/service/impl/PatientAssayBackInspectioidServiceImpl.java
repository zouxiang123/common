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

import com.xtt.common.assay.service.IPatientAssayBackInspectioidService;
import com.xtt.common.dao.mapper.PatientAssayBackInspectioidMapper;
import com.xtt.common.dao.model.PatientAssayBackInspectioid;

@Service
public class PatientAssayBackInspectioidServiceImpl implements IPatientAssayBackInspectioidService {

    @Autowired
    private PatientAssayBackInspectioidMapper patientAssayBackInspectioidMapper;

    @Override
    public void insertList(List<PatientAssayBackInspectioid> list) {
        patientAssayBackInspectioidMapper.insertList(list);
    }

    @Override
    public List<PatientAssayBackInspectioid> selectByPatientId(PatientAssayBackInspectioid patientAssayBackInspectioid) {
        return patientAssayBackInspectioidMapper.selectByPatientId(patientAssayBackInspectioid);
    }

}
