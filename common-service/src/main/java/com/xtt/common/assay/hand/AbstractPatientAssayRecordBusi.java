/**   
 * @Title: AbstractPatientAssayRecordBusi.java 
 * @Package com.xtt.common.assay.hand
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月4日 下午6:01:35 
 *
 */
package com.xtt.common.assay.hand;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IPatientAssayRecordBusiService;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.util.DictUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

//抽象工厂
public abstract class AbstractPatientAssayRecordBusi {

    @Autowired
    private IPatientAssayRecordBusiService PatientAssayRecordBusiService;

    /**
     * 清洗数据方法
     * 
     * @Title: save
     * @param startCreateTime
     * @param endCreateTime
     *
     */
    public abstract void save(Date startCreateTime, Date endCreateTime);

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

}
