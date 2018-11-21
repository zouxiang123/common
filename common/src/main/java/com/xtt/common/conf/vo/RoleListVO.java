/**   
 * @Title: RoleVO.java 
 * @Package com.xtt.common.system.vo
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年10月24日 上午11:33:09 
 *
 */
package com.xtt.common.conf.vo;

import com.xtt.common.dao.model.AppRole;

public class RoleListVO {
    private Long[] checkedMenuIds;
    private Long menuRoleId;
    private Long[] delRoleIds;
    private AppRole[] roles;
    private String sysOwner;

    public Long[] getCheckedMenuIds() {
        return checkedMenuIds;
    }

    public void setCheckedMenuIds(Long[] checkedMenuIds) {
        this.checkedMenuIds = checkedMenuIds;
    }

    public Long getMenuRoleId() {
        return menuRoleId;
    }

    public void setMenuRoleId(Long menuRoleId) {
        this.menuRoleId = menuRoleId;
    }

    public Long[] getDelRoleIds() {
        return delRoleIds;
    }

    public void setDelRoleIds(Long[] delRoleIds) {
        this.delRoleIds = delRoleIds;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    public AppRole[] getRoles() {
        return roles;
    }

    public void setRoles(AppRole[] roles) {
        this.roles = roles;
    }

}
