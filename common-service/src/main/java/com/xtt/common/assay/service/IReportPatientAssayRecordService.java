/**   
 * @Title: IReportPatientAssayRecordService.java 
 * @Package com.xtt.txgl.report.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月24日 下午5:05:33 
 *
 */
package com.xtt.common.assay.service;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.model.ReportPatientAssayRecord;
import com.xtt.common.dao.po.ReportPatientAssayRecordPO;

public interface IReportPatientAssayRecordService {

    /**
     * 根据租户id，自动插入化验项统计表
     * 
     * @Title: insertAutoByTenantId
     * @param dateType
     * @param monthAndYear
     * @param tenantId
     * @param itemCodes
     * 
     */
    String insertAutoByTenantId(String dateType, String monthAndYear, Integer tenantId, Collection<String> itemCodes);

    /**
     * 根据租户ID，保存化验项统计 1、当前月不存在数据，自动复制上月数据 2、如果存在，自动获取所有最新数据覆盖
     * 
     * @Title: insertAutoCopyByTenantId
     * @param paramDate
     *            参数日期
     * @param monthAndYear
     *            当前月份
     * @param tenantId
     *            租户ID
     * @param itemCodes
     *
     */
    void insertAutoCopyByTenantId(String paramDate, String monthAndYear, Integer tenantId, Collection<String> itemCodes);

    /**
     * 同步拷贝上个月数据到当月作为基础数据
     * 
     * @Title: insertAutoCopyPreDataByTenantId
     * @param paramDate
     * @param monthAndYear
     * @param tenantId
     * @param itemCodes
     *
     */
    void insertAutoCopyPreMonthDataByTenantId(String monthAndYear, Integer tenantId, Collection<String> itemCodes);

    /**
     * 根据日期类型分组查询化验统计
     * 
     * @Title: selectByDateType
     * @param record
     * @return
     * 
     */
    List<ReportPatientAssayRecordPO> selectByDateType(ReportPatientAssayRecordPO record);

    /**
     * 获取标准差
     * 
     * @Title: selectPopByDateType
     * @param record
     * @return
     * 
     */
    Double selectPopByDateType(ReportPatientAssayRecord record);

    /**
     * 根据日期类型查询化验详情
     * 
     * @Title: selectDetailByDateType
     * @param record
     * @return
     * 
     */
    List<ReportPatientAssayRecordPO> selectDetailByDateType(ReportPatientAssayRecordPO record);

    /**
     * 删除所有化验项统计
     * 
     * @Title: deleteAll
     * 
     */
    void deleteAll();

    /**
     * 根据条件删除报表信息
     * 
     * @Title: deleteByCondition
     * @param record
     * 
     */
    void deleteByCondition(ReportPatientAssayRecord record);

    /**
     * 根据批次号删除临时数据
     * 
     * @Title: deleteTempByBatchNo
     * @param batchNo
     *
     */
    void deleteTempByBatchNo(String batchNo);
}
