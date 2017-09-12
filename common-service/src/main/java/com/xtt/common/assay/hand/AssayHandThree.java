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
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateUtil;

public class AssayHandThree extends AssayHandFactory {

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, Long patientId) {
        // 根据项目编码过滤查询
        AssayFilterRule assayFilterRule = assayFilterRuleService.getByTenantId(UserUtil.getTenantId());
        // 以","分割
        String strItemCode = assayFilterRule.getItemCode();
        if (strItemCode == null) {
            LOGGER.error("assay_filter_rule表中item_code字段为空");
            return;
        }
        List<PatientAssayRecordBusi> newPatientAssayRecordBusi = new ArrayList<>();
        PatientAssayRecordBusiPO query = new PatientAssayRecordBusiPO();
        query.setQueryOrderBy(3);
        query.setFkDictCodes(Arrays.asList(strItemCode.split(",")));
        query.setFkTenantId(UserUtil.getTenantId());
        List<PatientAssayRecordBusiPO> assayList = patientAssayRecordBusiService.listByCondition(query);
        if (CollectionUtils.isNotEmpty(assayList)) {
            // 记录上一条记录的itemCode
            String preCode = null;
            // 记录上一条记录的ResultActual
            Double preResult = null;
            // 上一次申请单id
            String preReqId = null;
            // 上一次样品时间
            Date preSimpleTime = null;
            // 化验单集合
            Set<String> reqIdSet = new HashSet<>();
            PatientAssayRecordBusi updateReq;// 需要更新的化验单
            for (PatientAssayRecordBusiPO assayRecord : assayList) {
                if (assayRecord.getResultActual() == null) {
                    continue;
                }
                // 当化验单重复出现时候不处理
                if (reqIdSet.contains(assayRecord.getReqId())) {
                    continue;
                }
                // 本次和上次属于不同的记录单、同一个项目（如"尿素氮"）、样品时间是同一天
                if (!Objects.equals(assayRecord.getReqId(), preReqId) && Objects.equals(assayRecord.getFkDictCode(), preCode)
                                && (preSimpleTime != null && DateUtil.isSameDay(assayRecord.getSampleTime(), preSimpleTime))) {
                    if (Objects.equals(assayRecord.getGroupName(), assayFilterRule.getGroupName())) {// 当前项目所属组是透后组
                        if (preResult.compareTo(assayRecord.getResultActual()) > 0) {// 透前数据大于透后数据
                            updateReq = new PatientAssayRecordBusi();
                            updateReq.setFkPatientId(assayRecord.getFkPatientId());
                            updateReq.setReqId(assayRecord.getReqId());
                            updateReq.setSampleTime(assayRecord.getSampleTime());
                            updateReq.setFkTenantId(UserUtil.getTenantId());
                            updateReq.setDiaAbFlag(AssayConsts.AFTER_HD);
                            newPatientAssayRecordBusi.add(updateReq);
                            reqIdSet.add(assayRecord.getReqId());
                        }
                    }
                }
                preResult = assayRecord.getResultActual();
                preCode = assayRecord.getFkDictCode();
                preReqId = assayRecord.getReqId();
                preSimpleTime = assayRecord.getSampleTime();
            }
        }
        if (CollectionUtils.isNotEmpty(newPatientAssayRecordBusi)) {
            patientAssayRecordBusiService.updateDiaAbFlagByReqId(newPatientAssayRecordBusi);
        }
    }

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record, String labBefore, String labAfter) {
        return AssayConsts.BEFORE_HD;
    }
}
