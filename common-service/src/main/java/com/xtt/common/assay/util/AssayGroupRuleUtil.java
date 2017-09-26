/**   
 * @Title: AssayGroupRuleUtil.java 
 * @Package com.xtt.txgl.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月17日 下午8:24:04 
 *
 */
package com.xtt.common.assay.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.po.ReportPatientAssayRecordPO;
import com.xtt.common.util.DictUtil;

public class AssayGroupRuleUtil {
    public static final int RULE_GT = 1;
    public static final int RULE_ET = 2;
    public static final int RULE_LT = 3;
    public static final int RULE_GTET = 4;
    public static final int RULE_LTET = 5;
    public static final int RULE_RANGE = 6;

    /**
     * 根据分组规则，将化验记录分组
     * 
     * @Title: countGroupList
     * @param ruleList
     * @param recordList
     * @return
     * 
     */
    public static Map<String, Integer> countGroupList(List<Double> ruleList, List<ReportPatientAssayRecordPO> recordList) {
        Map<String, Integer> ruleMap = new LinkedHashMap<String, Integer>();
        if (CollectionUtils.isNotEmpty(ruleList)) {
            for (int i = 0; i < ruleList.size(); i++) {
                Double rule = ruleList.get(i);
                String key = null;
                if (i == 0) {
                    key = "min_" + rule;
                    ruleMap.put(key, countByRule(i, null, rule, recordList));
                } else {
                    Double minValue = ruleList.get(i - 1);
                    key = minValue + "_" + rule;
                    ruleMap.put(key, countByRule(i, minValue, rule, recordList));
                }

                if (i == ruleList.size() - 1) {
                    key = "max_" + rule;
                    ruleMap.put(key, countByRule(i + 1, rule, null, recordList));
                }
            }
        }
        return ruleMap;
    }

    /**
     * 获取匹配当前规则的数量
     * 
     * @Title: countByRule
     * @param key
     * @param rule
     * @param recordList
     * @return
     * 
     */
    private static Integer countByRule(int index, Double minValue, Double maxValue, List<ReportPatientAssayRecordPO> recordList) {
        int count = 0;
        for (int i = 0; i < recordList.size(); i++) {
            ReportPatientAssayRecordPO record = recordList.get(i);

            boolean flag = true;

            // 如果小于最小值，则不属于该区间
            if (minValue != null && minValue.compareTo(Double.parseDouble(record.getResult() == null ? "0" : record.getResult())) == 1) {
                flag = false;
            }

            // 如果大于等于最大值，则不属于该区间
            if (maxValue != null && maxValue.compareTo(Double.parseDouble(record.getResult() == null ? "0" : record.getResult())) < 1) {
                flag = false;
            }

            if (flag) {
                count++;
                record.setGroupIndex(index);
            }
        }
        return count;
    }

    public static Map<String, Object> getPieMap(List<Double> ruleList, List<ReportPatientAssayRecordPO> recordList) {
        Map<String, Integer> map = countGroupList(ruleList, recordList);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String[] titles = new String[map.size()];

        Set<String> set = map.keySet();
        Iterator<String> iter = set.iterator();
        int i = 0;
        Map<String, Object> pieDataMap = null;
        List<Map<String, Object>> pieData = new ArrayList<Map<String, Object>>();
        while (iter.hasNext()) {
            String key = iter.next();
            String[] keyArr = key.split("_");
            String minValue = keyArr[0];
            String maxValue = keyArr[1];

            if (key.indexOf("min") == 0) {
                titles[i] = DictUtil.getItemName(CmDictConsts.GROUP_RULE, RULE_LT + "") + " " + maxValue;
            } else if (key.indexOf("max") == 0) {
                titles[i] = DictUtil.getItemName(CmDictConsts.GROUP_RULE, RULE_GTET + "") + " " + maxValue;
            } else {
                titles[i] = minValue + " " + DictUtil.getItemName(CmDictConsts.GROUP_RULE, RULE_RANGE + "") + " " + maxValue;
            }

            pieDataMap = new HashMap<String, Object>();
            pieDataMap.put("name", titles[i]);
            pieDataMap.put("value", map.get(key));
            pieData.add(pieDataMap);

            i++;
        }

        resultMap.put("title", titles);
        resultMap.put("data", pieData);
        return resultMap;
    }
}
