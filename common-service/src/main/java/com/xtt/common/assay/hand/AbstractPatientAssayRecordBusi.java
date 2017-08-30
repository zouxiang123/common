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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IAssayHospDictGroupMappingService;
import com.xtt.common.assay.service.IAssayHospDictGroupService;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayBackInspectioidService;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
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
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

//抽象工厂
@Service
public abstract class AbstractPatientAssayRecordBusi {

    @Autowired
    private IPatientAssayRecordBusiService PatientAssayRecordBusiService;

    @Autowired
    private IAssayHospDictService assayHospDictService;

    @Autowired
    private IAssayHospDictGroupService assayHospDictGroupService;

    @Autowired
    private IAssayHospDictGroupMappingService assayHospDictGroupMappingService;

    @Autowired
    private IPatientAssayBackInspectioidService patientAssayBackInspectioidService;

    /**
     * 清洗数据方法
     * 
     * @Title: save
     * @param startCreateTime
     * @param endCreateTime
     *
     */
    public abstract void save(Date startCreateTime, Date endCreateTime, Long fkPatientId);

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

    public static boolean isNum(String str) {
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
