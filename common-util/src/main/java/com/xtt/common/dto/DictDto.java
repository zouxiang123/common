package com.xtt.common.dto;

/**
 * cm_dict
 */
public class DictDto {

	/**
	 * 父节点 cm_dict.p_item_code
	 */
	private String pItemCode;

	/**
	 * 类型。如性别(sex)、状态(status) cm_dict.item_code
	 */
	private String itemCode;

	/**
	 * 页面显示 cm_dict.item_name
	 */
	private String itemName;

	/**
	 * 排序 cm_dict.order_by
	 */
	private Integer orderBy;

	/**
	 * 是否可用 cm_dict.is_enable
	 */
	private Boolean isEnable;

	/**
	 * 所属系统 cm_dict.sys_owner
	 */
	private String sysOwner;

	/**
	 * 租户id cm_dict.fk_tenant_id
	 */
	private Integer fkTenantId;

	// 是否选中
	private Boolean isChecked = false;

	public String getpItemCode() {
		return pItemCode;
	}

	public void setpItemCode(String pItemCode) {
		this.pItemCode = pItemCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getSysOwner() {
		return sysOwner;
	}

	public void setSysOwner(String sysOwner) {
		this.sysOwner = sysOwner;
	}

	public Integer getFkTenantId() {
		return fkTenantId;
	}

	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

}