/**   
 * @Title: AssayReportController.java 
 * @Package com.xtt.txgl.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月5日 上午11:21:35 
 *
 */
package com.xtt.common.report.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayConfService;
import com.xtt.common.assay.service.IPatientAssayGroupRuleService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IReportPatientAssayRecordService;
import com.xtt.common.assay.util.AssayGroupRuleUtil;
import com.xtt.common.constants.AssayConsts;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayConfPO;
import com.xtt.common.dao.po.PatientAssayGroupRulePO;
import com.xtt.common.dao.po.ReportPatientAssayRecordPO;
import com.xtt.common.patient.service.IPatientLabelService;
import com.xtt.common.util.CalendarUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.report.ReportExcelTemplate;
import com.xtt.common.util.report.entity.ReportSheetEntity;
import com.xtt.platform.util.time.DateFormatUtil;

@Controller
@RequestMapping("/report/assay/")
public class AssayReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssayReportController.class);
    @Autowired
    private IAssayHospDictService assayHospDictService;
    @Autowired
    private IPatientAssayGroupRuleService patientAssayGroupRuleService;
    @Autowired
    private IReportPatientAssayRecordService reportPatientAssayRecordService;
    @Autowired
    private IPatientAssayConfService patientAssayConfService;
    @Autowired
    private IPatientAssayRecordBusiService patientAssayRecordBusiService;
    @Autowired
    private IPatientLabelService patientLabelService;

    /**
     * 跳转至化验报表也页面
     * 
     * @Title: view
     * @return
     * 
     */
    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("cm/report/assay_report");
        model.addObject(CmDictConsts.ASSAY_TEXT_TYPE, DictUtil.listByPItemCode(CmDictConsts.ASSAY_TEXT_TYPE));
        model.addObject("labels", patientLabelService.listByTenantId(UserUtil.getTenantId()));
        return model;
    }

    /**
     * 化验项统计
     * 
     * @Title: count
     * @param dateType
     * @return
     * 
     */
    @RequestMapping("selectYearDatas")
    @ResponseBody
    public Map<String, Object> selectYearDatas(ReportPatientAssayRecordPO reportPatientAssayRecord) {
        AssayHospDictPO assayDic = assayHospDictService.getByItemCode(reportPatientAssayRecord.getItemCode());

        reportPatientAssayRecord.setFkTenantId(UserUtil.getTenantId());
        List<ReportPatientAssayRecordPO> recordList = reportPatientAssayRecordService.selectByDateType(reportPatientAssayRecord);

        return getYearDatas(recordList, reportPatientAssayRecord, assayDic);
    }

    /**
     * 获取年报表信息
     * 
     * @Title: getBarXAxis
     * @param recordList
     * @return
     * 
     */
    private Map<String, Object> getYearDatas(List<ReportPatientAssayRecordPO> recordList, ReportPatientAssayRecordPO reportPatientAssayRecord,
                    AssayHospDictPO assayDic) {
        Map<String, Object> map = new HashMap<String, Object>();

        String[] dateShows = new String[recordList.size()];// 日期显示
        String[] dateValues = new String[recordList.size()];// 日期显示
        Double[] allSum = new Double[recordList.size()];// 总数值
        int[] allCount = new int[recordList.size()];// 总数量
        int[] okCounts = new int[recordList.size()];// 达标数
        int[] noOkCounts = new int[recordList.size()];// 不达标数
        int[] excessOkCounts = new int[recordList.size()];// 超标数
        Double[] okRates = new Double[recordList.size()];// 达标率
        Double[] avgValues = new Double[recordList.size()];// 平均值
        Double[] popValues = new Double[recordList.size()];// 标准差
        int allOkCount = 0;
        int allNoOkCount = 0;
        int allExcessOkCount = 0;
        for (int i = 0; i < recordList.size(); i++) {
            ReportPatientAssayRecordPO record = recordList.get(i);
            dateShows[i] = getDateShow(record.getDateValue(), reportPatientAssayRecord.getDateType());
            dateValues[i] = record.getDateValue();
            allSum[i] = record.getAllSum();
            allCount[i] = record.getAllCount() == null ? 0 : record.getAllCount();
            okCounts[i] = record.getOkCount() == null ? 0 : record.getOkCount();
            noOkCounts[i] = record.getNoOkCount() == null ? 0 : record.getNoOkCount();
            excessOkCounts[i] = record.getExcessOkCount() == null ? 0 : record.getExcessOkCount();
            okRates[i] = record.getOkRates();
            avgValues[i] = record.getAvg();
            popValues[i] = record.getPop();

            allOkCount += okCounts[i];
            allNoOkCount += noOkCounts[i];
            allExcessOkCount += excessOkCounts[i];
        }

        // 达标统计
        Map<String, Object> barMap = new HashMap<String, Object>();
        barMap.put("xAxis", dateShows);
        barMap.put("monthSeason", dateValues);
        List<String> barTitle = new ArrayList<String>();
        List<Object> barData = new ArrayList<Object>();

        barTitle.add(AssayConsts.ASSAY_RESULT_OK);
        barData.add(okCounts);

        if (reportPatientAssayRecord.getMinValue() != null) {
            barTitle.add(AssayConsts.ASSAY_RESULT_NO_OK);
            barData.add(noOkCounts);
        }

        if (reportPatientAssayRecord.getMaxValue() != null) {
            barTitle.add(AssayConsts.ASSAY_RESULT_EXCESS_OK);
            barData.add(excessOkCounts);
        }

        barTitle.add(AssayConsts.OK_RATE);
        barData.add(okRates);

        barMap.put("title", barTitle);
        barMap.put("data", barData);
        map.put("bar", barMap);

        // 中位数统计
        Map<String, Object> lineMap = new HashMap<String, Object>();
        String[] lineTitle = { AssayConsts.AVG, AssayConsts.POP };
        lineMap.put("title", lineTitle);
        lineMap.put("xAxis", dateShows);
        lineMap.put("yUnit", StringUtils.trimToEmpty(assayDic.getUnit()));
        lineMap.put("sumData", allSum);
        lineMap.put("avgData", avgValues);//
        lineMap.put("popData", popValues);// 标准差
        lineMap.put("countData", allCount);
        map.put("line", lineMap);

        // 达标率饼图
        Map<String, Object> pieMap = new HashMap<String, Object>();
        List<Map<String, Object>> pieData = new ArrayList<Map<String, Object>>();
        Map<String, Object> pieDataMap = new HashMap<String, Object>();
        pieDataMap.put("name", AssayConsts.ASSAY_RESULT_OK);
        pieDataMap.put("value", allOkCount);
        pieData.add(pieDataMap);

        pieDataMap = new HashMap<String, Object>();
        pieDataMap.put("name", AssayConsts.ASSAY_RESULT_NO_OK);
        pieDataMap.put("value", allNoOkCount);
        pieData.add(pieDataMap);

        pieDataMap = new HashMap<String, Object>();
        pieDataMap.put("name", AssayConsts.ASSAY_RESULT_EXCESS_OK);
        pieDataMap.put("value", allExcessOkCount);
        pieData.add(pieDataMap);

        String[] pieTitle = { AssayConsts.ASSAY_RESULT_OK, AssayConsts.ASSAY_RESULT_NO_OK, AssayConsts.ASSAY_RESULT_EXCESS_OK };
        pieMap.put("title", pieTitle);
        pieMap.put("data", pieData);

        map.put("pie", pieMap);

        return map;
    }

    private String getDateShow(String dateValue, String dateType) {
        String[] array = dateValue.split("-");
        String dateShow = "";
        if (AssayConsts.REPORT_DATE_TYPE_MONTH.equals(dateType)) {
            dateShow = array[1] + "月";
        }
        if (AssayConsts.REPORT_DATE_TYPE_SEASON.equals(dateType)) {
            dateShow = array[1] + "季度";
        }
        return dateShow;
    }

    private Map<String, Object> getMonthSeasonDatas(List<ReportPatientAssayRecordPO> recordList, ReportPatientAssayRecordPO reportPatientAssayRecord,
                    AssayHospDictPO assayDic) {

        int allOkCount = 0;
        int allNoOkCount = 0;
        int allExcessOkCount = 0;
        for (int i = 0; i < recordList.size(); i++) {
            ReportPatientAssayRecordPO record = recordList.get(i);
            Integer okIndex = null;
            allOkCount += record.getOkCount();
            if (record.getNoOkCount() != null) {
                allNoOkCount += record.getNoOkCount();
                if (record.getNoOkCount() > 0) {
                    okIndex = 1;
                }
            }
            if (record.getExcessOkCount() != null) {
                allExcessOkCount += record.getExcessOkCount();
                if (record.getExcessOkCount() > 0) {
                    okIndex = 2;
                }
            }
            if (record.getOkCount() > 0) {
                okIndex = 0;
            }
            record.setOkIndex(okIndex);
        }

        // 达标率饼图
        Map<String, Object> pieMap = new HashMap<String, Object>();
        List<Map<String, Object>> pieData = new ArrayList<Map<String, Object>>();
        Map<String, Object> pieDataMap = new HashMap<String, Object>();
        pieDataMap.put("name", AssayConsts.ASSAY_RESULT_OK);
        pieDataMap.put("value", allOkCount);
        pieData.add(pieDataMap);

        pieDataMap = new HashMap<String, Object>();
        pieDataMap.put("name", AssayConsts.ASSAY_RESULT_NO_OK);
        pieDataMap.put("value", allNoOkCount);
        pieData.add(pieDataMap);

        pieDataMap = new HashMap<String, Object>();
        pieDataMap.put("name", AssayConsts.ASSAY_RESULT_EXCESS_OK);
        pieDataMap.put("value", allExcessOkCount);
        pieData.add(pieDataMap);

        String[] pieTitle = { AssayConsts.ASSAY_RESULT_OK, AssayConsts.ASSAY_RESULT_NO_OK, AssayConsts.ASSAY_RESULT_EXCESS_OK };
        pieMap.put("title", pieTitle);
        pieMap.put("data", pieData);

        return pieMap;
    }

    /**
     * 获取化验项分组规则
     * 
     * @Title: getRuleList
     * @param assayRecord
     * @return
     * 
     */
    @RequestMapping("getRuleList")
    @ResponseBody
    public Map<String, Object> getRuleList(ReportPatientAssayRecordPO assayRecord) {
        Map<String, Object> map = new HashMap<String, Object>();
        PatientAssayGroupRulePO ruleCondition = new PatientAssayGroupRulePO();
        ruleCondition.setItemCode(assayRecord.getItemCode());
        ruleCondition.setFkTenantId(UserUtil.getTenantId());
        List<PatientAssayGroupRulePO> ruleList = patientAssayGroupRuleService.selectByCondition(ruleCondition);

        map.put("list", ruleList);
        return map;
    }

    /**
     * 重新整理分组规则
     * 
     * @Title: resetRules
     * @param assayRecord
     * @return
     * 
     */
    @RequestMapping("resetRules")
    @ResponseBody
    public Map<String, Object> resetRules(ReportPatientAssayRecordPO assayRecord) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Double> ruleList = assayRecord.getRuleList();
        if (CollectionUtils.isNotEmpty(ruleList)) {
            Iterator<Double> iter = ruleList.iterator();
            while (iter.hasNext()) {
                Double rule = iter.next();
                if (rule == null) {
                    iter.remove();
                }
            }
            Collections.sort(ruleList);
        }
        map.put("list", ruleList);
        return map;
    }

    /**
     * 获取化验项的明细
     * 
     * @Title: listItem
     * @param assayRecord
     * @return
     * 
     */
    @RequestMapping("countItem")
    @ResponseBody
    public Map<String, Object> countItem(ReportPatientAssayRecordPO assayRecord) {

        Map<String, Object> map = new HashMap<String, Object>();

        AssayHospDictPO assayDic = assayHospDictService.getByItemCode(assayRecord.getItemCode());
        assayRecord.setFkTenantId(UserUtil.getTenantId());
        List<ReportPatientAssayRecordPO> recordList = reportPatientAssayRecordService.selectDetailByDateType(assayRecord);

        PatientAssayGroupRulePO ruleCondition = new PatientAssayGroupRulePO();
        ruleCondition.setItemCode(assayRecord.getItemCode());

        List<Double> ruleList = assayRecord.getRuleList();

        if (CollectionUtils.isNotEmpty(ruleList)) {
            Iterator<Double> iter = ruleList.iterator();
            while (iter.hasNext()) {
                Double rule = iter.next();
                if (rule == null) {
                    iter.remove();
                }
            }
        }

        Map<String, Object> groupMap = AssayGroupRuleUtil.getPieMap(ruleList, recordList);

        map.put(CmDictConsts.GROUP_RULE, DictUtil.listByPItemCode(CmDictConsts.GROUP_RULE));
        map.put("groupMap", groupMap);
        map.put("okRangePie", getMonthSeasonDatas(recordList, assayRecord, assayDic));
        map.put("recordList", recordList);
        return map;
    }

    /**
     * 自动生成化验统计数据
     * 
     * @Title: insertAutoHistory
     * @param tenantId
     * @param itemCodes
     * @return
     *
     */
    @RequestMapping("subInterface/insertAutoHistory")
    @ResponseBody
    public Map<String, Object> insertAutoHistory(Integer tenantId, @RequestParam(required = false) Collection<String> itemCodes) {
        UserUtil.setThreadTenant(tenantId);
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> assayMonthList = patientAssayRecordBusiService.listAllAssayMonthByTenantId(tenantId);// 获取所有的化验月份
        for (String assayMonth : assayMonthList) {
            insertMonthData(assayMonth, tenantId, itemCodes);
        }

        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;

    }

    /**
     * 自动生成化验统计数据
     * 
     * @Title: insertAuto
     * @return
     * 
     */
    @RequestMapping("insertAuto")
    @ResponseBody
    public Map<String, Object> insertAuto(String monthStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 确认日期格式正确性，避免产生脏数据
        monthStr = DateFormatUtil.convertDateToStr(DateFormatUtil.convertStrToDate(monthStr, DateFormatUtil.FORMAT_YYYY_MM),
                        DateFormatUtil.FORMAT_YYYY_MM);
        insertMonthData(monthStr, UserUtil.getTenantId(), null);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 根据日期
     * 
     * @Title: refreshMonthReport
     * @param dateStr
     * @return
     * 
     */
    @RequestMapping("subInterface/refreshMonthReport")
    @ResponseBody
    public Map<String, Object> refreshMonthReport(Integer tenantId, String dateStr) {
        long start = System.currentTimeMillis();
        LOGGER.info("get request to hand dateStr={},tenantId={} assay report data", dateStr, tenantId);
        UserUtil.setThreadTenant(tenantId);
        Map<String, Object> map = new HashMap<String, Object>();
        String monthStr = patientAssayConfService.selectMonthAndYearByDate(DateFormatUtil.convertStrToDate(dateStr), tenantId, null);
        LOGGER.info("begin to hand month={},tenantId={} assay report data", monthStr, tenantId);
        Calendar cal = Calendar.getInstance();
        if (StringUtils.isNotBlank(monthStr)) {
            Date date = DateFormatUtil.convertStrToDate(monthStr, DateFormatUtil.FORMAT_YYYY_MM);
            cal.setTime(date);
        }

        String testItemSwitch = SysParamUtil.getValueByName(CmSysParamConsts.TEST_ITEM_SWITCH);
        /**
         * 策略逻辑统计 [TestItemSwitch[copy|simple]] 复制更新方式处理 业务逻辑实现 <br>
         * 1、判断当前月份是否存在数据【统计化验结果表 report_patient_assay_record】 1.1 存在执行 2 <br>
         * 1.2 不存在 复制上个月数据为当前月所有患者化验数据 <br>
         * 2、获取 参数租户，参数日期 所有患者化验结果表【原始患者化验原始数据 patient_assay_record】 <br>
         * 3、判断指定患者是否存在化验结果数据和化验项 <br>
         * 3.1存在修改指定患者化验项数据 【判断 report_patient_assay_record ,数据来源 patient_assay_record ,修改表数据 report_patient_assay_record】<br>
         * 3.2不存在新增 患者 和 化验项数据 【判断 report_patient_assay_record ,数据来源 patient_assay_record ,修改表数据 report_patient_assay_record】
         */
        if (CmSysParamConsts.TEST_ITEM_SWITCH_COPY.equalsIgnoreCase(testItemSwitch)) {
            reportPatientAssayRecordService.insertAutoCopyPreMonthDataByTenantId(monthStr, tenantId, null);
            reportPatientAssayRecordService.insertAutoCopyByTenantId(dateStr, monthStr, tenantId, null);
        } else {
            // 原始设计方案
            // 当月化验数据插入
            ReportPatientAssayRecordPO condition = new ReportPatientAssayRecordPO();
            condition.setDateType(AssayConsts.REPORT_DATE_TYPE_MONTH);
            condition.setAssayMonth(monthStr);
            condition.setFkTenantId(tenantId);
            reportPatientAssayRecordService.deleteByCondition(condition);
            String batchNo = reportPatientAssayRecordService.insertAutoByTenantId(AssayConsts.REPORT_DATE_TYPE_MONTH, monthStr, tenantId, null);
            // 删除临时数据
            deleteTempData(batchNo);
        }
        LOGGER.info("end of hand month={},tenantId={} assay report data,total cost {} ms", monthStr, tenantId, (System.currentTimeMillis() - start));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 数据插入动作
     * 
     * @Title: insertData
     * @param monthStr
     * @param tenantId
     * @param itemCodes
     *            指定项目
     *
     */
    public void insertMonthData(String monthStr, Integer tenantId, Collection<String> itemCodes) {
        long start = System.currentTimeMillis();
        LOGGER.info("begin to hand month={},tenantId={} assay report data", monthStr, tenantId);
        String testItemSwitch = SysParamUtil.getValueByName(CmSysParamConsts.TEST_ITEM_SWITCH);// 化验统计的方式
        /** 删除当月的所有记录 */
        ReportPatientAssayRecordPO condition = new ReportPatientAssayRecordPO();
        condition.setFkTenantId(tenantId);
        condition.setDateType(AssayConsts.REPORT_DATE_TYPE_MONTH);
        condition.setAssayMonth(monthStr);
        condition.setItemCodes(itemCodes);
        reportPatientAssayRecordService.deleteByCondition(condition);

        if (CmSysParamConsts.TEST_ITEM_SWITCH_COPY.equalsIgnoreCase(testItemSwitch)) { // 复制上月化验数据
            /** 获取当月的开始日期和结束日期 */
            PatientAssayConfPO patientAssayConf = patientAssayConfService.selectDateScopeByMonth(monthStr, tenantId);
            Date startDate = patientAssayConf.getStartDate();
            String startDateStr = DateFormatUtil.convertDateToStr(patientAssayConf.getStartDate());
            String endDateStr = DateFormatUtil.convertDateToStr(patientAssayConf.getEndDate());
            int days = DateFormatUtil.getDaysBetweenDates(startDateStr, endDateStr);
            /**
             * 策略逻辑统计 [TestItemSwitch[copy|simple]] 复制更新方式处理 业务逻辑实现 <br>
             * 1、判断当前月份是否存在数据【统计化验结果表 report_patient_assay_record】 1.1 存在执行 2 <br>
             * 1.2 不存在 复制上个月数据为当前月所有患者化验数据 <br>
             * 2、获取 参数租户，参数日期 所有患者化验结果表【原始患者化验原始数据 patient_assay_record】 <br>
             * 3、判断指定患者是否存在化验结果数据和化验项 <br>
             * 3.1存在修改指定患者化验项数据 【判断 report_patient_assay_record ,数据来源 patient_assay_record ,修改表数据 report_patient_assay_record】<br>
             * 3.2不存在新增 患者 和 化验项数据 【判断 report_patient_assay_record ,数据来源 patient_assay_record ,修改表数据 report_patient_assay_record】
             */
            for (int i = 0; i <= days; i++) {
                Date date = CalendarUtil.add(startDate, Calendar.DATE, i);
                String dateStr = DateFormatUtil.convertDateToStr(date);
                reportPatientAssayRecordService.insertAutoCopyPreMonthDataByTenantId(monthStr, tenantId, itemCodes);
                reportPatientAssayRecordService.insertAutoCopyByTenantId(dateStr, monthStr, tenantId, itemCodes);
            }
        } else {
            String batchNo = reportPatientAssayRecordService.insertAutoByTenantId(AssayConsts.REPORT_DATE_TYPE_MONTH, monthStr, tenantId, itemCodes);
            // 删除临时数据
            deleteTempData(batchNo);
        }
        LOGGER.info("end of hand month={},tenantId={} assay report data,total cost {} ms", monthStr, tenantId, (System.currentTimeMillis() - start));
    }

    /**
     * 
     * /** 下载化验报表
     * 
     * @Title: downloadYear
     * @param request
     * @param response
     * @param assayRecord
     * @throws Exception
     * 
     */
    @RequestMapping("downloadYear")
    public void downloadYear(HttpServletRequest request, HttpServletResponse response, ReportPatientAssayRecordPO assayRecord) throws Exception {

        AssayHospDictPO assayDic = assayHospDictService.getByItemCode(assayRecord.getItemCode());

        assayRecord.setFkTenantId(UserUtil.getTenantId());
        List<ReportPatientAssayRecordPO> recordList = reportPatientAssayRecordService.selectByDateType(assayRecord);

        List<Map<String, Object>> dataList = Lists.newArrayList();
        Map<String, Object> map = null;

        double allSum = 0.0;
        Integer allCountsSum = 0;
        Integer okCountsSum = 0;
        Integer noOkCountsSum = 0;
        Integer excessOkCountsSum = 0;
        Double okRatesSum = 0.0;
        Double avgValuesSum = 0.0;
        Double popValuesSum = 0.0;
        for (int i = 0; i < recordList.size(); i++) {
            ReportPatientAssayRecordPO record = recordList.get(i);
            map = new HashMap<String, Object>();
            record.setDateValueShow(getDateShow(record.getDateValue(), assayRecord.getDateType()));
            map.put("allCount", record.getAllCount());
            map.put("okCounts", record.getOkCount());
            map.put("noOkCounts", record.getNoOkCount());
            map.put("excessOkCounts", record.getExcessOkCount());
            map.put("okRates", record.getOkRates());
            map.put("avgValues", record.getAvg());
            map.put("popValues", record.getPop());
            dataList.add(map);

            allSum += record.getAllSum();
            allCountsSum += record.getAllCount();
            okCountsSum += record.getOkCount();
            if (record.getNoOkCount() != null) {
                noOkCountsSum += record.getNoOkCount();
            }
            if (record.getExcessOkCount() != null) {
                excessOkCountsSum += record.getExcessOkCount();
            }
            /*noOkCountsSum += record.getNoOkCount();
            excessOkCountsSum += record.getExcessOkCount();*/
            okRatesSum += record.getOkRates();
            avgValuesSum += record.getAvg();
            popValuesSum += record.getPop();
        }

        Map<String, Object> totalMap = new HashMap<String, Object>();
        totalMap.put("dateShow", "合计");
        totalMap.put("allCount", allCountsSum);
        totalMap.put("okCounts", okCountsSum);
        totalMap.put("noOkCounts", noOkCountsSum);
        totalMap.put("excessOkCounts", excessOkCountsSum);

        if (new BigDecimal(okCountsSum).equals(BigDecimal.ZERO) || new BigDecimal(allCountsSum).equals(BigDecimal.ZERO)) {
            totalMap.put("okRates", 0.0);
        } else {
            totalMap.put("okRates", new BigDecimal(okCountsSum * 100).divide(new BigDecimal(allCountsSum), 2, BigDecimal.ROUND_HALF_UP));
        }

        if (new BigDecimal(allSum).equals(BigDecimal.ZERO) || new BigDecimal(allCountsSum).equals(BigDecimal.ZERO)) {
            totalMap.put("avgValues", 0.0);
        } else {
            totalMap.put("avgValues", new BigDecimal(allSum).divide(new BigDecimal(allCountsSum), 2, BigDecimal.ROUND_HALF_UP));
        }
        totalMap.put("popValues", reportPatientAssayRecordService.selectPopByDateType(assayRecord));
        dataList.add(totalMap);

        String[] headNames = new String[] { "时间", "总数（例）", "达标数（例）", "不达标数（例）", "超标数（例）", "达标率（%）", "平均值", "标准差" };
        String[] columns = new String[] { "dateValueShow", "allCount", "okCount", "noOkCount", "excessOkCount", "okRates", "avg", "pop" };
        ReportSheetEntity sheetEntity = new ReportSheetEntity("化验报表统计", "化验报表统计", headNames, columns, recordList, ReportPatientAssayRecordPO.class);
        String fileItem = assayRecord.getAssayYear() + assayDic.getItemName();
        String filename = "化验年度统计（" + fileItem + "）.xls";
        ReportExcelTemplate template = new ReportExcelTemplate(new ReportSheetEntity[] { sheetEntity });
        template.writeToOutStream(request, response, filename);
    }

    /**
     * 下载化验详情名单（月度或者季度）
     * 
     * @Title: downloadDetail
     * @param request
     * @param response
     * @param assayRecord
     * @throws Exception
     * 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("downloadDetail")
    public void downloadDetail(HttpServletRequest request, HttpServletResponse response, ReportPatientAssayRecordPO assayRecord) throws Exception {
        Map<String, Object> map = countItem(assayRecord);
        List<ReportPatientAssayRecordPO> recordList = (List<ReportPatientAssayRecordPO>) map.get("recordList");

        String[] okShows = { "达标", "不达标", "超标" };
        Map<String, Object> groupMap = (Map<String, Object>) map.get("groupMap");
        String[] groupTitles = (String[]) groupMap.get("title");

        for (ReportPatientAssayRecordPO record : recordList) {
            record.setOkShow(okShows[record.getOkIndex()]);
            if (groupTitles != null && groupTitles.length > 0) {
                record.setGroupShow(groupTitles[record.getGroupIndex()]);
            }
        }

        List<ReportPatientAssayRecordPO> tempList = new ArrayList<ReportPatientAssayRecordPO>();

        ReportSheetEntity[] sheetEntitys = new ReportSheetEntity[3];

        // 化验名单sheet
        CollectionUtils.addAll(tempList, new Object[recordList.size()]);
        Collections.copy(tempList, recordList);
        String[] headNames = new String[] { "患者姓名", "化验值" };
        String[] columns = new String[] { "patientName", "result" };
        ReportSheetEntity sheetEntity = new ReportSheetEntity("化验名单", null, headNames, columns, tempList, ReportPatientAssayRecordPO.class);
        sheetEntitys[0] = sheetEntity;

        // 达标分组名单
        Collections.sort(tempList, (arg0, arg1) -> arg0.getOkIndex().compareTo(arg1.getOkIndex()));

        headNames = new String[] { "是否达标", "患者姓名", "化验值" };
        columns = new String[] { "okShow", "patientName", "result" };
        sheetEntity = new ReportSheetEntity("达标分组名单", null, headNames, columns, tempList, ReportPatientAssayRecordPO.class);
        Set<String> mergeColumns = new HashSet<String>();
        mergeColumns.add("okShow");
        sheetEntity.setMergeColumnNames(mergeColumns);
        sheetEntitys[1] = sheetEntity;

        // 化验值分组名单
        if (groupTitles != null && groupTitles.length > 0) {
            Collections.sort(tempList, (arg0, arg1) -> arg0.getGroupIndex().compareTo(arg1.getGroupIndex()));
            headNames = new String[] { "化验值范围", "患者姓名", "化验值" };
            columns = new String[] { "groupShow", "patientName", "result" };
            sheetEntity = new ReportSheetEntity("化验值分组名单", null, headNames, columns, tempList, ReportPatientAssayRecordPO.class);
            mergeColumns = new HashSet<String>();
            mergeColumns.add("groupShow");
            sheetEntity.setMergeColumnNames(mergeColumns);
            sheetEntitys[2] = sheetEntity;
        }

        AssayHospDictPO assayDic = assayHospDictService.getByItemCode(assayRecord.getItemCode());
        String fileItem = assayDic.getItemName();
        ReportExcelTemplate template = new ReportExcelTemplate(sheetEntitys);
        String dateType = assayRecord.getDateType();
        String filename = "";
        String month = assayRecord.getDateValue();
        StringBuffer quarterFinally = null;
        if ("s".equals(dateType)) {
            String quarter = assayRecord.getDateValue();
            String finalQuarters = selectMonthOrQuarter(quarter);
            quarterFinally = new StringBuffer();
            quarterFinally.append("化验详情表（").append(assayRecord.getAssayYear()).append("年第").append(finalQuarters).append("季度_").append(fileItem)
                            .append("）.xls");
            // 季度 化验详情表（" +quarterFinally+fileItem + "）.xls"
            filename = quarterFinally.toString();
        }
        if ("m".equals(dateType)) {
            String finalMonth = selectMonthOrQuarter(month);
            quarterFinally = new StringBuffer();
            quarterFinally.append("化验详情表（").append(assayRecord.getAssayYear()).append("年").append(finalMonth).append("月_").append(fileItem)
                            .append("）.xls");
            // 月份
            filename = quarterFinally.toString();
        }
        template.writeToOutStream(request, response, filename);
    }

    /**
     * 报表下载时获取具体的季度或者月份
     * 
     * @Title: selectMonthOrQuarter
     * @param quarter
     * @return
     *
     */
    public static String selectMonthOrQuarter(String quarter) {
        String[] quarters = quarter.split("-");
        String finalQuarters = quarters[1];
        if ("0".equals(finalQuarters.substring(0, 1))) {
            return finalQuarters.substring(1);
        }
        return finalQuarters;

    }

    /**
     * 根据批次号删除临时数据
     * 
     * @Title: deleteTempData
     * @param batchNo
     *
     */
    private synchronized void deleteTempData(String batchNo) {
        reportPatientAssayRecordService.deleteTempByBatchNo(batchNo);
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, 4 - 1);
        System.out.println(DateFormatUtil.convertDateToStr(cal.getTime()));
        System.out.println("6".substring(0, 1));
    }
}
