package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayInspectioidBack;

@Repository
public interface PatientAssayInspectioidBackMapper {
    int insert(PatientAssayInspectioidBack record);

    int insertSelective(PatientAssayInspectioidBack record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<PatientAssayInspectioidBack> list);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientAssayInspectioidBack> listByCondition(PatientAssayInspectioidBack record);

    /**
     * 根据申请单和租户查询当前申请单的条数
     * 
     * @Title: countByInspectionId
     * @param inspectionId
     * @param fkTenantId
     * @return
     *
     */
    int countByInspectionId(@Param("inspectionId") String inspectionId, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 根据化验项唯一标识删除
     * 
     * @Title: deleteByInspectionId
     * @param inspectionId
     * @param fkPatientId
     * @param fkTenantId
     *
     */
    void deleteByInspectionId(@Param("inspectionId") String inspectionId, @Param("fkPatientId") Long fkPatientId,
                    @Param("fkTenantId") Integer fkTenantId);
}