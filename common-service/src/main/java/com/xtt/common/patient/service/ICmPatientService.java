/**   
 * @Title: IPatientService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月17日 上午10:01:26 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.model.CmPatient;
import com.xtt.common.dao.po.CmPatientPO;

/**
 * @ClassName: IPatientService
 * @date: 2015年9月17日 上午10:01:26
 * @version: V1.0
 * 
 */
public interface ICmPatientService {
    /**
     * 患者登陆
     * 
     * @Title: login
     * @param account
     * @param password
     * @return
     *
     */
    public CmPatient login(String account, String password);

    /**
     * 验证身份证是否已存在
     * 
     * @Title: checkPatientExistByIdNumber
     * @param id
     * @param idNumber
     * @return
     * 
     */
    public boolean checkPatientExistByIdNumber(Long id, String idNumber);

    /**
     * 新增患者
     * 
     * @Title: savePatient
     * @param patient
     * @param isImport
     *            是否是导入患者
     * 
     */
    public void savePatient(CmPatient patient, boolean isImport);

    /**
     * 更新患者
     * 
     * @Title: savePatient
     * @param patient
     * 
     */
    public void updatePatient(CmPatient patient);

    /**
     * 查询该租户的所有患者
     * 
     * @Title: getPatientByTenantId
     * @param tenantId
     * @return
     * 
     */
    public List<CmPatientPO> getPatientByTenantId(Integer tenantId, Boolean delFlag);

    /**
     * 查询该租户的所有患者
     * 
     * @Title: getPatientByTenantId
     * @param tenantId
     * @return
     * 
     */
    public List<CmPatientPO> getAllPatientByTenantId(Integer tenantId);

    /**
     * 获取当前租户下所有患者数目
     * 
     * @Title: getPatientCount
     * @param tenantId
     * @return
     * 
     */
    public Integer getPatientCount(Integer tenantId);

    /**
     * @Title: selectById 根据主键查询患者信息
     * @param id
     * @return
     * 
     */
    public CmPatientPO selectById(Long id);

    /**
     * 根据条件查询患者集合
     * 
     * @Title: selectByCondition
     * @param patent
     * @return
     * 
     */
    List<CmPatientPO> selectByCondition(CmPatient patent);

    /**
     * 根据身份证号查询患者信息
     * 
     * @Title: selectPatientByIdNumber
     * @param patient
     * @return
     * 
     */
    public CmPatientPO selectPatientByIdNumber(CmPatient patient);

    public void updateByPrimaryKeySelective(CmPatient patient);

    /**
     * 查询未转归患者
     * 
     * @Title: selectByActive
     * @return
     *
     */
    public List<CmPatientPO> selectByActive();

    /**
     * 更新当前租户下患者类别标识(住院还是随访)
     * 
     * @Title: updatePatientType
     * @param tenantId
     *
     */
    public void updatePatientType(Integer tenantId);
}
