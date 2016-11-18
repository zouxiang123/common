/**   
 * @Title: IPatientService.java 
 * @Package com.xtt.txgl.patient.service
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
	 * @Title: getPatientByTenantId
	 * @param tenantId
	 * @return
	 * 
	 */
	public List<PatientPO> getPatientByTenantId(Integer tenantId, Boolean delFlag);

	/**
	 * 查询该租户的所有患者
	 * 
	 * @Title: getPatientByTenantId
	 * @param tenantId
	 * @return
	 * 
	 */
	public List<PatientPO> getAllPatientByTenantId(Integer tenantId);

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
	public PatientPO selectById(Long id);

	/**
	 * 根据条件查询患者集合
	 * 
	 * @Title: selectByCondition
	 * @param patent
	 * @return
	 * 
	 */
	List<PatientPO> selectByCondition(Patient patent);

	/**
	 * 根据身份证号查询患者信息
	 * 
	 * @Title: selectPatientByIdNumber
	 * @param patient
	 * @return
	 * 
	 */
	public PatientPO selectPatientByIdNumber(Patient patient);

	public void updateByPrimaryKeySelective(Patient patient);

	/**
	 * 更新是否删除标识
	 * 
	 * @Title: updateDelFlag
	 * @param id
	 *
	 */
	public void updateDelFlag(Long id);

	/**
	 * 查询未转归患者
	 * 
	 * @Title: selectByActive
	 * @return
	 *
	 */
	public List<PatientPO> selectByActive();
}
