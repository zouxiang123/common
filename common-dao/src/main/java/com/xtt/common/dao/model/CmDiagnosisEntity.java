package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_entity
 */
public class CmDiagnosisEntity {
	/**
	 * 主键Id cm_diagnosis_entity.id
	 */
	private Long id;

	/**
	 * 诊断患者Id cm_diagnosis_entity.fk_patient_id
	 */
	private Long fkPatientId;

	/**
	 * 字典表item_code值（诊断第一层item_code值） cm_diagnosis_entity.item_code
	 */
	private String itemCode;

	/**
	 * 租户Id cm_diagnosis_entity.fk_tenant_id
	 */
	private Integer fkTenantId;

	/**
	 * 创建时间 cm_diagnosis_entity.create_time
	 */
	private Date createTime;

	/**
	 * 创建人 cm_diagnosis_entity.create_user_id
	 */
	private Long createUserId;

	/**
	 * 更新时间 cm_diagnosis_entity.update_time
	 */
	private Date updateTime;

	/**
	 * 更新人 cm_diagnosis_entity.update_user_id
	 */
	private Long updateUserId;

	/**
	 * 主键Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 诊断患者Id
	 */
	public Long getFkPatientId() {
		return fkPatientId;
	}

	/**
	 * 诊断患者Id
	 */
	public void setFkPatientId(Long fkPatientId) {
		this.fkPatientId = fkPatientId;
	}

	/**
	 * 字典表item_code值（诊断第一层item_code值）
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * 字典表item_code值（诊断第一层item_code值）
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * 租户Id
	 */
	public Integer getFkTenantId() {
		return fkTenantId;
	}

	/**
	 * 租户Id
	 */
	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
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