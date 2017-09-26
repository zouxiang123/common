/**   
 * @Title: IPatientKpiHistService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年4月5日 上午10:43:57 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.model.PatientKpiHist;
import com.xtt.common.dao.po.PatientKpiHistPO;

public interface IPatientKpiHistService {
    /**
     * 根据患者id查询某种类别最近七次变更记录
     * 
     * @Title: listLatest7
     * @param category
     *            类别
     * @param fkPatientId
     *            患者id
     * @param sysOwner
     *            所属系统
     * @return
     *
     */
    List<PatientKpiHistPO> listLatest7(String category, Long fkPatientId, String sysOwner);

    /**
     * 获取最近的一条数据
     * 
     * @Title: getLatest
     * @param category
     *            类别
     * @param fkPatientId
     *            患者id
     * @param sysOwner
     *            所属系统
     * @return
     *
     */
    PatientKpiHistPO getLatest(String category, Long fkPatientId, String sysOwner);

    /**
     * 保存数据
     * 
     * @Title: save
     * @param record
     *
     */
    void save(PatientKpiHistPO record);

    /**
     * 根据患者id和所属系统删除数据
     * 
     * @Title: removeByPatientId
     * @param fkPatientId
     * @param sysOwner
     *
     */
    void removeByPatientId(Long fkPatientId, String sysOwner);

    /**
     * 批量插入数据
     * 
     * @Title: insertBatch
     * @param records
     *
     */
    void insertBatch(List<PatientKpiHist> records);

}
