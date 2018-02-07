/**   
 * @Title: PatientAssayDictionaryServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:37:42 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayDictionaryService;
import com.xtt.common.dao.mapper.PatientAssayDictionaryMapper;
import com.xtt.common.dao.po.PatientAssayDictionaryPO;

@Service
public class PatientAssayDictionaryServiceImpl implements IPatientAssayDictionaryService {
    @Autowired
    private PatientAssayDictionaryMapper patientAssayDictionaryMapper;

    @Override
    public List<PatientAssayDictionaryPO> listByCondition(PatientAssayDictionaryPO record) {
        return patientAssayDictionaryMapper.listByCondition(record);
    }

    @Override
    public PatientAssayDictionaryPO getByItemCode(String itemCode) {
        PatientAssayDictionaryPO query = new PatientAssayDictionaryPO();
        query.setItemCode(itemCode);
        List<PatientAssayDictionaryPO> list = patientAssayDictionaryMapper.listByCondition(query);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

}
