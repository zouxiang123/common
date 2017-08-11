/**   
 * @Title: ReportPatientAssayRecordServiceImpl.java 
 * @Package com.xtt.txgl.report.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月24日 下午5:06:38 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayConfService;
import com.xtt.common.assay.service.IReportPatientAssayRecordService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.PatientAssayFilterRuleMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.mapper.PatientAssayTempRecordMapper;
import com.xtt.common.dao.mapper.ReportPatientAssayRecordMapper;
import com.xtt.common.dao.mapper.SysTenantMapper;
import com.xtt.common.dao.model.PatientAssayFilterRule;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.model.ReportPatientAssayRecord;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayConfPO;
import com.xtt.common.dao.po.PatientAssayFilterRulePO;
import com.xtt.common.dao.po.PatientAssayTempRecordPO;
import com.xtt.common.dao.po.ReportPatientAssayRecordPO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class ReportPatientAssayRecordServiceImpl implements IReportPatientAssayRecordService {
    Logger LOGGER = LoggerFactory.getLogger(ReportPatientAssayRecordServiceImpl.class);
    @Autowired
    private PatientAssayTempRecordMapper patientAssayTempRecordMapper;
    @Autowired
    private SysTenantMapper sysTenantMapper;
    @Autowired
    private PatientAssayFilterRuleMapper patientAssayFilterRuleMapper;
    @Autowired
    private ReportPatientAssayRecordMapper reportPatientAssayRecordMapper;
    @Autowired
    private IPatientAssayConfService patientAssayConfService;
    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;

    @Override
    @Deprecated
    public void insertAuto(String dateType, String monthAndYear) {

        List<SysTenant> tenantList = sysTenantMapper.selectAll();

        for (SysTenant sysTenant : tenantList) {
            Integer tenantId = sysTenant.getId();
            insertAutoByTenantId(dateType, monthAndYear, tenantId);
        }

    }

    @Override
    public String insertAutoByTenantId(String dateType, String monthAndYear, Integer tenantId) {
        String batchNo = UUID.randomUUID().toString();
        if (DictHospitalLabPO.DATE_TYPE_MONTH.equals(dateType)) {
            insertByDateType(monthAndYear, tenantId, batchNo);// 插入当月的数据
        }

        PatientAssayFilterRule ruleCondition = new PatientAssayFilterRule();
        ruleCondition.setFkTenantId(tenantId);
        List<PatientAssayFilterRule> filterRuleList = patientAssayFilterRuleMapper.selectAll(ruleCondition);
        filterByRule(filterRuleList, tenantId, monthAndYear, dateType, batchNo);// 根据过滤规则过滤
        return batchNo;
    }

    /**
     * 根据月份插入临时数据
     * 
     * @Title: insertByDateType
     * @param date
     * @param tenantId
     * @param batchNo
     * 
     */
    private void insertByDateType(String monthAndYear, Integer tenantId, String batchNo) {
        // 获取时间段配置
        PatientAssayConfPO conf = patientAssayConfService.selectDateScopeByMonth(monthAndYear, tenantId);

        PatientAssayTempRecordPO tempCondition = new PatientAssayTempRecordPO();
        tempCondition.setFkTenantId(tenantId);
        tempCondition.setBatchNo(batchNo);
        tempCondition.setStartDateStr(DateFormatUtil.convertDateToStr(conf.getStartDate()));
        tempCondition.setEndDateStr(DateFormatUtil.convertDateToStr(conf.getEndDate()));
        patientAssayTempRecordMapper.insertBatchByRecord(tempCondition);
    }

    /**
     * 根据过滤规则过滤插入数据
     * 
     * @Title: filterByRule
     * @param filterRuleList
     * @param fkTenantId
     * @param monthAndYear
     * @param dateType
     * @param batchNo
     *
     */
    private void filterByRule(List<PatientAssayFilterRule> filterRuleList, Integer fkTenantId, String monthAndYear, String dateType, String batchNo) {
        String sqlCondition = null;

        for (PatientAssayFilterRule filterRule : filterRuleList) {
            // 如果过滤函数
            if ("002".equals(filterRule.getRuleCode())) {
                if (PatientAssayFilterRulePO.FUNCTION_NAME_MAX_VALUE.equals(filterRule.getFunctionName())) {
                    sqlCondition = "MAX(cast(patr1.result as decimal(10,2)))";
                } else if (PatientAssayFilterRulePO.FUNCTION_NAME_MIN_VALUE.equals(filterRule.getFunctionName())) {
                    sqlCondition = "min(cast(patr1.result as decimal(10,2)))";
                } else if (PatientAssayFilterRulePO.FUNCTION_NAME_AVG.equals(filterRule.getFunctionName())) {
                    sqlCondition = "round(AVG(patr1.result), 2)";
                }

                ReportPatientAssayRecordPO reportCondition = new ReportPatientAssayRecordPO();
                reportCondition.setSqlCondition(sqlCondition);
                reportCondition.setFkTenantId(fkTenantId);
                reportCondition.setDateType(dateType);
                reportCondition.setCreateTime(DateFormatUtil.getDate());

                Calendar cal = Calendar.getInstance();
                cal.setTime(DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM));
                Integer year = cal.get(Calendar.YEAR);
                Integer season = cal.get(Calendar.MONTH) / 3 + 1;
                reportCondition.setAssayMonth(monthAndYear);
                reportCondition.setAssayYear(year + "");
                reportCondition.setAssaySeason(year + "-" + season);
                reportCondition.setBatchNo(batchNo);
                if (DictHospitalLabPO.DATE_TYPE_MONTH.equals(dateType)) {
                    reportPatientAssayRecordMapper.insertBatchByMonth(reportCondition);
                } else {
                    reportPatientAssayRecordMapper.insertBatchBySeason(reportCondition);
                }
            } else if ("001".equals(filterRule.getRuleCode())) {

            }
        }
    }

    @Override
    public List<ReportPatientAssayRecordPO> selectByDateType(ReportPatientAssayRecordPO record) {
        Long userId = UserUtil.getLoginUserId();
        record.setFkUserId(userId);
        List<ReportPatientAssayRecordPO> list = reportPatientAssayRecordMapper.selectByDateType(record);
        if (list == null) {
            list = new ArrayList<ReportPatientAssayRecordPO>();
        }
        return init(list);
    }

    @Override
    public List<ReportPatientAssayRecordPO> selectDetailByDateType(ReportPatientAssayRecordPO record) {
        Long userId = UserUtil.getLoginUserId();
        record.setFkUserId(userId);
        List<ReportPatientAssayRecordPO> list = reportPatientAssayRecordMapper.selectDetailByDateType(record);
        if (list == null) {
            list = new ArrayList<ReportPatientAssayRecordPO>();
        }
        return init(list);
    }

    private List<ReportPatientAssayRecordPO> init(List<ReportPatientAssayRecordPO> list) {
        for (ReportPatientAssayRecordPO item : list) {
            item.setResultTipsShow(DictUtil.getItemName(CmDictConsts.IS_OR_NOT, ("1".equals(item.getResultTips()) ? item.getResultTips() : "0")));
        }

        return list;
    }

    @Override
    public Double selectPopByDateType(ReportPatientAssayRecord record) {

        return reportPatientAssayRecordMapper.selectPopByDateType(record);
    }

    @Override
    public void deleteAll() {
        reportPatientAssayRecordMapper.deleteAll(UserUtil.getTenantId());

    }

    @Override
    public void deleteByCondition(ReportPatientAssayRecord record) {
        reportPatientAssayRecordMapper.deleteByCondition(record);

    }

    @Override
    public void insertAutoCopyByTenantId(String paramDate, String monthAndYear, Integer tenantId) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM));
        Integer year = cal.get(Calendar.YEAR);
        Integer season = cal.get(Calendar.MONTH) / 3 + 1;
        String currentSeason = year + "-" + season;

        /*同步指定日期原始患者化验结果数据*/
        List<PatientAssayRecord> notSynchroResults = patientAssayRecordMapper.selectPatientAssayRecordByAssayDate(paramDate, tenantId);
        if (notSynchroResults != null && notSynchroResults.size() > 0) {
            for (PatientAssayRecord par : notSynchroResults) {

                ReportPatientAssayRecord rpar = new ReportPatientAssayRecord();
                rpar.setFkTenantId(tenantId);
                rpar.setAssayMonth(monthAndYear);
                rpar.setItemCode(par.getItemCode());
                rpar.setFkPatientId(par.getFkPatientId());

                /*验证指定患者化验项是否存在，存在修改否则插入*/
                rpar = reportPatientAssayRecordMapper.selectByReportPatientAssay(rpar);
                if (rpar == null) {
                    rpar = new ReportPatientAssayRecord();
                    rpar.setFkTenantId(tenantId);
                    rpar.setAssayMonth(monthAndYear);
                    rpar.setItemCode(par.getItemCode());
                    rpar.setFkPatientId(par.getFkPatientId());
                    rpar.setCreateTime(new Date());
                    rpar.setItemName(par.getItemName());
                    rpar.setResult(par.getResult());
                    rpar.setResultTips(par.getResultTips());
                    rpar.setAssaySeason(currentSeason);
                    rpar.setAssayYear(String.valueOf(year));
                    rpar.setDateType("m");

                    reportPatientAssayRecordMapper.insertSelective(rpar);
                    continue;
                }

                rpar.setItemName(par.getItemName());
                rpar.setResult(par.getResult());
                rpar.setResultTips(par.getResultTips());
                rpar.setAssaySeason(currentSeason);
                rpar.setAssayYear(String.valueOf(year));
                reportPatientAssayRecordMapper.updateReportPatientAssay(rpar);

            }
        }

    }

    @Override
    public void insertAutoCopyPreMonthDataByTenantId(String paramDate, String monthAndYear, Integer tenantId) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM));
        Integer year = cal.get(Calendar.YEAR);
        Integer season = cal.get(Calendar.MONTH) / 3 + 1;
        String currentSeason = year + "-" + season;

        /*验证当前月数据是否存在,不存在同步上月数据*/
        int existCount = reportPatientAssayRecordMapper.selectExistCurrentMothodByMonth(monthAndYear, tenantId);
        if (existCount <= 0) {
            Date currentMonth = DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM);
            String preMonth = DateFormatUtil.convertDateToStr(DateUtil.addMonths(currentMonth, -1), DateFormatUtil.FORMAT_YYYY_MM);

            reportPatientAssayRecordMapper.insertPreMonthDate(monthAndYear, currentSeason, year, preMonth, tenantId);
        }
    }

    @Override
    public void deleteTempByBatchNo(String batchNo) {
        // 删除临时数据
        patientAssayTempRecordMapper.deleteByBatchNo(batchNo);
    }
}
