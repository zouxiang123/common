/**   
 * @Title: YearEvaluationRepostController.java 
 * @Package com.xtt.txgl.report.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月18日 下午3:01:49 
 *
 */
package com.xtt.common.report.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientOutcomeReport;
import com.xtt.common.dao.po.ReportParameterPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.report.service.IPatientOutcomeReportService;
import com.xtt.common.util.BusinessDateUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.report.ReportExcelTemplate;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Controller
@RequestMapping("/report/outcome/")
public class OutcomeReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutcomeReportController.class);
    @Autowired
    private IPatientOutcomeReportService patientOutcomeReportService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("report/outcome_report");
        Calendar c = Calendar.getInstance();
        model.addObject("currentYear", c.get(Calendar.YEAR));
        // id号显示方式
        model.addObject("showPatientSerialNum", SysParamUtil.getValueByName(CmSysParamConsts.showPatientSerialNum));
        return model;
    }

    /**
     * 获取年份的统计报表
     * 
     * @Title: countByDateType
     * @param reportParameterPO
     * @return
     * 
     */
    @RequestMapping("getReportData")
    @ResponseBody
    public Map<String, Object> getReportData(ReportParameterPO reportParameterPO) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dataMap = patientOutcomeReportService.getReportData(reportParameterPO);
        map.putAll(dataMap);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("detailView")
    public ModelAndView detailView(ReportParameterPO reportParameterPO) {
        ModelAndView model = new ModelAndView("report/outcome_detail_report");
        model.addObject("year", reportParameterPO.getYear());
        model.addObject("month", reportParameterPO.getMonth());
        model.addObject("type", reportParameterPO.getType());
        model.addObject("patientTempValue", reportParameterPO.getPatientTempValue());
        return model;
    }

    @RequestMapping("getPatients")
    @ResponseBody
    public Map<String, Object> getPatients(ReportParameterPO reportParameterPO) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtil.isEmpty(reportParameterPO.getMonth()) && StringUtils.isEmpty(reportParameterPO.getYear())) {
            LOGGER.error("the param month can't be empty");
            map.put(CommonConstants.STATUS, CommonConstants.FAILURE);
            return map;
        }
        map.put("items", getOutcomePatients(reportParameterPO));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    private List<PatientOutcomeReport> getOutcomePatients(ReportParameterPO reportParameterPO) {
        String year = reportParameterPO.getYear();
        String month = reportParameterPO.getMonth();
        String type = reportParameterPO.getType();
        Calendar c = Calendar.getInstance();
        PatientOutcomeReport record = new PatientOutcomeReport();
        if (StringUtils.isNotBlank(month)) {
            String[] strs = month.split("-");
            c.set(Calendar.YEAR, Integer.valueOf(strs[0]));
            c.set(Calendar.MONTH, Integer.valueOf(strs[1]) - 1);
            // 获取month的开始和截止时间
            record.setStartDate(BusinessDateUtil.getMonthStartOrEnd(c.getTime(), true));
            record.setEndDate(BusinessDateUtil.getMonthStartOrEnd(c.getTime(), false));
        } else {
            c.set(Calendar.YEAR, Integer.valueOf(year));
            // 获取year的开始和截止时间
            record.setStartDate(BusinessDateUtil.getYearStartOrEnd(c.getTime(), true));
            record.setEndDate(BusinessDateUtil.getYearStartOrEnd(c.getTime(), false));
        }
        List<DictDto> dictionaryList = DictUtil.listByPItemCode(CmDictConsts.PATIENT_OUTCOME_TYPE);
        if (StringUtils.isNotBlank(type)) {
            dictionaryList = dictionaryList.stream().filter(dict -> dict.getItemName().equals(type)).collect(Collectors.toList());
            record.setType(dictionaryList.get(0).getItemCode());
        }
        String[] types = new String[dictionaryList.size()];
        int j = 0;
        for (DictDto dictDto : dictionaryList) {
            types[j] = dictDto.getItemCode();
            j++;
        }
        record.setTypes(types);
        record.setIsTemp(reportParameterPO.getIsTemp());
        // 根据患者和转归时间排序过的转归记录
        List<PatientOutcomeReport> list = patientOutcomeReportService.listByCondition(record);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<PatientOutcomeReport>();
        }
        Long prePatientId = null;
        PatientOutcomeReport or;
        String outComeDate = "";
        for (Iterator<PatientOutcomeReport> it = list.iterator(); it.hasNext();) {
            or = it.next();
            if (or.getFkPatientId().equals(prePatientId)
                            && outComeDate.equals(DateFormatUtil.convertDateToStr(or.getRecordDate(), DateFormatUtil.FORMAT_DATE1))) {
                it.remove();
            } else {
                prePatientId = or.getFkPatientId();
                outComeDate = DateFormatUtil.convertDateToStr(or.getRecordDate(), DateFormatUtil.FORMAT_DATE1);
            }
        }
        return list;
    }

    /**
     * 报表下载
     * 
     * @param reportParameterPO
     * @return
     * @throws Exception
     * 
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response, ReportParameterPO reportParameterPO) throws Exception {
        String year = reportParameterPO.getYear();
        String month = reportParameterPO.getMonth();
        String type = reportParameterPO.getType();
        Map<String, Object> yearDataMap = patientOutcomeReportService.getReportData(reportParameterPO);
        month = StringUtils.isEmpty(month) ? "" : month;
        type = StringUtils.isEmpty(type) ? "" : type;

        String[] sheetNames = { year + type + "转归患者统计" };
        if (StringUtil.isNotBlank(month)) {
            sheetNames = new String[] { month + "月" + type + "转归患者统计" };
        }
        ReportExcelTemplate excel = new ReportExcelTemplate();
        excel.createSheet(sheetNames);
        int rowNum = 0;
        int sheetIndex = 0;
        if (StringUtils.isNotBlank(year) && StringUtils.isEmpty(month) && StringUtils.isEmpty(type)) {
            String[] firstCols = (String[]) yearDataMap.get("cols");
            String[] colCodes = (String[]) yearDataMap.get("colCodes");
            Map<String, List<Map<String, String>>> rowsMap = (Map<String, List<Map<String, String>>>) yearDataMap.get("rows");
            String[] colNames = new String[rowsMap.size() + 1];
            colNames[0] = firstCols[0];
            int index = 1;
            for (List<Map<String, String>> row : rowsMap.values()) {
                colNames[index] = row.get(0).get("colValue");
                index++;
            }

            List<List<String>> rowData = new ArrayList<List<String>>(colCodes.length);
            {// 正文数据封装
                List<String> row;
                for (int i = 0; i < colCodes.length; i++) {// 遍历所有的列，列值不存在时设为空
                    String code = colCodes[i];
                    if (code == "typeCol") {// 如果是标题行
                        continue;
                    }
                    row = new ArrayList<String>(rowsMap.size());
                    row.add(firstCols[i]);
                    for (Entry<String, List<Map<String, String>>> entry : rowsMap.entrySet()) {
                        String value = "0";// 默认显示0
                        for (Map<String, String> map : entry.getValue()) {
                            if (code.equals(map.get("itemCode"))) {
                                value = map.get("colValue");
                                break;
                            }
                        }
                        if ("total".equals(code) && !entry.getKey().equals("total")) {// 如果是汇总行,而且不是汇总列
                            if (Integer.valueOf(value) > 0) {
                                float total = Float.valueOf(rowsMap.get("total").get(rowsMap.get("total").size() - 1).get("colValue"));// 获取总人数
                                value = value + "（" + String.format("%.2f", Integer.valueOf(value) / total * 100.00) + "%）";// 计算百分比
                            }
                        }
                        row.add(value);
                    }
                    rowData.add(row);
                }
            }
            // 设置默认列宽
            excel.setDefaultColumnWidth(sheetIndex, 220 / colNames.length);
            // 生成标题行
            rowNum = excel.createHeadRow(sheetIndex, rowNum, colNames, sheetNames[0]);
            // 创建正文
            rowNum = excel.createContent(sheetIndex, rowNum, rowData);
        } else {
            float total = 0;
            List<Map<String, String>> monthTotal = new ArrayList<Map<String, String>>();
            {// 封装汇总数据
                Map<String, List<Map<String, String>>> rowsMap = (Map<String, List<Map<String, String>>>) yearDataMap.get("rows");
                for (Entry<String, List<Map<String, String>>> entry : rowsMap.entrySet()) {
                    if (entry.getKey().equals("total")) {// 过滤汇总行
                        continue;
                    }
                    for (Map<String, String> cellMap : entry.getValue()) {
                        if (cellMap.get("itemCode").equals(month)) {
                            Map<String, String> temp = new HashMap<String, String>();
                            temp.put("name", entry.getValue().get(0).get("colValue"));
                            temp.put("value", cellMap.get("colValue"));
                            monthTotal.add(temp);
                            break;
                        }
                    }
                }
            }
            int maxColCount = 0;
            if (monthTotal.size() > 0) {
                {// 生成月份统计
                    String[] colNames = { "名称", "数量", "占比" };
                    maxColCount = colNames.length;
                    for (int i = 0; i < monthTotal.size(); i++) {
                        total += Integer.valueOf(monthTotal.get(i).get("value"));
                    }
                    rowNum = excel.createHeadRow(sheetIndex, rowNum, colNames, sheetNames[0]);
                    List<List<String>> rowData = new ArrayList<List<String>>();
                    List<String> row;
                    NumberFormat nf = NumberFormat.getPercentInstance();
                    nf.setMinimumFractionDigits(2);
                    for (Map<String, String> map : monthTotal) {
                        row = new ArrayList<String>();
                        row.add(map.get("name"));
                        row.add(map.get("value"));
                        row.add(nf.format(Integer.valueOf(map.get("value")) / total));
                        rowData.add(row);
                    }
                    row = new ArrayList<String>();
                    row.add("合计");
                    row.add((int) total + "");
                    row.add("100%");
                    rowData.add(row);
                    rowNum = excel.createContent(sheetIndex, rowNum, rowData);
                }
            }
            rowNum++;
            {// 生成患者列表
                List<PatientOutcomeReport> list = getOutcomePatients(reportParameterPO);

                String[] colNames = { "名称", "时间", "类别", "原因" };
                maxColCount = colNames.length > maxColCount ? colNames.length : maxColCount;
                List<List<String>> rowData = new ArrayList<List<String>>();
                List<String> row;
                for (PatientOutcomeReport or : list) {
                    row = new ArrayList<String>();
                    row.add(or.getPatientName());
                    row.add(or.getRecordDateShow());
                    row.add(or.getTypeShow());
                    row.add(or.getReason());
                    rowData.add(row);
                }

                rowNum = excel.createHeadRow(sheetIndex, rowNum, colNames, "患者列表");
                rowNum = excel.createContent(sheetIndex, rowNum, rowData);
                excel.setDefaultColumnWidth(sheetIndex, 220 / maxColCount);
            }

        }
        String timeStyle = "yyyyMMdd_HHmm";
        String currentTime = excel.getCurrentTime(timeStyle);
        excel.writeToOutStream(request, response, sheetNames[0] + "_" + currentTime + ".xls");
    }

}
