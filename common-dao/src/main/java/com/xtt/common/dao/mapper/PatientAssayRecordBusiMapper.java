package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;

@Repository
public interface PatientAssayRecordBusiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientAssayRecordBusi record);

    int insertSelective(PatientAssayRecordBusi record);

    PatientAssayRecordBusi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientAssayRecordBusi record);

    int updateByPrimaryKey(PatientAssayRecordBusi record);

    /*user define*/

    int countByInspectionId(String inspectionId);

    void insertList(@Param("list") List<PatientAssayRecordBusi> list);

    /**
     * 根据自定义条件查询常用项
     * 
     * @Title: listByCondition
     * @param query
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listByCondition(PatientAssayRecordBusiPO query);

    /**
     * 根据reqId，patientId，SampleTime 更新投后透前逻辑
     * 
     * @Title: updateDiaAbFlagByReqId
     * @param patientAssayRecordBusi
     *
     */
    void updateDiaAbFlagByReqId(PatientAssayRecordBusi patientAssayRecordBusi);
}