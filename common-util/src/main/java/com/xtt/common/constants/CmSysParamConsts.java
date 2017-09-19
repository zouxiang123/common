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
}
