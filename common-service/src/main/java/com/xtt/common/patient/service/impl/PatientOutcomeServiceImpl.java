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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.cache.UserCache;
import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.PatientOutcomeMapper;
import com.xtt.common.dao.mapper.PatientOwnerMapper;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.dao.po.PatientOutcomePO;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.dto.SysUserDto;
import com.xtt.common.patient.service.IPatientOutcomeService;
import com.xtt.common.patient.service.IPatientOwnerService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientOutcomeServiceImpl implements IPatientOutcomeService {

    @Autowired
    private PatientOutcomeMapper patientOutcomeMapper;
    @Autowired
    private IPatientOwnerService patientOwnerService;
    @Autowired
    private PatientOwnerMapper patientOwnerMapper;
    @Autowired
    private ISysTenantService sysTenantService;

    @Override
    public void save(PatientOutcomePO record) {
        record.setSysOwner(UserUtil.getSysOwner());
        if (record.getFkPatientId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        DataUtil.setSystemFieldValue(record);
        if (record.getId() == null) {
            // 保存转归记录
            patientOutcomeMapper.insert(record);
        } else {
            // 更新转归记录
            patientOutcomeMapper.updateByPrimaryKeySelective(record);
        }
        PatientOwner owner = new PatientOwner();
        owner.setFkPatientId(record.getFkPatientId());
        owner.setSysOwner(UserUtil.getSysOwner());
        owner.setIsTemp(record.getIsTemp());
        // 如果是转到其它系统
        Map<String, String> sysOwners = DictUtil.getMapByPItemCode(CmDictConsts.SYS_OWNER);
        owner.setIsEnable(!sysOwners.containsKey(record.getType()));
        // 如果转出租户不为空，则使用转出租户，如果为空，则使用当前租户
        if (Objects.equal("in", record.getPatientOutcomeType())) {
            owner.setIsEnable(true);
            owner.setSysOwner(record.getToSysOwner());
        }
        owner.setFkTenantId(record.getFkTenantId());
        // 转归
        if (Objects.equal("out", record.getPatientOutcomeType())) {
            // 判断是否为血透或者腹透
            if ("1".equals(record.getType()) || "2".equals(record.getType())) {
                owner.setSysOwner(record.getSysOwner());
                owner.setIsEnable(false);
                // 判断是否为其它医院
                if (record.getToTenantId() == null) {
                    // 转其它医院只更新本院
                    owner.setSysOwner(UserUtil.getSysOwner());
                } else {
                    // 不是转其它医院将数据制为删除
                    patientOwnerMapper.updateDisableByPatientId(record.getFkPatientId(), record.getFkTenantId()); // 将本院患者状态为删除
                }
                // 转死亡,失随访,肾移植,其它 设置为失效
            } else {
                owner.setIsEnable(false);
            }
        }
        if (Objects.equal("2", record.getType()) || Objects.equal("temporary", record.getPatientOutcomeType())) {
            owner.setFkTenantId(record.getToTenantId());
            owner.setSysOwner(record.getToSysOwner());
            owner.setIsEnable(true);
        }
        if (record.getFkTenantId().equals(record.getToTenantId())) {
            owner.setSysOwner(record.getToSysOwner());
            owner.setIsEnable(true);
        }
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
            Map<String, String> typesMap = DictUtil.getMapByPItemCode(CmDictConsts.OUTCOME_RECORD_TYPE);
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
    public List<PatientOutcomePO> listLatest(Collection<Long> patientIds, String month, String multiTenant, String sysOwner, Date startTime,
                    Date endTime) {
        return patientOutcomeMapper.listLatest(patientIds, month, multiTenant, sysOwner, startTime, endTime);
    }

    @Override
    public Integer selectCountByPatientId(PatientOutcomePO outcomeRecord) {
        outcomeRecord.setFkTenantId(UserUtil.getTenantId());
        return patientOutcomeMapper.selectCountByPatientId(outcomeRecord);
    }

    @Override
    public List<Long> selectPatientByMonth(Date startTime, Date endTime, List<String> excludeTypes) {
        return patientOutcomeMapper.selectPatientByMonth(startTime, endTime, excludeTypes);
    }
}
