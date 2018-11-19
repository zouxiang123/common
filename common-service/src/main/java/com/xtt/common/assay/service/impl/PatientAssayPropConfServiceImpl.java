/**   
 * @Title: PatientAssayPropConfServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年7月12日 上午10:45:41 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IPatientAssayPropConfService;
import com.xtt.common.dao.mapper.PatientAssayPropConfMapper;
import com.xtt.common.dao.model.PatientAssayPropConf;
import com.xtt.common.dao.po.PatientAssayPropConfPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayPropConfServiceImpl implements IPatientAssayPropConfService {
    @Autowired
    private PatientAssayPropConfMapper assayPropConfMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        assayPropConfMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(PatientAssayPropConf record) {
        record.setFkTenantId(UserUtil.getTenantId());
        DataUtil.setAllSystemFieldValue(record);
        assayPropConfMapper.insert(record);
    }

    @Override
    public void insertSelective(PatientAssayPropConf record) {
        DataUtil.setAllSystemFieldValue(record);
        record.setFkTenantId(UserUtil.getTenantId());
        assayPropConfMapper.insertSelective(record);
    }

    @Override
    public PatientAssayPropConf selectByPrimaryKey(Long id) {
        return assayPropConfMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(PatientAssayPropConf record) {
        DataUtil.setUpdateSystemFieldValue(record);
        assayPropConfMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(PatientAssayPropConf record) {
        DataUtil.setUpdateSystemFieldValue(record);
        assayPropConfMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PatientAssayPropConf> listByCond(PatientAssayPropConf record) {
        return assayPropConfMapper.listByCond(record);
    }

    @Override
    public void insertBatch(List<PatientAssayPropConf> list) {
        // 根据租户号删除数据
        this.deleteByTenantId(UserUtil.getTenantId());
        // 保存数据
        if (list != null && list.size() > 0) {
            list.forEach(entity -> {
                entity.setItemCode(entity.getItemCode().replace(AssayConsts.DICT_UK_SUFFIX, ""));
                entity.setFkTenantId(UserUtil.getTenantId());
                DataUtil.setAllSystemFieldValue(entity);
            });
            assayPropConfMapper.insertBatch(list);
        }
    }

    @Override
    public void deleteByTenantId(Integer fkTenantId) {
        assayPropConfMapper.deleteByTenantId(fkTenantId);
    }

    @Override
    public List<PatientAssayPropConfPO> listPropTemplateLabel(PatientAssayPropConf record) throws Exception {
        List<PatientAssayPropConfPO> returnList = new ArrayList<PatientAssayPropConfPO>();
        List<PatientAssayPropConf> list = this.listByCond(record);
        if (list != null && list.size() > 0) {
            for (PatientAssayPropConf entity : list) {
                // 异常
                PatientAssayPropConfPO propConf1 = new PatientAssayPropConfPO();
                BeanUtils.copyProperties(propConf1, entity);
                propConf1.setUnusualCode(AssayConsts.UNUSUAL_CODE);
                propConf1.setUnusualName(entity.getItemName() + AssayConsts.UNUSUAL_NAME);
                propConf1.setItemCode(entity.getItemCode());
                // 高
                PatientAssayPropConfPO propConf2 = new PatientAssayPropConfPO();
                BeanUtils.copyProperties(propConf2, entity);
                propConf2.setUnusualCode(AssayConsts.HIGH_CODE);
                propConf2.setUnusualName(AssayConsts.HIGH_NAME + entity.getItemName());
                propConf2.setItemCode(entity.getItemCode());
                // 低
                PatientAssayPropConfPO propConf3 = new PatientAssayPropConfPO();
                BeanUtils.copyProperties(propConf3, entity);
                propConf3.setUnusualCode(AssayConsts.LOW_CODE);
                propConf3.setUnusualName(AssayConsts.LOW_NAME + entity.getItemName());
                propConf3.setItemCode(entity.getItemCode());
                returnList.add(propConf1);
                returnList.add(propConf2);
                returnList.add(propConf3);
            }
        }
        return returnList;
    }

}
