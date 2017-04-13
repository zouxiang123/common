/**   
 * @Title: PatientKpiHistServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年4月5日 上午10:44:23 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.PatientKpiHistMapper;
import com.xtt.common.dao.model.PatientKpiHist;
import com.xtt.common.dao.po.PatientKpiHistPO;
import com.xtt.common.patient.service.IPatientKpiHistService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.PrimaryKeyUtil;

@Service
public class PatientKpiHistServiceImpl implements IPatientKpiHistService {
    @Autowired
    private PatientKpiHistMapper patientKpiHistMapper;

    @Override
    public List<PatientKpiHistPO> listLatest7(String category, Long fkPatientId, String sysOwner) {
        PatientKpiHistPO query = new PatientKpiHistPO();
        query.setCategory(category);
        query.setFkPatientId(fkPatientId);
        query.setSysOwner(sysOwner);
        query.setFkTenantId(UserUtil.getTenantId());
        query.setLimit(7);
        return listByCondition(query);
    }

    @Override
    public PatientKpiHistPO getLatest(String category, Long fkPatientId, String sysOwner) {
        PatientKpiHistPO query = new PatientKpiHistPO();
        query.setCategory(category);
        query.setFkPatientId(fkPatientId);
        query.setSysOwner(sysOwner);
        query.setFkTenantId(UserUtil.getTenantId());
        query.setLimit(1);
        List<PatientKpiHistPO> list = listByCondition(query);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    private List<PatientKpiHistPO> listByCondition(PatientKpiHistPO record) {
        return patientKpiHistMapper.listByCondition(record);
    }

    @Override
    public void save(PatientKpiHistPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        if (record.getId() == null) {
            DataUtil.setAllSystemFieldValue(record);
            patientKpiHistMapper.insert(record);
        } else {
            DataUtil.setUpdateSystemFieldValue(record);
            patientKpiHistMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public void removeByPatientId(Long fkPatientId, String sysOwner) {
        patientKpiHistMapper.removeByPatientId(fkPatientId, UserUtil.getTenantId(), sysOwner);
    }

    @Override
    public void insertBatch(List<PatientKpiHist> records) {
        Long startId = PrimaryKeyUtil.getPrimaryKey("PatientKpiHist", UserUtil.getTenantId(), records.size());
        PatientKpiHist record;
        Integer tenantId = UserUtil.getTenantId();
        for (int i = 0; i < records.size(); i++) {
            record = records.get(i);
            record.setId(startId + i);
            record.setFkTenantId(tenantId);
            DataUtil.setAllSystemFieldValue(record);
            patientKpiHistMapper.insert(record);
        }
    }
}
