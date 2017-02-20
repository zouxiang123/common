/**   
 * @Title: PatientAssayRecordServiceImpl.java 
 * @Package com.xtt.txgl.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:54 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayRecordServiceImpl implements IPatientAssayRecordService {
    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;

    @Override
    public List<PatientAssayRecordPO> getAssayDateRecord(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectDateAssayRecord(record);
        return list;
    }

    @Override
    public List<Map<String, Object>> getReportData(Long patientId, Date startTime, Date endTime, String itemCode) {
        return patientAssayRecordMapper.selectReportData(patientId, startTime, endTime, itemCode);
    }

    @Override
    public List<PatientAssayRecordPO> getByCondition(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectByCondition(record);
    }

    @Override
    public List<PatientAssayRecordPO> getByAssayDate(String date, Long patientId) {
        PatientAssayRecordPO record = new PatientAssayRecordPO();
        record.setFkTenantId(UserUtil.getTenantId());
        record.setAssayDate(date);
        record.setFkPatientId(patientId);
        return patientAssayRecordMapper.selectByCondition(record);
    }

    @Override
    public List<PatientAssayRecordPO> selectReportByAssayTime(PatientAssayRecordPO patientAssayRecord) {

        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectReportByAssayTime(patientAssayRecord);
        if (list == null) {
            list = new ArrayList<PatientAssayRecordPO>();
        }
        return list;
    }

    @Override
    public List<PatientAssayRecordPO> selectStatisticsReport(PatientAssayRecordPO patientAssayRecord) {

        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectStatisticsReport(patientAssayRecord);
        if (list == null) {
            list = new ArrayList<PatientAssayRecordPO>();
        }
        return list;
    }

    @Deprecated
    @Override
    public List<Map<String, Object>> getCategoryListByPatientId(Long patientId) {
        return patientAssayRecordMapper.selectCategoryListByPatientId(patientId, UserUtil.getTenantId());
    }

    @Override
    public List<PatientAssayRecordPO> getCategoryList(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectCategoryList(record);
    }

    @Override
    public List<PatientAssayRecordPO> selectByMonth(PatientAssayRecordPO patientAssayRecord) {
        List<PatientAssayRecordPO> list = patientAssayRecordMapper.selectByMonth(patientAssayRecord);
        if (list == null) {
            list = new ArrayList<PatientAssayRecordPO>();
        }
        return init(list);
    }

    private List<PatientAssayRecordPO> init(List<PatientAssayRecordPO> list) {
        /*for (PatientAssayRecordPO item : list) {
        	item.setResultTipsShow(
        					DictionaryUtil.getName(DictionaryConstants.IS_OR_NOT, ("1".equals(item.getResultTips()) ? item.getResultTips() : "0")));
        }*/

        return list;
    }

    @Override
    public List<PatientAssayRecordPO> selectByFkDictCode(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectByFkDictCode(record);
    }

    @Override
    public List<PatientAssayRecordPO> selectItemLatestDataByCondition(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectItemLatestDataByCondition(record);
    }
}
