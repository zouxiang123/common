/**   
 * @Title: IPatientService.java 
 * @Package com.xtt.txgl.patient.service
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月17日 上午10:01:26 
 *
 */
package com.xtt.common.patient;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.dao.po.PatientManagePO;
import com.xtt.common.dao.po.PatientPO;

/**
 * @ClassName: IPatientService
 * @date: 2015年9月17日 上午10:01:26
 * @version: V1.0
 * 
 */
public interface IPatientService {

	/**
	 * 查询指定患者信息
	 * 
	 * @Title: selectPatient
	 * @param id
	 * @return
	 * 
	 */
	public PatientPO selectPatient(Long id);

	/**
	 * 根据姓名模糊查询患者
	 * 
	 * @Title: selectPatientList
	 * @param name
	 * @return
	 * 
	 */
	public List<Patient> selectPatientList(String name);

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
	 * 
	 */
	public void savePatient(Patient patient);

	/**
	 * 更新患者
	 * 
	 * @Title: savePatient
	 * @param patient
	 * 
	 */
	public void updatePatient(Patient patient);

	/**
	 * 取得患者所有诊断信息
	 * 
	 * @Title: getDignosisByPatient
	 * @param patientId
	 *            患者ID
	 * @return
	 * 
	 */
	public Map<String, Object> getDignosisByPatient(Long patientId);

	/**
	 * 查询该租户的所有患者
	 * 
	 * @Title: getPatientByTenantId
	 * @param tenantId
	 * @return
	 * 
	 */
	public List<PatientPO> getPatientByTenantId(Integer tenantId);

	/**
	 * 获取患者数目
	 * 
	 * @Title: getPatientCount
	 * @param tenantId
	 * @return
	 * 
	 */
	public Integer getPatientCount(Integer tenantId);

	/**
	 * 
	 * @Title: selectById 根据主键查询患者信息
	 * @param id
	 * @return
	 * 
	 */
	PatientPO selectById(Long id);

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
	 * 根据临床诊断类型查询患者信息
	 * 
	 * @Title: selectPatientByCDRType
	 * @param type
	 * @return
	 * 
	 */
	List<PatientPO> selectPatientByCDRType(String CDRType);

	/**
	 * 根据活动id查询患者
	 * 
	 * @Title: getByDcId
	 * @param dcId
	 * @return
	 * 
	 */
	PatientPO getByDCId(Long dcId);

	/**
	 * 根据条件查询患者名单，用于患者管理页面
	 * 
	 * @Title: selectListForPatientManage
	 * @param patientManagePO
	 * @return
	 * 
	 */
	List<PatientManagePO> selectListForPatientManage(PatientManagePO patientManagePO);

	/**
	 * 根据身份证号查询患者信息
	 * 
	 * @Title: selectPatientByIdNumber
	 * @param patient
	 * @return
	 * 
	 */
	public PatientPO selectPatientByIdNumber(Patient patient);

	/**
	 * 根据各种组合条件查询患者信息
	 * 
	 * @Title: selectPatientEHR
	 * @param map
	 * @return
	 * 
	 */
	public List<PatientManagePO> selectPatientEHR(Map<String, Object> map);

	/**
	 * 根据患者身份证判断患者是否正确
	 * 
	 * @Title: selectPatientExistByIdNumber
	 * @param id
	 * @param idNumber
	 * @return
	 * 
	 */
	boolean selectPatientExistByIdNumber(Long id, String idNumber);

	/**
	 * 根据是否转归来查询患者信息列表
	 */
	List<PatientPO> selectPatientByDelFlag();

	/**
	 * 保存患者信息多个卡号信息
	 * 
	 * @Title: savePatientCard
	 * @param PatientCardPOList
	 * @return String msg
	 * 
	 */
	public String savePatientCard(List<PatientCardPO> patient);
}
