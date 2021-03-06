/**   
 * @Title: PatientAssayBackCommonService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月11日 下午1:15:56 
 *
 */
package com.xtt.common.assay.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.xtt.common.dao.model.PatientAssayReportCommon;
import com.xtt.common.dao.po.PatientAssayReportCommonPO;

public interface IPatientAssayReportCommonService {

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<PatientAssayReportCommon> list);

    /**
     * 删除数据
     * 
     * @Title: deleteByTenant
     * @param patientAssayBackCommon
     *
     */
    void deleteByTenant(Integer fkTenantId);

    /**
     * 根据月份查询
     */
    List<PatientAssayReportCommonPO> selectByAssayDate(PatientAssayReportCommonPO patientAssayReportCommon);

    /**
     * 根据患者标签/患者类型查询
     */

    List<PatientAssayReportCommonPO> selectByPatientLabel(PatientAssayReportCommonPO patientAssayReportCommon);

    /**
     * 根据化验项删除数据
     * 
     * @Title: deleteByItemCodes
     * @param listItemCode
     *
     */
    void deleteByItemCodes(List<String> listItemCode);

    /**
     * 保存数据
     * 
     * 
     * @Title: insertAuto
     * @param allPatientIds
     *            全部患者id
     * @param filterPatientIds
     *            过滤患者id
     * @param monthDate
     * @param tenantId
     * @param itemCodes
     *
     */
    void insertAuto(List<Long> allPatientIds, Set<Long> filterPatientIds, Date monthDate, Integer tenantId, Collection<String> itemCodes);

}
