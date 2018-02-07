/**   
 * @Title: PatientAssayBackInspectioidService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月10日 上午11:15:23 
 *
 */
package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayInspectioidBack;

public interface IPatientAssayInspectioidBackService {

    void insertList(List<PatientAssayInspectioidBack> list);

    /**
     * 根据患者id查询
     * 
     * @Title: selectByPatientId
     * @param record
     * @return
     *
     */
    List<PatientAssayInspectioidBack> listByPatientId(PatientAssayInspectioidBack record);

    /**
     * 根据申请单和租户查询当前申请单的条数
     * 
     * @Title: countByInspectionId
     * @param inspectionId
     * @param fkTenantId
     * @return
     *
     */
    int countByInspectionId(String inspectionId, Integer fkTenantId);

    /**
     * 根据化验单唯一标识删除数据
     * 
     * @Title: deleteByInspectionId
     * @param inspectionId
     * @param fkPatientId
     * @param fkTenantId
     *
     */
    void deleteByInspectionId(String inspectionId, Long fkPatientId, Integer fkTenantId);

    /**
     * 插入数据
     * 
     * @Title: insert
     * @param record
     *
     */
    void insert(PatientAssayInspectioidBack record);

}
