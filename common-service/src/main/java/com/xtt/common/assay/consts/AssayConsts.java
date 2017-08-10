/**   
 * @Title: AssayConsts.java 
 * @Package com.xtt.common.assay.consts
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年8月2日 下午4:30:56 
 *
 */
package com.xtt.common.assay.consts;

/**
 * 化验相关常量
 * 
 * @ClassName: AssayConsts
 * @date: 2017年8月2日 下午4:31:04
 * @version: V1.0
 *
 */
public class AssayConsts {
    /** 非透前透后 */
    public static final String NOT_BEFORE_AFTER_HD = "0";
    /** 透前 */
    public static final String BEFORE_HD = "1";
    /** 透后 */
    public static final String AFTER_HD = "2";

    /** 检验透析前后判断逻辑控制（检验结果表<patient_assay_record> 1：根据group_name判断 2：根据sample_class判断 3：根据item_code判断 4：根据sample_time判断 5:根据化验条目判断） */
    public static final String LAB_AFTER_BEFORE = "lab_after_before";
    public static final String LAB_AFTER_BEFORE_ONE = "1";
    public static final String LAB_AFTER_BEFORE_TWO = "2";
    public static final String LAB_AFTER_BEFORE_THREE = "3";
    public static final String LAB_AFTER_BEFORE_FOUR = "4";
    public static final String LAB_AFTER_BEFORE_FIVE = "5";

    /** 查询条件 item_code in ('CA','K') */
    public static final String WHERE_IN_ITEM_CODE_LIST = "itemCodeList";

    /** 关键字：透析前 透析后 */
    public static final String LAB_AFTER_BEFORE_KEYWORD = "lab_after_before_keyword";
    /** 非透析前和透析后 */
    public static final String NOT_AFTER_BEFORE = "0";
    /** 关键字： 透析前 */
    public static final String LAB_BEFORE = "1";
    public static final String LAB_BEFORE_CN = "(前)";
    /** 关键字：透析后 */
    public static final String LAB_AFTER = "2";
    public static final String LAB_AFTER_CN = "(后)";
    /** 关键字：透析前后时间（<=24） */
    public static final String LAB_GJZ_SJ = "3";
    /** 关键字：透析前表单项目条数 */
    public static final String LAB_BEFORE_COUNT = "4";
    /** 关键字：透析后表单项目条数 */
    public static final String LAB_AFTER_COUNT = "5";

    /** 关键字：化验项目的名称 */
    public static final String LAB_GROUP_NAME = "化验项";

    /** 正常 */
    public static final String TIPS_NORMAL = "1";
    /** 无法识别的 */
    public static final String TIPS_UNKNOWN = "2";
    /** 偏低 */
    public static final String TIPS_LOW = "3";
    /** 偏高 */
    public static final String TIPS_HIGH = "4";
}
