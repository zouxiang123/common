/**   
 * @Title: PatientAssayRecordServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:54 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayRecordBusiServiceImpl implements IPatientAssayRecordBusiService {

    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;

    @Override
    public List<PatientAssayRecordBusiPO> listByCondition(PatientAssayRecordBusiPO record) {
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        return patientAssayRecordBusiMapper.listByCondition(record);
    }

    @Override
    public List<PatientAssayRecordBusiPO> listCategory(PatientAssayRecordBusiPO record) {
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        return patientAssayRecordBusiMapper.listCategory(record);
    }

    @Override
    public List<Map<String, Object>> listReportData(Long fkPatientId, Date startDate, Date endDate, String itemCode, Collection<String> itemCodes) {
        return patientAssayRecordBusiMapper.listReportData(fkPatientId, startDate, endDate, itemCode, itemCodes);
    }

    @Override
    public List<Map<String, Object>> listApiAssayList(Long patientId, Date startDate, Date endDate) {
        return patientAssayRecordBusiMapper.listApiAssayList(patientId, startDate, endDate);
    }

    @Override
    public List<PatientAssayRecordBusi> listApiAssayItems(Long patientId, Date assayDate) {
        return patientAssayRecordBusiMapper.listApiAssayItems(patientId, assayDate);
    }

    @Override
    public List<PatientAssayRecordBusiPO> listByFkDictCode(PatientAssayRecordBusiPO record) {
        record.setQueryOrderBy(2);
        return listByCondition(record);
    }

    @Override
    public List<PatientAssayRecordBusiPO> listByItemCodes(Set<String> itemCodes, Date startDate, Date endDate) {
        PatientAssayRecordBusiPO record = new PatientAssayRecordBusiPO();
        record.setItemCodes(itemCodes);
        record.setStartDate(startDate);
        record.setEndDate(endDate);
        record.setQueryOrderBy(1);
        return listByCondition(record);
    }

    @Override
    public List<PatientAssayRecordBusiPO> listByGroupId(PatientAssayRecordBusiPO record) {
        return listByCondition(record);
    }

    @Override
    public void removeById(Long id) {
        patientAssayRecordBusiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map<String, Object>> listForPersonReport(Long patientId, Date startDate, Date endDate, String itemCode) {
        PatientAssayRecordBusiPO record = new PatientAssayRecordBusiPO();
        record.setFkPatientId(patientId);
        record.setStartDate(startDate);
        record.setEndDate(endDate);
        record.setItemCode(itemCode);
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordBusiMapper.listForPersonReport(record);
    }

    @Override
    public int countByInspectionId(String inspectionId) {

        return patientAssayRecordBusiMapper.countByInspectionId(inspectionId);
    }

    @Override
    public void insertList(List<PatientAssayRecordBusi> listPatientAssayRecordBusi) {
        patientAssayRecordBusiMapper.insertList(listPatientAssayRecordBusi);

    }

    @Override
    public void updateDiaAbFlagByReqId(PatientAssayRecordBusi patientAssayRecordBusi) {
        patientAssayRecordBusiMapper.updateDiaAbFlagByReqId(patientAssayRecordBusi);

    }

    @Override
    public List<String> listAllAssayMonthByTenantId(Integer tenantId) {
        return patientAssayRecordBusiMapper.listAllAssayMonthByTenantId(tenantId);
    }

    @Override
    public List<PatientAssayRecordBusiPO> listForBeforeAfterReport(PatientAssayRecordBusiPO record) {
        return patientAssayRecordBusiMapper.listForBeforeAfterReport(record);
    }

    @Override
    public List<PatientAssayRecordBusiPO> listLatestByFkDictCodes(Long fkPatientId, Collection<String> fkDictCodes, Integer tenantId, Date date) {
        return patientAssayRecordBusiMapper.listLatestByFkDictCodes(fkPatientId, fkDictCodes, tenantId, date);
    }

}
