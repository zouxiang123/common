package com.xtt.common.dao.model;

import java.util.Date;

public class SysRole {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.parent_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long parentId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.name
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.description
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private String description;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.constant_type
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Integer constantType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.fk_tenant_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Integer fkTenantId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.create_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.create_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long createUserId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.update_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Date updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.update_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	private Long updateUserId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.id
	 *
	 * @return the value of sys_role.id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.id
	 *
	 * @param id
	 *            the value for sys_role.id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.parent_id
	 *
	 * @return the value of sys_role.parent_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.parent_id
	 *
	 * @param parentId
	 *            the value for sys_role.parent_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.name
	 *
	 * @return the value of sys_role.name
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.name
	 *
	 * @param name
	 *            the value for sys_role.name
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.description
	 *
	 * @return the value of sys_role.description
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.description
	 *
	 * @param description
	 *            the value for sys_role.description
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.constant_type
	 *
	 * @return the value of sys_role.constant_type
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Integer getConstantType() {
		return constantType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.constant_type
	 *
	 * @param constantType
	 *            the value for sys_role.constant_type
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setConstantType(Integer constantType) {
		this.constantType = constantType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.fk_tenant_id
	 *
	 * @return the value of sys_role.fk_tenant_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Integer getFkTenantId() {
		return fkTenantId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.fk_tenant_id
	 *
	 * @param fkTenantId
	 *            the value for sys_role.fk_tenant_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.create_time
	 *
	 * @return the value of sys_role.create_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.create_time
	 *
	 * @param createTime
	 *            the value for sys_role.create_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.create_user_id
	 *
	 * @return the value of sys_role.create_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.create_user_id
	 *
	 * @param createUserId
	 *            the value for sys_role.create_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.update_time
	 *
	 * @return the value of sys_role.update_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.update_time
	 *
	 * @param updateTime
	 *            the value for sys_role.update_time
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.update_user_id
	 *
	 * @return the value of sys_role.update_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.update_user_id
	 *
	 * @param updateUserId
	 *            the value for sys_role.update_user_id
	 *
	 * @mbggenerated Fri Jun 17 09:57:37 CST 2016
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
}