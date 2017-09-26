/**   
 * @Title: PatientReportServiceImpl.java 
 * @Package com.xtt.common.report.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年9月20日 上午10:18:30 
 *
 */
package com.xtt.common.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.mapper.PatientReportMapper;
import com.xtt.common.dto.DictDto;
import com.xtt.common.report.service.IPatientReportService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientReportServiceImpl implements IPatientReportService {
    @Autowired
    private PatientReportMapper patientReportMapper;

    @Override
    public Map<String, List<Map<String, Object>>> listReportData(Map<String, Object> map, Integer reportType) {

        map.put("tenantId", UserUtil.getTenantId());

        // ageRangeList(年龄段统计),sexList(性别统计),
        Map<String, List<Map<String, Object>>> retMap = new HashMap<String, List<Map<String, Object>>>();

        String sql = "";
        if (reportType == 1) {
            int ageRange = Integer.parseInt(map.get("ageRange").toString());
            int to = ageRange;
            if (ageRange < 100) {
                for (int from = 0; to < 100; from = to + 1, to += ageRange) {
                    sql += "    when age >=" + from + " and age <=" + to + " then '" + from + "-" + to + "'";
                }
                sql += "    else '" + (to - ageRange) + "以上'";
            } else {// 年龄段大于等于100按：0到年龄段统计
                sql += "   when age >= 0 and age <=" + ageRange + " then '0-" + ageRange + "' else '" + ageRange + "以上' ";
            }
            map.put("whenAgeRange", sql);
            List<Map<String, Object>> ageRangeList = patientReportMapper.listAgeRange(map);
            retMap.put("ageRangeList", ageRangeList);
        }

        if (reportType == 3) {
            List<Map<String, Object>> sexList = patientReportMapper.listSex(map);
            retMap.put("sexList", sexList);
        }

        if (reportType == 4) {
            Map<String, String> chargeMap = new HashMap<>();
            List<DictDto> chargeTypeList = DictUtil.listByPItemCode(CmDictConsts.PATIENT_CHARGE_TYPE);
            for (DictDto po : chargeTypeList) {
                chargeMap.put(po.getItemCode(), po.getItemName());
            }
            List<Map<String, Object>> medicalList = patientReportMapper.listMedical(map);
            for (Map<String, Object> medicalMap : medicalList) {
                medicalMap.put("chargeName", chargeMap.get(medicalMap.get("chargeType")) == null ? "" : chargeMap.get(medicalMap.get("chargeType")));

            }
            retMap.put("medicalList", medicalList);
        }

        if (reportType == 1) {
            // 平均年龄
            List<Map<String, Object>> avgMap = patientReportMapper.listAvgAge(UserUtil.getTenantId(), map.get("isTemp"));
            retMap.put("avgMap", avgMap);
        }
        return retMap;
    }

}
