package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayNewst;
import com.xtt.common.dao.po.PatientAssayNewstPO;
import com.xtt.common.dao.po.PatientNewstPO;

@Repository
public interface PatientAssayNewstMapper {
    int insert(PatientAssayNewst record);

    int insertSelective(PatientAssayNewst record);

    /**
     * 定时任务，删除 自动处理患者最新一次化验项纪录
     * 
     * @param fkTenantId
     * 
     * @Title: deleteNewstAuto
     * @return
     *
     */
    int deleteNewstAuto(@Param("fkTenantId") Integer fkTenantId);

    /**
     * 获取指定患者化验项列表
     * 
     * @Title: selectByPatientArray
     * @param patientArrays
     *            患者ID列表
     * @param fkTenantId
     * @return
     *
     */
    List<PatientAssayNewstPO> selectByPatientArray(@Param("patientIdArrays") List<Long> patientArrays, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 根据患者名称和首字母查询逾期患者
     * 
     * @Title: listOverduePatients
     * @param patient
     * @return
     *
     */
    List<PatientNewstPO> listOverduePatients(PatientNewstPO patientNewst);

    /**
     * 查询最近一条化验数据
     * 
     * @Title: listLatestAssayRecord
     * @param itemCodes
     * @param fkTenantId
     * @return
     *
     */
    List<PatientAssayNewst> listLatestAssayRecord(@Param("fkTenantId") Integer fkTenantId);

    /**
     * 批量插入数据
     * 
     * @Title: insertList
     * @param records
     *
     */
    void insertList(@Param("records") List<PatientAssayNewst> records);

}