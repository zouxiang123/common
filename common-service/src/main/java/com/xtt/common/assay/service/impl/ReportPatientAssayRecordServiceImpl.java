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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IAssayGroupService;
import com.xtt.common.assay.service.IPatientAssayConfService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IReportPatientAssayRecordService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.AssayReportFilterRuleMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.mapper.PatientAssayTempRecordMapper;
import com.xtt.common.dao.mapper.ReportPatientAssayRecordMapper;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.AssayReportFilterRule;
import com.xtt.common.dao.model.ReportPatientAssayRecord;
import com.xtt.common.dao.po.PatientAssayConfPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayTempRecordPO;
import com.xtt.common.dao.po.ReportPatientAssayRecordPO;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class ReportPatientAssayRecordServiceImpl implements IReportPatientAssayRecordService {
    Logger LOGGER = LoggerFactory.getLogger(ReportPatientAssayRecordServiceImpl.class);
    @Autowired
    private PatientAssayTempRecordMapper patientAssayTempRecordMapper;
    @Autowired
    private AssayReportFilterRuleMapper assayReportFilterRuleMapper;
    @Autowired
    private ReportPatientAssayRecordMapper reportPatientAssayRecordMapper;
    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;
    @Autowired
    private IPatientAssayConfService patientAssayConfService;
    @Autowired
    private IAssayGroupService assayGroupService;
    @Autowired
    private IPatientAssayRecordBusiService patientAssayRecordBusiService;

    @Override
    public String insertAutoByTenantId(String dateType, String monthAndYear, Integer tenantId, Collection<String> itemCodes) {
        String batchNo = UUID.randomUUID().toString();
        if (AssayConsts.REPORT_DATE_TYPE_MONTH.equals(dateType)) {
            // 插入月份临时数据
            PatientAssayConfPO conf = patientAssayConfService.selectDateScopeByMonth(monthAndYear, tenantId);

            PatientAssayTempRecordPO tempCondition = new PatientAssayTempRecordPO();
            tempCondition.setFkTenantId(tenantId);
            tempCondition.setBatchNo(batchNo);
            tempCondition.setStartDate(conf.getStartDate());
            tempCondition.setEndDate(conf.getEndDate());
            tempCondition.setItemCodes(itemCodes);
            patientAssayTempRecordMapper.insertBatchByRecord(tempCondition);
        }

        AssayReportFilterRule ruleCondition = new AssayReportFilterRule();
        ruleCondition.setFkTenantId(tenantId);
        List<AssayReportFilterRule> filterRuleList = assayReportFilterRuleMapper.selectAll(ruleCondition);
        filterByRule(filterRuleList, tenantId, monthAndYear, dateType, batchNo);// 根据过滤规则过滤
        return batchNo;
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
    private void filterByRule(List<AssayReportFilterRule> filterRuleList, Integer fkTenantId, String monthAndYear, String dateType, String batchNo) {
        String sqlCondition = null;

        for (AssayReportFilterRule filterRule : filterRuleList) {
            // 如果过滤函数
            if ("002".equals(filterRule.getRuleCode())) {
                if (AssayConsts.REPORT_FILTER_RULE_MAX_VALUE.equals(filterRule.getFunctionName())) {
                    sqlCondition = "MAX(cast(patr1.result as decimal(10,2)))";
                } else if (AssayConsts.REPORT_FILTER_RULE_MIN_VALUE.equals(filterRule.getFunctionName())) {
                    sqlCondition = "min(cast(patr1.result as decimal(10,2)))";
                } else if (AssayConsts.REPORT_FILTER_RULE_AVG.equals(filterRule.getFunctionName())) {
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
                if (AssayConsts.REPORT_DATE_TYPE_MONTH.equals(dateType)) {
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
        // 避免影响原始对象，copy原始查询条件
        ReportPatientAssayRecordPO query = new ReportPatientAssayRecordPO();
        BeanUtil.copyProperties(record, query);
        Long userId = UserUtil.getLoginUserId();
        query.setFkUserId(userId);
        // 判断化验项是否存在于同类组,如果存在于同类组，则itemCode为同类组id
        query.setItemCode(handItemCode(record.getItemCode(), UserUtil.getTenantId()));
        List<ReportPatientAssayRecordPO> list = reportPatientAssayRecordMapper.selectByDateType(query);
        if (list == null) {
            list = new ArrayList<ReportPatientAssayRecordPO>();
        }
        return init(list);
    }

    @Override
    public List<ReportPatientAssayRecordPO> selectDetailByDateType(ReportPatientAssayRecordPO record) {
        // 避免影响原始对象，copy原始查询条件
        ReportPatientAssayRecordPO query = new ReportPatientAssayRecordPO();
        BeanUtil.copyProperties(record, query);
        Long userId = UserUtil.getLoginUserId();
        query.setFkUserId(userId);
        // 判断化验项是否存在于同类组,如果存在于同类组，则itemCode为同类组id
        query.setItemCode(handItemCode(record.getItemCode(), UserUtil.getTenantId()));
        List<ReportPatientAssayRecordPO> list = reportPatientAssayRecordMapper.selectDetailByDateType(query);
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
    public void insertAutoCopyByTenantId(String paramDate, String monthAndYear, Integer tenantId, Collection<String> itemCodes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM));
        Integer year = cal.get(Calendar.YEAR);
        Integer season = cal.get(Calendar.MONTH) / 3 + 1;
        String currentSeason = year + "-" + season;
        PatientAssayRecordBusiPO query = new PatientAssayRecordBusiPO();
        query.setFkTenantId(tenantId);
        query.setAssayDate(DateFormatUtil.convertStrToDate(paramDate));
        /*同步指定日期原始患者化验结果数据*/
        List<PatientAssayRecordBusiPO> notSynchroResults = patientAssayRecordBusiService.listByCondition(query);
        if (notSynchroResults != null && notSynchroResults.size() > 0) {
            for (PatientAssayRecordBusiPO par : notSynchroResults) {
                /*判断化验项是否存在于同类组,如果存在于同类组，则itemCode为同类组id*/
                par.setItemCode(handItemCode(par.getItemCode(), tenantId));

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
                    rpar.setDateType(AssayConsts.REPORT_DATE_TYPE_MONTH);

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
    public void insertAutoCopyPreMonthDataByTenantId(String monthAndYear, Integer tenantId, Collection<String> itemCodes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM));
        Integer year = cal.get(Calendar.YEAR);
        Integer season = cal.get(Calendar.MONTH) / 3 + 1;
        String currentSeason = year + "-" + season;

        /*验证当前月数据是否存在,不存在同步上月数据*/
        int existCount = reportPatientAssayRecordMapper.selectExistCurrentMothodByMonth(monthAndYear, tenantId, itemCodes);
        if (existCount <= 0) {
            Date currentMonth = DateFormatUtil.convertStrToDate(monthAndYear, DateFormatUtil.FORMAT_YYYY_MM);
            String preMonth = DateFormatUtil.convertDateToStr(DateUtil.addMonths(currentMonth, -1), DateFormatUtil.FORMAT_YYYY_MM);

            reportPatientAssayRecordMapper.insertPreMonthDate(monthAndYear, currentSeason, year, preMonth, tenantId, itemCodes);
        }
    }

    @Override
    public void deleteTempByBatchNo(String batchNo) {
        // 删除临时数据
        patientAssayTempRecordMapper.deleteByBatchNo(batchNo);
    }

    /**
     * 处理itemCode 如果itemCode 属于同类组，则返回同类组的id，否则返回当前itemCode
     * 
     * @Title: handleItemCode
     * @param itemCode
     * @param tenantId
     * @return
     *
     */
    private String handItemCode(String itemCode, Integer tenantId) {
        if (StringUtil.isBlank(itemCode)) {
            return itemCode;
        }
        AssayGroupConfDetail conf = assayGroupService.getByItemCode(itemCode, tenantId);
        if (conf != null) {
            return String.valueOf(conf.getFkAssayGroupConfId());
        }
        return itemCode;
    }

    @Override
    public List<ReportPatientAssayRecordPO> listByStage(ReportPatientAssayRecordPO record) {
        // 避免影响原始对象，copy原始查询条件
        ReportPatientAssayRecordPO query = new ReportPatientAssayRecordPO();
        BeanUtil.copyProperties(record, query);
        Long userId = UserUtil.getLoginUserId();
        query.setFkUserId(userId);
        // 判断化验项是否存在于同类组,如果存在于同类组，则itemCode为同类组id
        query.setItemCode(handItemCode(record.getItemCode(), UserUtil.getTenantId()));
        List<ReportPatientAssayRecordPO> list = patientAssayRecordBusiMapper.listByStage(query);
        if (list == null) {
            list = new ArrayList<ReportPatientAssayRecordPO>();
        }
        return init(list);
    }
}
