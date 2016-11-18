/**   
 * @Title: PatientServiceImpl.java 
 * @Package com.xtt.txgl.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月17日 上午10:02:40 
 *
 */
package com.xtt.common.patient.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayResultService;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CmDictConstants;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientDiagnosisMapper;
import com.xtt.common.dao.mapper.PatientHistoryMapper;
import com.xtt.common.dao.mapper.PatientMapper;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientHistory;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.CmDictUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.QRCode.QRCodeUtil;
import com.xtt.platform.util.SpellUtil;

/**
 * @ClassName: PatientServiceImpl
 * @date: 2015年9月17日 上午10:02:40
 * @version: V1.0
 * 
 */
@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private PatientHistoryMapper patientHistoryMapper;
	@Autowired
	PatientDiagnosisMapper patientDiagnosisMapper;
	@Autowired
	private IPatientAssayResultService patientAssayResultService;
	@Autowired
	ICommonService commonService;

	/**
	 * 更新缓存数据
	 * 
	 * @Title: updateCache
	 * @param id
	 *
	 */
	private void updateCache(Long id) {
		// update cache
		PatientPO p = selectById(id, false);
		PatientDto patientDto = new PatientDto();
		BeanUtils.copyProperties(p, patientDto);
		PatientCache.refresh(patientDto);
	}

	@Override
	public void savePatient(Patient patient, boolean isImport) {
		patient.setFkTenantId(UserUtil.getLoginUser().getTenantId());
		DataUtil.setSystemFieldValue(patient);
		Patient prePatient = null;
		if (patient.getId() != null) {
			prePatient = this.selectById(patient.getId());
		}
		// 新建或修改了姓名才生成首字母
		if (prePatient == null && StringUtils.isNotBlank(patient.getName())
						|| prePatient != null && !prePatient.getName().equals(patient.getName())) {
			patient.setName(patient.getName().trim());
			String spellInitials = SpellUtil.getSpellInitials(patient.getName().trim());
			if (StringUtils.isNotEmpty(spellInitials)) {
				patient.setInitial(spellInitials.substring(0, 1).toUpperCase());
				patient.setSpellInitials(spellInitials);
			}
		}
		if (patient.getId() == null) {
			patient.setDelFlag(false);
			patientMapper.insert(patient);
			// 插入传染病标识数据
			insertAssayResult(patient.getId(), isImport);
		} else {
			patient.setCreateTime(null);
			patient.setCreateUserId(null);
			patientMapper.updateByPrimaryKeySelective(patient);
		}
		// 上传头像
		String newFilename = "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT
						+ "/" + patient.getId() + ".png";
		// 首次创建头像
		if (StringUtils.isBlank(patient.getImagePath()) && prePatient == null) {
			// commonService.notifyDB();
			String name = patient.getName().length() >= 2 ? patient.getName().substring(patient.getName().length() - 2) : patient.getName();
			BusinessCommonUtil.combineImage(name, newFilename);
			patient.setImagePath(newFilename);
			patientMapper.updateByPrimaryKeySelective(patient);
		}
		String path = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH;
		// 修改头像
		if (!newFilename.equals(patient.getImagePath())) {
			com.xtt.platform.util.io.FileUtil.rename(new File(path + patient.getImagePath()), patient.getId() + ".png");
			patient.setImagePath(newFilename);
			patientMapper.updateByPrimaryKeySelective(patient);
		}

		try {
			if (prePatient == null || prePatient != null && !prePatient.getIdNumber().equals(patient.getIdNumber())) {
				// 生成二维码
				QRCodeUtil.encode(patient.getIdNumber(), null, path + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT_BARCODE,
								"/" + patient.getId() + ".png", true);
				patient.setBarcodePath("/" + UserUtil.getTenantId() + "/images/patient/barcode/" + patient.getId() + ".png");
				patientMapper.updateByPrimaryKeySelective(patient);
			}
			PatientHistory ph = new PatientHistory();
			PropertyUtils.copyProperties(ph, patientMapper.selectById(patient.getId()));
			patientHistoryMapper.insert(ph);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// update cache
		updateCache(patient.getId());
	}

	/**
	 * 插入传染病标识数据
	 * 
	 * @Title: insertAssayResult
	 * @param patientId患者id
	 * @param isImport
	 *
	 */
	private void insertAssayResult(Long patientId, boolean isImport) {
		PatientAssayResultPO record = new PatientAssayResultPO();
		record.setFkPatientId(patientId);
		record.setHav(false);
		record.setHbv(false);
		record.setHcv(false);
		record.setHev(false);
		record.setHiv(false);
		record.setHsv(false);
		if (isImport) {// 如果是导入，默认是阴性患者
			record.setNormal(true);
		} else {// 新增患者，默认未检查
			record.setUnknown(true);
		}
		patientAssayResultService.saveAssayResult(record);
	}

	@Override
	public void updatePatient(Patient patient) {
		DataUtil.setSystemFieldValue(patient);
		patientMapper.updateByPrimaryKey(patient);
		PatientHistory ph = new PatientHistory();
		try {
			PropertyUtils.copyProperties(ph, patientMapper.selectById(patient.getId()));
			patientHistoryMapper.insert(ph);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// update cache
		updateCache(patient.getId());
	}

	@Override
	public List<PatientPO> getPatientByTenantId(Integer tenantId, Boolean delFlag) {
		PatientPO patient = new PatientPO();
		patient.setFkTenantId(tenantId);
		patient.setDelFlag(delFlag);
		return patientMapper.selectByCondition(patient);
	}

	@Override
	public Integer getPatientCount(Integer tenantId) {
		PatientPO patient = new PatientPO();
		patient.setFkTenantId(tenantId);
		patient.setDelFlag(Boolean.FALSE);
		return patientMapper.selectCountByCondition(patient);
	}

	@Override
	public PatientPO selectById(Long id) {
		return selectById(id, true);
	}

	/**
	 * 是否从缓存中获取数据
	 * 
	 * @Title: selectById
	 * @param id
	 * @param fromCache
	 * @return
	 *
	 */
	private PatientPO selectById(Long id, boolean fromCache) {
		PatientPO patient = null;
		if (fromCache) {
			PatientDto patientDto = PatientCache.getById(id);
			if (patientDto != null) {
				patient = new PatientPO();
				BeanUtils.copyProperties(patientDto, patient);
			}
		}
		if (patient == null) {
			patient = patientMapper.selectById(id);
		}
		if (patient != null) {
			patient.setSexShow(CmDictUtil.getName(CmDictConstants.SEX, patient.getSex()));
			patient.setMedicareCardTypeShow(CmDictUtil.getName(CmDictConstants.MEDICARE_CARD_TYPE, patient.getMedicareCardType()));
		}
		return patient;
	}

	@Override
	public List<PatientPO> selectByCondition(Patient patent) {
		patent.setFkTenantId(UserUtil.getTenantId());
		return patientMapper.selectByCondition(patent);
	}

	@Override
	public PatientPO selectPatientByIdNumber(Patient patient) {
		List<PatientPO> list = patientMapper.selectPatientByIdNumber(patient);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean checkPatientExistByIdNumber(Long id, String idNumber) {
		PatientPO patient = new PatientPO();
		patient.setId(id);
		patient.setIdNumber(idNumber);
		patient.setFkTenantId(UserUtil.getTenantId());
		List<PatientPO> list = patientMapper.selectPatientByIdNumber(patient);
		return CollectionUtils.isEmpty(list) ? false : true;
	}

	@Override
	public void updateByPrimaryKeySelective(Patient patient) {
		patientMapper.updateByPrimaryKeySelective(patient);
		updateCache(patient.getId());
	}

	@Override
	public void updateDelFlag(Long id) {
		Patient patient = new Patient();
		patient.setDelFlag(Boolean.TRUE);
		patient.setId(id);
		updateByPrimaryKeySelective(patient);
	}

	@Override
	public List<PatientPO> selectByActive() {
		Patient p = new Patient();
		p.setDelFlag(false);
		p.setFkTenantId(UserUtil.getTenantId());
		return selectByCondition(p);
	}

	@Override
	public Patient login(String account, String password) {
		return patientMapper.login(account, password);
	}

	@Override
	public List<PatientPO> getAllPatientByTenantId(Integer tenantId) {
		PatientPO patient = new PatientPO();
		patient.setFkTenantId(tenantId);
		return patientMapper.selectByCondition(patient);
	}
}
