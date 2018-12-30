/**   
 * @Title: ReportExceptionServiceImpl.java 
 * @Package com.xtt.txgl.report.service.impl
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年6月6日 上午11:15:02 
 *
 */
package com.xtt.common.report.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.AssayConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientAssayClassMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordBusiMapper;
import com.xtt.common.dao.mapper.ReportExAssayMapper;
import com.xtt.common.dao.mapper.ReportExConfigMapper;
import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.model.ReportExAssay;
import com.xtt.common.dao.model.ReportExConfig;
import com.xtt.common.dao.po.PatientAssayClassPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;
import com.xtt.common.dao.po.ReportExAssayPo;
import com.xtt.common.report.service.IReportExceptionService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.CollectionUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class ReportExceptionServiceImpl implements IReportExceptionService {

    @Autowired
    private ReportExAssayMapper reportExAssayMapper;
    @Autowired
    private PatientAssayClassMapper patientAssayClassMapper;
    @Autowired
    private PatientAssayRecordBusiMapper patientAssayRecordBusiMapper;
    @Autowired
    private ReportExConfigMapper reportExConfigMapper;

    /**
     * 根据传入日期自动生成异常提醒-化验结果 <br/>
     * 化验标准数据，以化验时间为标准有效时间 <br/>
     * 
     * @Title: insertAutoGenerateExWeight
     * @param dateDay
     *            日期[yyyy-mm-dd]
     * @param tenantId
     *            租户ID
     * 
     */
    @Override
    public void insertAutoGenerateExAssay(String dateDay, Integer tenantId) {
        if (StringUtil.isEmpty(dateDay) || tenantId == null) {
            return;
        }
        Date assayDate = DateUtil.parseDate(dateDay, DateFormatUtil.FORMAT_DATE1);
        // 获取所有提醒化验项目
        PatientAssayClass assayClassQuery = new PatientAssayClass();
        assayClassQuery.setFkTenantId(tenantId);
        List<PatientAssayClassPO> assayClassLs = patientAssayClassMapper.listByCondition(assayClassQuery);

        if (CollectionUtil.isNotEmpty(assayClassLs)) {
            for (PatientAssayClassPO assayClass : assayClassLs) {
                // 获取指定日期,化验项目的化验数据
                PatientAssayRecordBusiPO assayRecordBusiPO = new PatientAssayRecordBusiPO();
                assayRecordBusiPO.setFkTenantId(tenantId);
                assayRecordBusiPO.setAssayDate(assayDate);
                assayRecordBusiPO.setFkDictUk(assayClass.getItemCode());
                List<PatientAssayRecordBusiPO> assayRecordBusiLs = patientAssayRecordBusiMapper.listByCondition(assayRecordBusiPO);
                if (CollectionUtil.isNotEmpty(assayRecordBusiLs)) {
                    for (PatientAssayRecordBusiPO assay : assayRecordBusiLs) {
                        // 是否存在异常情况
                        boolean conditionExFlag = false;
                        // 本次化验结果
                        double thisResultActual = 0;
                        if (assay.getResultActual() != null) {
                            thisResultActual = assay.getResultActual();
                        }
                        if (thisResultActual != 0) {

                            // 获取上次化验结果
                            double beforeResultActual = 0;
                            PatientAssayRecordBusi busiRecord = new PatientAssayRecordBusi();
                            busiRecord.setFkTenantId(tenantId);
                            busiRecord.setFkPatientId(assay.getFkPatientId());
                            busiRecord.setGroupId(assay.getGroupId());
                            busiRecord.setItemCode(assayClass.getItemCode());
                            busiRecord.setAssayDate(assayDate);
                            List<PatientAssayRecordBusi> beforeAssayRecord = patientAssayRecordBusiMapper.getByNewestAssayRecordBusi(busiRecord);

                            if (beforeAssayRecord != null && beforeAssayRecord.size() > 0) {
                                if (beforeAssayRecord.get(0).getResultActual() != null) {
                                    beforeResultActual = beforeAssayRecord.get(0).getResultActual();
                                }
                            }
                            if (beforeResultActual != 0) {
                                ReportExAssay reportExAssay = new ReportExAssay();
                                reportExAssay.setPreResultActual(thisResultActual);
                                reportExAssay.setFkPatientAssayRecordBusiId(assay.getId());
                                reportExAssay.setGroupId(assay.getGroupId());
                                reportExAssay.setGroupName(assay.getGroupName());
                                reportExAssay.setItemCode(assay.getItemCode());
                                reportExAssay.setItemName(assay.getItemName());
                                reportExAssay.setReqId(assay.getReqId());
                                reportExAssay.setReportDate(assay.getAssayDate());
                                reportExAssay.setReportDateMonth(assay.getAssayMonth());

                                // 是否小于下限值
                                if (thisResultActual != 0 && assayClass.getMinValue() != null && assayClass.getMinValue() != 0) {
                                    if (thisResultActual < assayClass.getMinValue()) {
                                        reportExAssay.setMinValue(assayClass.getMinValue());
                                        conditionExFlag = true;
                                    }
                                }
                                // 是否大于上限值
                                if (thisResultActual != 0 && assayClass.getMaxValue() != null && assayClass.getMaxValue() != 0) {
                                    if (thisResultActual > assayClass.getMaxValue()) {
                                        reportExAssay.setMaxValue(assayClass.getMaxValue());
                                        conditionExFlag = true;
                                    }
                                }
                                // 相差值+-|本次检验值—上次检验值|
                                if (thisResultActual != 0 && assayClass.getDifferenceValue() != null && assayClass.getDifferenceValue() != 0) {
                                    double differenceValue = thisResultActual - beforeResultActual;
                                    if (differenceValue >= assayClass.getDifferenceValue() || differenceValue <= -assayClass.getDifferenceValue()) {
                                        reportExAssay.setDifferenceValue(assayClass.getDifferenceValue());
                                        conditionExFlag = true;
                                    }
                                }
                                // 相差百分比+-|(本次检验值—上次检验值)/100|
                                if (thisResultActual != 0 && assayClass.getPercentageValue() != null && assayClass.getPercentageValue() != 0) {
                                    double percentageValue = (thisResultActual - beforeResultActual) / 100;
                                    if (percentageValue >= assayClass.getPercentageValue() || percentageValue <= -assayClass.getPercentageValue()) {
                                        reportExAssay.setPercentageValue(assayClass.getPercentageValue());
                                        conditionExFlag = true;
                                    }
                                }
                                // 保存导常数据
                                if (conditionExFlag) {
                                    reportExAssay.setIsEx(conditionExFlag ? AssayConsts.REPORT_EX_TRUE : AssayConsts.REPORT_EX_FALSE);
                                    reportExAssay.setFkTenantId(tenantId);
                                    reportExAssay.setFkPatientId(assay.getFkPatientId());
                                    reportExAssay.setBeforeResultActual(beforeResultActual);
                                    DataUtil.setSystemFieldValue(reportExAssay);
                                    reportExAssayMapper.insert(reportExAssay);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public List<ReportExAssayPo> listExAssayByPatientDeital(ReportExAssayPo exAssay) {
        if (exAssay != null) {
            exAssay.setFkTenantId(UserUtil.getTenantId());
        }
        return reportExAssayMapper.listExAssayByPatientDeital(exAssay);
    }

    @Override
    public List<Map> listExAssayByPatient(ReportExAssayPo exAssay) {
        if (exAssay != null) {
            exAssay.setFkTenantId(UserUtil.getTenantId());
        }
        List<Map> exAssayLs = reportExAssayMapper.listExAssayByPatient(exAssay);
        if (CollectionUtil.isNotEmpty(exAssayLs)) {

            exAssayLs.forEach((Map map) -> {
                Long fkPatientId = (Long) map.get("fkPatientId");
                exAssay.setFkPatientId(fkPatientId);
                List<ReportExAssayPo> assayPos = reportExAssayMapper.listExAssayByPatientDeital(exAssay);
                map.put("errmsg", getErrMsg(assayPos));
            });
        }
        return exAssayLs;
    }

    private String getErrMsg(List<ReportExAssayPo> assayPos) {
        StringBuffer errmsg = new StringBuffer();
        Date befoAssayDate = new Date();
        for (ReportExAssayPo po : assayPos) {

            StringBuffer msg = new StringBuffer();
            if (po.getMinValue() != null && po.getMinValue() != 0) {
                msg.append(po.getItemName() + "<" + po.getMinValue() + "，");
            }
            if (po.getMaxValue() != null && po.getMaxValue() != 0) {
                msg.append(po.getItemName() + ">" + po.getMaxValue() + "，");
            }
            if (po.getDifferenceValue() != null && po.getDifferenceValue() != 0) {
                msg.append(po.getItemName() + "较前次变化超过+/- " + po.getDifferenceValue() + "，");
            }
            if (po.getPercentageValue() != null && po.getPercentageValue() != 0) {
                msg.append(po.getItemName() + "较前次变化超过+/- " + po.getPercentageValue() + "%，");
            }
            if (DateUtil.getDays(befoAssayDate, po.getReportDate()) != 0 && StringUtils.isNotEmpty(msg.toString())) {
                errmsg.append(DateUtil.format(po.getReportDate(), DateFormatUtil.FORMAT_DATE1) + "：");
            }
            errmsg.append(msg.toString());
            befoAssayDate = po.getReportDate();
        }
        return errmsg.toString().length() > 0 ? errmsg.toString().substring(0, errmsg.toString().length() - 1) : "";
    }

    /**
     * 用于手动调用异常提醒_检验结果
     * 
     * @param tenantId
     * @Title: executeAssayResultByTenantId
     *
     */
    @Override
    public void executeAssayResultByTenantId(Integer tenantId) {
        try {
            // 获取当前日期
            String currentDate = DateFormatUtil.convertDateToStr(DateUtil.getCurrDate(), DateFormatUtil.FORMAT_DATE1);
            boolean initAllFlag = false;
            // 初始化所有数据 ，修改配置参数了
            // 判断是否修改过 [化验项目提醒字典]参数，初始化所有数据
            ReportExConfig paramConfig = new ReportExConfig();
            paramConfig.setExType(AssayConsts.REPORT_EX_ASSAY);
            paramConfig.setStatus(AssayConsts.REPORT_EX_STATUS_INIT);
            paramConfig.setFkTenantId(tenantId);
            List<ReportExConfig> pecs = listByReportExConfig(paramConfig);
            if (CollectionUtil.isNotEmpty(pecs)) {
                initAllFlag = true;
                // 参数状态设置为1 正在处理中
                updateByReportExConfigStatus(pecs, AssayConsts.REPORT_EX_STATUS_RUN, tenantId);
                // 获取化验最小日期和最大日期
                Map<String, Date> dcStartTimeMap = getByAssayStartTime(tenantId);
                if (dcStartTimeMap != null && dcStartTimeMap.size() > 0) {
                    // 最小日期和最大日期
                    Date startDate = dcStartTimeMap.get("startDate");
                    Date endDate = dcStartTimeMap.get("endDate");
                    // 判断化验结果日期
                    if (startDate != null && endDate != null && startDate.getTime() != endDate.getTime()) {
                        // 计算相差天数
                        int days = DateUtil.getDays(endDate, startDate);
                        for (int i = 0; i < days; i++) {
                            String paramDate = DateFormatUtil.convertDateToStr(DateUtil.addDays(startDate, i), DateFormatUtil.FORMAT_DATE1);
                            if (paramDate.equals(currentDate)) {
                                break;
                            }
                            // 删除指定日期数据
                            deleteByReportExAssayByReportDate(paramDate, tenantId);
                            // 生成指定日期初始化数据
                            insertAutoGenerateExAssay(paramDate, tenantId);
                        }
                    }
                }
                // 任务已处理完成
                updateByReportExConfigStatus(pecs, AssayConsts.REPORT_EX_STATUS_INSTALL, tenantId);
            }
            // 判断每月1号，重新初始化上月化验数据
            // 是否执行过按月处理
            boolean monthFlag = false;
            if (!initAllFlag) {
                // 获取当前日期月份第一天
                Calendar currentMonthDate = Calendar.getInstance();// 获取当前日期
                currentMonthDate.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
                String methodFirstDay = DateFormatUtil.convertDateToStr(currentMonthDate.getTime());
                // 判断当前日期与当月第一天是否相同，如果相同进行按月生成数据
                if (currentDate.equals(methodFirstDay)) {
                    monthFlag = true;
                    // 上月日期
                    currentMonthDate.add(Calendar.MONTH, -1);// 设置上月
                    currentMonthDate.set(Calendar.DAY_OF_MONTH, 1);
                    // 上月第一天
                    methodFirstDay = DateFormatUtil.convertDateToStr(currentMonthDate.getTime());
                    for (int i = 0; i < 31; i++) {
                        String paramDate = DateFormatUtil.convertDateToStr(DateUtil.addDays(DateFormatUtil.convertStrToDate(methodFirstDay), i),
                                        DateFormatUtil.FORMAT_DATE1);
                        if (paramDate.equals(currentDate)) {
                            break;
                        }
                        // 删除指定日期数据
                        deleteByReportExAssayByReportDate(paramDate, tenantId);
                        // 生成指定日期初始化数据
                        insertAutoGenerateExAssay(paramDate, tenantId);
                    }
                }
            }
            // 每日更新前一天化验数据
            if (!initAllFlag && !monthFlag) {
                String paramDate = DateFormatUtil.convertDateToStr(DateUtil.addDays(DateFormatUtil.convertStrToDate(currentDate), -1),
                                DateFormatUtil.FORMAT_DATE1);
                // 删除指定日期数据
                deleteByReportExAssayByReportDate(paramDate, tenantId);
                // 生成指定日期初始化数据
                insertAutoGenerateExAssay(paramDate, tenantId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 定时发生异常记录异常信息
            ReportExConfig config = new ReportExConfig();
            config.setBeforeConfigJson(e.getMessage());
            config.setAfterConfigJson(e.getMessage());
            config.setCreateTime(new Date());
            config.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
            config.setUpdateTime(new Date());
            config.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
            config.setStatus(10);
            config.setExType(10);
            config.setFkTenantId(tenantId);
            insertReportExConfig(config);
        }
    }

    @Override
    public List<ReportExConfig> listByReportExConfig(ReportExConfig config) {
        return reportExConfigMapper.listByCondition(config);
    }

    /**
     * 异常提醒_化验结果 删除指定日期的所有数据
     * 
     * @Title: deleteByReportExWeightAndReportDate
     * @param reportDate
     * @param tenantId
     *
     */
    @Override
    public void deleteByReportExAssayByReportDate(String reportDate, Integer tenantId) {
        reportExAssayMapper.deleteByCondition(reportDate, tenantId);
    }

    @Override
    public void insertReportExConfig(ReportExConfig config) {
        reportExConfigMapper.insertSelective(config);
    }

    @Override
    public void updateByReportExConfigStatus(List<ReportExConfig> recArrays, Integer status, Integer tenantId) {
        reportExConfigMapper.updateByStatus(recArrays, status, tenantId);
    }

    /**
     * 获取化验结果 assay_date 的最小日期和最大日期<br/>
     * 如果返回内容null表示没有找到有效化验结果或者血透项目刚刚部署
     * 
     * @Title: getByAssayStartTime
     * @return [{startDate:最小日期},{endDate:最大日期}]
     */
    @Override
    public Map<String, Date> getByAssayStartTime(Integer tenantId) {
        return reportExAssayMapper.getByAssayStartTime(tenantId);
    }

}
