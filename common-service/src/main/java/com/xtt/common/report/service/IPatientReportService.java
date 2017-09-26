/**   
 * @Title: IPatientReportService.java 
 * @Package com.xtt.common.report.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年9月20日 上午10:18:09 
 *
 */
package com.xtt.common.report.service;

import java.util.List;
import java.util.Map;

public interface IPatientReportService {
    /**
     * 报表类别
     * 
     * @Title: listReportData
     * @param paraMap
     * @param reportType
     * @return
     *
     */
    Map<String, List<Map<String, Object>>> listReportData(Map<String, Object> paraMap, Integer reportType);

}
