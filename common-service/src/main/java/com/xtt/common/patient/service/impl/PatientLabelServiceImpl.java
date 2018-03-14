/**   
 * @Title: PatientLabelServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2018年3月12日 下午4:41:58 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.PatientLabelMapper;
import com.xtt.common.dao.mapper.PatientLabelRefMapper;
import com.xtt.common.dao.model.PatientLabel;
import com.xtt.common.dao.model.PatientLabelRef;
import com.xtt.common.dao.po.PatientLabelPO;
import com.xtt.common.patient.service.IPatientLabelService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientLabelServiceImpl implements IPatientLabelService {
    @Autowired
    private PatientLabelMapper patientLabelMapper;
    @Autowired
    private PatientLabelRefMapper patientLabelRefMapper;

    @Override
    public List<PatientLabelPO> listByCondition(PatientLabelPO record) {
        record.setSysOwner(UserUtil.getSysOwner());
        return patientLabelMapper.listByCondition(record);
    }

    @Override
    public List<PatientLabelPO> listByTenantId(Integer tenantId) {
        return patientLabelMapper.listByTenantId(tenantId, UserUtil.getSysOwner());
    }

    @Override
    public void deleteById(Long id) {
        patientLabelMapper.deleteByPrimaryKey(id);
        patientLabelRefMapper.deleteByCondition(null, null, id, UserUtil.getSysOwner());
    }

    @Override
    public List<PatientLabelPO> listPatient(PatientLabelPO record) {
        record.setSysOwner(UserUtil.getSysOwner());
        List<PatientLabelPO> patients = patientLabelMapper.listPatient(record);
        if (CollectionUtils.isNotEmpty(patients)) {
            Map<Long, PatientLabelPO> patientIdMap = patients.stream()
                            .collect(Collectors.toMap(PatientLabelPO::getPatientId, Function.identity(), (key1, key2) -> key2));
            // 查询患者对应的标签
            PatientLabelPO query = new PatientLabelPO();
            query.setFkTenantId(record.getFkTenantId());
            query.setSysOwner(record.getSysOwner());
            query.setPatientIds(patientIdMap.keySet());
            List<PatientLabelPO> labels = listByCondition(query);
            if (CollectionUtils.isNotEmpty(labels)) {
                labels.forEach(label -> {
                    PatientLabelPO patient = patientIdMap.get(label.getPatientId());
                    if (patient.getLabels() == null) {
                        patient.setLabels(new ArrayList<>());
                    }
                    patient.getLabels().add(label);
                });
            }
        }
        return patients;
    }

    @Override
    public void insert(PatientLabel record) {
        Integer tenantId = UserUtil.getTenantId();
        // 设置的order_by 的值
        Integer maxOrderBy = patientLabelMapper.getMaxOrderBy(tenantId, UserUtil.getSysOwner());
        record.setOrderBy(maxOrderBy == null ? 1 : ++maxOrderBy);
        record.setFkTenantId(tenantId);
        record.setSysOwner(UserUtil.getSysOwner());
        DataUtil.setAllSystemFieldValue(record);
        patientLabelMapper.insert(record);
    }

    @Override
    public void saveRef(Collection<Long> patientIds, Collection<Long> labelIds) {
        Integer tenantId = UserUtil.getTenantId();
        Long userId = UserUtil.getLoginUserId();
        Date now = new Date();
        String sysOwner = UserUtil.getSysOwner();
        // 删除患者关联的标签数据
        patientLabelRefMapper.deleteByCondition(patientIds, tenantId, null, UserUtil.getSysOwner());
        patientIds.forEach(patientId -> {
            int i = 1;
            for (Long labelId : labelIds) {
                PatientLabelRef ref = new PatientLabelRef();
                ref.setFkLabelId(labelId);
                ref.setFkPatientId(patientId);
                ref.setOrderBy(i++);
                ref.setFkTenantId(tenantId);
                ref.setSysOwner(sysOwner);
                ref.setCreateTime(now);
                ref.setCreateUserId(userId);
                ref.setUpdateTime(now);
                ref.setUpdateUserId(userId);
                patientLabelRefMapper.insert(ref);
            }
        });
    }

    @Override
    public boolean checkNameExists(String name) {
        PatientLabel record = new PatientLabel();
        record.setName(name);
        record.setFkTenantId(UserUtil.getTenantId());
        record.setSysOwner(UserUtil.getSysOwner());
        int count = patientLabelMapper.countByCondition(record);
        return count > 0;
    }

}
