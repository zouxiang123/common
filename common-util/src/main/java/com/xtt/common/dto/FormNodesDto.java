package com.xtt.common.dto;

import java.math.BigDecimal;
import java.util.List;

public class FormNodesDto {
	/*========basic items=========*/
	private String itemType;
	private String itemDesc;
	private String unit;
	private Boolean needScore;
	private Integer score;
	private Boolean isFixed;
	private String fkCode;
	private String dataType;
	private BigDecimal minValue;
	private BigDecimal maxValue;
	private Integer displayCols;
	private String groupTag;
	/*========Form conf=========*/
	private Long fkFormId;
	private String itemName;
	private String pItemCode;
	private String itemCode;
	private Integer orderBy;
	private String sysOwner;
	private String itemPath;
	private Integer itemLevel;
	private Boolean isLeaf;
	private Boolean isEnable;
	private Integer fkTenantId;
	/*============form value==============*/
	private String itemValue;
	private Long fkRecordId;

	/*============for display==============*/
	private String unitShow;
	/** 子节点列表 */
	private List<FormNodesDto> childNodes;

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getNeedScore() {
		return needScore;
	}

	public void setNeedScore(Boolean needScore) {
		this.needScore = needScore;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getIsFixed() {
		return isFixed;
	}

	public void setIsFixed(Boolean isFixed) {
		this.isFixed = isFixed;
	}

	public String getFkCode() {
		return fkCode;
	}

	public void setFkCode(String fkCode) {
		this.fkCode = fkCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getFkFormId() {
		return fkFormId;
	}

	public void setFkFormId(Long fkFormId) {
		this.fkFormId = fkFormId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

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

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getSysOwner() {
		return sysOwner;
	}

	public void setSysOwner(String sysOwner) {
		this.sysOwner = sysOwner;
	}

	public String getItemPath() {
		return itemPath;
	}

	public void setItemPath(String itemPath) {
		this.itemPath = itemPath;
	}

	public Integer getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(Integer itemLevel) {
		this.itemLevel = itemLevel;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getFkTenantId() {
		return fkTenantId;
	}

	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
	}

	public List<FormNodesDto> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<FormNodesDto> childNodes) {
		this.childNodes = childNodes;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Long getFkRecordId() {
		return fkRecordId;
	}

	public void setFkRecordId(Long fkRecordId) {
		this.fkRecordId = fkRecordId;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getDisplayCols() {
		return displayCols;
	}

	public void setDisplayCols(Integer displayCols) {
		this.displayCols = displayCols;
	}

	public String getGroupTag() {
		return groupTag;
	}

	public void setGroupTag(String groupTag) {
		this.groupTag = groupTag;
	}

	public String getUnitShow() {
		return unitShow;
	}

	public void setUnitShow(String unitShow) {
		this.unitShow = unitShow;
	}

}