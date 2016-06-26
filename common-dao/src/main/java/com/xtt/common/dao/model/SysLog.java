package com.xtt.common.dao.model;

import java.util.Date;

import com.xtt.platform.framework.core.model.MyBatisSuperModel;

public class SysLog extends MyBatisSuperModel {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.operator_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long operatorId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.log_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Date logTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.log_type
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private String logType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.log_info
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private String logInfo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.fk_tenant_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Integer fkTenantId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.create_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.create_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long createUserId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.update_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Date updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_log.update_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long updateUserId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.id
	 *
	 * @return the value of sys_log.id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.id
	 *
	 * @param id
	 *            the value for sys_log.id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.operator_id
	 *
	 * @return the value of sys_log.operator_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.operator_id
	 *
	 * @param operatorId
	 *            the value for sys_log.operator_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.log_time
	 *
	 * @return the value of sys_log.log_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Date getLogTime() {
		return logTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.log_time
	 *
	 * @param logTime
	 *            the value for sys_log.log_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.log_type
	 *
	 * @return the value of sys_log.log_type
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public String getLogType() {
		return logType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.log_type
	 *
	 * @param logType
	 *            the value for sys_log.log_type
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setLogType(String logType) {
		this.logType = logType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.log_info
	 *
	 * @return the value of sys_log.log_info
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public String getLogInfo() {
		return logInfo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.log_info
	 *
	 * @param logInfo
	 *            the value for sys_log.log_info
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.fk_tenant_id
	 *
	 * @return the value of sys_log.fk_tenant_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Integer getFkTenantId() {
		return fkTenantId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.fk_tenant_id
	 *
	 * @param fkTenantId
	 *            the value for sys_log.fk_tenant_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.create_time
	 *
	 * @return the value of sys_log.create_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.create_time
	 *
	 * @param createTime
	 *            the value for sys_log.create_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.create_user_id
	 *
	 * @return the value of sys_log.create_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.create_user_id
	 *
	 * @param createUserId
	 *            the value for sys_log.create_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.update_time
	 *
	 * @return the value of sys_log.update_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.update_time
	 *
	 * @param updateTime
	 *            the value for sys_log.update_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_log.update_user_id
	 *
	 * @return the value of sys_log.update_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_log.update_user_id
	 *
	 * @param updateUserId
	 *            the value for sys_log.update_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
}