/**   
 * @Title: PatientAssayDataServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年12月5日 下午2:54:14 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayDataService;
import com.xtt.common.dao.mapper.PatientAssayDataMapper;
import com.xtt.common.dao.model.DictHospitalLab;
import com.xtt.common.dao.model.PatientAssayData;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.po.PatientAssayDataPO;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayDataServiceImpl implements IPatientAssayDataService {

    @Autowired
    private PatientAssayDataMapper patientAssayDataMapper;

    @Override
    public List<PatientAssayData> findByPatientAssayData(PatientAssayData patientAssayData) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PatientAssayData save(PatientAssayData patientAssayData) {
        patientAssayDataMapper.insertSelective(patientAssayData);
        return patientAssayData;
    }

    @Override
    public void patientAssayRecordTransformationPatientAssayData(Date transformationDate) {
        // 无效日期
        if (transformationDate == null) {
            return;
        }
        String endDate = DateFormatUtil.convertDateToStr(DateUtil.addDays(transformationDate, -1), "yyyy-MM-dd");

        // 获取部署医院化验项常量 数据类型 [快速]
        List<DictHospitalLab> dictHospitalLabArrays = patientAssayDataMapper.selectByDictHospitalLab();
        Map<String, Integer> dictHospitalLabMap = new HashMap<String, Integer>();
        if (CollectionUtils.isEmpty(dictHospitalLabArrays)) {
            return;
        }

        for (DictHospitalLab dhl : dictHospitalLabArrays) {
            dictHospitalLabMap.put(dhl.getItemCode(), dhl.getValueType());
        }

        int patientAssayDataCount = patientAssayDataMapper.selectByPatientAssayDataCount(null);
        // 同步全部数据[运行一次]
        if (patientAssayDataCount == 0) {

            // 获取当前多少页数据
            String minDate = patientAssayDataMapper.selectByPatientAssayRecordMinDate();
            String maxDate = endDate;
            int pageNo = 1;

            // 查询满足条件的数据
            PatientAssayDataPO patientAssayDataPO = new PatientAssayDataPO();
            patientAssayDataPO.setStartTime(minDate);
            patientAssayDataPO.setEndTime(maxDate);
            patientAssayDataPO.setIspaging(true);
            patientAssayDataPO.setPageNo(1);
            patientAssayDataPO.setPageSize(100);

            List<PatientAssayRecord> patientAssayRecordArrays = patientAssayDataMapper.findByPatientAssayRecord(patientAssayDataPO);
            while (true) {
                if (CollectionUtils.isNotEmpty(patientAssayRecordArrays)) {
                    for (PatientAssayRecord record : patientAssayRecordArrays) {
                        savePatientAssayData(record, dictHospitalLabMap);
                    }
                    pageNo++;
                    patientAssayRecordArrays.clear();
                    // 继续获取未处理的数据
                    patientAssayDataPO.setPageNo(pageNo);
                    patientAssayRecordArrays = patientAssayDataMapper.findByPatientAssayRecord(patientAssayDataPO);

                } else {
                    break;
                }
            }
        } else {
            // 只同步当前日期数据
            int currentDateCount = patientAssayDataMapper.selectByPatientAssayDataCount(endDate);
            if (currentDateCount == 0) {
                PatientAssayDataPO patientAssayDataPO = new PatientAssayDataPO();
                patientAssayDataPO.setStartTime(endDate);
                List<PatientAssayRecord> patientAssayRecordArrays = patientAssayDataMapper.findByPatientAssayRecord(patientAssayDataPO);
                if (CollectionUtils.isNotEmpty(patientAssayRecordArrays)) {
                    for (PatientAssayRecord record : patientAssayRecordArrays) {
                        savePatientAssayData(record, dictHospitalLabMap);
                    }
                }
            }
        }
    }

    private void savePatientAssayData(PatientAssayRecord record, Map<String, Integer> dictHospitalLabMap) {
        PatientAssayData data = new PatientAssayData();
        data.setFkTenantId(record.getFkTenantId());
        data.setFkPatientId(record.getFkPatientId());
        data.setFkAssayRecordId(record.getId());
        data.setGroupId(record.getGroupId());
        data.setGroupName(record.getGroupName());
        data.setItemCode(record.getItemCode());
        data.setItemName(record.getItemName());
        data.setAssayTime(record.getSampleTime());
        data.setAssayTimeDay(Integer.valueOf(DateFormatUtil.convertDateToStr(new Date(), "yyyyMMdd")));
        data.setAssayTimeMonth(Integer.valueOf(DateFormatUtil.convertDateToStr(new Date(), "yyyyMM")));
        data.setAssayTimeYear(Integer.valueOf(DateFormatUtil.convertDateToStr(new Date(), "yyyy")));
        data.setAssayVal(record.getResult());
        data.setAssayValNum(record.getResultActual());
        data.setAssayCase(record.getResultTips());
        data.setSendDoctor(record.getSendDoctor());
        data.setCheckPerson(record.getCheckPerson());
        data.setCreateTime(new Date());
        data.setCreateUserId(0l);
        data.setUpdateTime(new Date());
        data.setUpdateUserId(0l);

        // 数据类型
        if (dictHospitalLabMap.containsKey(record.getItemCode())) {
            data.setAssayValType(dictHospitalLabMap.get(record.getItemCode()));
        } else {
            data.setAssayValType(0);
        }

        patientAssayDataMapper.insertSelective(data);
    }

}
