package com.xtt.common.assay.service;

import java.util.Date;

import com.xtt.common.dao.model.PatientAssayConf;
import com.xtt.common.dao.po.PatientAssayConfPO;

public interface IPatientAssayConfService {

    /**
     * 查询化验配置
     */
    PatientAssayConf selectByTenantId(Integer tenantId);

    /**
     * 修改时间配置
     */
    void update(PatientAssayConf patientAssayConf);

    /**
     * 新增
     */
    void insert(PatientAssayConf patientAssayConf);

    /**
     * 根据租户查询和月份查询月份的开始时间和结束时间
     * 
     * @Title: selectDateScope
     * @param tenantId
     * @return
     * 
     */
    PatientAssayConfPO selectDateScopeByMonth(String monthAndYear, Integer tenantId);

    /**
     * 根据租户查询和日期，查询日期对应的月份的开始时间和结束时间
     * 
     * @Title: selectDateScope
     * @param tenantId
     * @return
     * 
     */
    PatientAssayConfPO selectDateScopeByDate(Date date, Integer tenantId);

    /**
     * 根据日期，获取日期对应的月份
     * 
     * @Title: selectMonthAndYearByDate
     * @return
     * 
     */
    String selectMonthAndYearByDate(Date date, Integer tenantId, PatientAssayConf conf);
}
