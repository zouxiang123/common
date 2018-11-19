package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayPropConf;

@Repository
public interface PatientAssayPropConfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientAssayPropConf record);

    int insertSelective(PatientAssayPropConf record);

    PatientAssayPropConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientAssayPropConf record);

    int updateByPrimaryKey(PatientAssayPropConf record);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param record
     * @return
     *
     */
    List<PatientAssayPropConf> listByCond(PatientAssayPropConf record);

    /**
     * 批量保存
     * 
     * @Title: saveBatch
     * @param list
     *
     */
    void insertBatch(List<PatientAssayPropConf> list);

    /**
     * 根据租户号删除数据
     * 
     * @Title: deleteByTenantId
     * @param fkTenantId
     *
     */
    void deleteByTenantId(@Param("fkTenantId") Integer fkTenantId);
}