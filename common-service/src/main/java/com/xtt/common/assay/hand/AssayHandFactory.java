/**   
 * @Title: AbstractPatientAssayRecordBusi.java 
 * @Package com.xtt.common.assay.hand
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月4日 下午6:01:35 
 *
 */
package com.xtt.common.assay.hand;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IAssayFilterRuleService;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayInspectioidBackService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.assay.service.impl.AssayFilterRuleServiceImpl;
import com.xtt.common.assay.service.impl.AssayHospDictServiceImpl;
import com.xtt.common.assay.service.impl.PatientAssayInspectioidBackServiceImpl;
import com.xtt.common.assay.service.impl.PatientAssayRecordBusiServiceImpl;
import com.xtt.common.assay.service.impl.PatientAssayRecordServiceImpl;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.dao.model.PatientAssayInspectioidBack;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;
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

    protected IPatientAssayRecordBusiService patientAssayRecordBusiService = SpringUtil.getBean(
                    StringUtil.firstToLowerCase(PatientAssayRecordBusiServiceImpl.class.getSimpleName()), IPatientAssayRecordBusiService.class);

    protected IAssayHospDictService assayHospDictService = SpringUtil
                    .getBean(StringUtil.firstToLowerCase(AssayHospDictServiceImpl.class.getSimpleName()), IAssayHospDictService.class);

    protected IPatientAssayInspectioidBackService patientAssayInspectioidBackService = SpringUtil.getBean(
                    StringUtil.firstToLowerCase(PatientAssayInspectioidBackServiceImpl.class.getSimpleName()),
                    IPatientAssayInspectioidBackService.class);

    protected IPatientAssayRecordService patientAssayRecordService = SpringUtil
                    .getBean(StringUtil.firstToLowerCase(PatientAssayRecordServiceImpl.class.getSimpleName()), IPatientAssayRecordService.class);

    protected IAssayFilterRuleService assayFilterRuleService = SpringUtil
                    .getBean(StringUtil.firstToLowerCase(AssayFilterRuleServiceImpl.class.getSimpleName()), IAssayFilterRuleService.class);

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
    public Set<String> insertAssayRecord(List<PatientAssayRecordPO> listPatientAssayRecord) {
        Date nowDate = new Date();
        PatientAssayRecordBusi patientAssayRecordBusi;
        // 获取主键id
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayRecordBusi.class.getSimpleName(), UserUtil.getTenantId(), listPatientAssayRecord.size());
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(1008);
        int i = 1;
        List<PatientAssayInspectioidBack> inspectioidBackList = new ArrayList<>();
        AssayFilterRule assayFilterRule = assayFilterRuleService.getByTenantId(UserUtil.getTenantId());
        Set<String> existsInspectionId = new HashSet<>(listPatientAssayRecord.size());
        // 数据来源表查询到的有效的数据日期
        Set<String> dateSet = new TreeSet<>();
        // 默认为取样时间
        boolean issampleTime = StringUtil.equals(assayFilterRule.getAssayDateType(), AssayConsts.SAMPLE_TIME);
        for (PatientAssayRecordPO patientAssayRecord : listPatientAssayRecord) {
            // 检查项目唯一ID、itemCode、组id、化验结果等任意一项数据为空则不处理
            if (StringUtil.isBlank(patientAssayRecord.getInspectionId()) || StringUtil.isBlank(patientAssayRecord.getItemCode())
                            || StringUtil.isBlank(patientAssayRecord.getGroupId()) || StringUtil.isBlank(patientAssayRecord.getResult())) {
                continue;
            }
            if (patientAssayRecord.getSampleTime() == null && patientAssayRecord.getReportTime() == null) {// 如果样品时间不存在且报告时间不存在，不处理
                continue;
            }
            if (patientAssayRecord.getSampleTime() == null) {// 如果样品时间为空，设置样品时间的值为报告时间的
                patientAssayRecord.setSampleTime(patientAssayRecord.getReportTime());
            }
            if (patientAssayRecord.getReportTime() == null) {// 如果报告时间为空，设置报告时间的值为样品时间的
                patientAssayRecord.setReportTime(patientAssayRecord.getSampleTime());
            }
            Date assayDate;
            if (issampleTime) {
                assayDate = patientAssayRecord.getSampleTime();// 检查日期默认取样品时间
            } else {
                assayDate = patientAssayRecord.getReportTime();// 检查日期为报告时间
            }

            dateSet.add(DateFormatUtil.convertDateToStr(assayDate));// 添加查询到的数据日期
            int countByInspectionId = patientAssayRecordBusiService.countByInspectionId(patientAssayRecord.getInspectionId(),
                            patientAssayRecord.getFkTenantId());
            if (countByInspectionId != 0) {
                continue;
            }
            if (existsInspectionId.contains(patientAssayRecord.getInspectionId())) {// 如果插入过，无须再插入
                continue;
            }
            existsInspectionId.add(patientAssayRecord.getInspectionId());
            patientAssayRecordBusi = new PatientAssayRecordBusi();
            BeanUtil.copyProperties(patientAssayRecord, patientAssayRecordBusi);
            // create time 取原始数据的create_time,以免后续查询获取不到数据
            patientAssayRecordBusi.setFlage(false);
            patientAssayRecordBusi.setUpdateTime(nowDate);
            patientAssayRecordBusi.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
            patientAssayRecordBusi.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
            patientAssayRecordBusi.setAssayDate(assayDate);
            patientAssayRecordBusi.setAssayMonth(DateFormatUtil.convertDateToStr(assayDate, DateFormatUtil.FORMAT_YYYY_MM));
            patientAssayRecordBusi.setResultActual(matcherToNum(patientAssayRecordBusi.getResult()));
            patientAssayRecordBusi.setId(id++);
            patientAssayRecordBusi
                            .setDiaAbFlag(getDiaAbAlag(patientAssayRecord, assayFilterRule.getKeywordBefore(), assayFilterRule.getKeywordAfter()));
            // 备份透后记录
            PatientAssayInspectioidBack inspectioidBack = patientAssayRecordBusiService.getInspectioidBack(patientAssayRecordBusi.getInspectionId(),
                            patientAssayRecordBusi.getFkPatientId(), patientAssayRecordBusi.getDiaAbFlag(), patientAssayRecordBusi.getFkTenantId());
            if (inspectioidBack != null) {
                inspectioidBackList.add(inspectioidBack);
            }
            listPatientAssayRecordBusi.add(patientAssayRecordBusi);
            i++;
            if (i == 1000) {
                patientAssayRecordBusiService.insertList(listPatientAssayRecordBusi);
                listPatientAssayRecordBusi.clear();
                i = 1;
            }
            patientAssayRecordBusi = null;
        }
        if (inspectioidBackList.size() > 0) {
            patientAssayInspectioidBackService.insertList(inspectioidBackList);
        }
        if (listPatientAssayRecordBusi.size() != 0) {
            patientAssayRecordBusiService.insertList(listPatientAssayRecordBusi);
        }
        listPatientAssayRecord = null;
        return dateSet;
    }

    /**
     * 获取透前透后标识
     * 
     * @Title: getDiaAbAlag
     * @param record
     * @return
     *
     */
    abstract String getDiaAbAlag(PatientAssayRecordPO record, String labBefore, String labAfter);

    /**
     * 插入之后处理透前透后标识
     * 
     * @Title: afterHandDiaAbAlag
     * @param mapPatientId
     * @param startCreateTime
     * @param endCreateTime
     * @param fkPatientId
     * @param listAssayRecord
     *
     */
    public abstract void afterHandDiaAbAlag(Map<Long, List<Date>> mapPatientId, Date startCreateTime, Date endCreateTime, Long fkPatientId);

    /**
     * 清洗数据方法
     * 
     * @Title: save
     * @param startCreateTime
     * @param endCreateTime
     * @param mapPatientId
     * @return 所有查询到数据的化验日期
     */
    public Set<String> save(Date startCreateTime, Date endCreateTime, Long fkPatientId, Map<Long, List<Date>> mapPatientId) {
        List<PatientAssayRecordPO> listAssayRecord = listPatientAssayRecordByCreateTime(startCreateTime, endCreateTime, fkPatientId);
        Set<String> dateSet = null;
        if (!CollectionUtils.isEmpty(listAssayRecord)) {
            dateSet = insertAssayRecord(listAssayRecord);
        }
        afterHandDiaAbAlag(mapPatientId, startCreateTime, endCreateTime, fkPatientId);
        // 更新透后数据的itemCode
        handAfterItemCode(startCreateTime, endCreateTime, fkPatientId);
        return dateSet;
    }

    /**
     * 更新透后数据的itemCode
     * 
     * @Title: handAfterItemCode
     * @param startCreateTime
     * @param endCreateTime
     * @param fkPatientId
     *
     */
    private void handAfterItemCode(Date startCreateTime, Date endCreateTime, Long fkPatientId) {
        PatientAssayRecordBusiPO query = new PatientAssayRecordBusiPO();
        query.setFkPatientId(fkPatientId);
        query.setCreateTime(startCreateTime);
        query.setEndCreateTime(endCreateTime);
        query.setDiaAbFlag(AssayConsts.AFTER_HD);
        query.setFkTenantId(UserUtil.getTenantId());
        List<PatientAssayRecordBusiPO> list = patientAssayRecordBusiService.listByCondition(query);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(par -> {
                if (!par.getItemCode().endsWith(AssayConsts.AFTER_HD_SUFFIX)) {// 如果item_code已经是处理过的，不用更新对应的itemCode和itemName
                    par.setItemName(par.getItemName().concat(AssayConsts.AFTER_HD_SUFFIX_NAME));
                    par.setItemCode(par.getItemCode().concat(AssayConsts.AFTER_HD_SUFFIX));
                    // 更新透后标识
                    PatientAssayRecordBusiPO update = new PatientAssayRecordBusiPO();
                    update.setId(par.getId());
                    update.setItemName(par.getItemName());
                    update.setItemCode(par.getItemCode());
                    patientAssayRecordBusiService.updateByIdSelective(update);
                }
            });
            // 插入透后字典数据
            insertAssayHospDict(list, true);
        }
    }

    /**
     * @Title: diaAbFlag
     * @Description:根据指定的字段判断字符中是否有透前透后关键字
     * @param where
     * @return String @throws
     */
    public String diaAbFlag(String where, String labBefore, String labAfter) {
        String ab = AssayConsts.BEFORE_HD;// 默认透前
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
                LOGGER.error("result exception:", e);
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
     * 根据租户id删除数据
     * 
     * @Title: deleteByTenant
     * @param fkTenantId
     *
     */
    public void deleteByTenant(Integer fkTenantId) {
        patientAssayRecordBusiService.deleteByTenant(fkTenantId);
    }

    /**
     * 根据患者删除
     * 
     * @Title: deleteAll
     *
     */
    public void deleteByPatient(Long fkPatientId, Integer fkTenantId) {
        patientAssayRecordBusiService.deleteByPatientId(fkPatientId, fkTenantId);
    }

    /**
     * 插入字典表数据
     * 
     * @Title: insertAssayHospDict
     * @param list
     * @param isPost
     *            是否透后
     *
     */
    public void insertAssayHospDict(List<PatientAssayRecordBusiPO> list, boolean isPost) {
        List<AssayHospDictPO> dictList = new ArrayList<>(list.size());
        list.forEach(par -> {
            AssayHospDictPO dict = new AssayHospDictPO();
            dict.setGroupId(par.getGroupId());
            dict.setGroupName(par.getGroupName());
            dict.setItemCode(par.getItemCode());
            dict.setItemName(par.getItemName());
            dict.setValueType(par.getValueType());
            dict.setMinValue(par.getMinValue());
            dict.setMaxValue(par.getMaxValue());
            dict.setUnit(par.getValueUnit());
            dict.setScalingFactor(par.getScalingFactor());
            dict.setFkTenantId(par.getFkTenantId());
            dictList.add(dict);
        });
        assayHospDictService.insertList(dictList);
    }

}
