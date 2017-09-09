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

import org.apache.commons.collections4.CollectionUtils;
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
        if (CollectionUtils.isNotEmpty(list)) {// 一次插入一千条数据
            int batchNo = 1000;// 每次插入条数
            int size = list.size();
            int page = size / batchNo;
            if (size % batchNo != 0) {
                page = page + 1;
            }
            for (int i = 0; i < page; i++) {
                int fromIndex = i * batchNo;
                int toIndex = fromIndex + batchNo;
                toIndex = toIndex > size ? size : toIndex;
                patientAssayInspectioidBackMapper.insertList(list.subList(fromIndex, toIndex));
            }
        }
    }

    @Override
    public List<PatientAssayInspectioidBack> listByPatientId(PatientAssayInspectioidBack record) {
        return patientAssayInspectioidBackMapper.listByCondition(record);
    }
}
