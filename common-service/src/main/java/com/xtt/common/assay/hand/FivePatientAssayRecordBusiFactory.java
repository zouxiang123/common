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
import com.xtt.common.dto.DictDto;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class FivePatientAssayRecordBusiFactory extends AbstractPatientAssayRecordBusi {

    private static final Logger log = LoggerFactory.getLogger(FivePatientAssayRecordBusiFactory.class);

    @Autowired
    private IPatientAssayRecordService patientAssayRecordService;

    @Autowired
    private IPatientAssayRecordBusiService PatientAssayRecordBusiService;

    @Autowired
    private IPatientAssayBackInspectioidService patientAssayBackInspectioidService;

    @Override
    public void save(Date startCreateTime, Date endCreateTime) {
        Date nowDate = new Date();
        if (startCreateTime == null && endCreateTime == null) {
            startCreateTime = DateFormatUtil.getStartTime(nowDate);
            endCreateTime = DateFormatUtil.getEndTime(nowDate);
        }
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(1008);
        PatientAssayRecordBusi patientAssayRecordBusi;
        // List<PatientAssayRecordPO> listPatientAssayRecord = new ArrayList<>(523853);
        List<PatientAssayRecordPO> listPatientAssayRecord = patientAssayRecordService.listByCreateTime(startCreateTime, endCreateTime);
        if (log.isDebugEnabled()) {
            log.debug("查询病人化验检查结果表成功总共查询到" + listPatientAssayRecord.size());
        }
        if (listPatientAssayRecord.size() == 0) {
            return;
        }
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayRecordBusi.class.getSimpleName(), UserUtil.getTenantId(), listPatientAssayRecord.size());
        int i = 1;
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
                    if (result != null) {
                        Double matcherToNum = matcherToNum(result);
                        patientAssayRecordBusi.setResultActual(matcherToNum);
                    }
                } catch (Exception e) {
                    log.error("result exception:", e);
                }
                patientAssayRecordBusi.setId(id++);
                // 5：根据化验表单条数
                patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_BEFORE);
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

    public void cleanDate(Map<Long, List<Date>> map) {
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
        PatientAssayBackInspectioid patientAssayBackInspectioid;
        List<PatientAssayBackInspectioid> insertPatientAssayBackInspectioidList = new ArrayList<>();
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
            endCreateDate = nowDate;
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
                        patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_AFTER);
                        updateRecordList.add(patientAssayRecordBusi);
                        patientAssayBackInspectioid = new PatientAssayBackInspectioid();
                        patientAssayBackInspectioid.setReqId(afterPatientAssayRecord.getReqId());
                        patientAssayBackInspectioid.setSampleTime(afterPatientAssayRecord.getSampleTime());
                        patientAssayBackInspectioid.setFkPatientId(afterPatientAssayRecord.getFkPatientId());
                        patientAssayBackInspectioid.setFkTenantId(UserUtil.getTenantId());
                        patientAssayBackInspectioid.setDiaAbFlag(AssayConsts.LAB_AFTER);
                        patientAssayBackInspectioid.setCreateTime(nowDate);
                        patientAssayBackInspectioid.setUpdateTime(nowDate);
                        patientAssayBackInspectioid.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                        patientAssayBackInspectioid.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                        insertPatientAssayBackInspectioidList.add(patientAssayBackInspectioid);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(updateRecordList)) {
                this.updateListPatientAssayRecordBusi(updateRecordList);
            }
            if (CollectionUtils.isNotEmpty(insertPatientAssayBackInspectioidList)) {
                patientAssayBackInspectioidService.insertList(insertPatientAssayBackInspectioidList);
            }
        }
    }

    /**
     * 根据备份的透后申请单号更新化验表
     * 
     * @Title: updateBaskDiaAbFlag
     *
     */
    public void updateBaskDiaAbFlag() {
        List<PatientAssayRecordBusi> updateRecordList = new ArrayList<>();
        PatientAssayRecordBusi patientAssayRecordBusi;
        List<PatientAssayBackInspectioid> listPatientAssayBackInspectioid = patientAssayBackInspectioidService.selectByPatientId(null);
        for (PatientAssayBackInspectioid patientAssayBackInspectioid : listPatientAssayBackInspectioid) {
            patientAssayRecordBusi = new PatientAssayRecordBusi();
            BeanUtil.copyProperties(patientAssayBackInspectioid, patientAssayRecordBusi);
            updateRecordList.add(patientAssayRecordBusi);
        }
        if (CollectionUtils.isNotEmpty(updateRecordList)) {
            this.updateListPatientAssayRecordBusi(updateRecordList);
        }
    }

}