/**   
 * @Title: IPatientOwnerService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月3日 下午7:19:11 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.model.PatientOwner;

public interface IPatientOwnerService {

    void insert(PatientOwner record);

    /**
     * 更新患者归属信息
     * 
     * @Title: updateOwner
     * @param patient
     *
     */
    void updateOwner(PatientOwner record);

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<PatientOwner> selectByCondition(PatientOwner record);

}
