/**   
 * @Title: ICmDiagnosisConf.java 
 * @Package com.xtt.common.diagnosis.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月6日 下午8:22:47 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.CmDiagnosisConfPO;

/**
 * @ClassName: ICmDiagnosisConf
 * @date: 2016年7月6日 下午8:22:47
 * @version: V1.0
 *
 */
public interface IDiagnosisConfService {
	/**
	 * 获取所有的随访单配置项
	 * 
	 * @Title: selectAll
	 * @return
	 *
	 */
	List<CmDiagnosisConfPO> selectAll();

	/**
	 * 保存随访单配置
	 * 
	 * @Title: saveFollowUpConf
	 * @param records
	 * @return
	 *
	 */
	Map<String, Object> saveConf(CmDiagnosisConfPO[] records);

	/**
	 * 根据ItemCode查询数据
	 * 
	 * @Title: selectByItemCode
	 * @param itemCode
	 * @return
	 *
	 */
	List<CmDiagnosisConfPO> selectByItemCode(String itemCode);

	/**
	 * 批量删除节点
	 * 
	 * @Title: delFollowConf
	 * @param records
	 * @return
	 *
	 */
	Map<String, Object> delConf(CmDiagnosisConfPO[] records);

	/**
	 * 根据自定义条件查询数据
	 * 
	 * @Title: selectByCondition
	 * @param record
	 * @return
	 *
	 */
	List<CmDiagnosisConfPO> selectByCondition(CmDiagnosisConfPO record);

}
