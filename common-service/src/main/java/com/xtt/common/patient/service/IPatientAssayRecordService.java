/**   
 * @Title: IPatientAssayRecordService.java 
 * @Package com.xtt.txgl.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:24 
 *
 */
package com.xtt.common.patient.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.PatientAssayRecordPO;

public interface IPatientAssayRecordService {
	/**
	 * 查询不同化验时间的化验记录
	 * 
	 * @Title: getAssayRecord
	 * @return
	 * 
	 */
	List<PatientAssayRecordPO> getAssayDateRecord(PatientAssayRecordPO record);

	/**
	 * 获取某项化验单报表数据
	 * 
	 * @Title: getReportData
	 * @param patientId
	 * @param startTime
	 * @param endTime
	 * @param itemCode
	 * @return
	 * 
	 */

	List<Map<String, Object>> getReportData(Long patientId, Date startTime, Date endTime, String itemCode);

	/**
	 * 根据自定义条件获取数据
	 * 
	 * @Title: getByCondition
	 * @param record
	 * @return
	 * 
	 */
	List<PatientAssayRecordPO> getByCondition(PatientAssayRecordPO record);

	/**
	 * 根据化验日期获取患者化验数据
	 * 
	 * @Title: getByAssayDate
	 * @param date
	 * @return
	 * 
	 */
	List<PatientAssayRecordPO> getByAssayDate(String date, Long patientId);

	/**
	 * 根据时间查询报表信息
	 * 
	 * @Title: selectByAssayTime
	 * @param patientAssayRecord
	 * @return
	 * 
	 */
	List<PatientAssayRecordPO> selectReportByAssayTime(PatientAssayRecordPO patientAssayRecord);

	/**
	 * 查询相关统计。求总数量，总和，均值，标准差
	 * 
	 * @Title: selectStatisticsReport
	 * @param patientAssayRecord
	 * @return
	 * 
	 */
	List<PatientAssayRecordPO> selectStatisticsReport(PatientAssayRecordPO patientAssayRecord);

	/**
	 * 查询患者所有的类别列表
	 * 
	 * @Title: categoryList
	 * @param patientId
	 * @return
	 * 
	 */
	@Deprecated
	List<Map<String, Object>> getCategoryListByPatientId(Long patientId);

	/**
	 * 查询类别列表
	 * 
	 * @Title: getCategoryList
	 * @param query
	 * @return
	 * 
	 */
	List<PatientAssayRecordPO> getCategoryList(PatientAssayRecordPO record);

	/**
	 * 获取患者最新一次的化验数据
	 * 
	 * @Title: selectLatestAssayDateByTenantId
	 * @param tenantId
	 * @return
	 * 
	 */
	List<Map<String, Object>> selectLatestAssayDateByTenantId(Integer tenantId);

	/**
	 * 根据月份查询月份的化验明细
	 * 
	 * @Title: selectByMonth
	 * @param patientAssayRecord
	 * @return
	 * 
	 */
	List<PatientAssayRecordPO> selectByMonth(PatientAssayRecordPO patientAssayRecord);

	/**
	 * 根据自定义code查询数据
	 * 
	 * @Title: selectByFkDictCode
	 * @param record
	 * @return
	 *
	 */
	List<PatientAssayRecordPO> selectByFkDictCode(PatientAssayRecordPO record);
}
