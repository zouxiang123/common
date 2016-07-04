/**   
 * @Title: PatientAssayRecordPO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:46:36 
 *
 */
package com.xtt.common.dao.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xtt.common.dao.model.PatientAssayRecord;

public class PatientAssayRecordPO extends PatientAssayRecord {
	/** 正常 */
	public static String TIPS_NORMAL = "1";
	/** 无法识别的 */
	public static String TIPS_UNKNOWN = "2";
	/** 偏低 */
	public static String TIPS_LOW = "3";
	/** 偏高 */
	public static String TIPS_HIGH = "4";

	/** 1：数字；2：字符 */
	private BigDecimal minValue;
	private BigDecimal maxValue;
	private String unit;
	private Integer orderBy;
	private String[] groupIds;
	private String fkDictCode;
	private Integer valueType;

	private String startDateStr;
	private String endDateStr;
	private Date startDate;
	private Date endDate;
	private String month;
	private Integer allCount;// 总数量
	private Integer okCount;// 达标数量
	private Double allSum;// 求和
	private Double avg;// 平均值
	private Double pop; // 标准差

	private String patientName;// 患者名字
	private String dataType;// 日期类型
	private String dateStr;// 日期字符串

	private String resultTipsShow;// 是否达标
	private String[] multResultTips;

	private String groupRange;// 分组

	private List<PatientAssayGroupRulePO> ruleList;// 化验规则集合

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public Integer getOkCount() {
		return okCount;
	}

	public void setOkCount(Integer okCount) {
		this.okCount = okCount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getAllSum() {
		return allSum;
	}

	public void setAllSum(Double allSum) {
		this.allSum = allSum;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Double getPop() {
		return pop;
	}

	public void setPop(Double pop) {
		this.pop = pop;
	}

	public String[] getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}

	public String getFkDictCode() {
		return fkDictCode;
	}

	public void setFkDictCode(String fkDictCode) {
		this.fkDictCode = fkDictCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getValueType() {
		return valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getResultTipsShow() {
		return resultTipsShow;
	}

	public void setResultTipsShow(String resultTipsShow) {
		this.resultTipsShow = resultTipsShow;
	}

	public String getGroupRange() {
		return groupRange;
	}

	public void setGroupRange(String groupRange) {
		this.groupRange = groupRange;
	}

	public List<PatientAssayGroupRulePO> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<PatientAssayGroupRulePO> ruleList) {
		this.ruleList = ruleList;
	}

	public String[] getMultResultTips() {
		return multResultTips;
	}

	public void setMultResultTips(String[] multResultTips) {
		this.multResultTips = multResultTips;
	}

}
