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

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class FourPatientAssayRecordBusiFactory extends AbstractPatientAssayRecordBusi {

    private static final Logger log = LoggerFactory.getLogger(FourPatientAssayRecordBusiFactory.class);

    @Autowired
    private IPatientAssayRecordService patientAssayRecordService;

    @Autowired
    private IPatientAssayRecordBusiService PatientAssayRecordBusiService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void save(Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        Date nowDate = new Date();
        if (startCreateTime == null && endCreateTime == null) {
            startCreateTime = DateFormatUtil.getStartTime(nowDate);
            endCreateTime = DateFormatUtil.getEndTime(nowDate);
        }
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(1008);
        PatientAssayRecordBusi patientAssayRecordBusi;
        List<PatientAssayRecordPO> listPatientAssayRecord = patientAssayRecordService.listByCreateTime(startCreateTime, endCreateTime, fkPatientId);
        if (log.isDebugEnabled()) {
            log.debug("查询病人化验检查结果表成功总共查询到" + listPatientAssayRecord.size());
        }
        if (listPatientAssayRecord.size() == 0) {
            return;
        }
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayRecordBusi.class.getSimpleName(), UserUtil.getTenantId(), listPatientAssayRecord.size());
        int i = 1;
        for (PatientAssayRecordPO patientAssayRecord : listPatientAssayRecord) {
            // 检查项目唯一ID为空时候不插入
            if (patientAssayRecord.getInspectionId() == null) {
                continue;
            }
            // 根据项目唯一ID判断是否已经插入
            int countByInspectionId = PatientAssayRecordBusiService.countByInspectionId(patientAssayRecord.getInspectionId());
            if (countByInspectionId != 0) {
                continue;
            } else {
                // 化验没数值删除
                if (patientAssayRecord.getResult() == null) {
                    continue;
                }
                patientAssayRecordBusi = new PatientAssayRecordBusi();
                BeanUtil.copyProperties(patientAssayRecord, patientAssayRecordBusi);
                patientAssayRecordBusi.setCreateTime(nowDate);
                patientAssayRecordBusi.setUpdateTime(nowDate);
                patientAssayRecordBusi.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                patientAssayRecordBusi.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                patientAssayRecordBusi.setAssayMonth(patientAssayRecord.getAssayMonth());
                patientAssayRecordBusi.setAssayDate(getAssyaDate(patientAssayRecord.getAssayDate()));
                try {
                    String result = patientAssayRecordBusi.getResult();
                    Double matcherToNum = matcherToNum(result);
                    patientAssayRecordBusi.setResultActual(matcherToNum);
                } catch (Exception e) {
                    log.error("result exception:", e);
                }
                patientAssayRecordBusi.setId(id++);
                // 4：根据sample_time判断
                patientAssayRecordBusi.setDiaAbFlag(AssayConsts.NOT_AFTER_BEFORE);
                listPatientAssayRecordBusi.add(patientAssayRecordBusi);
                i++;
                if (i == 1000) {
                    PatientAssayRecordBusiService.insertList(listPatientAssayRecordBusi);
                    listPatientAssayRecordBusi.clear();
                    i = 1;
                }
                patientAssayRecordBusi = null;
            }
        }
        if (listPatientAssayRecordBusi.size() != 0) {
            PatientAssayRecordBusiService.insertList(listPatientAssayRecordBusi);
        }
        listPatientAssayRecord = null;
    }

    /**
     * 对sampleTime字段判断透析前还是透析后
     * 
     * @Title: sampleTime
     * @param startCreateTime
     * @param endCreateTime
     *
     */
    public void sampleTime(Date startCreateTime, Date endCreateTime) {
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
                            patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_AFTER);
                            newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                            patientAssayRecordBusi = null;
                            patientAssayRecordBusi = new PatientAssayRecordBusi();
                            patientAssayRecordBusi.setFkPatientId(oldFkPatientId);
                            patientAssayRecordBusi.setReqId(oldReqId);
                            patientAssayRecordBusi.setReportTime(oldReportTime);
                            patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_BEFORE);
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
}
