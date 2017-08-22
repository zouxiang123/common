package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayBackCommon;
import com.xtt.common.dao.po.PatientAssayBackCommonPO;

@Repository
public interface PatientAssayBackCommonMapper {

    int insert(PatientAssayBackCommon record);

    int insertSelective(PatientAssayBackCommon record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<PatientAssayBackCommon> list);

    /**
     * 删除数据
     * 
     * @Title: deleteByTenant
     * @param patientAssayBackCommon
     *
     */
    void deleteByTenant(PatientAssayBackCommon patientAssayBackCommon);

    /**
     * 根据日期查询
     * 
     * @Title: seleteByAssayDate
     * @param patientAssayBackCommon
     * @return
     *
     */
    List<PatientAssayBackCommonPO> selectByAssayDate(PatientAssayBackCommonPO patientAssayBackCommon);

    /**
     * 根据患者标签查询
     * 
     * @Title: selectByPatientLabel
     * @param patientAssayBackCommon
     * @return
     *
     */
    List<PatientAssayBackCommonPO> selectByPatientLabel(PatientAssayBackCommonPO patientAssayBackCommon);

    /**
     * 根据化验项删除数据
     * 
     * @Title: deleteByItemCodes
     * @param listItemCode
     *
     */
    void deleteByItemCodes(@Param("itemCodes") List<String> itemCodes, @Param("fkTenantId") Integer fkTenanId);
}