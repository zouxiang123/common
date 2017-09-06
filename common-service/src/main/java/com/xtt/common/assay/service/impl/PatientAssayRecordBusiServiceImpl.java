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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.hand.AssayHandDelete;
import com.xtt.common.assay.hand.AssayHandFactory;
import com.xtt.common.assay.hand.AssayHandFour;
import com.xtt.common.assay.hand.AssayHandOne;
import com.xtt.common.assay.hand.AssayHandThree;
import com.xtt.common.assay.hand.AssayHandTwo;
import com.xtt.common.assay.service.IAssayFilterRuleService;
import com.xtt.common.assay.service.IAssayGroupService;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayRecordBusiServiceImpl implements IPatientAssayRecordBusiService {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;

    @Autowired
    private IAssayFilterRuleService assayFilterRuleService;

    @Autowired
    private IAssayHospDictService assayHospDictService;
    @Autowired
    private IAssayGroupService assayGroupService;

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
        if (StringUtil.isNotBlank(record.getStrStartDate())) {
            record.setStartDate(DateFormatUtil.getStartTime(record.getStrStartDate()));
        }
        if (StringUtil.isNotBlank(record.getStrEndDate())) {
            record.setEndDate(DateFormatUtil.getEndTime(record.getStrEndDate()));
        }
        List<PatientAssayRecordBusiPO> listAssayRecordBusi = patientAssayRecordBusiMapper.listCategory(record);
        for (PatientAssayRecordBusiPO patientAssayRecordBusi : listAssayRecordBusi) {
            patientAssayRecordBusi.setStrSampleTime(DateUtil.format(patientAssayRecordBusi.getSampleTime(), "yyyy-MM-dd HH:mm"));
            patientAssayRecordBusi.setStrReportTime(DateUtil.format(patientAssayRecordBusi.getReportTime(), "yyyy-MM-dd HH:mm"));
        }
        return listAssayRecordBusi;
    }

    @Override
    public List<Map<String, Object>> listReportData(Long fkPatientId, Date startDate, Date endDate, String itemCode) {
        Set<String> groupItemCodes = assayGroupService.listGroupItemCodes(itemCode, UserUtil.getTenantId());
        if (CollectionUtils.isNotEmpty(groupItemCodes)) {
            itemCode = null;
        }
        return patientAssayRecordBusiMapper.listReportData(fkPatientId, startDate, endDate, itemCode, groupItemCodes);
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
    public void removeById(Long id) {
        patientAssayRecordBusiMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map<String, Object>> listForPersonReport(Long patientId, Date startDate, Date endDate, String itemCode) {
        Set<String> groupItemCodes = assayGroupService.listGroupItemCodes(itemCode, UserUtil.getTenantId());
        if (CollectionUtils.isNotEmpty(groupItemCodes)) {
            itemCode = null;
        }
        PatientAssayRecordBusiPO record = new PatientAssayRecordBusiPO();
        record.setFkPatientId(patientId);
        record.setStartDate(startDate);
        record.setEndDate(endDate);
        record.setItemCode(itemCode);
        record.setItemCodes(groupItemCodes);
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordBusiMapper.listForPersonReport(record);
    }

    @Override
    public int countByInspectionId(String inspectionId, Integer fkTenantId) {

        return patientAssayRecordBusiMapper.countByInspectionId(inspectionId, fkTenantId);
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
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        return patientAssayRecordBusiMapper.listForBeforeAfterReport(record);
    }

    @Override
    public List<PatientAssayRecordBusiPO> listLatestOneByFkDictCodes(Long fkPatientId, Collection<String> fkDictCodes, Integer tenantId, Date date,
                    Boolean isBefore) {
        return patientAssayRecordBusiMapper.listLatestOneByFkDictCodes(fkPatientId, fkDictCodes, tenantId, date,
                        isBefore == null ? null : (isBefore ? AssayConsts.BEFORE_HD : AssayConsts.AFTER_HD));
    }

    @Override
    public void updatePatientAssay(List<AssayHospDictPO> getdHL) {
        PatientAssayRecordBusi patientAssayRecordBusi = new PatientAssayRecordBusi();
        String result;
        for (AssayHospDictPO dictHospitalLab : getdHL) {
            String assayDateStr = dictHospitalLab.getAssayDateStr();
            patientAssayRecordBusi.setId(dictHospitalLab.getId());
            patientAssayRecordBusi.setReqId(dictHospitalLab.getReqId());
            result = dictHospitalLab.getResult();
            if (dictHospitalLab.getMinValue().doubleValue() > Double.valueOf(result)) {
                patientAssayRecordBusi.setResultTips(AssayConsts.TIPS_LOW);
            }
            if (dictHospitalLab.getMaxValue().doubleValue() < Double.valueOf(result)) {
                patientAssayRecordBusi.setResultTips(AssayConsts.TIPS_HIGH);
            }
            if (dictHospitalLab.getMinValue().doubleValue() < Double.valueOf(result)
                            && dictHospitalLab.getMaxValue().doubleValue() > Double.valueOf(result)) {
                patientAssayRecordBusi.setResultTips(AssayConsts.TIPS_NORMAL);
            }
            patientAssayRecordBusi.setResult(result);
            patientAssayRecordBusi.setResultActual(Double.valueOf(result));
            patientAssayRecordBusi.setReportTime(DateFormatUtil.getStartTime(assayDateStr));
            patientAssayRecordBusi.setSampleTime(DateFormatUtil.getStartTime(assayDateStr));
            patientAssayRecordBusi.setAssayDate(DateFormatUtil.getStartTime(assayDateStr));
            patientAssayRecordBusi.setAssayMonth(DateUtil.format(DateFormatUtil.getStartTime(assayDateStr), DateFormatUtil.FORMAT_YYYY_MM));
            patientAssayRecordBusi.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setUpdateSystemFieldValue(patientAssayRecordBusi);
            patientAssayRecordBusiMapper.updatePatientAssay(patientAssayRecordBusi);
        }
    }

    @Override
    public PatientAssayRecordBusiPO getLatestOneByFkDictCode(Long fkPatientId, String fkDictCode, Integer tenantId, Date date, Boolean isBefore) {
        List<String> codes = new ArrayList<>(1);
        codes.add(fkDictCode);
        List<PatientAssayRecordBusiPO> list = listLatestOneByFkDictCodes(fkPatientId, codes, tenantId, date, isBefore);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public void insertPatientAssay(List<AssayHospDictPO> getdHL) {
        String strRandom = RandomStringUtils.randomAlphanumeric(6);
        for (AssayHospDictPO assayHospDictPO : getdHL) {
            String assayDateStr = assayHospDictPO.getAssayDateStr();
            if (StringUtil.isEmpty(assayHospDictPO.getResult())) {
                continue;
            }
            String result = assayHospDictPO.getResult();
            assayHospDictPO.setFkTenantId(UserUtil.getTenantId());
            AssayHospDictPO dictHospitalLab = assayHospDictService.selectTop(assayHospDictPO);
            PatientAssayRecordBusi patientAssayRecordBusi = new PatientAssayRecordBusi();
            patientAssayRecordBusi.setFkPatientId(assayHospDictPO.getFkPatientId());
            patientAssayRecordBusi.setGroupId(dictHospitalLab.getGroupId());
            patientAssayRecordBusi.setGroupName(dictHospitalLab.getGroupName());
            patientAssayRecordBusi.setReportTime(dictHospitalLab.getAssayDate());
            patientAssayRecordBusi.setItemCode(dictHospitalLab.getItemCode());
            patientAssayRecordBusi.setItemName(dictHospitalLab.getItemName());
            patientAssayRecordBusi.setResult(assayHospDictPO.getResult());
            patientAssayRecordBusi.setResultActual(Double.valueOf(result));
            patientAssayRecordBusi.setReference(dictHospitalLab.getReference());
            patientAssayRecordBusi.setValueUnit(dictHospitalLab.getUnit());
            patientAssayRecordBusi.setAssayDate(DateFormatUtil.getStartTime(assayDateStr));
            patientAssayRecordBusi.setAssayMonth(DateUtil.format(DateFormatUtil.getStartTime(assayDateStr), DateFormatUtil.FORMAT_YYYY_MM));
            patientAssayRecordBusi.setFlage(true);
            patientAssayRecordBusi.setDiaAbFlag(AssayConsts.BEFORE_HD);

            if (dictHospitalLab.getMinValue().doubleValue() > Double.valueOf(assayHospDictPO.getResult())) {
                patientAssayRecordBusi.setResultTips(AssayConsts.TIPS_LOW);
            }
            if (dictHospitalLab.getMaxValue().doubleValue() < Double.valueOf(assayHospDictPO.getResult())) {
                patientAssayRecordBusi.setResultTips(AssayConsts.TIPS_HIGH);
            }
            if (dictHospitalLab.getMinValue().doubleValue() < Double.valueOf(assayHospDictPO.getResult())
                            && dictHospitalLab.getMaxValue().doubleValue() > Double.valueOf(assayHospDictPO.getResult())) {
                patientAssayRecordBusi.setResultTips(AssayConsts.TIPS_NORMAL);
            }
            patientAssayRecordBusi.setSampleTime(DateFormatUtil.getStartTime(assayDateStr));
            patientAssayRecordBusi.setReportTime(DateFormatUtil.getStartTime(assayDateStr));
            patientAssayRecordBusi.setFkTenantId(UserUtil.getTenantId());
            patientAssayRecordBusi.setReqId(assayDateStr.concat("_").concat(strRandom));
            patientAssayRecordBusi.setInspectionId(patientAssayRecordBusi.getReqId().concat(RandomStringUtils.randomAlphanumeric(7)));
            DataUtil.setSystemFieldValue(patientAssayRecordBusi);
            patientAssayRecordBusiMapper.insertSelective(patientAssayRecordBusi);
        }
    }

    @Override
    public void deleteByPatientId(Long fkPatientId, Integer fkTenantId) {
        patientAssayRecordBusiMapper.deleteByPatientId(fkPatientId, fkTenantId);

    }

    @Override
    public void selectInsertFromSource(Date startCreateTime, Date endCreateTime, Map<Long, List<Date>> mapPatientId, Long patientId, boolean isDelete,
                    Integer fkTenantId) {
        UserUtil.setThreadTenant(fkTenantId);
        AssayFilterRule assayFilterRule = assayFilterRuleService.getAssayFilterRuleByTenantId(UserUtil.getTenantId());
        if (assayFilterRule == null) {
            LOGGER.error("assay_filter_rule表中category字段为空");
            return;
        }
        AssayHandFactory assayHandFactory = null;
        if (isDelete) {// 如果是删除操作，不需要清洗，只需重新把透析透后标识带过来；
            deleteByPatientId(patientId, fkTenantId);
            assayHandFactory = new AssayHandDelete();
        } else {
            switch (assayFilterRule.getCategory()) {
            case AssayConsts.LAB_AFTER_BEFORE_ONE:
                assayHandFactory = new AssayHandOne();
                break;
            case AssayConsts.LAB_AFTER_BEFORE_TWO:
                assayHandFactory = new AssayHandTwo();
                break;
            case AssayConsts.LAB_AFTER_BEFORE_THREE:
                assayHandFactory = new AssayHandThree();
                break;
            case AssayConsts.LAB_AFTER_BEFORE_FOUR:
                assayHandFactory = new AssayHandFour();
                break;
            default:
                break;
            }
        }
        if (assayHandFactory != null) {
            assayHandFactory.save(startCreateTime, endCreateTime, patientId, mapPatientId);
        }
    }

    @Override
    public List<PatientAssayRecordBusiPO> listLatestByFkDictCode(Long fkPatientId, String dictCode, Integer tenantId, Date date, int count,
                    String diaAbFlag) {
        List<Map<String, Object>> paramList = new ArrayList<>(2);
        Map<String, Object> param = new HashMap<>();
        param.put("fkPatientId", fkPatientId);
        param.put("fkDictCode", dictCode);
        param.put("fkTenantId", tenantId);
        param.put("count", count);
        param.put("date", date);
        param.put("diaAbFlag", diaAbFlag);
        param.put("isBefore", true);
        paramList.add(param);
        Map<String, Object> paramAfter = new HashMap<>(param.size());
        paramAfter.putAll(param);
        paramAfter.put("isBefore", false);
        paramList.add(paramAfter);
        return patientAssayRecordBusiMapper.listLatestByFkDictCode(paramList);
    }

    @Override
    public List<PatientAssayRecordBusi> selectCommonByItemCode(PatientAssayRecordBusiPO patientAssayRecordBusi) {
        if (patientAssayRecordBusi.getFkTenantId() == null) {
            patientAssayRecordBusi.setFkTenantId(UserUtil.getTenantId());
        }

        return patientAssayRecordBusiMapper.selectCommonByItemCode(patientAssayRecordBusi);
    }

    @Override
    public void deleteByTenant(Integer fkTenantId) {
        patientAssayRecordBusiMapper.deleteByTenant(fkTenantId);

    }

    @Override
    public List<PatientAssayRecordBusiPO> listByReqId(PatientAssayRecordBusiPO par) {
        if (par.getFkTenantId() == null) {
            par.setFkTenantId(UserUtil.getTenantId());
        }
        return patientAssayRecordBusiMapper.listByCondition(par);
    }

    @Override
    public void updateDiaAbFlagByInspectionIdBack(Long fkPatientId, Integer tenantId) {
        patientAssayRecordBusiMapper.updateDiaAbFlagByInspectionIdBack(fkPatientId, tenantId);
    }

}
