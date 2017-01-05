package com.xtt.common.dao.po;

public class QueryPO {

	private Long fkPatientId; // 血透管理系统中的患者ID

	private String cardType; // 卡号类型
	private String cardNo; // 卡号
	private String ptId; // 患者id
	private String visitId; // 次数
	private String startDate;// 开始日期
	private String endDate; // 结束日期
	private String downType; // 下载类型（1=患者基本信息 2=Lis 数据 3=PACS影像信息 4= 医嘱信息）

	private String retMsg; // 返回结果消息
	private Integer fkTenantId; // 租户ID 比较重要

	private String uniqueId; // 唯一ID
	private String assayMonth; // 月份字符（2016-11）
	// MQ ========================================================================
	private String fid;

	// where =====================================================================
	private String where;
	private String where1;
	private String value1;
	private String where2;
	private String value2;
	private String where3;
	private String value3;
	private String orders;

	private String mapKey;

	private String mapValue;

	// where =====================================================================

	private String year;

	private Integer month;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public String getMapValue() {
		return mapValue;
	}

	public void setMapValue(String mapValue) {
		this.mapValue = mapValue;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getAssayMonth() {
		return assayMonth;
	}

	public void setAssayMonth(String assayMonth) {
		this.assayMonth = assayMonth;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public Long getFkPatientId() {
		return fkPatientId;
	}

	public void setFkPatientId(Long fkPatientId) {
		this.fkPatientId = fkPatientId;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getWhere1() {
		return where1;
	}

	public void setWhere1(String where1) {
		this.where1 = where1;
	}

	public String getWhere2() {
		return where2;
	}

	public void setWhere2(String where2) {
		this.where2 = where2;
	}

	public String getWhere3() {
		return where3;
	}

	public void setWhere3(String where3) {
		this.where3 = where3;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public Integer getFkTenantId() {
		return fkTenantId;
	}

	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPtId() {
		return ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDownType() {
		return downType;
	}

	public void setDownType(String downType) {
		this.downType = downType;
	}

}
