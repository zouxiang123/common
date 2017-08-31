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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class AssayHandThree extends AssayHandFactory {

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, Long patientId) {
        // 根据项目编码过滤查询
        AssayFilterRule assayFilterRule = assayFilterRuleService.getAssayFilterRuleByTenantId(UserUtil.getTenantId());
        // 以","分割
        String strItemCode = assayFilterRule.getItemCode();
        if (strItemCode == null) {
            LOGGER.error("assay_filter_rule表中item_code字段为空");
            return;
        }
        String[] itemCodes = strItemCode.split(",");
        String groupName = assayFilterRule.getGroupName();// 化验单名称
        List<PatientAssayRecordBusi> newPatientAssayRecordBusi = new ArrayList<>();
        List<String> listItemCode = new ArrayList<String>();
        Collections.addAll(listItemCode, itemCodes);
        List<PatientAssayRecord> listPatientAssayRecord = patientAssayRecordService.listByItemCode(startCreateTime, endCreateTime, listItemCode,
                        groupName);
        if (listPatientAssayRecord.size() > 0) {
            // 记录上一条数据患者id
            Long oldFkPatientId = 0L;
            // 记录上一条报告时间
            Date oldReportTime = null;
            // 记录上一条申请id
            String oldReqId = null;
            PatientAssayRecordBusi patientAssayRecordBusi;
            for (PatientAssayRecord patientAssayRecord : listPatientAssayRecord) {
                if (patientAssayRecord.getResultActual() == null) {
                    continue;
                }

                // 当化验单重复出现时候不处理
                if (StringUtil.isNotEmpty(oldReqId) && StringUtil.equals(oldReqId, patientAssayRecord.getReqId())
                                && oldReportTime.compareTo(patientAssayRecord.getReportTime()) == 0
                                && StringUtil.equals(oldFkPatientId.toString(), patientAssayRecord.getFkPatientId().toString())) {
                    continue;
                }
                Date endTime = patientAssayRecord.getReportTime();
                Date startTime = DateFormatUtil.getStartTime(patientAssayRecord.getReportTime());
                String itemCode = patientAssayRecord.getItemCode();
                Long fkPatientId = patientAssayRecord.getFkPatientId();
                Double resultActual = patientAssayRecord.getResultActual();
                Integer count = patientAssayRecordService.countByPatientId(startTime, endTime, itemCode, fkPatientId, resultActual);
                // 获取当天这个患者化验项相同的数据

                // 如果当天存在一条比当前化验单数值小时 当前化验单标记为透后并跳出当前循环
                if (count > 0) {
                    patientAssayRecordBusi = new PatientAssayRecordBusi();
                    patientAssayRecordBusi.setFkPatientId(patientAssayRecord.getFkPatientId());
                    patientAssayRecordBusi.setReqId(patientAssayRecord.getReqId());
                    patientAssayRecordBusi.setReportTime(patientAssayRecord.getReportTime());
                    patientAssayRecordBusi.setDiaAbFlag(AssayConsts.AFTER_HD);
                    newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                    patientAssayRecordBusi = null;
                    oldFkPatientId = patientAssayRecord.getFkPatientId();
                    oldReportTime = patientAssayRecord.getReportTime();
                    oldReqId = patientAssayRecord.getReqId();
                    break;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(newPatientAssayRecordBusi)) {
            this.updateDiaAbFlagByReqId(newPatientAssayRecordBusi);
        }
    }

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record, String labBefore, String labAfter) {
        return AssayConsts.BEFORE_HD;
    }
}
