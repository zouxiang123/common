/**   
 * @Title: AppPatientAccountServiceImpl.java 
 * @Package com.xtt.hdApp.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年6月4日 上午11:56:58 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.AppPatientAccountMapper;
import com.xtt.common.dao.model.AppPatientAccount;
import com.xtt.common.dao.po.AppPatientAccountPO;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.patient.service.IAppPatientAccountService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.security.MD5Util;

@Service
public class AppPatientAccountServiceImpl implements IAppPatientAccountService {
    @Autowired
    private AppPatientAccountMapper appPatientAccountMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        appPatientAccountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(AppPatientAccount record) {
        DataUtil.setSystemFieldValue(record, 1L);
        appPatientAccountMapper.insert(record);
    }

    @Override
    public void insertSelective(AppPatientAccount record) {
        DataUtil.setSystemFieldValue(record, 1L);
        appPatientAccountMapper.insertSelective(record);
    }

    @Override
    public AppPatientAccount selectByPrimaryKey(Long id) {
        return appPatientAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(AppPatientAccount record) {
        DataUtil.setUpdateSystemFieldValue(record, 1L);
        appPatientAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(AppPatientAccount record) {
        DataUtil.setUpdateSystemFieldValue(record, 1L);
        appPatientAccountMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean checkExistByMobile(String mobile, Long fkPatientId) {
        return appPatientAccountMapper.getCountByMobile(mobile, fkPatientId) > 0 ? true : false;
    }

    @Override
    public AppPatientAccount getByMobile(String mobile) {
        return appPatientAccountMapper.getByMobile(mobile);
    }

    @Override
    public AppPatientAccountPO login(String account, String password, Integer fkTenantId) {
        AppPatientAccountPO pa = appPatientAccountMapper.login(account, password, fkTenantId);
        if (pa != null) {
            pa.setCountSubAccount(appPatientAccountMapper.countSubAccount(pa.getFkPatientId()));
        }
        return pa;
    }

    @Override
    public AppPatientAccountPO loginToAdmin(String account, String password, Integer fkTenantId) {
        return appPatientAccountMapper.loginToAdmin(account, password, fkTenantId);
    }

    @Override
    public List<AppPatientAccountPO> selectSubAccount(Long patientId) {
        return appPatientAccountMapper.selectSubAccount(patientId);
    }

    @Override
    public List<AppPatientAccountPO> selectPatientAccountByPatient(AppPatientAccountPO record) {
        List<AppPatientAccountPO> list = appPatientAccountMapper.selectByCondition(record);
        for (AppPatientAccountPO po : list) {
            if (StringUtils.isBlank(po.getSex())) {
                po.setSexShow("");
            } else {
                po.setSexShow(DictUtil.getItemName(CmDictConsts.SEX, po.getSex()));
            }
        }
        return list;
    }

    @Override
    public Map<String, Object> loginSubmit(String account, String password, Integer fkTenantId, boolean isAdmin) {
        Map<String, Object> map = new HashMap<String, Object>();
        password = MD5Util.md5(password);
        String token = UUID.randomUUID().toString();
        AppPatientAccountPO pa = null;
        if (isAdmin) {
            pa = this.loginToAdmin(account, password, fkTenantId);
            HttpServletUtil.getRequest().setAttribute(CommonConstants.COOKIE_TOKEN, token);
        } else {
            pa = this.login(account, password, fkTenantId);
        }
        if (pa == null) {
            map.put(CommonConstants.STATUS, CommonConstants.FAILURE);
            map.put(CommonConstants.API_ERROR_MESSAGE, "帐号或密码不正确！");
            return map;
        }
        map.put("patientId", pa.getFkPatientId());
        map.put("accountId", pa.getId());
        map.put("isSub", pa.getIsSib());
        map.put(CommonConstants.API_TOKEN, token);
        LoginUser loginUser = new LoginUser();
        loginUser.setId(pa.getId());
        loginUser.setAccount(account);
        loginUser.setName(pa.getSibName());
        loginUser.setPatientId(pa.getFkPatientId());
        loginUser.setCountSubAccount(pa.getCountSubAccount());
        loginUser.setImagePath(pa.getImagePath() == null ? null : CommonConstants.BASE_IMAGE_PATH + pa.getImagePath());
        loginUser.setBarcodePath(pa.getBarcodePath() == null ? null : CommonConstants.BASE_IMAGE_PATH + pa.getBarcodePath());
        loginUser.setIsSib(Boolean.valueOf(pa.getIsSib()));
        loginUser.setSex(pa.getSexShow());
        loginUser.setAge(StringUtil.isNotBlank(pa.getAge()) ? Integer.valueOf(pa.getAge()) : null);
        loginUser.setMultiTenant(pa.getMultiTenantId());
        loginUser.setTenantId(pa.getFkTenantId());
        loginUser.setMultiSysOwner(pa.getMultiSysOwner());
        loginUser.setSysOwner(pa.getSysOwner());
        loginUser.setAppSysOwner(StringUtils.isBlank(pa.getAppSysOwner()) ? CommonConstants.APP_OWNER_P : pa.getAppSysOwner());
        UserUtil.setLoginUser(token, loginUser);
        if (pa.getRoleId() == null) {
            pa.setRoleId(CommonConstants.ROLE_PATIENT_GENERAL);
        }
        UserUtil.setApiNonPermissionList(pa.getRoleId(), token);// 设置没有权限的菜单列表
        UserUtil.setApiPermissionList(pa.getRoleId(), token);// 设置有权限的菜单列表
        // 设置用户职称
        if (pa.getParentRoleId() != null && pa.getParentRoleId().indexOf(CommonConstants.ROLE_ADMIN) > -1) {
            loginUser.setRoleType(CommonConstants.ROLE_ADMIN);
            // loginUser.setPositionShow(sysUser.getPosition());
        } else {
            loginUser.setRoleType(CommonConstants.ROLE_OTHER);
            // loginUser.setPositionShow(sysUser.getPosition());
        }
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @Override
    public List<AppPatientAccountPO> listAll() {
        return appPatientAccountMapper.listAll();
    }

    @Override
    public List<AppPatientAccountPO> listTenantByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }
        return appPatientAccountMapper.listTenantByAccount(account);
    }

    @Override
    public List<AppPatientAccountPO> listByCond(AppPatientAccountPO record) {
        List<AppPatientAccountPO> list = appPatientAccountMapper.listByCond(record);

        return list;
    }
}
