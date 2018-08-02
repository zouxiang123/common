/**   
 * @Title: IReportExceptionService.java 
 * @Package com.xtt.txgl.report.service
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2017年6月6日 上午10:39:53 
 *
 */
package com.xtt.common.report.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xtt.common.dao.model.ReportExConfig;
import com.xtt.common.dao.po.ReportExAssayPo;

/**
 * 异常报表统计 相关业务处理
 * 
 * @ClassName: IReportExceptionService
 * @date: 2017年6月6日 上午10:39:58
 * @version: V1.0
 *
 */
public interface IReportExceptionService {

    /**
     * 根据日期查询化验结果集
     * 
     * @Title: listExAssayByPatientDeital
     * @param exAssay
     * @return
     *
     */
    public List<Map> listExAssayByPatient(ReportExAssayPo exAssay);

    /**
     * 根据患者化验结果详细
     * 
     * @Title: listExAssayByPatientDeital
     * @param exAssay
     * @return
     *
     */
    public List<ReportExAssayPo> listExAssayByPatientDeital(ReportExAssayPo exAssay);

    /**
     * 根据传入日期自动生成异常提醒-化验结果 <br/>
     * 化验标准数据，以化验时间为标准有效时间 <br/>
     * 
     * @Title: insertAutoGenerateExAssay
     * @param dateDay
     *            日期[yyyy-mm-dd]
     * @param tenantId
     *            租户ID
     * 
     */
    public void insertAutoGenerateExAssay(String dateDay, Integer tenantId);

    /**
     * 根据传入日期自动生成异常提醒-化验结果 <br/>
     * 化验标准数据，以化验时间为标准有效时间 <br/>
     * 
     * @Title: insertAutoGenerateExAssay
     * @param dateDay
     *            日期[yyyy-mm-dd]
     * @param tenantId
     *            租户ID
     * 
     */
    public List<ReportExConfig> listByReportExConfig(ReportExConfig config);

    /**
     * 异常提醒_化验结果 删除指定日期的所有数据
     * 
     * @Title: deleteByReportExAssayByReportDate
     * @param reportDate
     * @param tenantId
     *
     */
    public void deleteByReportExAssayByReportDate(String reportDate, Integer tenantId);

    /**
     * 保存异常提醒配置
     * 
     * @Title: insertReportExConfig
     * @param config
     *
     */
    public void insertReportExConfig(ReportExConfig config);

    /**
     * 修改异常提醒配置状态
     * 
     * @Title: updateByReportExConfigStatus
     * @param recArrays
     * @param tenantId
     *
     */
    public void updateByReportExConfigStatus(List<ReportExConfig> recArrays, Integer status, Integer tenantId);

    /**
     * 获取化验结果 assay_date 的最小日期和最大日期<br/>
     * 如果返回内容null表示没有找到有效化验结果或者血透项目刚刚部署
     * 
     * @Title: getByAssayStartTime
     * @return [{startDate:最小日期},{endDate:最大日期}]
     *
     */
    public Map<String, Date> getByAssayStartTime(Integer tenantId);

    /**
     * 用于手动调用异常提醒_检验结果
     * 
     * @param tenantId
     * @Title: executeAssayResultByTenantId
     *
     */
    public void executeAssayResultByTenantId(Integer tenantId);

}
