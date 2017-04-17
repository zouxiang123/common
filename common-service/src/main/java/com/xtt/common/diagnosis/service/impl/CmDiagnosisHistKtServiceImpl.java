/**   
 * @Title: CmDiagnosisHistKtServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:44:26 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistKtMapper;
import com.xtt.common.dao.model.CmDiagnosisHistKt;
import com.xtt.common.dao.po.CmDiagnosisHistKtPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistKtService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistKtServiceImpl implements ICmDiagnosisHistKtService {

    @Autowired
    private CmDiagnosisHistKtMapper cmDiagnosisHistKtMapper;

    @Override
    public CmDiagnosisHistKtPO selectById(Long id) {
        return cmDiagnosisHistKtMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据患者Id获取患者诊断之肾移植史集合数据
     * 
     * @Title: selectKtsByPatient
     * @param patientId
     * @return
     *
     */
    @Override
    public List<CmDiagnosisHistKtPO> selectKtsByPatient(Long patientId) {
        return cmDiagnosisHistKtMapper.selectByPatient(patientId, UserUtil.getMultiTenant());
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
    public String saveItem(CmDiagnosisHistKtPO record) {
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisHistKtMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisHistKtMapper.updateByPrimaryKeySelective(record);
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
        CmDiagnosisHistKt item = cmDiagnosisHistKtMapper.selectByPrimaryKey(id);
        if (item != null) {
            cmDiagnosisHistKtMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}