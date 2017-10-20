/**   
 * @Title: PatientCardServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月17日 下午2:33:33 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.dao.mapper.PatientCardMapper;
import com.xtt.common.dao.model.PatientCard;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.patient.service.IPatientCardService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class PatientCardServiceImpl implements IPatientCardService {
    @Autowired
    private PatientCardMapper patientCardMapper;

    @Override
    public void saveBatch(List<PatientCardPO> list) {
        for (PatientCardPO record : list) {
            if (record.getId() != null) {
                PatientCard old = patientCardMapper.selectByPrimaryKey(record.getId());
                // if zhe cardNo is empty,use the old
                if (StringUtil.isBlank(record.getCardNo())) {
                    record.setCardNo(old.getCardNo());
                }
                // if this card not select newFlag,set newFlag to false
                if (record.getNewFlag() == null) {
                    record.setNewFlag(false);
                }
                // if this card is delete,set newFlag to false
                if (record.getDelFlag()) {
                    record.setNewFlag(false);
                }
                record.setCreateTime(old.getCreateTime());
                record.setCreateUserId(old.getCreateUserId());
                record.setFkTenantId(old.getFkTenantId());
                DataUtil.setSystemFieldValue(record);
                patientCardMapper.updateByPrimaryKey(record);
            } else {
                // defalut newFlag is false
                if (record.getNewFlag() == null) {
                    record.setNewFlag(false);
                }
                // defalut delFlag is false
                record.setDelFlag(false);
                record.setFkTenantId(UserUtil.getTenantId());
                DataUtil.setSystemFieldValue(record);
                patientCardMapper.insert(record);
            }
        }
    }

    @Override
    public List<PatientCardPO> listByPatientId(Long patientId) {
        PatientCardPO record = new PatientCardPO();
        record.setFkPtId(patientId);
        record.setDelFlag(false);
        return listByCondition(record);
    }

    @Override
    public List<PatientCardPO> listAllNewHisCard() {
        PatientCardPO record = new PatientCardPO();
        record.setDelFlag(false);
        record.setNewFlag(true);
        record.setCardType(CmSysParamConsts.showPatientCradType);
        return listByCondition(record);
    }

    @Override
    public List<PatientCardPO> listByCondition(PatientCardPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientCardMapper.selectByCondition(record);
    }

    @Override
    public PatientCardPO getNewHisIdByPatientId(Long patientId) {
        PatientCardPO record = new PatientCardPO();
        record.setFkPtId(patientId);
        record.setDelFlag(false);
        record.setNewFlag(true);
        record.setCardType(CmSysParamConsts.showPatientCradType);
        List<PatientCardPO> list = listByCondition(record);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Map<Long, PatientCardPO> listNewHisIdByPatientIds(Collection<Long> patientIds) {
        Map<Long, PatientCardPO> map = new HashMap<>();
        if (CollectionUtils.isEmpty(patientIds)) {
            return map;
        }
        PatientCardPO record = new PatientCardPO();
        record.setPatientIds(patientIds);
        record.setDelFlag(false);
        record.setNewFlag(true);
        record.setCardType(CmSysParamConsts.showPatientCradType);
        List<PatientCardPO> list = listByCondition(record);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(pc -> {
                map.put(pc.getFkPtId(), pc);
            });
        }
        return map;
    }

    @Override
    public List<PatientCardPO> listPatientCard(PatientCardPO record) {
        return patientCardMapper.listPatientCard(record);
    }

    @Override
    public List<PatientCardPO> selectHisId(Collection<Long> patientIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PatientCard> listByCardNoTypeTenant(String cardNo, String cardType, Integer tenant, Long nePatientId) {
        return patientCardMapper.listByCardNoTypeTenant(cardNo, cardType, tenant, nePatientId);
    }

    @Override
    public String getCardNo(PatientCardPO record) {
        record.setDelFlag(false);
        record.setNewFlag(true);
        record.setFkTenantId(UserUtil.getTenantId());
        List<PatientCardPO> list = patientCardMapper.selectByCondition(record);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).getCardNo();
    }

    @Override
    public List<PatientCardPO> listPatientCardByRecord(PatientCardPO record) {
        return patientCardMapper.selectByCondition(record);
    }
}
