package com.xtt.common.dao.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.po.PatientManagePO;
import com.xtt.common.dao.po.PatientPO;

@Repository
public interface PatientMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Patient record);

	int insertSelective(Patient record);

	Patient selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Patient record);

	int updateByPrimaryKey(Patient record);

	/**
	 * 根据患者诊断主表ID查询患者信息
	 * 
	 * @Title: selectByPDId
	 * @param pdId
	 *            患者诊断主表ID
	 * @return PatientPO
	 * 
	 */
	PatientPO selectByPDId(Long pdId);

	/**
	 * 查询待排床的患者
	 * 
	 * @Title: selectForSickbed
	 * @param patient
	 * @return
	 * 
	 */
	List<PatientPO> selectForSickbed(PatientPO patient);

	/**
	 * 根据排床记录id查询当前的患者
	 * 
	 * @Title: selectBySrId
	 * @param patient
	 * @return
	 * 
	 */
	PatientPO selectBySrId(PatientPO patient);

	/**
	 * 查询待常规排床的患者
	 * 
	 * @Title: selectForSickbed
	 * @param patient
	 * @return
	 * 
	 */
	List<PatientPO> selectForSickbedTemplate(PatientPO patient);

	/**
	 * 根据常规排床记录id查询当前的患者
	 * 
	 * @Title: selectBySrId
	 * @param patient
	 * @return
	 * 
	 */
	PatientPO selectBySrtId(PatientPO patient);

	/**
	 * 
	 * @Title: selectForDrugStock 查询存在库存的患者列表
	 * @return
	 * 
	 */
	public List<PatientPO> selectForDrugStock(Patient patient);

	/**
	 * 查询该租户的所有患者
	 * 
	 * @Title: selectPatientByTenantId
	 * @param tenantId
	 * @return
	 * 
	 */
	List<PatientPO> selectPatientByTenantId(Integer tenantId);

	/**
	 * 
	 * @Title: 查询用户今天参与过的透析活动的患者数据
	 * @param userId
	 * 
	 * @return
	 * 
	 */
	List<PatientPO> selectTodayUserDCOperatedPatients(@Param("userId") Long userId, @Param("currentDate") Date currentDate);

	/**
	 * 
	 * @Title: 查询用户今天诊断过的患者数据
	 * @param userId
	 * @param ids
	 *            已经存在的患者id
	 * 
	 * @return
	 * 
	 */
	List<PatientPO> selectTodayUserDiagnosePatients(@Param("userId") Long userId, @Param("ids") List<Long> ids,
					@Param("currentDate") Date currentDate);

	/**
	 * 
	 * @Title: 查询用户今天建立过通路的患者数据
	 * @param userId
	 * @param ids
	 *            已经存在的患者id
	 * 
	 * @return
	 * 
	 */
	List<PatientPO> selectTodayUserBuildPathwayPatients(@Param("userId") Long userId, @Param("ids") List<Long> ids,
					@Param("currentDate") Date currentDate);

	/**
	 * 查询该租户的所有患者数目
	 * 
	 * @Title: selectPatientByTenantId
	 * @param tenantId
	 * @return
	 * 
	 */
	Integer selectPatientCount(Integer tenantId);

	/**
	 * 
	 * @Title: selectById 根据主键查询患者信息
	 * @param id
	 * @return
	 * 
	 */
	PatientPO selectById(Long id);

	/**
	 * 根据年月日 获取当前用户制定过处方的数据
	 * 
	 * @param dateType
	 * 
	 * @Title: getMakePrescriptionReport
	 * @param userId
	 * @param startDate
	 * 
	 */
	List<Map<String, Object>> selectMakePrescriptionReport(@Param("dateType") int dateType, @Param("userId") Long userId,
					@Param("startDate") Date startDate);

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
	 * @param patent
	 * @return
	 * 
	 */
	List<PatientPO> selectPatientByCDRType(Patient patent);

	/**
	 * 查询当天，已排床、待透析活动建立的患者
	 * 
	 * @param shiftNumber
	 * 
	 * @Title: selectPatientByStartCampaign
	 * @param patent
	 * @return
	 * 
	 */
	List<PatientPO> selectPatientByStartCampaign(@Param("fkTenantId") Integer fkTenantId, @Param("currentDate") Date currentDate,
					@Param("shiftNumber") Integer shiftNumber);

	/**
	 * 查询当天，已排床、待透析活动建立的患者 数目 和患者头像
	 * 
	 * @Title: selectPatientByStartCampaignCount
	 * @param patent
	 * @return
	 * 
	 */
	Map<String, Object> selectPatientByStartCampaignCount(@Param("fkTenantId") Integer fkTenantId, @Param("currentDate") Date currentDate,
					@Param("shiftNumber") Integer shiftNumber);

	/**
	 * 
	 * @Title: updateDelFlag
	 * @param id
	 * 
	 */
	void updateDelFlag(Long id);

	Integer checkPatientExistByIdNumber(@Param("id") Long id, @Param("idNumber") String idNumber, @Param("fkTenantId") Integer tenantId);

	/**
	 * 根据活动id查询患者
	 * 
	 * @Title: selectByDCId
	 * @param dcId
	 * @param tenantId
	 * @return
	 * 
	 */
	PatientPO selectByDCId(@Param("dcId") Long dcId, @Param("tenantId") Integer tenantId);

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
	 * 根据条件查询通路患者列表信息
	 * 
	 * @Title: selectPathwayPatientList
	 * @param patientManagePO
	 * @return
	 * 
	 */
	List<PatientManagePO> selectPathwayPatientList(Map<String, Object> map);

	/**
	 * 修改患者余额
	 * 
	 * @Title: updateBalance
	 * @param patient
	 * 
	 */
	void updateBalance(Patient patient);

	/**
	 * 根据主键查询患者
	 * 
	 * @Title: findById
	 * @param id
	 * @return
	 * 
	 */
	Patient findById(Long id);

	/**
	 * 根据身份证号查询患者信息
	 * 
	 * @Title: selectPatientByIdNumber
	 * @param patient
	 * @return
	 * 
	 */
	List<PatientPO> selectPatientByIdNumber(Patient patient);

	/**
	 * 根据各种组合条件查询患者信息
	 * 
	 * @Title: selectPatientEHR
	 * @param dynmaicSql
	 * @return
	 * 
	 */
	List<PatientManagePO> selectPatientEHR(Map<String, String> customeCondition);

	/*
	 * 根据条件查询药品耗材对应的患者
	 * 
	 * @Title: selectByDrugStock
	 * 
	 * @param patient
	 * 
	 * @return
	 */
	List<PatientPO> selectByDrugStock(Patient patient);

	/**
	 * 根据患者ID判断患者是否正确
	 * 
	 * @Title: selectPatientExistByIdNumber
	 * @param id
	 * @param idNumber
	 * @param tenantId
	 * @return
	 * 
	 */
	Integer selectPatientExistByIdNumber(@Param("id") Long id, @Param("idNumber") String idNumber, @Param("fkTenantId") Integer tenantId);

	/**
	 * 根据是否转归来查询患者名单
	 */
	List<PatientPO> selectPatientByDelFlag();
}
