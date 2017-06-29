/**   
 * @Title: IOutcomeService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月29日 上午10:42:18 
 *
 */
package com.xtt.common.patient.service;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.po.PatientOutcomePO;

public interface IPatientOutcomeService {

    /**
     * 保存转归记录
     * 
     * @Title: save
     * @param record
     * 
     */
    void save(PatientOutcomePO record);

    /**
     * 根据患者id查询所有转归记录
     * 
     * @Title: selectAllByPatientId
     * @param patientId
     * @return
     * 
     */
    List<PatientOutcomePO> listByPatientId(Long patientId);

    /**
     * 根据条件查询患者转归记录
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<PatientOutcomePO> selectByCondition(PatientOutcomePO record);

    /**
     * 查询最新的一条转归记录
     * 
     * @Title: listLatest
     * @param patientIds
     *            患者ids
     * @param month
     *            月份
     * @param multiTenant
     *            租户id
     * @param sysOwner
     *            所属系统
     * @return
     *
     */
    List<PatientOutcomePO> listLatest(Collection<Long> patientIds, String month, String multiTenant, String sysOwner);

}
