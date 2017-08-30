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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayReportCommonService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientAssayReportCommonMapper;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.model.PatientAssayReportCommon;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayReportCommonPO;
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

    @Override
    public void insertList(List<PatientAssayReportCommon> list) {
        patientAssayReportCommonMapper.insertList(list);

    }

    @Override
    public void deleteByTenant(Integer fkTenantId) {
        patientAssayReportCommonMapper.deleteByTenant(fkTenantId);
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
        patientAssayReportCommonMapper.deleteByItemCodes(itemCodes, UserUtil.getTenantId());

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

    /**
     * 得到本月第一天的日期
     * 
     * @Methods Name getFirstDayOfMonth
     * @return Date
     */
    private Date getFirstDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, 1);
        return cDay.getTime();
    }

    /**
     * 得到本月最后一天的日期
     * 
     * @Methods Name getLastDayOfMonth
     * @return Date
     */
    private Date getLastDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cDay.getTime();
    }

    @Override
    public void save(List<String> listItemCode, List<Long> deletePatientId, Date monthDate, Integer tenantId) {
        Date nowDate = new Date();
        List<PatientAssayReportCommon> assayCommonlist = new ArrayList<PatientAssayReportCommon>();
        PatientAssayReportCommon PatientAssayReportCommon = new PatientAssayReportCommon();
        PatientAssayReportCommon.setAssayMonth(DateUtil.format(monthDate, DateFormatUtil.FORMAT_YYYY_MM));
        PatientAssayReportCommon.setFkTenantId(UserUtil.getTenantId());
        this.deleteByTenant(tenantId);
        PatientAssayRecordBusiPO patientAssayRecordBusi = new PatientAssayRecordBusiPO();
        patientAssayRecordBusi.setStartDate(DateFormatUtil.getStartTime(getFirstDayOfMonth(monthDate)));
        patientAssayRecordBusi.setEndDate(DateFormatUtil.getEndTime(getLastDayOfMonth(monthDate)));
        patientAssayRecordBusi.setAssayMonth(DateUtil.format(monthDate, DateFormatUtil.FORMAT_YYYY_MM));
        patientAssayRecordBusi.setItemCodes(listItemCode);
        // 删除已经转归的患者id
        patientAssayRecordBusi.setPatientIds(deletePatientId);
        List<PatientAssayRecordBusi> listpatientAssayRecordBusi = patientAssayRecordBusiService.selectCommonByItemCode(patientAssayRecordBusi);
        PatientAssayReportCommon = null;
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayReportCommon.class.getSimpleName(), UserUtil.getTenantId(),
                        listpatientAssayRecordBusi.size());
        int j = 0;
        for (PatientAssayRecordBusi recordBusi : listpatientAssayRecordBusi) {
            PatientAssayReportCommon = new PatientAssayReportCommon();
            BeanUtil.copyProperties(recordBusi, PatientAssayReportCommon);
            PatientAssayReportCommon.setCreateTime(nowDate);
            PatientAssayReportCommon.setUpdateTime(nowDate);
            PatientAssayReportCommon.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
            PatientAssayReportCommon.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
            PatientAssayReportCommon.setId(id++);
            assayCommonlist.add(PatientAssayReportCommon);
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
    }

}
