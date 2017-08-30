/**   
 * @Title: AbstractPatientAssayRecordBusi.java 
 * @Package com.xtt.common.assay.hand
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月4日 下午6:01:35 
 *
 */
package com.xtt.common.assay.hand;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IAssayHospDictGroupMappingService;
import com.xtt.common.assay.service.IAssayHospDictGroupService;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayBackInspectioidService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.assay.service.impl.AssayHospDictGroupMappingServiceImpl;
import com.xtt.common.assay.service.impl.AssayHospDictGroupServiceImpl;
import com.xtt.common.assay.service.impl.AssayHospDictServiceImpl;
import com.xtt.common.assay.service.impl.PatientAssayBackInspectioidServiceImpl;
import com.xtt.common.assay.service.impl.PatientAssayRecordBusiServiceImpl;
import com.xtt.common.assay.service.impl.PatientAssayRecordServiceImpl;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.AssayHospDict;
import com.xtt.common.dao.model.AssayHospDictGroup;
import com.xtt.common.dao.model.AssayHospDictGroupMapping;
import com.xtt.common.dao.model.PatientAssayBackInspectioid;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.config.SpringUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

//抽象工厂
public abstract class AssayHandFactory {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected IPatientAssayRecordBusiService PatientAssayRecordBusiService = SpringUtil.getBean(
                    StringUtil.firstToLowerCase(PatientAssayRecordBusiServiceImpl.class.getSimpleName()), IPatientAssayRecordBusiService.class);

    protected IAssayHospDictService assayHospDictService = SpringUtil
                    .getBean(StringUtil.firstToLowerCase(AssayHospDictServiceImpl.class.getSimpleName()), IAssayHospDictService.class);

    protected IAssayHospDictGroupService assayHospDictGroupService = SpringUtil
                    .getBean(StringUtil.firstToLowerCase(AssayHospDictGroupServiceImpl.class.getSimpleName()), IAssayHospDictGroupService.class);

    protected IAssayHospDictGroupMappingService assayHospDictGroupMappingService = SpringUtil.getBean(
                    StringUtil.firstToLowerCase(AssayHospDictGroupMappingServiceImpl.class.getSimpleName()), IAssayHospDictGroupMappingService.class);

    protected IPatientAssayBackInspectioidService patientAssayBackInspectioidService = SpringUtil.getBean(
                    StringUtil.firstToLowerCase(PatientAssayBackInspectioidServiceImpl.class.getSimpleName()),
                    IPatientAssayBackInspectioidService.class);

    protected IPatientAssayRecordService patientAssayRecordService = SpringUtil
                    .getBean(StringUtil.firstToLowerCase(PatientAssayRecordServiceImpl.class.getSimpleName()), IPatientAssayRecordService.class);

    /**
     * 根据创建时间查询数据
     * 
     * @Title: listPatientAssayRecordByCreateTime
     * @param startCreateTime
     * @param endCreateTime
     * @param fkPatientId
     * @return
     *
     */
    public List<PatientAssayRecordPO> listPatientAssayRecordByCreateTime(Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        Date nowDate = new Date();
        if (startCreateTime == null && endCreateTime == null) {
            // 当天开始时间
            startCreateTime = DateFormatUtil.getStartTime(nowDate);
            // 当天结束时间
            endCreateTime = DateFormatUtil.getEndTime(nowDate);
        }
        // 创建对象存储需要插入的数据
        List<PatientAssayRecordPO> listPatientAssayRecord = patientAssayRecordService.listByCreateTime(startCreateTime, endCreateTime, fkPatientId);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("查询病人化验检查结果表成功总共查询到" + listPatientAssayRecord.size());
        }
        return listPatientAssayRecord;
    }

    /**
     * 插入数据
     * 
     * @Title: insertAssayRecord
     *
     */
    public void insertAssayRecord(List<PatientAssayRecordPO> listPatientAssayRecord) {
        Date nowDate = new Date();
        PatientAssayRecordBusi patientAssayRecordBusi;
        // 获取主键id
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayRecordBusi.class.getSimpleName(), UserUtil.getTenantId(), listPatientAssayRecord.size());
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(1008);
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
                    LOGGER.error("result exception:", e);
                }
                patientAssayRecordBusi.setId(id++);
                // 5：根据化验表单条数
                patientAssayRecordBusi.setDiaAbFlag(getDiaAbAlag(patientAssayRecord));
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
     * 获取透前透后标识
     * 
     * @Title: getDiaAbAlag
     * @param record
     * @return
     *
     */
    abstract String getDiaAbAlag(PatientAssayRecordPO record);

    /**
     * 插入之后处理透前透后标识
     * 
     * @Title: afterHandDiaAbAlag
     * @param mapPatientId
     * @param startCreateTime
     * @param endCreateTime
     * @param listAssayRecord
     *
     */
    public abstract void afterHandDiaAbAlag(Map<Long, List<Date>> mapPatientId, Date startCreateTime, Date endCreateTime,
                    List<PatientAssayRecordPO> listAssayRecord);

    /**
     * 清洗数据方法
     * 
     * @Title: save
     * @param startCreateTime
     * @param endCreateTime
     * @param mapPatientId
     *
     */
    public void save(Date startCreateTime, Date endCreateTime, Long fkPatientId, Map<Long, List<Date>> mapPatientId) {
        List<PatientAssayRecordPO> listAssayRecord = listPatientAssayRecordByCreateTime(startCreateTime, endCreateTime, fkPatientId);
        insertAssayRecord(listAssayRecord);
        afterHandDiaAbAlag(mapPatientId, startCreateTime, endCreateTime, listAssayRecord);
    };

    /**
     * 根据申请单号更新透前透后字段
     * 
     * @Title: updateListPatientAssayRecordBusi
     * @param newPatientAssayRecordBusi
     *
     */
    public void updateListPatientAssayRecordBusi(List<PatientAssayRecordBusi> newPatientAssayRecordBusi) {

        for (PatientAssayRecordBusi patientAssayRecordBusi : newPatientAssayRecordBusi) {
            PatientAssayRecordBusiService.updateDiaAbFlagByReqId(patientAssayRecordBusi);
        }
    }

    /**
     * @Title: diaAbFlag
     * @Description:根据指定的字段判断字符中是否有透前透后关键字
     * @param where
     * @return String @throws
     */
    public String diaAbFlag(String where) {
        String ab = AssayConsts.BEFORE_HD;// 默认透前
        // 关键字
        String labBefore = DictUtil.getItemCode(AssayConsts.LAB_AFTER_BEFORE_KEYWORD, AssayConsts.BEFORE_HD);// 透析前=1
        String labAfter = DictUtil.getItemCode(AssayConsts.LAB_AFTER_BEFORE_KEYWORD, AssayConsts.AFTER_HD);// 透析后=2
        // 透析前
        if (StringUtil.isNotEmpty(labBefore) && where.indexOf(labBefore) >= 0) {
            ab = AssayConsts.BEFORE_HD;
        }
        // 透析后
        if (StringUtil.isNotEmpty(labAfter) && where.indexOf(labAfter) >= 0) {
            ab = AssayConsts.AFTER_HD;
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
    public PatientAssayRecordBusi newPatientAssayRecordBusi(PatientAssayRecordBusi patientAssayRecordBusi, String ifStr) {
        String diaAbFlag = diaAbFlag(ifStr); // 透析前后标示
        patientAssayRecordBusi.setDiaAbFlag(diaAbFlag);
        return patientAssayRecordBusi;
    }

    /**
     * 将报告时间转换成月份
     * 
     * @Title: getAssayMonth
     * @param sampleTime
     * @return
     *
     */
    public String getAssayMonth(Date reportTime) {
        return DateUtil.format(new Date(), DateFormatUtil.FORMAT_YYYY_MM);
    }

    public Date getAssyaDate(String strDate) {
        if (strDate == null) {
            return null;
        }
        return DateUtil.parseDate(strDate, DateFormatUtil.FORMAT_DATE1);
    }

    public Double matcherToNum(String parmStr) {
        Double retDB = null;
        boolean num = isNum(parmStr); // 是否包含数字

        if (StringUtil.isNotEmpty(parmStr) && num) {
            try {
                String zzStr = "([-\\+]?[0-9]([0-9]*)(\\.[0-9]+)?)|(^0$)";
                Pattern p = Pattern.compile(zzStr);
                Matcher m = p.matcher(parmStr);
                if (m.find() == true) {
                    String group = m.group(1);
                    retDB = Double.valueOf(group);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return retDB;
    }

    public boolean isNum(String str) {
        String regex = "[0-9]+?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 删除全部
     * 
     * @Title: deleteAll
     *
     */
    public void deleteAll() {
        PatientAssayRecordBusiService.delteteAll();
    }

    /**
     * 根据租户id删除数据
     * 
     * @Title: deleteByTenant
     * @param fkTenantId
     *
     */
    public void deleteByTenant(Integer fkTenantId) {
        PatientAssayRecordBusiService.deleteByTenant(fkTenantId);
    }

    /**
     * 根据患者删除
     * 
     * @Title: deleteAll
     *
     */
    public void deleteByPatient(Long fkPatientId, Integer fkTenantId) {
        PatientAssayRecordBusiService.deleteByPatientId(fkPatientId, fkTenantId);
    }

    /**
     * 维护字典表
     * 
     * @Title: selectInsert
     * @param par
     *
     */
    public void insertAssayHospDict(PatientAssayRecordPO par) {
        // 固定输出数据
        Date currDate = DateUtil.getCurrDate();
        String itemCode = par.getItemCode();
        String groupId = par.getGroupId();
        Integer tenantId = UserUtil.getTenantId();
        if (assayHospDictService.getCountByItemCode(itemCode) == 0) {
            AssayHospDict assayHospDict = new AssayHospDict();
            BeanUtil.copyProperties(par, assayHospDict);
            BigDecimal valueMax = par.getValueMax();// 最大值
            BigDecimal valueMin = par.getValueMin();// 最小值
            if (valueMax != null) {
                assayHospDict.setMaxValue(valueMax);
                assayHospDict.setPersonalMaxValue(valueMax);
            }
            if (valueMin != null) {
                assayHospDict.setMinValue(valueMin);
                assayHospDict.setPersonalMinValue(valueMin);
            }

            String result = par.getResult();
            if (StringUtil.isNumber(result)) {
                assayHospDict.setValueType(1);
                assayHospDict.setDateType("n");
            } else {
                assayHospDict.setValueType(2);
                assayHospDict.setDateType("s");
            }

            assayHospDict.setUnit(par.getValueUnit());
            assayHospDict.setCreateTime(currDate);
            assayHospDict.setUpdateTime(currDate);
            assayHospDict.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
            assayHospDict.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
            DataUtil.setSystemFieldValue(assayHospDict);
            assayHospDictService.insert(assayHospDict);
        }
        if (assayHospDictGroupService.getCountByGroupId(groupId) == 0) {
            AssayHospDictGroup assayHospDictGroup = new AssayHospDictGroup();
            assayHospDictGroup.setGroupId(par.getGroupId());
            assayHospDictGroup.setGroupName(par.getGroupName());
            assayHospDictGroup.setFkTenantId(tenantId);
            assayHospDictGroup.setCreateTime(currDate);
            assayHospDictGroup.setUpdateTime(currDate);
            assayHospDictGroup.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
            assayHospDictGroup.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
            assayHospDictGroupService.insert(assayHospDictGroup);
        }
        if (assayHospDictGroupMappingService.getCountByGroupId(groupId, itemCode) == 0) {
            AssayHospDictGroupMapping assayHospDictGroupMapping = new AssayHospDictGroupMapping();
            assayHospDictGroupMapping.setFkItemCode(itemCode);
            assayHospDictGroupMapping.setFkGroupId(groupId);
            assayHospDictGroupMapping.setFkTenantId(tenantId);
            assayHospDictGroupMapping.setCreateTime(currDate);
            assayHospDictGroupMapping.setUpdateTime(currDate);
            assayHospDictGroupMapping.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
            assayHospDictGroupMapping.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
            assayHospDictGroupMappingService.insert(assayHospDictGroupMapping);
        }

    }

    /**
     * 根据租户id删除数据
     * 
     * @Title: deleteAssayHospDict
     *
     */
    public void deleteAssayHospDict(Integer tenantId) {
        assayHospDictService.deleteByTenant(tenantId);
        assayHospDictGroupService.deleteByTenant(tenantId);
        assayHospDictGroupMappingService.deleteByTenant(tenantId);
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
