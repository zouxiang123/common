/**   
 * @Title: PatientAssayGroupRulePO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月17日 下午7:49:36 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayGroupRule;

public class PatientAssayGroupRulePO extends PatientAssayGroupRule {
	private String showCreateTime;
	private String showUpdateTime;

	public String getShowCreateTime() {
		return showCreateTime;
	}

	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}

	public String getShowUpdateTime() {
		return showUpdateTime;
	}

	public void setShowUpdateTime(String showUpdateTime) {
		this.showUpdateTime = showUpdateTime;
	}
}
