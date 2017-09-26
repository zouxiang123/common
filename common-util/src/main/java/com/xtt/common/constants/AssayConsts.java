/**   
 * @Title: AssayConsts.java 
 * @Package com.xtt.common.constants
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年8月9日 下午5:04:05 
 *
 */
package com.xtt.common.constants;

public class AssayConsts {
    public static final String ASSAY_RESULT_OK = "达标";
    public static final String ASSAY_RESULT_NO_OK = "不达标";
    public static final String ASSAY_RESULT_EXCESS_OK = "超标";
    public static final String OK_RATE = "达标率";
    public static final String COUNT = "总数量";
    public static final String SUM = "总和";
    public static final String AVG = "平均值";
    public static final String POP = "标准差";

    /** 非透前透后 */
    public static final String NOT_BEFORE_AFTER_HD = "0";
    /** 透前 */
    public static final String BEFORE_HD = "1";
    /** 透后 */
    public static final String AFTER_HD = "2";

    /** 分类规则(0:不区分透前透后,1:分组名关键字;2:样式名称关键字;3:分组名+化验项+透前透后时间;4:分组名+化验项数量+化验项数值判断+透析日时间;) */

    public static final String LAB_AFTER_BEFORE_ZERO = "0";
    public static final String LAB_AFTER_BEFORE_ONE = "1";
    public static final String LAB_AFTER_BEFORE_TWO = "2";
    public static final String LAB_AFTER_BEFORE_THREE = "3";
    public static final String LAB_AFTER_BEFORE_FOUR = "4";

    /** 正常 */
    public static final String TIPS_NORMAL = "1";
    /** 无法识别的 */
    public static final String TIPS_UNKNOWN = "2";
    /** 偏高 */
    public static final String TIPS_HIGH = "3";
    /** 偏低 */
    public static final String TIPS_LOW = "4";
    /**
     * 化验日期类别--月
     */
    public static final String REPORT_DATE_TYPE_MONTH = "m";
    /**
     * 化验日期类别--季
     */
    public static final String REPORT_DATE_TYPE_SEASON = "s";
    /**
     * 报表数据过滤规则 -- 最大值
     */
    public static final String REPORT_FILTER_RULE_MAX_VALUE = "1";
    /**
     * 报表数据过滤规则 -- 最小值
     */
    public static final String REPORT_FILTER_RULE_MIN_VALUE = "2";
    /**
     * 报表数据过滤规则 -- 平均值
     */
    public static final String REPORT_FILTER_RULE_AVG = "3";
    /**
     * 数值类别--数值
     */
    public static final Integer VALUE_TYPE_NUMBER = 1;

}
