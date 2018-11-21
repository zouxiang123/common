/**   
 * @Title: AppRoleServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年7月23日 上午10:18:06 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.AppHospitalMenuMapper;
import com.xtt.common.dao.mapper.AppMenuMapper;
import com.xtt.common.dao.mapper.AppPatientRoleMapper;
import com.xtt.common.dao.mapper.AppRoleMapper;
import com.xtt.common.dao.mapper.AppRoleMenuMapper;
import com.xtt.common.dao.model.AppHospitalMenu;
import com.xtt.common.dao.model.AppMenu;
import com.xtt.common.dao.model.AppPatientRole;
import com.xtt.common.dao.model.AppRole;
import com.xtt.common.dao.model.AppRoleMenu;
import com.xtt.common.patient.service.IAppRoleService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class AppRoleServiceImpl implements IAppRoleService {
    @Autowired
    private AppRoleMapper appRoleMapper;
    @Autowired
    private AppMenuMapper appMenuMapper;
    @Autowired
    private AppRoleMenuMapper appRoleMenuMapper;
    @Autowired
    private AppPatientRoleMapper appPatientRoleMapper;
    @Autowired
    private AppHospitalMenuMapper appHospitalMenuMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        appRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(AppRole record) {
        DataUtil.setAllSystemFieldValue(record);
        appRoleMapper.insert(record);
    }

    @Override
    public void insertSelective(AppRole record) {
        DataUtil.setAllSystemFieldValue(record);
        appRoleMapper.insertSelective(record);
    }

    @Override
    public AppRole selectByPrimaryKey(Long id) {
        return appRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(AppRole record) {
        DataUtil.setUpdateSystemFieldValue(record);
        appRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(AppRole record) {
        DataUtil.setUpdateSystemFieldValue(record);
        appRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AppMenu> listAllMenuList(String[] types, String appSysOwner) {
        return appMenuMapper.listAllMenuList(types, appSysOwner);
    }

    @Override
    public List<AppMenu> listMenuListBySysOwner(String[] types, String appSysOwner, String sysOwner) {
        return appMenuMapper.listMenuListBySysOwner(types, appSysOwner, sysOwner);
    }

    @Override
    public List<AppMenu> listMenuListByHospital(String[] types, Integer fkHospitalId) {
        return appMenuMapper.listMenuListByHospital(types, fkHospitalId);
    }

    @Override
    public List<AppMenu> listMenuListByRoleId(Long[] roleIds, String[] types) {
        return appMenuMapper.listMenuListByRoleId(roleIds, types);
    }

    @Override
    public List<AppRole> listByAppOwner(String appOwner) {
        return appRoleMapper.listByAppOwner(appOwner);
    }

    @Override
    public void saveMenuList(Long[] checkedMenuIds, Long menuRoleId, String[] types) {
        if (checkedMenuIds != null && menuRoleId != null && checkedMenuIds.length > 0) {
            appRoleMenuMapper.deleteByRoleIdAndType(menuRoleId, types);
            List<AppRoleMenu> menus = new ArrayList<AppRoleMenu>(checkedMenuIds.length);
            AppRoleMenu sr;
            String[] menuKeys = convertToMenuKeys(checkedMenuIds);
            for (int i = 0; i < menuKeys.length; i++) {
                sr = new AppRoleMenu();
                sr.setFkMenuKey(menuKeys[i]);
                sr.setFkTenantId(UserUtil.getTenantId());
                sr.setFkRoleId(menuRoleId);
                DataUtil.setSystemFieldValue(sr);
                menus.add(sr);
            }
            appRoleMenuMapper.insertBatch(menus);
        }
    }

    @Override
    public void saveHospitalMenuList(Long[] checkedMenuIds, Integer fkHospitalId, String[] types) {
        if (checkedMenuIds != null && fkHospitalId != null && checkedMenuIds.length > 0) {
            appHospitalMenuMapper.deleteByHospitalIdAndType(fkHospitalId, types);
            List<AppHospitalMenu> menus = new ArrayList<AppHospitalMenu>(checkedMenuIds.length);
            AppHospitalMenu sr;
            String[] menuKeys = convertToMenuKeys(checkedMenuIds);
            for (int i = 0; i < menuKeys.length; i++) {
                sr = new AppHospitalMenu();
                sr.setFkMenuKey(menuKeys[i]);
                sr.setFkTenantId(UserUtil.getTenantId());
                sr.setFkHospitalId(fkHospitalId);
                DataUtil.setSystemFieldValue(sr);
                menus.add(sr);
            }
            appHospitalMenuMapper.insertBatch(menus);
        }
    }

    @Override
    public String saveRoleList(Long[] delRoleIds, AppRole[] roles, String sysOwner, String appSysOwner) {
        if (delRoleIds != null && delRoleIds.length > 0) {
            List<Long> canDelRoles = new ArrayList<>();
            for (Long roleId : delRoleIds) {
                List<AppPatientRole> list = appPatientRoleMapper.selectByRoleId(roleId);
                if (list != null && !list.isEmpty()) {
                    return CommonConstants.WARNING;
                }
                if (roleCanModify(roleId)) {
                    canDelRoles.add(roleId);
                }
            }
            if (canDelRoles.size() > 0) {
                Long[] delArr = canDelRoles.toArray(new Long[canDelRoles.size()]);
                appRoleMapper.batchDeleteByRoleIds(delArr);
                appPatientRoleMapper.batchDeleteByRoleIds(delArr);
                appRoleMenuMapper.batchDeleteByRoleIds(delArr);
            }

        }
        if (roles != null && roles.length > 0) {
            for (AppRole sr : roles) {
                if (sr.getConstantType() != null) {// 固定角色不能修改
                    continue;
                }
                if (sr.getId() == null) {
                    sr.setFkTenantId(UserUtil.getTenantId());
                    DataUtil.setAllSystemFieldValue(sr);
                    sr.setAppSysOwner(appSysOwner);
                    appRoleMapper.insertSelective(sr);
                } else {
                    DataUtil.setUpdateSystemFieldValue(sr);
                    appRoleMapper.updateByPrimaryKeySelective(sr);
                }
            }
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public String delRoleById(Long roleId) {
        List<AppPatientRole> list = appPatientRoleMapper.selectByRoleId(roleId);// 判断是否正在使用
        if (list != null && !list.isEmpty()) {
            return CommonConstants.WARNING;
        }
        if (roleCanModify(roleId)) {
            appRoleMapper.deleteByPrimaryKey(roleId);
            appRoleMenuMapper.deleteByRoleId(roleId);// 删除关联的菜单
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public String addMenu(AppMenu obj) {
        String[] types = { "api" };
        if (appMenuMapper.selectByKey(obj.getKey(), types) != null) {
            return CommonConstants.WARNING;
        }
        // obj.setVersion(SysParamUtil.getValueByName(CmSysParamConsts.VERSION));
        obj.setType("api");
        DataUtil.setSystemFieldValue(obj);
        obj.setId(null);
        // obj.setSysOwner(HttpServletUtil.getSysName());
        appMenuMapper.insertSelective(obj);
        return CommonConstants.SUCCESS;
    }

    /**
     * 将菜单按钮id转换成按钮key
     *
     * @Title: convertToMenuKeys
     * @param menuIds
     * @return
     *
     */
    private String[] convertToMenuKeys(Long[] menuIds) {
        List<AppMenu> objs = appMenuMapper.selectByIds(menuIds);
        String[] menuKeys = new String[menuIds.length];
        for (int i = 0; i < objs.size(); i++) {
            menuKeys[i] = objs.get(i).getKey();
        }
        return menuKeys;
    }

    /** 检查角色是否为固定角色 */
    private boolean roleCanModify(Long roleId) {
        AppRole sr = appRoleMapper.selectByPrimaryKey(roleId);
        if (sr.getConstantType() != null) {
            return false;
        }
        return true;
    }

    @Override
    public void savePatientRole(Long fkPatientId, Long[] fkRoleIds) {
        if (fkPatientId != null) {
            appPatientRoleMapper.deleteByPatientId(fkPatientId);
        }
        if (fkRoleIds != null) {
            for (Long fkRoleId : fkRoleIds) {
                AppPatientRole appPatientRole = new AppPatientRole();
                appPatientRole.setFkPatientId(fkPatientId);
                appPatientRole.setFkRoleId(fkRoleId);
                appPatientRole.setFkTenantId(UserUtil.getTenantId());
                DataUtil.setAllSystemFieldValue(appPatientRole);
                appPatientRoleMapper.insertSelective(appPatientRole);
            }
        }
    }
}
