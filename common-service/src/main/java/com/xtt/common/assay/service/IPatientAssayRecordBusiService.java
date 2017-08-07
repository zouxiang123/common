/**   
 * @Title: IDictHospitalLabService.java 
 * @Package com.xtt.common.patient
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月22日 下午12:54:10 
 *
 */
package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;

public interface IPatientAssayRecordBusiService {

    /**
     * 根据自定义条件查询常用项
     * 
     * @Title: listByCondition
     * @param query
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listByCondition(PatientAssayRecordBusiPO query);

    /**
     * 根据Inspection_Id 查询是否唯一
     * 
     * @Title: countByInspectionId
     * @param inspectionId
     * @return
     *
     */
    int countByInspectionId(String inspectionId);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param listPatientAssayRecordBusi
     *
     */
    void insertList(List<PatientAssayRecordBusi> listPatientAssayRecordBusi);

    /**
     * 根据条件更新透前透后标识
     * 
     * @Title: updateDiaAbFlagByReqId
     * @param patientAssayRecordBusi
     *
     */
    void updateDiaAbFlagByReqId(PatientAssayRecordBusi patientAssayRecordBusi);

}
