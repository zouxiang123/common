/**   
 * @Title: CmDiagnosisHistHdServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:44:03 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistHdMapper;
import com.xtt.common.dao.model.CmDiagnosisHistHd;
import com.xtt.common.dao.po.CmDiagnosisHistHdPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistHdService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistHdServiceImpl implements ICmDiagnosisHistHdService {

    @Autowired
    private CmDiagnosisHistHdMapper cmDiagnosisHistHdMapper;

    @Override
    public CmDiagnosisHistHdPO selectById(Long id) {
        return cmDiagnosisHistHdMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据患者Id获取患者诊断之血透史集合数据
     * 
     * @Title: selectHdsByPatient
     * @param patientId
     * @return
     *
     */
    @Override
    public List<CmDiagnosisHistHdPO> selectHdsByPatient(Long patientId) {
        return cmDiagnosisHistHdMapper.selectByPatient(patientId, UserUtil.getMultiTenant());
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
    public String saveItem(CmDiagnosisHistHdPO record) {
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisHistHdMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisHistHdMapper.updateByPrimaryKeySelective(record);
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
        CmDiagnosisHistHd item = cmDiagnosisHistHdMapper.selectByPrimaryKey(id);
        if (item != null) {
            cmDiagnosisHistHdMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}