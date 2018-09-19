/**   
 * @Title: DiagnosisReportController.java 
 * @Package com.xtt.common.report.controller
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年9月19日 上午9:41:16 
 *
 */
package com.xtt.common.report.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.CmDiagnosisEntityValuePO;
import com.xtt.common.dao.po.CmDictDiagnosisPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.ReportParameterPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisEntityValueService;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.excel.ExcelUtil;
import com.xtt.common.util.excel.ExcelUtil.ExcelExportData;
import com.xtt.platform.util.lang.CollectionUtil;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/report/diagnosis/")
public class DiagnosisReportController {
    @Autowired
    private ICmDiagnosisEntityValueService cmDiagnosisEntityValueService;
    @Autowired
    private IDictDiagnosisService dictDiagnosisService;
    @Autowired
    private IPatientService patientService;

    /**
     * 原发病统计页面跳转
     * 
     * @Title: view
     * @return
     *
     */
    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("cm/report/diagnosis_report");
        return model;
    }

    /**
     * 年度原发病统计
     * 
     * @Title: countYearDiagnosisByCondition
     * @param reportParameterPO
     * @return
     *
     */
    @RequestMapping("countYearDiagnosisByCondition")
    public Map<String, Object> countYearDiagnosisByCondition(ReportParameterPO reportParameterPO) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        getSql(reportParameterPO, map);
        List<CmDiagnosisEntityValuePO> dataList = cmDiagnosisEntityValueService.countYearDiagnosisByCondition(map);
        if (CollectionUtil.isNotEmpty(dataList)) {
            for (CmDiagnosisEntityValuePO po : dataList) {
                if (po.getItemName().equals("其他") || po.getItemName().equals("原因不明")) {
                    CmDictDiagnosisPO pCmDictDiagnosisPO = dictDiagnosisService.selectPInfo(po.getItemCode()); // 获取父节点的信息
                    if (pCmDictDiagnosisPO.getpItemName().equals("造成原因")) {
                        CmDictDiagnosisPO pPCmDictDiagnosisPO = dictDiagnosisService.selectPInfo(pCmDictDiagnosisPO.getpItemCode()); // 再获取父节点的信息
                        po.setItemName(pPCmDictDiagnosisPO.getpItemName() + "——" + pCmDictDiagnosisPO.getpItemName() + "——" + po.getItemName());
                    } else {
                        po.setItemName(pCmDictDiagnosisPO.getpItemName() + "——" + po.getItemName());
                    }
                }
            }
        }
        resultMap.put("data", dataList);
        resultMap.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return resultMap;
    }

    /**
     * 点击查看患者详情
     * 
     * @Title: getPatientByCondition
     * @param reportParameterPO
     * @return
     *
     */
    @RequestMapping("getPatientByCondition")
    public Map<String, Object> getPatientByCondition(ReportParameterPO reportParameterPO) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        getSql(reportParameterPO, map);
        map.put("diagonsisItemCodes", reportParameterPO.getDiagonsisItemCodes());// 原发病类型
        List<PatientPO> dataList = patientService.listDiagnosisPatientByCondtion(map);
        resultMap.put("data", dataList);
        resultMap.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return resultMap;
    }

    /**
     * 拼接原发病统计的sql条件
     * 
     * @Title: getSql
     * @param reportParameterPO
     * @param map
     *
     */
    private void getSql(ReportParameterPO reportParameterPO, Map<String, Object> map) {
        map.put("tenantId", UserUtil.getTenantId());
        map.put("year", reportParameterPO.getYear());// 年份
        map.put("sex", reportParameterPO.getSex());// 性别
        map.put("isTemp", reportParameterPO.getPatientTempValue());// 病患类型
        map.put("diagonsisItemCodes", reportParameterPO.getDiagonsisItemCodes());// 原发病种类
        StringBuffer sb = new StringBuffer("");
        // 四种情况
        // 1.最大年龄和最小年龄都为空 (不需要判断)
        // 2.最小年龄为空 最大年龄不为空
        // 3.最大年龄为空，最小年龄不为空
        // 4.最大年龄最小 年龄都不为空
        Integer maxAge = reportParameterPO.getMaxAge();
        Integer minAge = reportParameterPO.getMinAge();
        Integer maxDialysisAge = reportParameterPO.getMaxDialysisAge();
        Integer minDialysisAge = reportParameterPO.getMinDialysisAge();

        if (null != maxAge && null == minAge) {
            sb.append("AND timestampdiff(YEAR, IFNULL(p.birthday,NOW()), now()) <=" + maxAge);
        }
        if (null == maxAge && null != minAge) {
            sb.append("AND timestampdiff(YEAR, IFNULL(p.birthday,NOW()), now()) >=" + minAge);
        }
        if (null != maxAge && null != minAge) {
            sb.append("AND timestampdiff(YEAR, IFNULL(p.birthday,NOW()), now()) BETWEEN " + minAge + " AND " + maxAge);
        }
        if (null != maxDialysisAge && null != minDialysisAge) {
            sb.append("  AND timestampdiff(YEAR,IFNULL(cdh.first_treatment_date,NOW()),now()) BETWEEN " + minDialysisAge + " AND " + maxDialysisAge);
        }
        if (null == maxDialysisAge && null != minDialysisAge) {
            sb.append("  AND timestampdiff(YEAR,IFNULL(cdh.first_treatment_date,NOW()),now()) >=" + minDialysisAge);
        }
        if (null == minDialysisAge && null != maxDialysisAge) {
            sb.append("  AND timestampdiff(YEAR,IFNULL(cdh.first_treatment_date,NOW()),now()) <=" + maxDialysisAge);
        }
        map.put("sql", sb.toString());// 拼接sql
    }

    /**
     * 原发病统计下载
     * 
     * @throws Exception
     * 
     * @Title: download
     *
     */
    @RequestMapping("download")
    public void download(ReportParameterPO reportParameterPO, String type, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // 存储多个sheet页数据
        LinkedHashMap<String, List<?>> lhMap = new LinkedHashMap<String, List<?>>();
        List<String[]> columNames = new ArrayList<String[]>();
        List<String[]> fieldNames = new ArrayList<String[]>();
        String fileNames[] = null;
        String fileName = null;
        if ("1".equals(type)) {// 年度统计
            fileNames = new String[1];
            fileNames[0] = reportParameterPO.getYear() + "原发病统计";
            Map<String, Object> data = countYearDiagnosisByCondition(reportParameterPO);
            List<Map<String, Object>> dataList1 = Lists.newArrayList();
            Integer totalCount = 0;// 患者数量
            List<CmDiagnosisEntityValuePO> reportData = (List<CmDiagnosisEntityValuePO>) data.get("data");
            Map<String, Object> map;
            // 获取患者总数量
            for (int i = 0; i < reportData.size(); i++) {
                CmDiagnosisEntityValuePO p = reportData.get(i);
                totalCount += (p.getCount());
            }

            for (int j = 0; j < reportData.size(); j++) {
                map = new HashMap<String, Object>();
                CmDiagnosisEntityValuePO p1 = reportData.get(j);
                map.put("name", p1.getItemName());
                map.put("count", p1.getCount());
                DecimalFormat dft = new DecimalFormat("0.00");// 格式化小数
                String percent = dft.format((float) p1.getCount() / (float) totalCount * 100);// 返回的是String类型
                map.put("percent", percent + "%");// （百分比）
                dataList1.add(map);
            }
            if (totalCount != 0) {
                map = new HashMap<String, Object>();
                map.put("name", "总计");
                map.put("count", totalCount);
                map.put("percent", "100%");// （百分比）
                dataList1.add(map);
            }
            lhMap.put(reportParameterPO.getYear() + "原发病统计", dataList1);
            columNames.add(new String[] { "原发病", "患者数量", "百分比" });
            fieldNames.add(new String[] { "name", "count", "percent" });
        }
        if ("2".equals(type)) {// 原发病统计
            Map<String, Object> dataMap = countDiagnosisByCondition(reportParameterPO);
            List<CmDiagnosisEntityValuePO> reportData = (List<CmDiagnosisEntityValuePO>) dataMap.get("data");
            fileNames = new String[1];
            fileNames[0] = reportData.get(0).getItemName();
            List<Map<String, Object>> dataList = Lists.newArrayList();
            Map<String, Object> map2;
            for (int j = 0; j < reportData.size(); j++) {
                map2 = new HashMap<String, Object>();
                CmDiagnosisEntityValuePO p1 = reportData.get(j);
                // 占据比例
                map2.put("year", p1.getYear());
                map2.put("count", p1.getCount());
                DecimalFormat dft = new DecimalFormat("0.00");// 格式化小数
                String percent = dft.format((float) p1.getCount() / (float) p1.getCountAll() * 100);// 返回的是String类型
                map2.put("percent", percent + "%");// （百分比）
                dataList.add(map2);
            }
            lhMap.put(reportData.get(0).getItemName(), dataList);
            fileName = reportData.get(0).getItemName();
            columNames.add(new String[] { "年份", "患者数量", "百分比" });
            fieldNames.add(new String[] { "year", "count", "percent" });
        }
        if ("3".equals(type)) {// 年度统计+详情
            fileNames = new String[2];
            fileNames[0] = reportParameterPO.getYear() + "原发病统计";
            fileNames[1] = reportParameterPO.getYear() + reportParameterPO.getPatientTitle().replace(" ", "");
            Map<String, Object> data = countYearDiagnosisByCondition(reportParameterPO);
            List<Map<String, Object>> dataList1 = Lists.newArrayList();
            int totalCount = 0;// 患者数量
            List<CmDiagnosisEntityValuePO> reportData = (List<CmDiagnosisEntityValuePO>) data.get("data");
            Map<String, Object> map;
            // 获取患者总数量
            for (int i = 0; i < reportData.size(); i++) {
                CmDiagnosisEntityValuePO p = reportData.get(i);
                totalCount += (p.getCount());
            }
            for (int j = 0; j < reportData.size(); j++) {
                map = new HashMap<String, Object>();
                CmDiagnosisEntityValuePO p1 = reportData.get(j);
                // 占据比例
                map.put("name", p1.getItemName());
                map.put("count", p1.getCount());
                DecimalFormat dft = new DecimalFormat("0.00");// 格式化小数
                String percent = dft.format((float) p1.getCount() / (float) totalCount * 100);// 返回的是String类型
                map.put("percent", percent + "%");// （百分比）
                dataList1.add(map);
            }
            if (totalCount != 0) {
                map = new HashMap<String, Object>();
                map.put("name", "总计");
                map.put("count", totalCount);
                map.put("percent", "100%");// （百分比）
                dataList1.add(map);
            }
            lhMap.put(fileNames[0], dataList1);
            reportParameterPO.setDiagonsisItemCodes(new String[] { reportParameterPO.getItemCode() });
            if (reportParameterPO.getItemCode().equals("qb100000")) {
                reportParameterPO.setDiagonsisItemCodes(null);
            }
            Map<String, Object> PatientList = getPatientByCondition(reportParameterPO);
            List<PatientPO> patientList = (List<PatientPO>) PatientList.get("data");
            dataList1 = Lists.newArrayList();
            for (int j = 0; j < patientList.size(); j++) {
                PatientPO p = patientList.get(j);
                map = new HashMap<String, Object>();
                // 占据比例
                map.put("patientName", p.getName());
                map.put("registerTime", p.getRegisterTime());
                map.put("content", p.getContent());
                dataList1.add(map);
            }
            lhMap.put(fileNames[1], dataList1);
            columNames.add(new String[] { "原发病", "患者数量", "百分比" });
            columNames.add(new String[] { "患者姓名", "登记日期", "备注" });

            fieldNames.add(new String[] { "name", "count", "percent" });
            fieldNames.add(new String[] { "patientName", "registerTime", "content" });
            fileName = fileNames[1];
        }
        if ("4".equals(type)) {// 原发病统计+详情
            Map<String, Object> dataMap = countDiagnosisByCondition(reportParameterPO);
            List<CmDiagnosisEntityValuePO> reportData = (List<CmDiagnosisEntityValuePO>) dataMap.get("data");
            fileNames = new String[2];
            fileNames[0] = reportData.get(0).getItemName();
            fileNames[1] = reportParameterPO.getItemYear() + reportParameterPO.getPatientTitle();
            List<Map<String, Object>> dataList = Lists.newArrayList();
            Map<String, Object> map2;
            for (int j = 0; j < reportData.size(); j++) {
                map2 = new HashMap<String, Object>();
                CmDiagnosisEntityValuePO p1 = reportData.get(j);
                // 占据比例
                map2.put("year", p1.getYear());
                map2.put("count", p1.getCount());
                DecimalFormat dft = new DecimalFormat("0.00");// 格式化小数
                String percent = dft.format((float) p1.getCount() / (float) p1.getCountAll() * 100);// 返回的是String类型
                map2.put("percent", percent + "%");// （百分比）
                dataList.add(map2);
            }
            lhMap.put(fileNames[0], dataList);
            fileName = reportData.get(0).getItemName();
            reportParameterPO.setYear(reportParameterPO.getItemYear());
            reportParameterPO.setDiagonsisItemCodes(new String[] { reportParameterPO.getItemCode() });
            Map<String, Object> patientMap = getPatientByCondition(reportParameterPO);
            List<PatientPO> patientList = (List<PatientPO>) patientMap.get("data");
            dataList = Lists.newArrayList();
            for (int j = 0; j < patientList.size(); j++) {
                PatientPO p = patientList.get(j);
                map2 = new HashMap<String, Object>();
                // 占据比例
                map2.put("patientName", p.getName());
                map2.put("registerTime", p.getRegisterTime());
                map2.put("content", p.getContent());
                dataList.add(map2);
            }
            lhMap.put(fileNames[1], dataList);
            columNames.add(new String[] { "年份", "患者数量", "百分比" });
            columNames.add(new String[] { "患者姓名", "登记日期", "备注" });

            fieldNames.add(new String[] { "year", "count", "percent" });
            fieldNames.add(new String[] { "patientName", "registerTime", "content" });
            fileName = fileNames[1];
        }
        ExcelExportData setInfo = new ExcelExportData();
        setInfo.setDataMap(lhMap);
        setInfo.setFieldNames(fieldNames);
        setInfo.setColumnNames(columNames);
        // 将需要导出的数据输出到文件
        if ("1".equals(type)) {
            setInfo.setTitles(new String[] { fileNames[0] });
            ExcelUtil.export2Stream(request, response, setInfo, reportParameterPO.getYear() + "原发病统计.xls");
        }
        if ("2".equals(type)) {
            setInfo.setTitles(new String[] { fileNames[0] });
            ExcelUtil.export2Stream(request, response, setInfo, fileName + ".xls");
        }
        if ("3".equals(type)) {
            setInfo.setTitles(new String[] { fileNames[0], fileNames[1] });
            ExcelUtil.export2Stream(request, response, setInfo, fileName + ".xls");
        }
        if ("4".equals(type)) {
            setInfo.setTitles(new String[] { fileNames[0], fileNames[1] });
            ExcelUtil.export2Stream(request, response, setInfo, fileName + ".xls");
        }
        fileName = null;
    }

    /**
     * 单一原发病统计
     * 
     * @Title: countDiagnosisByCondition
     * @param reportParameterPO
     * @return
     *
     */
    @RequestMapping("countDiagnosisByCondition")
    public Map<String, Object> countDiagnosisByCondition(ReportParameterPO reportParameterPO) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        getSql(reportParameterPO, map);
        // 获取本年时间
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR); // 当年时间
        int startDate = year - 2;
        map.put("startDate", startDate);// 开始年份
        map.put("endDate", year);// 结束年份

        // 如果开始年份
        // 结束年份为空
        if (StringUtil.isNotBlank(reportParameterPO.getStartDate()) && StringUtil.isNotBlank(reportParameterPO.getEndDate())) {
            map.put("startDate", reportParameterPO.getStartDate());// 开始年份
            map.put("endDate", reportParameterPO.getEndDate());// 结束年份
        }
        // 第一次默认加载 原发病种类为空
        CmDiagnosisEntityValuePO cmDictDiagnosis = null;
        if (StringUtil.isEmpty(reportParameterPO.getDiagonsisItemCode())) {
            // 第一次加载
            // 先查询本年度最多的原发病种类
            cmDictDiagnosis = cmDiagnosisEntityValueService.getItemCodeByYear();
            map.put("diagonsisItemCode", cmDictDiagnosis.getItemCode());// 原发病类型
            reportParameterPO.setDiagonsisItemCode(cmDictDiagnosis.getItemCode());
        } else {
            map.put("diagonsisItemCode", reportParameterPO.getDiagonsisItemCode());// 原发病类型
        }
        List<CmDiagnosisEntityValuePO> reportData = cmDiagnosisEntityValueService.countByCondtion(map);
        if (CollectionUtil.isNotEmpty(reportData)) {
            for (CmDiagnosisEntityValuePO po : reportData) {
                // 同年所有原发病的数量
                Integer countAll = cmDiagnosisEntityValueService.countAllDiagnosis(po.getYear(), UserUtil.getTenantId());
                po.setCountAll(countAll);
            }
        }
        // 获取父节点信息
        CmDictDiagnosisPO cmDictDiagnosisPO = dictDiagnosisService.selectPInfo(reportParameterPO.getDiagonsisItemCode());
        /**
         * 如果子节点 的itemname为其他 itemName=自己+父节点
         */
        if (cmDictDiagnosisPO.getItemName().equals("其他") || cmDictDiagnosisPO.getItemName().equals("原因不明")) {
            if (cmDictDiagnosisPO.getpItemName().equals("造成原因")) {
                CmDictDiagnosisPO pPCmDictDiagnosisPO = dictDiagnosisService.selectPInfo(cmDictDiagnosisPO.getpItemCode()); // 再获取父节点的信息
                cmDictDiagnosisPO.setItemName(pPCmDictDiagnosisPO.getpItemName() + "——" + cmDictDiagnosisPO.getpItemName() + "——"
                                + cmDictDiagnosisPO.getItemName());
            } else {
                cmDictDiagnosisPO.setItemName(cmDictDiagnosisPO.getpItemName() + "——" + cmDictDiagnosisPO.getItemName());
            }
        }
        resultMap.put("data", reportData);
        resultMap.put("itemName", cmDictDiagnosisPO.getItemName());
        resultMap.put("itemCode", cmDictDiagnosisPO.getItemCode());
        resultMap.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return resultMap;
    }
}
