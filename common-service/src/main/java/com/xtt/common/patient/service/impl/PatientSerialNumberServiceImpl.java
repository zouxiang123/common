package com.xtt.common.patient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.PatientSerialNumberMapper;
import com.xtt.common.dao.po.PatientSerialNumberPO;
import com.xtt.common.patient.service.IPatientSerialNumberService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientSerialNumberServiceImpl implements IPatientSerialNumberService {

    @Autowired
    private PatientSerialNumberMapper patientSerialNumberMapper;

    /**
     * 根据条件查询
     */
    @Override
    public List<PatientSerialNumberPO> selectByCondition(PatientSerialNumberPO patientSerialNumberPO) {
        return patientSerialNumberMapper.selectByCondition(patientSerialNumberPO);
    }

    /**
     * 根据SerialNum进行编辑
     */
    @Override
    public void updateBySerialNum(PatientSerialNumberPO patientSerialNumberPO) {
        patientSerialNumberMapper.updateBySerialNum(patientSerialNumberPO);
    }

    /**
     * 如果所有的患者序列号用完了，则新增一条数据
     */
    @Override
    public void insert() {
        PatientSerialNumberPO patientSerialNumberPO = new PatientSerialNumberPO();
        DataUtil.setSystemFieldValue(patientSerialNumberPO);
        patientSerialNumberPO.setFkTenantId(UserUtil.getTenantId());
        patientSerialNumberPO.setIsUse(false);
        patientSerialNumberPO.setSerialNum(getNextSerialNum());
        patientSerialNumberMapper.insert(patientSerialNumberPO);
    }

    // 获得下一个患者序列号
    private String getNextSerialNum() {
        PatientSerialNumberPO patientSerialNumberPO = new PatientSerialNumberPO();
        patientSerialNumberPO.setIsOrderBy(1);
        List<PatientSerialNumberPO> psnList = patientSerialNumberMapper.selectByCondition(patientSerialNumberPO);
        if (psnList != null && psnList.size() > 0) {
            return Integer.parseInt(psnList.get(0).getSerialNum()) + 1 + "";
        } else {
            return "1";
        }
    }
}
