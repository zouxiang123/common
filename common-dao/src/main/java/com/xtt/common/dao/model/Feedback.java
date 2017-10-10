package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * feedback
 */
public class Feedback {
    /**
     * feedback.id
     */
    private Long id;

    /**
     * 联系方式
     * feedback.tel
     */
    private String tel;

    /**
     * 反馈内容
     * feedback.content
     */
    private String content;

    /**
     * 用户访问设备信息
     * feedback.user_agent
     */
    private String userAgent;

    /**
     * IP地址
     * feedback.ip
     */
    private String ip;

    /**
     * 经度
     * feedback.longitude
     */
    private BigDecimal longitude;

    /**
     * 纬度
     * feedback.latitude
     */
    private BigDecimal latitude;

    /**
     * 租户
     * feedback.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建人
     * feedback.create_user_id
     */
    private Long createUserId;

    /**
     * 创建时间
     * feedback.create_time
     */
    private Date createTime;

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
     * 联系方式
     */
    public String getTel() {
        return tel;
    }

    /**
     * 联系方式
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 反馈内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 反馈内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 用户访问设备信息
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 用户访问设备信息
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 经度
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * 经度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 纬度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 租户
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
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
}