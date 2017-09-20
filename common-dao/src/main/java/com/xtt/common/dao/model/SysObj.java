package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_obj
 */
public class SysObj {
    /**
     * sys_obj.id
     */
    private Long id;

    /**
     * 对象key
     * sys_obj.key
     */
    private String key;

    /**
     * 对象名称
     * sys_obj.name
     */
    private String name;

    /**
     * 对象描述
     * sys_obj.description
     */
    private String description;

    /**
     * 对应的CSS样式
     * sys_obj.css_name
     */
    private String cssName;

    /**
     * 对应的版本号
     * sys_obj.version
     */
    private String version;

    /**
     * 对象类型(1:菜单 2:按钮)
     * sys_obj.type
     */
    private String type;

    /**
     * 请求地址
     * sys_obj.url
     */
    private String url;

    /**
     * 对象code
     * sys_obj.code
     */
    private String code;

    /**
     * 父对象ID
     * sys_obj.p_code
     */
    private String pCode;

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     * sys_obj.sys_owner
     */
    private String sysOwner;

    /**
     * 创建时间
     * sys_obj.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_obj.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sys_obj.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sys_obj.update_user_id
     */
    private Long updateUserId;

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
     * 对象key
     */
    public String getKey() {
        return key;
    }

    /**
     * 对象key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 对象名称
     */
    public String getName() {
        return name;
    }

    /**
     * 对象名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 对象描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 对象描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 对应的CSS样式
     */
    public String getCssName() {
        return cssName;
    }

    /**
     * 对应的CSS样式
     */
    public void setCssName(String cssName) {
        this.cssName = cssName;
    }

    /**
     * 对应的版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 对应的版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 对象类型(1:菜单 2:按钮)
     */
    public String getType() {
        return type;
    }

    /**
     * 对象类型(1:菜单 2:按钮)
     */
    public void setType(String type) {
        this.type = type;
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
     * 父对象ID
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * 父对象ID
     */
    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
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
}