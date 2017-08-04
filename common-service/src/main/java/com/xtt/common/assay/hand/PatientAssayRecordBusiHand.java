/**   
 * @Title: PatientAssayRecordBusiHand.java 
 * @Package com.xtt.common.assay.hand
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月4日 下午4:04:03 
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
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.dto.DictDto;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class PatientAssayRecordBusiHand {

    private static final Logger log = LoggerFactory.getLogger(PatientAssayRecordBusiHand.class);

    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;

    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;

    String labAfterBefore = SysParamUtil.getValueByName(10107, AssayConsts.LAB_AFTER_BEFORE);

    public void save(Date startCreateTime, Date endCreateTime) {
        UserUtil.setThreadTenant(10107);
        Date nowDate = new Date();
        if (startCreateTime == null && endCreateTime == null) {
            startCreateTime = DateFormatUtil.getStartTime(nowDate);
            endCreateTime = DateFormatUtil.getEndTime(nowDate);
        }
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(512);
        PatientAssayRecordBusi patientAssayRecordBusi;
        List<PatientAssayRecordPO> listPatientAssayRecord = patientAssayRecordMapper.listByCreateTime(startCreateTime, endCreateTime);
        if (log.isDebugEnabled()) {
            log.debug("查询病人化验检查结果表成功总共查询到" + listPatientAssayRecord.size());
        }
        Long id = PrimaryKeyUtil.getPrimaryKey("patientAssayRecordBusi", 10101, listPatientAssayRecord.size());
        int i = 1;
        for (PatientAssayRecordPO patientAssayRecord : listPatientAssayRecord) {
            // 检查项目唯一ID为空时候不插入
            if (patientAssayRecord.getInspectionId() == null) {
                continue;
            }
            int countByInspectionId = patientAssayRecordBusiMapper.countByInspectionId(patientAssayRecord.getInspectionId());
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
                // 1：根据group_name判断
                if (AssayConsts.LAB_AFTER_BEFORE_ONE.equals(labAfterBefore)) {
                    // 申请单名
                    listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getGroupName()));
                }
                // 2：根据sample_class判断
                if (AssayConsts.LAB_AFTER_BEFORE_TWO.equals(labAfterBefore)) {
                    // 样本类型
                    listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getSampleClass()));
                }
                // 3：根据item_code判断
                if (AssayConsts.LAB_AFTER_BEFORE_THREE.equals(labAfterBefore)) {
                    // 项目名称
                    listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getItemCode()));
                }
                // 4：根据sample_time判断
                if (AssayConsts.LAB_AFTER_BEFORE_FOUR.equals(labAfterBefore)) {
                    patientAssayRecordBusi.setDiaAbFlag(AssayConsts.NOT_AFTER_BEFORE);
                    listPatientAssayRecordBusi.add(patientAssayRecordBusi);
                }
                // 5：根据化验表单条数
                if (AssayConsts.LAB_AFTER_BEFORE_FIVE.equals(labAfterBefore)) {
                    patientAssayRecordBusi.setDiaAbFlag(AssayConsts.NOT_AFTER_BEFORE);
                    listPatientAssayRecordBusi.add(patientAssayRecordBusi);
                }
                i++;
                if (i == 1000) {
                    patientAssayRecordBusiMapper.insertList(listPatientAssayRecordBusi);
                    listPatientAssayRecordBusi.clear();
                    i = 1;
                }
                patientAssayRecordBusi = null;
            }
        }
        if (listPatientAssayRecordBusi.size() != 0) {
            patientAssayRecordBusiMapper.insertList(listPatientAssayRecordBusi);
        }
        // 4：根据sample_time判断
        if (AssayConsts.LAB_AFTER_BEFORE_FOUR.equals(labAfterBefore)) {
            sampleTime(startCreateTime, endCreateTime);
        }
        // 5：根据化验表单条数
        if (AssayConsts.LAB_AFTER_BEFORE_FIVE.equals(labAfterBefore)) {
            countResult(startCreateTime, endCreateTime);
        }
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
        List<PatientAssayRecordPO> listAfterPatientAssayRecord = patientAssayRecordMapper.listByAfterCount(afterCount, startCreateTime, endCreateTime,
                        UserUtil.getTenantId(), strItemCode.toString());

        if (CollectionUtils.isNotEmpty(listAfterPatientAssayRecord)) {
            long time1;
            long time2;
            long sj = StringUtil.isEmpty(sjStr) ? 0L : Long.valueOf(sjStr);

            PatientAssayRecordBusi patientAssayRecordBusi = new PatientAssayRecordBusi();
            // 化验的开始时间
            Date startSampleTime = new Date();
            for (PatientAssayRecordPO patientAssayRecord : listAfterPatientAssayRecord) {
                startSampleTime = DateUtil.add(patientAssayRecord.getSampleTime(), 10, (Integer.parseInt(sjStr) * -1));
                PatientAssayRecordPO getPatientAssayRecord = patientAssayRecordMapper.getByBeforeCount(beforeCount,
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
        List<PatientAssayRecordBusi> newPatientAssayRecordBusi = new ArrayList<>();
        List<String> itemCode = new ArrayList<String>();
        for (DictDto dictDto : itemCodeList) {
            itemCode.add(dictDto.getItemCode());
        }
        List<PatientAssayRecord> listPatientAssayRecord = patientAssayRecordMapper.listByItemCode(startCreateTime, endCreateTime, itemCode);
        if (listPatientAssayRecord.size() > 0) {
            // 记录上一条数据患者id
            Long oldFkPatientId = 0L;
            // 记录上一条报告时间
            Date oldSampleTime = null;
            // 记录上一条申请id
            String oldReqId = null;
            // 记录上一条化验项目
            String oldItemCode = null;
            // 记录上一条化验数值
            String oldResult = null;

            long time1;
            long time2;
            long sj = StringUtil.isEmpty(sjStr) ? 0L : Long.valueOf(sjStr);
            PatientAssayRecordBusi patientAssayRecordBusi;
            for (PatientAssayRecord patientAssayRecord : listPatientAssayRecord) {
                if (patientAssayRecord.getResult() == null) {
                    continue;
                }
                if (oldFkPatientId.equals(patientAssayRecord.getFkPatientId())) {

                    if (oldSampleTime != null && patientAssayRecord.getSampleTime() != null) {
                        time1 = oldSampleTime.getTime();
                        time2 = patientAssayRecord.getSampleTime().getTime();
                        long dd = (time1 - time2) / (1000 * 3600); // 共计小时
                        if (dd <= sj && StringUtil.equals(oldItemCode, patientAssayRecord.getItemCode())
                                        && Double.valueOf(oldResult) > Double.valueOf(patientAssayRecord.getResult())) {
                            patientAssayRecordBusi = new PatientAssayRecordBusi();
                            BeanUtil.copyProperties(patientAssayRecord, patientAssayRecordBusi);
                            patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_AFTER);
                            newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                            patientAssayRecordBusi = null;
                            patientAssayRecordBusi = new PatientAssayRecordBusi();
                            patientAssayRecordBusi.setFkPatientId(oldFkPatientId);
                            patientAssayRecordBusi.setReqId(oldReqId);
                            patientAssayRecordBusi.setSampleTime(oldSampleTime);
                            patientAssayRecordBusi.setDiaAbFlag(AssayConsts.LAB_BEFORE);
                            newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                            patientAssayRecordBusi = null;
                        }
                    }
                }
                oldFkPatientId = patientAssayRecord.getFkPatientId();
                oldSampleTime = patientAssayRecord.getSampleTime();
                oldReqId = patientAssayRecord.getReqId();
                oldItemCode = patientAssayRecord.getItemCode();
                oldResult = patientAssayRecord.getResult();
            }
        }
        if (CollectionUtils.isNotEmpty(newPatientAssayRecordBusi)) {
            this.updateListPatientAssayRecordBusi(newPatientAssayRecordBusi);
        }

    }

    /**
     * 根据申请单号更新透前透后字段
     * 
     * @Title: updateListPatientAssayRecordBusi
     * @param newPatientAssayRecordBusi
     *
     */
    private void updateListPatientAssayRecordBusi(List<PatientAssayRecordBusi> newPatientAssayRecordBusi) {

        for (PatientAssayRecordBusi patientAssayRecordBusi : newPatientAssayRecordBusi) {
            patientAssayRecordBusiMapper.updateDiaAbFlagByReqId(patientAssayRecordBusi);
        }
    }

    /**
     * @Title: diaAbFlag
     * @Description:根据指定的字段判断字符中是否有透前透后关键字
     * @param where
     * @return String @throws
     */
    private String diaAbFlag(String where) {
        String ab = AssayConsts.NOT_AFTER_BEFORE;// 非透析前后
        // 关键字
        String labBefore = DictUtil.getItemCode(AssayConsts.LAB_AFTER_BEFORE_KEYWORD, AssayConsts.LAB_BEFORE);// 透析前=1
        String labAfter = DictUtil.getItemCode(AssayConsts.LAB_AFTER_BEFORE_KEYWORD, AssayConsts.LAB_AFTER);// 透析后=2
        // 透析前
        if (StringUtil.isNotEmpty(labBefore) && where.indexOf(labBefore) >= 0) {
            ab = AssayConsts.LAB_BEFORE;
        }
        // 透析后
        if (StringUtil.isNotEmpty(labAfter) && where.indexOf(labAfter) >= 0) {
            ab = AssayConsts.LAB_AFTER;
        }
        return ab;
    }

    /**
     * @Title: newPatientAssayRecordPO
     * @Description:生成新的对象
     * @param parPO
     * @param ifStr
     * @return PatientAssayRecordPO @throws
     */
    private PatientAssayRecordBusi newPatientAssayRecordBusi(PatientAssayRecordBusi patientAssayRecordBusi, String ifStr) {
        String diaAbFlag = diaAbFlag(ifStr); // 透析前后标示
        patientAssayRecordBusi.setDiaAbFlag(diaAbFlag);
        return patientAssayRecordBusi;
    }

}
