/**   
 * @Title: RoleServiceImpl.java 
 * @Package com.xtt.common.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年10月23日 下午2:16:33 
 *
 */
package com.xtt.common.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.SysObjMapper;
import com.xtt.common.dao.mapper.SysRole2objMapper;
import com.xtt.common.dao.mapper.SysRoleMapper;
import com.xtt.common.dao.mapper.SysUser2roleMapper;
import com.xtt.common.dao.model.SysObj;
import com.xtt.common.dao.model.SysRole;
import com.xtt.common.dao.model.SysRole2obj;
import com.xtt.common.dao.model.SysUser2role;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUser2roleMapper sysUser2roleMapper;
    @Autowired
    private SysObjMapper sysObjMapper;
    @Autowired
    private SysRole2objMapper sysRole2objMapper;

    @Override
    public List<SysRole> getRoleListByTenantId(Integer tenantId, String sysOwner) {
        return sysRoleMapper.selectSysRoleByTenantId(tenantId, sysOwner);
    }

    @Override
    public List<SysObj> getMenuListByRoleId(Long[] roleId, String[] types, String sysOwner) {
        return sysObjMapper.selectMenuListByRoleId(roleId, types, null);
    }

    @Override
    public List<SysObj> getAllMenuList(String[] types, String sysOwner) {
        return sysObjMapper.selectAllMenuList(SysParamUtil.getValueByName(CmSysParamConsts.VERSION), types, null);
    }

    @Override
    public void saveMenuList(Long[] checkedMenuIds, Long menuRoleId, String[] types) {
        if (checkedMenuIds != null && menuRoleId != null && checkedMenuIds.length > 0) {
            sysRole2objMapper.deleteByRoleIdAndType(menuRoleId, types);
            SysRole2obj[] menus = new SysRole2obj[checkedMenuIds.length];
            SysRole2obj sr;
            String[] menuKeys = convertToMenuKeys(checkedMenuIds);
            for (int i = 0; i < menuKeys.length; i++) {
                sr = new SysRole2obj();
                sr.setFkObjKey(menuKeys[i]);
                sr.setFkTenantId(UserUtil.getTenantId());
                sr.setFkRoleId(menuRoleId);
                DataUtil.setSystemFieldValue(sr);
                menus[i] = sr;
            }
            sysRole2objMapper.batchInsert(menus);
        }
    }

    @Override
    public String saveRoleList(Long[] delRoleIds, SysRole[] roles, String sysOwner) {
        if (delRoleIds != null && delRoleIds.length > 0) {
            List<Long> canDelRoles = new ArrayList<Long>();
            for (Long roleId : delRoleIds) {
                List<SysUser2role> list = sysUser2roleMapper.selectByRoleId(roleId);
                if (list != null && !list.isEmpty()) {
                    return CommonConstants.WARNING;
                }
                if (roleCanModify(roleId)) {
                    canDelRoles.add(roleId);
                }
            }
            if (canDelRoles.size() > 0) {
                Long[] delArr = canDelRoles.toArray(new Long[canDelRoles.size()]);
                sysRoleMapper.batchDeleteByRoleIds(delArr);
                sysUser2roleMapper.batchDeleteByRoleIds(delArr);
                sysRole2objMapper.batchDeleteByRoleIds(delArr);
            }

        }
        if (roles != null && roles.length > 0) {
            for (SysRole sr : roles) {
                if (sr.getConstantType() != null) {// 固定角色不能修改
                    continue;
                }
                if (sr.getId() == null) {
                    sr.setFkTenantId(UserUtil.getTenantId());
                    DataUtil.setSystemFieldValue(sr);
                    sr.setSysOwner(sysOwner);
                    sysRoleMapper.insertSelective(sr);
                } else {
                    DataUtil.setSystemFieldValue(sr);
                    sysRoleMapper.updateByPrimaryKey(sr);
                }
            }
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public String delRoleById(Long roleId) {
        List<SysUser2role> list = sysUser2roleMapper.selectByRoleId(roleId);// 判断是否正在使用
        if (list != null && !list.isEmpty()) {
            return CommonConstants.WARNING;
        }
        if (roleCanModify(roleId)) {
            sysRoleMapper.deleteByPrimaryKey(roleId);
            sysRole2objMapper.deleteByRoleId(roleId);// 删除关联的菜单
        }
        return CommonConstants.SUCCESS;
    }

    @Override
    public List<SysObj> getNotChecked(Long[] roleIds, String[] types, String sysOwner) {
        return sysObjMapper.selectNotChecked(SysParamUtil.getValueByName(CmSysParamConsts.VERSION), roleIds, types, sysOwner);
    }

    @Override
    public String addMenu(SysObj obj) {
        String[] types = { "api" };
        if (sysObjMapper.selectByKey(obj.getKey(), types, obj.getSysOwner()) != null)
            return CommonConstants.WARNING;
        obj.setVersion(SysParamUtil.getValueByName(CmSysParamConsts.VERSION));
        obj.setType("api");
        DataUtil.setSystemFieldValue(obj);
        obj.setId(null);
        obj.setSysOwner(UserUtil.getSysOwner());
        sysObjMapper.insertSelective(obj);
        return CommonConstants.SUCCESS;
    }

    @Override
    public void delMenu(Long[] menuIds) {
        sysRole2objMapper.deleteByMenuKey(convertToMenuKeys(menuIds), UserUtil.getTenantId());
        sysObjMapper.deleteMenuByIds(menuIds);
    }

    @Override
    public SysRole getByConstant(int constantType, Integer tenantId, String sysOwner) {
        int[] constantTypes = { constantType };
        List<SysRole> list = sysRoleMapper.selectByConstant(constantTypes, tenantId, sysOwner);
        if (list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /** 检查角色是否为固定角色 */
    private boolean roleCanModify(Long roleId) {
        SysRole sr = sysRoleMapper.selectByPrimaryKey(roleId);
        if (sr.getConstantType() != null) {
            return false;
        }
        return true;
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
        List<SysObj> objs = sysObjMapper.selectByIds(menuIds);
        String[] menuKeys = new String[menuIds.length];
        for (int i = 0; i < objs.size(); i++) {
            menuKeys[i] = objs.get(i).getKey();
        }
        return menuKeys;
    }

    @Override
    public List<SysRole> getByConstants(int[] constantTypes, Integer tenantId, String sysOwner) {
        return sysRoleMapper.selectByConstant(constantTypes, tenantId, sysOwner);
    }
}
