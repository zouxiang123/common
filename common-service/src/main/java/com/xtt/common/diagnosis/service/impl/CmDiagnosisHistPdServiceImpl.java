/**   
 * @Title: CmDiagnosisHistPdServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:43:08 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistPdMapper;
import com.xtt.common.dao.model.CmDiagnosisHistPd;
import com.xtt.common.dao.po.CmDiagnosisHistPdPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistPdService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistPdServiceImpl implements ICmDiagnosisHistPdService {

    @Autowired
    private CmDiagnosisHistPdMapper cmDiagnosisHistPdMapper;

    @Override
    public CmDiagnosisHistPdPO selectById(Long id) {
        return cmDiagnosisHistPdMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据患者Id获取患者诊断之腹透史集合数据
     * 
     * @Title: selectPdsByPatient
     * @param patientId
     * @return
     *
     */
    @Override
    public List<CmDiagnosisHistPdPO> selectPdsByPatient(Long patientId) {
        return cmDiagnosisHistPdMapper.selectByPatient(patientId, UserUtil.getMultiTenant());
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
    public String saveItem(CmDiagnosisHistPdPO record) {
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisHistPdMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisHistPdMapper.updateByPrimaryKeySelective(record);
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
        CmDiagnosisHistPd item = cmDiagnosisHistPdMapper.selectByPrimaryKey(id);
        if (item != null) {
            cmDiagnosisHistPdMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}