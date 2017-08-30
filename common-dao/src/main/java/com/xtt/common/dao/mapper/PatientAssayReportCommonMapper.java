package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayReportCommon;
import com.xtt.common.dao.po.PatientAssayReportCommonPO;

@Repository
public interface PatientAssayReportCommonMapper {

    int insert(PatientAssayReportCommon record);

    int insertSelective(PatientAssayReportCommon record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<PatientAssayReportCommon> list);

    /**
     * 删除数据
     * 
     * @Title: deleteByTenant
     * @param patientAssayBackCommon
     *
     */
    void deleteByTenant(Integer fkTenatId);

    /**
     * 根据日期查询
     * 
     * @Title: seleteByAssayDate
     * @param patientAssayBackCommon
     * @return
     *
     */
    List<PatientAssayReportCommonPO> selectByAssayDate(PatientAssayReportCommonPO patientAssayBackCommon);

    /**
     * 根据患者标签查询
     * 
     * @Title: selectByPatientLabel
     * @param patientAssayBackCommon
     * @return
     *
     */
    List<PatientAssayReportCommonPO> selectByPatientLabel(PatientAssayReportCommonPO patientAssayBackCommon);

    /**
     * 根据化验项删除数据
     * 
     * @Title: deleteByItemCodes
     * @param listItemCode
     *
     */
    void deleteByItemCodes(@Param("itemCodes") List<String> itemCodes, @Param("fkTenantId") Integer fkTenanId);
}