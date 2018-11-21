package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppPatientAccount;
import com.xtt.common.dao.po.AppPatientAccountPO;

@Repository
public interface AppPatientAccountMapper {
    void deleteByPrimaryKey(Long id);

    void insert(AppPatientAccount record);

    void insertSelective(AppPatientAccount record);

    AppPatientAccount selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(AppPatientAccount record);

    void updateByPrimaryKey(AppPatientAccount record);

    /**
     * 通过手机号查询患者信息个数
     * 
     * @Title: getCountByMobile
     * @param entity
     * @return
     *
     */
    int getCountByMobile(@Param("mobile") String mobile, @Param("fkPatientId") Long fkPatientId);

    /**
     * 根据手机号查询患者账户
     * 
     * @Title: getByMobile
     * @param mobile
     * @return
     *
     */
    AppPatientAccount getByMobile(@Param("mobile") String mobile);

    /**
     * 患者登陆验证
     * 
     * @Title: login
     * @param account
     * @param password
     * @param tenantId
     * @return
     *
     */
    AppPatientAccountPO login(@Param("account") String account, @Param("password") String password, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 管理员登录
     * 
     * @Title: loginToAdmin
     * @param account
     * @param password
     * @return
     *
     */
    AppPatientAccountPO loginToAdmin(@Param("account") String account, @Param("password") String password, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 统计患者家庭帐号数
     * 
     * @Title: countSubAccount
     * @param fkPatientId
     * @return
     *
     */
    Integer countSubAccount(@Param("fkPatientId") Long fkPatientId);

    /**
     * 查询所有可用的子帐号
     * 
     * @Title: selectSubAccount
     * @param patientId
     * @return
     *
     */
    List<AppPatientAccountPO> selectSubAccount(@Param("fkPatientId") Long fkPatientId);

    /**
     * 根据条件查询患者账户信息
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<AppPatientAccountPO> selectByCondition(AppPatientAccountPO record);

    /**
     * 查询所有患者
     * 
     * @Title: listAll
     * @return
     *
     */
    List<AppPatientAccountPO> listAll();

    /**
     * 根据患者登录账户查询所有的租户号
     * 
     * @Title: listTenantByAccount
     * @param account
     * @return
     *
     */
    List<AppPatientAccountPO> listTenantByAccount(@Param("account") String account);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param record
     * @return
     *
     */
    List<AppPatientAccountPO> listByCond(AppPatientAccountPO record);
}