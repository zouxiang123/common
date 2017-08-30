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

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IPatientAssayBackInspectioidService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.PatientAssayBackInspectioid;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class OnePatientAssayRecordBusiFactory extends AssayHandFactory {

    private static final Logger log = LoggerFactory.getLogger(OnePatientAssayRecordBusiFactory.class);

    @Autowired
    private IPatientAssayRecordService patientAssayRecordService;
    @Autowired
    private IPatientAssayRecordBusiService PatientAssayRecordBusiService;
    @Autowired
    private IPatientAssayBackInspectioidService patientAssayBackInspectioidService;

    @Override
    public void save(Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        Date nowDate = new Date();
        if (startCreateTime == null && endCreateTime == null) {
            // 当天开始时间
            startCreateTime = DateFormatUtil.getStartTime(nowDate);
            // 当天结束时间
            endCreateTime = DateFormatUtil.getEndTime(nowDate);
        }
        // 创建对象存储需要插入的数据
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(1008);
        PatientAssayRecordBusi patientAssayRecordBusi;
        List<PatientAssayRecordPO> listPatientAssayRecord = patientAssayRecordService.listByCreateTime(startCreateTime, endCreateTime, fkPatientId);
        List<PatientAssayBackInspectioid> insertPatientAssayBackInspectioidList = new ArrayList<>();
        if (log.isDebugEnabled()) {
            log.debug("查询病人化验检查结果表成功总共查询到" + listPatientAssayRecord.size());
        }
        if (listPatientAssayRecord.size() == 0) {
            return;
        }
        // 获取主键表数据
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayRecordBusi.class.getSimpleName(), UserUtil.getTenantId(), listPatientAssayRecord.size());
        int i = 1;
        PatientAssayBackInspectioid patientAssayBackInspectioid;
        for (PatientAssayRecordPO patientAssayRecord : listPatientAssayRecord) {
            // 维护字典表
            this.insertAssayHospDict(patientAssayRecord);
            // 检查项目唯一ID为空时候不插入
            if (patientAssayRecord.getInspectionId() == null) {
                continue;
            }
            int countByInspectionId = PatientAssayRecordBusiService.countByInspectionId(patientAssayRecord.getInspectionId());
            if (countByInspectionId != 0) {
                continue;
            } else {
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
                // 1：根据group_name判断
                // 申请单名
                listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getGroupName()));
                patientAssayBackInspectioid = new PatientAssayBackInspectioid();
                patientAssayBackInspectioid.setReqId(patientAssayRecordBusi.getReqId());
                patientAssayBackInspectioid.setSampleTime(patientAssayRecordBusi.getSampleTime());
                patientAssayBackInspectioid.setFkPatientId(patientAssayRecordBusi.getFkPatientId());
                patientAssayBackInspectioid.setFkTenantId(UserUtil.getTenantId());
                patientAssayBackInspectioid.setDiaAbFlag(AssayConsts.LAB_AFTER);
                patientAssayBackInspectioid.setCreateTime(nowDate);
                patientAssayBackInspectioid.setUpdateTime(nowDate);
                patientAssayBackInspectioid.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                patientAssayBackInspectioid.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                insertPatientAssayBackInspectioidList.add(patientAssayBackInspectioid);
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
        // 备份透后数据到patient_assay_back_inspectioid
        if (CollectionUtils.isNotEmpty(insertPatientAssayBackInspectioidList)) {
            patientAssayBackInspectioidService.insertList(insertPatientAssayBackInspectioidList);
        }
        listPatientAssayRecord = null;
    }

}
