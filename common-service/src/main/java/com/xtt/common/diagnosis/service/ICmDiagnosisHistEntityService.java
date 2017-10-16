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
import java.util.Map;

import com.xtt.common.dao.po.CmDiagnosisEntityPO;
import com.xtt.common.dto.DiagnosisApiDto;

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

    /**
     * 获取患者最新一次的诊断字符串
     * 
     * @Title: getStrByPatientIds
     * @param param
     * @return {患者id：{诊断类别：对应的字符串}}
     *
     */
    Map<Long, Map<String, String>> getLatestStrByPatientIds(DiagnosisApiDto param);
}
