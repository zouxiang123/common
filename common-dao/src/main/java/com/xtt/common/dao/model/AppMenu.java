package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_menu
 */
public class AppMenu {
    /**
     * app_menu.id
     */
    private Long id;

    /**
     * key
     * app_menu.key
     */
    private String key;

    /**
     * 菜单名称
     * app_menu.name
     */
    private String name;

    /**
     * 备注
     * app_menu.description
     */
    private String description;

    /**
     * 请求地址
     * app_menu.url
     */
    private String url;

    /**
     * 对象code
     * app_menu.code
     */
    private String code;

    /**
     * 父code
     * app_menu.p_code
     */
    private String pCode;

    /**
     * app_menu.app_sys_owner
     */
    private String appSysOwner;

    /**
     * 菜单类型(1：菜单 2：按钮
     * app_menu.type
     */
    private String type;

    /**
     * 菜单类型
     * app_menu.menu_type
     */
    private String menuType;

    /**
     * 创建时间
     * app_menu.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * app_menu.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * app_menu.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * app_menu.update_user_id
     */
    private Long updateUserId;

    /**
     * 租户id
     * app_menu.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * key
     */
    public String getKey() {
        return key;
    }

    /**
     * key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 请求地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 请求地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 对象code
     */
    public String getCode() {
        return code;
    }

    /**
     * 对象code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 父code
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * 父code
     */
    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    /**
     */
    public String getAppSysOwner() {
        return appSysOwner;
    }

    /**
     */
    public void setAppSysOwner(String appSysOwner) {
        this.appSysOwner = appSysOwner;
    }

    /**
     * 菜单类型(1：菜单 2：按钮
     */
    public String getType() {
        return type;
    }

    /**
     * 菜单类型(1：菜单 2：按钮
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 菜单类型
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 菜单类型
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 租户id
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户id
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }
}