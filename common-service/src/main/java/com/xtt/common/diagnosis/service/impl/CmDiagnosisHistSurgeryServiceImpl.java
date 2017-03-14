/**   
 * @Title: CmDiagnosisHistSurgeryServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:43:38 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistSurgeryMapper;
import com.xtt.common.dao.model.CmDiagnosisHistSurgery;
import com.xtt.common.dao.po.CmDiagnosisHistSurgeryPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistSurgeryService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistSurgeryServiceImpl implements ICmDiagnosisHistSurgeryService {

    @Autowired
    private CmDiagnosisHistSurgeryMapper cmDiagnosisHistSurgeryMapper;

    @Override
    public CmDiagnosisHistSurgeryPO selectById(Long id) {
        return cmDiagnosisHistSurgeryMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据患者Id获取患者诊断之手术史集合数据
     * 
     * @Title: selectSurgeriesByPatient
     * @param patientId
     * @return
     *
     */
    @Override
    public List<CmDiagnosisHistSurgeryPO> selectSurgeriesByPatient(Long patientId) {
        // TODO Auto-generated method stub
        return cmDiagnosisHistSurgeryMapper.selectByPatient(patientId);
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
    public String saveItem(CmDiagnosisHistSurgeryPO record) {
        // TODO Auto-generated method stub
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisHistSurgeryMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisHistSurgeryMapper.updateByPrimaryKeySelective(record);
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
        // TODO Auto-generated method stub
        CmDiagnosisHistSurgery item = cmDiagnosisHistSurgeryMapper.selectByPrimaryKey(id);
        if (item != null) {
            cmDiagnosisHistSurgeryMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}