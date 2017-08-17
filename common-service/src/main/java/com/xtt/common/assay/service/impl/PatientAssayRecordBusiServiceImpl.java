/**   
 * @Title: PatientAssayRecordServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:54 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.hand.FivePatientAssayRecordBusiFactory;
import com.xtt.common.assay.hand.FourPatientAssayRecordBusiFactory;
import com.xtt.common.assay.hand.OnePatientAssayRecordBusiFactory;
import com.xtt.common.assay.hand.ThreePatientAssayRecordBusiFactory;
import com.xtt.common.assay.hand.TwoPatientAssayRecordBusiFactory;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayRecordBusiServiceImpl implements IPatientAssayRecordBusiService {

    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;
    @Autowired
    private OnePatientAssayRecordBusiFactory onePatientAssayRecordBusiFactory;
    @Autowired
    private TwoPatientAssayRecordBusiFactory twoPatientAssayRecordBusiFactory;
    @Autowired
    private ThreePatientAssayRecordBusiFactory threePatientAssayRecordBusiFactory;
    @Autowired
    private FourPatientAssayRecordBusiFactory fourPatientAssayRecordBusiFactory;
    @Autowired
    private FivePatientAssayRecordBusiFactory fivePatientAssayRecordBusiFactory;

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
    public List<PatientAssayRecordBusiPO> listLatestOneByFkDictCodes(Long fkPatientId, Collection<String> fkDictCodes, Integer tenantId, Date date) {
        return patientAssayRecordBusiMapper.listLatestOneByFkDictCodes(fkPatientId, fkDictCodes, tenantId, date);
    }

    @Override
    public void updatePatientAssay(List<DictHospitalLabPO> getdHL) {
        patientAssayRecordBusiMapper.updatePatientAssay(getdHL);

    }

    @Override
    public void insertPatientAssay(List<DictHospitalLabPO> getdHL) {
        patientAssayRecordBusiMapper.insertPatientAssay(getdHL);

    }

    @Override
    public void delteteAll() {
        patientAssayRecordBusiMapper.delteteAll();

    }

    @Override
    public void deleteByPatientId(Long fkPatientId) {
        patientAssayRecordBusiMapper.deleteByPatientId(fkPatientId);

    }

    @Override
    public void save(Date startCreateTime, Date endCreateTime, Map<Long, List<Date>> mapPatientId, Long patientId, boolean isDelete,
                    Integer fktenantId) {
        UserUtil.setThreadTenant(fktenantId);
        String labAfterBefore = SysParamUtil.getValueByName(UserUtil.getTenantId(), AssayConsts.LAB_AFTER_BEFORE);
        DateUtil.add(new Date(), 2, -1);
        switch (labAfterBefore) {
        case "1":
            onePatientAssayRecordBusiFactory.save(startCreateTime, endCreateTime);
            break;
        case "2":
            twoPatientAssayRecordBusiFactory.save(startCreateTime, endCreateTime);
            break;
        case "3":
            threePatientAssayRecordBusiFactory.save(startCreateTime, endCreateTime);
            break;
        case "4":
            fourPatientAssayRecordBusiFactory.save(startCreateTime, endCreateTime);
            break;
        case "5":
            // 是否删除
            if (isDelete) {
                if (mapPatientId == null) {
                    fivePatientAssayRecordBusiFactory.deleteAll();
                    fivePatientAssayRecordBusiFactory.save(DateUtil.add(new Date(), 2, -10), new Date());
                    fivePatientAssayRecordBusiFactory.updateBaskDiaAbFlag();
                } else {
                    fivePatientAssayRecordBusiFactory.deleteByPatient(patientId);
                }
            } else {
                fivePatientAssayRecordBusiFactory.save(DateUtil.add(new Date(), 2, -10), new Date());
                fivePatientAssayRecordBusiFactory.cleanDate(mapPatientId);
            }
            break;

        default:
            break;
        }
    }

    @Override
    public List<PatientAssayRecordBusiPO> listLatestByFkDictCode(Long fkPatientId, String dictCode, Integer tenantId, Date date, int count) {
        List<Map<String, Object>> paramList = new ArrayList<>(2);
        Map<String, Object> param = new HashMap<>();
        param.put("fkPatientId", fkPatientId);
        param.put("fkDictCode", dictCode);
        param.put("fkTenantId", tenantId);
        param.put("count", count);
        param.put("date", date);
        param.put("isBefore", true);
        paramList.add(param);
        Map<String, Object> paramAfter = new HashMap<>(param.size());
        paramAfter.putAll(param);
        paramAfter.put("isBefore", false);
        paramList.add(paramAfter);
        return patientAssayRecordBusiMapper.listLatestByFkDictCode(paramList);
    }

}
