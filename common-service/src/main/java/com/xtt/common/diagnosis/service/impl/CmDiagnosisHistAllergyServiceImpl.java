/**   
 * @Title: CmDiagnosisHistAllergyServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:44:55 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistAllergyMapper;
import com.xtt.common.dao.model.CmDiagnosisHistAllergy;
import com.xtt.common.dao.po.CmDiagnosisHistAllergyPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistAllergyService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistAllergyServiceImpl implements ICmDiagnosisHistAllergyService {

    @Autowired
    private CmDiagnosisHistAllergyMapper cmDiagnosisHistAllergyMapper;

    @Override
    public CmDiagnosisHistAllergyPO selectById(Long id) {
        return cmDiagnosisHistAllergyMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据患者Id获取患者诊断之过敏史集合数据
     * 
     * @Title: selectAllergiesByPatient
     * @param patientId
     * @return
     *
     */
    @Override
    public List<CmDiagnosisHistAllergyPO> selectAllergiesByPatient(Long patientId) {
        return cmDiagnosisHistAllergyMapper.selectByPatient(patientId, UserUtil.getGroupTenant());
    }

    /**
     * 保存item数据
     * 
     * @Title: saveItem
     * @param record
     * @return status
     *
     */
    @Override
    public String saveItem(CmDiagnosisHistAllergyPO record) {
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisHistAllergyMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisHistAllergyMapper.updateByPrimaryKeySelective(record);
        }
        return CommonConstants.SUCCESS;
    }

    /**
     * 根据id删除数据
     * 
     * @Title: deleteById
     * @param id
     * @return status
     *
     */
    @Override
    public String deleteById(Long id) {
        CmDiagnosisHistAllergy item = cmDiagnosisHistAllergyMapper.selectByPrimaryKey(id);
        if (item != null) {
            cmDiagnosisHistAllergyMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}