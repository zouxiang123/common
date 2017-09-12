/**   
 * @Title: OnePatientAssayRecordBusiFactory.java 
 * @Package com.xtt.common.assay.hand
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月7日 上午10:05:13 
 *
 */
package com.xtt.common.assay.hand;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.BusinessDateUtil;
import com.xtt.common.util.UserUtil;

public class AssayHandFour extends AssayHandFactory {

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        Date nowDate = new Date();
        AssayFilterRule assayFilterRule = assayFilterRuleService.getByTenantId(UserUtil.getTenantId());
        Integer beforeCount = assayFilterRule.getItemCountBefore();
        Integer afterCount = assayFilterRule.getItemCountAfter();
        String groupName = assayFilterRule.getGroupName();
        String strItemCodes = assayFilterRule.getItemCode();
        String[] itemCodes = strItemCodes.split(",");
        // 根据项目编码过滤查询
        StringBuffer strItemCode = new StringBuffer();
        Date startCreateDate;
        Date endCreateDate;
        Integer tenantId = UserUtil.getTenantId();
        boolean isUpdate;
        PatientAssayRecordBusi patientAssayRecordBusi = new PatientAssayRecordBusi();
        List<PatientAssayRecordBusi> updateRecordList = new ArrayList<>();
        // 评级项目名称
        for (int i = 0; i < itemCodes.length; i++) {
            strItemCode.append(" , max(case when ahd.fk_dict_code = '").append(itemCodes[i])
                            .append("' then parb.result_actual end ) resultActual" + i);
        }
        // 对需要清洗数据的患者循环
        for (Long patientId : map.keySet()) {
            List<Date> listDate = map.get(patientId);
            // 患者中存在多个时间只取最后一个
            startCreateDate = listDate.get(listDate.size() - 1);
            startCreateDate = BusinessDateUtil.getDayStartOrEnd(startCreateDate, true);
            endCreateDate = BusinessDateUtil.getDayStartOrEnd(nowDate, false);
            // 查询时间区域中透后的数据筛选条件有“患者id”，“透析后数据有多少条”，“项目名称”，“时间段”
            List<Map<String, Object>> listAfterMap = patientAssayRecordBusiService.listByAfterCount(afterCount, startCreateDate, endCreateDate,
                            groupName, patientId, tenantId, strItemCode.toString());
            // 查询到投后项目循环查找透前
            for (Map<String, Object> recordMap : listAfterMap) {
                endCreateDate = (Date) recordMap.get("sampleTime");
                // 更加投后的化验时间查找最近的一条透前
                Map<String, Object> beforeRecordMap = patientAssayRecordBusiService.getByBeforeCount(beforeCount, startCreateDate, endCreateDate,
                                strItemCode.toString(), patientId, tenantId, groupName);
                // 如果查询到透前不为空判断项目是否透前大于透后
                if (beforeRecordMap != null) {
                    isUpdate = false;
                    for (int i = 0; i < itemCodes.length; i++) {
                        String key = "resultActual" + i;
                        if (beforeRecordMap.get(key) != null && recordMap.get(key) != null) {// 透前需大于透后
                            isUpdate = ((Double) beforeRecordMap.get(key)).compareTo((Double) recordMap.get(key)) > 0;
                        } else {
                            isUpdate = false;
                        }
                        if (!isUpdate) {
                            break;
                        }
                    }
                    // 当条件都满足时候更新透前透后标识
                    if (isUpdate) {
                        patientAssayRecordBusi = new PatientAssayRecordBusi();
                        patientAssayRecordBusi.setFkPatientId((Long) recordMap.get("fkPatientId"));
                        patientAssayRecordBusi.setReqId((String) recordMap.get("reqId"));
                        patientAssayRecordBusi.setSampleTime((Date) recordMap.get("sampleTime"));
                        patientAssayRecordBusi.setFkTenantId(UserUtil.getTenantId());
                        patientAssayRecordBusi.setDiaAbFlag(AssayConsts.AFTER_HD);
                        updateRecordList.add(patientAssayRecordBusi);
                    }
                }
            }
        }
        // 更新透前透后标识符
        if (CollectionUtils.isNotEmpty(updateRecordList)) {
            patientAssayRecordBusiService.updateDiaAbFlagByReqId(updateRecordList);
        }
    }

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record, String labBefore, String labAfter) {
        return AssayConsts.BEFORE_HD;
    }

}