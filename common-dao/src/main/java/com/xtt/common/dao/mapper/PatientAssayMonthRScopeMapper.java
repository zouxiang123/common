package com.xtt.common.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayMonthRScope;
import com.xtt.common.dao.po.PatientAssayMonthRScopePO;

@Repository
public interface PatientAssayMonthRScopeMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_month_r_scope
     * 
     * @mbggenerated Mon Jun 27 10:47:55 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_month_r_scope
     * 
     * @mbggenerated Mon Jun 27 10:47:55 CST 2016
     */
    int insert(PatientAssayMonthRScope record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_month_r_scope
     * 
     * @mbggenerated Mon Jun 27 10:47:55 CST 2016
     */
    int insertSelective(PatientAssayMonthRScope record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_month_r_scope
     * 
     * @mbggenerated Mon Jun 27 10:47:55 CST 2016
     */
    PatientAssayMonthRScope selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_month_r_scope
     * 
     * @mbggenerated Mon Jun 27 10:47:55 CST 2016
     */
    int updateByPrimaryKeySelective(PatientAssayMonthRScope record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_month_r_scope
     * 
     * @mbggenerated Mon Jun 27 10:47:55 CST 2016
     */
    int updateByPrimaryKey(PatientAssayMonthRScope record);

    /**
     * 查询所有
     */
    List<PatientAssayMonthRScopePO> selectAll(@Param("tenantId") Integer tenantId);

    /**
     * 修改PatientAssayMonthRScopePO
     */
    void updateMonth(@Param("monthValue") Integer monthValue, @Param("assayClass") String assayClass, @Param("updateTime") Date updateTime,
                    @Param("userId") long userId, @Param("tenantId") Integer tenantId);

    /**
     * 通过月份来查询开始时间和结束时间
     */
    List<PatientAssayMonthRScopePO> selectStartTimeEndTimeByMonth(@Param("monthValue") Integer monthValue, @Param("tenantId") Integer tenantId);

    /**
     * 通过月份来查询化验类
     */
    List<PatientAssayMonthRScopePO> selectAssayClassByMonth(@Param("monthValue") Integer monthValue, @Param("tenantId") Integer tenantId);

}