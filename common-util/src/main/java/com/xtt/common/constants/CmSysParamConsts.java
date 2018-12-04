package com.xtt.common.constants;

public class CmSysParamConsts {
    /** 版本号 */
    public static final String VERSION = "version";
    /** 腹透液肌酐校正因子 */
    public static final String PD_DRAIN_FLUID_CREATININE_CORRECTION_FACTOR = "PD_drain_fluid_creatinine_correction_factor";

    /**
     * 化验项统计 [复制更新患者统计copy|最新患者统计simple]
     */
    public static final String TEST_ITEM_SWITCH = "CM_test_item_switch";
    public static final String TEST_ITEM_SWITCH_COPY = "COPY";
    public static final String TEST_ITEM_SWITCH_SIMPLE = "SIMPLE";

    /**
     * 化验时间（1：采集时间，2：报告时间，3：都显示）
     */
    public static final String PATIENT_ASSAY_LOAD_CONDITION_TIME_SWITCH = "lab_time_flag";

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
    /** 是否调用第三方平台的接口，获取患者类型的开关（1=开启，0=未开启） */
    public static final String IS_OPEN_PT_TYPE = "isOpenPtType";
    public static final String IS_OPEN_PT_TYPE_OPEN = "1";
    public static final String IS_OPEN_PT_TYPE_CLOSE = "0";
    /** 患者序号前缀 */
    public static final String PATIENT_SERIALNUM_PREFIX = "patientSerialNumPrefix";
    /** 患者透析转归后序号是否复用 0：否 1：是 */
    public static final String PATIENT_OUTCOME_SERIALNUM_MULTIPLEXING = "patientOutComeSerialNumMultiplexing";
    /**
     * 接口服务开启的卡号类型(输入汉字)
     */
    public static final String PATIENT_INTERFACE = "patient_interface";

    /** 开:是 */
    public static final String SHOW_ON = "1";
    /** 关:否 */
    public static final String SHOW_OFF = "0";
    /** 打印服务地址 */
    public static final String PRINT_SERVER_ADDR = "print_server_addr";
    /**
     * app地址
     */
    public static final String HD_APP_ADDR = "hdAppAddr";
    /**
     * 化验是否关联宣教计划
     */
    public static final String PROP_AUTO_PUSH = "propAutoPush";
}
