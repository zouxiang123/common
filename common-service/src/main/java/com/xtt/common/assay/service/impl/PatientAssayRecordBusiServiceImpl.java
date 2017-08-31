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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.hand.AssayHandDelete;
import com.xtt.common.assay.hand.AssayHandFactory;
import com.xtt.common.assay.hand.FiveAssayHand;
import com.xtt.common.assay.hand.FourAssayHand;
import com.xtt.common.assay.hand.OneAssayHand;
import com.xtt.common.assay.hand.TwoAssayHand;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayRecordBusiServiceImpl implements IPatientAssayRecordBusiService {

    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;

    @Autowired
    private IAssayHospDictService assayHospDictService;

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
        Date nowDate = new Date();
        String strNowDate = DateUtil.format(nowDate, DateFormatUtil.FORMAT_DATE_YYYYMMDD);
        String strRandom = RandomStringUtils.randomAlphanumeric(6);
        for (AssayHospDictPO assayHospDictPO : getdHL) {
            if (StringUtil.isEmpty(assayHospDictPO.getResultTips())) {
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
            patientAssayRecordBusi.setSampleTime(nowDate);
            patientAssayRecordBusi.setReportTime(nowDate);
            patientAssayRecordBusi.setFkTenantId(UserUtil.getTenantId());
            patientAssayRecordBusi.setReqId(strNowDate.concat("_").concat(strRandom));
            DataUtil.setSystemFieldValue(patientAssayRecordBusi);
            patientAssayRecordBusiMapper.insertSelective(patientAssayRecordBusi);
        }
    }

    @Override
    public void deleteByPatientId(Long fkPatientId, Integer fkTenantId) {
        patientAssayRecordBusiMapper.deleteByPatientId(fkPatientId, fkTenantId);

    }

    @Override
    public void save(Date startCreateTime, Date endCreateTime, Map<Long, List<Date>> mapPatientId, Long patientId, boolean isDelete,
                    Integer fkTenantId) {
        UserUtil.setThreadTenant(fkTenantId);
        String labAfterBefore = SysParamUtil.getValueByName(UserUtil.getTenantId(), AssayConsts.LAB_AFTER_BEFORE);
        AssayHandFactory assayHandFactory = null;
        if (isDelete) {// 如果是删除操作，不需要清洗，只需重新把透析透后标识带过来；
            deleteByPatientId(patientId, fkTenantId);
            assayHandFactory = new AssayHandDelete();
        } else {
            switch (labAfterBefore) {
            case "1":
                assayHandFactory = new OneAssayHand();
                break;
            case "2":
                assayHandFactory = new TwoAssayHand();
                break;
            case "3":
                assayHandFactory = new OneAssayHand();
                break;
            case "4":
                assayHandFactory = new FourAssayHand();
                break;
            case "5":
                assayHandFactory = new FiveAssayHand();
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

        return patientAssayRecordBusiMapper.selectCommonByItemCode(patientAssayRecordBusi);
    }

    @Override
    public void deleteByTenant(Integer fkTenantId) {
        patientAssayRecordBusiMapper.deleteByTenant(fkTenantId);

    }

    @Override
    public List<PatientAssayRecordBusiPO> listByReqId(PatientAssayRecordBusiPO par) {
        return patientAssayRecordBusiMapper.listByCondition(par);
    }

    @Override
    public void updateDiaAbFlagByInspectioidBack(Long fkPatientId, Integer tenantId) {
        patientAssayRecordBusiMapper.updateDiaAbFlagByInspectioidBack(fkPatientId, tenantId);
    }

}
