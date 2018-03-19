/**   
 * @Title: CmDiagnosisEntityValueServiceImpl.java 
 * @Package com.xtt.txgl.report.service.impl
 * Copyright: Copyright (c) 2015
 * @author: tcy   
 * @date: 2018年3月13日 下午7:54:00 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.CmDiagnosisEntityValueMapper;
import com.xtt.common.dao.po.CmDiagnosisEntityValuePO;
import com.xtt.common.diagnosis.service.ICmDiagnosisEntityValueService;

@Service
public class CmDiagnosisEntityValueServiceImpl implements ICmDiagnosisEntityValueService {
    @Autowired
    private CmDiagnosisEntityValueMapper cmDiagnosisEntityValueMapper;

    @Override
    public List<CmDiagnosisEntityValuePO> countByCondtion(Map<String, Object> map) {

        return cmDiagnosisEntityValueMapper.countByCondtion(map);
    }

    @Override
    public CmDiagnosisEntityValuePO getItemCodeByYear() {
        return cmDiagnosisEntityValueMapper.getItemCodeByYear();
    }

    @Override
    public Integer countAllDiagnosis(String year, Integer tenantId) {
        return cmDiagnosisEntityValueMapper.countAllDiagnosis(year, tenantId);
    }

    @Override
    public List<CmDiagnosisEntityValuePO> countYearDiagnosisByCondition(Map<String, Object> map) {

        return cmDiagnosisEntityValueMapper.countYearDiagnosisByCondition(map);
    }

}
