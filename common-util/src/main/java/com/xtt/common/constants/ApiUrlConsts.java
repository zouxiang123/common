/**   
 * @Title: UrlConsts.java 
 * @Package com.xtt.common.constants
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年10月12日 下午7:33:39 
 *
 */
package com.xtt.common.constants;

public class ApiUrlConsts {
    /**
     * 根据患者id获取手术记录数据接口
     */
    public static final String POPS_OPERATION_LIST = "popsOperationRecord/listOperationForApi.shtml";
    /**
     * 获取最近一次手术记录填写日期
     */
    public static final String POPS_GET_LATEST_INPUT_DATE = "popsOperationRecord/getLatestInputDate.shtml";
    /**
     * 更新最近一次手术记录填写日期
     */
    public static final String POPS_UPDATE_LATEST_INPUT_DATE = "popsOperationRecord/updateLatestInputDate.shtml";

    /*------------------PD--------------------------*/
    /**
     * 获取最近一次腹透数据填写日期
     */
    public static final String PD_GET_LATEST_INPUT_DATE = "pdLatestInputRecord/getDate.shtml";
    /**
     * 更新最近一次腹透数据填写日期
     */
    public static final String PD_UPDATE_LATEST_INPUT_DATE = "pdLatestInputRecord/updateDate.shtml";
}
