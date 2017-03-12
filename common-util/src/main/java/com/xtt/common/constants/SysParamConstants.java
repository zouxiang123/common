package com.xtt.common.constants;

public class SysParamConstants {

    /** 开:是 */
    public static final String SHOW_ON = "1";
    /** 关:否 */
    public static final String SHOW_OFF = "0";
    /** 版本号 */
    public static final String VERSION = "version";
    /** 体重浮动范围 */
    public static final String WEIGHT = "weight";
    /** 血压浮动范围 */
    public static final String BLOOD_PRESSURE = "bloodPressure";
    /** 耗材不足提醒 */
    public static final String SUPPLIES_WARNING = "suppliesWarning";
    /** 药品不足提醒 */
    public static final String DRUG_WARNING = "drugWarning";
    /** 自动排床周期（周） */
    public static final String AUTO_BED_CYCLE = "autoBedCycle";
    /** 自动排床开始日期（年-月-日） */
    public static final String AUTO_BED_STARTDATE = "autoBedStartDate";
    /** 最大班次 */
    public static final String SICKBED_SHIFT = "sickbedShift";
    /** 坠床风险评估分值 */
    public static final String ASSESSMENT_TUMBLE_SCORE = "assessmentTumbleScore";
    /** PHP服务地址 */
    public static final String PHP_SERVER_ADDR = "phpServerAddr";
    /** 机器消毒时长（分钟） */
    public static final String DISINFECTION_DURATION = "disinfectionDuration";
    /** 透析机服务地址 */
    public static final String DIALYSIS_ADDR = "dialysisAddr";
    /** 自动小结提示 */
    public static final String AUTO_SUMMARY_MSG = "autoSummaryMsg";
    /** 扫码功能是否显示 */
    public static final String DIALYSIS_MACHINE_BARCODE = "dialysisMachineBarCode";
    /** 透中护理默认呼吸频率配置 */
    public static final String DIALYSIS_RESPIRATORY_RATE = "dialysisRespiratoryRate";
    /** 制定处方低分子肝素显示（0显示几分之几，1显示首剂和追加，2显示首剂） */
    public static final String DIALYSIS_PRESCRIPTION_LMWH = "dialysisPrescription_LMWH";
    /** 置换液计算 **/
    public static final String DIALYSIS_ZHY_JS = "zhyjs";
    /** 置换液前稀释默认值 **/
    public static final String DIALYSIS_ZHY_QXS = "zhy_qxs";
    /** 置换液后稀释默认值 **/
    public static final String DIALYSIS_ZHY_HXS = "zhy_hxs";
    /** 置换液混合稀释默认值 **/
    public static final String DIALYSIS_ZHY_HHXS = "zhy_hhxs";
    /** 置换液是否自动计算 **/
    public static final String DIALYSIS_ZHY_AUTOMATIC = "zhy_automatic";
    /** 是否联机获取生命体征数据 (0、否,1、是) */
    public static final String NEED_REFRESH_BLOOD = "needRefreshBlood";

    /** 透析液流量默认值(ml/min) */
    public static final String DIALYSATE_FLOW_RATE = "dialysateFlowRate";
    /** 透析液温度默认值(℃) */
    public static final String DIALYSATE_TEMPERATURE = "dialysateTemperature";
    /** Ca浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_CA = "dialysateConcCa";
    /** Na浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_NA = "dialysateConcNa";
    /** K浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_K = "dialysateConcK";
    /** Cl浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_CL = "dialysateConcCl";
    /** Mg浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_MG = "dialysateConcMg";
    /** Ac浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_AC = "dialysateConcAc";
    /** 碳酸氢盐浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_HCO3 = "dialysateConcHco3";
    /** 葡萄糖浓度默认值(mmol/L) */
    public static final String DIALYSATE_CONC_GLUCOSE = "dialysateConcGlucose";

    /** 透后称量是否自动更新实际超滤量（0:否，1:是） */
    public static final String AFTER_AUTO_UPDATE_UFV = "afterAutoUpdateUFV";
    /** 透析小结显示（1:简约版；2:完整版） */
    public static final String DIALYSIS_SUMMARY_DISPLAY = "dialysisSummaryDisplay";
    /** 24小時自动取消关注 */
    public static final String NEED_CLOSE_ATTENTION = "needCloseAttention";

    /** 最大床位数 */
    public static final String MAX_BED_COUNT = "maxBedCount";
    /** 是否显示处方信息 */
    public static final String SHOW_PRESCRIPTION_INFO = "showPrescriptionInfo";
    /** 记录单显示方式（0：不显示动脉压；1：显示动脉压） */
    public static final String SHOW_DIALYSIS_RECORDWAYS = "showDialysisRecordWays";
    /** 排床患者查询方式(0:字母索引,1:姓名搜索) */
    public static final String SICKBED_PATIENT_QUERY_WAYS_CONFIG = "configureQueryWays";
    /** 药品管理患者查询方式(0:字母索引,1:姓名搜索) */
    public static final String MANGER_PATIENT_QUERY_WAYS_CONFIG = "configureQueryPatientWays";

    /** 标签分类患者查询方式(0:字母索引,1:姓名搜索) */
    public static final String LABLE_PATIENT_QUERY_WAYS_CONFIG = "lablePatientShow";
    public static final String SHOW_Patient_LABLE = "1";

    /** 小秘书服务器地址 */
    public static final String WEBSOCKET_SERVER_ADDRESS = "websocket.server";

    /** 单据号是否显示 */
    public static final String SHOW_CHARGES_CODE = "showChargesCode";
    /** 大屏展示是否显示空床位 0：不显示 1：显示 */
    public static final String SHOW_BLANK_TV = "showBlankTV";
    /** 大屏展示是否显示欢迎页 0：不显示 1：显示 */
    public static final String SHOW_WELCOME = "showWelcome";
    /** 是否显示底部logo */
    public static final String SHOW_BUTTON = "showbutton";
    /** 生成交班看班的时间 */
    public static final String CREATE_SHIFT_BORAD = "createShiftBorad";

    /**
     * 透析区处方:预设超滤量[开ON|关OFF] 默认开
     */
    public static final String DIALYSIS_PRESCRITION_VOLUME_SWITCH = "dialysis_prescrition_volume_switch";
    /**
     * 透析起始时间开关:随机起始时间作为透析开始时间[开ON|关OFF] 默认关闭
     */
    public static final String SHIFT_RANDOM_START_TIME_SWITCH = "shift_random_start_time_switch";
    /** 上机检查和透析下机是否需要显示机器数据 (0、否,1、是) */
    public static final String CHECK_AND_RESULT_NEED_DISPLAY_MACHINE_DATA = "checkAndResultNeedDisplayMachineData";
    /** 上机检查和透析下机是否需要显示 生命体征(0、否,1、是) */
    public static final String CHECK_AND_RESULT_NEED_DISPLAY_VITAL_SIGNS = "checkAndResultNeedDisplayVitalSigns";
    /** 上机检查生命体征从透前称量获取(0、否,1、是) */
    public static final String CHECK_VITAL_SIGNS_FORM_BEFORE = "checkVitalSignsFormBefore";
    public static final String BEFORE_AND_AFTER_NEED_DISPLAY_MACHINE_DATA = "beforeAndAfterNeedDisplayMachineData";

    /**
     * 化验项统计 [复制更新患者统计copy|最新患者统计simple]
     */
    public static final String TEST_ITEM_SWITCH = "test_item_switch";
    public static final String TEST_ITEM_SWITCH_COPY = "COPY";
    public static final String TEST_ITEM_SWITCH_SIMPLE = "SIMPLE";

    /**
     * 开启
     */
    public static final String SWITCH_ON = "ON";
    /**
     * 关闭
     */
    public static final String SWITCH_OFF = "OFF";

    /**
     * 定容数据是否显示 [显示ON|不显示OFF]
     */
    public static final String DISPLAY_METERED_VOLUME = "display_metered_volume";

    /**
     * 交叉核对定容超过预设值发送通知-开关 [开ON|关OFF]
     */
    public static final String NOTICE_DIALYSIS_CROSS_VOLUME_SWITCH = "notice_dialysis_cross_volume_switch";

    /**
     * 设置定容超标预设值
     */
    public static final String DIALYSIS_CROSS_VOLUME_VAL = "dialysis_cross_volume_val";
    /**
     * 耗材是否启用个性化配置
     */
    public static final String DP_TEMPLATE_SUPPLY_CUSTOM = "dp_template_supply_custom";

    /**
     * 自动生成查房记录开关[开ON|关OFF]
     */
    public static final String WARD_ROUND_SWITCH = "ward_round_switch";
    /**
     * 自动生成查房记录模板操作人
     */
    public static final String WARD_ROUND_OPTION_USER = "ward_round_option_user";
    /**
     * 自动生成查房记录模板内容
     */
    public static final String WARD_ROUND_OPTION_CONTEXT = "ward_round_option_context";

    /** 费用预警阈值 */
    public static final String COST_WARNING_THRESHOLD = "cost_warning_threshold";
    /** 费用预估方式 */
    public static final String COST_ESTIMATION_MODE = "cost_estimation_mode";
    /** 费用预估方式 1：月预估 */
    public static final String COST_ESTIMATION_MODE_MONTH = "1";
    /** 费用预估方式 2：日预估 */
    public static final String COST_ESTIMATION_MODE_DAY = "2";
    /** 费用预估是否包含透中监测（1：是 0：否） */
    public static final String COST_ESTIMATION_PROCESS_MONITOR = "cost_estimation_process_monitor";

    /** 透析医生小结显示（0:不显示；1:显示） */
    public static final String DIALYSIS_DOCTORSUMMARY_DISPLAY = "dialysisDoctorSummaryDisplay";

    /** 是否调用第三方平台的接口，获取患者类型的开关（1=开启，0=未开启） */
    public static final String IS_OPEN_PT_TYPE = "isOpenPtType";
    public static final String IS_OPEN_PT_TYPE_OPEN = "1";
    public static final String IS_OPEN_PT_TYPE_CLOSE = "0";

    /** 是否显示患者序列号 */
    public static final String showPatientSerialNum = "showPatientSerialNum";
    /** 患者报表显示患者序列号 */
    public static final String showPatientSerial = "1";
    /** 患者报表显示患者Id */
    public static final String showPatientId = "0";
    /** 是否显示HisId */
    public static final String SHOW_HID_Id = "showHisId";
    /** 患者报表显示患者HisId */
    public static final String showPatientHisId = "1";
    /** 患者卡号类型 HisType */
    public static final String showPatientCradType = "03";

    /** 患者透析转归后序号是否复用 0：否 1：是 */
    public static final String PATIENT_OUTCOME_SERIALNUM_MULTIPLEXING = "patientOutComeSerialNumMultiplexing";

    /** 患者序号前缀 */
    public static final String PATIENT_SERIALNUM_PREFIX = "patientSerialNumPrefix";

    /** 透析评估模板 */
    public static final String DIALYSIS_NURSE_EVALUATION_TEMPLATE = "dialysisNurseEvaluationTemplate";

    /** 配置是否手动录入血压 */
    public static final String BLOOD_PRESSURE_EDIT = "inputBloodPressure";

    /** 配置pad端是否显示传染病 */
    public static final String SHOW_INFECTIOUS = "showInfectious";

    /** 制定处方之患者体重需求控制参数（0：普通需求，1：南总需求） */
    public static final String PRESCRIPTION_WEIGHT_CONTROL_PARAMETER = "prescriptionWeightControlParameter";

    /** 透析治疗单需求控制参数（0：普通需求，1：南总需求） */
    public static final String DIALYSIS_PREVIEW_CONTROL_PARAMETER = "dialysisPreviewControlParameter";

    /** 收费确认是否推送给his系统(1：推送,0:不推送) */
    public static final String MO_POST_FLAG = "mo_post_flag";
    public static final String MO_POST_FLAG_0 = "0";
    public static final String MO_POST_FLAG_1 = "1";

    /** 电子病历，患者临时转长期 */
    public static final String PATIENT_DIALYSIS_MONTH_VAL = "patient_dialysis_month_val";
    public static final String PATIENT_DIALYSIS_COUNT_VAL = "patient_dialysis_count_val";
    public static final String PATIENT_IS_TEMPORARY_TO_TRANSFER = "patient_is_temporary_to_transfer";

    /** 患者透析下机需求控制参数（0：普通需求，1：南总需求） */
    public static final String DIALYSIS_RESULT_CONTROL_PARAMETER = "dialysisResultControlParameter";

    /** 透析下机时，如果数据为空是否给出提示 **/
    public static final String Dialysis_remind_switch = "dialysisRemindSwitch";

    /** 统计报表之患者转归统计需求控制参数（0：普通需求，1：南总需求） */
    public static final String OUTCOME_REPORT_CONTROL_PARAMETER = "outcomeReportControlParameter";
    /** 床号搜索方式 ***/
    public static final String BED_SEARCH_WAYS = "bedSearchWays";
    /** 是否显示长期医嘱转临时医嘱 **/
    public static final String Show_Prn_To_Sos = "showPrnToSos";
    /** 肝素量是否显示配置(0:不显示，1:显示) **/
    public static final String HEPARIN_DISPLAY = "heparin_display";
    /** (预设超滤量、实际超滤量、定容)单位配置(1：ml，2：Kg) **/
    public static final String UFV_UNIT = "ufv_unit";
    public static final String UFV_UNIT_ML = "1";
    public static final String UFV_UNIT_KG = "2";

    /**
     * 临时医嘱通知 - 开关 [开ON|关OFF]
     */
    public static final String NOTICE_PROCESS_TEMPORARY_SWITCH = "notice_process_temporary_switch";

    /***
     * 抗凝方式 - 开关 0关 1南总抗凝方式
     */
    public static final String ANTICOAGULATION_CITRIC_ACID_SWITCH = "anticoagulation_citric_acid_switch";
    /**
     * 抗凝方式 1南总抗凝方式
     */
    public static final String ANTICOAGULATION_CITRIC_ACID_SWITCH_1_SOUTH = "1";
    /**
     * 枸橼酸，药品类型：枸橼酸B液 泵后滤器前 默认 40
     */
    public static final String ANTICOAGULATION_CITRIC_ACID_SWITCH_1_BEFOREPUMPFILTER = "anticoagulation_citric_acid_switch_1_beforepumpfilter";
    /**
     * 枸橼酸，药品类型：枸橼酸B液 静脉壶 默认 10
     */
    public static final String ANTICOAGULATION_CITRIC_ACID_SWITCH_1_VEINPOT = "anticoagulation_citric_acid_switch_1_veinpot";
    /**
     * 处方是否需要核对异常 - 开关 [0否|1是] 默认 0
     */
    public static final String PRESCRIPTION_CHECK_EXCEPTION_SWITCH = "prescription_check_exception_switch";
    /**
     * 制定处方是否显示打印复选框 - 开关 [0否|1是]
     */
    public static final String DIALYSIS_PRESCRIPTION_PRINT_SWITCH = "dialysis_prescription_print_switch";
    /**
     * 抗凝方式：无肝素开关 0关 1开，默认关闭
     */
    public static final String ANTICOAGULATION_DRUG_NOTHING_HEPARIN_SWITCH = "drug_nothing_heparin_switch";
    /**
     * 抗凝方式：无肝素关 1开启
     */
    public static final String ANTICOAGULATION_DRUG_NOTHING_HEPARIN_SWITCH_ON = "1";
    /** 腹透液肌酐校正因子 */
    public static final String PD_DRAIN_FLUID_CREATININE_CORRECTION_FACTOR = "PD_drain_fluid_creatinine_correction_factor";
}
