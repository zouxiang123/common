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
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.util.DictUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.lang.StringUtil;

public class FourAssayHand extends AssayHandFactory {

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        // 根据项目编码过滤查询
        List<DictDto> itemCodeList = DictUtil.listByPItemCode(AssayConsts.WHERE_IN_ITEM_CODE_LIST);
        String sjStr = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_GJZ_SJ);// 关键字：设置为24小时
        String groupName = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_GROUP_NAME);// 化验单名称
        List<PatientAssayRecordBusi> newPatientAssayRecordBusi = new ArrayList<>();
        List<String> itemCode = new ArrayList<String>();
        for (DictDto dictDto : itemCodeList) {
            itemCode.add(dictDto.getItemCode());
        }
        List<PatientAssayRecord> listPatientAssayRecord = patientAssayRecordService.listByItemCode(startCreateTime, endCreateTime, itemCode,
                        groupName);
        if (listPatientAssayRecord.size() > 0) {
            // 记录上一条数据患者id
            Long oldFkPatientId = 0L;
            // 记录上一条报告时间
            Date oldReportTime = null;
            // 记录上一条申请id
            String oldReqId = null;
            // 记录上一条化验项目
            String oldItemCode = null;
            // 记录上一条化验数值
            Double oldResultActual = null;

            long time1;
            long time2;
            long sj = StringUtil.isEmpty(sjStr) ? 0L : Long.valueOf(sjStr);
            PatientAssayRecordBusi patientAssayRecordBusi;
            for (PatientAssayRecord patientAssayRecord : listPatientAssayRecord) {
                if (patientAssayRecord.getResultActual() == null) {
                    continue;
                }
                if (oldFkPatientId.equals(patientAssayRecord.getFkPatientId())) {

                    if (oldReportTime != null && patientAssayRecord.getReportTime() != null) {
                        time1 = oldReportTime.getTime();
                        time2 = patientAssayRecord.getSampleTime().getTime();
                        long dd = (time1 - time2) / (1000 * 3600); // 共计小时
                        if (dd <= sj && StringUtil.equals(oldItemCode, patientAssayRecord.getItemCode())
                                        && oldResultActual > patientAssayRecord.getResultActual()) {
                            patientAssayRecordBusi = new PatientAssayRecordBusi();
                            BeanUtil.copyProperties(patientAssayRecord, patientAssayRecordBusi);
                            patientAssayRecordBusi.setDiaAbFlag(AssayConsts.AFTER_HD);
                            newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                            patientAssayRecordBusi = null;
                        }
                    }
                }
                oldFkPatientId = patientAssayRecord.getFkPatientId();
                oldReportTime = patientAssayRecord.getReportTime();
                oldReqId = patientAssayRecord.getReqId();
                oldItemCode = patientAssayRecord.getItemCode();
                oldResultActual = patientAssayRecord.getResultActual();
            }
        }
        if (CollectionUtils.isNotEmpty(newPatientAssayRecordBusi)) {
            this.updateListPatientAssayRecordBusi(newPatientAssayRecordBusi);
        }

    }

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record) {
        return AssayConsts.BEFORE_HD;
    }
}
