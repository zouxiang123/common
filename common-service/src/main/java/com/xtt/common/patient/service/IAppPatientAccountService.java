/**   
 * @Title: IAppPatientAccountService.java 
 * @Package com.xtt.hdApp.patient.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年6月4日 上午11:55:39 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.model.AppPatientAccount;
import com.xtt.common.dao.po.AppPatientAccountPO;

public interface IAppPatientAccountService {
    void deleteByPrimaryKey(Long id);

    void insert(AppPatientAccount record);

    void insertSelective(AppPatientAccount record);

    AppPatientAccount selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(AppPatientAccount record);

    void updateByPrimaryKey(AppPatientAccount record);

    /**
     * 根据手机号判断患者是否存在
     * 
     * @Title: checkExistByMobile
     * @param record
     * @return
     *
     */
    boolean checkExistByMobile(String mobile, Long fkPatientId);

    /**
     * 根据手机号查询登录账号
     * 
     * @Title: getByMobile
     * @param mobile
     * @return
     *
     */
    AppPatientAccount getByMobile(String mobile);

    /**
     * 患者/患者家属 查询
     * 
     * @Title: login
     * @param account
     * @param password
     * @return
     *
     */
    AppPatientAccountPO login(String account, String password, Integer fkTenantId);

    /**
     * 查询管理员
     * 
     * @Title: selectToAdmin
     * @param account
     * @param password
     * @param fkTenantId
     * @return
     *
     */
    AppPatientAccountPO loginToAdmin(String account, String password, Integer fkTenantId);

    /**
     * 患者/患者家属登陆
     * 
     * @Title: login
     * @param account
     * @param password
     * @return
     *
     */
    List<AppPatientAccountPO> selectSubAccount(Long patientId);

    /**
     * 根据条件查询患者账户信息
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<AppPatientAccountPO> selectPatientAccountByPatient(AppPatientAccountPO record);

    /**
     * 登录
     * 
     * @Title: loginSubmit
     * @param account
     *            账户
     * @param password
     *            密码
     * @param fkTenantId
     *            租户号
     * @param isAdmin
     *            是否管理员登录
     * @return
     *
     */
    Map<String, Object> loginSubmit(String account, String password, Integer fkTenantId, boolean isAdmin);

    /**
     * 查询所有患者
     * 
     * @Title: listAll
     * @return
     *
     */
    List<AppPatientAccountPO> listAll();

    /**
     * 根据患者登录账户查询所有的租户 号
     * 
     * @Title: listTenantByAccount
     * @param account
     * @return
     *
     */
    List<AppPatientAccountPO> listTenantByAccount(String account);

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
