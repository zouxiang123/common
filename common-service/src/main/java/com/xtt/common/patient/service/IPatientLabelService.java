/**   
 * @Title: IPatientLabelService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2018年3月12日 下午4:41:35 
 *
 */
package com.xtt.common.patient.service;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.model.PatientLabel;
import com.xtt.common.dao.po.PatientLabelPO;

public interface IPatientLabelService {
    /**
     * 根据租户id查询患者标签
     * 
     * @Title: listByTenantId
     * @param tenantId
     * @return
     *
     */
    List<PatientLabelPO> listByTenantId(Integer tenantId);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientLabelPO> listByCondition(PatientLabelPO record);

    /**
     * 根据id删除数据
     * 
     * @Title: deleteById
     * @param id
     *
     */
    void deleteById(Long id);

    /**
     * 获取患者列表
     * 
     * @Title: listPatient
     * @param record
     * @return
     *
     */
    List<PatientLabelPO> listPatient(PatientLabelPO record);

    /**
     * 插入数据
     * 
     * @Title: insert
     * @param record
     *
     */
    void insert(PatientLabel record);

    /**
     * 建立患者和标签之间的关联
     * 
     * @Title: saveRef
     * @param patientIds
     * @param labelIds
     *
     */
    void saveRef(Collection<Long> patientIds, Collection<Long> labelIds);

    /**
     * 根据标签名称判断标签是否存在
     * 
     * @Title: checkNameExists
     * @param name
     *
     */
    boolean checkNameExists(String name);
}
