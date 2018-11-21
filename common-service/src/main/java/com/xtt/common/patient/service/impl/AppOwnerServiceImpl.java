/**   
 * @Title: AppOwnerServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年8月13日 下午3:39:52 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.AppMenuMapper;
import com.xtt.common.dao.mapper.AppOwnerMapper;
import com.xtt.common.dao.mapper.AppOwnerMenuMapper;
import com.xtt.common.dao.model.AppMenu;
import com.xtt.common.dao.model.AppOwner;
import com.xtt.common.dao.model.AppOwnerMenu;
import com.xtt.common.patient.service.IAppOwnerService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class AppOwnerServiceImpl implements IAppOwnerService {
    @Autowired
    private AppOwnerMenuMapper appOwnerMenuMapper;
    @Autowired
    private AppOwnerMapper appOwnerMapper;
    @Autowired
    private AppMenuMapper appMenuMapper;

    @Override
    public void saveMenuList(Long[] checkedMenuIds, String sysOwner, String[] types) {
        if (checkedMenuIds != null && StringUtils.isNotBlank(sysOwner) && checkedMenuIds.length > 0) {
            appOwnerMenuMapper.deleteByOwnerAndType(sysOwner, types);
            List<AppOwnerMenu> menus = new ArrayList<AppOwnerMenu>(checkedMenuIds.length);
            AppOwnerMenu sr;
            String[] menuKeys = convertToMenuKeys(checkedMenuIds);
            for (int i = 0; i < menuKeys.length; i++) {
                sr = new AppOwnerMenu();
                sr.setFkMenuKey(menuKeys[i]);
                sr.setFkTenantId(UserUtil.getTenantId());
                sr.setFkSysOwner(sysOwner);
                DataUtil.setAllSystemFieldValue(sr);
                menus.add(sr);
            }
            appOwnerMenuMapper.insertBatch(menus);
        }
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

    @Override
    public List<AppOwner> listByCond(AppOwner appOwner) {
        return appOwnerMapper.listByCond(appOwner);
    }
}
