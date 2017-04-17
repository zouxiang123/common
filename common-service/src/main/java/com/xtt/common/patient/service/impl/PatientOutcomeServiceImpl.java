/**   
 * @Title: OutcomeServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月29日 上午10:43:55 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.cache.PatientCache;
import com.xtt.common.cache.UserCache;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.PatientOutcomeMapper;
import com.xtt.common.dao.model.PatientOutcome;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.dao.po.PatientOutcomePO;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.dto.SysUserDto;
import com.xtt.common.patient.service.IPatientOutcomeService;
import com.xtt.common.patient.service.IPatientOwnerService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class PatientOutcomeServiceImpl implements IPatientOutcomeService {

    @Autowired
    private PatientOutcomeMapper patientOutcomeMapper;
    @Autowired
    private IPatientOwnerService patientOwnerService;

    @Override
    public void save(PatientOutcome record) {
        record.setFkTenantId(UserUtil.getTenantId());
        DataUtil.setSystemFieldValue(record);
        patientOutcomeMapper.insert(record);

        PatientOwner owner = new PatientOwner();
        owner.setFkPatientId(record.getFkPatientId());
        // 如果转出系统不为空，则使用转出系统，如果为空，则使用当前系统
        owner.setSysOwner(StringUtil.isBlank(record.getToSysOwner()) ? record.getSysOwner() : record.getToSysOwner());
        // 如果是转到其它系统
        Map<String, String> sysOwners = DictUtil.getMapByPItemCode(CmDictConsts.SYS_OWNER);
        owner.setIsEnable(!sysOwners.containsKey(record.getType()));
        // 如果转出租户不为空，则使用转出租户，如果为空，则使用当前租户
        owner.setFkTenantId(record.getToTenantId() == null ? record.getFkTenantId() : record.getToTenantId());
        patientOwnerService.updateOwner(owner);
    }

    @Override
    public List<PatientOutcomePO> listByPatientId(Long patientId) {
        PatientOutcomePO record = new PatientOutcomePO();
        record.setFkPatientId(patientId);
        record.setSysOwner(UserUtil.getSysOwner());
        return selectByCondition(record);
    }

    @Override
    public List<PatientOutcomePO> selectByCondition(PatientOutcomePO record) {
        record = record == null ? new PatientOutcomePO() : record;
        record.setMultiTenant(UserUtil.getMultiTenant());
        return init(patientOutcomeMapper.selectByCondition(record));
    }

    /** 初始化显示 */
    private List<PatientOutcomePO> init(List<PatientOutcomePO> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Set<Long> patientIds = new HashSet<>();
            Set<Long> userIds = new HashSet<>();
            list.forEach(po -> {
                patientIds.add(po.getFkPatientId());
                userIds.add(po.getCreateUserId());
            });
            Map<Long, PatientDto> patientMap = PatientCache.getById(patientIds);
            Map<Long, SysUserDto> userMap = UserCache.getById(userIds);
            Map<String, String> typesMap = DictUtil.getMapByPItemCode(CmDictConsts.PATIENT_OUTCOME_TYPE);
            list.forEach(po -> {
                po.setTypeShow(typesMap.get(po.getType()));
                if (patientMap.get(po.getFkPatientId()) != null)
                    po.setPatientName(patientMap.get(po.getFkPatientId()).getName());
                if (userMap.get(po.getCreateUserId()) != null)
                    po.setCreateUserName(userMap.get(po.getCreateUserId()).getName());
            });
        }
        return list;
    }

    @Override
    public List<PatientOutcomePO> listLatest(Collection<Long> patientIds, String month, String multiTenant, String sysOwner) {
        return patientOutcomeMapper.listLatest(patientIds, month, multiTenant, sysOwner);
    }
}
