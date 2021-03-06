/**   
 * @Title: AssayGroupServiceImpl.java 
 * @Package com.xtt.common.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月18日 上午9:33:49 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayGroupService;
import com.xtt.common.assay.service.IPatientAssayClassService;
import com.xtt.common.dao.mapper.AssayGroupConfDetailMapper;
import com.xtt.common.dao.mapper.AssayGroupConfMapper;
import com.xtt.common.dao.model.AssayGroupConf;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.po.AssayGroupConfPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

/**
 * @ClassName: AssayGroupServiceImpl
 * @date: 2016年7月18日 上午9:33:49
 * @version: V1.0
 *
 */
@Service
public class AssayGroupServiceImpl implements IAssayGroupService {

    @Autowired
    private AssayGroupConfMapper assayGroupConfMapper;

    @Autowired
    private AssayGroupConfDetailMapper assayGroupConfDetailMapper;
    @Autowired
    private IPatientAssayClassService patientAssayClassService;

    @Override
    public List<AssayGroupConf> selectByCondition(AssayGroupConf record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return assayGroupConfMapper.selectByCondition(record);
    }

    @Override
    public AssayGroupConf selectByPrimaryKey(Long id) {
        return assayGroupConfMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(AssayGroupConfPO record) {
        boolean isUpdate = record.getId() != null;
        // 保存同类分组名
        record.setFkTenantId(UserUtil.getTenantId());
        if (record.getId() == null) {
            DataUtil.setSystemFieldValue(record);
            assayGroupConfMapper.insert(record);
        } else {
            AssayGroupConf old = assayGroupConfMapper.selectByPrimaryKey(record.getId());
            DataUtil.setSystemFieldValue(record);
            record.setId(old.getId());
            record.setCreateTime(old.getCreateTime());
            record.setCreateUserId(old.getCreateUserId());
            assayGroupConfMapper.updateByPrimaryKey(record);
        }

        // 保存同类化验项
        assayGroupConfDetailMapper.deleteByFkAssayGroupConfId(record.getId());
        String itemCode = "";
        for (AssayGroupConfDetail detail : record.getDetails()) {
            itemCode += detail.getItemCode() + ",";
            DataUtil.setSystemFieldValue(detail);
            detail.setFkTenantId(UserUtil.getTenantId());
            detail.setFkAssayGroupConfId(record.getId());
            assayGroupConfDetailMapper.insert(detail);
        }
        /**
         * 如果当前分组在化验项提醒中，更新提醒分组对应的item_code和名称
         */
        if (isUpdate) {
            PatientAssayClass patientAssayClass = patientAssayClassService.getByFkAssayGroupConfId(record.getId());
            if (patientAssayClass != null) {
                patientAssayClass.setItemCode(itemCode.length() > 0 ? itemCode.substring(0, itemCode.length() - 1) : "");
                patientAssayClass.setFkAssayGroupConfName(record.getName());
                patientAssayClassService.updateById(patientAssayClass);
            }
        }
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        assayGroupConfMapper.deleteByPrimaryKey(id);
        assayGroupConfDetailMapper.deleteByFkAssayGroupConfId(id);
        patientAssayClassService.deleteByFkAssayGroupConfId(id);
    }

    @Override
    public List<AssayGroupConfDetail> selectDetail(Long fkAssayGroupConfId) {
        return assayGroupConfDetailMapper.selectByFkAssayGroupConfId(fkAssayGroupConfId);
    }

    @Override
    public AssayGroupConfDetail getByItemCode(String itemCode, Integer tenantId) {
        return assayGroupConfDetailMapper.getByItemCode(itemCode, tenantId);
    }

    @Override
    public Set<String> listGroupItemCodes(String itemCode, Integer tenantId) {
        AssayGroupConfDetail conf = getByItemCode(itemCode, tenantId);
        if (conf == null) {
            return null;
        }
        List<AssayGroupConfDetail> details = selectDetail(conf.getFkAssayGroupConfId());
        Set<String> itemCodes = new HashSet<>(details.size());
        details.forEach(detail -> {
            itemCodes.add(detail.getItemCode());
        });
        return itemCodes;
    }

}
