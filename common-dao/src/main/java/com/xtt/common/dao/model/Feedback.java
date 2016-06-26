package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class Feedback {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.tel
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.content
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.user_agent
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private String userAgent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.ip
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.longitude
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private BigDecimal longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.latitude
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private BigDecimal latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.fk_tenant_id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private Integer fkTenantId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.create_user_id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.create_time
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.id
     *
     * @return the value of feedback.id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.id
     *
     * @param id the value for feedback.id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.tel
     *
     * @return the value of feedback.tel
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.tel
     *
     * @param tel the value for feedback.tel
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.content
     *
     * @return the value of feedback.content
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.content
     *
     * @param content the value for feedback.content
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.user_agent
     *
     * @return the value of feedback.user_agent
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.user_agent
     *
     * @param userAgent the value for feedback.user_agent
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.ip
     *
     * @return the value of feedback.ip
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.ip
     *
     * @param ip the value for feedback.ip
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.longitude
     *
     * @return the value of feedback.longitude
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.longitude
     *
     * @param longitude the value for feedback.longitude
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.latitude
     *
     * @return the value of feedback.latitude
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.latitude
     *
     * @param latitude the value for feedback.latitude
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.fk_tenant_id
     *
     * @return the value of feedback.fk_tenant_id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.fk_tenant_id
     *
     * @param fkTenantId the value for feedback.fk_tenant_id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.create_user_id
     *
     * @return the value of feedback.create_user_id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.create_user_id
     *
     * @param createUserId the value for feedback.create_user_id
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.create_time
     *
     * @return the value of feedback.create_time
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.create_time
     *
     * @param createTime the value for feedback.create_time
     *
     * @mbggenerated Fri Jun 17 09:55:17 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}