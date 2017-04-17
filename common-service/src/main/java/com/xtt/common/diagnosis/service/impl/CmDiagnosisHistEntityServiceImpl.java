/**   
 * @Title: CmDiagnosisHistEntityServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月5日 上午10:21:42 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisEntityMapper;
import com.xtt.common.dao.mapper.CmDiagnosisEntityValueMapper;
import com.xtt.common.dao.model.CmDiagnosisEntity;
import com.xtt.common.dao.po.CmDiagnosisEntityPO;
import com.xtt.common.dao.po.CmDiagnosisEntityValuePO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistEntityService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistEntityServiceImpl implements ICmDiagnosisHistEntityService {

    @Autowired
    private CmDiagnosisEntityMapper cmDiagnosisEntityMapper;
    @Autowired
    private CmDiagnosisEntityValueMapper cmDiagnosisEntityValueMapper;

    @Override
    public CmDiagnosisEntityPO selectById(Long id) {
        return cmDiagnosisEntityMapper.getById(id);
    }

    @Override
    public List<CmDiagnosisEntityPO> selectEntitiesByPatient(CmDiagnosisEntityPO entity) {
        entity.setMultiTenant(UserUtil.getMultiTenant());
        return cmDiagnosisEntityMapper.selectEntitiesByPatient(entity);
    }

    @Override
    public String saveItem(CmDiagnosisEntityPO record) {
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisEntityMapper.insertSelective(record);
            // 添加成功，则批量添加Value集合
            if (record.getId() != null) {
                for (CmDiagnosisEntityValuePO value : record.getValueList()) {
                    value.setFkEntityId(record.getId());
                }
            }
            cmDiagnosisEntityValueMapper.insertValueBatch(record.getValueList());
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisEntityMapper.updateByPrimaryKeySelective(record);
            // 先删除Value集合
            cmDiagnosisEntityValueMapper.deleteByEntity(record.getId());
            // 后添加Value集合
            cmDiagnosisEntityValueMapper.insertValueBatch(record.getValueList());
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public String deleteById(Long id) {
        CmDiagnosisEntity item = cmDiagnosisEntityMapper.selectByPrimaryKey(id);
        if (item != null) {
            int count = cmDiagnosisEntityMapper.deleteByPrimaryKey(item.getId());
            // 如果Entity删除成功，则执行删除Value集合
            if (count > 0) {
                cmDiagnosisEntityValueMapper.deleteByEntity(id);
            }
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}