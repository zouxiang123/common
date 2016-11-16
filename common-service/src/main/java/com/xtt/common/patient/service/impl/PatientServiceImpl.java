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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientCardMapper;
import com.xtt.common.dao.mapper.PatientHistoryMapper;
import com.xtt.common.dao.mapper.PatientMapper;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientCard;
import com.xtt.common.dao.model.PatientHistory;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.dao.po.PatientManagePO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.QRCode.QRCodeUtil;
import com.xtt.platform.util.time.DateFormatUtil;

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
	// @Autowired
	// PatientDiagnosisMapper patientDiagnosisMapper;
	@Autowired
	private PatientCardMapper patientCardMapper;

	@Autowired
	ICommonService commonService;

	@Override
	public void savePatient(Patient patient) {
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
			String spellInitials = PinyinHelper.getShortPinyin(patient.getName());
			patient.setInitial(spellInitials.substring(0, 1).toUpperCase());
			patient.setSpellInitials(spellInitials);
		}
		if (patient.getId() == null) {
			patient.setDelFlag(false);
			patientMapper.insert(patient);
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
			}
			PatientHistory ph = new PatientHistory();
			PropertyUtils.copyProperties(ph, patientMapper.selectById(patient.getId()));
			patientHistoryMapper.insert(ph);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 
	 * 保存多卡号信息
	 */
	@Override
	public String savePatientCard(List<PatientCardPO> ptCardList) {
		String msg = "";
		for (PatientCardPO patientCardPO : ptCardList) {
			// 检查一个患者不能添加重复的卡号
			List<PatientCardPO> selectByCardNo = patientCardMapper.selectByCardNo(patientCardPO);
			int size = selectByCardNo.size();
			String cardNo = patientCardPO.getCardNo();
			Long id = patientCardPO.getId();
			if (cardNo != null && !cardNo.equals("")) {
				// 1.更新数据
				if (id != null && !id.equals("")) {
					if (size <= 1) {
						PatientCard patientCard = patientCardMapper.selectByPrimaryKey(id);
						if (patientCard != null) {
							patientCardPO.setCreateUserId(patientCard.getCreateUserId());
							patientCardPO.setCreateTime(patientCard.getCreateTime());
						}
						patientCardPO.setUpdateUserId(UserUtil.getLoginUserId());
						patientCardPO.setUpdateTime(DateFormatUtil.getDate());
						patientCardMapper.updateByPrimaryKey(patientCardPO);
					} else {
						msg = "修改的卡号信息已存在";
					}
					// 2.在批量插入页面提交过来的病患卡号数据
				} else {
					if (size < 1) {
						patientCardPO.setDelFlag(true);
						patientCardPO.setFkTenantId(UserUtil.getTenantId());
						patientCardPO.setCreateUserId(UserUtil.getLoginUserId());
						patientCardPO.setCreateTime(DateFormatUtil.getDate());
						patientCardPO.setUpdateUserId(UserUtil.getLoginUserId());
						patientCardPO.setUpdateTime(DateFormatUtil.getDate());
						patientCardMapper.insert(patientCardPO);
					} else {
						msg = "插入的卡号信息已存在";
					}
				}
			}
		}
		return msg;
	}

	@Override
	public PatientPO selectPatient(Long id) {
		return selectById(id);
	}

	@Override
	public List<Patient> selectPatientList(String name) {
		return null;
	}

	@Override
	public void updatePatient(Patient patient) {
		patientMapper.updateByPrimaryKeySelective(patient);
		PatientHistory ph = new PatientHistory();
		try {
			PropertyUtils.copyProperties(ph, patientMapper.selectById(patient.getId()));
			patientHistoryMapper.insert(ph);
		} catch (Exception e) {
			e.printStackTrace();
		}
		patient.setUpdateTime(new Date());
		patient.setUpdateUserId(UserUtil.getLoginUserId());
	}

	@Override
	public Map<String, Object> getDignosisByPatient(Long patientId) {

		// 患者基本信息
		Patient patient = patientMapper.selectById(patientId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("patient", patient);
		/*// 询问病史
		List<MedicalHistoryPO> medicalHistory = medicalHistoryMapper.selectByPatientId(patientId);
		// 临床诊断
		List<ClinicalDiagnosisResult> clinicalDiagnosisResult = clinicalDiagnosisResultMapper.selectByPatientId(patientId);
		// 慢性肾功能衰竭
		List<CrfPO> crf = crfMapper.selectByPatientId(patientId);
		// 慢性肾功能不全急性加重
		List<SeriousCrfPO> seriousCrf = seriousCrfMapper.selectByPatientId(patientId);
		// 急性肾功能衰竭
		List<ArfPO> arf = arfMapper.selectByPatientId(patientId);
		// 病理诊断
		List<PathologicDiagnosisResultPO> pathologicDiagnosisResult = pathologicDiagnosisResultMapper.selectByPatientId(patientId);
		// CKD/AKI
		List<CkdStagePO> ckdStage = ckdStageMapper.selectByPatientId(patientId);
		// 治疗前合并症
		List<CureSymptomAndConditionPO> cureSymptomAndCondition = cureSymptomAndConditionMapper.selectByPatientId(patientId);
		// 其它诊断
		List<OtherDiagnosisResultPO> otherDiagnosisResult = otherDiagnosisResultMapper.selectByPatientId(patientId);
		
		map.put("medicalHistory", medicalHistory);
		map.put("clinicalDiagnosisResult", clinicalDiagnosisResult);
		map.put("crf", crf);
		map.put("seriousCrf", seriousCrf);
		map.put("arf", arf);
		map.put("ckdStage", ckdStage);
		map.put("pathologicDiagnosisResult", pathologicDiagnosisResult);
		map.put("cureSymptomAndCondition", cureSymptomAndCondition);
		map.put("otherDiagnosisResult", otherDiagnosisResult);*/
		return map;
	}

	@Override
	public List<PatientPO> getPatientByTenantId(Integer tenantId) {
		return patientMapper.selectPatientByTenantId(tenantId);
	}

	@Override
	public Integer getPatientCount(Integer tenantId) {
		return patientMapper.selectPatientCount(tenantId);
	}

	@Override
	public PatientPO selectById(Long id) {
		PatientPO p = patientMapper.selectById(id);
		if (p != null) {
			// 根据血透id获取该患者的相关卡号
			PatientCardPO ptCard = new PatientCardPO();
			ptCard.setFkPtId(p.getId());
			List<PatientCardPO> patientCardList = patientCardMapper.selectByCardNo(ptCard);
			p.setPatientCardList(patientCardList);
		}
		return p;
	}

	@Override
	public List<PatientPO> selectByCondition(Patient patent) {
		patent.setFkTenantId(UserUtil.getTenantId());
		return patientMapper.selectByCondition(patent);
	}

	@Override
	public List<PatientPO> selectPatientByCDRType(String CDRType) {
		PatientPO patientPO = new PatientPO();
		patientPO.setCDRType(CDRType);
		patientPO.setFkTenantId(UserUtil.getTenantId());
		return patientMapper.selectPatientByCDRType(patientPO);
	}

	@Override
	public boolean checkPatientExistByIdNumber(Long id, String idNumber) {
		Integer cnt = patientMapper.checkPatientExistByIdNumber(id, idNumber, UserUtil.getTenantId());
		return cnt > 0 ? true : false;
	}

	@Override
	public PatientPO getByDCId(Long dcId) {
		return patientMapper.selectByDCId(dcId, UserUtil.getTenantId());
	}

	@Override
	public List<PatientManagePO> selectListForPatientManage(PatientManagePO patientManagePO) {
		patientManagePO.setFkTenantId(UserUtil.getTenantId());
		return patientMapper.selectListForPatientManage(patientManagePO);
	}

	@Override
	public PatientPO selectPatientByIdNumber(Patient patient) {
		List<PatientPO> list = patientMapper.selectPatientByIdNumber(patient);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PatientManagePO> selectPatientEHR(Map<String, Object> map) {

		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		// 基本信息
		PatientManagePO p = (PatientManagePO) (map.get("p"));
		where.append(" and p.fk_tenant_id = " + UserUtil.getTenantId());
		if (StringUtils.isNotEmpty(p.getName())) {
			where.append(" and (");
			where.append(" p.name like concat('%', " + DataUtil.sqlStr(DataUtil.escape(p.getName())) + ",'%')");
			where.append(" or p.spell_initials like concat('%', LOWER(" + DataUtil.sqlStr(DataUtil.escape(p.getName())) + "),'%')");
			where.append(" )");
		}
		if (StringUtils.isNotEmpty(p.getMobile())) {
			where.append(" and p.mobile like concat('%', " + DataUtil.sqlStr(DataUtil.escape(p.getMobile())) + ",'%')");
		}
		if (StringUtils.isNotEmpty(p.getSex())) {
			where.append(" and p.sex = " + DataUtil.sqlStr(p.getSex()));
		}
		if (p.getDelFlag() != null) {
			where.append(" and p.del_flag = " + DataUtil.sqlStr(p.getDelFlag()));
		}
		if (StringUtils.isNotEmpty(p.getAgeFrom())) {
			where.append(" and DATE_FORMAT(NOW(), '%Y') - DATE_FORMAT(birthday, '%Y') - (DATE_FORMAT(NOW(), '00-%m-%d') < DATE_FORMAT(birthday, '00-%m-%d')) >= "
							+ DataUtil.sqlStr(p.getAgeFrom()));
		}
		if (StringUtils.isNotEmpty(p.getAgeTo())) {
			where.append(" and DATE_FORMAT(NOW(), '%Y') - DATE_FORMAT(birthday, '%Y') - (DATE_FORMAT(NOW(), '00-%m-%d') < DATE_FORMAT(birthday, '00-%m-%d')) <= "
							+ DataUtil.sqlStr(p.getAgeTo()));
		}

		// 通路信息
		/*StringBuffer pwWhere = new StringBuffer();
		PathwayPO pw = (PathwayPO) (map.get("pw"));
		if (StringUtils.isNotEmpty(pw.getType())) {
			pwWhere.append(" and pw.type like concat('%', " + DataUtil.sqlStr(DataUtil.escape(pw.getType())) + ",'%')");
		}
		if (StringUtils.isNotEmpty(pw.getSubType())) {
			pwWhere.append(convertCombineCondition(pw.getSubType(), "pw.sub_type"));
		}
		if (pwWhere.length() > 0) {
			from.append(" left join (select pa.fk_patient_id,GROUP_CONCAT(pathway.type) type,GROUP_CONCAT(pathway.sub_type) sub_type");
			from.append(" from pathway_activity pa left join pathway on pathway.fk_pathway_activity_id=pa.id and pathway.is_enable = 1 and pa.end_flag = 0");
			from.append(" group by pa.fk_patient_id) pw on p.id=pw.fk_patient_id");
			where.append(pwWhere);
		}
		
		// 病史
		MedicalHistoryPO mh = (MedicalHistoryPO) (map.get("mh"));
		StringBuffer mhWhere = new StringBuffer();
		if (StringUtils.isNotEmpty(mh.getFirstDialysisDateFrom())) {
			mhWhere.append(" and mh.first_dialysis_date >= " + DataUtil.sqlStr(mh.getFirstDialysisDateFrom()));
		}
		if (StringUtils.isNotEmpty(mh.getFirstDialysisDateTo())) {
			mhWhere.append(" and mh.first_dialysis_date <= " + DataUtil.sqlStr(mh.getFirstDialysisDateTo()));
		}
		if (mh.getHasCva() != null) {
			mhWhere.append(" and mh.has_cva = " + DataUtil.sqlStr(mh.getHasCva()));
		}
		if (mh.getHasVascularDisease() != null) {
			mhWhere.append(" and mh.has_vascular_disease = " + DataUtil.sqlStr(mh.getHasVascularDisease()));
		}
		if (mh.getHasSeriousDisease() != null) {
			mhWhere.append(" and mh.has_serious_disease = " + DataUtil.sqlStr(mh.getHasSeriousDisease()));
		}
		if (mh.getHasPsychosis() != null) {
			mhWhere.append(" and mh.has_psychosis = " + DataUtil.sqlStr(mh.getHasPsychosis()));
		}
		if (StringUtils.isNotEmpty(mh.getHemorrhage())) {
			mhWhere.append(convertCombineCondition(mh.getHemorrhage(), "mh.hemorrhage"));
		}
		if (StringUtils.isNotEmpty(mh.getHeartDefects())) {
			mhWhere.append(convertCombineCondition(mh.getHeartDefects(), "mh.heart_defects"));
		}
		// 手术日期
		if (StringUtils.isNotEmpty(mh.getSsDateFrom())) {
			mhWhere.append(" and mh.opt_time >= " + DataUtil.sqlStr(mh.getSsDateFrom()));
		}
		if (StringUtils.isNotEmpty(mh.getSsDateTo())) {
			mhWhere.append(" and mh.opt_time <= " + DataUtil.sqlStr(mh.getSsDateTo()));
		}
		{// 血透史
			// 开始日期
			if (StringUtils.isNotEmpty(mh.getXtStartDateFrom())) {
				mhWhere.append(" and mh.xt_start_Time >= " + DataUtil.sqlStr(mh.getXtStartDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getXtStartDateTo())) {
				mhWhere.append(" and mh.xt_start_Time <= " + DataUtil.sqlStr(mh.getXtStartDateTo()));
			}
			// 开始原因
			if (StringUtils.isNotEmpty(mh.getXtStartReason())) {
				mhWhere.append(convertCombineCondition(mh.getXtStartReason(), "mh.xt_start_reason"));
			}
			// 结束日期
			if (StringUtils.isNotEmpty(mh.getXtEndDateFrom())) {
				mhWhere.append(" and mh.xt_end_time >= " + DataUtil.sqlStr(mh.getXtEndDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getXtEndDateTo())) {
				mhWhere.append(" and mh.xt_end_time <= " + DataUtil.sqlStr(mh.getXtEndDateTo()));
			}
			// 结束原因
			if (StringUtils.isNotEmpty(mh.getXtEndReason())) {
				mhWhere.append(convertCombineCondition(mh.getXtEndReason(), "mh.xt_end_reason"));
			}
		}
		{// 腹透史
			// 开始日期
			if (StringUtils.isNotEmpty(mh.getFtStartDateFrom())) {
				mhWhere.append(" and mh.ft_start_Time >= " + DataUtil.sqlStr(mh.getFtStartDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getFtStartDateTo())) {
				mhWhere.append(" and mh.ft_start_Time <= " + DataUtil.sqlStr(mh.getFtStartDateTo()));
			}
			// 开始原因
			if (StringUtils.isNotEmpty(mh.getFtStartReason())) {
				mhWhere.append(convertCombineCondition(mh.getFtStartReason(), "mh.ft_start_reason"));
			}
			// 结束日期
			if (StringUtils.isNotEmpty(mh.getFtEndDateFrom())) {
				mhWhere.append(" and mh.ft_end_time >= " + DataUtil.sqlStr(mh.getFtEndDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getFtEndDateTo())) {
				mhWhere.append(" and mh.ft_end_time <= " + DataUtil.sqlStr(mh.getFtEndDateTo()));
			}
			// 结束原因
			if (StringUtils.isNotEmpty(mh.getFtEndReason())) {
				mhWhere.append(convertCombineCondition(mh.getFtEndReason(), "mh.ft_end_reason"));
			}
		}
		{// 肾移植史
			// 开始日期
			if (StringUtils.isNotEmpty(mh.getSyzStartDateFrom())) {
				mhWhere.append(" and mh.syz_start_Time >= " + DataUtil.sqlStr(mh.getSyzStartDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getSyzStartDateTo())) {
				mhWhere.append(" and mh.syz_start_Time <= " + DataUtil.sqlStr(mh.getSyzStartDateTo()));
			}
			// 结束日期
			if (StringUtils.isNotEmpty(mh.getSyzEndDateFrom())) {
				mhWhere.append(" and mh.syz_end_time >= " + DataUtil.sqlStr(mh.getSyzEndDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getSyzEndDateTo())) {
				mhWhere.append(" and mh.syz_end_time <= " + DataUtil.sqlStr(mh.getSyzEndDateTo()));
			}
			// 结束原因
			if (StringUtils.isNotEmpty(mh.getSyzEndReason())) {
				mhWhere.append(convertCombineCondition(mh.getSyzEndReason(), "mh.syz_end_reason"));
			}
		}
		{// 过敏史
			// 录入日期
			if (StringUtils.isNotEmpty(mh.getGmDateFrom())) {
				mhWhere.append(" and mh.gm_enter_time >= " + DataUtil.sqlStr(mh.getGmDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getGmDateTo())) {
				mhWhere.append(" and mh.gm_enter_time <= " + DataUtil.sqlStr(mh.getGmDateTo()));
			}
			// 过敏源
			if (StringUtils.isNotEmpty(mh.getGmResouce())) {
				mhWhere.append(convertCombineCondition(mh.getGmResouce(), "mh.gm_resouce"));
			}
		}
		{// 传染病
			// 诊断日期
			if (StringUtils.isNotEmpty(mh.getCrbDateFrom())) {
				mhWhere.append(" and mh.crb_dia_time >= " + DataUtil.sqlStr(mh.getCrbDateFrom()));
			}
			if (StringUtils.isNotEmpty(mh.getCrbDateTo())) {
				mhWhere.append(" and mh.crb_dia_time <= " + DataUtil.sqlStr(mh.getCrbDateTo()));
			}
			// 诊断名称
			if (StringUtils.isNotEmpty(mh.getCrbDiaName())) {
				mhWhere.append(convertCombineCondition(mh.getCrbDiaName(), "mh.crb_dia_name"));
			}
			// 活动状态
			if (StringUtils.isNotEmpty(mh.getCrbDiaStatus())) {
				mhWhere.append(convertCombineCondition(mh.getCrbDiaStatus(), "mh.crb_dia_status"));
			}
			// 治疗情况
			if (StringUtils.isNotEmpty(mh.getCrbCase())) {
				mhWhere.append(convertCombineCondition(mh.getCrbCase(), "mh.crb_case"));
			}
		}
		
		if (mhWhere.length() > 0) {
			from.append(" left join medical_history mh on p.id = mh.fk_patient_id");
			where.append(mhWhere);
		}
		
		// 慢性肾功能衰竭crf
		CrfPO crf = (CrfPO) (map.get("crf"));
		StringBuffer crfWhere = new StringBuffer();
		if (StringUtils.isNotEmpty(crf.getType())) {
			crfWhere.append(" and cdr.type=1");
		}
		if (StringUtils.isNotEmpty(crf.getPgn())) {
			crfWhere.append(convertCombineCondition(crf.getPgn(), "crf.pgn"));
		}
		if (StringUtils.isNotEmpty(crf.getSgn())) {
			crfWhere.append(convertCombineCondition(crf.getSgn(), "crf.sgn"));
		}
		if (StringUtils.isNotEmpty(crf.getHereditaryNephropathy())) {
			crfWhere.append(convertCombineCondition(crf.getHereditaryNephropathy(), "crf.hereditary_nephropathy"));
		}
		if (StringUtils.isNotEmpty(crf.getTin())) {
			crfWhere.append(convertCombineCondition(crf.getTin(), "crf.tin"));
		}
		if (crf.getUrologicNeoplasms() != null) {
			crfWhere.append(" and crf.urologic_neoplasms = " + DataUtil.sqlStr(crf.getUrologicNeoplasms()));
		}
		if (StringUtils.isNotEmpty(crf.getUnAndStone())) {
			crfWhere.append(convertCombineCondition(crf.getUnAndStone(), "crf.un_and_stone"));
		}
		if (StringUtils.isNotEmpty(crf.getRenalResection())) {
			crfWhere.append(convertCombineCondition(crf.getRenalResection(), "crf.renal_resection"));
		}
		if (crf.getUnknownReason() != null) {
			crfWhere.append(" and crf.unknown_reason = " + DataUtil.sqlStr(crf.getUnknownReason()));
		}
		if (crfWhere.length() > 0) {
			from.append(" left join clinical_diagnosis_result cdr on p.id = cdr.fk_patient_id");
			from.append(" left join crf on cdr.is_enable=1 and crf.fk_clinical_diagnosis_result_id=cdr.id and cdr.type=1");
			where.append(crfWhere);
		}
		
		// 慢性肾功能不全急性加重
		SeriousCrfPO scrf = (SeriousCrfPO) (map.get("scrf"));
		StringBuffer scrfWhere = new StringBuffer();
		if (StringUtils.isNotEmpty(scrf.getType())) {
			scrfWhere.append(" and cdr.type=2");
		}
		if (StringUtils.isNotEmpty(scrf.getReason())) {
			scrfWhere.append(convertCombineCondition(scrf.getReason(), "scrf.reason"));
		}
		if (scrfWhere.length() > 0) {
			from.append(" left join clinical_diagnosis_result cdr on p.id = cdr.fk_patient_id");
			from.append(" left join serious_crf scrf on cdr.is_enable=1 and scrf.fk_clinical_diagnosis_result_id=cdr.id and cdr.type=2");
			where.append(scrfWhere);
		}
		
		// 急性肾功能衰竭
		ArfPO arf = (ArfPO) (map.get("arf"));
		StringBuffer arfWhere = new StringBuffer();
		if (StringUtils.isNotEmpty(arf.getType())) {
			arfWhere.append(" and cdr.type=3");
		}
		if (StringUtils.isNotEmpty(arf.getReason())) {
			arfWhere.append(convertCombineCondition(arf.getReason(), "arf.reason"));
		}
		if (arf.getUnknownReason() != null) {
			arfWhere.append(" and arf.unknown_reason = " + DataUtil.sqlStr(arf.getUnknownReason()));
		}
		if (arfWhere.length() > 0) {
			from.append(" left join clinical_diagnosis_result cdr on p.id = cdr.fk_patient_id");
			from.append(" left join arf arf on cdr.is_enable=1 and arf.fk_clinical_diagnosis_result_id=cdr.id and cdr.type=3");
			where.append(arfWhere);
		}
		
		// 病理诊断
		PathologicDiagnosisResultPO pd = (PathologicDiagnosisResultPO) (map.get("pd"));
		StringBuffer pdWhere = new StringBuffer();
		if (pd.getHasRenalBiopsy() != null) {
			pdWhere.append(" and pd.has_renal_biopsy = " + DataUtil.sqlStr(pd.getHasRenalBiopsy()));
		}
		if (StringUtils.isNotEmpty(pd.getPgn())) {
			pdWhere.append(convertCombineCondition(pd.getPgn(), "pd.pgn"));
		}
		if (StringUtils.isNotEmpty(pd.getSgn())) {
			pdWhere.append(convertCombineCondition(pd.getSgn(), "pd.sgn"));
		}
		if (StringUtils.isNotEmpty(pd.getHereditaryNephropathy())) {
			pdWhere.append(convertCombineCondition(pd.getHereditaryNephropathy(), "pd.hereditary_nephropathy"));
		}
		if (StringUtils.isNotEmpty(pd.getTin())) {
			pdWhere.append(convertCombineCondition(pd.getTin(), "pd.tin"));
		}
		if (pd.getHasNsp() != null) {
			pdWhere.append(" and pd.has_nsp = " + DataUtil.sqlStr(pd.getHasNsp()));
		}
		if (pd.getHasTkd() != null) {
			pdWhere.append(" and pd.has_tkd = " + DataUtil.sqlStr(pd.getHasTkd()));
		}
		if (pdWhere.length() > 0) {
			from.append(" left join pathologic_diagnosis_result pd on p.id = pd.fk_patient_id");
			where.append(pdWhere);
		}
		
		CkdStagePO ca = (CkdStagePO) (map.get("ca"));
		StringBuffer csWhere = new StringBuffer();
		if (StringUtils.isNotEmpty(ca.getCkdAki())) {
			if ("ckd".equals(ca.getCkdAki())) {
				csWhere.append(" and ca.ckd_type is not null");
			} else if ("aki".equals(ca.getCkdAki())) {
				csWhere.append(" and ca.aki_type is not null");
			}
		}
		if (StringUtils.isNotEmpty(ca.getCkdType())) {
			csWhere.append(" and ca.ckd_type = " + DataUtil.sqlStr(ca.getCkdType()));
		}
		if (StringUtils.isNotEmpty(ca.getCkdStage())) {
			csWhere.append(" and ca.ckd_stage = " + DataUtil.sqlStr(ca.getCkdStage()));
		}
		if (StringUtils.isNotEmpty(ca.getAkiType())) {
			csWhere.append(" and ca.aki_type = " + DataUtil.sqlStr(ca.getAkiType()));
		}
		if (StringUtils.isNotEmpty(ca.getAkiStageRifle())) {
			csWhere.append(" and ca.aki_stage = " + DataUtil.sqlStr(ca.getAkiStageRifle()));
		}
		if (StringUtils.isNotEmpty(ca.getAkiStage())) {
			csWhere.append(" and ca.aki_stage = " + DataUtil.sqlStr(ca.getAkiStage()));
		}
		if (csWhere.length() > 0) {
			from.append(" left join ckd_stage ca on p.id = ca.fk_patient_id");
			where.append(csWhere);
		}
		
		{// 治疗前合并症
			CureSymptomAndCondition hbz = (CureSymptomAndCondition) (map.get("hbz"));
			StringBuffer hbzWhere = new StringBuffer();
			// 骨矿物质代谢紊乱
			if (StringUtils.isNotEmpty(hbz.getGkwzdxwl())) {
				hbzWhere.append(convertCombineCondition(hbz.getGkwzdxwl(), "hbz.gkwzdxwl"));
			}
			// 淀粉样变性
			if (StringUtils.isNotEmpty(hbz.getDfypx())) {
				hbzWhere.append(convertCombineCondition(hbz.getDfypx(), "hbz.dfypx"));
			}
			// 呼吸系统
			if (StringUtils.isNotEmpty(hbz.getHxxt())) {
				hbzWhere.append(convertCombineCondition(hbz.getHxxt(), "hbz.hxxt"));
			}
			{// 暂时用不到
				if (StringUtils.isNotEmpty(hbz.getXhxt())) {
					hbzWhere.append(convertCombineCondition(hbz.getXhxt(), "hbz.xhxt"));
				}
				if (StringUtils.isNotEmpty(hbz.getXxgxtXzb())) {
					hbzWhere.append(convertCombineCondition(hbz.getXxgxtXzb(), "hbz.xxgxtXzb"));
				}
				if (StringUtils.isNotEmpty(hbz.getXxgxtXjb())) {
					hbzWhere.append(convertCombineCondition(hbz.getXxgxtXjb(), "hbz.xxgxtXjb"));
				}
				if (StringUtils.isNotEmpty(hbz.getXxgxtXzbmbb())) {
					hbzWhere.append(convertCombineCondition(hbz.getXxgxtXzbmbb(), "hbz.xxgxt_xzbmbb"));
				}
			}
			
			// 心血管系统
			if (StringUtils.isNotEmpty(hbz.getXxgxt())) {
				hbzWhere.append(convertCombineCondition(hbz.getXxgxt(), "hbz.xxgxt"));
			}
			// 神经系统
			if (StringUtils.isNotEmpty(hbz.getSjxt())) {
				hbzWhere.append(convertCombineCondition(hbz.getSjxt(), "hbz.sjxt"));
			}
			// 皮肤瘙痒
			if (StringUtils.isNotEmpty(hbz.getPfsy())) {
				hbzWhere.append(convertCombineCondition(hbz.getPfsy(), "hbz.pfsy"));
			}
			// 血液系统
			if (StringUtils.isNotEmpty(hbz.getXyxt())) {
				hbzWhere.append(convertCombineCondition(hbz.getXyxt(), "hbz.xyxt"));
			}
			// 合并肿瘤
			if (StringUtils.isNotEmpty(hbz.getHbzl())) {
				hbzWhere.append(convertCombineCondition(hbz.getHbzl(), "hbz.hbzl"));
			}
			// 合并感染
			if (StringUtils.isNotEmpty(hbz.getHbgr())) {
				hbzWhere.append(convertCombineCondition(hbz.getHbgr(), "hbz.hbgr"));
			}
			// 自身免疫疾病
			if (StringUtils.isNotEmpty(hbz.getZsmyxjb())) {
				hbzWhere.append(convertCombineCondition(hbz.getZsmyxjb(), "hbz.zsmyxjb"));
			}
			// 内分泌及代谢系统
			if (StringUtils.isNotEmpty(hbz.getNfmjdxxt())) {
				hbzWhere.append(convertCombineCondition(hbz.getNfmjdxxt(), "hbz.nfmjdxxt"));
			}
			if (hbzWhere.length() > 0) {
				from.append(" left join cure_symptom_and_condition hbz on p.id = hbz.fk_patient_id");
				where.append(hbzWhere);
			}
		}*/

		// String sql = from.toString() + " where 1 = 1 " + where.toString();
		Map<String, String> customeCondition = new HashMap<String, String>();
		customeCondition.put("dynamicFrom", from.toString());
		customeCondition.put("dynamicWhere", where.toString());
		return patientMapper.selectPatientEHR(customeCondition);
	}

	private String convertCombineCondition(String combineCondition, String field) {
		if (StringUtils.isEmpty(combineCondition)) {
			return "";
		}
		String combineConditions[] = combineCondition.split(",");
		if (combineConditions.length == 1) {
			return " and find_in_set('" + DataUtil.escape(combineCondition) + "'," + field + ")";
		}
		StringBuffer where = new StringBuffer();
		for (int i = 0; i < combineConditions.length; i++) {
			where.append(" and find_in_set('" + DataUtil.escape(combineConditions[i]) + "'," + field + ")");
		}
		return " and (" + where.toString().substring(4) + ")";
	}

	public List<PatientPO> selectByDrugStock(Patient patient) {
		patient.setFkTenantId(UserUtil.getTenantId());
		return patientMapper.selectByDrugStock(patient);
	}

	@Override
	public boolean selectPatientExistByIdNumber(Long id, String idNumber) {
		Integer cnt = patientMapper.selectPatientExistByIdNumber(id, idNumber, UserUtil.getTenantId());
		return cnt > 0 ? true : false;
	}

	/**
	 * 根据是否转归来查询 患者信息列表
	 */
	@Override
	public List<PatientPO> selectPatientByDelFlag() {
		return patientMapper.selectPatientByDelFlag();
	}

}
