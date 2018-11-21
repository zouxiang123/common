package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_hospital_address
 */
public class AppHospitalAddress {
    /**
     * id app_hospital_address.id
     */
    private Long id;

    /**
     * 医院名称 app_hospital_address.name
     */
    private String name;

    /**
     * 英文全拼 app_hospital_address.spell_initials
     */
    private String spellInitials;

    /**
     * app项目url app_hospital_address.url
     */
    private String url;

    /**
     * 血透项目的url app_hospital_address.hd_url
     */
    private String hdUrl;

    /**
     * 背景图片 app_hospital_address.bg_image
     */
    private String bgImage;

    /**
     * 是否集团用户 app_hospital_address.group_flag
     */
    private Boolean groupFlag;

    /**
     * 是否可用 app_hospital_address.is_enable
     */
    private Boolean isEnable;

    /**
     * 排序 app_hospital_address.order_by
     */
    private Integer orderBy;

    /**
     * 是否需要检测服务是否通畅 app_hospital_address.check_connect
     */
    private Boolean checkConnect;

    /**
     * 推数据或拉数据 01：推数据 02拉数据 app_hospital_address.pull_and_Push
     */
    private String pullAndPush;

    /**
     * 创建时间 app_hospital_address.create_time
     */
    private Date createTime;

    /**
     * 创建人 app_hospital_address.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 app_hospital_address.update_time
     */
    private Date updateTime;

    /**
     * 更新人 app_hospital_address.update_user_id
     */
    private Long updateUserId;

    /**
     * app_hospital_address.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 医院名称
     */
    public String getName() {
        return name;
    }

    /**
     * 医院名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 英文全拼
     */
    public String getSpellInitials() {
        return spellInitials;
    }

    /**
     * 英文全拼
     */
    public void setSpellInitials(String spellInitials) {
        this.spellInitials = spellInitials;
    }

    /**
     * app项目url
     */
    public String getUrl() {
        return url;
    }

    /**
     * app项目url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 血透项目的url
     */
    public String getHdUrl() {
        return hdUrl;
    }

    /**
     * 血透项目的url
     */
    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    /**
     * 背景图片
     */
    public String getBgImage() {
        return bgImage;
    }

    /**
     * 背景图片
     */
    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    /**
     * 是否集团用户
     */
    public Boolean getGroupFlag() {
        return groupFlag;
    }

    /**
     * 是否集团用户
     */
    public void setGroupFlag(Boolean groupFlag) {
        this.groupFlag = groupFlag;
    }

    /**
     * 是否可用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否可用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 排序
     */
    public Integer getOrderBy() {
        return orderBy;
    }

    /**
     * 排序
     */
    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 是否需要检测服务是否通畅
     */
    public Boolean getCheckConnect() {
        return checkConnect;
    }

    /**
     * 是否需要检测服务是否通畅
     */
    public void setCheckConnect(Boolean checkConnect) {
        this.checkConnect = checkConnect;
    }

    /**
     * 推数据或拉数据 01：推数据 02拉数据
     */
    public String getPullAndPush() {
        return pullAndPush;
    }

    /**
     * 推数据或拉数据 01：推数据 02拉数据
     */
    public void setPullAndPush(String pullAndPush) {
        this.pullAndPush = pullAndPush;
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
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }
}