/**   
 * @Title: PatientAssayBackCommonService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月11日 下午1:15:56 
 *
 */
package com.xtt.common.assay.service;

import java.util.Date;
import java.util.List;

import com.xtt.common.dao.model.PatientAssayBackCommon;
import com.xtt.common.dao.po.PatientAssayBackCommonPO;

public interface IPatientAssayBackCommonService {

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<PatientAssayBackCommon> list);

    /**
     * 删除数据
     * 
     * @Title: deleteByTenant
     * @param patientAssayBackCommon
     *
     */
    void deleteByTenant(PatientAssayBackCommon patientAssayBackCommon);

    /**
     * 根据月份查询
     */
    List<PatientAssayBackCommonPO> selectByAssayDate(PatientAssayBackCommonPO patientAssayBackCommon);

    /**
     * 根据患者标签/患者类型查询
     */

    List<PatientAssayBackCommonPO> selectByPatientLabel(PatientAssayBackCommonPO patientAssayBackCommon);

    /**
     * 根据化验项删除数据
     * 
     * @Title: deleteByItemCodes
     * @param listItemCode
     *
     */
    void deleteByItemCodes(List<String> listItemCode);

    /**
     * 更新常用化验项
     * 
     * @Title: updateItemCode
     * @param itemCodes
     * @param isdelete
     *
     */
    void updateItemCode(List<String> itemCodes, Boolean isdelete, Integer tenantId);

    /**
     * 保存数据
     * 
     * @param listItemCode
     * 
     * @Title: save
     * @param deletePatientId
     * @param monthDate
     * @param tenantId
     *
     */
    void save(List<String> listItemCode, List<Long> deletePatientId, Date monthDate, Integer tenantId);

}
