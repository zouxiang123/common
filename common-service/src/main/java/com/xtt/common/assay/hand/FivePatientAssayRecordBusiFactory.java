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
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class FivePatientAssayRecordBusiFactory extends AbstractPatientAssayRecordBusi {

    private static final Logger log = LoggerFactory.getLogger(FivePatientAssayRecordBusiFactory.class);

    @Autowired
    private IPatientAssayRecordService patientAssayRecordService;

    @Autowired
    private IPatientAssayRecordBusiService PatientAssayRecordBusiService;

    @Override
    public void save(Date startCreateTime, Date endCreateTime) {
        Date nowDate = new Date();
        if (startCreateTime == null && endCreateTime == null) {
            startCreateTime = DateFormatUtil.getStartTime(nowDate);
            endCreateTime = DateFormatUtil.getEndTime(nowDate);
        }
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(1008);
        PatientAssayRecordBusi patientAssayRecordBusi;
        List<PatientAssayRecordPO> listPatientAssayRecord = patientAssayRecordService.listByCreateTime(startCreateTime, endCreateTime);
        if (log.isDebugEnabled()) {
            log.debug("查询病人化验检查结果表成功总共查询到" + listPatientAssayRecord.size());
        }
        Long id = PrimaryKeyUtil.getPrimaryKey("PatientAssayRecordBusi", UserUtil.getTenantId(), listPatientAssayRecord.size());
        int i = 1;
        for (PatientAssayRecordPO patientAssayRecord : listPatientAssayRecord) {
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
                patientAssayRecordBusi.setId(id++);
                // 5：根据化验表单条数
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
        countResult(startCreateTime, endCreateTime);

        listPatientAssayRecord = null;
    }

    /**
     * 根据化验项目判断透前投后
     * 
     * @Title: countResult
     * @param startCreateTime
     * @param endCreateTime
     *
     */
    public void countResult(Date startCreateTime, Date endCreateTime) {
        String sjStr = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_GJZ_SJ);// 关键字：设置为24小时
        String beforeCount = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_BEFORE_COUNT);
        String afterCount = DictUtil.getItemCode("lab_after_before_keyword", AssayConsts.LAB_AFTER_COUNT);
        List<PatientAssayRecordBusi> newPatientAssayRecordBusi = new ArrayList<>();
        // 根据项目编码过滤查询
        List<DictDto> itemCodeList = DictUtil.listByPItemCode(AssayConsts.WHERE_IN_ITEM_CODE_LIST);
        StringBuffer strItemCode = new StringBuffer();
        int i = 1;
        for (DictDto dictDto : itemCodeList) {
            strItemCode.append(" , max(case when item_code = '").append(dictDto.getItemCode()).append("' then result end ) itemCode" + i);
            i++;
        }
        List<PatientAssayRecordPO> listAfterPatientAssayRecord = patientAssayRecordService.listByAfterCount(afterCount, startCreateTime,
                        endCreateTime, UserUtil.getTenantId(), strItemCode.toString());

        if (CollectionUtils.isNotEmpty(listAfterPatientAssayRecord)) {
            long time1;
            long time2;
            long sj = StringUtil.isEmpty(sjStr) ? 0L : Long.valueOf(sjStr);

            PatientAssayRecordBusi patientAssayRecordBusi = new PatientAssayRecordBusi();
            // 化验的开始时间
            Date startSampleTime = new Date();
            for (PatientAssayRecordPO patientAssayRecord : listAfterPatientAssayRecord) {
                startSampleTime = DateUtil.add(patientAssayRecord.getSampleTime(), 10, (Integer.parseInt(sjStr) * -1));
                PatientAssayRecordPO getPatientAssayRecord = patientAssayRecordService.getByBeforeCount(beforeCount,
                                patientAssayRecord.getSampleTime(), startSampleTime, strItemCode.toString(), patientAssayRecord.getFkPatientId(),
                                UserUtil.getTenantId());
                if (getPatientAssayRecord != null && getPatientAssayRecord.getSampleTime() != null) {
                    if (itemCodeList.size() == 1
                                    && Double.valueOf(patientAssayRecord.getItemCode1()) > Double.valueOf(getPatientAssayRecord.getItemCode1())) {
                        continue;
                    }
                    if (itemCodeList.size() == 2
                                    && Double.valueOf(patientAssayRecord.getItemCode1()) > Double.valueOf(getPatientAssayRecord.getItemCode1())
                                    && Double.valueOf(patientAssayRecord.getItemCode2()) > Double.valueOf(getPatientAssayRecord.getItemCode2())) {
                        continue;
                    }
                    time1 = patientAssayRecord.getSampleTime().getTime(); // 透析后的时间
                    time2 = getPatientAssayRecord.getSampleTime().getTime();
                    long dd = (time1 - time2) / (1000 * 3600); // 共计小时
                    if (sj > dd) {

                        patientAssayRecordBusi = new PatientAssayRecordBusi();
                        BeanUtil.copyProperties(patientAssayRecord, patientAssayRecordBusi);
                        patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_AFTER);
                        newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                        patientAssayRecordBusi = null;
                        patientAssayRecordBusi = new PatientAssayRecordBusi();
                        patientAssayRecordBusi.setFkPatientId(getPatientAssayRecord.getFkPatientId());
                        patientAssayRecordBusi.setReqId(getPatientAssayRecord.getReqId());
                        patientAssayRecordBusi.setSampleTime(getPatientAssayRecord.getSampleTime());
                        patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_BEFORE);
                        newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                        patientAssayRecordBusi = null;
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(newPatientAssayRecordBusi)) {
            this.updateListPatientAssayRecordBusi(newPatientAssayRecordBusi);
        }
    }
}