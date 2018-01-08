/**   
 * @Title: PatientOwnerServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月3日 下午7:19:32 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.PatientOwnerMapper;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.patient.service.IPatientOwnerService;
import com.xtt.common.util.DataUtil;

@Service
public class PatientOwnerServiceImpl implements IPatientOwnerService {
    @Autowired
    private PatientOwnerMapper patientOwnerMapper;

    @Override
    public void insert(PatientOwner record) {
        DataUtil.setSystemFieldValue(record);
        patientOwnerMapper.insert(record);
    }

    @Override
    public void updateOwner(PatientOwner record) {
        PatientOwner query = new PatientOwner();
        query.setFkPatientId(record.getFkPatientId());
        query.setFkTenantId(record.getFkTenantId());
        List<PatientOwner> list = selectByCondition(query);
        if (CollectionUtils.isNotEmpty(list)) {// 判断所属系统是否存在,如果存在，更新其是否有效标识
            PatientOwner owner = list.get(0);
            owner.setIsEnable(record.getIsEnable());
            owner.setSysOwner(record.getSysOwner());
            owner.setIsTemp(record.getIsTemp());
            DataUtil.setUpdateSystemFieldValue(owner);
            patientOwnerMapper.updateByPrimaryKey(owner);
        } else {
            record.setIsEnable(true);
            insert(record);
        }
    }

    @Override
    public List<PatientOwner> selectByCondition(PatientOwner record) {
        return patientOwnerMapper.selectByCondition(record);
    }

    @Override
    public List<PatientOwner> listTenantIdByPatientId(PatientOwner record) {
        return patientOwnerMapper.listTenantIdByPatientId(record);
    }
}
