/**   
 * @Title: CmDiagnosisHistServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: jackie   
 * @date: 2016年12月1日 下午7:53:28 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistMapper;
import com.xtt.common.dao.model.CmDiagnosisHist;
import com.xtt.common.dao.po.CmDiagnosisHistPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistServiceImpl implements ICmDiagnosisHistService {

    @Autowired
    private CmDiagnosisHistMapper cmDiagnosisHistMapper;

    /**
     * 根据患者Id获取患者诊断之询问病史数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @return
     *
     */
    @Override
    public List<CmDiagnosisHistPO> selectByPatient(Long patientId) {
        // TODO Auto-generated method stub
        return cmDiagnosisHistMapper.selectByPatient(patientId);
    }

    /**
     * 保存患者诊断的病史主表数据（包括内容：首次透析日期和首次透析类型固定属性）
     */
    @Override
    public String updateCmDiagnosisHist(CmDiagnosisHist record) {
        // TODO Auto-generated method stub
        if (record.getId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDiagnosisHistMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDiagnosisHistMapper.updateByPrimaryKeySelective(record);
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public CmDiagnosisHistPO selectById(Long id) {
        // TODO Auto-generated method stub
        return cmDiagnosisHistMapper.selectByPrimaryKey(id);
    }

    @Override
    public String deleteById(Long id) {
        // TODO Auto-generated method stub
        CmDiagnosisHistPO item = cmDiagnosisHistMapper.selectByPrimaryKey(id);
        if (item != null) {
            cmDiagnosisHistMapper.deleteByPrimaryKey(item.getId());
            return CommonConstants.SUCCESS;
        } else {
            return CommonConstants.FAILURE;
        }
    }

}