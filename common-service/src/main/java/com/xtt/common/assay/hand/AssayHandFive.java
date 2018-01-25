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
import com.xtt.common.constants.AssayEnum;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

public class AssayHandFive extends AssayHandFactory {

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        List<PatientAssayRecordBusi> updateRecordList = new ArrayList<>();
        for (Long key : map.keySet()) {
            List<Date> dateList = map.get(key);
            PatientAssayRecordBusiPO patientAssayRecord = new PatientAssayRecordBusiPO();
            patientAssayRecord.setFkPatientId(key);
            patientAssayRecord.setFkDictCode(AssayEnum.BUN.getValue());
            patientAssayRecord.setStartDate(dateList.get(0));
            patientAssayRecord.setEndDate(dateList.get(1));
            List<PatientAssayRecordBusiPO> patientAssayRecordList = patientAssayRecordBusiService.listByItmeCode(patientAssayRecord);
            if (patientAssayRecordList != null && patientAssayRecordList.size() == 2) {
                PatientAssayRecordBusiPO startAssayRecord = patientAssayRecordList.get(0);
                PatientAssayRecordBusiPO endAssayRecord = patientAssayRecordList.get(1);
                // 第一条大于第二条时候 标记第二条化验的为透后
                if (startAssayRecord.getResultActual() > endAssayRecord.getResultActual()) {
                    endAssayRecord.setDiaAbFlag(AssayConsts.AFTER_HD);
                    endAssayRecord.setFkPatientId(key);
                    endAssayRecord.setFkTenantId(UserUtil.getTenantId());
                    DataUtil.setUpdateSystemFieldValue(endAssayRecord);
                    updateRecordList.add(endAssayRecord);
                }
                // 第一条小于第二条时候标记第一条化验的为透后
                if (startAssayRecord.getResultActual() < endAssayRecord.getResultActual()) {
                    startAssayRecord.setDiaAbFlag(AssayConsts.AFTER_HD);
                    startAssayRecord.setFkPatientId(key);
                    startAssayRecord.setFkTenantId(UserUtil.getTenantId());
                    DataUtil.setUpdateSystemFieldValue(startAssayRecord);
                    updateRecordList.add(startAssayRecord);
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