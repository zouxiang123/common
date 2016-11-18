/**   
 * @Title: IPdFollowUpValueService.java 
 * @Package com.xtt.pd.follow
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2016年7月8日 下午15:05:46 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;

import com.xtt.common.dao.po.CmPatientDiagnosisValuePO;

public interface IPatientDiagnosisValueService {

	/**
	 * 根据itemCode 查询数据
	 * 
	 * @Title: selectByItemCode
	 * @param itemCode
	 * @return PO Object list
	 *
	 */
	List<CmPatientDiagnosisValuePO> selectByItemCode(String itemCode);
}
