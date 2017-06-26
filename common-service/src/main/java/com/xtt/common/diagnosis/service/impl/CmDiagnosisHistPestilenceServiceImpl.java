/**   
 * @Title: CmDiagnosisHistPestilenceServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:45:14 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistPestilenceMapper;
import com.xtt.common.dao.model.CmDiagnosisHistPestilence;
import com.xtt.common.dao.po.CmDiagnosisHistPestilencePO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistPestilenceService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistPestilenceServiceImpl implements ICmDiagnosisHistPestilenceService {

    @Autowired
    private CmDiagnosisHistPestilenceMapper cmDiagnosisHistPestilenceMapper;

    @Override
    public CmDiagnosisHistPestilencePO selectById(Long id) {
        return cmDiagnosisHistPestilenceMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据患者Id获取患者诊断之传染病史集合数据
     * 
     * @Title: selectPestilencesByPatient
     * @param patientId
     * @return
     *
     */
    @Override
    public List<CmDiagnosisHistPestilencePO> selectPestilencesByPatient(Long patientId) {
        return cmDiagnosisHistPestilenceMapper.selectByPatient(patientId, UserUtil.getGroupTenant());
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
    public String saveItem(CmDiagnosisHistPestilencePO record) {
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisHistPestilenceMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisHistPestilenceMapper.updateByPrimaryKeySelective(record);
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
        CmDiagnosisHistPestilence item = cmDiagnosisHistPestilenceMapper.selectByPrimaryKey(id);
        if (item != null) {
            cmDiagnosisHistPestilenceMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}