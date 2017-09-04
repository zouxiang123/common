/**   
 * @Title: PatientAssayBackCommonServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月11日 下午1:16:05 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayReportCommonService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientAssayReportCommonMapper;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.model.PatientAssayReportCommon;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayReportCommonPO;
import com.xtt.common.util.BusinessDateUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayReportCommonServiceImpl implements IPatientAssayReportCommonService {

    @Autowired
    private PatientAssayReportCommonMapper patientAssayReportCommonMapper;

    @Autowired
    private IPatientAssayRecordBusiService patientAssayRecordBusiService;

    @Autowired
    private IAssayHospDictService assayHospDictService;

    @Override
    public void insertList(List<PatientAssayReportCommon> list) {
        patientAssayReportCommonMapper.insertList(list);

    }

    @Override
    public void deleteByTenant(Integer fkTenantId) {
        PatientAssayReportCommonPO record = new PatientAssayReportCommonPO();
        record.setFkTenantId(fkTenantId);
        deleteByCondition(record);
    }

    @Override
    public List<PatientAssayReportCommonPO> selectByAssayDate(PatientAssayReportCommonPO PatientAssayReportCommon) {
        return patientAssayReportCommonMapper.selectByAssayDate(PatientAssayReportCommon);
    }

    @Override
    public List<PatientAssayReportCommonPO> selectByPatientLabel(PatientAssayReportCommonPO PatientAssayReportCommon) {
        return patientAssayReportCommonMapper.selectByPatientLabel(PatientAssayReportCommon);
    }

    @Override
    public void deleteByItemCodes(List<String> itemCodes) {
        PatientAssayReportCommonPO record = new PatientAssayReportCommonPO();
        record.setItemCodes(itemCodes);
        record.setFkTenantId(UserUtil.getTenantId());
        deleteByCondition(record);
    }

    @Override
    public void updateItemCode(List<String> itemCodes, Boolean isDelete, Integer tenantId) {

        AssayHospDictPO dictAssay = new AssayHospDictPO();
        dictAssay.setIsTop(true);
        Date nowDate = new Date();
        List<PatientAssayReportCommon> assayCommonlist = new ArrayList<PatientAssayReportCommon>();
        this.deleteByItemCodes(itemCodes);
        if (isDelete) {
            PatientAssayRecordBusiPO patientAssayRecordBusi = new PatientAssayRecordBusiPO();
            patientAssayRecordBusi.setItemCodes(itemCodes);
            List<PatientAssayRecordBusi> listpatientAssayRecordBusi = patientAssayRecordBusiService.selectCommonByItemCode(patientAssayRecordBusi);
            PatientAssayReportCommon PatientAssayReportCommon = null;
            for (PatientAssayRecordBusi recordBusi : listpatientAssayRecordBusi) {
                PatientAssayReportCommon = new PatientAssayReportCommon();
                BeanUtil.copyProperties(recordBusi, PatientAssayReportCommon);
                PatientAssayReportCommon.setCreateTime(nowDate);
                PatientAssayReportCommon.setUpdateTime(nowDate);
                PatientAssayReportCommon.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                PatientAssayReportCommon.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                assayCommonlist.add(PatientAssayReportCommon);
            }

            this.insertList(assayCommonlist);
        }

    }

    @Override
    public void insertAuto(List<Long> allPatientIds, Set<Long> filterPatientIds, Date monthDate, Integer tenantId) {
        // 删除本月数据
        String assayMonth = DateUtil.format(monthDate, DateFormatUtil.FORMAT_YYYY_MM);
        PatientAssayReportCommonPO delCondition = new PatientAssayReportCommonPO();
        delCondition.setFkTenantId(UserUtil.getTenantId());
        delCondition.setAssayMonth(assayMonth);
        deleteByCondition(delCondition);

        // 获取常用化验项
        AssayHospDictPO dictAssay = new AssayHospDictPO();
        dictAssay.setIsTop(true);
        List<AssayHospDictPO> dicts = assayHospDictService.getByCondition(dictAssay);
        List<String> listItemCode = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dicts)) {
            for (AssayHospDictPO dictHospitalLab : dicts) {
                listItemCode.add(dictHospitalLab.getItemCode());
            }
        } else {
            return;
        }

        Date nowDate = new Date();
        List<PatientAssayReportCommon> assayCommonlist = new ArrayList<PatientAssayReportCommon>();
        PatientAssayRecordBusiPO patientAssayRecordBusi = new PatientAssayRecordBusiPO();
        patientAssayRecordBusi.setStartDate(BusinessDateUtil.getMonthStartOrEnd(monthDate, true));
        patientAssayRecordBusi.setEndDate(BusinessDateUtil.getMonthStartOrEnd(monthDate, false));
        patientAssayRecordBusi.setAssayMonth(assayMonth);
        patientAssayRecordBusi.setItemCodes(listItemCode);
        // 删除已经转归的患者id
        patientAssayRecordBusi.setPatientIds(filterPatientIds);
        List<PatientAssayRecordBusi> listpatientAssayRecordBusi = patientAssayRecordBusiService.selectCommonByItemCode(patientAssayRecordBusi);
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayReportCommon.class.getSimpleName(), UserUtil.getTenantId(),
                        listpatientAssayRecordBusi.size());
        int j = 0;
        PatientAssayReportCommon patientAssayReportCommon = null;
        // 已插入的患者id
        Set<Long> insertPatientIds = new HashSet<>();
        for (PatientAssayRecordBusi recordBusi : listpatientAssayRecordBusi) {
            insertPatientIds.add(recordBusi.getFkPatientId());
            patientAssayReportCommon = new PatientAssayReportCommon();
            BeanUtil.copyProperties(recordBusi, patientAssayReportCommon);
            patientAssayReportCommon.setAssayMonth(assayMonth);
            patientAssayReportCommon.setFkTenantId(tenantId);
            patientAssayReportCommon.setCreateTime(nowDate);
            patientAssayReportCommon.setUpdateTime(nowDate);
            patientAssayReportCommon.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
            patientAssayReportCommon.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
            patientAssayReportCommon.setId(id++);
            assayCommonlist.add(patientAssayReportCommon);
            j++;
            if (j == 1000) {
                this.insertList(assayCommonlist);
                assayCommonlist.clear();
                j = 0;
            }
        }
        if (CollectionUtils.isNotEmpty(assayCommonlist)) {
            this.insertList(assayCommonlist);
            assayCommonlist.clear();
        }
        // 因为要显示所有患者，所以如果患者不存在化验数据，默认插入一条常用项数据
        allPatientIds.removeAll(insertPatientIds);
        allPatientIds.removeAll(filterPatientIds);
        if (CollectionUtils.isNotEmpty(allPatientIds)) {
            id = PrimaryKeyUtil.getPrimaryKey(PatientAssayReportCommon.class.getSimpleName(), UserUtil.getTenantId(), allPatientIds.size());
            String itemCode = dicts.get(0).getItemCode();
            for (int i = 0; i < allPatientIds.size(); i++) {
                patientAssayReportCommon = new PatientAssayReportCommon();
                patientAssayReportCommon.setId(id++);
                patientAssayReportCommon.setItemCode(itemCode);
                patientAssayReportCommon.setResult("");
                patientAssayReportCommon.setFkPatientId(allPatientIds.get(i));
                patientAssayReportCommon.setAssayMonth(assayMonth);
                patientAssayReportCommon.setFkTenantId(tenantId);
                patientAssayReportCommon.setCreateTime(nowDate);
                patientAssayReportCommon.setUpdateTime(nowDate);
                patientAssayReportCommon.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                patientAssayReportCommon.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                assayCommonlist.add(patientAssayReportCommon);
            }
            this.insertList(assayCommonlist);
        }
    }

    /**
     * 根据自定义条件删除数据
     * 
     * @Title: deleteByCondition
     * @param record
     *
     */
    private void deleteByCondition(PatientAssayReportCommonPO record) {
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        patientAssayReportCommonMapper.deleteByCondition(record);
    }
}
