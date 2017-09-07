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

}