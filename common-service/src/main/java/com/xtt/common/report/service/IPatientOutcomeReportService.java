/**   
 * @Title: IReportPatientOutcomeService.java 
 * @Package com.xtt.txgl.report.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年3月28日 下午6:57:21 
 *
 */
package com.xtt.common.report.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.PatientOutcomeReport;
import com.xtt.common.dao.po.ReportParameterPO;
import com.xtt.common.dao.po.TotPO;

public interface IPatientOutcomeReportService {
    /**
     * 根据自定义添加查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientOutcomeReport> listByCondition(PatientOutcomeReport record);

    /**
     * 获取转归患者报表
     * 
     * @Title: getOutComeReport
     * @param reportParameterPO
     * @return
     *
     */
    public Map<String, Object> getReportData(ReportParameterPO reportParameterPO);

    /**
     * 获取当前年份之前的所有转归患者
     * 
     * @Title: getTotList
     * @param yearStr
     * @return
     *
     */
    List<TotPO> listTotByYear(String yearStr);

}
