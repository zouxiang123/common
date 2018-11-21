/**   
 * @Title: IAppOwnerService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年8月13日 下午3:38:57 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.model.AppOwner;

public interface IAppOwnerService {
    /**
     * 保存选中的菜单
     * 
     * @Title: saveMenuList
     * @param checkedMenuIds
     * @param sysOwner
     * @param types
     *
     */
    void saveMenuList(Long[] checkedMenuIds, String sysOwner, String[] types);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param appOwner
     * @return
     *
     */
    List<AppOwner> listByCond(AppOwner appOwner);
}
