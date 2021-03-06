/**   
 * @Title: CmDictConstants.java 
 * @Package com.xtt.common.util.constants
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午3:51:27 
 *
 */
package com.xtt.common.constants;

public class CmDictConsts {
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
    /** 表单样式展示方式 */
    public static final String FORM_DISPLAY_STYLE = "form_display_style";
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
    public static final String BS_KSYY = "bs_ksyy"; // 血透史开始原因
    public static final String BS_JSYY = "bs_jsyy"; // 结束原因
    public static final String BS_GMY = "bs_gmy"; // 过敏源
    public static final String BS_CRBZDMC = "bs_crbzdmc"; // 传染病诊断名称
    public static final String BS_CRBHDZT = "bs_crbhdzt"; // 传染病活动状态
    public static final String BS_CRBZLQK = "bs_crbzlqk"; // 传染病治疗情况
    // -------------------diagnosis-end----------------------------------------------
    /** 化验单文本类别 */
    public static final String ASSAY_TEXT_TYPE = "assay_text_type";

    /*PD catheter*/
    /** 置管方式 */
    public static final String PD_CATHETER_WAY = "PD_catheter_way";
    /** 腹透管类型 */
    public static final String PD_CATHETER_TYPE = "PD_catheter_type";
    /** 出口部位 */
    public static final String PD_CATHETER_EXPORT_PART = "PD_catheter_export_part";
    /*PD prescription*/
    /** 腹透处方模式 */
    public static final String PD_PRESCRIPTION_MODE = "PD_prescription_mode";

    /** 服务调用 */
    /**
     * 获取病患基本信息
     */
    public static final String URL_IF_PATIENT = "url_if_patient";
    /**
     * 获取检验信息
     */
    public static final String URL_IF_LIS = "url_if_lis";
    /**
     * 获取云端接口服务的检验数据的地址
     */
    public static final String YUN_URL_IF_LIS = "yun_url_if_lis";

    public static final String URL = "url";

    public static final String DOWN_DB_WS_URL_ALL = "00";

    public static final String DOWN_DB_WS_URL_PT = "01";

    public static final String DOWN_DB_WS_URL_ORDER = "02";
    /** 分组规则 */
    public static final String GROUP_RULE = "group_rule";

    /**
     * 患者-费用类型
     */
    public static final String PATIENT_CHARGE_TYPE = "patient_charge_type";

    // =========================================================================

    public static final String DOWN_DB_WS_URL_LIS = "03";
    /**
     * 跳转到新增数据源页面
     */
    public static final String ADD_SYS_DB_SOURCE = "04";
    // =========================================================================

    /** 检验类型 */
    public static final String ASSAY_TYPE = "assay_type";
    /**
     * 患者转归类型
     */
    public static final String PATIENT_OUTCOME_TYPE = "patient_outcome_type";

    /*-------- unit start --------*/
    /**
     * 单位--压力
     */
    public static final String CM_UNIT_PRESSURE = "CM_unit_pressure";
    /**
     * 单位--压力(kpa)
     */
    public static final String CM_UNIT_PRESSURE_KPA = "kpa";
    /**
     * 单位--压力(mmHg)
     */
    public static final String CM_UNIT_PRESSURE_MMHG = "mmHg";
    /**
     * 单位--温度
     */
    public static final String CM_UNIT_TEMPERATURE = "CM_unit_temperature";
    /**
     * 单位--频次
     */
    public static final String CM_UNIT_FREQUENCY = "CM_unit_frequency";
    /**
     * 单位--流量
     */
    public static final String CM_UNIT_FLOW = "CM_unit_flow";
    /**
     * 单位--容量
     */
    public static final String CM_UNIT_CAPACITY = "CM_unit_capacity";
    /**
     * 单位--电导率
     */
    public static final String CM_UNIT_CONDUCTIVITY = "CM_unit_conductivity";
    /*-------- unit end --------*/

    /**
     * 工程师：维修商家
     */
    public static final String ENG_MAINTAIN_MERCHANT = "eng_maintain_merchant";

    /**
     * 民族
     */
    public static final String CM_NATON = "nation";

    /**
     * 文化程度
     */
    public static final String CM_CULTURE = "standard_of_culture";

    /**
     * 药品观察类型
     */
    public static final String DRUGS_TYPE = "drugs_type";
}
