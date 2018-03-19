/**   
 * @Title: ReportPatientOutcomeServiceImpl.java 
 * @Package com.xtt.txgl.report.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年3月28日 下午6:57:43 
 *
 */
package com.xtt.common.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.PatientOutcomeReportMapper;
import com.xtt.common.dao.po.PatientOutcomeReport;
import com.xtt.common.dao.po.ReportParameterPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.report.service.IPatientOutcomeReportService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class PatientOutcomeReportServiceImpl implements IPatientOutcomeReportService {
    @Autowired
    private PatientOutcomeReportMapper patientOutcomeReportMapper;

    @Override
    public List<PatientOutcomeReport> listByCondition(PatientOutcomeReport record) {
        record.setFkTenantId(UserUtil.getTenantId());
        record.setSysOwner(UserUtil.getSysOwner());
        List<PatientOutcomeReport> list = patientOutcomeReportMapper.listByCondition(record);
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, String> typeMap = DictUtil.getMapByPItemCode(CmDictConsts.PATIENT_OUTCOME_TYPE);
            list.forEach(r -> {
                r.setTypeShow(typeMap.get(r.getType()));
            });
        }
        return list;
    }

    @Override
    public Map<String, Object> getReportData(ReportParameterPO reportParameterPO) {

        String startYear = reportParameterPO.getYear();
        String endYear = reportParameterPO.getYear();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String[] colNames = new String[14];
        String[] colCodes = new String[14];
        // 初始化列名信息
        initMonthHeadInfo(startYear, colNames, colCodes, true);
        List<DictDto> typeList = DictUtil.listByPItemCode(CmDictConsts.PATIENT_OUTCOME_TYPE);
        Map<String, List<Map<String, String>>> rowsMap = new LinkedHashMap<String, List<Map<String, String>>>();
        {
            // 行数据初始化
            for (DictDto d : typeList) {
                List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();
                // 第一列数据
                rowList.add(getCellMap("typeCol", d.getItemName()));
                rowsMap.put(d.getItemCode(), rowList);
            }
            // 最后一行数据
            List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();
            rowList.add(getCellMap("typeCol", "总计"));
            rowsMap.put("total", rowList);
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("from", DateFormatUtil.getStartTime(startYear + "-01-01"));
        paraMap.put("to", DateFormatUtil.getEndTime(endYear + "-12-31"));
        paraMap.put("tenantId", UserUtil.getTenantId());
        paraMap.put("dictType", CmDictConsts.PATIENT_OUTCOME_TYPE);
        paraMap.put("isTemp", reportParameterPO.getIsTemp());
        List<DictDto> dictionaryList = DictUtil.listByPItemCode(CmDictConsts.PATIENT_OUTCOME_TYPE);
        String[] types = new String[dictionaryList.size()];
        int j = 0;
        for (DictDto dictDto : dictionaryList) {
            types[j] = dictDto.getItemCode();
            j++;
        }
        paraMap.put("types", types);
        paraMap.put("sysOwner", UserUtil.getSysOwner());
        List<Map<String, Object>> dataList = patientOutcomeReportMapper.listReportData(paraMap);
        for (Map<String, Object> map : dataList) {
            if (map.get("value") != null && rowsMap.get(map.get("value")) != null) {
                rowsMap.get(map.get("value")).add(getCellMap(String.valueOf(map.get("time")), String.valueOf(map.get("cnt"))));
            }
        }
        // 添加合计数据
        addTotalData(colCodes, rowsMap);
        resultMap.put("cols", colNames);
        resultMap.put("colCodes", colCodes);
        resultMap.put("rows", rowsMap);
        return resultMap;
    }

    /** 生成Cellmap */
    private Map<String, String> getCellMap(String colValue, int rowspan, int colspan, String itemCode) {
        Map<String, String> col = new HashMap<String, String>();
        if (itemCode != null) {
            col.put("itemCode", itemCode);
        }
        col.put("colValue", colValue);
        col.put("colspan", colspan + "");
        col.put("rowspan", rowspan + "");
        return col;
    }

    /**
     * 生成Cellmap
     * 
     * @Title: getCellMap
     * @param colValue
     * @param itemCode
     * @return
     *
     */
    private Map<String, String> getCellMap(String itemCode, String colValue) {
        return getCellMap(colValue, 1, 1, itemCode);
    }

    /**
     * 初始化月份统计的表头信息
     * 
     * @Title: initMonthHeadInfo
     * @param colCodes
     * @param colNames
     * @param hasTotal
     *
     */
    private void initMonthHeadInfo(String year, String[] colNames, String[] colCodes, boolean hasTotal) {
        for (int i = 0; i < colNames.length; i++) {
            if (i == 0) {
                colNames[i] = year;
                colCodes[i] = "typeCol";
            } else if (i == colNames.length - 1 && hasTotal) {
                colNames[i] = "合计";
                colCodes[i] = "total";
            } else {
                colNames[i] = i + "月";
                colCodes[i] = year + "-" + (i < 10 ? "0" + i : i);
            }
        }
    }

    /**
     * 添加合计数据
     * 
     * @param rowsMap
     * @param colCodes
     */
    private void addTotalData(String[] colCodes, Map<String, List<Map<String, String>>> rowsMap) {
        {// 计算汇总列
            for (Entry<String, List<Map<String, String>>> entry : rowsMap.entrySet()) {
                if ("total".equals(entry.getKey())) {
                    continue;
                }
                List<Map<String, String>> rowList = entry.getValue();
                int count = 0;
                for (int i = 1; i < rowList.size(); i++) {
                    count += Integer.valueOf(rowList.get(i).get("colValue"));
                }
                rowList.add(getCellMap("total", count + ""));

            }
        }
        {// 计算汇总行
            List<Map<String, String>> totalRowList = rowsMap.get("total");
            for (int i = 1; i < colCodes.length; i++) {
                int count = 0;
                for (Entry<String, List<Map<String, String>>> entry : rowsMap.entrySet()) {
                    if ("total".equals(entry.getKey())) {
                        continue;
                    }
                    List<Map<String, String>> rowList = entry.getValue();
                    for (Map<String, String> map : rowList) {
                        if (map.get("itemCode").equals(colCodes[i])) {
                            count += Integer.valueOf(map.get("colValue"));
                        }
                    }
                }
                totalRowList.add(getCellMap(colCodes[i], count + ""));
            }
        }
    }
}
