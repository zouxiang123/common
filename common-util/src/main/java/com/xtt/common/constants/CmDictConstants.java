/**   
 * @Title: CmDictConstants.java 
 * @Package com.xtt.common.util.constants
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午3:51:27 
 *
 */
package com.xtt.common.constants;

public class CmDictConstants {
	// -------------common start----------------------------------------------
	/** 性别 */
	public static final String SEX = "sex";
	public static final String HAVE_OR_NOT = "have_or_not";// 有/无
	public static final String IS_OR_NOT = "is_or_not";// 是/否
	/** 控件类型 */
	public static final String FORM_ELEMENT_TYPE = "form_element_type";
	/** 数据类型 */
	public static final String FORM_ITEM_DATA_TYPE = "form_item_data_type";
	/** 单位 */
	public static final String FORM_ITEM_UNIT = "form_item_unit";
	/** 医生职称 */
	public static final String DOCTOR_PROFESSIONAL_TITLE = "doctor_professional_title";
	/** 医生职称 */
	public static final String NURSE_PROFESSIONAL_TITLE = "nurse_professional_title";
	// -------------common end----------------------------------------------
	// -------------dynamic form start----------------------------------------------
	/** 表单模块 */
	public static final String FORM_MODULE = "form_module";
	/** 所属系统 */
	public static final String SYS_OWNER = "sys_owner";
	/** 腹透表单类别 */
	public static final String PD_FORM_CATEGORY = "PD_form_category";
	/** 腹透随访类别 */
	public static final String PD_FU_CATEGORY = "PD_fu_category";
	/** 腹透随访表单类别 */
	public static final String PD_FU_FORM_CATEGORY = "PD_fu_form_category";
	// -------------dynamic form end----------------------------------------------
	// -------------fullow start----------------------------------------------
	/** 身高测量方式 */
	public static final String STATURE_MEASURE_WAY = "stature_measure_way";
	/** 皮褶部位 */
	public static final String SKINFOLD_PART = "skinfold_part";
	/** 医保卡类别 */
	public static final String MEDICARE_CARD_TYPE = "medicare_card_type";
	// -------------fullow end----------------------------------------------
	// -------------diagnosis-start----------------------------------------------
	public static final String FIRST_DIALYSIS_METHOD = "first_dialysis_method";// 首次透析方式
	public static final String HEMORRHAGE = "hemorrhage";// 严重出血或出血倾向
	public static final String HEART_DEFECTS = "heart_defects";// 严重心肺功能不全病史
	public static final String NEPHROSIS_TYPE = "nephrosis_type";// 肾病类型
	public static final String CLINICAL_PGN = "clinical_pgn";// 原发性肾小球疾病（临床诊断）
	public static final String CLINICAL_SGN = "clinical_sgn";// 继发性肾小球疾病（临床诊断）
	public static final String CLINICAL_HEREDITARY_NEPHROPATHY = "clinical_hereditary_nephropathy";// 遗传性及先天性肾病（临床诊断）
	public static final String CLINICAL_TIN = "clinical_tin";// 肾小管间质疾病（临床诊断）
	public static final String UN_AND_STONE = "un_and_stone";// 泌尿系统感染和结石
	public static final String RENAL_RESECTION = "renal_resection";// 肾脏切除术原因
	public static final String SERIOUS_CRF_REASON = "serious_crf_reason";// 慢性肾功能不全急性加重原因
	public static final String ARF_REASON = "arf_reason";// 急性肾功能衰竭原因
	public static final String PATHOLOGY_PGN = "pathology_pgn";// 原发性肾小球疾病（病理诊断）
	public static final String PATHOLOGY_SGN = "pathology_sgn";// 继发性肾小球疾病（病理诊断）
	public static final String PATHOLOGY_HEREDITARY_NEPHROPATHY = "pathology_hereditary_nephropathy";// 遗传性及先天性肾病（病理诊断）
	public static final String PATHOLOGY_TIN = "pathology_tin";// 肾小管间质疾病（病理诊断）
	public static final String CKD_TYPE = "ckd_type";// CKD类型
	public static final String CKD_STAGE = "ckd_stage";// CKD分期
	public static final String AKI_TYPE = "aki_type";// AKI类型
	public static final String AKI_STAGE_RIFLE = "aki_stage_rifle";// AKI分期RIFLE
	public static final String AKI_STAGE = "aki_stage";// AKI分期
	// =====================================================================================
	public static final String BS_KSYY = "bs_ksyy"; // 血透史开始原因
	public static final String BS_JSYY = "bs_jsyy"; // 结束原因
	public static final String BS_GMY = "bs_gmy"; // 过敏源
	public static final String BS_CRBZDMC = "bs_crbzdmc"; // 传染病诊断名称
	public static final String BS_CRBHDZT = "bs_crbhdzt"; // 传染病活动状态
	public static final String BS_CRBZLQK = "bs_crbzlqk"; // 传染病治疗情况
	// =====================================================================================
	// public static final String MAIN_SYMPTOM = "main_symptom";// 治疗前合并症及治疗情况
	public static final String MAIN_SYMPTOM = "zlqhbz_info"; // 治疗前合并症及治疗情况
	public static final String GKWZDXWL_INFO = "gkwzdxwl_info"; // 1:骨矿物质代谢紊乱
	public static final String DFYBX_INFO = "dfybx_info"; // 2:淀粉样变性
	public static final String HXXT_INFO = "hxxt_info"; // 3:呼吸系统
	public static final String XHXT_INFO = "xhxt_info"; // 4:消化系统
	public static final String XXGXT_INFO = "xxgxt_info"; // 5:心血管系统
	public static final String SJXT_INFO = "sjxt_info"; // 6:神经系统
	public static final String PFSY_INFO = "pfsy_info"; // 7:皮肤瘙痒
	public static final String XYXT_INFO = "xyxt_info"; // 8:血液系统
	public static final String HBZL_INFO = "hbzl_info"; // 9:合并肿瘤
	public static final String HBGR_INFO = "hbgr_info"; // 10:合并感染
	public static final String ZSMYXJB_INFO = "zsmyxjb_info"; // 11:自身免疫性疾病
	public static final String NFMJDXXT_INFO = "nfmjdxxt_info"; // 12:内分泌及代谢系统
	// -------------------diagnosis-end----------------------------------------------
	/** 化验单文本类别 */
	public static final String ASSAY_TEXT_TYPE = "assay_text_type";

	/** 服务调用 */
	public static final String URL = "url";

	public static final String DOWN_DB_WS_URL_ALL = "00";

	public static final String DOWN_DB_WS_URL_PT = "01";

	public static final String DOWN_DB_WS_URL_ORDER = "02";
}
