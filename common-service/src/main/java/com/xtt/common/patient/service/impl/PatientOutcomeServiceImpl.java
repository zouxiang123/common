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

import com.google.common.base.Objects;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.cache.UserCache;
import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.PatientOutcomeMapper;
import com.xtt.common.dao.mapper.PatientOwnerMapper;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.po.PatientOutcomePO;
import com.xtt.common.dao.po.SysTenantPO;
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
        record.setFkTenantId(UserUtil.getTenantId());
        DataUtil.setSystemFieldValue(record);
        // 非临时转移保存转归数据
        if (!Objects.equal("temporary", record.getPatientOutcomeType())) {
            // 临时转移中本院转本院
            if (record.getToTenantId() == null && (record.getToTenantName().length() == 0)) {
                SysTenantPO sysTenant = new SysTenantPO();
                sysTenant.setId(UserUtil.getTenantId());
                // 根据租户id查询租户表获取医院名称
                SysTenant getSysTenant = sysTenantService.listTenant(sysTenant).get(0);
                record.setToTenantName(getSysTenant.getName());
                record.setToTenantId(UserUtil.getTenantId());
            }
            // 保存转归记录
            patientOutcomeMapper.insert(record);
        }
        PatientOwner owner = new PatientOwner();
        owner.setFkPatientId(record.getFkPatientId());
        owner.setSysOwner(UserUtil.getSysOwner());
        // 如果是转到其它系统
        Map<String, String> sysOwners = DictUtil.getMapByPItemCode(CmDictConsts.SYS_OWNER);
        owner.setIsEnable(!sysOwners.containsKey(record.getType()));
        // 如果转出租户不为空，则使用转出租户，如果为空，则使用当前租户
        if (Objects.equal("in", record.getPatientOutcomeType())) {
            owner.setIsEnable(true);
        }
        owner.setFkTenantId(record.getToTenantId() == null ? record.getFkTenantId() : record.getToTenantId());
        // 转归
        if (Objects.equal("out", record.getPatientOutcomeType())) {
            // 判断是否为血透或者腹透
            if ("1".equals(record.getType()) || "2".equals(record.getType())) {
                // 判断是否为其它医院
                if (record.getToTenantId() == null) {
                    // 转其它医院将是否有效制定为false
                    owner.setIsEnable(false);
                } else {
                    // 不是转其它医院将数据制为删除
                    patientOwnerMapper.updateDisableByPatientId(record.getFkPatientId(), record.getFkTenantId()); // 将归属不状态更新为删除
                }
                // 转死亡,失随访,肾移植,其它 设置为失效
            } else {
                owner.setIsEnable(false);
            }
        }
        // 临时转移
        if (Objects.equal("temporary", record.getPatientOutcomeType())) {
            // 是否为转其它医院
            if (record.getToTenantId() == null) {
                owner.setIsEnable(false);
            } else {
                // patientOwnerMapper.updateDisableByPatientId(record.getFkPatientId(), record.getFkTenantId()); // 将归属不状态更新为删除
            }

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
            // Map<String, String> typesMap = DictUtil.getMapByPItemCode(DictConsts.OUTCOME_RECORD_TYPE);
            list.forEach(po -> {
                // po.setTypeShow(typesMap.get(po.getType()));
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
