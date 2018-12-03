/**   
 * @Title: IAssayQueryAPI.java 
 * @Package com.xtt.common.api
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年11月30日 上午11:38:50 
 *
 */
package com.xtt.common.api;

import java.util.Map;

public interface IAssayQueryAPI {

    /**
     * 查询患者化验项大类数据
     * 
     * @Title: getAssayDateRecord
     * @param fkPatientId
     * @param dateType
     * @param startDateStr
     * @param endDateStr
     * @return
     *
     */

    Map<String, Object> getAssayDateRecord(Long fkPatientId, String dateType, String startDateStr, String endDateStr);

    /**
     * 所获不同的检查时间
     * 
     * @Title: getAssayRecord
     * @param fkPatientId
     * @param reqId
     * @return
     *
     */
    Map<String, Object> getAssayRecord(Long fkPatientId, String reqId);
}
