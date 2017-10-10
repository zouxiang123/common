/**   
 * @Title: ICmDiagnosisHistSurgeryService.java 
 * @Package com.xtt.common.diagnosis.service
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:37:23 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;

import com.xtt.common.dao.po.CmDiagnosisEntityPO;

public interface ICmDiagnosisHistEntityService {

    /**
     * 根据Id获取诊断之选项数据
     * 
     * @param id
     * @return
     */
    CmDiagnosisEntityPO selectById(Long id);

    /**
     * 根据患者Id获取患者诊断之选项集合数据
     * 
     * @Title: selectEntitiesByPatient
     * @param entity
     * @return
     *
     */
    List<CmDiagnosisEntityPO> selectEntitiesByPatient(CmDiagnosisEntityPO entity);

    /**
     * 保存item数据
     * 
     * @Title: saveItem
     * @param record
     * @return status
     *
     */
    String saveItem(CmDiagnosisEntityPO record);

    /**
     * 根据id删除数据
     * 
     * @Title: deleteById
     * @param id
     * @return status
     *
     */
    String deleteById(Long id);
}
