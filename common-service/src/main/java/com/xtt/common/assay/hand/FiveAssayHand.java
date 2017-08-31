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
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.util.BusinessDateUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;

public class FiveAssayHand extends AssayHandFactory {

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        Date nowDate = new Date();
        String beforeCount = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_BEFORE_COUNT);
        String afterCount = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_AFTER_COUNT);
        String groupName = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_GROUP_NAME);
        // 根据项目编码过滤查询
        List<DictDto> itemCodeList = DictUtil.listByPItemCode(AssayConsts.WHERE_IN_ITEM_CODE_LIST);
        StringBuffer strItemCode = new StringBuffer();
        Date startCreateDate;
        Date endCreateDate;
        int i = 1;
        Integer tenantId = UserUtil.getTenantId();
        boolean isUpdate;
        PatientAssayRecordBusi patientAssayRecordBusi = new PatientAssayRecordBusi();
        List<PatientAssayRecordBusi> updateRecordList = new ArrayList<>();
        // 评级项目名称
        for (DictDto dictDto : itemCodeList) {
            strItemCode.append(" , max(case when item_code = '").append(dictDto.getItemCode()).append("' then result end ) itemCode" + i);
            i++;
        }
        // 对需要清洗数据的患者循环
        for (Long patientId : map.keySet()) {
            List<Date> listDate = map.get(patientId);
            // 患者中存在多个时间只取最后一个
            startCreateDate = listDate.get(listDate.size() - 1);
            startCreateDate = BusinessDateUtil.getDayStartOrEnd(startCreateDate, true);
            endCreateDate = BusinessDateUtil.getDayStartOrEnd(nowDate, false);
            // 查询时间区域中透后的数据筛选条件有“患者id”，“透析后数据有多少条”，“项目名称”，“时间段”
            List<PatientAssayRecordPO> listAfterPatientAssayRecord = patientAssayRecordService.listByAfterCount(afterCount, startCreateDate,
                            endCreateDate, groupName, patientId, tenantId, strItemCode.toString());
            // 查询到投后项目循环查找透前
            for (PatientAssayRecordPO afterPatientAssayRecord : listAfterPatientAssayRecord) {
                endCreateDate = afterPatientAssayRecord.getSampleTime();
                // 更加投后的化验时间查找最近的一条透前
                PatientAssayRecordPO beforePatientAssayRecord = patientAssayRecordService.getByBeforeCount(beforeCount, startCreateDate,
                                endCreateDate, strItemCode.toString(), patientId, tenantId, groupName);
                // 如果查询到透前不为空判断项目是否透前大于透后
                if (beforePatientAssayRecord != null) {
                    isUpdate = true;
                    for (int j = 0; j < itemCodeList.size() - 1; j++) {
                        if (isUpdate == false) {
                            continue;
                        }
                        isUpdate = Double.valueOf(beforePatientAssayRecord.getItemCode1()) > Double.valueOf(afterPatientAssayRecord.getItemCode1());
                    }
                    // 当条件都满足时候更新透前透后标识
                    if (isUpdate) {
                        patientAssayRecordBusi = new PatientAssayRecordBusi();
                        patientAssayRecordBusi.setFkPatientId(afterPatientAssayRecord.getFkPatientId());
                        patientAssayRecordBusi.setReqId(afterPatientAssayRecord.getReqId());
                        patientAssayRecordBusi.setSampleTime(afterPatientAssayRecord.getSampleTime());
                        patientAssayRecordBusi.setDiaAbFlag(AssayConsts.AFTER_HD);
                        updateRecordList.add(patientAssayRecordBusi);
                    }
                }
            }
            // 更新透前透后标识符
            if (CollectionUtils.isNotEmpty(updateRecordList)) {
                this.updateDiaAbFlagByReqId(updateRecordList);
            }
        }
    }

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record) {
        return AssayConsts.BEFORE_HD;
    }

}