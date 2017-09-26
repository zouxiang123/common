package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_entity_value
 */
public class CmDiagnosisEntityValue {
	/**
	 * 主键Id cm_diagnosis_entity_value.id
	 */
	private Long id;

	/**
	 * 患者Id cm_diagnosis_entity_value.fk_patient_id
	 */
	private Long fkPatientId;

	/**
	 * 患者诊断之选项存储主表Id cm_diagnosis_entity_value.fk_entity_id
	 */
	private Long fkEntityId;

	/**
	 * 字典表item_code值 cm_diagnosis_entity_value.item_code
	 */
	private String itemCode;

	/**
	 * 字典表item_code对应子集values值 cm_diagnosis_entity_value.item_value
	 */
	private String itemValue;

	/**
	 * 租户Id cm_diagnosis_entity_value.fk_tenant_id
	 */
	private Integer fkTenantId;

	/**
	 * 创建时间 cm_diagnosis_entity_value.create_time
	 */
	private Date createTime;

	/**
	 * 创建人 cm_diagnosis_entity_value.create_user_id
	 */
	private Long createUserId;

	/**
	 * 更新时间 cm_diagnosis_entity_value.update_time
	 */
	private Date updateTime;

	/**
	 * 更新人 cm_diagnosis_entity_value.update_user_id
	 */
	private Long updateUserId;

	/**
	 * 其它节点的文本内容（扩展） cm_diagnosis_entity_value.content
	 */
	private String content;

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
	 * 患者Id
	 */
	public Long getFkPatientId() {
		return fkPatientId;
	}

	/**
	 * 患者Id
	 */
	public void setFkPatientId(Long fkPatientId) {
		this.fkPatientId = fkPatientId;
	}

	/**
	 * 患者诊断之选项存储主表Id
	 */
	public Long getFkEntityId() {
		return fkEntityId;
	}

	/**
	 * 患者诊断之选项存储主表Id
	 */
	public void setFkEntityId(Long fkEntityId) {
		this.fkEntityId = fkEntityId;
	}

	/**
	 * 字典表item_code值
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * 字典表item_code值
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * 字典表item_code对应子集values值
	 */
	public String getItemValue() {
		return itemValue;
	}

	/**
	 * 字典表item_code对应子集values值
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
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

	/**
	 * 其它节点的文本内容（扩展）
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 其它节点的文本内容（扩展）
	 */
	public void setContent(String content) {
		this.content = content;
	}
}