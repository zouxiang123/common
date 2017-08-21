package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.po.PatientPO;

@Repository
public interface PatientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

    /*user define*/
    /**
     * 根据自定义条件查询患者数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientPO> listByCondition(PatientPO record);

    /**
     * 根据id查询数据，包含省市区，年龄等信息
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    PatientPO getById(Long id);

    /**
     * 根据条件查询患者人数
     * 
     * @Title: countByCondition
     * @param patent
     * @return
     *
     */
    Integer countByCondition(Patient patent);

    /**
     * 根据患者idNumber查询数据
     * 
     * @Title: listByIdNumber
     * @param idNumber
     *            证件号码
     * @param nePatientId
     *            不等于患者id
     * @return
     *
     */
    List<PatientPO> listByIdNumber(@Param("idNumber") String idNumber, @Param("nePatientId") Long nePatientId);

    /**
     * 患者的登陆
     * 
     * @Title: login
     * @param account
     *            账户
     * @param password
     *            密码
     * @return
     *
     */
    Patient login(@Param("account") String account, @Param("password") String password);

    /**
     * 通过手机号查询患者信息个数
     * 
     * @param mobile
     * @param neId
     *            不等于的患者id
     * @return
     */
    int getCountByMobile(@Param("mobile") String mobile, @Param("neId") Long neId);

    /**
     * 通过手机号查询患者信息
     * 
     * @param mobile
     * @param neId
     * @return
     */
    List<PatientPO> listByMobile(@Param("mobile") String mobile, @Param("neId") Long neId);
}
