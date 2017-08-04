/**   
 * @Title: PatientAssayRecordServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:54 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
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
public class PatientAssayRecordBusiServiceImpl implements IPatientAssayRecordBusiService {
    private static final Logger log = LoggerFactory.getLogger(PatientAssayRecordBusiServiceImpl.class);

    @Autowired
    private PatientAssayRecordMapper patientAssayRecordMapper;

    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;
    @Autowired
    private IPatientAssayRecordService patientAssayRecordService;

    String labAfterBefore = SysParamUtil.getValueByName(10101, PatientAssayRecordPO.LAB_AFTER_BEFORE);

    @Override
    public void save(Date startCreateTime, Date endCreateTime) {
        UserUtil.setThreadTenant(10101);
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
                if (PatientAssayRecordPO.LAB_AFTER_BEFORE_ONE.equals(labAfterBefore)) {
                    // 申请单名
                    listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getGroupName()));
                }
                // 2：根据sample_class判断
                if (PatientAssayRecordPO.LAB_AFTER_BEFORE_TWO.equals(labAfterBefore)) {
                    // 样本类型
                    listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getSampleClass()));
                }
                // 3：根据item_code判断
                if (PatientAssayRecordPO.LAB_AFTER_BEFORE_THREE.equals(labAfterBefore)) {
                    // 项目名称
                    listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getItemCode()));
                }
                // 4：根据sample_time判断
                if (PatientAssayRecordPO.LAB_AFTER_BEFORE_FOUR.equals(labAfterBefore)) {
                    patientAssayRecordBusi.setDiaAbFlag(PatientAssayRecordPO.NOT_AFTER_BEFORE);
                    listPatientAssayRecordBusi.add(patientAssayRecordBusi);
                }
                // 5：根据化验表单条数
                if (PatientAssayRecordPO.LAB_AFTER_BEFORE_FIVE.equals(labAfterBefore)) {
                    patientAssayRecordBusi.setDiaAbFlag(PatientAssayRecordPO.NOT_AFTER_BEFORE);
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
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_FOUR.equals(labAfterBefore)) {
            sampleTime(startCreateTime, endCreateTime);
        }
        // 5：根据化验表单条数
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_FIVE.equals(labAfterBefore)) {
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
        String sjStr = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_GJZ_SJ);// 关键字：设置为24小时
        String beforeCount = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_BEFORE_COUNT);
        String afterCount = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_AFTER_COUNT);
        List<PatientAssayRecordBusi> newPatientAssayRecordBusi = new ArrayList<>();
        // 根据项目编码过滤查询
        List<DictDto> itemCodeList = DictUtil.listByPItemCode(PatientAssayRecordPO.WHERE_IN_ITEM_CODE_LIST);
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

                    time1 = patientAssayRecord.getSampleTime().getTime(); // 透析后的时间
                    time2 = getPatientAssayRecord.getSampleTime().getTime();
                    long dd = (time1 - time2) / (1000 * 3600); // 共计小时
                    if (sj > dd) {

                        patientAssayRecordBusi = new PatientAssayRecordBusi();
                        BeanUtil.copyProperties(patientAssayRecord, patientAssayRecordBusi);
                        patientAssayRecordBusi.setDiaAbFlag(PatientAssayRecordPO.LAB_AFTER);
                        newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                        patientAssayRecordBusi = null;
                        patientAssayRecordBusi = new PatientAssayRecordBusi();
                        patientAssayRecordBusi.setFkPatientId(getPatientAssayRecord.getFkPatientId());
                        patientAssayRecordBusi.setReqId(getPatientAssayRecord.getReqId());
                        patientAssayRecordBusi.setSampleTime(getPatientAssayRecord.getSampleTime());
                        patientAssayRecordBusi.setDiaAbFlag(PatientAssayRecordPO.LAB_BEFORE);
                        newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                        patientAssayRecordBusi = null;
                    }
                }
            }
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
        List<DictDto> itemCodeList = DictUtil.listByPItemCode(PatientAssayRecordPO.WHERE_IN_ITEM_CODE_LIST);
        String sjStr = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_GJZ_SJ);// 关键字：设置为24小时
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
                            patientAssayRecordBusi.setDiaAbFlag(PatientAssayRecordPO.LAB_AFTER);
                            newPatientAssayRecordBusi.add(patientAssayRecordBusi);
                            patientAssayRecordBusi = null;
                            patientAssayRecordBusi = new PatientAssayRecordBusi();
                            patientAssayRecordBusi.setFkPatientId(oldFkPatientId);
                            patientAssayRecordBusi.setReqId(oldReqId);
                            patientAssayRecordBusi.setSampleTime(oldSampleTime);
                            patientAssayRecordBusi.setDiaAbFlag(PatientAssayRecordPO.LAB_BEFORE);
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

    private void updateListPatientAssayRecordBusi(List<PatientAssayRecordBusi> newPatientAssayRecordBusi) {

        for (PatientAssayRecordBusi patientAssayRecordBusi : newPatientAssayRecordBusi) {
            patientAssayRecordBusiMapper.updateDiaAbFlagByReqId(patientAssayRecordBusi);
        }
    }

    public void updateLisAfterBefore(List<PatientAssayRecordPO> listPatientAssayRecord) {
        // 检验透析前后判断逻辑控制（检验结果表<patient_assay_record> 1：根据group_name判断 2：根据sample_class判断 3：根据item_code判断 4：根据sample_time判断）
        UserUtil.setThreadTenant(10101);
        String labAfterBefore = SysParamUtil.getValueByName(UserUtil.getTenantId(), PatientAssayRecordPO.LAB_AFTER_BEFORE);
        String assayMonth = DateFormatUtil.getCurrentDateStr(DateFormatUtil.FORMAT_YYYY_MM);

        // 根据指定的条件获取检验结果集
        log.info("labAfterBefore 判断逻辑：" + labAfterBefore + ",获取集合大小：" + listPatientAssayRecord.size());
        // 如果为（返回的集合为null|只有一条数据|没有配置开个信息）不做任何处理
        if (StringUtil.isEmpty(labAfterBefore) || CollectionUtils.isEmpty(listPatientAssayRecord) || listPatientAssayRecord.size() <= 1) {
            return;
        }

        List<PatientAssayRecordPO> newList = new ArrayList<PatientAssayRecordPO>(listPatientAssayRecord.size());
        /* // 1：根据group_name判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_ONE.equals(labAfterBefore)) {
            for (PatientAssayRecordPO parPO : listPatientAssayRecord) {
                String groupName = parPO.getGroupName();// 申请单名
                PatientAssayRecordPO newParPO = newPatientAssayRecordPO(parPO, groupName);
                newList.add(newParPO);
            }
        }
        // 2：根据sample_class判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_TWO.equals(labAfterBefore)) {
            for (PatientAssayRecordPO parPO : listPatientAssayRecord) {
                String sampleClass = parPO.getSampleClass();// 样本类型
                PatientAssayRecordPO newParPO = newPatientAssayRecordPO(parPO, sampleClass);
                newList.add(newParPO);
            }
        }
        // 3：根据item_code判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_THREE.equals(labAfterBefore)) {
            for (PatientAssayRecordPO parPO : listPatientAssayRecord) {
                String itemCode = parPO.getItemCode();// 项目名称
                PatientAssayRecordPO newParPO = newPatientAssayRecordPO(parPO, itemCode);
                newList.add(newParPO);
            }
        }*/
        // 4：根据sample_time判断
        if (PatientAssayRecordPO.LAB_AFTER_BEFORE_FOUR.equals(labAfterBefore)) {
            // 根据项目编码过滤查询
            List<DictDto> itemCodeList = DictUtil.listByPItemCode(PatientAssayRecordPO.WHERE_IN_ITEM_CODE_LIST);
            List<String> itemCode = new ArrayList<String>();
            for (DictDto dictDto : itemCodeList) {
                itemCode.add(dictDto.getItemCode());
            }
            // a.根据指定的条件获取检验结果集(根据指定的项目编码获取项目所在的申请单信息)
            List<PatientAssayRecordPO> listItemCodePO = listPatientAssayRecord(assayMonth, itemCode, null);
            List<String> reqList = new ArrayList<String>();
            for (PatientAssayRecordPO patientAssayRecordPO : listItemCodePO) {
                String reqId = patientAssayRecordPO.getReqId();// 申请单ID
                reqList.add(reqId);
            }
            // b.根据指定的条件获取检验结果集(根据申请单获取所有的数据)
            List<PatientAssayRecordPO> listReqIdPO = listPatientAssayRecord(assayMonth, null, reqList);

            // c.对查询的结果集做逻辑处理
            newList = listPatientAssayRecordPO(listReqIdPO);
        }

        // 更新检验结果（透析前后逻辑）
        if (CollectionUtils.isNotEmpty(newList)) {
            updateListPatientAssayRecord(newList);
        }
        log.info("==========更新了检验透析前后逻辑标示：" + newList.size() + "条记录.");
    }

    /**
     * @Title: diaAbFlag
     * @Description:根据指定的条件判断是透析前还是透析后
     * @param where
     * @return String @throws
     */
    private String diaAbFlag(String where) {
        String ab = "0";// 非透析前后
        // 关键字
        String labBefore = DictUtil.getItemCode(PatientAssayRecordPO.LAB_AFTER_BEFORE_KEYWORD, PatientAssayRecordPO.LAB_BEFORE);// 透析前=1
        String labAfter = DictUtil.getItemCode(PatientAssayRecordPO.LAB_AFTER_BEFORE_KEYWORD, PatientAssayRecordPO.LAB_AFTER);// 透析后=2
        // 透析前
        if (StringUtil.isNotEmpty(labBefore) && where.indexOf(labBefore) >= 0) {
            ab = PatientAssayRecordPO.LAB_BEFORE;
        }
        // 透析后
        if (StringUtil.isNotEmpty(labAfter) && where.indexOf(labAfter) >= 0) {
            ab = PatientAssayRecordPO.LAB_AFTER;
        }
        return ab;
    }

    /**
     * @Title: listPatientAssayRecordPO
     * @Description:根据样本时间来判断
     * @param listPO
     * @return List<PatientAssayRecordPO> @throws
     */
    private List<PatientAssayRecordPO> listPatientAssayRecordPO(List<PatientAssayRecordPO> listPO) {
        List<PatientAssayRecordPO> newList = new ArrayList<PatientAssayRecordPO>();
        String sjStr = DictUtil.getItemCode("lab_after_before_keyword", PatientAssayRecordPO.LAB_GJZ_SJ);// 关键字：设置为24小时
        if (StringUtil.isNotEmpty(sjStr)) {
            Long sj = Long.valueOf(sjStr);
            for (int i = 0; i < listPO.size() - 1; i++) {
                PatientAssayRecordPO po1 = listPO.get(i);// 集合中第一条记录
                PatientAssayRecordPO po2 = listPO.get(i + 1);// 集合的第二条记录
                Date sampleTime1 = po1.getSampleTime();
                Date sampleTime2 = po2.getSampleTime();
                if (sampleTime1 != null && sampleTime2 != null) {
                    long time1 = sampleTime1.getTime();
                    long time2 = sampleTime2.getTime();

                    long dd = (time1 - time2) / (1000 * 3600); // 共计小时
                    if (dd <= sj) {
                        newList.add(po1);
                        newList.add(po2);
                    }
                }
            }
        }
        return newList;
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

    private void updateListPatientAssayRecord(List<PatientAssayRecordPO> newList) {

        for (PatientAssayRecord patientAssayRecord : newList) {
            patientAssayRecordBusiMapper.updateByInspectionId(patientAssayRecord);
        }
    }

    /**
     * @Title: listPatientAssayRecord
     * @Description:根据指定条件获取结果集数据
     * @param assayMonth
     * @param itemCodeList
     * @param reqIdList
     * @return List <PatientAssayRecordPO> @throws
     */
    private List<PatientAssayRecordPO> listPatientAssayRecord(String assayMonth, List<String> itemCodeList, List<String> reqIdList) {
        PatientAssayRecordPO po = new PatientAssayRecordPO();
        po.setAssayMonth(assayMonth);
        po.setItemCodeList(itemCodeList);
        po.setReqIdList(reqIdList);
        po.setDiaAbFlag(PatientAssayRecordPO.NOT_AFTER_BEFORE);// 只处理未处理的非透析前后的数据
        List<PatientAssayRecordPO> listPatientAssayRecord = patientAssayRecordService.listPatientAssayRecord(po);
        return listPatientAssayRecord;
    }

    @Override
    public List<PatientAssayRecordBusiPO> listByCondition(PatientAssayRecordBusiPO query) {
        if (query.getFkTenantId() == null) {
            query.setFkTenantId(UserUtil.getTenantId());
        }
        return patientAssayRecordBusiMapper.listByCondition(query);
    }

}
