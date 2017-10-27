/**   
 * @Title: PatientReportController.java 
 * @Package com.xtt.common.report.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年9月20日 上午10:08:36 
 *
 */
package com.xtt.common.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.ReportParameterPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.patient.service.IPatientCardService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.report.service.IPatientReportService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.excel.ExcelUtil;
import com.xtt.common.util.excel.ExcelUtil.ExcelExportData;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/report/patient/")
public class PatientReportController {
    @Autowired
    private IPatientReportService patientReportService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IPatientCardService patientCardService;

    /**
     * 跳转到患者统计报表页面
     * 
     * @Title: view
     * @param reportType
     * @param sysOwner
     * @return
     *
     */
    @RequestMapping("view")
    public ModelAndView view(String reportType, String sysOwner) {
        if (StringUtil.isBlank(reportType)) {
            reportType = "1";
        }
        ModelAndView model = new ModelAndView("report/patient_report");
        model.addObject("reportType", reportType);
        // 费用类型
        model.addObject("chargeTypes", DictUtil.listByPItemCode(CmDictConsts.PATIENT_CHARGE_TYPE));
        model.addObject("sysOwner", sysOwner);
        return model;
    }

    /**
     * 获取包表数据
     * 
     * @Title: getReportData
     * @param reportParameterPO
     * @return
     * 
     */
    @RequestMapping("getReportData")
    @ResponseBody
    public Map<String, Object> getReportData(ReportParameterPO reportParameterPO) {
        Integer ageRange = reportParameterPO.getAgeRange();
        Integer dialysisAgeRange = reportParameterPO.getDialysisAgeRange();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        /*paraMap.put("fromDate", BusinessReportUtil.getStartOrEndDate(startDateStr, true));
        paraMap.put("toDate", BusinessReportUtil.getStartOrEndDate(endDateStr, false));*/
        paraMap.put("ageRange", ageRange);
        paraMap.put("dialysisAgeRange", dialysisAgeRange);
        paraMap.put("isTemp", reportParameterPO.getIsTemp());

        paraMap.put("isMedical", reportParameterPO.getMedicalTypeValue());
        paraMap.put("patientName", reportParameterPO.getPatientName());

        // ageRangeList(年龄段统计),sexList(性别统计),dialysisRangeList(透析龄统计)
        Map<String, List<Map<String, Object>>> retMap = patientReportService.listReportData(paraMap, reportParameterPO.getPatientReportType());
        Map<String, Object> map = new HashMap<String, Object>();
        List<DictDto> chargeTypeList = DictUtil.listByPItemCode(CmDictConsts.PATIENT_CHARGE_TYPE);
        if (reportParameterPO.getPatientReportType() == 1) {// 年龄段统计
            map.put("ageRangeList", retMap.get("ageRangeList"));
        }
        if (reportParameterPO.getPatientReportType() == 3) {// 性别统计
            map.put("sexList", retMap.get("sexList"));
        }
        if (reportParameterPO.getPatientReportType() == 4) {// 医保信息统计
            map.put("medicalList", retMap.get("medicalList"));
            map.put("medicalTitleList", chargeTypeList);// 标题
        }
        if (reportParameterPO.getPatientReportType() == 1 || reportParameterPO.getPatientReportType() == 2) {
            List<Map<String, Object>> avgMapList = retMap.get("avgMap");
            if (avgMapList.size() > 0 && avgMapList.get(0) != null) {
                Map<String, Object> avgMap = avgMapList.get(0);
                map.put("avgAge", avgMap.get("avgAge"));
                map.put("avgDialysisAge", avgMap.get("avgDialysisAge"));
            }
        }
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 下载患者统计
     * 
     * @throws Exception
     */
    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response, ReportParameterPO reportParameterPO) throws Exception {
        Integer ageRange = reportParameterPO.getAgeRange();
        Integer dialysisAgeRange = reportParameterPO.getDialysisAgeRange();
        Map<String, Object> paraMap = new HashMap<String, Object>();

        paraMap.put("ageRange", ageRange);
        paraMap.put("dialysisAgeRange", dialysisAgeRange);
        paraMap.put("isTemp", reportParameterPO.getPatientTempValue());

        paraMap.put("isMedical", reportParameterPO.getMedicalTypeValue());
        paraMap.put("patientName", reportParameterPO.getPatientName());

        // ageRangeList(年龄段统计),sexList(性别统计),dialysisRangeList(透析龄统计),医保信息统计
        Map<String, List<Map<String, Object>>> retMap = patientReportService.listReportData(paraMap, reportParameterPO.getPatientReportType());

        LinkedHashMap<String, List<?>> lhMap = new LinkedHashMap<String, List<?>>();

        List<String[]> columNames = new ArrayList<String[]>();
        List<String[]> fieldNames = new ArrayList<String[]>();
        List<Map<String, Object>> dataList = Lists.newArrayList();
        if (reportParameterPO.getPatientReportType() == 1) {// 年龄段
            columNames.add(new String[] { "年龄段", "数量", "占比" });
            fieldNames.add(new String[] { "ageRange", "count", "percent" });
            int totalCount = 0;// 总数量
            List<Map<String, Object>> ageRangeList = retMap.get("ageRangeList");
            Map<String, Object> map;
            // 先遍历获取整个数量
            for (int j = 0; j < ageRangeList.size(); j++) {
                totalCount += Integer.parseInt(ageRangeList.get(j).get("value") + "");
            }
            for (int j = 0; j < ageRangeList.size(); j++) {
                map = new HashMap<String, Object>();
                String count = ageRangeList.get(j).get("value") + "";
                // 占据比例
                Double percent = (double) (Integer.parseInt(count) / Double.parseDouble(totalCount + ""));
                Double upNum = (double) ((int) Math.round(percent * 10000));// 四舍五入
                String newPercent = (int) (upNum / 100) + "." + (int) (upNum % 100);
                map.put("ageRange", ageRangeList.get(j).get("name"));
                map.put("count", ageRangeList.get(j).get("value"));
                map.put("percent", newPercent + "%");
                dataList.add(map);
            }
            map = new HashMap<String, Object>();
            map.put("ageRange", "合计");
            map.put("count", totalCount);
            map.put("percent", "100%");
            dataList.add(map);
            lhMap.put("年龄段统计", dataList);
        }

        if (reportParameterPO.getPatientReportType() == 3) {
            columNames.add(new String[] { "透析龄", "数量", "占比" });
            fieldNames.add(new String[] { "sex", "count", "percent" });
            List<Map<String, Object>> sexList = retMap.get("sexList");
            int totalCount = 0;// 总数量
            // 先遍历获取整个数量
            for (int j = 0; j < sexList.size(); j++) {
                totalCount += Integer.parseInt(sexList.get(j).get("value") + "");
            }
            Map<String, Object> map;
            for (int j = 0; j < sexList.size(); j++) {
                map = new HashMap<String, Object>();
                String count = sexList.get(j).get("value") + "";
                // 占据比例
                Double percent = (double) (Integer.parseInt(count) / Double.parseDouble(totalCount + ""));
                Double upNum = (double) ((int) Math.round(percent * 10000));// 四舍五入
                String newPercent = (int) (upNum / 100) + "." + (int) (upNum % 100);
                map.put("sex", sexList.get(j).get("name"));
                map.put("count", sexList.get(j).get("value"));
                map.put("percent", newPercent + "%");
                dataList.add(map);
            }
            map = new HashMap<String, Object>();
            map.put("sex", "合计");
            map.put("count", totalCount);
            map.put("percent", "100%");
            dataList.add(map);
            lhMap.put("性别统计", dataList);
        }

        ExcelExportData setInfo = new ExcelExportData();

        setInfo.setDataMap(lhMap);
        setInfo.setFieldNames(fieldNames);
        setInfo.setColumnNames(columNames);
        List<Map<String, Object>> avgMapList = null;
        Map<String, Object> avgMap = null;
        if (reportParameterPO.getPatientReportType() == 1) {
            avgMapList = retMap.get("avgMap");
            avgMap = avgMapList.get(0);
        }

        // 将需要导出的数据输出到文件
        if (reportParameterPO.getPatientReportType() == 1) {
            setInfo.setTitles(new String[] { "年龄段统计(平均年龄：" + String.format("%.0f", avgMap.get("avgAge")) + ")" });
            ExcelUtil.export2Stream(request, response, setInfo, "年龄段统计.xls");
        }
        if (reportParameterPO.getPatientReportType() == 3) {
            setInfo.setTitles(new String[] { "性别统计", });
            ExcelUtil.export2Stream(request, response, setInfo, "性别统计.xls");
        }

        if (reportParameterPO.getPatientReportType() == 4) {
            List<Map<String, Object>> medicalList = retMap.get("medicalList");
            List<Map<String, Object>> valueList = new ArrayList<>();
            Integer count = medicalList.size();
            Map<String, Object> valueMap = null;
            for (Map<String, Object> map : medicalList) {
                valueMap = new LinkedHashMap<>();
                valueMap.put("serialNum", map.get("serialNum"));
                valueMap.put("patientName", map.get("patientName"));
                valueMap.put("chargeName", map.get("chargeName"));
                valueList.add(valueMap);
            }
            downloadMedicalReport(response, valueList, count);
        }

    }

    public void downloadMedicalReport(HttpServletResponse response, List<Map<String, Object>> medicalList, Integer count) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        // 产生工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            // 进行转码，使其支持中文文件名
            codedFileName = java.net.URLEncoder.encode("医保信息统计", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");

            // 产生工作表对象
            HSSFSheet sheet = workbook.createSheet();

            // 初始化表头行字体
            HSSFFont headFont = workbook.createFont();
            headFont.setFontName("宋体");
            headFont.setFontHeightInPoints((short) 10);
            headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            headFont.setCharSet(Font.DEFAULT_CHARSET);
            headFont.setColor(IndexedColors.BLUE_GREY.index);
            HSSFCellStyle headStyle = workbook.createCellStyle();
            headStyle.setAlignment(CellStyle.ALIGN_CENTER);
            headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            headStyle.setFont(headFont);
            headStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
            headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
            headStyle.setBorderBottom(CellStyle.BORDER_THIN);
            headStyle.setBorderLeft(CellStyle.BORDER_THIN);
            headStyle.setBorderRight(CellStyle.BORDER_THIN);
            headStyle.setTopBorderColor(IndexedColors.BLUE.index);
            headStyle.setBottomBorderColor(IndexedColors.BLUE.index);
            headStyle.setLeftBorderColor(IndexedColors.BLUE.index);
            headStyle.setRightBorderColor(IndexedColors.BLUE.index);

            // 初始化内容行样式
            HSSFFont contentFont = workbook.createFont();
            contentFont.setFontName("宋体");
            contentFont.setFontHeightInPoints((short) 10);
            contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
            contentFont.setCharSet(Font.DEFAULT_CHARSET);
            contentFont.setColor(IndexedColors.BLUE_GREY.index);
            HSSFCellStyle contentStyle = workbook.createCellStyle();
            contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
            contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            contentStyle.setFont(contentFont);
            contentStyle.setBorderTop(CellStyle.BORDER_THIN);
            contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
            contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
            contentStyle.setBorderRight(CellStyle.BORDER_THIN);
            contentStyle.setTopBorderColor(IndexedColors.BLUE.index);
            contentStyle.setBottomBorderColor(IndexedColors.BLUE.index);
            contentStyle.setLeftBorderColor(IndexedColors.BLUE.index);
            contentStyle.setRightBorderColor(IndexedColors.BLUE.index);
            contentStyle.setWrapText(true); // 字段换行

            // 初始化标题行字体
            HSSFFont titleFont = workbook.createFont();
            titleFont.setFontName("宋体");
            titleFont.setFontHeightInPoints((short) 20);
            titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            titleFont.setCharSet(Font.DEFAULT_CHARSET);
            titleFont.setColor(IndexedColors.BLUE_GREY.index);
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
            titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            titleStyle.setFont(titleFont);
            titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);

            // 内容
            // 合并单元格供标题使用(表名)
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            HSSFRow firstRow = sheet.createRow(0);// 第一行（从0开始）
            HSSFCell firstCell = firstRow.createCell(0);
            firstCell.setCellValue("医保信息统计");
            firstCell.setCellStyle(titleStyle);
            // 填充表头header
            HSSFRow row = sheet.createRow(2);// 第3行
            String[] colName = { "透析号", "姓名", "费用类型" };
            for (int i = 0; i < colName.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(colName[i]);
                cell.setCellStyle(headStyle);
                sheet.setColumnWidth(i, 6000);
            }
            // 从第三行开始
            if (CollectionUtils.isNotEmpty(medicalList)) {
                for (int i = 0; i < medicalList.size(); i++) {
                    Map<String, Object> map = medicalList.get(i);
                    HSSFRow row2 = sheet.createRow(i + 3);// index：第几行
                    Set<Entry<String, Object>> setEntry = map.entrySet();
                    List<Entry<String, String>> ll = new ArrayList(setEntry);
                    for (int j = 0; j < ll.size(); j++) {
                        HSSFCell cell = row2.createCell(j);// 第几列：从0开始
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(ll.get(j).getValue());
                        cell.setCellStyle(contentStyle);
                    }
                }
                sheet.addMergedRegion(new CellRangeAddress(count + 3, count + 3, 1, 2));
                String[] lastRowValues = { "总共", count + "人", "" };
                HSSFRow lastRow = sheet.createRow(count + 3);// 最后一行
                for (int i = 0; i < lastRowValues.length; i++) {
                    HSSFCell lastCell = lastRow.createCell(i);
                    lastCell.setCellValue(lastRowValues[i]);
                    lastCell.setCellStyle(contentStyle);
                }
            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (UnsupportedEncodingException e1) {
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 下载患者名单 包括name sex age
     * 
     * @throws Exception
     */
    @RequestMapping("downLoadPatientsList")
    public void downLoadPatientsList(HttpServletRequest request, HttpServletResponse response, ReportParameterPO reportParameterPO) throws Exception {
        // 查询患是否显示序列号
        String sysParam = SysParamUtil.getValueByName(CmSysParamConsts.showPatientSerialNum);
        String hisIdParam = SysParamUtil.getValueByName(CmSysParamConsts.SHOW_HID_Id);
        // 患者编号显示方式
        List<PatientPO> patientList = patientService.listForDownloadList(sysParam, reportParameterPO.getIsTemp());
        List<Map<String, Object>> dataList = Lists.newArrayList();
        // 如果存在多个His卡号则显示newFlag标志的
        if (CollectionUtils.isNotEmpty(patientList)) {
            Set<Long> patientIds = new HashSet<>();
            patientList.forEach(p -> {
                patientIds.add(p.getId());
            });
            Map<Long, PatientCardPO> cardMap = patientCardService.listNewHisIdByPatientIds(patientIds);
            Map<String, String> dictSexMap = DictUtil.getMapByPItemCode(CmDictConsts.SEX);
            patientList.forEach(p -> {
                PatientCardPO card = cardMap.get(p.getId());
                if (card != null) {
                    p.setCardNo(card.getCardNo());
                    p.setCardType(card.getCardType());
                }
                // set patient id display
                Map<String, Object> map = new HashMap<String, Object>();
                if (CmSysParamConsts.showPatientHisId.equals(hisIdParam)) {
                    if (CmSysParamConsts.showPatientCradType.equals(p.getCardType())) {
                        map.put("fkPatientSerialNum", p.getCardNo());
                    } else {
                        map.put("fkPatientSerialNum", "");
                    }
                } else if ((CmSysParamConsts.showPatientSerial).equals(sysParam)) {
                    map.put("fkPatientSerialNum", p.getSerialNum());
                } else if (CmSysParamConsts.showPatientId.equals(sysParam)) {
                    map.put("fkPatientSerialNum", p.getId());
                } else {
                    map.put("fkPatientSerialNum", "");
                }

                // 设置性别
                map.put("sex", dictSexMap.get(p.getSex()));
                map.put("name", p.getName());
                if (p.getIsTemp() != null) {
                    map.put("type", p.getIsTemp() ? "临时" : "长期");
                }
                dataList.add(map);
            });
        }

        List<String[]> columNames = new ArrayList<String[]>();
        columNames.add(new String[] { "患者编号", "姓名", "性别", "类型" });

        List<String[]> fieldNames = new ArrayList<String[]>();
        fieldNames.add(new String[] { "fkPatientSerialNum", "name", "sex", "type" });

        LinkedHashMap<String, List<?>> lhMap = new LinkedHashMap<String, List<?>>();
        lhMap.put("患者名单列表", dataList);

        ExcelExportData setInfo = new ExcelExportData();

        setInfo.setDataMap(lhMap);
        setInfo.setFieldNames(fieldNames);
        setInfo.setColumnNames(columNames);
        // 将需要导出的数据输出到文件
        setInfo.setTitles(new String[] { "患者名单列表" });
        String currentTime = ExcelUtil.getCurrentTime();
        ExcelUtil.export2Stream(request, response, setInfo, "患者名单列表_" + currentTime + ".xls");
    }
}
