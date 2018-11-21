package com.xtt.common.dao.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppPatientAssayRecordBusi;
import com.xtt.common.dao.po.AppPatientAssayRecordBusiPO;

@Repository
public interface AppPatientAssayRecordBusiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppPatientAssayRecordBusi record);

    int insertSelective(AppPatientAssayRecordBusi record);

    AppPatientAssayRecordBusi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppPatientAssayRecordBusi record);

    int updateByPrimaryKey(AppPatientAssayRecordBusi record);

    /**
     * 查询患者的化验单(api用)
     * 
     * @Title: listApiAssayList
     * @param patientId
     * @param startDate
     * @param endDate
     * @return
     *
     */
    List<Map<String, Object>> listApiAssayList(@Param("patientId") Long patientId, @Param("startDate") Date startDate,
                    @Param("endDate") Date endDate);

    /**
     * 根据患者、日期查询化验项(api用)
     * 
     * @Title: listApiAssayItems
     * @param patientId
     * @param assayDate
     * @return
     *
     */
    List<AppPatientAssayRecordBusi> listApiAssayItems(@Param("patientId") Long patientId, @Param("assayDate") Date assayDate);

    /**
     * 根据自定义条件查询常用项
     * 
     * @Title: listByCondition
     * @param query
     * @return
     *
     */
    List<AppPatientAssayRecordBusiPO> listByCondition(AppPatientAssayRecordBusiPO query);

}