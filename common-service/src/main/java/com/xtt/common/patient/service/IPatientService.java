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

import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.po.PatientPO;

/**
 * @ClassName: IPatientService
 * @date: 2015年9月17日 上午10:01:26
 * @version: V1.0
 * 
 */
public interface IPatientService {
    /**
     * 患者登陆
     * 
     * @Title: login
     * @param account
     * @param password
     * @return
     *
     */
    public Patient login(String account, String password);

    /**
     * 验证身份证是否已存在
     * 
     * @Title: checkPatientExistByIdNumber
     * @param id
     * @param idNumber
     * @return
     * 
     */
    public boolean checkIdNumberExist(Long id, String idNumber);

    /**
     * 新增患者
     * 
     * @Title: savePatient
     * @param patient
     * @param isImport
     *            是否是导入患者
     * 
     */
    public void savePatient(Patient patient, boolean isImport);

    /**
     * 更新患者
     * 
     * @Title: savePatient
     * @param patient
     * 
     */
    public void updatePatient(Patient patient);

    /**
     * 查询该租户的所有患者
     * 
     * @Title: listByTenantId
     * @param tenantId
     *            租户id
     * @param sysOwner
     *            所属系统
     * @param isEnable
     *            患者在当前系统是否有效
     * @return
     * 
     */
    public List<PatientPO> listByTenantId(Integer tenantId, Boolean isEnable, String sysOwner);

    /**
     * 根据租户id,所属系统，是否有效获取患者条数
     * 
     * @Title: countPatient
     * @param tenantId
     * @param sysOwner
     * @param isEnable
     * @return
     *
     */
    public Integer countPatient(Integer tenantId, String sysOwner, Boolean isEnable);

    /**
     * @Title: selectById 根据主键查询患者信息
     * @param id
     * @return
     * 
     */
    public PatientPO getById(Long id);

    /**
     * 根据条件查询患者集合
     * 
     * @Title: selectByCondition
     * @param patent
     * @return
     * 
     */
    List<PatientPO> listByCondition(PatientPO patent);

    /**
     * 根据主键更新非空字段
     * 
     * @Title: updateByPrimaryKeySelective
     * @param patient
     *
     */
    public void updateByPrimaryKeySelective(Patient patient);

    /**
     * 查询所有患者
     * 
     * @Title: listAll
     * @return
     *
     */
    public List<PatientPO> listAll();

    /**
     * 判断手机号是否存在
     * 
     * @Title: checkMobileExist
     * @param mobile
     * @param neId
     *            不等于的患者id
     * @return exists :true:false
     *
     */
    public boolean checkMobileExist(String mobile, Long neId);

    /**
     * 根据患者证件号查询患者
     * 
     * @Title: getByIdNumber
     * @param idNumber
     * @return
     *
     */
    PatientPO getByIdNumber(String idNumber);

    /**
     * 通过手机号查询患者信息
     * 
     * @Title: listByMobile
     * @param mobile
     * @param neId
     * @return
     *
     */
    public List<PatientPO> listByMobile(String mobile, Long neId);
}
