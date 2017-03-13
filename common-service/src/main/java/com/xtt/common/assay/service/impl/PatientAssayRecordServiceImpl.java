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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.DictHospitalLabMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.GenerationUUID;
import com.xtt.platform.util.lang.NumberUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayRecordServiceImpl implements IPatientAssayRecordService {
    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;
    @Autowired
    private DictHospitalLabMapper dictHospitalLabMapper;

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
    public List<PatientAssayRecordPO> selectByCondition(PatientAssayRecordPO record) {
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
    public List<Map<String, Object>> selectLatestAssayDateByTenantId(Integer tenantId, Date startTime, Date endTIme) {
        return patientAssayRecordMapper.selectLatestAssayDateByTenantId(tenantId, startTime, endTIme);
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
        for (PatientAssayRecordPO item : list) {
            item.setResultTipsShow(DictUtil.getItemName(CmDictConsts.IS_OR_NOT, ("1".equals(item.getResultTips()) ? item.getResultTips() : "0")));
        }

        return list;
    }

    @Override
    public List<PatientAssayRecordPO> selectByFkDictCode(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectByFkDictCode(record);
    }

    /**
     * 查询患者化验详情
     */
    @Override
    public List<PatientAssayRecordPO> selectPatientAssayDetail(String startTime, String endTime, Long patientId, String assayClass) {
        return patientAssayRecordMapper.selectPatientAssayDetail(startTime, endTime, patientId, assayClass, UserUtil.getTenantId());

    }

    @Override
    public List<Map<String, Object>> selectAssayList(Long patientId, String assayDateFrom, String assayDateTo) {
        return patientAssayRecordMapper.selectAssayList(patientId, assayDateFrom, assayDateTo);
    }

    @Override
    public List<PatientAssayRecord> selectAssayItems(Long patientId, String assayDate) {
        return patientAssayRecordMapper.selectAssayItems(patientId, assayDate);
    }

    @Override
    public List<PatientAssayRecordPO> selectByItemCodes(Collection<String> itemCodes, Date startDate, Date endDate, String patientTempValue) {
        return patientAssayRecordMapper.selectByItemCodes(itemCodes, startDate, endDate, patientTempValue, UserUtil.getTenantId());
    }

    /**
     * 查询特定患者的阶段小结数据
     */
    @Override
    public List<Map<String, Object>> selectLatestAssayDateByPatient(Integer tenantId, Date startTime, Date endTime, Long fkPatientId) {
        return patientAssayRecordMapper.getLatestAssayDateByPatient(tenantId, startTime, endTime, fkPatientId);

    }

    /**
     * 手动录入化验项
     */
    @Override
    public void insertPatientAssay(List<DictHospitalLabPO> dHL) {
        PatientAssayRecordPO po = null;
        List<PatientAssayRecordPO> list = new ArrayList<>();
        // 获取化验单id
        // DictHospitalLabPO dhPO=null;
        String reQid = StringUtil.getUUIDFive();
        Date date = new Date();
        for (DictHospitalLabPO dPO : dHL) {
            // 设置
            po = new PatientAssayRecordPO();
            po.setFkTenantId(UserUtil.getTenantId());
            po.setCreateTime(date);

            po.setId(GenerationUUID.getGenerationUUID());
            po.setFkPatientId(dPO.getFkPatientId());

            Date assayDate = dPO.getAssayDate();
            po.setReportTime(assayDate);// 报告日期
            po.setAssayDate(DateUtil.format(assayDate, "yyyy-MM-dd"));// 化验日期
            po.setAssayMonth(DateUtil.format(assayDate, "yyyy-MM"));// 化验日期
            po.setSampleTime(assayDate);// 取样时间

            po.setCheckTime(assayDate);// 检查时间
            po.setReqTime(assayDate);// 申请时间

            po.setCheckTime(dPO.getAssayDate());
            po.setReqTime(dPO.getAssayDate());

            String groupId = PinyinHelper.convertToPinyinString(dPO.getGroupName(), "", PinyinFormat.WITHOUT_TONE);
            po.setGroupId(groupId);
            po.setGroupName(dPO.getGroupName());
            po.setItemCode(dPO.getItemCode());
            po.setItemName(dPO.getItemName());
            po.setResultTips(dPO.getResultTips());

            // po.setResultActual(Double.parseDouble(po.getResult()));//结果

            po.setReference(dPO.getReference());
            if (dPO.getMinValue() != null) {
                po.setValueMin(dPO.getMinValue());
            }
            if (dPO.getMaxValue() != null) {
                po.setValueMax(dPO.getMaxValue());
            }
            if (dPO.getResult() != null && !"".equals(dPO.getResult())) {
                po.setResult(dPO.getResult());
                if (NumberUtil.isNumeric(dPO.getResult())) {
                    po.setResultActual(Double.parseDouble(po.getResult()));// 结果
                    dictHospitalLabMapper.updateByPrimaryKeySelective(dPO);
                } else {
                    dictHospitalLabMapper.updateByPrimaryKeySelective(dPO);
                }
            }
            po.setFlage(true);
            po.setUnit(dPO.getUnit());
            po.setValueUnit(dPO.getUnit());
            po.setReqId(reQid);
            // dhPO=dPO;
            list.add(po);
        }
        // 其他选项保存到字典表
        /*if("others".equals(dhPO.getGroupId())){
        	//一个化验单下有多项
        	for (DictHospitalLabPO dictHospitalLabPO : dHL) {
        		String groupId=CnPinyin.getPingYin(dhPO.getGroupName());
        		dictHospitalLabPO.setGroupId(groupId);
        		dictHospitalLabPO.setFkTenantId(UserUtil.getTenantId());
        		DataUtil.setSystemFieldValue(dictHospitalLabPO);
        		dictHospitalLabPO.setFlage(true);
        	}
        	dictHospitalLabMapper.insertDictHospital(dHL);
        }*/
        patientAssayRecordMapper.insertPatientAssay(list);
    }

    /**
     * 修改录入的化验项
     */
    @Override
    public void updatePatientAssay(List<DictHospitalLabPO> dHL) {
        PatientAssayRecordPO patientAssayRecordPO = null;
        // Long dictID=null;
        for (DictHospitalLabPO dPo : dHL) {
            // dictID=dictHospitalLabMapper.getDictId(dPo);
            patientAssayRecordPO = new PatientAssayRecordPO();
            patientAssayRecordPO.setItemName(dPo.getItemName());
            patientAssayRecordPO.setItemCode(dPo.getItemCode());
            patientAssayRecordPO.setResult(dPo.getResult());
            patientAssayRecordPO.setResultTips(dPo.getResultTips());
            patientAssayRecordPO.setValueMin(dPo.getMinValue());
            patientAssayRecordPO.setValueMax(dPo.getMaxValue());
            patientAssayRecordPO.setValueUnit(dPo.getUnit());
            patientAssayRecordPO.setId(dPo.getPatientAssayId());
            /*dPo.setId(dictID);
            dictHospitalLabMapper.updateByPrimaryKeySelective(dPo);*/
            patientAssayRecordMapper.updateByPrimaryKeySelective(patientAssayRecordPO);

        }
    }

    /**
     * 删除手动录入的化验项
     */
    @Override
    public int deleteById(String id) {
        return patientAssayRecordMapper.deleteById(id);
    }

    /**
     * 查询手动录入的化验项
     */
    @Override
    public List<PatientAssayRecordPO> selectByGroupId(PatientAssayRecordPO par) {
        return patientAssayRecordMapper.selectByGroupId(par);
    }

    @Override
    public List<PatientAssayRecordPO> selectItemLatestDataByCondition(PatientAssayRecordPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayRecordMapper.selectItemLatestDataByCondition(record);
    }
}
